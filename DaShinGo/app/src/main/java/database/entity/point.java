package database.entity;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.amap.api.location.AMapLocation;

import database.DatabaseHelper;

/**
 * Created by MSI on 2015/11/16.
 */
public class point {
    private int num;
    private int traceId;                   //轨迹编号
//    private AMapLocation aMapLocation;     //点的信息
    private double latitude;
    private double longitude;
    private String address;                 //地址
    private String userId;                  //创造者Id
    private Boolean isEnd;
    public point()
    {

    }
    public point(int _num)
    {
      num = _num;
    }
    public point(AMapLocation aMapLocation,String _userId,int _traceId,Boolean _isEnd,int _num)
    {
     longitude=aMapLocation.getLongitude();
     latitude=aMapLocation.getLatitude();
     address=aMapLocation.getAddress();
     userId = _userId;
     traceId= _traceId;
     isEnd  = _isEnd;
     num = _num;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getTraceId() {
        return traceId;
    }

    public void setTraceId(int traceId) {
        this.traceId = traceId;
    }

//    public AMapLocation getaMapLocation() {
//        return aMapLocation;
//    }
//
//    public void setaMapLocation(AMapLocation aMapLocation) {
//        this.aMapLocation = aMapLocation;
//    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getlongitude() {
        return longitude;
    }

    public void setlongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getIsEnd() {
        return isEnd;
    }

    public void setIsEnd(Boolean isEnd) {
        this.isEnd = isEnd;
    }



    //    private String Country;
//    private String City;
//    private String Road;
//    private String Province;




}
