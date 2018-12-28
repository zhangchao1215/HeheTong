package com.example.heyikun.heheshenghuo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.ShouYeFragmentAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.UserRecyclerAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.ViewPagerAdapter;
import com.example.heyikun.heheshenghuo.controller.user.HeheServiceActivity;
import com.example.heyikun.heheshenghuo.controller.user.UserFeedBackActivity;
import com.example.heyikun.heheshenghuo.controller.user.UserMyCollectActivity;
import com.example.heyikun.heheshenghuo.controller.user.UserSetSettingActivity;
import com.example.heyikun.heheshenghuo.controller.user.UserSettingActivity;
import com.example.heyikun.heheshenghuo.controller.user.fragment.MyMoneyFragment;
import com.example.heyikun.heheshenghuo.controller.user.fragment.MyMoneyFragmentWomen;
import com.example.heyikun.heheshenghuo.controller.user.fragment.MydaRenFragment;
import com.example.heyikun.heheshenghuo.controller.user.fragment.MydaRenFragmentWomen;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.UserFragmentBean;
import com.example.heyikun.heheshenghuo.modle.bean.UserLinkBean;
import com.example.heyikun.heheshenghuo.modle.bean.UserLoginBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBLoginBean;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBLoginBeanWomen;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBSettringBean;
import com.example.heyikun.heheshenghuo.modle.eventbus.EventUserLinkBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.SPUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.UserLinrarManager;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by hyk on 2017/9/22.
 * 男性个人中心
 */

public class HeHeUserWomenFragment extends BaseFragment {

    @BindView(R.id.User_ImageFanKui)
    ImageView UserImageFanKui;
    @BindView(R.id.User_ImageMessage)
    ImageView UserImageMessage;
    @BindView(R.id.User_ImageSmallSetting)
    ImageView UserImageSmallSetting;
    @BindView(R.id.User_JiangPaiImage)
    ImageView UserJiangPaiImage;
    @BindView(R.id.User_userImage)
    ImageView UserUserImage;
    @BindView(R.id.User_userName)
    TextView UserUserName;
    @BindView(R.id.User_Man_man)
    ImageView UserManMan;
    @BindView(R.id.User_SettingImage)
    RelativeLayout UserSettingImage;
    @BindView(R.id.User_ViewPager1)
    ViewPager UserViewPager;
    @BindView(R.id.User_meCollect)
    TextView UserMeCollect;
    @BindView(R.id.User_meCollect_Image)
    ImageView UserMeCollectImage;
    @BindView(R.id.User_meFootprint)
    TextView UserMeFootprint;
    @BindView(R.id.User_meFootprint_Image)
    ImageView UserMeFootprintImage;
    @BindView(R.id.User_meJop)
    TextView UserMeJop;
    @BindView(R.id.User_meJop_Image)
    ImageView UserMeJopImage;
    @BindView(R.id.User_meGeneralize)
    TextView UserMeGeneralize;
    @BindView(R.id.User_meGeneralize_Image)
    ImageView UserMeGeneralizeImage;
    @BindView(R.id.User_meActivity)
    TextView UserMeActivity;
    @BindView(R.id.User_meActivity_Image)
    ImageView UserMeActivityImage;
    @BindView(R.id.User_meConsult)
    TextView UserMeConsult;
    @BindView(R.id.User_meConsult_Image)
    ImageView UserMeConsultImage;
    @BindView(R.id.My_collection)
    RelativeLayout MyCollection;
    @BindView(R.id.My_Zuji)
    RelativeLayout MyZuji;
    @BindView(R.id.My_Task)
    RelativeLayout MyTask;
    @BindView(R.id.My_Extension)
    RelativeLayout MyExtension;
    @BindView(R.id.My_Activity)
    RelativeLayout MyActivity;
    @BindView(R.id.My_Consultation)
    RelativeLayout MyConsultation;
    @BindView(R.id.mText_MyDaren)
    TextView mTextMyDaren;
    @BindView(R.id.Blue_Image)
    ImageView BlueImage;
    @BindView(R.id.Hui_Image)
    ImageView HuiImage;
    @BindView(R.id.mText_user_brief)
    TextView mTextUserBrief;
    @BindView(R.id.mText_user_waitPay)
    TextView mTextUserWaitPay;
    @BindView(R.id.mText_user_waitShip)
    TextView mTextUserWaitShip;
    @BindView(R.id.mText_user_waitReceipt)
    TextView mTextUserWaitReceipt;
    @BindView(R.id.mText_user_waitEvaluate)
    TextView mTextUserWaitEvaluate;
    @BindView(R.id.mText_user_afterSale)
    TextView mTextUserAfterSale;
    @BindView(R.id.Look_AllDingdan)
    TextView LookAllDingdan;
    @BindView(R.id.mImage_vip)
    ImageView mImageVip;
    Unbinder unbinder;
    @BindView(R.id.user_LoginOut)
    Button userLoginOut;

    @BindView(R.id.User_Vip_image_tuiguang)
    ImageView UserVipImageTuiguang;
    @BindView(R.id.User_Vip_tuiguang)
    RelativeLayout UserVipTuiguang;

    @BindView(R.id.Money_Updada_plan)
    ImageView MoneyUpdadaPlan;
    @BindView(R.id.Money_health_plan)
    ImageView MoneyHealthPlan;

    @BindView(R.id.User_friend_Image)
    ImageView UserFriendImage;
    @BindView(R.id.user_relative_friend)
    RelativeLayout userRelativeFriend;
    @BindView(R.id.user_item_viewpager)
    ViewPager userItemViewpager;
    @BindView(R.id.User_TiZhiImage)
    TextView UserTiZhiImage;
    @BindView(R.id.User_XinliImage)
    TextView UserXinliImage;
    @BindView(R.id.User_MoneyImage)
    TextView UserMoneyImage;
    @BindView(R.id.user_VipImage)
    ImageView userVipImage;
    @BindView(R.id.user_recycler)
    RecyclerView userRecycler;
    @BindView(R.id.view_Image1)
    ImageView viewImage1;
    @BindView(R.id.view_Image2)
    ImageView viewImage2;
    @BindView(R.id.view_Image3)
    ImageView viewImage3;

    private ShouYeFragmentAdapter fragmentAdapter;
    private List<Fragment> fragmentList;
    private String encryptUserId;
    private String encryptToken;
    private String encrypt_appsign;
    private String mUserId;
    private String mTokenTwo;
    private String answer;
    private String email;
    private String mtoken;
    private FragmentManager fragmentManager;
    private UserLinkBean.DataBean dataLinkBean;
    private HeHeUserWomenFragment womenFragment;
    private List<View> itemviewList;
    private ViewPagerAdapter viewPagerAdapter;
    private UserFragmentBean.DataBean.VipAdBean vip_ad;


    private UserFragmentBean.DataBean dataBean;
    private List<UserFragmentBean.DataBean.TestQueBean> datarrBeanList;
    private UserRecyclerAdapter recyclerAdapter;
    private int vip;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_main_women;
    }

    @Override
    public void onResume() {
        super.onResume();

        userRequest();
    }

    @Override
    protected void initData() {
        init();

        allLink();
    }

    @Override
    protected void initView(View view) {
        datarrBeanList = new ArrayList<>();
        itemviewList = new ArrayList<>();

        EventBus.getDefault().register(this);

    }

    @Override
    protected void initListener() {
        mViewPagerListener();

    }


    /**
     * 方法初始化
     */
    private void init() {

        fragmentList = new ArrayList<>();

        fragmentList.add(new MydaRenFragmentWomen());
        fragmentList.add(new MyMoneyFragmentWomen());


        fragmentAdapter = new ShouYeFragmentAdapter(getFragmentManager(), fragmentList);

        UserViewPager.setAdapter(fragmentAdapter);


        //try catch 抛出这个异常这样就不会崩溃了

        //        userRecycler.setLayoutManager(new UserLinrarManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        userRecycler.setLayoutManager(linearLayoutManager);
        userRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();//可见范围内的第一项的位置
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();//可见范围内的最后一项的位置
                int itemCount = linearLayoutManager.getItemCount();//recyclerview中的item的所有的数目


                int firstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                switch (firstCompletelyVisibleItemPosition) {
                    case 0:
                        viewImage1.setImageDrawable(getResources().getDrawable(R.drawable.dianxuanzhong_woman_4x));
                        viewImage2.setImageDrawable(getResources().getDrawable(R.drawable.dianmeixuanzhong_man_4x));
                        viewImage3.setImageDrawable(getResources().getDrawable(R.drawable.dianmeixuanzhong_man_4x));
                        break;

                    case 1:
                        viewImage2.setImageDrawable(getResources().getDrawable(R.drawable.dianxuanzhong_woman_4x));
                        viewImage1.setImageDrawable(getResources().getDrawable(R.drawable.dianmeixuanzhong_man_4x));
                        viewImage3.setImageDrawable(getResources().getDrawable(R.drawable.dianmeixuanzhong_man_4x));
                        break;

                    case 2:
                        viewImage3.setImageDrawable(getResources().getDrawable(R.drawable.dianxuanzhong_woman_4x));
                        viewImage2.setImageDrawable(getResources().getDrawable(R.drawable.dianmeixuanzhong_man_4x));
                        viewImage1.setImageDrawable(getResources().getDrawable(R.drawable.dianmeixuanzhong_man_4x));
                        break;
                }


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });


    }

    private void mViewPagerListener() {
        UserViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position % fragmentList.size() == 0) {
                    BlueImage.setImageDrawable(getResources().getDrawable(R.drawable.dianxuanzhong_woman_4x));
                    HuiImage.setImageDrawable(getResources().getDrawable(R.drawable.dianmeixuanzhong_man_4x));
                    mTextMyDaren.setText("达人数据");
                } else if (position % fragmentList.size() == 1) {
                    HuiImage.setImageDrawable(getResources().getDrawable(R.drawable.dianxuanzhong_woman_4x));
                    BlueImage.setImageDrawable(getResources().getDrawable(R.drawable.dianmeixuanzhong_man_4x));
                    mTextMyDaren.setText("资产数据");
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @OnClick({R.id.User_ImageFanKui, R.id.User_ImageMessage, R.id.User_ImageSmallSetting, R.id.User_SettingImage,
            R.id.User_meCollect, R.id.User_meCollect_Image, R.id.user_VipImage,
            R.id.My_collection, R.id.My_Zuji, R.id.User_meJop, R.id.User_meJop_Image, R.id.My_Task, R.id.My_Extension,
            R.id.User_meActivity, R.id.My_Activity, R.id.My_Consultation, R.id.Look_AllDingdan,
            R.id.mText_user_waitPay, R.id.mText_user_waitShip, R.id.mText_user_waitReceipt,
            R.id.mText_user_waitEvaluate, R.id.mText_user_afterSale, R.id.user_LoginOut, R.id.User_Vip_tuiguang,
            R.id.Money_health_plan, R.id.Money_Updada_plan, R.id.user_relative_friend
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.user_VipImage:

                WebViewUtils.publicWebView(getContext(), vip_ad.getVip_link(), "和合家庭全域健康管理计划");


                break;

            //全部订单
            case R.id.Look_AllDingdan:

                WebViewUtils.publicWebView(getContext(), dataLinkBean.getAllOrder(), "全部订单");

                break;

            //我的反馈
            case R.id.User_ImageFanKui:

                mIntent(UserFeedBackActivity.class);


                break;

            //我的通知
            case R.id.User_ImageMessage:

                WebViewUtils.publicWebView(getContext(), dataLinkBean.getNotice(), "我的消息");

                //                                mIntent(UserNotificationActivity.class);


                break;
            //我的设置
            case R.id.User_ImageSmallSetting:
                mIntent(UserSetSettingActivity.class);

                break;
            //设置个人信息的
            case R.id.User_SettingImage:

                EBSettringBean bean = new EBSettringBean();
                bean.setAnswer(answer);
                bean.setEmail(email);
                EventBus.getDefault().postSticky(bean);
                Intent SexIntent = new Intent(getContext(), UserSettingActivity.class);
                startActivityForResult(SexIntent, 521);


                break;


            //我的收藏
            case R.id.My_collection:
                Intent intent1 = new Intent(getContext(), UserMyCollectActivity.class);
                startActivity(intent1);

                break;

            //我的足迹
            case R.id.My_Zuji:
                WebViewUtils.publicWebView(getContext(), dataLinkBean.getFootprint(), "我的足迹");

                break;


            //我的任务
            case R.id.My_Task:

                //                WebViewUtils.ArticleWebView(getContext(), dataLinkBean);

                break;

            //我的分享
            case R.id.My_Extension:
                WebViewUtils.publicWebView(getContext(), dataLinkBean.getSharelink(), "我的分享");

                break;

            //我的VIP推广

            case R.id.User_Vip_tuiguang:
                //                WebViewUtils.ArticleWebView(getContext(), dataLinkBean.getExtension());
                //                Intent VipIntent = new Intent(getContext(), ShareWebView.class);
                //                VipIntent.putExtra("url", dataLinkBean.getExtension());
                //                VipIntent.putExtra("title","Vip推广");
                //                startActivity(VipIntent);

                WebViewUtils.publicWebView(getContext(), dataLinkBean.getExtension(), "VIP推广");

                break;

            //我的活动
            case R.id.My_Activity:
                //                mIntent(UserMyActivitiesActivity.class);
                WebViewUtils.publicWebView(getContext(), dataLinkBean.getActivlink(), "我的活动");

                break;

            //我的咨询
            case R.id.My_Consultation:

                WebViewUtils.publicWebView(getContext(), dataLinkBean.getConsulink(), "我的咨询");

                break;
            //待付款
            case R.id.mText_user_waitPay:
                WebViewUtils.publicWebView(getContext(), dataLinkBean.getPaylink(), "待付款");

                break;
            //代发货
            case R.id.mText_user_waitShip:

                WebViewUtils.publicWebView(getContext(), dataLinkBean.getShiplink(), "代发货");
                break;

            //待收货
            case R.id.mText_user_waitReceipt:

                WebViewUtils.publicWebView(getContext(), dataLinkBean.getReceiptlinnk(), "待收货");
                break;

            //待评价
            case R.id.mText_user_waitEvaluate:

                WebViewUtils.publicWebView(getContext(), dataLinkBean.getCommentlink(), "待评价");
                break;


            //售后
            case R.id.mText_user_afterSale:

                Intent intent = new Intent(getContext(), HeheServiceActivity.class);
                startActivity(intent);

                break;
            //退出登录
            case R.id.user_LoginOut:
                dialog();

                break;

            //我的朋友
            case R.id.user_relative_friend:

                WebViewUtils.publicWebView(getContext(), dataLinkBean.getMyfriend(), "我的朋友");


                break;
        }
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("确认退出吗？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                SPUtils.remove(getContext(), "token");
                SPUtils.remove(getContext(), "user_id");

                SPUtils.clear(getContext());
                dialog.dismiss();
                outLogin();


            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void mIntent(Class c) {

        Intent intent = new Intent(getContext(), c);
        startActivity(intent);
    }


    /**
     * 登录页面通过EventBUs传过来的值
     */

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void UserFragment(EBLoginBean bean) {
        //一句话简介
        String brief = bean.getBrief();


        //待退款订单
        String await_comment = bean.getAwait_comment();

        //待付款
        String await_pay = bean.getAwait_pay();


        //待收货
        String await_receipt = bean.getAwait_receipt();


        //代发货
        String await_ship = bean.getAwait_ship();


        //收益

        String profit = bean.getProfit();

        //退款订单

        String payback = bean.getPayback();


    }


    /**
     * 获取个人中心的请求
     */
    int userFlag = 0;

    private void userRequest() {
        datarrBeanList.clear();
        //        AppUtils.dialog();
        final Map<String, String> params = new HashMap<>();

        mUserId = AppUtils.get().getString("user_id", "");


        mtoken = AppUtils.get().getString("token", "");


        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        try {

            //给userID 还有 生成二次token ，在进行AES加密
            encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);

            mTokenTwo = mUserId + "," + mtoken + "," + timestamp;


            encryptToken = AESUtils.Encrypt(mTokenTwo, BaseUrl.AESKey);

            // app_sign
            String md5 = "Personal" + timestamp + BaseUrl.AESKey;

            String encrypt = MD5Utils.encrypt(md5);

            encrypt_appsign = AESUtils.Encrypt(encrypt, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("action", "Personal");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("app_sign", encrypt_appsign);

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        Log.d("HeHeUser", response);

                        Gson gson = new Gson();
                        try {
                            UserLoginBean userLoginBean = gson.fromJson(response, UserLoginBean.class);

                            if (userLoginBean.getStatus().equals("100")) {
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                startActivity(intent);
                            }

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

        //


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private UserFragmentBean fragmentBean;

            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();
                try {
                    fragmentBean = gson.fromJson(data, UserFragmentBean.class);

                    if (fragmentBean.getStatus().equals("200")) {
                        int size = fragmentBean.getData().getTest_que().size();
                        Log.d("HeHeUserFragment", "response.getData().getTest_que().size():" + size);
                        datarrBeanList.addAll(fragmentBean.getData().getTest_que());
                        recyclerAdapter = new UserRecyclerAdapter(datarrBeanList, getContext());
                        //                    recyclerAdapter.notifyData(fragmentBean.getData().getTest_que());

                        recyclerAdapter.setOnitemClick(new UserRecyclerAdapter.OnitemClick() {
                            @Override
                            public void OnClickListener(View view, int position) {
                                UserFragmentBean.DataBean.TestQueBean testQueBean = datarrBeanList.get(position);
                                //为0就是不是vip 1 是VIP
                                if (vip == 0) {
                                    AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                                    dialog.setMessage("请升级为VIP用户，查看专家定制养生方案");

                                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });

                                    dialog.create();
                                    dialog.show();

                                    UserJiangPaiImage.setVisibility(View.GONE);

                                } else {

                                    UserJiangPaiImage.setVisibility(View.VISIBLE);

                                    WebViewUtils.publicWebView(getContext(), testQueBean.getBody_link(), "诊断信息");


                                }
                            }
                        });
                        userRecycler.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();


                        dataBean = fragmentBean.getData();
                        // VIP 图片

                        vip_ad = dataBean.getVip_ad();

                        Glide.with(getContext())
                                .load(vip_ad.getVip_pic())
                                .placeholder(R.drawable.vip_life)
                                .into(userVipImage);


                        String headimg = dataBean.getHeadimg();

                        if (headimg.equals("")) {

                        } else {
                            Glide.with(getContext()).load(headimg).asBitmap().centerCrop().placeholder(R.drawable.touxiang_nan_man_4x).into(new BitmapImageViewTarget(UserUserImage) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                                    circularBitmapDrawable.setCircular(true);
                                    UserUserImage.setImageDrawable(circularBitmapDrawable);

                                }

                            });
                            AppUtils.put().putString("user_image", headimg + "");
                            AppUtils.put().putString("user_name", dataBean.getUser_name() + "");
                            AppUtils.put().commit();
                            //获取邮箱，密保 vip

                            vip = dataBean.getVip();

                            if (vip == 0) {
                                mImageVip.setVisibility(View.GONE);
                            } else {
                                mImageVip.setVisibility(View.VISIBLE);
                            }


                            answer = dataBean.getAnswer();


                            email = dataBean.getEmail();

                            //获取姓名

                            UserUserName.setText(dataBean.getUser_name());

                            //获取签名
                            mTextUserBrief.setText(dataBean.getAlias());

                            List<UserFragmentBean.DataBean.TestQueBean> test_que = dataBean.getTest_que();

                            //身----------------------------------------------------------------------
                            UserFragmentBean.DataBean.TestQueBean testQueBean1 = test_que.get(0);

                            List<UserFragmentBean.DataBean.TestQueBean.DatarrBean> datarr = testQueBean1.getDatarr();


                            /**
                             * 向首页传递数据用户名和头像
                             */
                            //----------------------------------------------------------------------------
                            EBLoginBeanWomen loginBean = new EBLoginBeanWomen();

                            //账户余额
                            loginBean.setUserMoney(dataBean.getUser_money());

                            //获取易券

                            loginBean.setYiTicket(dataBean.getPay_points());

                            //获取坤券

                            loginBean.setKunTicket(dataBean.getKun());

                            //获取和券

                            loginBean.setHeTicket(dataBean.getFrozen_money());

                            //获取收益
                            loginBean.setProfit(dataBean.getProfit());

                            //获取优惠券

                            loginBean.setCoupon(dataBean.getCoupon());

                            //获取大咖数量

                            loginBean.setBigcast(dataBean.getBigcast());

                            //获取关注我的数量

                            loginBean.setFans(dataBean.getFans());

                            //获取我关注的数量

                            loginBean.setFollow(dataBean.getFollow());

                            //获取主题

                            loginBean.setTheme(dataBean.getTheme());

                            //获取新回复
                            loginBean.setReplay(dataBean.getReplay());

                            //获取待收货

                            loginBean.setAwait_receipt(dataBean.getAwait_receipt());

                            //获取退款中订单

                            loginBean.setPayback(dataBean.getPayback());

                            //获取待评价

                            loginBean.setAwait_comment(dataBean.getAwait_comment());

                            //获取代发货

                            loginBean.setAwait_ship(dataBean.getAwait_ship());

                            //获取待付款
                            loginBean.setAwait_pay(dataBean.getAwait_pay());


                            EventBus.getDefault().postSticky(loginBean);


                        }


                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Exception c) {
                    c.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {

            }
        });

    }


    //    private void getHeight(UserFragmentBean.DataBean.TestQueBean.DatarrBean datarrBean, ImageView imageView) {
    //
    //        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
    //        layoutParams.height = datarrBean.getTest_res();
    //        imageView.setLayoutParams(layoutParams);
    //
    //
    //    }


    /**
     * 获取个人中心网址的请求
     */


    private void allLink() {

        Map<String, String> params = new HashMap<>();
        params.put("action", "AllLink");
        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<UserLinkBean>() {
                    @Override
                    public void onSuccess(int statusCode, UserLinkBean response) {

                        if (response.getData() == null) {
                            return;
                        }
                        Log.d("HeHeUserFragment", response.getData().getMyfriend());

                        if (response.getStatus().equals("200")) {

                            dataLinkBean = response.getData();

                            EventUserLinkBean bean = new EventUserLinkBean();
                            bean.setBigcastlink(dataLinkBean.getBigcastlink());

                            bean.setEasylink(dataLinkBean.getEasylink());

                            bean.setFrozenlink(dataLinkBean.getFrozenlink());

                            bean.setKunlink(dataLinkBean.getKunlink());

                            bean.setCouponlink(dataLinkBean.getCouponlink());

                            bean.setBalancelink(dataLinkBean.getBalancelink());

                            EventBus.getDefault().postSticky(bean);

                        }


                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }

    /**
     * 退出登录
     */

    private void outLogin() {

        Map<String, String> params = new HashMap<>();
        params.put("action", "LoginOut");
        params.put("user_id", encryptUserId);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("HeHeUserFragment", "退出登录   " + data);

                Gson gson = new Gson();
                ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

                if (bean == null || data == null) {
                    return;
                } else if (bean.getStatus().equals("200")) {

                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    SPUtils.clear(getContext());
                    SPUtils.remove(getContext(), "token");
                    SPUtils.remove(getContext(), "user_id");
                    startActivity(intent);


                }

            }

            @Override
            public void onError(String error) {

            }
        });

    }

    /**
     * 跳转回传参数
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 521 && resultCode == Activity.RESULT_OK) {
            String mSex = data.getStringExtra("mSex");


            //            //
            //            if (App.activity instanceof HeHeMainActivity) {
            //                if ("女".equals(mSex)) {
            //
            //                    Log.d("HeHeUserFragment", ("男性页面，  结果   " + mSex));
            //
            //                    ((HeHeMainActivity) App.activity).getManradioGroup().setVisibility(View.GONE);
            //                    ((HeHeMainActivity) App.activity).getWomenRadioGroup().setVisibility(View.VISIBLE);
            //
            //
            //                    ((HeHeMainActivity) App.activity).getWomenRadioGroup().check(4);
            //
            //                    ((HeHeMainActivity) App.activity).getmRadioUserWomen().setChecked(true);
            //
            //                    //把女性隐藏掉，显示男性
            //                                        fragmentManager = getFragmentManager();
            //                                        FragmentTransaction transaction = fragmentManager.beginTransaction();
            //
            //                                        if (womenFragment == null) {
            //                                            womenFragment = new HeHeUserWomenFragment();
            //                                            transaction.add(R.id.Man_frameLayout, womenFragment).commit();
            //                                        } else {
            //                                            transaction.hide(this);
            //                                            transaction.show(womenFragment);
            //                                            transaction.commitAllowingStateLoss();
            //                                        }
            //
            //
            //                } else {
            //
            //                    Log.d("HeHeUserFragment", ("男性页面，  结果   " + mSex));
            //                    ((HeHeMainActivity) App.activity).getManradioGroup().setVisibility(View.VISIBLE);
            //                    ((HeHeMainActivity) App.activity).getWomenRadioGroup().setVisibility(View.GONE);
            //
            //                    ((HeHeMainActivity) App.activity).getManradioGroup().check(4);
            //
            //                    ((HeHeMainActivity) App.activity).getmRadioUser().setChecked(true);
            //
            //
            //                    //                    fragmentManager = getFragmentManager();
            //                    //                    FragmentTransaction transaction = fragmentManager.beginTransaction();
            //                    //
            //                    //                    if (womenFragment != null) {
            //                    //                        transaction.hide(womenFragment);
            //                    //                    }
            //                    //                    transaction.show(this);
            //                    //                    transaction.commit();
            //
            //
            //                }
            //
            //            }
            //
        }


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


}
