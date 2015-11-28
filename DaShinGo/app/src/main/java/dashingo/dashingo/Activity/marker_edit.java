package dashingo.dashingo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import basic.FileNameUtil;
import basic.ScreenManager;
import basic.TDUtils;
import dashingo.dashingo.R;
import map.Picture_marker;

public class marker_edit extends Activity implements View.OnClickListener {
    private EditText text_title;
    private EditText text_text;
    private Button button_photo;
    private Button button_confirm;
    private Button button_cancel;
    private File cameraFile;
    private String picUrl;
    private Picture_marker picture_marker;
    private ImageView imageView_image;
    public static final int REQUEST_CODE_CAMERA = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_edit);

        //图片URL
//        picUrl = "/storage/emulated/0/tencent/MicroMsg/WeiXin/mmexport1436407324128.jpg";
        picUrl = "";
//        File file = new File(picUrl);
        //初始化button text
        text_title = (EditText)findViewById(R.id.editText_title);
        text_text  = (EditText)findViewById(R.id.editText_text);
        button_photo = (Button)findViewById(R.id.button_photo);
        button_photo.setOnClickListener(this);
        button_confirm = (Button) findViewById(R.id.button_finish);
        button_confirm.setOnClickListener(this);
        button_cancel = (Button)findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);
        imageView_image = (ImageView)findViewById(R.id.imageView_image);
        imageView_image.setVisibility(View.VISIBLE);
        Bitmap bitmap = getLoacalBitmap(picUrl);

        imageView_image.setImageBitmap(bitmap);



    }



    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("tanjingrusb",e.toString());
            return null;
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_finish:
                //向MainActivity更改参数，将marker的参数传入
                uploadImage();
                final MainActivity act = (MainActivity) ScreenManager.getInstance().getActivityByClass(MainActivity.class);
                if (act != null) {
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //使用线程，调用Mainactivity的方法
                            act.updateMarker(text_title.getText()+"",text_text.getText()+"",picUrl);
                            act.finish();
                        }
                    });
                }

//                Intent intent = new Intent();

          //      intent.putExtra("Picture_Marker",picture_marker);
           //     intent.putExtra("title", title.getText())
            //            .putExtra("content", text.getText())
           //             .putExtra("pictureUrl", Uri.fromFile(cameraFile));
                /*
                 * 调用setResult方法表示我将Intent对象返回给之前的那个Activity，这样就可以在onActivityResult方法中得到Intent对象，
                 */
           //     setResult(1001, intent);
                //    结束当前这个Activity对象的生命

          //      finish();
                break;


            case R.id.button_photo:
                selectPicFromCamera();
                break;

            case R.id.button_cancel:
            //    uploadImage();
            //     imageView_image.setImageBitmap(BitmapFactory.decodeFile(picUrl));
                finish();
                break;
            default:
                break;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_marker_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //通过相机获取照片
    public void selectPicFromCamera() {
        cameraFile = new File(FileNameUtil.getInstance()
                .getPicNameByTime("jpg"));
        cameraFile.getParentFile().mkdirs();
        picUrl = FileNameUtil.getInstance()
                .getPicNameByTime("jpg");
        startActivityForResult(
                new Intent(MediaStore.ACTION_IMAGE_CAPTURE).putExtra(
                        MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile)),
                REQUEST_CODE_CAMERA);
    }

    //获取照片所用
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAMERA:
                if (cameraFile != null && cameraFile.exists()) {
                    picture_marker = new Picture_marker();
                    picture_marker.setTitle(text_title.getText().toString().trim());
                    picture_marker.setContent(text_text.getText().toString().trim());
                    picture_marker.setImagefile(cameraFile);
                }
                break;

            default:
                break;

        }
        }


    public void uploadImage(){

        TDUtils.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final MainApplication mainApplication = MainApplication.getInstance();
                    InputStream input;
                    input = new FileInputStream(new File(picUrl));
                    HttpUtils httpUtils = new HttpUtils();
                    RequestParams requestParams = new RequestParams();
                    //确定图片名
                    requestParams.setHeader("name",System.currentTimeMillis() + ".png");
                    requestParams.setBodyEntity(new InputStreamEntity(input,102400000));
                    httpUtils.send(HttpRequest.HttpMethod.POST, mainApplication.getUpLoadPic(), requestParams, new RequestCallBack<Object>() {
                        @Override
                        public void onSuccess(final ResponseInfo<Object> objectResponseInfo) {
//
                            Log.d("tanjingrusb", (String) objectResponseInfo.result);

                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Log.d("tanjingrusb", s);
                        }
                    });


                } catch (Exception e) {
                    //					NetExceptionManager.showException(context, e);
                    e.printStackTrace();
                    Log.d("tanjingrusb",e.toString());
                }

            }
        });
    }

    }
//    public static boolean isExitsSdcard() {
//        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
//            return true;
//        else
//            return false;
//    }
//}

