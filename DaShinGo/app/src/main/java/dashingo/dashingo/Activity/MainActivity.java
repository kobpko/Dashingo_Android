package dashingo.dashingo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.PolylineOptions;

import java.util.Vector;

import dashingo.dashingo.R;
//手机号登录 微信登陆
//用户 《----粉丝
//推荐轨迹
//

public class MainActivity extends Activity implements LocationSource,AMapLocationListener ,View.OnClickListener, View.OnLongClickListener {
        private MapView mapView;
        private AMap aMap;
        private OnLocationChangedListener mListener;
        private LocationManagerProxy mAMapLocationManager;
        private Vector<AMapLocation> track=new Vector<>();
        private Button button_start;
        private Button button_stop;

        private boolean trackenabled = false;

    private void drawtrace(Vector<AMapLocation> track)
    {
        aMap.addPolyline(new PolylineOptions().add(new LatLng(track.elementAt(0).getLatitude(),track.elementAt(0).getLongitude())).geodesic(true).color(Color.RED));
        if(!track.isEmpty())
        {
            for(int i=1;i<track.size();i++)
            {
                aMap.addPolyline(new PolylineOptions()
                        .visible(true)
                        .add(new LatLng(track.elementAt(i).getLatitude(), track.elementAt(i).getLongitude()))
                        .geodesic(true).color(Color.RED));
            }

        }
    }
    private void drawtraceBypoint(Vector<AMapLocation> track)
    {
        //将vector内的最后两点连接在一起
        aMap.addPolyline(new PolylineOptions()
                .visible(true)
                .add(new LatLng(track.get(track.size()-2).getLatitude(),track.get(track.size()-2).getLongitude()),new LatLng(track.lastElement().getLatitude(),track.lastElement().getLongitude()))
                .geodesic(true).color(Color.RED));

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
    @Override
       public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation.getAMapException().getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                if(trackenabled) {
                    track.add(amapLocation);
                    drawtraceBypoint(track);
                }


            }
        }
    }
    @Override
    public void onLocationChanged(Location location) {


    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }
    @Override
      public void onProviderEnabled(String s) {
        }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        button_start = (Button)findViewById(R.id.button);
        button_start.setOnClickListener(this);
        button_start.setBackgroundColor(Color.GREEN);
        button_stop = (Button)findViewById(R.id.button2);
        button_stop.setOnClickListener(this);
        button_stop.setLongClickable(false);
        button_stop.setBackgroundColor(Color.RED);
        button_stop.setOnLongClickListener(this);
        button_stop.setVisibility(View.INVISIBLE);

        init();         // create AMap object
        aMap.addPolyline(new PolylineOptions()
                .visible(true)
                .add(new LatLng(43.828, 87.621), new LatLng(45.808, 126.55))
                .geodesic(true).color(Color.RED));
    }
    @Override
    public boolean onLongClick(View view) {
        switch(view.getId())
        {
            case R.id.button2:
                trackenabled = false;
                button_start.setClickable(true);
                button_start.setVisibility(View.VISIBLE);
                button_stop.setLongClickable(false);
                button_stop.setVisibility(View.INVISIBLE);
                break;
            default:
                    break;
        }
        return false;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button:
                trackenabled = true;

                button_start.setClickable(false);
                button_stop.setLongClickable(true);
                button_stop.setVisibility(View.VISIBLE);
                button_start.setVisibility(View.INVISIBLE);

                break;
//            case R.id.button2:
//                trackenabled = false;
            default:
                break;
        }

    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    private void init() {
        if (aMap == null)
        {
            aMap = mapView.getMap();
            setUpMap();
        }
    }
    private void setUpMap()
    {
        aMap.setLocationSource(this);
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        aMap.setMyLocationEnabled(true);
        aMap.moveCamera(CameraUpdateFactory.zoomTo(20));
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        aMap.setMyLocationStyle(myLocationStyle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
        if (mAMapLocationManager == null) {
             mAMapLocationManager = LocationManagerProxy.getInstance(this);
             mAMapLocationManager.setGpsEnable(true);
             //此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
             //注意设置合适的定位时间的间隔，并且在合适时间调用removeUpdates()方法来取消定位请求
             //在定位结束后，在合适的生命周期调用destroy()方法
             //其中如果间隔时间为-1，则定位只定一次
            /*
             *
             * 1.0.2版本新增方法，设置true表示混合定位中包含gps定位，false表示纯网络定位，默认是true Location
             * API定位采用GPS和网络混合定位方式
             * ，第一个参数是定位provider，第二个参数时间最短是2000毫秒，第三个参数距离间隔单位是米，第四个参数是定位监听者
             */
             mAMapLocationManager.requestLocationData(
                        LocationProviderProxy.AMapNetwork, 2000, 10, this);
            }

        }
    @Override
    public void deactivate() {
         mListener = null;
         if (mAMapLocationManager != null) {
            mAMapLocationManager.removeUpdates(this);
            mAMapLocationManager.destroy();
            }
         mAMapLocationManager = null;
        }



}
