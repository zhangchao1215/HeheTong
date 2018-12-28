package com.example.heyikun.heheshenghuo.controller.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.PopupSheTaiAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.PopuoBean;
import com.example.heyikun.heheshenghuo.modle.bean.Second_RecyclerBean;
import com.example.heyikun.heheshenghuo.modle.bean.SheTaiSuccessBean;
import com.example.heyikun.heheshenghuo.modle.bean.TypeIdBean;
import com.example.heyikun.heheshenghuo.modle.bean.ZhenDuanTitleBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.ScreenUtils;
import com.example.heyikun.heheshenghuo.modle.util.TabUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loction.choose.flowchooselibrary.listener.CustomDataListener;
import com.loction.choose.flowchooselibrary.listener.OnChooseItemClick;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.R.id.Self_mLinearDongtaiSecond;
import static com.example.heyikun.heheshenghuo.R.id.mRecyclerView;

/**
 * Created by hyk on 2017/9/12.
 * <p>
 * 返回身体的各个部位进行诊断
 * 1：点击每个部位返回对应的并症，一共有三层进行展示选择，最后进行选择舌苔，
 * 返回一些对应的文章
 */

public class
ZhenDuanActivity extends BaseActivity implements View.OnClickListener, PopupSheTaiAdapter.SheTaiItemClickListener {


    @BindView(R.id.Self_Diagnose_Back)
    TextView SelfDiagnoseBack;
    @BindView(R.id.Self_TitleTabLayout)
    TabLayout SelfTitleTab;
    @BindView(R.id.Self_ManImage)
    ImageView SelfManImage;
    @BindView(R.id.Self_Relative_PersonImage)
    RelativeLayout SelfRelativePersonImage;
    @BindView(R.id.Self_XiongQiang)
    ImageView SelfXiongQiang;
    @BindView(R.id.Self_TextYinhuan)
    TextView SelfTextYinhuan;
    @BindView(R.id.Self_TextKesou)
    TextView SelfTextKesou;
    @BindView(R.id.Self_LookDetail_but)
    Button SelfLookDetailBut;
    @BindView(R.id.Self_YinHuan_Relative)
    RelativeLayout SelfYinHuanRelative;
    @BindView(R.id.XQ_ImageKuang)
    ImageView XQImageKuang;
    @BindView(R.id.ZhenDuan_TextTitle)
    TextView ZhenDuanTextTitle;
    @BindView(R.id.Self_RadioChaHao)
    RadioButton SelfRadioChaHao;
    @BindView(R.id.Self_mLinearZhengZhuang)
    RelativeLayout SelfMLinearZhengZhuang;
    @BindView(R.id.Self_SelectorText)
    TextView SelfSelectorText;
    @BindView(R.id.ZhenDuan_JieGuoText)
    LinearLayout ZhenDuanJieGuoText;
    @BindView(R.id.Self_ImageNext)
    ImageView SelfImageNext;
    @BindView(R.id.mTextNext)
    TextView mTextNext;
    @BindView(R.id.Self_Relative_Kuang)
    RelativeLayout SelfRelativeKuang;
    @BindView(R.id.mLinear_Popup)
    LinearLayout mLinearPopup;
    @BindView(R.id.Self_mLinearDongtaiSecond)
    RadioButton SelfMLinearDongtaiSecond;
    @BindView(R.id.mText_XiongFu)
    TextView mTextXiongFu;
    @BindView(R.id.JieGuo_Title)
    TextView JieGuoTitle;
    @BindView(R.id.JieGuo_ZhiLiaoText)
    TextView JieGuoZhiLiaoText;

    @BindView(R.id.mScrollview)
    ScrollView mScrollview;
    @BindView(R.id.Self_ManSaomiaoImage)
    ImageView SelfManSaomiaoImage;


    @BindView(R.id.Result_Link)
    RelativeLayout ResultLink;
    @BindView(R.id.zhenduan_descText)
    TextView zhenduanDescText;
    @BindView(R.id.zhenduan_descScroll)
    ScrollView zhenduanDescScroll;
    private int screenWidth;
    private int widthX;
    private float translationX;
    private PopupSheTaiAdapter PopwindowAdapter;
    private List<PopuoBean.DataBean> BeanList;
    private RecyclerView mRecycler;
    private ImageView mPopBackImage;
    private int flag = 0;
    private PopupWindow popupWindow;
    private int tabposition;
    private RelativeLayout mRelative;
    private List<String> mSecondAddList = new ArrayList<>();
    private SheTaiSuccessBean successBean;
    private List<TypeIdBean.DataBean> data1;

    private String firstdiag_id;

    private TextView FirsttextView;
    private String secondDiag_id;
    private String secondTwodiag_id;
    private String sheTaiDiagId;

    private ImageView SaoMiaoImage;
    private List<Second_RecyclerBean.DataBean> secondBean;
    private String seconddiag_name;
    private int finailI;
    private List<TextView> textViewList;
    private int SecondType;

    @Override
    protected int layoutId() {
        return R.layout.activity_shouye_self_diagnose;
    }

    @Override
    protected void initView() {

        SelfImageNext.setClickable(false);

        SaoMiaoImage = (ImageView) findViewById(R.id.Self_ManSaomiaoImage);


        initAll();

        ImageAnimator();
        //开启一个EventBus

        //这是给人形图设置动画,工具类，得到整体屏幕的宽高
        screenWidth = ScreenUtils.getScreenWidth(this);

        translationX = SelfRelativePersonImage.getTranslationX();


        widthX = (screenWidth / 3) + 100;

    }

    //动画一直扫描
    private void ImageAnimator() {
        float translationY = SaoMiaoImage.getTranslationY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(SaoMiaoImage, "TranslationY", translationY, 800f, translationY);
        animator.setDuration(5000).setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();

    }


    /**
     * 初始化组件
     */
    private void initAll() {
        textViewList = new ArrayList<>();
        BeanList = new ArrayList<>();

    }


    // TODO: 2017/9/17 initData 加载数据的

    /**
     * 第一次请求病症
     */
    @Override
    protected void initData() {
        //第一层Tablayout上面的数据
        getJianceTitle();


    }


    /**
     * 第一次请求病症,标题TabLayout
     */
    private void getJianceTitle() {
        Map<String, String> map = new HashMap<>();

        map.put("action", "Diagnosis");

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, map, "", new MyCallBack() {

            @Override
            public void onSuccess(final String data) {
                Gson gson = new Gson();
                Log.d("ZhenDuanActivity", data);

                ZhenDuanTitleBean titleBean = gson.fromJson(data, ZhenDuanTitleBean.class);

                if (titleBean == null) {
                    return;
                }
                final List<ZhenDuanTitleBean.DataBean> mData = titleBean.getData();

                SelfTitleTab.setTabMode(TabLayout.MODE_FIXED);
                ZhenDuanTextTitle.setText(mData.get(0).getTypename());


                TabUtils.setIndicator(SelfTitleTab, 8, 8, 8);
                for (int i = 0; i < mData.size(); i++) {
                    SelfTitleTab.addTab(SelfTitleTab.newTab().setText(mData.get(i).getTypename()));
                    Log.d("ZhenDuanActivity", "得到的数据   " + mData.get(i).getDesc());

                    SelfTextKesou.setText(mData.get(i).getDesc());
                    //                    desc = mData.get(i).getDesc();


                }
                mTextXiongFu.setText(mData.get(0).getTypename());
                Glide.with(ZhenDuanActivity.this)
                        .load(mData.get(0).getPic_url())
                        .placeholder(R.drawable.zhenduan_default)
                        .into(SelfManImage);
                //                            SelfManImage.setImageResource(R.drawable.toujing_man_4x);

                SelfTextKesou.setText(mData.get(0).getDesc());
                ZhenDuanTextTitle.setText(mData.get(0).getTypename());


                //TabLayout的点击事件
                SelfTitleTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {


                        //这是给人形图设置动画,工具类，得到整体屏幕的宽高

                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(SelfRelativePersonImage, "translationX", translationX, 0);
                        objectAnimator.start();


                        tabposition = tab.getPosition() + 1;


                        Log.d("ZhenDuanActivity", "循环中的typid" + mData.get(finailI).getTypeid());


                        //分别加载三层数据

                        //                        SecondRecyclerData(firstdiag_id, "1");
                        //                        SecondRecyclerDataTwo(firstdiag_id, "2");

                        ZhenDuanJieGuoText.setVisibility(View.GONE);
                        if (tabposition == 1) {
                            tab.select();
                            mTextXiongFu.setText(mData.get(0).getTypename());
                            Glide.with(ZhenDuanActivity.this)
                                    .load(mData.get(0).getPic_url())
                                    .placeholder(R.drawable.zhenduan_default)
                                    .into(SelfManImage);
                            //                            SelfManImage.setImageResource(R.drawable.toujing_man_4x);

                            SelfTextKesou.setText(mData.get(0).getDesc());

                            SelfMLinearDongtaiSecond.setVisibility(View.GONE);
                            SelfRadioChaHao.setVisibility(View.GONE);
                            ZhenDuanJieGuoText.setVisibility(View.GONE);
                            SelfSelectorText.setText("请选择您的症状：");
                            ZhenDuanTextTitle.setText(mData.get(0).getTypename());

                        } else if (tabposition == 2) {
                            Glide.with(ZhenDuanActivity.this)
                                    .load(mData.get(1).getPic_url())
                                    .placeholder(R.drawable.zhenduan_default)
                                    .into(SelfManImage);
                            //                            SelfManImage.setImageResource(R.drawable.yaobeisizhi_man_4x);
                            mTextXiongFu.setText(mData.get(1).getTypename());
                            SelfRadioChaHao.setVisibility(View.GONE);
                            ZhenDuanJieGuoText.setVisibility(View.GONE);
                            SelfMLinearDongtaiSecond.setVisibility(View.GONE);
                            SelfSelectorText.setText("请选择您的症状：");
                            SelfTextKesou.setText(mData.get(1).getDesc());
                            ZhenDuanTextTitle.setText(mData.get(1).getTypename());
                        } else if (tabposition == 3) {
                            Glide.with(ZhenDuanActivity.this)
                                    .load(mData.get(2).getPic_url())
                                    .placeholder(R.drawable.zhenduan_default)
                                    .into(SelfManImage);
                            //                            SelfManImage.setImageResource(R.drawable.shengzhiminiao_man_4x);
                            mTextXiongFu.setText(mData.get(2).getTypename());
                            SelfRadioChaHao.setVisibility(View.GONE);
                            ZhenDuanJieGuoText.setVisibility(View.GONE);
                            SelfMLinearDongtaiSecond.setVisibility(View.GONE);
                            SelfSelectorText.setText("请选择您的症状：");
                            SelfTextKesou.setText(mData.get(2).getDesc());
                            ZhenDuanTextTitle.setText(mData.get(2).getTypename());
                        } else if (tabposition == 4) {
                            Glide.with(ZhenDuanActivity.this)
                                    .load(mData.get(3).getPic_url())
                                    .placeholder(R.drawable.zhenduan_default)
                                    .into(SelfManImage);
                            //                            SelfManImage.setImageResource(R.drawable.quanshen_man_4x);
                            mTextXiongFu.setText(mData.get(3).getTypename());
                            SelfRadioChaHao.setVisibility(View.GONE);
                            ZhenDuanJieGuoText.setVisibility(View.GONE);
                            SelfMLinearDongtaiSecond.setVisibility(View.GONE);
                            SelfSelectorText.setText("请选择您的症状：");
                            SelfTextKesou.setText(mData.get(3).getDesc());
                            ZhenDuanTextTitle.setText(mData.get(3).getTypename());

                        } else if (tabposition == 5) {
                            Glide.with(ZhenDuanActivity.this)
                                    .load(mData.get(4).getPic_url())
                                    .placeholder(R.drawable.zhenduan_default)
                                    .into(SelfManImage);

                            mTextXiongFu.setText(mData.get(4).getTypename());
                            SelfRadioChaHao.setVisibility(View.GONE);
                            ZhenDuanJieGuoText.setVisibility(View.GONE);
                            SelfMLinearDongtaiSecond.setVisibility(View.GONE);
                            SelfSelectorText.setText("请选择您的症状：");
                            SelfTextKesou.setText(mData.get(4).getDesc());
                            ZhenDuanTextTitle.setText(mData.get(4).getTypename());

                        }


                        SelfYinHuanRelative.setVisibility(View.VISIBLE);

                        SelfRelativeKuang.setVisibility(View.GONE);



                        //再次点击Tab的时候 flag重新变化为 0,重新加载第一个请求病症
                        flag = 0;
                        //点击布局中，消除数据，重新加载新的数据


                    }


                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }

                });

            }

            @Override
            public void onError(String error) {

            }
        });
        //这是自定义下划线的宽度和与字体之间的距离

        //自定义Tablayout的点击事件


    }


    // TODO: 2017/9/17 这是第一次诊断的数据 病症

    /**
     * 第一次诊断请求数据
     *
     * @param typeid 上面每个tab的id，进行病症的显示
     */
//
//    private void FIrsZhenDuan(final int typeid) {
//        SelfImageNext.setClickable(false);
//        FirstRecyclerView.removeAllViews();
//        Map<String, String> params = new HashMap<>();
//        params.put("action", "Diagnosis");
//        params.put("typeid", String.valueOf(typeid));
//
//        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
//
//            private TextView lastClickView;
//
//            @Override
//            public void onSuccess(String data) {
//
//                Log.d("ZhenDuanActivity", "第一次加载的数据" + data);
//                Log.d("ZhenDuanActivity", "typeid:" + typeid);
//
//
//                Gson gson = new Gson();
//                TypeIdBean typeIdBean = gson.fromJson(data, TypeIdBean.class);
//
//                data1 = typeIdBean.getData();
//
//                if (typeIdBean == null || data1 == null)
//                    return;
//                if (!typeIdBean.getStatus().equals("200")) {
//                    return;
//                }
//                SelfRadioChaHao.setText("");
//
//                FirstRecyclerView.setList(data1, new CustomDataListener<TypeIdBean.DataBean>() {
//                    @Override
//                    public String setListItemData(TypeIdBean.DataBean dataBean) {
//
//
//                        return dataBean.getDiag_name();
//                    }
//                });
//
//                FirstRecyclerView.setMaxRows(100);
//                FirstRecyclerView.setSecond(true);
//
//                FirstRecyclerView.setOnChooseItemClick(new OnChooseItemClick() {
//                    @Override
//                    public void onItemDataListener(int position, View view, int type) {
//
//                        Log.d("ZhenDuanActivity", "type:" + type);
//                        if (type == 2) {
//
//                            SelfImageNext.setClickable(true);
//                            mTextNext.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//
//                        } else if (type == 1) {
//                            SelfImageNext.setClickable(false);
//                            mTextNext.setTextColor(getResources().getColor(R.color.colorNoClick));
//                        }
//
//                        TypeIdBean.DataBean dataBean = data1.get(position);
//
//                        SelfRadioChaHao.setText(dataBean.getDiag_name());
//                        Log.d("ZhenDuanActivity", dataBean.getDiag_name());
//
//                        firstdiag_id = dataBean.getDiag_id();
//
//
//                    }
//                });
//
//                //                textViewList.clear();
//                //                for (int i = 0; i < data1.size(); i++) {
//                //
//                //                    FirsttextView = new TextView(ZhenDuanActivity.this);
//                //                    textViewList.add(FirsttextView);
//                //
//                //
//                //                    FirsttextView.setText(data1.get(i).getDiag_name());
//                //                    FirsttextView.setGravity(Gravity.CENTER);
//                //
//                //                    FirsttextView.setTextSize(12);
//                //
//                //                    FirsttextView.setGravity(Gravity.CENTER);
//                //
//                //
//                //                    FirsttextView.setBackgroundResource(R.drawable.tv_item_selector);
//                //
//                //
//                //                    FirsttextView.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//                //
//                //
//                //                    FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                //                    layoutParams.setMargins(8, 5, 8, 5);
//                //
//                //                    FirsttextView.setLayoutParams(layoutParams);
//                //
//                //                    final int Finali = i;
//                //
//                //
//                //                    //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
//                //
//                //
//                //                    FirsttextView.setOnClickListener(new View.OnClickListener() {
//                //
//                //
//                //                        @Override
//                //                        public void onClick(View v) {
//                //
//                //
//                //                            int flag = Finali;
//                //
//                //                            Log.d("ZhenDuanActivity", "flag:      " + flag);
//                //                            for (int i1 = 0; i1 < textViewList.size(); i1++) {
//                //                                TextView textView = textViewList.get(i1);
//                //                                Log.d("ZhenDuanActivity", "i1:遍历的    " + i1);
//                //                                if (i1 == flag) {
//                //                                    SelfImageNext.setClickable(true);
//                //                                    mTextNext.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//                //
//                //                                    Log.d("ZhenDuanActivity", "i1:       " + textView.getText().toString());
//                //                                    textView.setBackgroundResource(R.drawable.self_zhengzhuang_shape_true);
//                //                                    textView.setTextColor(getResources().getColor(R.color.white));
//                //                                    FirsttextView.setBackgroundResource(R.drawable.self_zhengzhuang_shape_true);
//                //                                    Log.d("ZhenDuanActivity", "得到的字段ID+++++   " + data1.get(i1).getDiag_id());
//                //
//                //                                    firstdiag_id = data1.get(i1).getDiag_id();
//                //
//                //                                    SelfRadioChaHao.setText(data1.get(i1).getDiag_name());
//                //
//                //
//                //                                } else {
//                //                                    textView.setBackgroundResource(R.drawable.self_zhengzhuang_shape_false);
//                //                                    textView.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//                //                                }
//                //
//                //
//                //                            }
//                //
//                //                            FirsttextView.setBackgroundResource(R.drawable.self_zhengzhuang_shape_false);
//                //
//                //
//                //                        }
//                //                    });
//                //
//                //
//                //                    FirstRecyclerView.addView(FirsttextView); //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
//                //
//                //
//                //                }
//
//
//            }
//
//            //
//            @Override
//            public void onError(String error) {
//
//            }
//        });
//    }
//
//
//    /**
//     * 第二次请求，主症
//     */
//    // TODO: 2017/10/16  第二次请求的数据
//    private void SecondRecyclerData(String diag_id, String disease_id) {
//        //判断第一次进来时不可点击，同时把数据进行清空，在点击上面选项时，下面才可进行下一步
//        SelfImageNext.setClickable(false);
//
//        mTextNext.setTextColor(getResources().getColor(R.color.colorNoClick));
//
//        SecondRecyclerView.removeAllViews();
//
//        Map<String, String> params = new HashMap<>();
//        params.put("action", "Diagnosis");
//        params.put("typeid", disease_id);
//        params.put("diag_id", diag_id);
//        params.put("disease_id", "1");
//
//        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
//            @Override
//            public void onSuccess(String data) {
//                Log.d("ZhenDuanActivity主症Two", data);
//                Gson gson = new Gson();
//
//                Second_RecyclerBean bean = gson.fromJson(data, Second_RecyclerBean.class);
//
//                if (bean == null)
//                    return;
//                if (!bean.getStatus().equals("200")) {
//                    return;
//                }
//
//                final List<Second_RecyclerBean.DataBean> beanList = bean.getData();
//
//                SecondRecyclerView.setMaxRows(100);
//                SecondRecyclerView.setList(beanList, new CustomDataListener<Second_RecyclerBean.DataBean>() {
//                    @Override
//                    public String setListItemData(Second_RecyclerBean.DataBean dataBean) {
//
//                        return dataBean.getDiag_name();
//                    }
//                });
//
//                SecondRecyclerView.setOnChooseItemClick(new OnChooseItemClick() {
//                    @Override
//                    public void onItemDataListener(int position, View view, int type) {
//
//                        List<Integer> allCheckedIndex = SecondRecyclerView.getAllCheckedIndex();
//                        Log.d("ZhenDuanActivity", "allCheckedIndex.size():" + allCheckedIndex.size());
//                        if (allCheckedIndex.size() == 0) {
//
//                            SelfImageNext.setClickable(false);
//                            mTextNext.setTextColor(getResources().getColor(R.color.colorNoClick));
//                        } else {
//
//                            SelfImageNext.setClickable(true);
//                            mTextNext.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//                        }
//
//
//                        Second_RecyclerBean.DataBean dataBean = beanList.get(position);
//
//                        SelfMLinearDongtaiSecond.setText(dataBean.getDiag_name());
//
//                        SecondType = type - 1;
//                        secondDiag_id = dataBean.getDiag_id();
//
//                        Log.d("ZhenDuanActivity", "选中的数据+++" + dataBean.getDiag_name());
//
//
//                        List<Second_RecyclerBean.DataBean> allCheckData = SecondRecyclerView.getAllCheckData(Second_RecyclerBean.DataBean.class);
//
//
//                        for (Second_RecyclerBean.DataBean dataBean1 : allCheckData) {
//                            Log.d("ZhenDuanActivity", "dataBean1:" + dataBean1.getDiag_name());
//                        }
//                        mSecondAddList.add(dataBean.getDiag_name());
//                        //                        AddSecondLinear(mSecondAddList);
//
//                        Log.d("ZhenDuanActivity", "type:点击次数" + type);
//
//                    }
//                });
//
//                SecondRecyclerView.setButtonThreeTextColor(getResources().getColor(R.color.red));
//                SecondRecyclerView.setTypeThreeBack(getResources().getColor(R.color.colorTextYangXIn));
//                //                SecondRecyclerView.setTypeThreeBorad(getResources().getColor(R.color.colorTextYangXIn));
//
//                SecondRecyclerView.setSecond(false);
//
//
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });
//
//        //添加第二层选中的数据
//    }
//
//    private int CheckTwoFlag;
//
//    //第三次数据的请求，第三个recyclerView
//    private void SecondRecyclerDataTwo(String id, String disease_id) {
//        SelfImageNext.setClickable(false);
//        mTextNext.setTextColor(getResources().getColor(R.color.colorNoClick));
//        ThreeRecyclerView.removeAllViews();
//        Map<String, String> params = new HashMap<>();
//        params.put("action", "Diagnosis");
//        params.put("typeid", "2");
//        params.put("diag_id", id);
//        params.put("disease_id", "2");
//
//        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
//            @Override
//            public void onSuccess(String data) {
//                Log.d("ZhenDuanActivity主症THree", data);
//                Gson gson = new Gson();
//
//                Second_RecyclerBean bean = gson.fromJson(data, Second_RecyclerBean.class);
//
//                if (bean == null)
//                    return;
//                if (!bean.getStatus().equals("200")) {
//                    return;
//                }
//
//                secondBean = bean.getData();
//                ThreeRecyclerView.setMaxRows(100);
//                ThreeRecyclerView.setSecond(false);
//
//                ThreeRecyclerView.setButtonThreeTextColor(getResources().getColor(R.color.red));
//                ThreeRecyclerView.setTypeThreeBack(getResources().getColor(R.color.colorTextYangXIn));
//                ThreeRecyclerView.setList(secondBean, new CustomDataListener<Second_RecyclerBean.DataBean>() {
//                    @Override
//                    public String setListItemData(Second_RecyclerBean.DataBean dataBean) {
//                        return dataBean.getDiag_name();
//                    }
//                });
//
//                ThreeRecyclerView.setOnChooseItemClick(new OnChooseItemClick() {
//                    @Override
//                    public void onItemDataListener(int position, View view, int type) {
//                        List<Integer> allCheckedIndex = ThreeRecyclerView.getAllCheckedIndex();
//
//                        Second_RecyclerBean.DataBean dataBean = secondBean.get(position);
//                        if (allCheckedIndex.size() == 0) {
//                            SelfImageNext.setClickable(false);
//                            mTextNext.setTextColor(getResources().getColor(R.color.colorNoClick));
//                        } else {
//
//                            SelfImageNext.setClickable(true);
//                            mTextNext.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//
//                            secondTwodiag_id = dataBean.getDiag_id();
//                        }
//
//
//                    }
//                });
//
//
//                //
//                //                for (int i = 0; i < secondBean.size(); i++) {
//                //
//                //                    final RadioButton checkBox = new RadioButton(ZhenDuanActivity.this);
//                //
//                //                    checkBox.setGravity(Gravity.CENTER);
//                //
//                //                    checkBox.setButtonDrawable(null);
//                //
//                //                    checkBox.setText(secondBean.get(i).getDiag_name());
//                //
//                //                    checkBox.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//                //
//                //                    checkBox.setBackgroundResource(R.drawable.tv_item_selector);
//                //
//                //                    checkBox.setTextSize(12);
//                //
//                //                    FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                //                    layoutParams.setMargins(8, 5, 8, 5);
//                //
//                //                    checkBox.setLayoutParams(layoutParams);
//                //
//                //
//                //                    final int Fianli = i;
//                //
//                //                    checkBox.setOnClickListener(new View.OnClickListener() {
//                //                        @Override
//                //                        public void onClick(View v) {
//                //
//                //                            if (CheckTwoFlag == 0) {
//                //                                checkBox.setTextColor(getResources().getColor(R.color.white));
//                //                                SelfImageNext.setClickable(true);
//                //                                mTextNext.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//                //                                CheckTwoFlag = 1;
//                //                            } else if (CheckTwoFlag == 1) {
//                //                                checkBox.setTextColor(getResources().getColor(R.color.red));
//                //                                SelfImageNext.setClickable(true);
//                //
//                //                                CheckTwoFlag = 2;
//                //                            } else if (CheckTwoFlag == 2) {
//                //                                checkBox.setChecked(false);
//                //                                SelfImageNext.setClickable(false);
//                //                                checkBox.setTextColor(getResources().getColor(R.color.ZhenDuanText));
//                //                                mTextNext.setTextColor(getResources().getColor(R.color.colorNoClick));
//                //
//                //                                CheckTwoFlag = 0;
//                //                            }
//                //
//                //
//                //                            secondTwodiag_id = secondBean.get(Fianli).getDiag_id();
//                //
//                //                        }
//                //                    });
//                //                    ThreeRecyclerView.addView(checkBox);
//                //
//                //                }
//
//
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });
//
//    }
//
//    private void SecondRecyclerDataThree(String typeid) {
//        flag = 2;
//        BeanList.clear();
//        Map<String, String> params = new HashMap<>();
//        params.put("action", "Diagnosis");
//        params.put("typeid", String.valueOf(2));
//        params.put("diag_id", typeid);
//        params.put("disease_id", "3");
//
//        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
//            @Override
//            public void onSuccess(String data) {
//
//                Log.d("ZhenDuanActivity", "舌苔请求的数据     " + data);
//
//                Gson gson = new Gson();
//
//                PopuoBean popuoBean = gson.fromJson(data, PopuoBean.class);
//
//                if (data == null || popuoBean == null) {
//                    return;
//                } else if (!popuoBean.getStatus().equals("200")) {
//                    return;
//                } else {
//                    mPopup();
//                    BeanList.addAll(popuoBean.getData());
//
//                }
//
//            }
//
//            @Override
//            public void onError(String error) {
//
//            }
//        });
//
//
//    }


    /**
     * 动态添加显示数据
     */

    // TODO: 2017/9/22 initLisenter
    @Override
    protected void initLisenter() {

    }

    // TODO: 2017/9/17 点击事件
    @OnClick({R.id.Self_LookDetail_but, R.id.Self_ImageNext, R.id.Self_RadioChaHao, R.id.Self_Diagnose_Back
            , Self_mLinearDongtaiSecond
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.Self_Diagnose_Back:

                finish();
                break;

            // TODO: 2017/9/16     //这是点击查看详情，把布局整体移动，把另一个布局进行隐藏。
            case R.id.Self_LookDetail_but:

                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(SelfRelativePersonImage, "translationX", translationX, -widthX);
                objectAnimator.start();

                //把隐患这个布局进行隐藏,
                SelfYinHuanRelative.setVisibility(View.GONE);
                //这是隐藏的带叉号的布局
                //                SelfMLinearZhengZhuang.setVisibility(View.GONE);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(100);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //框框这张图片，把它延时进行显示
                                    SelfRelativeKuang.setVisibility(View.VISIBLE);

                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }.start();
                mTextNext.setText("下一步");

                //在点击查看详情的时候加载数据


                //把框框这个布局进行展示

                break;


            // TODO: 2017/9/16 这是点击下一步做的操作
            case R.id.Self_ImageNext:

                /**
                 * 1:当我点击第一次的时候，选择病症，不选的话不让点击，把选择的值传到下个页面
                 *
                 * 2: 上面那个可以点击删除，没有的话，返回上级重新选择,下面还有兼症。再把值传到下一页面
                 *
                 * 3: 和上个页面一样的操作，再点击诊断弹出框，选择舌苔
                 *
                 * 4：进行判断吧！
                 */

                if (flag == 4) {
                    SelfSelectorText.setText("请选择您的症状：");
                    mTextNext.setText("下一步");

                    SelfSelectorText.setVisibility(View.VISIBLE);

                    ZhenDuanJieGuoText.setVisibility(View.GONE);

                    flag = 0;

                } else if (flag == 0) {

                    mTextNext.setText("下一步");

                    SelfRadioChaHao.setVisibility(View.VISIBLE);

                    //如果上面选择好的布局一直是隐藏的状态，就让他执行第一次点击事件
                    //在进行点击的时候在加载数据


                    //第二次加载的数据

                    Log.d("ZhenDuanActivity", "tabposition:诊断部位   " + tabposition);


                    Log.d("ZhenDuanActivity", "flag:当点击等于0的时候隐藏第一层布局++++" + flag);
                    flag = 1;


                } else if (flag == 1) {

                    mTextNext.setText("下一步");
                    //在进行第二次点击的时候，把第一的隐藏掉，展示我第二层的叉号
                    SelfMLinearDongtaiSecond.setVisibility(View.VISIBLE);

                    SelfRadioChaHao.setVisibility(View.GONE);



                    Log.d("ZhenDuanActivity", "flag:当点击等于1的时候隐藏第二层布局++++" + flag);
                    flag = 2;


                    //第二层显示的布局
                } else {
                    mTextNext.setText("下一步");
                    //显示得到的是舌苔布局


                    Log.d("ZhenDuanActivity", "flag:Popupwindow" + flag);

                }
                //                                flag = (flag + 1) % 3;//其余得到循环执行上面3个不同的功能

                //弹出popwindow

                break;


            case R.id.Self_RadioChaHao:
                //当带叉号的button病情没了之后，相当于返回上衣页面，再把这个布局进行隐藏
                //在把他隐藏的时候 把flag重新变为0，重新记录为第一次的点击事件
                flag = 0;


                SelfRadioChaHao.setVisibility(View.GONE);  //痛引足心
                /**
                 * 当点击叉号返回的时候把第二个Recyclerview隐藏，把上个进行显示
                 */

                break;

            case R.id.Self_mLinearDongtaiSecond:
                flag = 1;
                //这是在点击第三层recycleview的时候，把当前布局，自己本身进行隐藏。。上面的叉号

                SelfRadioChaHao.setVisibility(View.VISIBLE);
                SelfMLinearDongtaiSecond.setVisibility(View.GONE);  //小

                break;
        }

    }


    private void AddSecondLinear(List<String> list) {
        //        for (int i = 0; i < list.size(); i++) {
        //
        //            TextView tv = new TextView(this);
        //
        //            tv.setTextColor(getResources().getColor(R.color.ZhenDuanText));
        //
        //            tv.setTextSize(12);
        //
        //            tv.setText(seconddiag_name);
        //
        //            tv.setBackground(getResources().getDrawable(R.drawable.self_zhengzhuang_shape_false));
        //
        //            Drawable drawable = getResources().getDrawable(R.drawable.chazi_4man);
        //
        //
        //            tv.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        //
        //            tv.setCompoundDrawablePadding(5);
        //
        //            tv.setOnClickListener(new View.OnClickListener() {
        //                @Override
        //                public void onClick(View v) {
        //                    flag = 1;
        //                    //这是在点击第三层recycleview的时候，把当前布局，自己本身进行隐藏。。上面的叉号
        //
        //                    SelfRadioChaHao.setVisibility(View.VISIBLE);
        //                    SecondRecyclerView.setVisibility(View.VISIBLE);
        //                    ThreeRecyclerView.setVisibility(View.GONE);
        //                    FirstRecyclerView.setVisibility(View.GONE);
        //                    SelfMLinearDongtaiSecond.setVisibility(View.GONE);  //小便不利
        //
        //
        //                }
        //            });
        //
        //
        //            SelfMLinearDongtaiSecond.addView(tv);
        //
        //        }


    }

    /**
     * 弹出popupwindow
     */

    // TODO: 2017/10/17  舌苔弹出PopupWindow
    private void mPopup() {

        backgroundAlpha(0.4f);
        View view = ZhenDuanActivity.this.getLayoutInflater().inflate(R.layout.activity_shouye_shetai, null);

        mRelative = (RelativeLayout) view.findViewById(R.id.SheTai_ZhenDuan);

        mRelative.setOnClickListener(this);

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);
        popupWindow.showAtLocation(findViewById(R.id.mLinear_Popup), Gravity.NO_GRAVITY, 0, 0);

        mRecycler = (RecyclerView) view.findViewById(mRecyclerView);

        mPopBackImage = (ImageView) view.findViewById(R.id.mPopup_Back);

        //设置数据

        mRecycler.setLayoutManager(new GridLayoutManager(this, 2));

        PopwindowAdapter = new PopupSheTaiAdapter(ZhenDuanActivity.this, BeanList);
        PopwindowAdapter.setClickListener(this);

        mRecycler.setAdapter(PopwindowAdapter);

        PopwindowAdapter.notifyDataSetChanged();

        mPopBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });
        popupWindow.setOnDismissListener(new poponDismissListener());

    }


    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }

    /**
     * //popupWindow里面组件的点击事件
     * <p>
     * 1：点击舌苔确定进行跳转文章页面 ,诊断的点击事件
     *
     * @param v
     */

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.SheTai_ZhenDuan:
                SheTaiRequest();
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    backgroundAlpha(1.0f);

                    mTextNext.setText("再测一次");

                    flag = 4;
                }

                break;

        }

    }

    /**
     * 最后面舌苔的请求数据
     */
    // TODO: 2017/10/17 舌苔的请求数据
    private void SheTaiRequest() {
        JSONObject object = new JSONObject();

        String ids = null;
        try {
            object.put(secondDiag_id, "1");
            object.put(secondTwodiag_id, "1");
            object.put(sheTaiDiagId, "1");

            ids = object.toString();

            Log.d("ZhenDuanActivity", "组合起来的id" + ids);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "Diagnosis");
        params.put("typeid", "1");
        params.put("diag_id", firstdiag_id);
        params.put("state", "1");
        params.put("ids", ids);
        params.put("token", "");

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("ZhenDuanActivity", "这段舌苔结果      " + data);

                Gson gson = new Gson();
                try {
                    successBean = gson.fromJson(data, SheTaiSuccessBean.class);

                    final SheTaiSuccessBean.DataBean bean = successBean.getData();

                    if (data == null || successBean == null) {

                        return;
                    } else if (successBean.getStatus().equals("200")) {
                        SelfSelectorText.setText("您当前症状为：");

                        zhenduanDescText.setText(bean.getDescription() + "");
                        zhenduanDescText.setVisibility(View.VISIBLE);


                        SelfMLinearDongtaiSecond.setVisibility(View.GONE);

                        ResultLink.setVisibility(View.VISIBLE);

                        ZhenDuanJieGuoText.setVisibility(View.VISIBLE);

                        JieGuoTitle.setText(bean.getName());
                        JieGuoZhiLiaoText.setText(bean.getName() + "");


                        ResultLink.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WebViewUtils.WebFrom(ZhenDuanActivity.this, bean.getArticle().get(0).getLink(), bean.getName());

                            }
                        });
                    } else if (successBean.getStatus().equals("50")) {
                        SelfSelectorText.setText("您当前症状为：");


                        SelfMLinearDongtaiSecond.setVisibility(View.GONE);

                        ResultLink.setVisibility(View.GONE);

                        ZhenDuanJieGuoText.setVisibility(View.VISIBLE);

                        JieGuoTitle.setText(bean.getDescription() + "");

                        zhenduanDescText.setVisibility(View.GONE);


                    }

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String error) {

            }
        });

        //        App.myOkHttp.post().url(BaseUrl.BaseUrl)
        //                .params(params)
        //                .enqueue(new GsonResponseHandler<SheTaiSuccessBean>() {
        //                    @Override
        //                    public void onSuccess(int statusCode, SheTaiSuccessBean response) {
        //
        //                        if (response == null || response.getData() == null) {
        //                            return;
        //                        }
        //                        final SheTaiSuccessBean.DataBean bean = response.getData();
        //
        //                        //诊断成功
        //                        if (response.getStatus().equals("200")) {
        //
        //
        //                            ThreeRecyclerView.setVisibility(View.GONE);
        //                            SelfMLinearDongtaiSecond.setVisibility(View.GONE);
        //
        //                            ResultLink.setVisibility(View.VISIBLE);
        //
        //                            ZhenDuanJieGuoText.setVisibility(View.VISIBLE);
        //
        //                            JieGuoTitle.setText(bean.getName());
        //
        //                            JieGuoZhiLiaoText.setText(bean.getArticle().get(0).getTitle());
        //
        //                            JieGuoZhiLiaoText.setOnClickListener(new View.OnClickListener() {
        //                                @Override
        //                                public void onClick(View v) {
        //                                    WebViewUtils.WebFrom(ZhenDuanActivity.this, bean.getArticle().get(0).getLink(), bean.getName());
        //
        //                                }
        //                            });
        //
        //
        //                        } else if (response.getStatus().equals("50")) {
        //
        //                            ResultLink.setVisibility(View.GONE);
        //                            JieGuoTitle.setVisibility(View.GONE);
        //                            ChuFangContent.setText(bean.getDescription() + "");
        //                            ChuFangContent.setVisibility(View.VISIBLE);
        //
        //                        }
        //
        //
        //                    }
        //
        //                    @Override
        //                    public void onFailure(int statusCode, String error_msg) {
        //
        //                    }
        //                });
    }

    /**
     * 舌苔页面的自定义点击事件
     *
     * @param Position
     */
    // TODO: 2017/10/17 舌苔的自定义点击事件
    @Override
    public void OnItemClick(int Position) {

        Log.d("ZhenDuanActivity", "Position:" + Position);

        PopuoBean.DataBean dataBean = BeanList.get(Position);

        sheTaiDiagId = dataBean.getDiag_id();


        mRelative.setVisibility(View.VISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 添加新笔记时弹出的popWin关闭的事件，主要是为了将背景透明度改回来
     *
     * @author cg
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }


    /**
     * 注销EventBUs
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}