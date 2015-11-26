package dashingo.dashingo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


//import com.lidroid.xutils.HttpUtils;
//import com.lidroid.xutils.exception.HttpException;
//import com.lidroid.xutils.http.RequestParams;
//import com.lidroid.xutils.http.ResponseInfo;
//import com.lidroid.xutils.http.callback.RequestCallBack;
//import com.lidroid.xutils.http.client.HttpRequest;
//
//import net.sf.json.JSONArray;

import java.io.File;

import dashingo.dashingo.R;
import model.User;


public class HomePage extends Activity implements View.OnClickListener {

    private Button button_tomap;
    private TextView text_id;
    private TextView text_password_;
    private TextView tv_login_regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        tv_login_regist = (TextView) findViewById(R.id.tv_login_regist);
        tv_login_regist.setOnClickListener(this);
        text_id = (TextView)findViewById(R.id.text_id);
        text_password_ = (TextView)findViewById(R.id.text_password_);
        button_tomap=(Button)findViewById(R.id.button_tomap);
        button_tomap.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button_tomap:


                login();
                Intent intent = new Intent();
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
//                if(text_id.getText().toString().isEmpty())
//                {
//                    T.showLong(HomePage.this,"手机号不能为空" );
//                    return;
//                }
//                if(text_password_.getText().toString().isEmpty())
//                {
//                    T.showLong(HomePage.this,"密码不能为空" );
//                    return;
//                }
//                if(text_id)
//                if ((etName.getText().toString())) {
//                    T.showLong(LoginActivity.this, R.string.tel_not_empty);
//                    return;
//                }
//                if (StringUtil.empty(etPwd.getText().toString())) {
//                    T.showLong(LoginActivity.this, R.string.pwd_not_empty);
//                    return;
//                }

                break;
            case R.id.tv_login_regist:
                Intent intent_regist = new Intent();
                intent_regist.setClass(this,RegistActivity.class);
                startActivity(intent_regist);
                break;
            default:
                break;
        }

    }
        public void login()
        {
            User user = new User();
            user.setUserName("zhao");
            user.setPassword(text_password_.getText().toString());
            user.setUserId(text_id.getText().toString());


//            String json = JSONArray.fromObject(user).toString();
//            RequestParams params = new RequestParams();
//            params.addHeader("name", "value");
//            params.addQueryStringParameter("name", "value");
//
//// 只包含字符串参数时默认使用BodyParamsEntity，
//// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
//            params.addBodyParameter("name", "value");

// 加入文件参数后默认使用MultipartEntity（"multipart/form-data"），
// 如需"multipart/related"，xUtils中提供的MultipartEntity支持设置subType为"related"。
// 使用params.setBodyEntity(httpEntity)可设置更多类型的HttpEntity（如：
// MultipartEntity,BodyParamsEntity,FileUploadEntity,InputStreamUploadEntity,StringEntity）。
// 例如发送json参数：params.setBodyEntity(new StringEntity(jsonStr,charset));
            //params.addBodyParameter("file", new File("path"));
//            params.setBodyEntity()
//
//            HttpUtils http = new HttpUtils();
//            http.send(HttpRequest.HttpMethod.POST,
//                    "uploadUrl....",
//                    params,
//                    new RequestCallBack<String>() {
//
//                        @Override
//                        public void onStart() {
//                            testTextView.setText("conn...");
//                        }
//
//                        @Override
//                        public void onLoading(long total, long current, boolean isUploading) {
//                            if (isUploading) {
//                                testTextView.setText("upload: " + current + "/" + total);
//                            } else {
//                                testTextView.setText("reply: " + current + "/" + total);
//                            }
//                        }
//
//                        @Override
//                        public void onSuccess(ResponseInfo<String> responseInfo) {
//                            testTextView.setText("reply: " + responseInfo.result);
//                        }
//
//                        @Override
//                        public void onFailure(HttpException error, String msg) {
//                            testTextView.setText(error.getExceptionCode() + ":" + msg);
//                        }
//                    });



        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
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


}
