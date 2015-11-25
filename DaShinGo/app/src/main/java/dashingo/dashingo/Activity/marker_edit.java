package dashingo.dashingo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import basic.FileNameUtil;
import basic.ScreenManager;
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
    public static final int REQUEST_CODE_CAMERA = 18;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marker_edit);
        //图片URL
        picUrl = "";

        //初始化button text
        text_title = (EditText)findViewById(R.id.editText_title);
        text_text  = (EditText)findViewById(R.id.editText_text);
        button_photo = (Button)findViewById(R.id.button_photo);
        button_photo.setOnClickListener(this);
        button_confirm = (Button) findViewById(R.id.button_finish);
        button_confirm.setOnClickListener(this);
        button_cancel = (Button)findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_finish:
                //向MainActivity更改参数，将marker的参数传入
                final MainActivity act = (MainActivity) ScreenManager.getInstance().getActivityByClass(MainActivity.class);
                if (act != null) {
                    act.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //使用线程，调用Mainactivity的方法
                            act.updateMarker(text_title.getText()+"",text_text.getText()+"",picUrl);
                            finish();
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
        picUrl = Uri.fromFile(cameraFile)+"";
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

    }
//    public static boolean isExitsSdcard() {
//        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
//            return true;
//        else
//            return false;
//    }
//}

