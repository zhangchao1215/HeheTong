package com.example.heyikun.heheshenghuo.modle.htttp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by hyk on 2017/11/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 用于数据库的缓存操作
 */

public class CacheDao {

    private static volatile CacheDao cacheDao;

    private MyDBHelper helper;
    private SQLiteDatabase database;

    private CacheDao(Context context){
        helper = new MyDBHelper(context.getApplicationContext());
        database = helper.getWritableDatabase();
    }
    public static CacheDao getInstance(Context context) {
        if (cacheDao == null) {
            synchronized (CacheDao.class) {
                if (cacheDao == null) {
                    cacheDao = new CacheDao(context);
                }
            }
        }
        return cacheDao;
    }
    //查
    public String queryResponse(String urlKey, String params) {
        return null;
    }
    //增
    public void insertResponse(String urlKey, String params, String value) {
    }
    //改
    public void updateResponse(String urlKey, String params, String value) {
    }
    //删
    public void deleteResponse(String urlKey, String params) {
    }
}
