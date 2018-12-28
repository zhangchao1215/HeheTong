package com.example.heyikun.heheshenghuo.controller.user;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ShouYeFragmentAdapter;
import com.example.heyikun.heheshenghuo.controller.user.fragment.UserManageMoneyFragment;
import com.example.heyikun.heheshenghuo.controller.user.fragment.UserMentalityFragment;
import com.example.heyikun.heheshenghuo.controller.user.fragment.UserTiZhiFragment;
import com.example.heyikun.heheshenghuo.controller.user.fragment.UserVIPFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

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
 * 3：类描述：  体质总的
 * <p>
 * 4:类功能： TabLayout + viewpager + fragment
 */

public class UserTizhiActivity extends BaseActivity {
    @BindView(R.id.TiZhi_Tab)
    TabLayout TiZhiTab;
    @BindView(R.id.TiZhi_viewpager)
    ViewPager TiZhiViewpager;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    private ShouYeFragmentAdapter adapter;
    private List<Fragment> fragmentList;
    private List<String> strList;


    @Override
    protected int layoutId() {
        return R.layout.activity_user_tizhi;
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

        strList.add("体质");
        strList.add("心里");
        strList.add("理财");
        strList.add("VIP");

        fragmentList.add(new UserTiZhiFragment());
        fragmentList.add(new UserMentalityFragment());
        fragmentList.add(new UserManageMoneyFragment());
        fragmentList.add(new UserVIPFragment());


        adapter = new ShouYeFragmentAdapter
                 (getSupportFragmentManager(), strList, fragmentList);
        TiZhiViewpager.setAdapter(adapter);

        TiZhiTab.setupWithViewPager(TiZhiViewpager);

    }


    @OnClick(R.id.Image_Back)
    public void onViewClicked() {
        finish();

    }
}
