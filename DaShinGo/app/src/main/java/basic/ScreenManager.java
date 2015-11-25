package basic;

/**
 * Created by MSI on 2015/11/24.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import dashingo.dashingo.Activity.MainActivity;

public class ScreenManager  extends Application{

    private static ScreenManager instance;
    // private List<Activity> mList = new LinkedList<Activity>();
    private LinkedList<Activity> mList;
    private Class<?> currentActivity = MainActivity.class;//记录当前的activity
    private Intent intent;

    // 空构造
    private ScreenManager() {
        mList = new LinkedList<Activity>();
    }

    // 单例
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    // 把Activity放入List中
    public void addActivity(Activity activity) {

        mList.addLast(activity);

    }

    public Activity getActivityByClass(Class clazz) {
        for (Activity aty : mList) {
            if (aty.getClass().equals(clazz)) {
                return aty;
            }
        }
        return null;
    }

}
