package com.example.heyikun.heheshenghuo.modle.htttp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hyk on 2017/11/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 缓存创建的数据库
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 1;
    static final String CACHE_TABLE = "cache";

    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + CACHE_TABLE +
                " (url text, params text, response text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + CACHE_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

}
