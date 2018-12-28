package com.example.heyikun.heheshenghuo;

import android.app.Activity;
import android.app.Application;

import com.example.heyikun.heheshenghuo.greendao.DaoSession;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tsy.sdk.myokhttp.MyOkHttp;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by hyk on 2017/9/11.
 */

public class App extends Application {
    private List activities = new ArrayList();
    private List<Activity> mList = new ArrayList<>();
    public static BaseActivity activity;

    public static BaseFragment lastFragment;
    private static DaoSession daoSession;

    public static IWXAPI api;
    //    分享需要配置的东西

    public static MyOkHttp myOkHttp = new MyOkHttp();


    {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //        setupDatabase();

//        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        MyOkHttp.context = getApplicationContext();

        api = WXAPIFactory.createWXAPI(this,Constants.APP_ID);


    }


    //    private void setupDatabase() {
    //        //        创建数据库News.db
    //        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "myDb", null);
    //        //获取数据库操作.权限
    //        SQLiteDatabase db = helper.getWritableDatabase();
    //        //获取数据库对象
    //        DaoMaster daoMaster = new DaoMaster(db);
    //        //        获取Dao对象管理者
    //        daoSession = daoMaster.newSession();
    //    }
    //
    //    public static DaoSession getDaoInstant() {
    //        return daoSession;
    //    }

}
