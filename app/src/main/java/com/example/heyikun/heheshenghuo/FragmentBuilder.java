package com.example.heyikun.heheshenghuo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;


/**
 * Created by Administrator on 2017/4/12.
 */

public class FragmentBuilder {

    private FragmentManager manager;
    private FragmentTransaction transaction;
    private BaseFragment fragment;
    private String simpleName;

    private FragmentBuilder() {
        init();
    }

    private static FragmentBuilder config;

    public static FragmentBuilder getInstance() {
        if (config == null) {
            synchronized (FragmentBuilder.class) {
                config = new FragmentBuilder();
            }

        }
        return config;
    }

    public void init() {
        manager = App.activity.getSupportFragmentManager();

    }

    public FragmentBuilder start(Class<? extends BaseFragment> simpleClass) {

        transaction = manager.beginTransaction();
        //得到当前每个Fragment的类名
        simpleName = simpleClass.getSimpleName();
        //用每个Fragment类名作为tag做一个标记,因为每个Fragment都继承BaseFragment
        fragment = (BaseFragment) manager.findFragmentByTag(simpleName);
        if (fragment == null) {
            try {
                //用到Java的动态代理模式，自动创建一个新的Fragment实例
                fragment = simpleClass.newInstance();
                //并把fragment添加到一个容器中
                transaction.add(R.id.Man_frameLayout, fragment, simpleName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        //这是隐藏上一个Fragment
        if (App.lastFragment != null) {
            transaction.hide(App.lastFragment);
        }
        //让fragment进行展示
        transaction.show(fragment);
        //把每个Fragment添加到回退栈进行管理
        transaction.addToBackStack(simpleName);
        return this;
    }

    public BaseFragment build() {
        App.lastFragment = fragment;
        transaction.commit();
        return fragment;
    }


}
