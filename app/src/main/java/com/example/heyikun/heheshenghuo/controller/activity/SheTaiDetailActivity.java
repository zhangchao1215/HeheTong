package com.example.heyikun.heheshenghuo.controller.activity;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.SheTai_ViewPagerAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.bean.PopuoBean;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBShetaiImageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by hyk on 2017/9/15.
 */

public class SheTaiDetailActivity extends BaseActivity {

    @BindView(R.id.Detail_SheTai_ImageDetail_Back)
    ImageView DetailBack;
    @BindView(R.id.Detail_SheTai_mText)
    TextView DetailmText;
    @BindView(R.id.Detail_SheTai_TitleRelative)
    RelativeLayout DetailSheTaiTitleRelative;
    @BindView(R.id.Detail_SheTai_ViewPager)
    ViewPager DetailViewPager;
    @BindView(R.id.Detail_HuaDong_Text)
    TextView DetailHuaDongText;
    private SheTai_ViewPagerAdapter adapter;

    private List<View> ViewsList;

    private ImageView mImage;

    private int position;
    private List<PopuoBean.DataBean> dataBeen;

    @Override
    protected int layoutId() {
        return R.layout.activity_shedai_imagedetail;
    }

    @Override
    protected void initView() {


        EventBus.getDefault().register(this);

        ViewsList = new ArrayList<>();


        for (int i = 0; i < dataBeen.size(); i++) {
            View iv1 = LayoutInflater.from(this).inflate(R.layout.activity_shetai_viewpager_item, null);

            mImage = (ImageView) iv1.findViewById(R.id.SheTai_ViewPager_itemImage);
            Glide.with(this)
                    .load(dataBeen.get(i).getImg())
                    .centerCrop()
                    .placeholder(R.drawable.jcwz)
                    .into(mImage);

            ViewsList.add(iv1);
        }
        adapter = new SheTai_ViewPagerAdapter(ViewsList);
        DetailViewPager.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        DetailViewPager.setCurrentItem(position);

        //这是选择舌苔页面---------------选择图片viewpager


        Log.d("SheTaiDetailActivity", "adapter:" + adapter);
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void MainThreadData(EBShetaiImageBean bean) {

        dataBeen = bean.getDataBeen();

        position = bean.getPosition();
    }


    private int flag = 0;

    @Override
    protected void initLisenter() {
        DetailViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                DetailmText.setText(position + 1 + "/" + dataBeen.size());

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        mImage.setOnClickListener(new View.OnClickListener() {
            //Imageview两次点击事件进行切换

            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    //这是选中状态
                    mImage.setBackgroundResource(R.drawable.xuanzhongtupian_4man);
                    DetailHuaDongText.setText("选定");
                    DetailHuaDongText.setBackgroundResource(R.color.colorPrimary);

                    flag = 1;
                } else {
                    mImage.setBackgroundResource(R.drawable.meixuanzhongtupian4x);
                    DetailHuaDongText.setText("请选择你的舌苔");
                    DetailHuaDongText.setBackgroundResource(R.color.colorViewPagerText);
                    flag = 0;
                }


            }
        });
        DetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
