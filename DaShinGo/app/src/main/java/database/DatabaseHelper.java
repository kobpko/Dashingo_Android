package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import database.entity.point;

/**
 * Created by MSI on 2015/11/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DataBaseVersion= 1;
    private static final String DataBaseName= "test1.db";
    public DatabaseHelper(Context context) {

        super(context, DataBaseName, null, DataBaseVersion);
    }
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, null, version);
    }
    public void savepoint(point point)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("address", point.getAddress());
        db.insert("point1", null, values);

    }
    public point findpoint(Integer num) {
        SQLiteDatabase db = this.getReadableDatabase();
        /**
         * 1:表名
         * 2:查找表列名的组合 是一个字符串数组 如果为null的话 则表示全部
         * 3:查询的条件,可用占位符
         * 4:是参数3中占位符的值
         * 5:分组依据
         * 6:分组筛选语句
         * 7:排序语句
         */
        Cursor cursor = db.rawQuery("select * from point1",null);
        // 上面语句等价于 Cursor cursor =
        // db.rawQuery("select * from person where id=?", new
        // String[] { personId.toString() });
        if (cursor.moveToFirst()) {
            point point = new point();
            point.setNum(cursor.getInt(cursor.getColumnIndex("num")));
            point.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            return point;
        }
        return null;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String user_info = "CREATE TABLE IF NOT EXISTS point1(num INTEGER PRIMARY KEY,address text)";
        sqLiteDatabase.execSQL(user_info);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
    @Override
    public void onOpen(SQLiteDatabase db)
    {
        super.onOpen(db);
    }





}
