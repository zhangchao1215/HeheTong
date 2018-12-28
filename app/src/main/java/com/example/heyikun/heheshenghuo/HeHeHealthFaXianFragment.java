package com.example.heyikun.heheshenghuo;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.controller.adapter.FaXianDaKaRecyclerAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.HeHeFaxianArticleAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.SlideshowAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.FaxianShopBean;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeFaxianBean;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MarqueeView;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.youth.banner.Banner.CIRCLE_INDICATOR_TITLE;

/**
 * Created by hyk on 2017/9/25.
 * <p>
 * 健康发现首页
 */

public class HeHeHealthFaXianFragment extends BaseFragment implements View.OnClickListener {


	@BindView(R.id.mViewPager)
	Banner mViewPager;
	@BindView(R.id.LinearAdd_consults)
	LinearLayout LinearAddConsults;
	@BindView(R.id.faxian_lookAll_zixun)
	TextView faxianLookAllZixun;
	@BindView(R.id.faxian_recycler_guanggao)
	RecyclerView faxianRecyclerGuanggao;
	@BindView(R.id.Faxian_articleRecycler)
	RecyclerView FaxianArticleRecycler;
	@BindView(R.id.mScrollview)
	NestedScrollView mScrollview;
	@BindView(R.id.Day_ke)
	TextView DayKe;
	@BindView(R.id.Faxian_TitleCishi)
	TextView FaxianTitleCishi;
	@BindView(R.id.marqueeview)
	MarqueeView marqueeview;
	@BindView(R.id.Title_Relative)
	RelativeLayout TitleRelative;
	@BindView(R.id.Faxian_RadioGroup)
	RadioGroup FaxianRadioGroup;
	@BindView(R.id.loding)
	LinearLayout loding;
	@BindView(R.id.mImage_plan)
	ImageView mImagePlan;
	Unbinder unbinder;
	private FaXianDaKaRecyclerAdapter recyclerAdapter;
	private ArrayList<View> views;
	private List<HeHeFaxianBean.DataBean.PlateBean> plateBeanList;
	private List<FaxianShopBean.DataBean> DataBeen;
	private HeHeFaxianArticleAdapter articleAdapter;
	private Timer timer;
	private int Index = 100000;
	private final int START = 0;
	private final int END = 1;
	int num = 0;
	private boolean isContinue = true;

	private SlideshowAdapter slideshowAdapter;
	private RadioButton[] arrRadioButton = new RadioButton[]{};
	private int articleIndex = 1;


	//    private Handler handler = new Handler() {
	//        @Override
	//        public void handleMessage(Message msg) {
	//            super.handleMessage(msg);
	//            switch (msg.what) {
	//                case START:
	//                    try {
	//                        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
	//                    } catch (Exception e) {
	//                        e.printStackTrace();
	//                    }
	//
	//
	//                    break;
	//                case END:
	//                    handler.removeMessages(START);
	//
	//
	//                    break;
	//            }
	//        }
	//    };


	private List<HeHeFaxianBean.DataBean.ConsultsBean> consults;
	private HeHeFaxianBean.DataBean dataBean;
	private RadioButton radioButton;
	private List<FaxianShopBean.DataBean> shopBeanData;

	private List<String> bannerList;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main_faxian;
	}


	@Override
	public void onResume() {
		super.onResume();
		String sex = AppUtils.get().getString("sex", "");

		if (sex.equals("2")) {
			TitleRelative.setBackgroundColor(getResources().getColor(R.color.colorTextWomen));
		} else {
			TitleRelative.setBackgroundColor(getResources().getColor(R.color.colorManTitle));
		}

	}

	@Override
	protected void initData() {


		LodaData();
		commodityData();
	}

	@Override
	protected void initView(View view) {
		mScrollview.smoothScrollTo(0, 20);
		init();
	}

	@Override
	protected void initListener() {

		//        RecyclerRefresh();


		final String url = " https://hehe.heyishenghuo.com/mobile2/goods.php?u=5947&id=271410";
		mImagePlan.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				WebViewUtils.publicWebView(getContext(), url, "健康365");


			}
		});
	}


	private void init() {

		mViewPager.setBannerStyle(CIRCLE_INDICATOR_TITLE);
		mViewPager.isAutoPlay(true);
		mViewPager.setDelayTime(3000);
		mViewPager.setIndicatorGravity(Banner.CENTER);

		bannerList = new ArrayList<>();

		views = new ArrayList<>();

		plateBeanList = new ArrayList<>();


		DataBeen = new ArrayList<>();

		LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
		layoutManager.setSmoothScrollbarEnabled(true);
		layoutManager.setAutoMeasureEnabled(true);
		FaxianArticleRecycler.setHasFixedSize(true);
		FaxianArticleRecycler.setNestedScrollingEnabled(false);

		FaxianArticleRecycler.setLayoutManager(layoutManager);
		// 初始化引导图片列
		timer = new Timer();

		//        mViewPager.setCurrentItem(Index++);

		//        mViewPager.setOnTouchListener(onTouchListener);
		//
		//        //定时器每隔三秒发送一次消息
		//        timer.schedule(new TimerTask() {
		//            @Override
		//            public void run() {
		//                if (isContinue) {
		//                    handler.sendEmptyMessage(START);
		//
		//                }
		//            }
		//        }, 4000, 5000);

		GridLayoutManager GridManager = new GridLayoutManager(getContext(), 2);
		GridManager.setSmoothScrollbarEnabled(true);
		GridManager.setAutoMeasureEnabled(true);
		faxianRecyclerGuanggao.setHasFixedSize(true);
		faxianRecyclerGuanggao.setNestedScrollingEnabled(false);

		faxianRecyclerGuanggao.setLayoutManager(GridManager);


		faxianLookAllZixun.setOnClickListener(this);


		FaxianTitleCishi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String searchUrl = "https://hehe.heyishenghuo.com/mobile2/app2/public/search.php?";

				WebViewUtils.publicWebView(getContext(), searchUrl + "&search=1", " ");


			}
		});
	}

	/**
	 * 请求数据
	 */

	private void LodaData() {

		Map<String, String> params = new HashMap<>();
		params.put("action", "FoundPage");
		App.myOkHttp.post().url(BaseUrl.BaseUrl)
				.params(params)
				.enqueue(new GsonResponseHandler<HeHeFaxianBean>() {
					@Override
					public void onSuccess(int statusCode, HeHeFaxianBean response) {

						if (response.getStatus().equals("200")) {

							loding.setVisibility(View.GONE);
							mScrollview.setVisibility(View.VISIBLE);

							dataBean = response.getData();

							consults = dataBean.getConsults();

							if (consults == null) {
								return;
							}

							consultsData();

							List<HeHeFaxianBean.DataBean.CarouselBean> carousel = dataBean.getCarousel();

							//加载轮播图

							LunBoImage(carousel);

							List<HeHeFaxianBean.DataBean.PlateBean> plate = dataBean.getPlate();

							plateBeanList.addAll(plate);


							//广告位
							recyclerAdapter = new FaXianDaKaRecyclerAdapter(plateBeanList, getContext());

							faxianRecyclerGuanggao.setAdapter(recyclerAdapter);


						}else {
							loding.setVisibility(View.GONE);
							mScrollview.setVisibility(View.VISIBLE);
						}


					}

					@Override
					public void onFailure(int statusCode, String error_msg) {
						loding.setVisibility(View.GONE);
						mScrollview.setVisibility(View.VISIBLE);
					}
				});

	}

	/**
	 * 加载轮播图
	 */

	private void LunBoImage(final List<HeHeFaxianBean.DataBean.CarouselBean> carousel) {
		for (HeHeFaxianBean.DataBean.CarouselBean carouselBean : carousel) {
			bannerList.add(carouselBean.getCarousel_pic());
			Log.d("HeHeHealthFaXianFragmen", "轮播图片？？？？？？  " + carouselBean.getCarousel_pic());
		}
		mViewPager.setImages(bannerList);

		mViewPager.setOnBannerClickListener(new Banner.OnBannerClickListener() {
			private String carousel_title;
			private String carousel_link;

			@Override
			public void OnBannerClick(View view, int position) {
				try {
					carousel_link = carousel.get(position - 1).getCarousel_link();
					carousel_title = carousel.get(position - 1).getCarousel_title();
				} catch (Exception e) {
					e.printStackTrace();
				}
				WebViewUtils.publicWebView(getContext(), carousel_link, carousel_title);


			}
		});

		//        Log.d("HeHeHealthFaXianFragmen", "carousel.size():" + carousel.size());
		//
		//        for (int i = 0; i < carousel.size(); i++) {
		//            View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_faxian_carousel_item, null);
		//
		//            ImageView image = (ImageView) view.findViewById(R.id.Faxian_mImage);
		//
		//            Glide.with(getContext())
		//                    .load(carousel.get(i).getCarousel_pic())
		//                    .placeholder(R.drawable.jfjz24x)
		//                    .error(R.drawable.jfjz24x)
		//                    .centerCrop()
		//                    .into(image);
		//
		//
		//            views.add(view);
		//            final int FinalI = i;
		//            image.setOnClickListener(new View.OnClickListener() {
		//
		//                private String carousel_title;
		//                private String carousel_link;
		//
		//                @Override
		//                public void onClick(View v) {
		//                    try {
		//                        carousel_link = carousel.get(FinalI).getCarousel_link();
		//                        carousel_title = carousel.get(FinalI).getCarousel_title();
		//                    } catch (Exception e) {
		//                        e.printStackTrace();
		//                    }
		//                    WebViewUtils.publicWebView(getContext(), carousel_link, carousel_title);
		//
		//                }
		//            });
		//
		//
		//        }
		//        slideshowAdapter = new SlideshowAdapter(views);
		//
		//        mViewPager.setAdapter(slideshowAdapter);
		//
		//
		//        for (int i = 0; i < carousel.size(); i++) {
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
		//            FaxianRadioGroup.addView(radioButton);
		//
		//            if (i == 0) {
		//                radioButton.setBackgroundResource(R.drawable.dianxuanzhong_man_4x);
		//            }
		//
		//
		//            Log.d("HeHeHealthFaXianFragmen", "carousel.size():" + carousel.size());
		//        }
		//
		//        //设置默认第一个为选中状态
		//
		//
		//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
		//            @Override
		//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		//
		//            }
		//
		//            @Override
		//            public void onPageSelected(int position) {
		//                for (int i = 0; i < carousel.size(); i++) {
		//                    position = position % carousel.size();
		//
		//                    //还原背景
		//                    View view = FaxianRadioGroup.getChildAt(i);
		//
		//                    view.setBackgroundResource(R.drawable.dianmeixuanzhong_man_4x);
		//
		//                    if (i == position) {
		//
		//                        view.setBackgroundResource(R.drawable.dianxuanzhong_man_4x);
		//                    }
		//
		//                }
		//
		//
		//            }
		//
		//            @Override
		//            public void onPageScrollStateChanged(int state) {
		//
		//            }
		//        });


	}

	/**
	 * 加载上面两层的数据
	 */
	private void consultsData() {
		final List<String> list = new ArrayList<>();


		for (int i = 0; i < consults.size(); i++) {

			list.add(consults.get(i).getTitle());

		}

		marqueeview.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
			@Override
			public void onItemClick(int position, TextView textView) {

				WebViewUtils.WebFrom(getContext(), consults.get(position).getLink(), "每日一课");


			}
		});
		marqueeview.startWithList(list);

	}


	//最下方的数据
	private void commodityData() {
		String user_id = AppUtils.get().getString("user_id", "");

		Map<String, String> params = new HashMap<>();
		params.put("action", "HealthArt");
		params.put("user_id", user_id);
		App.myOkHttp.post().url(BaseUrl.BaseUrl)
				.params(params)
				.enqueue(new GsonResponseHandler<FaxianShopBean>() {
					@Override
					public void onSuccess(int statusCode, FaxianShopBean response) {

						if (response.getStatus().equals("200")) {

							shopBeanData = response.getData();

							if (shopBeanData == null) {
								return;
							}

							DataBeen.addAll(shopBeanData);

							articleAdapter = new HeHeFaxianArticleAdapter(DataBeen, getContext());

							//金木水火土
							FaxianArticleRecycler.setAdapter(articleAdapter);
							articleAdapter.notifyDataSetChanged();

							articleAdapter.setArticleClick(new HeHeFaxianArticleAdapter.FaxianArticleClick() {
								@Override
								public void onClick(int position) {
									WebViewUtils.publicWebView(getContext(), DataBeen.get(position).getArticle_link(), DataBeen.get(position).getArticle_title());
								}
							});


						}


					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});

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
					isContinue = false;

				case MotionEvent.ACTION_MOVE:

					break;

				default:
					isContinue = true;
			}
			return false;//注意这里只能返回false,如果返回true，Dwon就会消费掉事件，MOVE无法获得事件，
			// 导致图片无法滑动

		}

	};


	@Override
	public void onDestroy() {
		super.onDestroy();

		if (timer != null) {
			timer.cancel();
		}

	}


	/**
	 * 查看更多咨讯
	 *
	 * @param v
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.faxian_lookAll_zixun:

				break;
		}
	}


	//
	//
	//
	//
	//
	//
	//    @Override
	//    public void mOnItemClickLisenter(int position) {
	//
	//
	//
	//
	//
	//    }


}
