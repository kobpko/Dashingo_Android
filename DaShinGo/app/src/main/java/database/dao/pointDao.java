package database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.amap.api.location.AMapLocation;

import database.DatabaseHelper;
import database.entity.point;

/**
 * Created by MSI on 2015/11/16.
 */
public class pointDao extends point {

    public pointDao(AMapLocation aMapLocation, String _userId, int _traceId, Boolean _isEnd, int _num) {
        super(aMapLocation, _userId, _traceId, _isEnd, _num);
    }
    DatabaseHelper databaseHelper;



}

