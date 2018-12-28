package com.example.heyikun.heheshenghuo.newlife;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.ZhenDuanActivity;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.HeHeLifeNewAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.HeHeLifeTextAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.NewLifeMuAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.NewLifeMuAdapterTwo;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeNewLifeBean;
import com.example.heyikun.heheshenghuo.modle.bean.JingluoArticleBean;
import com.example.heyikun.heheshenghuo.modle.bean.NewLifeMuBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EventIntentUserBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.DownPopupWindow;
import com.example.heyikun.heheshenghuo.modle.util.PullDownScrollView;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by hyk on 2018/1/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/13
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：  新首页
 */

public class HeHeNewLifeFragment extends BaseFragment {
    @BindView(R.id.life_shengyinImage)
    ImageView lifeShengyinImage;
    @BindView(R.id.Life_UserImage)
    ImageView LifeUserImage;
    @BindView(R.id.Life_UserName)
    TextView LifeUserName;
    @BindView(R.id.Man_AnimatorImage)
    ImageView ManAnimatorImage;
    @BindView(R.id.Man_LeftImage)
    ImageView ManLeftImage;
    @BindView(R.id.JingMai_mBut)
    Button JingMaiMBut;
    @BindView(R.id.new_image_huo)
    ImageView newImageHuo;
    @BindView(R.id.new_image_mu)
    ImageView newImageMu;
    @BindView(R.id.new_image_tu)
    ImageView newImageTu;
    @BindView(R.id.relative1)
    RelativeLayout relative1;
    @BindView(R.id.new_image_shui)
    ImageView newImageShui;
    @BindView(R.id.new_image_jin)
    ImageView newImageJin;
    @BindView(R.id.New_Life_wuxing_text)
    TextView NewLifeWuxingText;
    @BindView(R.id.New_Life_bianzheng_text)
    TextView NewLifeBianzhengText;
    @BindView(R.id.New_cepinMore_text)
    TextView NewCepinMoreText;
    @BindView(R.id.new_huo_relative)
    RelativeLayout newHuoRelative;
    @BindView(R.id.new_tu_relative)
    RelativeLayout newTuRelative;
    @BindView(R.id.new_mu_relative)
    RelativeLayout newMuRelative;
    @BindView(R.id.new_shui_relative)
    RelativeLayout newShuiRelative;
    @BindView(R.id.new_jin_relative)
    RelativeLayout newJinRelative;
    @BindView(R.id.mScrollview_Linear)
    LinearLayout mScrollviewLinear;
    @BindView(R.id.mScrollview)
    ScrollView mScrollview;
    @BindView(R.id.refresh_root)
    PullDownScrollView refreshRoot;
    @BindView(R.id.New_Life_nine_text)
    TextView NewLifeNineText;
    @BindView(R.id.new_Life_recycler)
    RecyclerView newLifeRecycler;
    @BindView(R.id.mLinear)
    LinearLayout mLinear;
    @BindView(R.id.bianzheng_relative)
    RelativeLayout bianzhengRelative;
    Unbinder unbinder;
    @BindView(R.id.new_life_wuxing_relative)
    RelativeLayout newLifeWuxingRelative;
    @BindView(R.id.New_Life_nine_Relative)
    RelativeLayout NewLifeNineRelative;
    @BindView(R.id.New_life_more_relative)
    RelativeLayout NewLifeMoreRelative;
    private MediaPlayer mediaPlayer;
    private int MusicFlag;
    private String user_id;
    private String token;
    private HeHeLifeTextAdapter textAdapter;

    public static final String TAG = "HeHeNewLifeFragment";
    private PopupWindow popupWindow;

    private Timer timer;
    private int time;
    private RelativeLayout background;
    private View view;
    private ListView listView;
    private HeHeNewLifeBean.DataBean dataBean;

    private String test_type;
    private LinearLayout linearLayout;
    private HeHeNewLifeBean.DataBean.NineBean nine;
    private HeHeNewLifeBean.DataBean.FivelineBean fiveline;

    private List<HeHeNewLifeBean.DataBean.FireBean> fireBeanList;
    private HeHeLifeNewAdapter newAdapter;

    private List<NewLifeMuBean.FiveTestBean> fiveTestBeanList;
    private NewLifeMuAdapter muAdapter;
    private NewLifeMuAdapterTwo adapterTwo;
    private View mView;
    private PopupWindow ppw;
    private PopupWindow ppwDown;
    private View mDownView;
    private LinearLayout downLinear;

    private String five_result;
    private int wuXingImageFlag = -1;
    private String aesToken;
    private String aesUserid;
    private HeHeNewLifeBean heHeNewLifeBean;

    @Override
    protected void initData() {

        getJingLuoArtircle();

        getMainData("火");

        getWuxingLoad("火");

    }

    @Override
    protected void initView(View view) {


        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        newLifeRecycler.setLayoutManager(manager);

        //初始化
        init();

        //扫描动画
        ImageAnimator();

        //得到用户信息头像姓名

        MainUserImage();

        //播放音乐
        //写一个Handler
        mDownView = LayoutInflater.from(getContext())
                .inflate(R.layout.activity_newlife_ceshiti_ppw, null);

        downLinear = (LinearLayout) mDownView.findViewById(R.id.newLife_DownLinear);
    }

    @Override
    protected void initListener() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_new_hehe_life_man;
    }


    /**
     * 跳转页面刷新当前视图
     */

    @Override
    public void onResume() {
        super.onResume();

        switch (wuXingImageFlag) {
            case 0:
                getWuxingLoad("火");

                break;
            case 1:
                getWuxingLoad("土");
                break;
            case 2:
                getWuxingLoad("木");
                break;
            case 3:
                getWuxingLoad("水");
                break;
            case 4:
                getWuxingLoad("金");
                break;


        }

    }

    /**
     * 进行初始化的方法
     */
    private void init() {


        fireBeanList = new ArrayList<>();
        fiveTestBeanList = new ArrayList<>();


        //对数据加密
        user_id = AppUtils.get().getString("user_id", "");
        token = AppUtils.get().getString("token", "");
        String time_today = TimeUtils.getCurrentTime_Today();
        String timeStamp = TimeUtils.dataOne(time_today);


        String twoToken = user_id + "," + token + "," + timeStamp;

        Log.d(TAG, "第一次就获取的userID  " + user_id);
        Log.d(TAG, "第一次就获取的token    " + token);

        try {
            if (!user_id.equals("") && !token.equals("")) {
                aesUserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

                aesToken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }


        view = LayoutInflater.from(getContext()).inflate(R.layout.activity_jingmai_ppw, null);
        background = (RelativeLayout) view.findViewById(R.id.relative);

        listView = (ListView) view.findViewById(R.id.Jingmai_listview_ppw);

        //        dataTextAdapter(listView);

        mView = LayoutInflater.from(getContext())
                .inflate(R.layout.activity_newlife_mu_ppw, null);
        linearLayout = (LinearLayout) mView.findViewById(R.id.newLife_Muppw_Linear);


        mediaPlayer = new MediaPlayer();


        /**
         * 启用一个定时器 timer 没格10秒重新获取下时间，重新刷新页面
         * 必须在主线程更新UI 否则会报错
         */
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault());
                String currentTime = sdf.format(new Date());


                time = Integer.parseInt(currentTime);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ChangeImage(time);

                    }
                });

            }
        }, 3000, 10000);

        //进来先得到时间进行图片的切换 ，然后在通过定时器 切换不然的话没有初始化
        SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault());
        String mcurrentTime = sdf.format(new Date());
        int mtime = Integer.parseInt(mcurrentTime);
        ChangeImage(mtime);


        adapterTwo = new NewLifeMuAdapterTwo(fiveTestBeanList, getContext());

    }

    private void clickImage(RelativeLayout jin, RelativeLayout mu, RelativeLayout shui, RelativeLayout huo, RelativeLayout tu) {

        jin.setBackgroundResource(R.drawable.click_biaoqian_4x);
        mu.setBackgroundResource(R.drawable.no_click_biaoqian_4x);
        shui.setBackgroundResource(R.drawable.no_click_biaoqian_4x);
        huo.setBackgroundResource(R.drawable.no_click_biaoqian_4x);
        tu.setBackgroundResource(R.drawable.no_click_biaoqian_4x);

    }


    @OnClick({R.id.life_shengyinImage, R.id.Life_UserName, R.id.Man_LeftImage, R.id.JingMai_mBut,
            R.id.new_image_huo, R.id.new_image_mu, R.id.new_image_tu, R.id.new_image_shui,
            R.id.new_image_jin, R.id.new_huo_relative, R.id.new_tu_relative, R.id.new_mu_relative,
            R.id.new_shui_relative, R.id.new_jin_relative, R.id.New_Life_nine_text, R.id.New_Life_wuxing_text
            , R.id.New_cepinMore_text, R.id.bianzheng_relative, R.id.New_life_more_relative, R.id.New_Life_nine_Relative
            , R.id.new_life_wuxing_relative
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //声音文件
            case R.id.life_shengyinImage:

                PlayMusic();

                break;

            //用户姓名
            case R.id.Life_UserName:


                break;

            //人形图片
            case R.id.Man_LeftImage:

                mAliasPopWindow();
                break;

            //经脉的button
            case R.id.JingMai_mBut:
                mAliasPopWindow();

                break;

            //九种体质之一
            case R.id.New_Life_nine_Relative:

                WebViewUtils.publicWebView(getContext(), nine.getVote_link(), nine.getVote_name());


                break;

            //五行体质之一
            case R.id.new_life_wuxing_relative:
                WebViewUtils.publicWebView(getContext(), fiveline.getVote_link(), fiveline.getVote_name());


                break;
            case R.id.New_life_more_relative:

                Intent intent = new Intent(getContext(), HeHetikuActivity.class);
                startActivity(intent);

                break;

            case R.id.bianzheng_relative:
                Intent intent1 = new Intent(getContext(), ZhenDuanActivity.class);
                startActivity(intent1);

                break;

            //火 在按钮下方横向显示
            case R.id.new_image_huo:


                newImageHuo.setImageResource(R.drawable.huo_click4x);

                getMainData("火");


                wuxingImageppw();
                if (Build.VERSION.SDK_INT < 24) {

                    ppwDown.showAsDropDown(view, -120, 0);
                } else {
//                    ppwDown.showAtLocation(view, Gravity.NO_GRAVITY, 0, view.getHeight() + getStatusBarHeight() + 80);
                    ppwDown.showAsDropDown(view);
                }

                break;

            //木 在按钮右边竖着显示
            case R.id.new_image_mu:

                newImageMu.setImageResource(R.drawable.mu_click4x);
                //                dataBean.getFive().clear();
                getMainData("木");


                wuxingImageppwlife(view);
                int[] location = new int[2];
                view.getLocationOnScreen(location);

                //        ppw.showAtLocation(v, Gravity.NO_GRAVITY, location[0], location[1] - ppw.getHeight());
                //右方显示
                ppw.showAtLocation(view, Gravity.NO_GRAVITY, location[0] + view.getWidth(), location[1]);

                break;

            //土 在按钮左边竖着显示
            case R.id.new_image_tu:

                getMainData("土");

                newImageTu.setImageResource(R.drawable.tu_click4x);

                wuxingImageppwlife(view);
                int[] location1 = new int[2];
                view.getLocationOnScreen(location1);
                ppw.showAtLocation(view, Gravity.NO_GRAVITY, location1[0] - ppw.getWidth() - 125, location1[1]);

                break;

            //水 在按钮上方显示 横向显示
            case R.id.new_image_shui:

                newImageShui.setImageResource(R.drawable.shui_click4x);

                getMainData("水");
                wuxingImageppw();
                int[] shuilocation = new int[2];
                view.getLocationOnScreen(shuilocation);
                wuxingImageppw();

                if(Build.VERSION.SDK_INT<24){
                    ppwDown.showAtLocation(view, Gravity.NO_GRAVITY, shuilocation[0] - 200, shuilocation[1] - ppwDown.getHeight());

                }else {
                    ppwDown.showAtLocation(view, Gravity.NO_GRAVITY, shuilocation[0] - 200, shuilocation[1] - ppwDown.getHeight());

                }




                break;


            //金 在按钮上方横向显示  Y 轴是竖着 X 轴是横向
            case R.id.new_image_jin:

                newImageJin.setImageResource(R.drawable.jin_click4x);

                getMainData("金");

                int[] toplocation = new int[2];
                view.getLocationOnScreen(toplocation);
                wuxingImageppw();

                if(Build.VERSION.SDK_INT<24){

                    ppwDown.showAtLocation(view, Gravity.NO_GRAVITY, toplocation[0] - 200, toplocation[1] - ppwDown.getHeight());
                }else {
                    ppwDown.showAtLocation(view, Gravity.NO_GRAVITY, toplocation[0] - 200, toplocation[1] - ppwDown.getHeight());

                }


                break;
            //---------------------------------------------------------------------------------------------------------------
            //-----------------------------------------------------------------------------------------------

            //火行
            case R.id.new_huo_relative:


                clickImage(newHuoRelative, newJinRelative, newShuiRelative, newMuRelative, newTuRelative);

                getWuxingLoad("火");
                wuXingImageFlag = 0;
                break;

            //土行
            case R.id.new_tu_relative:
                clickImage(newTuRelative, newJinRelative, newShuiRelative, newMuRelative, newHuoRelative);
                getWuxingLoad("土");
                wuXingImageFlag = 1;
                break;

            //木行
            case R.id.new_mu_relative:
                clickImage(newMuRelative, newJinRelative, newShuiRelative, newHuoRelative, newTuRelative);


                getWuxingLoad("木");

                wuXingImageFlag = 2;
                break;

            //水行
            case R.id.new_shui_relative:
                clickImage(newShuiRelative, newJinRelative, newHuoRelative, newMuRelative, newTuRelative);

                getWuxingLoad("水");
                wuXingImageFlag = 3;
                break;

            //金行
            case R.id.new_jin_relative:
                clickImage(newJinRelative, newHuoRelative, newShuiRelative, newMuRelative, newTuRelative);

                getWuxingLoad("金");

                wuXingImageFlag = 4;
                break;
        }
    }


    /**
     * 获取主页数据
     *
     * @param test_type
     */
    private void getMainData(String test_type) {
        fiveTestBeanList.clear();
        linearLayout.removeAllViews();
        downLinear.removeAllViews();

        Log.d(TAG, "加密的token" + aesToken);
        Log.d(TAG, "加密的userID" + aesUserid);
        String timeToday = TimeUtils.getCurrentTime_Today();
        String timeStamp = TimeUtils.dataOne(timeToday);

        String twoToken = user_id + "," + token + "," + timeStamp;

        String aesToken = null;
        if (!token.equals("")) {
            try {
                aesToken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "GetFive");
        params.put("user_id", aesUserid);
        params.put("token", aesToken);
        params.put("test_type", test_type);

        OkHttpUtils.getInstands()
                .OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
                    @Override
                    public void onSuccess(String data) {
                        Log.d(TAG, "获取的数据   " + data);
                        Gson gson = new Gson();

                        try {
                            heHeNewLifeBean = gson.fromJson(data, HeHeNewLifeBean.class);
                            dataBean = heHeNewLifeBean.getData();


                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }

                        if (heHeNewLifeBean.getStatus().equals("200")) {
                            //
                            for (int i = 0; i < dataBean.getFive().size(); i++) {
                                View view = LayoutInflater.from(getContext())
                                        .inflate(R.layout.activity_new_life_text, null);

                                Log.d(TAG, dataBean.getFive().get(i).getVote_name());

                                RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.relativeText);

                                TextView textView = (TextView) view.findViewById(R.id.NewLife_tiText);

                                textView.setText(dataBean.getFive().get(i).getVote_name());

                                textView.setTextColor(getResources().getColor(R.color.white));

                                textView.setTextSize(12);

                                final int FinalI = i;


                                rl.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        WebViewUtils.LifeNewWeb(getContext()
                                                , dataBean.getFive().get(FinalI).getVote_link(),
                                                dataBean.getFive().get(FinalI).getVote_name(),
                                                dataBean.getFive().get(FinalI).getTest_link());

                                        if (ppw.isShowing()) {
                                            ppw.dismiss();
                                            backgroundAlpha(1.0f);
                                            noClickImage();
                                        }
                                    }
                                });


                                linearLayout.addView(view);
                            }

                            for (int i = 0; i < dataBean.getFive().size(); i++) {
                                View view = LayoutInflater.from(getContext())
                                        .inflate(R.layout.activity_new_life_text, null);

                                Log.d(TAG, dataBean.getFive().get(i).getVote_name());
                                RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.relativeText);

                                TextView textView = (TextView) view.findViewById(R.id.NewLife_tiText);

                                textView.setText(dataBean.getFive().get(i).getVote_name());

                                textView.setTextColor(getResources().getColor(R.color.white));

                                textView.setTextSize(12);

                                final int FinalI = i;


                                rl.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        WebViewUtils.LifeNewWeb(getContext()
                                                , dataBean.getFive().get(FinalI).getVote_link(),
                                                dataBean.getFive().get(FinalI).getVote_name()
                                                , dataBean.getFive().get(FinalI).getTest_link()
                                        );

                                        if (ppwDown.isShowing()) {
                                            ppwDown.dismiss();
                                            backgroundAlpha(1.0f);
                                            noClickImage();
                                        }

                                    }
                                });


                                downLinear.addView(view);
                            }


                            //五行体质
                            fiveline = dataBean.getFiveline();

                            NewLifeWuxingText.setText(fiveline.getVote_name());

                            //五行体质

                            nine = dataBean.getNine();

                            NewLifeNineText.setText(nine.getVote_name());

                        }


                        // 得到体质进行播放音乐

                        five_result = heHeNewLifeBean.getFive_result();


                    }

                    @Override
                    public void onError(String error) {

                    }
                });

    }


    private void getWuxingLoad(String mTest_type) {

        fiveTestBeanList.clear();
        String timeToday = TimeUtils.getCurrentTime_Today();
        String timeStamp = TimeUtils.dataOne(timeToday);

        String twoToken = user_id + "," + token + "," + timeStamp;

        String aesToken = null;
        if (!token.equals("")) {
            try {
                aesToken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "FiveTest");
        params.put("user_id", aesUserid);
        params.put("token", aesToken);
        params.put("test_type", mTest_type);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private NewLifeMuBean newLifeMuBean;

            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();
                Log.d(TAG, "测量结果数据   " + data);

                try {
                    newLifeMuBean = gson.fromJson(data, NewLifeMuBean.class);
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                }

                if (newLifeMuBean.getStatus().equals("200")) {
                    List<NewLifeMuBean.FiveTestBean> five_test = newLifeMuBean.getFive_test();

                    Log.d(TAG, "five_test.size():" + five_test.size());
                    fiveTestBeanList.addAll(five_test);

                    //                            muAdapter = new NewLifeMuAdapter(five_test, getContext());
                    //                            newLifeRecycler.setAdapter(muAdapter);


                    adapterTwo.setNewOnitemClick(new NewLifeMuAdapterTwo.NewOnitemClick() {
                        @Override
                        public void onItemClick(int position) {
                            NewLifeMuBean.FiveTestBean fiveTestBean = fiveTestBeanList.get(position);

                            WebViewUtils.LifeNewWeb(getContext(),
                                    fiveTestBean.getVote_link(), fiveTestBean.getTest_name(), fiveTestBean.getTest_link());

                        }
                    });

                    newLifeRecycler.setAdapter(adapterTwo);
                    adapterTwo.notifyDataSetChanged();

                }

            }

            @Override
            public void onError(String error) {

            }
        });

    }


    private void ChangeImage(int time) {
        switch (time) {
            case 1:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zujueyinganjing_4man));
                JingMaiMBut.setText("足厥阴肝经当令");
                background.setBackgroundResource(R.drawable.yinganjing);

                break;
            case 2:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zujueyinganjing_4man));
                JingMaiMBut.setText("足厥阴肝经当令");
                background.setBackgroundResource(R.drawable.yinganjing);
                break;

            case 3:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoutaiyinfeijing_4man));
                JingMaiMBut.setText("手太阴肺经当令");
                background.setBackgroundResource(R.drawable.yinfeijing);

                break;

            case 4:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoutaiyinfeijing_4man));
                JingMaiMBut.setText("手太阴肺经当令");

                background.setBackgroundResource(R.drawable.yinfeijing);

                break;

            case 5:

                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shouyangmingdachangjing_4man));
                JingMaiMBut.setText("手阳明大肠经当令");

                background.setBackgroundResource(R.drawable.dachangjing);

                break;
            case 6:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shouyangmingdachangjing_4man));
                JingMaiMBut.setText("手阳明大肠经当令");

                background.setBackgroundResource(R.drawable.dachangjing);

                break;

            case 7:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zuyangmingweijing_4man));
                JingMaiMBut.setText("足阳明胃经当令");
                background.setBackgroundResource(R.drawable.mingweijing);


                break;

            case 8:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zuyangmingweijing_4man));
                JingMaiMBut.setText("足阳明胃经当令");

                background.setBackgroundResource(R.drawable.mingweijing);

                break;

            case 9:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zutaiyinpijing_4man));
                JingMaiMBut.setText("足太阴脾经当令");

                background.setBackgroundResource(R.drawable.yinpijing);

                break;

            case 10:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zutaiyinpijing_4man));
                JingMaiMBut.setText("足太阴脾经当令");

                background.setBackgroundResource(R.drawable.yinpijing);

                break;

            case 11:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoushaoyinxinjing_4man));
                JingMaiMBut.setText("手少阴心经当令");
                background.setBackgroundResource(R.drawable.yinxinjing);

                break;

            case 12:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoushaoyinxinjing_4man));
                JingMaiMBut.setText("手少阴心经当令");
                background.setBackgroundResource(R.drawable.yinxinjing);

                break;

            case 13:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoutaiyangxiaochangjing_4man));
                JingMaiMBut.setText("手太阳小肠经当令");
                background.setBackgroundResource(R.drawable.xiaochangjing);

                break;

            case 14:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoutaiyangxiaochangjing_4man));
                JingMaiMBut.setText("手太阳小肠经当令");

                background.setBackgroundResource(R.drawable.xiaochangjing);

                break;

            case 15:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zutaiyangpangguangjing_4man));
                JingMaiMBut.setText("足太阳膀胱经当令");
                background.setBackgroundResource(R.drawable.pangguang);

                break;

            case 16:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zutaiyangpangguangjing_4man));
                JingMaiMBut.setText("足太阳膀胱经当令");
                background.setBackgroundResource(R.drawable.pangguang);


                break;

            case 17:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zushaoyinshenjing_4man));
                JingMaiMBut.setText("足少阴肾经当令");
                background.setBackgroundResource(R.drawable.yinshenjing);

                break;

            case 18:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zushaoyinshenjing_4man));
                JingMaiMBut.setText("足少阴肾经当令");

                background.setBackgroundResource(R.drawable.yinshenjing);

                break;

            case 19:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoujueyinxinbaojing_4man));
                JingMaiMBut.setText("手厥阴心包经当令");
                background.setBackgroundResource(R.drawable.yinxinbao);

                break;
            case 20:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoujueyinxinbaojing_4man));
                JingMaiMBut.setText("手厥阴心包经当令");
                background.setBackgroundResource(R.drawable.yinxinbao);

                break;

            case 21:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoushaoyangsanjiaojing_4man));
                JingMaiMBut.setText("手少阳三焦经当令");

                background.setBackgroundResource(R.drawable.sanjiaojing);

                break;

            case 22:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoushaoyangsanjiaojing_4man));
                JingMaiMBut.setText("手少阳三焦经当令");

                background.setBackgroundResource(R.drawable.sanjiaojing);

                break;
            case 23:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zushaoyangdanjing_4man));
                JingMaiMBut.setText("足少阳胆经当令");
                background.setBackgroundResource(R.drawable.yangdanjing);

                break;


            case 24:
                ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zushaoyangdanjing_4man));
                JingMaiMBut.setText("足少阳胆经当令");

                background.setBackgroundResource(R.drawable.yangdanjing);

                break;


        }


    }

    /**
     * 进行播放音乐
     */
    private void PlayMusic() {
        try {
            if (five_result.equals("")) {
                Toast.makeText(getContext(), "请先做测试题", Toast.LENGTH_SHORT).show();
            } else if (MusicFlag == 0) {

                switch (five_result) {
                    case "jin":
                        String url = "http://music.heyishenghuo.com/jin.mp3";
                        initPlay(url);

                        break;
                    case "mu":
                        String muurl = "http://music.heyishenghuo.com/mu.mp3";

                        initPlay(muurl);

                        break;
                    case "shui":
                        String Shuiurl = "http://music.heyishenghuo.com/shui.mp3";

                        initPlay(Shuiurl);

                        break;
                    case "huo":
                        String huourl = "http://music.heyishenghuo.com/huo.mp3";

                        initPlay(huourl);

                        break;
                    case "tu":
                        String Tuurl = "http://music.heyishenghuo.com/tu.mp3";

                        initPlay(Tuurl);

                        break;

                }

                MusicFlag = 1;
            } else {

                initStop();
                MusicFlag = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //点击任务图片做的操作


    private void mAliasPopWindow() {
        backgroundAlpha(0.4f);


        TextView JingMaiTime = (TextView) view.findViewById(R.id.jingmai_time_ppw);


        Button but = (Button) view.findViewById(R.id.jingmai_but_ppw);


        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        popupWindow.setOutsideTouchable(false);

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);
        popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
        //popupwindow的弹出位置nnnnnn
        popupWindow.showAtLocation(getActivity().findViewById(R.id.mLinear), Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(new poponDismissListener());

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    backgroundAlpha(1.0f);

                }
            }
        });

    }

    /**
     * 位于组件正下方显示
     */
    private void wuxingImageppw() {
        downLinear.removeAllViews();
        //        linearLayout.removeAllViews();
        backgroundAlpha(0.8f);
        //        View mView = LayoutInflater.from(getContext())
        //                .inflate(R.layout.activity_newlife_ceshiti_ppw, null);


        ppwDown = new PopupWindow(mDownView, ViewGroup.LayoutParams.MATCH_PARENT
                , 100, true);
        ppwDown.setBackgroundDrawable(new ColorDrawable());

        ppwDown.setOutsideTouchable(true);

        ppwDown.setFocusable(true);
        ppwDown.setClippingEnabled(false);
        ppwDown.setOnDismissListener(new poponDismissListener());
        //        ppw.showAtLocation(view,Gravity.NO_GRAVITY,loca);

    }

    //这是自定义位置显示 左上右下
    private void wuxingImageppwlife(View v) {
        linearLayout.removeAllViews();
        //        downLinear.removeAllViews();
        backgroundAlpha(0.8f);

        ppw = new PopupWindow(mView, ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT, true);

        ppw.setBackgroundDrawable(new ColorDrawable());

        ppw.setOutsideTouchable(true);

        ppw.setFocusable(true);
        ppw.setClippingEnabled(false);

        ppw.setOnDismissListener(new poponDismissListener());


    }


    /**
     * popupwindow的内部类
     */
    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = App.activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        App.activity.getWindow().setAttributes(lp);


    }


    /**
     * 扫描动画
     */
    private void ImageAnimator() {
        float translationY = ManAnimatorImage.getTranslationY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(ManAnimatorImage, "TranslationY", translationY, 500f, translationY);
        animator.setDuration(5000).setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();

    }


    /**
     * 获取最上方的头像
     */
    public void MainUserImage() {
        String user_name = AppUtils.get().getString("user_name", "");
        String user_image = AppUtils.get().getString("user_image", "");


        if (user_name.equals("")) {
            LifeUserName.setText("注册/登录");

        } else {
            LifeUserName.setText(user_name);
        }


        Glide.with(getContext())
                .load(user_image)
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.touxiang_nan_man_4x)
                .error(R.drawable.touxiang_nan_man_4x)
                .into(new BitmapImageViewTarget(LifeUserImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        try {
                            LifeUserImage.setImageDrawable(circularBitmapDrawable);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                });


        /**
         *  这是判断右上角头像在没登录是跳登录，否则跳个人中心
         */
        LifeUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user_id.equals("") || token.equals("")) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);

                } else {

                    EventIntentUserBean bean = new EventIntentUserBean();
                    bean.setUserIntent("跳转");
                    EventBus.getDefault().postSticky(bean);

                }

            }
        });

    }

    //    得到经络文案
    private void getJingLuoArtircle() {

        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);
        String token = AppUtils.get().getString("token", "");
        String aesUser = null;
        String aesToken = null;

        String twoToken = user_id + "," + token + "," + timestamp;

        try {
            if (!user_id.equals("") && !token.equals("")) {
                aesUser = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

                aesToken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "GetChannels");
        params.put("user_id", aesUser);
        params.put("token", aesToken);


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "经络文章的    " + data);

                Gson gson = new Gson();
                try {
                    JingluoArticleBean Bean = gson.fromJson(data, JingluoArticleBean.class);
                    if (Bean.getStatus().equals("200")) {


                        textAdapter = new HeHeLifeTextAdapter(getContext(), Bean);
                        listView.setAdapter(textAdapter);

                        //

                    }


                } catch (JsonSyntaxException e) {


                }


            }

            @Override
            public void onError(String error) {

            }
        });

    }

    /**
     * 开始播放音乐
     *
     * @param stringExtra 声音文件从网络获取
     */
    private void initPlay(String stringExtra) {


        Uri uri1;
        if (stringExtra != null) {
            uri1 = Uri.parse(stringExtra);
        } else {
            uri1 = Uri.parse(stringExtra);
        }
        try {
            mediaPlayer.setDataSource(getContext(), uri1);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    Log.e("MusicReceiver", "a");
                    mp.start();
                }
            });
            lifeShengyinImage.setImageResource(R.drawable.wylfon4x);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 暂停播放
     */
    private void initStop() {

        try {
            mediaPlayer.pause();
            mediaPlayer.reset();
            lifeShengyinImage.setImageResource(R.drawable.wylfoff4x);
            mediaPlayer.stop();

        } catch (IllegalStateException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void onDestroy() {

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {

            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
        if (timer != null) {
            timer.cancel();

        }
    }


    private void noClickImage() {

        newImageMu.setImageResource(R.drawable.mu_4x);
        newImageJin.setImageResource(R.drawable.jin_4x);
        newImageShui.setImageResource(R.drawable.shui_4x);
        newImageHuo.setImageResource(R.drawable.huo_4x);
        newImageTu.setImageResource(R.drawable.tu_4x);

    }



    /**
     * popupwindow 实现关闭事件监听
     */
    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);

            noClickImage();
        }

    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        try {
            Resources resource = getActivity().getApplication().getResources();
            int resourceId = resource.getIdentifier("status_bar_height", "dimen", "Android");
            if (resourceId != 0) {
                return resource.getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
        }
        return 0;
    }

}
