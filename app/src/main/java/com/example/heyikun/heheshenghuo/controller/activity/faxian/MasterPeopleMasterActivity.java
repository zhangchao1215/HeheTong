package com.example.heyikun.heheshenghuo.controller.activity.faxian;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.MasterBigcastAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.MasterFansAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.MastermasterAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.bean.MasterDetailBean;
import com.example.heyikun.heheshenghuo.modle.eventbus.MasterEvBean;
import com.example.heyikun.heheshenghuo.modle.util.DividerItemDecorationRecy;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hyk on 2017/12/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/14
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 养生达人个人主页里面的关注人物列表，粉丝等
 */

public class MasterPeopleMasterActivity extends BaseActivity {
    @BindView(R.id.master_people_recycler)
    RecyclerView masterPeopleRecycler;
    @BindView(R.id.master_text)
    TextView masterText;
    private List<MasterDetailBean.DataBean.BigcastsBean> bigcastsBeanList;//关注的大咖信息
    private List<MasterDetailBean.DataBean.FansBean> fansBeanList;
    private List<MasterDetailBean.DataBean.MasterBean> masterBeanList;//关注的达人信息
    private String master;
    private MasterBigcastAdapter bigcastAdapter;
    private MastermasterAdapter masterAdapter;
    private MasterFansAdapter fansAdapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_master_people_guanzhu;
    }

    @Override
    protected void initView() {

        EventBus.getDefault().register(this);


        masterText.setText("达人列表");
        masterBeanList = new ArrayList<>();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }

    @Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
    public void masterEventMaster(MasterEvBean bean) {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        masterPeopleRecycler.addItemDecoration(new DividerItemDecorationRecy(this, DividerItemDecorationRecy.VERTICAL_LIST));

        masterPeopleRecycler.setLayoutManager(manager);
        List<MasterDetailBean.DataBean.MasterBean> mmasterBeanList = bean.getMasterBeanList();

        masterBeanList.addAll(mmasterBeanList);
        masterAdapter = new MastermasterAdapter(masterBeanList, this);
        masterPeopleRecycler.setAdapter(masterAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
