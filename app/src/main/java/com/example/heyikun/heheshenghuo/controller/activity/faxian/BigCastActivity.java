package com.example.heyikun.heheshenghuo.controller.activity.faxian;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.crazysunj.cardslideview.CardViewPager;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BigCastAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.BigCastLunboRecyclerAdaoter;
import com.example.heyikun.heheshenghuo.controller.adapter.SlideshowAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastBean;
import com.example.heyikun.heheshenghuo.modle.util.ClipViewPager;
import com.example.heyikun.heheshenghuo.modle.util.DividerItemDecorationRecy;
import com.example.heyikun.heheshenghuo.modle.util.RecyclingPagerAdapter;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.alipay.sdk.app.statistic.c.e;
import static com.alipay.sdk.app.statistic.c.v;

/**
 * Created by hyk on 2017/11/18.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/18
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class BigCastActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.bigcast_search)
    ImageView bigcastSearch;
    @BindView(R.id.bigcast_LookAll)
    TextView bigcastLookAll;
    @BindView(R.id.bigcast_Tab)
    TabLayout bigcastTab;
    @BindView(R.id.Bigcast_recyeler)
    RecyclerView BigcastRecyeler;

    @BindView(R.id.Linear)
    LinearLayout Linear;
    @BindView(R.id.card_viewpager)
    CardViewPager viewpager;

    private BigCastLunboRecyclerAdaoter adaoter;
    private List<BigCastBean.DataBean.RecommendBean> mRecommendList;
    private List<BigCastBean.DataBean.ArticlesBean.ArticleBean> articleList;
    private BigCastAdapter bigCastAdapter;
    private BigCastBean.DataBean dataBean;
    private List<BigCastBean.DataBean.ArticlesBean> articles;
    private List<BigCastBean.DataBean.RecommendBean> viewList;
    private List<View> viewImageList;
    private SlideshowAdapter viewpageAdapter;
    private int width;
    private int height;
    private int TabFlag = 0;

    @Override
    protected int layoutId() {
        return R.layout.activity_bigcast;
    }

    @Override
    protected void initView() {
        viewList = new ArrayList<>();
        mRecommendList = new ArrayList<>();
        articleList = new ArrayList<>();
        viewImageList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        BigcastRecyeler.addItemDecoration(new DividerItemDecorationRecy(this, DividerItemDecorationRecy.VERTICAL_LIST));

        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        BigcastRecyeler.setHasFixedSize(true);
        BigcastRecyeler.setNestedScrollingEnabled(false);

        BigcastRecyeler.setLayoutManager(manager);
        //拿取屏幕的宽和高
        WindowManager wn = getWindowManager();
        DisplayMetrics dm = new DisplayMetrics();
        wn.getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;

        //设置ViewPager的LayoutParams

        //设置ViewPager的PageTransformer

        //将外部大容器的触摸事件委托给ViewPager处理
        //        Linear.setOnTouchListener(new View.OnTouchListener() {
        //            @Override
        //            public boolean onTouch(View view, MotionEvent motionEvent) {
        //                return viewpager.dispatchTouchEvent(motionEvent);
        //            }
        //        });
    }

    //在一进入的时候就刷新页面
    @Override
    protected void onResume() {
        super.onResume();

        if (TabFlag == 0) {
            BigCastRequest(0);

        } else if (TabFlag == 1) {
            BigCastRequest(1);

        } else if (TabFlag == 2) {
            BigCastRequest(2);

        } else if (TabFlag == 3) {
            BigCastRequest(3);

        } else if (TabFlag == 4) {
            BigCastRequest(4);

        }
    }

    @Override
    protected void initData() {
        //        BigCastRequest(0);

        initTab();

    }


    private void initTab() {
        bigcastTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();


                if (position == 0) {

                    ArticleData(0);

                    TabFlag = 0;
                }
                if (position == 1) {
                    ArticleData(1);
                    TabFlag = 1;
                }
                if (position == 2) {
                    ArticleData(2);
                    TabFlag = 2;
                }
                if (position == 3) {
                    ArticleData(3);
                    TabFlag = 3;
                }
                if (position == 4) {
                    ArticleData(4);
                    TabFlag = 4;
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

    @Override
    protected void initLisenter() {
        final String url = "https://hehe.heyishenghuo.com/mobile2/app2/public/search.php?";
        bigcastSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewUtils.publicWebView(BigCastActivity.this, url+"&search=3", "搜索");

            }
        });

    }

    private int flag = 0;

    private void BigCastRequest(final int position) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "BigcastPage");
        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<BigCastBean>() {
                    @Override
                    public void onSuccess(int statusCode, BigCastBean response) {

                        if (response.getStatus().equals("200")) {

                            dataBean = response.getData();
                            //TabLayout 的请求数据
                            bigcastTab.setTabMode(TabLayout.MODE_SCROLLABLE);

                            articles = dataBean.getArticles();

                            if (flag == 0) {
                                for (int i = 0; i < articles.size(); i++) {

                                    bigcastTab.addTab(bigcastTab.newTab().setText(dataBean.getArticles().get(i).getArticle_type()));

                                    flag = 1;
                                }
                            }
                            //最下面文章的数据
                            mRecommendList.addAll(dataBean.getRecommend());
                            viewpager.bind(getSupportFragmentManager(), new MyCardHandler(), mRecommendList);

                            ArticleData(position);

                            viewpager.setCardPadding(100);
                            viewpager.setCardMargin(10);
                            //                            viewpager.notifyUI(CardViewPager.MODE_CARD);


                            //                            bannerViewpager.setPages(viewList, new MZHolderCreator<BannerViewHolder>() {
                            //                                @Override
                            //                                public BannerViewHolder createViewHolder() {
                            //                                    return new BannerViewHolder();
                            //                                }
                            //                            });
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }

    //    public static class BannerViewHolder implements MZViewHolder<BigCastBean.DataBean.RecommendBean> {
    //        private ImageView mImageView;
    //
    //        @Override
    //        public View createView(Context context) {
    //            View view = LayoutInflater.from(context).inflate(R.layout.activity_bigcast_image, null);
    //            mImageView = (ImageView) view.findViewById(R.id.bigcast_lunbo_image);
    //            return view;
    //        }
    //
    //        @Override
    //        public void onBind(Context context, int position, BigCastBean.DataBean.RecommendBean data) {
    //
    //            Glide.with(context)
    //                    .load(data.getPeople_pic())
    //                    .into(mImageView);
    //        }
    //
    //
    //    }

    private void ArticleData(int position) {

        //
        articleList.clear();


        articleList.addAll(dataBean.getArticles().get(position).getArticle());
        bigCastAdapter = new BigCastAdapter(articleList, BigCastActivity.this);
        BigcastRecyeler.setAdapter(bigCastAdapter);

        bigCastAdapter.setOnClick(new BigCastAdapter.BigcastOnClick() {
            @Override
            public void OnClickBigcast(int position) {

                BigCastBean.DataBean.ArticlesBean.ArticleBean articleBean = articleList.get(position);

                Intent intent = new Intent(BigCastActivity.this, BigCastPeopleDetailActivity.class);
                intent.putExtra("id", articleBean.getBig_id());
                intent.putExtra("name", articleBean.getBig_name());
                intent.putExtra("image", articleBean.getArticle_pic());
                startActivity(intent);

            }
        });


    }


    @OnClick({R.id.Image_Back, R.id.bigcast_LookAll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();

                break;
            case R.id.bigcast_LookAll:
                Intent intent = new Intent(this, AllBigCastActivity.class);
                startActivity(intent);


                break;
        }
    }


}
