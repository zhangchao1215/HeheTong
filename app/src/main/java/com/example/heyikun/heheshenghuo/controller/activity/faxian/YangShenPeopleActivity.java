package com.example.heyikun.heheshenghuo.controller.activity.faxian;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.AgentWebActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.SlideshowAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.EditRecommendAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.HotMasterAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.YangshenArticleAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.YangshengHostLanmuAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.YangShenPeopleBean;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.DividerItemDecorationRecy;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by hyk on 2017/12/9.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/9
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 养生达人
 */

public class YangShenPeopleActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.Faxian_titleSeach)
    TextView FaxianTitleSeach;
    @BindView(R.id.sendArticle_image)
    ImageView sendArticleImage;
    @BindView(R.id.yangsheng_people_pager)
    ViewPager viewPager;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.host_lanmu_recycler)
    RecyclerView hostLanmuRecycler;
    @BindView(R.id.tuijian_recycler)
    RecyclerView tuijianRecycler;
    @BindView(R.id.host_recycler)
    RecyclerView hostRecycler;
    @BindView(R.id.article_recycler)
    RecyclerView articleRecycler;
    @BindView(R.id.Master_radioGroup)
    RadioGroup MasterRadioGroup;
    @BindView(R.id.mNestedScroll)
    NestedScrollView mNestedScroll;
    private int pageNum = 1;
    private List<YangShenPeopleBean.DataBean.ArticleBean> articleBeenList;
    private List<YangShenPeopleBean.DataBean.ColumnBean> columnBeanList;
    private List<YangShenPeopleBean.DataBean.HotMasterBean> hotMasterBeanList;
    private List<YangShenPeopleBean.DataBean.RecommendationBean> recommendationBeanList;
    private List<YangShenPeopleBean.DataBean.BannerBean> bannerBeanList;
    private YangshengHostLanmuAdapter lanmuAdapter;
    private EditRecommendAdapter recommendAdapter;
    private SlideshowAdapter slideshowAdapter;
    private HotMasterAdapter masterAdapter;
    private YangshenArticleAdapter articleAdapter;
    private List<View> viewList;
    private Timer timer;
    private int Index = 100000;
    private final int START = 0;
    private final int END = 1;
    private boolean isContinue = true;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START:
                    try {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    break;
                case END:
                    handler.removeMessages(START);


                    break;
            }
        }
    };
    private RadioButton radioButton;
    private List<YangShenPeopleBean.DataBean.BannerBean> banner;

    @Override
    protected int layoutId() {
        return R.layout.activity_yangshen_peopel;
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void initView() {
        viewList = new ArrayList<>();
        articleBeenList = new ArrayList<>();
        columnBeanList = new ArrayList<>();
        hotMasterBeanList = new ArrayList<>();
        recommendationBeanList = new ArrayList<>();
        bannerBeanList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.HORIZONTAL);
        hostLanmuRecycler.setLayoutManager(manager);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        tuijianRecycler.setHasFixedSize(true);
        tuijianRecycler.setNestedScrollingEnabled(false);
        tuijianRecycler.addItemDecoration(new DividerItemDecorationRecy(this, DividerItemDecorationRecy.VERTICAL_LIST));
        tuijianRecycler.setLayoutManager(layoutManager);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        hostRecycler.setLayoutManager(linearLayoutManager);


        LinearLayoutManager articleManager = new LinearLayoutManager(this);
        articleManager.setSmoothScrollbarEnabled(true);
        articleManager.setAutoMeasureEnabled(true);
        articleRecycler.setHasFixedSize(true);
        articleRecycler.setNestedScrollingEnabled(false);
        articleRecycler.addItemDecoration(new DividerItemDecorationRecy(this, DividerItemDecorationRecy.VERTICAL_LIST));
        articleRecycler.setLayoutManager(articleManager);


        // 初始化引导图片列
        timer = new Timer();

        viewPager.setCurrentItem(Index++);

        viewPager.setOnTouchListener(onTouchListener);

        //定时器每隔三秒发送一次消息
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isContinue) {
                    handler.sendEmptyMessage(START);

                }
            }
        }, 4000, 5000);

    }

    @Override
    protected void initData() {
        lodaData();
        RecyclerRefresh();
    }


    @Override
    protected void initLisenter() {


    }


    private void RecyclerRefresh() {
        //是否开启下拉刷新功能
        if (mNestedScroll != null) {

            mNestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY > oldScrollY) {

                        if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                            pageNum++;
                            lodaData();
                        }
                    }
                    if (scrollY < oldScrollY) {


                    }

                    if (scrollY == 0) {

                    }

                    //                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    //                        Log.i(TAG, "BOTTOM SCROLL");
                    //                    }
                }
            });
        }

    }

    private void lodaData() {
        columnBeanList.clear();
        recommendationBeanList.clear();
        hotMasterBeanList.clear();
        viewList.clear();
        bannerBeanList.clear();
        MasterRadioGroup.removeAllViews();
        Map<String, String> params = new HashMap<>();
        params.put("action", "MasterPage");
        params.put("pageNum", String.valueOf(pageNum));
        params.put("pageSize", "8");
        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<YangShenPeopleBean>() {
                    @Override
                    public void onSuccess(int statusCode, YangShenPeopleBean response) {
                        final YangShenPeopleBean.DataBean dataBean = response.getData();

                        if (response.getStatus().equals("200")) {

                            //轮播图
                            banner = dataBean.getBanner();
                            bannerBeanList.addAll(banner);


                            for (int i = 0; i < bannerBeanList.size(); i++) {
                                View view = LayoutInflater.from(YangShenPeopleActivity.this).inflate(R.layout.activity_faxian_carousel_item, null);

                                ImageView image = (ImageView) view.findViewById(R.id.Faxian_mImage);

                                Glide.with(YangShenPeopleActivity.this)
                                        .load(bannerBeanList.get(i).getBanner_pic())
                                        .placeholder(R.drawable.jfjz24x)
                                        .centerCrop()
                                        .into(image);

                                final int FinalI = i;
                                image.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        WebViewUtils.publicWebView(YangShenPeopleActivity.this, bannerBeanList.get(FinalI).getBanner_link(), bannerBeanList.get(FinalI).getBanner_name());

                                    }
                                });
                                viewList.add(view);

                                radioButton = new RadioButton(YangShenPeopleActivity.this);

                                radioButton.setBackgroundResource(R.drawable.lunboimage_radio);

                                radioButton.setButtonDrawable(null);
                                RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 20);

                                layoutParams.setMargins(10, 0, 10, 0);

                                radioButton.setLayoutParams(layoutParams);


                                if (i == 0) {
                                    radioButton.setBackgroundResource(R.drawable.dianxuanzhong_man_4x);
                                }
                                MasterRadioGroup.addView(radioButton);

                            }

                            slideshowAdapter = new SlideshowAdapter(viewList);
                            viewPager.setAdapter(slideshowAdapter);

                            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {

                                    for (int i = 0; i < bannerBeanList.size(); i++) {
                                        position = position % bannerBeanList.size();

                                        View view = MasterRadioGroup.getChildAt(i);
                                        view.setBackgroundResource(R.drawable.dianmeixuanzhong_man_4x);

                                        if (i == position) {
                                            view.setBackgroundResource(R.drawable.dianxuanzhong_man_4x);
                                        }

                                    }


                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {

                                }
                            });
                            //热门栏目

                            columnBeanList.addAll(dataBean.getColumn());
                            lanmuAdapter = new YangshengHostLanmuAdapter(columnBeanList, YangShenPeopleActivity.this);
                            hostLanmuRecycler.setAdapter(lanmuAdapter);


                            //编辑推荐

                            recommendationBeanList.addAll(dataBean.getRecommendation());
                            recommendAdapter = new EditRecommendAdapter(recommendationBeanList, YangShenPeopleActivity.this);

                            recommendAdapter.setOnItemClick(new EditRecommendAdapter.ImageOnItemClick() {
                                @Override
                                public void onImageClick(int position) {

                                    YangShenPeopleBean.DataBean.RecommendationBean bean = dataBean.getRecommendation().get(position);

                                    Intent intent = new Intent(YangShenPeopleActivity.this, MasterDetailActivity.class);
                                    intent.putExtra("master_id", bean.getMaster_id());
                                    startActivity(intent);


                                }
                            });

                            tuijianRecycler.setAdapter(recommendAdapter);

                            //热门达人

                            hotMasterBeanList.addAll(dataBean.getHot_master());
                            masterAdapter = new HotMasterAdapter(hotMasterBeanList, YangShenPeopleActivity.this);
                            hostRecycler.setAdapter(masterAdapter);


                            //文章

                            articleBeenList.addAll(dataBean.getArticle());
                            articleAdapter = new YangshenArticleAdapter(articleBeenList, YangShenPeopleActivity.this);
                            articleAdapter.setImageClikLisenter(new YangshenArticleAdapter.ImageClikLisenter() {
                                @Override
                                public void imageClickListener(int position) {

                                    YangShenPeopleBean.DataBean.ArticleBean articleBean = dataBean.getArticle().get(position);
                                    Intent intent = new Intent(YangShenPeopleActivity.this, MasterDetailActivity.class);
                                    intent.putExtra("master_id", articleBean.getMaster_id());
                                    startActivity(intent);

                                }
                            });
                            articleRecycler.setAdapter(articleAdapter);


                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }

    //加载轮播图
    private void lunBoImage(final YangShenPeopleBean.DataBean dataBean) {

        //        //轮播图
        //        banner = dataBean.getBanner();
        //        for (int i = 0; i < banner.size(); i++) {
        //            View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_faxian_carousel_item, null);
        //
        //            ImageView image = (ImageView) view.findViewById(R.id.Faxian_mImage);
        //
        //            Glide.with(getContext())
        //                    .load(banner.get(i).getBanner_pic())
        //                    .placeholder(R.drawable.jfjz24x)
        //                    .centerCrop()
        //                    .into(image);
        //
        //            final int FinalI = i;
        //            image.setOnClickListener(new View.OnClickListener() {
        //                @Override
        //                public void onClick(View v) {
        //                    WebViewUtils.publicWebView(getContext(), banner.get(FinalI).getBanner_link(), banner.get(FinalI).getBanner_name());
        //
        //                }
        //            });
        //            viewList.add(view);
        //
        //            radioButton = new RadioButton(getContext());
        //
        //            radioButton.setBackgroundResource(R.drawable.lunboimage_radio);
        //
        //            radioButton.setButtonDrawable(null);
        //            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 20);
        //
        //            layoutParams.setMargins(10, 0, 10, 0);
        //
        //            radioButton.setLayoutParams(layoutParams);
        //
        //
        //            if (i == 0) {
        //                radioButton.setBackgroundResource(R.drawable.dianxuanzhong_man_4x);
        //            }
        //            MasterRadioGroup.addView(radioButton);
        //
        //        }
        //
        //        slideshowAdapter = new SlideshowAdapter(viewList);
        //        viewPager.setAdapter(slideshowAdapter);
        //
        //
        //        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        //            @Override
        //            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //
        //            }
        //
        //            @Override
        //            public void onPageSelected(int position) {
        //
        //                for (int i = 0; i < banner.size(); i++) {
        //                    position = position % banner.size();
        //
        //                    View view = MasterRadioGroup.getChildAt(i);
        //                    view.setBackgroundResource(R.drawable.dianmeixuanzhong_man_4x);
        //
        //                    if (i == position) {
        //                        view.setBackgroundResource(R.drawable.dianxuanzhong_man_4x);
        //                    }
        //
        //                }
        //
        //            }
        //
        //            @Override
        //            public void onPageScrollStateChanged(int state) {
        //
        //            }
        //        });

    }
    //轮播图的切换监听


    @OnClick({R.id.Image_Back, R.id.Faxian_titleSeach, R.id.sendArticle_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();


                break;
            case R.id.Faxian_titleSeach:

                final String url = "https://hehe.heyishenghuo.com/mobile2/app2/public/search.php?";

                WebViewUtils.publicWebView(YangShenPeopleActivity.this, url + "search=3", "搜索");


                break;
            case R.id.sendArticle_image:

                String mUserId = AppUtils.get().getString("user_id", "");


                String mtoken = AppUtils.get().getString("token", "");

                //获取时间戳
                String currentTime_today = TimeUtils.getCurrentTime_Today();
                String timestamp = TimeUtils.dataOne(currentTime_today);

                Log.d("HeHeUserFragment", "时间戳+++" + timestamp);


                String token = mUserId + "," + mtoken + "," + timestamp;


                final String sendurl = "http://hehe.heyishenghuo.com/mobile2/app2/health_talent/health-posted.php";

                String loadUrl = sendurl + "?user_id=" + mUserId + "&token=" + token + "&from=1";

                //                Intent intent = new Intent();
                //                intent.setAction("android.intent.action.VIEW");
                //                Uri content_url = Uri.parse(loadUrl);
                //                intent.setData(content_url);
                //                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //                startActivity(intent);

                //                                WebViewUtils.publicWebTwo(getContext(), sendurl, "发布文章");

                Intent intent = new Intent(this, AgentWebActivity.class);
//                intent.putExtra("url", sendurl);
                startActivity(intent);

                break;
        }
    }

    /**
     * 根据当前触摸事件判断是否要轮播
     */
    View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                //手指按下和划动的时候停止图片的轮播
                case MotionEvent.ACTION_DOWN:

                case MotionEvent.ACTION_MOVE:
                    isContinue = false;
                    break;
                default:
                    isContinue = true;
            }
            return false;//注意这里只能返回false,如果返回true，Dwon就会消费掉事件，MOVE无法获得事件，
            // 导致图片无法滑动

        }

    };



}
