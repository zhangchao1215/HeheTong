package com.example.heyikun.heheshenghuo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
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
import com.example.heyikun.heheshenghuo.controller.activity.SmallSettingActivity;
import com.example.heyikun.heheshenghuo.controller.activity.ZhenDuanActivity;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.HeHeLifeTextAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.LifeActicleAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.LifeGvManAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.LifeRecyckerAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.ShouYeFragmentAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.SlideshowAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeYangshenBean;
import com.example.heyikun.heheshenghuo.modle.bean.JingluoArticleBean;
import com.example.heyikun.heheshenghuo.modle.bean.LifeActicleBean;
import com.example.heyikun.heheshenghuo.modle.bean.MainBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EventIntentUserBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.NoScrollViewPager;
import com.example.heyikun.heheshenghuo.modle.util.PullDownElasticImp;
import com.example.heyikun.heheshenghuo.modle.util.PullDownScrollView;
import com.example.heyikun.heheshenghuo.modle.util.SPUtils;
import com.example.heyikun.heheshenghuo.modle.util.TabUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.text.ParsePosition;
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

import static com.example.heyikun.heheshenghuo.modle.util.AESUtils.Encrypt;
import static com.youth.banner.Banner.CIRCLE_INDICATOR_TITLE;

public class HeHeLifeFragment extends BaseFragment implements PullDownScrollView.RefreshListener {
    private static final String TAG = "HeHeLifeFragment";
    @BindView(R.id.Life_UserImage)
    ImageView LifeUserImage;
    @BindView(R.id.Life_UserName)
    TextView LifeUserName;
    @BindView(R.id.Man_SaoMiao_Image)
    TextView ManSaoMiaoImage;
    @BindView(R.id.Man_LeftImage)
    ImageView ManLeftImage;
    @BindView(R.id.Man_AnimatorImage)
    ImageView ManAnimatorImage;
    @BindView(R.id.mImage_VipLink)
    ImageView mImageVipLink;
    @BindView(R.id.mTabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.Main_ImageSmallSetting)
    ImageView MainImageSmallSetting;
    @BindView(R.id.Life_Viewpager)
    NoScrollViewPager LifeViewpager;
    @BindView(R.id.Life_Text_dingyou_year)
    TextView LifeTextDingyouYear;
    @BindView(R.id.Life_Text_nongli)
    TextView LifeTextNongli;
    @BindView(R.id.Life_Text_day)
    TextView LifeTextDay;
    @BindView(R.id.Life_Text_Year)
    TextView LifeTextYear;
    @BindView(R.id.Life_Image_Yundongsuggest)
    ImageView LifeImageYundongsuggest;
    @BindView(R.id.Life_Text_YundongSuggest)
    TextView LifeTextYundongSuggest;
    @BindView(R.id.Life_Text_lidong)
    TextView LifeTextLidong;
    @BindView(R.id.Life_Image_Eatsuggest)
    ImageView LifeImageEatsuggest;
    @BindView(R.id.Life_Text_eatSuggest)
    TextView LifeTextEatSuggest;
    @BindView(R.id.Life_Text_eat)
    TextView LifeTextEat;
    @BindView(R.id.mImage_Eit)
    ImageView mImageEit;
    @BindView(R.id.mText_eat)
    TextView mTextEat;
    @BindView(R.id.mText_eatConent)
    TextView mTextEatConent;
    @BindView(R.id.mImage_Tizhi)
    ImageView mImageTizhi;
    @BindView(R.id.mText_Tizhi)
    TextView mTextTizhi;
    @BindView(R.id.mText_TizhiContent)
    TextView mTextTizhiContent;
    @BindView(R.id.mLifeRecyclerView)
    RecyclerView mLifeRecyclerView;
    @BindView(R.id.mScrollview_Linear)
    LinearLayout mScrollviewLinear;
    @BindView(R.id.mScrollview)
    ScrollView mScrollview;
    Unbinder unbinder;
    @BindView(R.id.Life_year_relative)
    RelativeLayout LifeYearRelative;
    @BindView(R.id.Life_jianyi_relative)
    RelativeLayout LifeJianyiRelative;
    @BindView(R.id.Life_edt_relative)
    RelativeLayout LifeEdtRelative;

    @BindView(R.id.life_gridview)
    GridView lifeRecyclerview;
    @BindView(R.id.ceshi_result)
    TextView ceshiResult;
    @BindView(R.id.new_tab)
    TabLayout newTab;

    @BindView(R.id.JingMai_mBut)
    Button JingMaiMBut;
    @BindView(R.id.new_life_linear_Yangcai)
    RelativeLayout YangcaiRelative;
    @BindView(R.id.new_life_image_money_zhuanqian)
    ImageView newLifeImageMoneyZhuanqian;
    @BindView(R.id.new_life_money_quanqian)
    LinearLayout newLifeMoneyQuanqian;
    @BindView(R.id.new_life_image_money_licaiyi)
    ImageView newLifeImageMoneyLicaiyi;
    @BindView(R.id.new_life_money_licai)
    LinearLayout newLifeMoneyLicai;
    @BindView(R.id.new_life_image_money_licaiEr)
    ImageView newLifeImageMoneyLicaiEr;
    @BindView(R.id.new_life_money_zhichang)
    LinearLayout newLifeMoneyZhichang;
    @BindView(R.id.new_life_money_yiyuzheng)
    LinearLayout newLifeMoneyYiyuzheng;
    @BindView(R.id.new_life_money_touzi)
    LinearLayout newLifeMoneyTouzi;
    @BindView(R.id.new_life_money_wuxing)
    LinearLayout newLifeMoneyWuxing;
    @BindView(R.id.life_vote_copy)
    TextView lifeVoteCopy;
    @BindView(R.id.relative2)
    RelativeLayout relative2;
    @BindView(R.id.life_shengyinImage)
    ImageView lifeShengyinImage;
    @BindView(R.id.Image_viewpager)
    Banner ImageViewpager;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.loadMall_relative)
    RelativeLayout loadMallRelative;
    @BindView(R.id.refresh_root)
    PullDownScrollView refreshRoot;
    @BindView(R.id.EmptyView1)
    LinearLayout EmptyView1;
    private List<LifeActicleBean.DataBean> ActicleList;
    private LifeActicleAdapter acticleAdapter;
    private List<String> strTabList;
    private List<Fragment> FragList;
    private List<View> viewList;
    private SlideshowAdapter slideshowAdapter;
    private ShouYeFragmentAdapter ShouYedapter;
    private int time; //得到系统时间，为整数int型
    private MainBean mainBean;
    private List<String> bannerList;
    private LifeGvManAdapter manAdapter;

    private String user_id;

    private Timer mTimer;
    private int Index = 100000;
    private final int START = 0;
    private final int END = 1;
    int num = 0;
    private boolean isContinue = true;
    private int pageIndex = 1;
    private PopupWindow popupWindow;
    //    private Handler handler = new Handler() {
    //        @Override
    //        public void handleMessage(Message msg) {
    //            super.handleMessage(msg);
    //
    //            switch (msg.what) {
    //                case START:
    //
    //                    try {
    //                        ImageViewpager.setCurrentItem(ImageViewpager.getCurrentItem() + 1);
    //                    } catch (Exception e) {
    //                        e.printStackTrace();
    //                    }
    //                    break;
    //                case END:
    //                    handler.removeMessages(START);
    //                    break;
    //            }
    //
    //        }
    //    };
    private List<HeHeYangshenBean.DataBean.BodyBean> body;
    private FragmentTransaction transaction;
    private String token;
    private String timestamp;
    private String five_result;
    private ProgressDialog dialog;
    private List<MainBean.DataBean.BannerAdBean> banner_ad;


    private void initList() {
        bannerList = new ArrayList<>();
        refreshRoot.setRefreshListener(this);
        PullDownElasticImp pullDownElasticImp = new PullDownElasticImp(getContext());
        pullDownElasticImp.TopColor(R.color.colorTextYangXIn);

        refreshRoot.setPullDownElastic(pullDownElasticImp);
        ImageViewpager.setBannerStyle(CIRCLE_INDICATOR_TITLE);
        ImageViewpager.isAutoPlay(true);
        ImageViewpager.setDelayTime(3000);
        ImageViewpager.setIndicatorGravity(Banner.CENTER);

    }

    @Override
    public void onRefresh(PullDownScrollView view) {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                refreshRoot.finishRefresh("");

                try {
                    bannerList.clear();
                    mList.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                MainRequest();

                if (LifeTabFlag == 0) {
                    YangShenDate("BodyTest");

                } else if (LifeTabFlag == 1) {
                    YangShenDate("HeartTest");
                } else if (LifeTabFlag == 2) {
                    YangShenDate("MoneyTest");
                }

                getArticleList();


            }
        }, 2500);

    }

    private HeHeLifeTextAdapter textAdapter;
    private String[] tabText = {"养身", "养心", "养财"};

    private String encryptUserId;
    private String mTokenTwo;
    private String encryptToken;

    private List<HeHeYangshenBean.DataBean.BodyBean> mList;
    private LifeRecyckerAdapter adapter;
    private MediaPlayer mediaPlayer;

    // TODO: 2017/9/25  找布局id
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    // TODO: 2017/9/25  加载网络数据
    @Override
    protected void initData() {
        //主页面请求
        MainRequest();

        //加载文章
        getArticleList();
        //加载更多
        RecyclerRefresh();

        //        EventBus.getDefault().register(this);

        mScrollview.smoothScrollTo(0, 20);


        //主页面的请求数据


        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

            }
        };

        Timer timer = new Timer(true);
        timer.schedule(timerTask, strToDateLong("15:10:00"));


        // 初始化引导图片列
        mTimer = new Timer();

        //        ImageViewpager.setCurrentItem(Index++);
        //
        //        ImageViewpager.setOnTouchListener(onTouchListener);
        //
        //        //定时器每隔三秒发送一次消息
        //        mTimer.schedule(new TimerTask() {
        //            @Override
        //            public void run() {
        //                if (isContinue) {
        //                    handler.sendEmptyMessage(START);
        //
        //                }
        //            }
        //        }, 4000, 5000);

        getJingLuoArtircle();

    }


    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    isContinue = false;
                    break;
                case MotionEvent.ACTION_UP:

                    break;

                default:
                    isContinue = true;
                    break;
            }

            return false;
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        MainUserImage();
        if (ActicleList != null) {
            ActicleList.clear();
        }

        if (LifeTabFlag == 0) {
            YangShenDate("BodyTest");

        } else if (LifeTabFlag == 1) {
            YangShenDate("HeartTest");
        } else if (LifeTabFlag == 2) {
            YangShenDate("MoneyTest");
        }

    }


    /**
     * string类型时间转换为date
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    private int LifeTabFlag = 0;

    // TODO: 2017/9/25 初始化的方法
    @Override
    protected void initView(View view) {
        mediaPlayer = new MediaPlayer();


        mList = new ArrayList<>();

        viewList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("HH mm ss", Locale.getDefault());
        String currentTime = sdf.format(new Date());


        initList();


        ManSaoMiaoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ZhenDuanActivity.class));
            }
        });
        TimerPerson();  //切换图片

        ImageAnimator(); //动画扫描

        init(); //加载viewpager

        // 给RecyclerView设置布局方向的，同时消除滑动阻塞感
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mLifeRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        mLifeRecyclerView.setHasFixedSize(true);
        mLifeRecyclerView.setNestedScrollingEnabled(false);

        mLifeRecyclerView.setLayoutManager(layoutManager);

        for (int i = 0; i < tabText.length; i++) {
            newTab.addTab(newTab.newTab().setText(tabText[i]));

        }


        newTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                if (position == 0) {
                    LifeTabFlag = 0;
                    YangShenDate("BodyTest");
                } else if (position == 1) {
                    LifeTabFlag = 1;
                    YangShenDate("HeartTest");

                } else if (position == 2) {
                    LifeTabFlag = 2;
                    YangShenDate("MoneyTest");

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }


    private void YangShenDate(final String action) {

        mList.clear();
        Map<String, String> params = new HashMap<>();
        //
        //        mUserId = AppUtils.get().getString("user_id", "");
        //
        //        mtoken = AppUtils.get().getString("token", "");


        Log.d(TAG, "有没有消除  " + token + "这是userID" + user_id);

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        String encryptUserId = null;
        String encryptToken = null;

        try {

            if (!user_id.equals("") && !token.equals("")) {

                encryptUserId = Encrypt(user_id, BaseUrl.AESKey);
                String mTokenTwo = user_id + "," + token + "," + timestamp;

                encryptToken = Encrypt(mTokenTwo, BaseUrl.AESKey);
            }
            //给userID 还有 生成二次token ，在进行AES加密

        } catch (Exception e) {
            e.printStackTrace();
        }

        params.put("action", action);
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "测试题   " + data);
                Gson gson = new Gson();
                //
                HeHeYangshenBean yangshenBean = null;
                try {
                    yangshenBean = gson.fromJson(data, HeHeYangshenBean.class);


                    if (yangshenBean == null || data == null) {
                        return;
                    }
                    if (yangshenBean.getStatus().equals("200")) {

                        YangcaiRelative.setVisibility(View.GONE);
                        relative2.setVisibility(View.VISIBLE);
                        body = yangshenBean.getData().getBody();

                        mList.addAll(body);
                        manAdapter = new LifeGvManAdapter(mList, getContext());
                        lifeRecyclerview.setAdapter(manAdapter);


                        final HeHeYangshenBean.DataBean.BodyBean bodyBean = body.get(6);

                        ceshiResult.setText(bodyBean.getVote_name());

                        lifeVoteCopy.setText(bodyBean.getVote_copy());

                        lifeVoteCopy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WebViewUtils.LifeNewWeb(getContext(), bodyBean.getVote_link(), bodyBean.getVote_name(), bodyBean.getTests_link());
                            }
                        });

                        ceshiResult.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                WebViewUtils.LifeNewWeb(getContext(), bodyBean.getVote_link(), bodyBean.getVote_name(), bodyBean.getTests_link());
                            }
                        });
                    } else if (yangshenBean.getStatus().equals("100")) {
                        SPUtils.remove(getContext(), "token");
                        SPUtils.remove(getContext(), "user_id");
                        SPUtils.clear(getContext());

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


        lifeRecyclerview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (token == null || user_id.equals("")) {

                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {

                    HeHeYangshenBean.DataBean.BodyBean bodyBean = mList.get(position);

                    WebViewUtils.LifeNewWeb(getContext(), bodyBean.getVote_link(), bodyBean.getVote_name(), bodyBean.getTests_link());

                    //                    Intent intent = new Intent(getContext(), NewLifeWebview.class);
                    //                    intent.putExtra("title", bodyBean.getVote_name());
                    //                    intent.putExtra("url", bodyBean.getVote_link());
                    //                    startActivityForResult(intent, 200);


                }
            }

        });

    }

    private int MusicFlag;

    /**
     * 播放声音文件的点击事件
     */
    @OnClick(R.id.life_shengyinImage)
    public void onViewClicked() {


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

    //    private void initPlay(int music) {
    //
    //
    //        mp = MediaPlayer.create(getContext(), music);
    //
    //        try {
    //
    //
    //            if (mp.isPlaying()) {
    //
    //                mp.stop();
    //                mp.release();
    //                mp = MediaPlayer.create(getContext(), R.raw.baixue_jin);
    //            }
    //            mp.start();
    //            lifeShengyinImage.setImageResource(R.drawable.wylfon4x);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //
    //    }

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


    //    private void initStop() {
    //        try {
    //            mp.pause();
    //            mp.reset();
    //
    //            lifeShengyinImage.setImageResource(R.drawable.wylfoff4x);
    //
    //        } catch (IllegalStateException e) {
    //            e.printStackTrace();
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //        }
    //        mp.stop();
    //
    //    }


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


    int tabFlag;


    // TODO: 2017/9/25 重写的监听事件
    @Override
    protected void initListener() {


        //小设置的点击事件,跳转页面
        MainImageSmallSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SmallSetting();
            }
        });


    }


    private void TimerPerson() {


        SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault());
        String currentTime = sdf.format(new Date());


        time = Integer.parseInt(currentTime);
        if (time < 17 && time >= 15) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zutaiyangpangguangjing_man));
            JingMaiMBut.setText("足太阳膀胱经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //                    PeopleImageClick(R.drawable.pangguang, "申时15：00 - 17:00");

                    mAliasPopWindow(R.drawable.pangguang, "申时15：00 - 17:00");

                }
            });


            /**
             * 经脉button的点击事件
             */
            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.pangguang, "申时15：00 - 17:00");


                }
            });

        }
        if (time >= 17 && time < 19) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zushaoyinshenjing_man));
            JingMaiMBut.setText("足少阴肾经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    //                    PeopleImageClick(R.drawable.yinshenjing, "酉时17：00 - 19：00");

                    mAliasPopWindow(R.drawable.yinshenjing, "酉时17：00 - 19：00");
                }
            });


            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.yinshenjing, "酉时17：00 - 19：00");
                }
            });
        }
        if (time < 21 && time >= 19) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoujueyinxinbaojing_4man));
            JingMaiMBut.setText("手厥阴心包经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAliasPopWindow(R.drawable.yinxinbao, "戌时19：00 - 21:00");

                }
            });


            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAliasPopWindow(R.drawable.yinxinbao, "戌时19：00 - 21:00");

                }
            });

        } else if (time < 23 && time >= 21) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoushaoyangsanjiaojing_4man));
            JingMaiMBut.setText("手少阳三焦经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.sanjiaojing, "亥时21：00 - 23：00");

                }
            });


            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ManLeftImage.setClickable(false);


                    mAliasPopWindow(R.drawable.sanjiaojing, "亥时21：00 - 23：00");

                }
            });


        } else if (time < 25 && time >= 23) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zushaoyangdanjing_man));
            JingMaiMBut.setText("足少阳胆经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAliasPopWindow(R.drawable.yangdanjing, "子时23：00 - 1：00");

                }
            });

            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.yangdanjing, "子时23：00 - 1：00");


                }
            });
        } else if (time < 3 && time >= 1) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zujueyinganjing_man));
            JingMaiMBut.setText("足厥阴肝经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAliasPopWindow(R.drawable.yinganjing, "丑时1：00 - 3：00");
                }
            });


            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.yinganjing, "丑时1：00 - 3：00");

                }
            });
        } else if (time < 5 && time >= 3) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoutaiyinfeijing_man));
            JingMaiMBut.setText("手太阴肺经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mAliasPopWindow(R.drawable.yinfeijing, "寅时3：00 - 5：00");

                }
            });

            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.yinfeijing, "寅时3：00 - 5：00");

                }
            });
        } else if (time < 7 && time >= 5) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shouyangmingdachangjing_4man));
            JingMaiMBut.setText("手阳明大肠经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.dachangjing, "卯时5：00 - 7：00");
                }
            });


            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.dachangjing, "卯时5：00 - 7：00");

                }
            });

        } else if (time < 9 && time >= 7) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zuyangmingweijing_4man));
            JingMaiMBut.setText("足阳明胃经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAliasPopWindow(R.drawable.mingweijing, "辰时7：00 - 9：00");


                }
            });


            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.mingweijing, "辰时7：00 - 9：00");

                }
            });
        } else if (time < 11 && time >= 9) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.zutaiyinpijing_man));
            JingMaiMBut.setText("足太阴脾经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAliasPopWindow(R.drawable.yinpijing, "巳时9：00 - 11：00");

                }
            });

            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.yinpijing, "巳时9：00 - 11：00");

                }
            });
        } else if (time < 13 && time >= 11) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoushaoyinxinjing_man));
            JingMaiMBut.setText("手少阴心经当令");

            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAliasPopWindow(R.drawable.yinxinjing, "午时11：00 - 13：00");


                }
            });


            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.yinxinjing, "午时11：00 - 13：00");

                }
            });
        } else if (time < 15 && time >= 13) {
            ManLeftImage.setImageDrawable(getResources().getDrawable(R.drawable.shoutaiyangxiaochangjing_man));
            JingMaiMBut.setText("手太阳小肠经当令");
            ManLeftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mAliasPopWindow(R.drawable.xiaochangjing, "未时13：00 - 15：00");


                }
            });

            JingMaiMBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    mAliasPopWindow(R.drawable.xiaochangjing, "未时13：00 - 15：00");

                }
            });
        }
    }


    //点击任务图片做的操作


    private void mAliasPopWindow(int image, String time) {
        backgroundAlpha(0.4f);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_jingmai_ppw, null);


        TextView JingMaiTime = (TextView) view.findViewById(R.id.jingmai_time_ppw);

        JingMaiTime.setText(time);

        RelativeLayout background = (RelativeLayout) view.findViewById(R.id.relative);

        background.setBackgroundResource(image);


        ListView listView = (ListView) view.findViewById(R.id.Jingmai_listview_ppw);

        Button but = (Button) view.findViewById(R.id.jingmai_but_ppw);

        dataTextAdapter(listView);

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
        popupWindow.showAtLocation(getActivity().findViewById(R.id.EmptyView1), Gravity.CENTER, 0, 0);

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
     * popupwindow的内部类
     */
    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = App.activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        App.activity.getWindow().setAttributes(lp);


    }


    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }


    //动画一直扫描
    private void ImageAnimator() {
        float translationY = ManAnimatorImage.getTranslationY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(ManAnimatorImage, "TranslationY", translationY, 700f, translationY);
        animator.setDuration(5000).setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.start();

    }


    private void init() {


        dialog = new ProgressDialog(App.activity);
        dialog.setMax(100);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("正在努力加载...");
        dialog.show();
        ActicleList = new ArrayList<>();
        strTabList = new ArrayList<>();
        FragList = new ArrayList<>();


        //设置Viewpager不可滑动
        LifeViewpager.setNoScroll(true);

        TabUtils.setIndicator(mTabLayout, 10, 10, 10);


        ShouYedapter = new ShouYeFragmentAdapter(getFragmentManager(), strTabList, FragList);

        LifeViewpager.setAdapter(ShouYedapter);

        LifeViewpager.setCurrentItem(0, true);

        mTabLayout.setupWithViewPager(LifeViewpager);

    }

    //跳转到小工具列表页面
    private void SmallSetting() {
        Intent intent = new Intent(getContext(), SmallSettingActivity.class);
        startActivity(intent);

    }


    // TODO: 2017/9/29  主页面的请求
    private void MainRequest() {
        user_id = AppUtils.get().getString("user_id", "");
        token = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        String tokenTwo = user_id + "," + token + "," + timestamp;

        String AEStoken = null;
        String encryptUserID = null;

        try {
            if (!user_id.equals("") && !token.equals("")) {

                encryptUserID = Encrypt(user_id, BaseUrl.AESKey);

                AEStoken = Encrypt(tokenTwo, BaseUrl.AESKey);

                Log.d(TAG, encryptUserID);

            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "Homepage");
        params.put("user_id", encryptUserID);
        params.put("token", AEStoken);

        Log.d(TAG, "加密的userID       " + encryptUserID);

        Log.d(TAG, "加密的token      " + AEStoken);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private MainBean mainBean;
            private MainBean.DataBean dataBean;

            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                Log.d(TAG, "首页数据" + data);
                Gson gson = new Gson();
                try {
                    mainBean = gson.fromJson(data, MainBean.class);
                    dataBean = mainBean.getData();
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Exception c) {
                    c.printStackTrace();
                }

                if (mainBean.getStatus().equals("200")) {

                    mScrollview.setVisibility(View.VISIBLE);

                    //音乐文件
                    five_result = dataBean.getFive_result();


                    banner_ad = dataBean.getBanner_ad();
                    //                    for (int i = 0; i < banner_ad.size(); i++) {
                    //                        View view = LayoutInflater.from(getContext())
                    //                                .inflate(R.layout.activity_faxian_carousel_item, null);
                    //
                    //                        ImageView imageView = (ImageView) view.findViewById(R.id.Faxian_mImage);
                    //
                    //                        Glide.with(getContext())
                    //                                .load(banner_ad.get(i).getBanner_pic())
                    //                                .centerCrop()
                    //                                .error(R.drawable.jfjz24x)
                    //                                .placeholder(R.drawable.vip_life)
                    //                                .into(imageView);
                    //
                    //                        final int FinalI = i;
                    //
                    //                        view.setOnClickListener(new View.OnClickListener() {
                    //
                    //                            private String banner_title;
                    //                            private String banner_link;
                    //
                    //                            @Override
                    //                            public void onClick(View v) {
                    //                                try {
                    //                                    banner_link = banner_ad.get(FinalI).getBanner_link();
                    //                                    banner_title = banner_ad.get(FinalI).getBanner_title();
                    //                                } catch (Exception e) {
                    //                                    e.printStackTrace();
                    //                                }
                    //                                WebViewUtils.publicWebView(getContext(), banner_link, banner_title);
                    //                            }
                    //                        });
                    //
                    //                        viewList.add(view);
                    //                    }

                    //viewPager的适配器加载轮播图
                    //                    slideshowAdapter = new SlideshowAdapter(viewList);
                    //                    ImageViewpager.setAdapter(slideshowAdapter);


                    for (MainBean.DataBean.BannerAdBean bannerAdBean : banner_ad) {
                        bannerList.add(bannerAdBean.getBanner_pic());
                    }


                    ImageViewpager.setOnBannerClickListener(new Banner.OnBannerClickListener() {
                        private String banner_title;
                        private String banner_link;

                        @Override
                        public void OnBannerClick(View view, int position) {

                            try {
                                banner_link = banner_ad.get(position - 1).getBanner_link();
                                banner_title = banner_ad.get(position - 1).getBanner_title();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            WebViewUtils.publicWebView(getContext(), banner_link, banner_title);
                        }


                    });

                    ImageViewpager.setImages(bannerList);

                    //获取农历阴历时间
                    final MainBean.DataBean.DatesBean dates = dataBean.getDates();

                    String gregorian = dates.getGregorian();//节气
                    String lunar = dates.getLunar();
                    LifeTextDingyouYear.setText(gregorian);

                    LifeTextDay.setText(dates.getSolar());  //多少号

                    LifeTextYear.setText(dates.getYear()); //年-月

                    LifeTextNongli.setText(lunar);//农历时间

                    LifeYearRelative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebViewUtils.publicWebView(getContext(), dates.getSolarlink() + "&deltitle=1", " ");
                        }
                    });

                    //今天吃什么的请求

                    final MainBean.DataBean.EatBean eat = dataBean.getEat();


                    LifeTextEat.setText(eat.getEat_copy());


                    Glide.with(getContext())
                            .load(eat.getEat_pic())
                            .placeholder(R.drawable.jcwz)
                            .centerCrop()
                            .into(LifeImageEatsuggest);

                    LifeEdtRelative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebViewUtils.publicWebView(getContext(), eat.getEat_link() + "&deltitle=1", "  ");
                        }
                    });


                    //运动建议的请求
                    final MainBean.DataBean.PhysiqueBean physique = dataBean.getPhysique();

                    LifeTextLidong.setText(physique.getBody_copy());

                    Glide.with(getContext())
                            .load(physique.getBody_pic())
                            .placeholder(R.drawable.jcwz)
                            .centerCrop()
                            .into(LifeImageYundongsuggest);

                    LifeJianyiRelative.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            WebViewUtils.publicWebView(getContext(), physique.getBody_link() + "&deltitle=1", " ");
                        }
                    });


                } else if (mainBean.getStatus().equals("100")) {
                    Toast.makeText(getContext(), mainBean.getMessage(), Toast.LENGTH_SHORT).show();
                    //                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    //                    startActivity(intent);
                    SPUtils.remove(getContext(), "token");
                    SPUtils.clear(getContext());

                }


            }

            @Override
            public void onError(String error) {

            }
        });

    }


    private void RecyclerRefresh() {
        //是否开启下拉刷新功能
        //        if (mScrollview != null) {
        //
        //            mScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
        //                @Override
        //                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        //
        //                    if (scrollY > oldScrollY) {
        //
        //                        if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
        //                            pageIndex++;
        //                            getArticleList();
        //                        }
        //                    }
        //                    if (scrollY < oldScrollY) {
        //
        //
        //                    }
        //
        //                    if (scrollY == 0) {
        //
        //                    }
        //
        //                    //                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
        //                    //                        Log.i(TAG, "BOTTOM SCROLL");
        //                    //                    }
        //                }
        //            });
        //        }
        mScrollview.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(final View v, MotionEvent event) {
                // TODO Auto-generated method stub
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        // getScrollY()是滚动条距离页面顶端的距离
                        // getMeasuredHeight()是屏幕顶端距离页面顶端的距离
                        // getHeight()是屏幕高度
                        int y = mScrollview.getScrollY();
                        if (y <= 0) {
                            Log.i("Main", "正在顶部");
                        } else if (mScrollview.getChildAt(0).getMeasuredHeight() <= y + mScrollview.getHeight()) {
                            Log.i("Main", "已经滚动到底部");
                            Log.i("Main", "MeasuredHeight:" + mScrollview.getMeasuredHeight() + ",ScrollY:" + y + ",Height:" + mScrollview.getHeight());
                            // 追加内容
                            //                            textView.append(getString(R.string.text));
                            loadMallRelative.setVisibility(View.VISIBLE);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    pageIndex++;
                                    getArticleList();
                                }
                            }, 2000);


                        }
                        break;
                }

                return false;
            }
        });

    }


    private void getArticleList() {
        Map<String, String> params = new HashMap<>();
        String user_id = AppUtils.get().getString("user_id", "");


        String token = AppUtils.get().getString("token", "");


        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);
        String encryptUserId = null;
        String encryptToken = null;
        try {

            String mToken = user_id + "," + token + "," + timestamp;
            if (!user_id.equals("") && !token.equals("")) {

                encryptUserId = Encrypt(user_id, BaseUrl.AESKey);
                encryptToken = Encrypt(mToken, BaseUrl.AESKey);
            }
            //给userID 还有 生成二次token ，在进行AES加密


        } catch (Exception e) {
            e.printStackTrace();
        }

        params.put("action", "GetArticle");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("pageNum", String.valueOf(pageIndex));
        params.put("pageSize", "8");
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private LifeActicleBean lifeActicleBean;

            @Override
            public void onSuccess(String data) {
                Log.d(TAG, "文章    6++" + data);
                Gson gson = new Gson();
                try {
                    lifeActicleBean = gson.fromJson(data, LifeActicleBean.class);


                    if (lifeActicleBean.getStatus().equals("200")) {
                        List<LifeActicleBean.DataBean> dataBean = lifeActicleBean.getData();
                        ActicleList.addAll(dataBean);
                        acticleAdapter = new LifeActicleAdapter(ActicleList, getContext());

                        mLifeRecyclerView.setAdapter(acticleAdapter);

                        loadMallRelative.setVisibility(View.GONE);
                    } else if (lifeActicleBean.getStatus().equals("100")) {

                        SPUtils.remove(getContext(), "token");
                        SPUtils.clear(getContext());
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


    private void dataTextAdapter(ListView listView) {

        //        textAdapter = new HeHeLifeTextAdapter(getContext(), str);
        //
        listView.setAdapter(textAdapter);

    }


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

                        String channels = Bean.getChannels();
                        textAdapter = new HeHeLifeTextAdapter(getContext(), Bean);
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
     * 获取用户姓名 和 头像
     */
    public void MainUserImage() {
        FragmentManager manager = getFragmentManager();
        transaction = manager.beginTransaction();
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


    @Override
    public void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {

            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();

        //                EventBus.getDefault().unregister(this);

        if (mTimer != null) {
            mTimer.cancel();

        }
        if (dialog.isShowing() || dialog != null) {
            dialog.dismiss();
        }
    }


}


