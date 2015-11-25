package basic;

import java.io.File;

import android.os.Environment;

/**
 * 生成或获取文件路径名
 *
 * @author Administrator
 *
 */
public class FileNameUtil {

    private static String PHOTO_PATH;

    private static String VOICE_PATH;

    private static FileNameUtil instance;

    private static String currentPicName = null;

    private static String currentVoiceName = null;

    public FileNameUtil() {
        String strDir = "/sdcard";
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.DIRECTORY_DCIM); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory().getAbsoluteFile();// 获取根目录
            if (!AsessTool.judgeStrNull(sdDir.getAbsolutePath())) {
                strDir = sdDir.getAbsolutePath();
            }
            // L.e("文件根路径："+sdDir);
        }

        PHOTO_PATH = strDir + "/DCIM/Camera";
        VOICE_PATH = strDir + "/linjia";

        File destDir = new File(VOICE_PATH);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }

    }

    public static FileNameUtil getInstance() {
        if (instance == null) {
            instance = new FileNameUtil();
        }
        return instance;
    }

    public synchronized String getPicNameByTime(String suffix) {
        currentPicName = PHOTO_PATH + "/" + System.currentTimeMillis() + "." + suffix;
        return currentPicName;
    }

    public synchronized String getPicNameByName(String name) {
        currentPicName = PHOTO_PATH + "/" + name;
        ;
        return currentPicName;
    }

    public synchronized String getVoiceNameByTime(String suffix) {
        currentVoiceName = VOICE_PATH + "/" + System.currentTimeMillis() + "." + suffix;
        return currentVoiceName;
    }

    public String getAndClearCurrentName() {
        // L.e("当前文件路径："+currentPicName);
        try {
            return currentPicName;
        } finally {
            currentPicName = null;
        }
    }

    public String getCurrentName() {
        return currentPicName;
    }

}
