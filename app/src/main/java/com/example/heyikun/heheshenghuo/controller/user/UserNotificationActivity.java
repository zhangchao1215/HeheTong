package com.example.heyikun.heheshenghuo.controller.user;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ShouYeFragmentAdapter;
import com.example.heyikun.heheshenghuo.controller.user.fragment.UserActivityFragment;
import com.example.heyikun.heheshenghuo.controller.user.fragment.UserNotificationFragment;
import com.example.heyikun.heheshenghuo.controller.user.fragment.UserOrderFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.util.TabUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/10.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/10
 * <p>
 * 3：类描述： 个人中心页面，右上角的通知
 * <p>
 * 4:类功能： viewPager加Fragment
 */

public class UserNotificationActivity extends BaseActivity {


    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.notification_Tab)
    TabLayout notificationTab;
    @BindView(R.id.notification_ViewPager)
    ViewPager notificationViewPager;
    private ShouYeFragmentAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> strList;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_notification;
    }

    @Override
    protected void initView() {
        init();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }

    private void init() {
        fragmentList = new ArrayList<>();
        strList = new ArrayList<>();
        strList.add("通知");
        strList.add("订单");
        strList.add("活动");

        fragmentList.add(new UserNotificationFragment());
        fragmentList.add(new UserOrderFragment());
        fragmentList.add(new UserActivityFragment());

        adapter = new ShouYeFragmentAdapter(getSupportFragmentManager(), strList, fragmentList);

        notificationViewPager.setAdapter(adapter);

        notificationTab.setupWithViewPager(notificationViewPager);
        TabUtils.setIndicator(notificationTab, 20, 20, 10);

    }


    @OnClick(R.id.Image_Back)
    public void onViewClicked() {

        finish();
    }
}
