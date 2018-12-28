package com.example.heyikun.heheshenghuo.controller.shop;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.ShouYeFragmentAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.util.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2018/1/8.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/8
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 商城购买的页面原生态
 */

public class PayDetailActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.ddImage)
    ImageView ddImage;
    @BindView(R.id.Shopping_carImage)
    ImageView ShoppingCarImage;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.pay_detail_shouye)
    TextView payDetailShouye;
    @BindView(R.id.pay_detail_kefu)
    TextView payDetailKefu;
    @BindView(R.id.pay_detail_collect)
    TextView payDetailCollect;
    @BindView(R.id.relative2)
    RelativeLayout relative2;
    @BindView(R.id.shopping_Tab)
    TabLayout shoppingTab;
    @BindView(R.id.shopping_viewpager)
    NoScrollViewPager shoppingViewpager;
    @BindView(R.id.but_car)
    Button butCar;
    @BindView(R.id.but_buy)
    Button butBuy;
    private List<Fragment> fragmentList;
    private List<String> strList;
    private ShouYeFragmentAdapter adapter;

    @Override
    protected int layoutId() {
        return R.layout.activity_pay_detail;
    }

    @Override
    protected void initView() {

        fragmentList = new ArrayList<>();
        strList = new ArrayList<>();

        fragmentList.add(new ShoppingFragment());
        fragmentList.add(new DetailFragment());
        fragmentList.add(new EvaluteFragment());

        strList.add("商品");
        strList.add("详情");
        strList.add("评价");

        adapter = new ShouYeFragmentAdapter(getSupportFragmentManager(), strList, fragmentList);

        shoppingViewpager.setAdapter(adapter);

        shoppingViewpager.setNoScroll(true);

        shoppingTab.setupWithViewPager(shoppingViewpager);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }




    @OnClick({R.id.Image_Back, R.id.ddImage, R.id.Shopping_carImage, R.id.pay_detail_shouye,
            R.id.pay_detail_kefu, R.id.pay_detail_collect, R.id.but_car, R.id.but_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;

            //点点
            case R.id.ddImage:

                break;
            //购物车
            case R.id.Shopping_carImage:


                break;
            //回到首页
            case R.id.pay_detail_shouye:


                break;

            //客服
            case R.id.pay_detail_kefu:


                break;

            //收藏
            case R.id.pay_detail_collect:


                break;

            //加入购物车
            case R.id.but_car:


                break;

            //立即购买
            case R.id.but_buy:


                break;
        }
    }


}
