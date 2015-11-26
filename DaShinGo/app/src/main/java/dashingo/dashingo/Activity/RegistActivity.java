package dashingo.dashingo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;



import basic.TDUtils;
import dashingo.dashingo.R;
import database.DatabaseHelper;
import model.User;

public class RegistActivity extends Activity implements View.OnClickListener{

    private Button button_regist;
    private Button button_regback;
    private TextView textView_id;
    private TextView textView_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_regist);
        button_regist = (Button)findViewById(R.id.button_regist);
        button_regist.setOnClickListener(this);
        button_regback = (Button) findViewById(R.id.button_regback);
        button_regback.setOnClickListener(this);

        textView_id =(TextView)findViewById(R.id.editText_id);
        textView_password = (TextView)findViewById(R.id.editText_password);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_regist, menu);
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
    public String regist()
    {
        User user = new User();
        user.setUserId(textView_id.getText().toString());
        user.setPassword(textView_password.getText().toString());
        String jsonString = JSON.toJSONString(user);




        return null;
        /*
        注册发送部分
        */
    }

    public void registUser()
    {
        final MainApplication mainApplication = MainApplication.getInstance();
        TDUtils.execute(new Runnable() {
           @Override
            public void run() {
                try {

                    HttpUtils httpUtils = new HttpUtils();
                    RequestParams requestParams = new RequestParams();
                    requestParams.addBodyParameter("userid","1352892sb");
                    requestParams.addBodyParameter("password","1234");
                    requestParams.addBodyParameter("username","tanjingru");
                    //send(http指令，url,requestparams,requestcallback)
                    httpUtils.send(HttpRequest.HttpMethod.POST,mainApplication.getSignupUrl(), requestParams, new RequestCallBack<Object>() {
                        @Override
                        public void onSuccess(ResponseInfo<Object> objectResponseInfo) {

                        }

                        @Override
                        public void onFailure(HttpException e, String s) {

                        }
                    });






                }catch (Exception e) {
                    //					NetExceptionManager.showException(context, e);
                    e.printStackTrace();
                }

            }
        });

    }



    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.button_regist:
                registUser();
                break;
            case R.id.button_regback:
                finish();
                break;
            default:
                break;
        }

    }

    }
