package dashingo.dashingo.Activity;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import basic.ScreenManager;
import basic.TDUtils;
import dashingo.dashingo.R;
import model.Route;

public class MActivity extends Activity {

    private Route rInformation=new Route();
    private List<Route> routes=new ArrayList<Route>();

    /* private int pic[]=new int[]
             {
                     R.drawable.i1,
                     R.drawable.i2,
                     R.drawable.i3,
                     R.drawable.i1,
                     R.drawable.i2,
                     R.drawable.i3
             };
     private String data[][]=new String[][]
             {
                     {"baidu","lihongyu"},
                     {"alibaba","mayun"},
                     {"tengxun","mahuateng"},
                     {"xinlang","caoguowei"},
                     {"wangyi","dinglei"},
                     {"souhu","zhangchaoyang"},
             };*/
    private ListView dataList=null;
    private List<Map<String,String>> list=new ArrayList<Map<String,String>>();
    private SimpleAdapter simpleAdapter=null;
    private TextView info=null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenManager.getInstance().addActivity(this);



        setContentView(R.layout.activity_m);
        this.dataList=(ListView) super.findViewById(R.id.datalist);
        this.info=(TextView)super.findViewById(R.id.info);
        test();
        /*for(int i=0;i<data.length;i++){

            Map<String,String> map=new HashMap<String, String>();

            map.put("pic", String.valueOf(pic[i]));
            map.put("userName",data[i][0]);
            map.put("ceo", data[i][1]);
            this.list.add(map);
        }
        this.simpleAdapter=new SimpleAdapter(
                this,
                this.list,
                R.layout.datalist,
                new String[]{"pic","userName","ceo"},
                new int[]{R.id.pic,R.id.userName,R.id.ceo}
        );*/
        InitView();
        // this.dataList.setAdapter(this.simpleAdapter);
        //this.dataList.setOnItemClickListener(new OnItemClickListenerImp());
    }

    public void InitView(){
        if(routes.size()>0){
            for(int i=0;i<routes.size();i++){
                Map<String,String> map=new HashMap<String, String>();

                map.put("userName",routes.get(i).getUser().getUserName());
                map.put("description",routes.get(i).getDescription());
                map.put("pic",String.valueOf(R.drawable.i1));
                this.list.add(map);
            }

            this.simpleAdapter=new SimpleAdapter(
                    this,
                    this.list,
                    R.layout.datalist,
                    new String[]{"pic","userName","description"},
                    new int[]{R.id.pic,R.id.userName,R.id.ceo}
            );
        }
        this.dataList.setAdapter(this.simpleAdapter);
        this.dataList.setOnItemClickListener(new OnItemClickListenerImp());
    }


    public void updateView(String routes){
        final List<Route> route =  JSON.parseArray(routes,Route.class);
        this.routes=route;
        InitView();
    }


    public void test()
    {
        TDUtils.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final MainApplication mainApplication = MainApplication.getInstance();
                    HttpUtils httpUtils = new HttpUtils();
                    RequestParams requestParams = new RequestParams();
                    httpUtils.send(HttpRequest.HttpMethod.GET, mainApplication.getGetAllList(), requestParams, new RequestCallBack<Object>() {
                        @Override
                        public void onSuccess(final ResponseInfo<Object> objectResponseInfo) {
//
//                            final List<Route> routeList =  JSON.parseArray((String) objectResponseInfo.result,Route.class);
//                            Log.d("1352863sb", routeList.toString());
                            final MActivity act = (MActivity) ScreenManager.getInstance().getActivityByClass(MActivity.class);
                            if (act != null) {
                                act.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //使用线程，调用Mainactivity的方法
                                        act.updateView((String)objectResponseInfo.result);
                                    }
                                });
                            }


                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            Log.d("tanjingrusb", s);
                        }
                    });


                } catch (Exception e) {
                    //					NetExceptionManager.showException(context, e);
                    e.printStackTrace();
                }

            }
        });


    }


    public class OnItemClickListenerImp implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Map<String,String> map=(Map<String, String>) MActivity.this.simpleAdapter.getItem(position);
            String userName=map.get("userName");
            String ceo=map.get("ceo");
            MActivity.this.info.setText("you select"+userName+"ceois"+ceo);
        }
    }
}

