package com.example.heyikun.heheshenghuo.controller.user;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ShouYeFragmentAdapter;
import com.example.heyikun.heheshenghuo.controller.user.fragment.YiticketAccountDetailFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.util.TabUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/13
 * <p>
 * 3：类描述： 易券账户的明细
 * <p>
 * 4:类功能：
 */

public class YiticketAccountDetailActivity extends BaseActivity {

    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.Yiticket_Tab)
    TabLayout YiticketTab;
    @BindView(R.id.Yiticket_viewpager)
    ViewPager YiticketViewpager;
    private List<String> strList;

    private List<Fragment> fragmentList;

    private ShouYeFragmentAdapter adapter;


    @Override
    protected int layoutId() {
        return R.layout.activity_ticket_yiaccount_detail;
    }

    @Override
    protected void initView() {
        mTextIncludeTitle.setText("易券明细");

        mTextIncludeTitle.setTextSize(16);
        mTextIncludeTitle.setTextColor(getResources().getColor(R.color.Black));


        fragmentList = new ArrayList<>();

        strList = new ArrayList<>();

        strList.add("01月");
        strList.add("02月");
        strList.add("03月");
        strList.add("04月");
        strList.add("05月");
        strList.add("06月");
        strList.add("07月");
        strList.add("08月");
        strList.add("09月");
        strList.add("10月");
        strList.add("11月");
        strList.add("12月");


        for (int i = 0; i < strList.size(); i++) {
            YiticketAccountDetailFragment fragment = new YiticketAccountDetailFragment();
            fragmentList.add(fragment);


        }

        //设置tabla可以滑动
        YiticketTab.setTabMode(TabLayout.MODE_SCROLLABLE);

        adapter = new ShouYeFragmentAdapter(getSupportFragmentManager(), strList, fragmentList);

        YiticketViewpager.setAdapter(adapter);

        YiticketTab.setupWithViewPager(YiticketViewpager);


        TabUtils.reflex(YiticketTab);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick(R.id.Image_Back)
    public void onViewClicked() {
        finish();

    }
}
