package com.example.heyikun.heheshenghuo.controller.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.HelpAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.HelpBean;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 项目名称:
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/5 19:50
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */

public class UserHeheHelpActivity extends BaseActivity {

    @BindView(R.id.help_recycler)
    RecyclerView helpRecycler;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    private HelpAdapter adapter;
    private List<HelpBean.DataBean> dataBeanList;

    @Override
    protected int layoutId() {
        return R.layout.user_hehe_userhelp;
    }

    @Override
    protected void initView() {
        dataBeanList = new ArrayList<>();
        ImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Map<String, String> params = new HashMap<>();
        params.put("action", "UseHelp");
        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<HelpBean>() {
                    @Override
                    public void onSuccess(int statusCode, HelpBean response) {

                        if (response.getStatus().equals("200")) {

                            LinearLayoutManager manager = new LinearLayoutManager(UserHeheHelpActivity.this);
                            helpRecycler.setLayoutManager(manager);

                            dataBeanList.addAll(response.getData());

                            adapter = new HelpAdapter(dataBeanList, UserHeheHelpActivity.this);
                            helpRecycler.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


}
