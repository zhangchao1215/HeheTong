package com.example.heyikun.heheshenghuo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
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
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.ShopTuiJianRecyclerAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.ShopYouHuiRecyclerAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.SlideshowAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeShoppingBean;
import com.example.heyikun.heheshenghuo.modle.bean.ShoppingArticleBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hyk on 2017/9/25.
 * <p>
 * 和合商城
 */

public class HeHeShoppingFragment extends BaseFragment {

	@BindView(R.id.mText_shop_tizhi)
	TextView mTextShopTizhi;
	@BindView(R.id.Shap_ImageTwo)
	ImageView ShapImageTwo;
	@BindView(R.id.mText_shop_wuxing)
	TextView mTextShopWuxing;
	@BindView(R.id.mText_shop_siji)
	TextView mTextShopSiji;

	@BindView(R.id.Shop_viewpager)
	ViewPager ShopViewpager;
	@BindView(R.id.mText_shop_tizhi_desc)
	TextView mTextShopTizhiDesc;
	@BindView(R.id.mText_shop_wuxing_desc)
	TextView mTextShopWuxingDesc;
	@BindView(R.id.mImage_shop_wuxing)
	ImageView mImageShopWuxing;
	@BindView(R.id.mText_shop_siji_desc)
	TextView mTextShopSijiDesc;
	@BindView(R.id.mImage_shop_siji)
	ImageView mImageShopSiji;

	@BindView(R.id.ShopYouhui_recycler)
	RecyclerView ShopYouhuiRecycler;
	@BindView(R.id.Shop_text_miaosha)
	TextView ShopTextMiaosha;
	@BindView(R.id.Shop_Image_Miaosha)
	ImageView ShopImageMiaosha;
	@BindView(R.id.Shop_text_BenZhou)
	TextView ShopTextBenZhou;
	@BindView(R.id.Shop_Image_BenZhou)
	ImageView ShopImageBenZhou;
	@BindView(R.id.Shop_text_Paihang)
	TextView ShopTextPaihang;
	@BindView(R.id.Shop_Image_PaiHang)
	ImageView ShopImagePaiHang;
	@BindView(R.id.Shop_Tuijian_Recycler)
	RecyclerView ShopTuijianRecycler;
	@BindView(R.id.Shop_Linear_haohuo)
	LinearLayout ShopLinearHaohuo;
	Unbinder unbinder;
	@BindView(R.id.Shop_ImageOne)
	ImageView ShopImageOne;
	@BindView(R.id.Shop_ImageTwo)
	ImageView ShopImageTwo;
	@BindView(R.id.Shop_ImageThree)
	ImageView ShopImageThree;
	@BindView(R.id.Shop_Search_link)
	RelativeLayout ShopSearchLink;
	@BindView(R.id.mNestedScroll)
	ScrollView mNestedScroll;
	@BindView(R.id.Shop_TizhiRelative)
	RelativeLayout ShopTizhiRelative;
	@BindView(R.id.Shop_Wuxing_Relative)
	RelativeLayout ShopWuxingRelative;
	@BindView(R.id.Shop_SijiRelative)
	RelativeLayout ShopSijiRelative;
	@BindView(R.id.Shop_radiogroup)
	RadioGroup ShopRadiogroup;
	@BindView(R.id.loding)
	LinearLayout loding;
	@BindView(R.id.shop_text_search)
	TextView shopTextSearch;
	Unbinder unbinder2;
	@BindView(R.id.Shopping_car)
	ImageView ShoppingCar;
	@BindView(R.id.text)
	TextView text;
	@BindView(R.id.loadMall_relative)
	RelativeLayout loadMallRelative;
	Unbinder unbinder1;
	@BindView(R.id.mRelative_title)
	RelativeLayout mRelativeTitle;

	private ArrayList<View> views;

	private Timer timer;

	private int Index = 100000;
	private final int START = 0;
	private final int END = 1;
	private int mIndex = 1;

	private boolean isContinue = true;

	private SlideshowAdapter slideshowAdapter;

	private ShopYouHuiRecyclerAdapter youHuiAdapter;
	private List<HeHeShoppingBean.DataBean.NewGoodsBean> newGoodsBeenList;

	private List<HeHeShoppingBean.DataBean.RecGoodsBean> recGoodsBeen;

	private ShopTuiJianRecyclerAdapter tuijianAdapter;

	private List<ShoppingArticleBean.DataBean> shopArticleList;

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
				case START:
					ShopViewpager.setCurrentItem(ShopViewpager.getCurrentItem() + 1);


					break;
				case END:
					handler.removeMessages(START);
					break;
			}
		}
	};
	private RadioButton radioButton;
	private String user_id;
	private String phoneEncrypt;
	private String pwdencrypt;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_main_shopping;
	}

	@Override
	public void onResume() {
		super.onResume();
		String sex = AppUtils.get().getString("sex", "");

		if (sex.equals("2")) {
			mRelativeTitle.setBackgroundColor(getResources().getColor(R.color.colorTextWomen));
		} else {
			mRelativeTitle.setBackgroundColor(getResources().getColor(R.color.colorManTitle));
		}

	}

	@Override
	protected void initData() {

		ShopLodaData();
		getArticleList();
		RecyclerRefresh();
	}

	@Override
	protected void initView(View view) {

		mNestedScroll.smoothScrollTo(0, 20);
		init();

		final String searchUrl = "https://hehe.heyishenghuo.com/mobile2/app2/public/search.php?";
		shopTextSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebViewUtils.publicWebView(getContext(), searchUrl + "&search=2", "搜索");

			}
		});
	}

	@Override
	protected void initListener() {

		ShoppingCar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (user_id.equals("")) {
					Intent intent = new Intent(getContext(), LoginActivity.class);
					startActivity(intent);
				} else
					WebViewUtils.publicWebTwo(getContext(), "http://hehe.heyishenghuo.com/mobile2/flow.php", "购物车");

			}
		});


	}

	private void init() {
		shopArticleList = new ArrayList<>();
		views = new ArrayList<View>();
		// 初始化引导图片列
		timer = new Timer();


		ShopViewpager.setCurrentItem(Index++);

		ShopViewpager.setOnTouchListener(onTouchListener);

		//定时器每隔三秒发送一次消息
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (isContinue) {
					handler.sendEmptyMessage(START);

				}
			}
		}, 3000, 3500);

		newGoodsBeenList = new ArrayList<>();

		recGoodsBeen = new ArrayList<>();
		LinearLayoutManager manager = new LinearLayoutManager(getContext());

		manager.setOrientation(LinearLayoutManager.HORIZONTAL);

		ShopYouhuiRecycler.setLayoutManager(manager);

		ShopTuijianRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

		GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

		gridLayoutManager.setSmoothScrollbarEnabled(true);
		gridLayoutManager.setAutoMeasureEnabled(true);
		ShopTuijianRecycler.setHasFixedSize(true);
		ShopTuijianRecycler.setNestedScrollingEnabled(false);
		ShopTuijianRecycler.setLayoutManager(gridLayoutManager);
	}


	private void ShopLodaData() {
		String password = AppUtils.get().getString("PassWord", "");
		String userName = AppUtils.get().getString("phone", "");

		Log.d("HeHeShoppingFragment", "userName》》》》》 " + userName);
		Log.d("HeHeShoppingFragment", "password>》》》》》》》 " + password);

		try {
			//对账号进行AES加密

			phoneEncrypt = AESUtils.Encrypt(userName, BaseUrl.AESKey);
			//对密码进行base64加密 在进行AES加密
			String BasePwd = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
			pwdencrypt = AESUtils.Encrypt(BasePwd, BaseUrl.AESKey);

		} catch (Exception e) {
			e.printStackTrace();
		}


		user_id = AppUtils.get().getString("user_id", "");
		Map<String, String> params = new HashMap<>();

		params.put("action", "GoodsPage");
		params.put("user_id", user_id);
		params.put("user_name", phoneEncrypt);
		params.put("password", pwdencrypt);

		Log.d("HeHeShoppingFragment", ">>> >>>》》》》》加密的手机号" + phoneEncrypt);
		Log.d("HeHeShoppingFragment", ">>> >>>》》》》》加密的mima   " + pwdencrypt);
		App.myOkHttp.post()
				.url(BaseUrl.BaseUrl)
				.params(params)
				.enqueue(new GsonResponseHandler<HeHeShoppingBean>() {
					@Override
					public void onSuccess(int statusCode, HeHeShoppingBean response) {

						HeHeShoppingBean.DataBean dataBean = response.getData();
						if (response.getStatus().equals("200")) {

							loding.setVisibility(View.GONE);
							mNestedScroll.setVisibility(View.VISIBLE);

							//每日优选 轮播图下面的那一横条

							final List<HeHeShoppingBean.DataBean.MenuBean> menu = dataBean.getMenu();

							for (int i = 0; i < menu.size(); i++) {

								final View view = LayoutInflater.from(getContext())
										.inflate(R.layout.activity_shop_haohuo_item, null);
								TextView mText = (TextView) view.findViewById(R.id.Shop_Text_Haohuo);

								ImageView image = (ImageView) view.findViewById(R.id.Shop_Image_haohuo);


								mText.setText(menu.get(i).getMenu_name());

								Glide.with(getContext())
										.load(menu.get(i).getMenu_img())
										.placeholder(R.drawable.jcwz)
										.into(image);

								final int FinalI = i;

								view.setOnClickListener(new View.OnClickListener() {
									@Override
									public void onClick(View v) {


										if (FinalI == 3) {
											if (user_id.equals("")) {
												Intent intent = new Intent(getContext(), LoginActivity.class);
												startActivity(intent);
											} else {
												WebViewUtils.publicWebView(getContext(), menu.get(3).getMenu_url(), menu.get(3).getMenu_name());

											}
										} else
											WebViewUtils.publicWebView(getContext(), menu.get(FinalI).getMenu_url(), menu.get(FinalI).getMenu_name());


									}
								});
								ShopLinearHaohuo.addView(view);

							}


							//加载轮播图
							LunboImage(dataBean.getBanner_ad());

							List<HeHeShoppingBean.DataBean.FoundsBean> founds = dataBean.getFounds();

							final HeHeShoppingBean.DataBean.FoundsBean foundsBean = founds.get(0);

							//五行养生
							mTextShopWuxing.setText(foundsBean.getFound_name());

							mTextShopWuxingDesc.setText(foundsBean.getFound_desc());

							Glide.with(getContext())
									.load(foundsBean.getFound_pic())
									.centerCrop()
									.placeholder(R.drawable.jcwz)
									.into(mImageShopWuxing);

							ShopWuxingRelative.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									WebViewUtils.publicWebView(getContext(), foundsBean.getFound_link(), "五行养生");

									//                                                Intent inten = new Intent(getContext(), ShopQueRenOrderActivity.class);
									//                                                startActivity(inten);
								}
							});


							//体质养生
							final HeHeShoppingBean.DataBean.FoundsBean foundsBean1 = founds.get(1);

							mTextShopTizhi.setText(foundsBean1.getFound_name());
							mTextShopTizhiDesc.setText(foundsBean1.getFound_desc());
							Glide.with(getContext())
									.load(foundsBean1.getFound_pic())
									.centerCrop()
									.placeholder(R.drawable.jcwz)
									.into(ShapImageTwo);

							ShopTizhiRelative.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									WebViewUtils.publicWebView(getContext(), foundsBean1.getFound_link(), "体质养生");

								}
							});


							//四季养生

							final HeHeShoppingBean.DataBean.FoundsBean foundsBean2 = founds.get(2);

							mTextShopSiji.setText(foundsBean2.getFound_name());
							mTextShopSijiDesc.setText(foundsBean2.getFound_desc());

							Glide.with(getContext())
									.load(foundsBean2.getFound_pic())
									.centerCrop()
									.placeholder(R.drawable.jcwz)
									.into(mImageShopSiji);

							ShopSijiRelative.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View v) {
									WebViewUtils.publicWebView(getContext(), foundsBean2.getFound_link(), "四季养生");
								}
							});


							//每日优惠
							newGoodsBeenList.addAll(dataBean.getNew_goods());

							youHuiAdapter = new ShopYouHuiRecyclerAdapter(newGoodsBeenList, getContext());

							ShopYouhuiRecycler.setAdapter(youHuiAdapter);


							//排行榜


							List<HeHeShoppingBean.DataBean.BnBean> bn = dataBean.getBn();


							HeHeShoppingBean.DataBean.BnBean bnBean = bn.get(0);
							HeHeShoppingBean.DataBean.BnBean bnBean1 = bn.get(1);
							HeHeShoppingBean.DataBean.BnBean bnBean2 = bn.get(2);


							//                ShopTextPaihang.setText(bnBean.getBn_name());

							Glide.with(getContext())
									.load(bnBean.getBn_pic())
									.centerCrop()
									.placeholder(R.drawable.jcwz)
									.into(ShopImagePaiHang);


							onImageClick(ShopImagePaiHang, bnBean.getBn_link(), "排行榜");

							Log.d("HeHeShoppingFragment", "排行榜的链接" + bnBean.getBn_link());


							//本周

							Glide.with(getContext())
									.load(bnBean1.getBn_pic())
									.centerCrop()
									.placeholder(R.drawable.jcwz)
									.into(ShopImageBenZhou);

							onImageClick(ShopImageBenZhou, bnBean1.getBn_link(), "本周上新");

							Log.d("HeHeShoppingFragment", "本周的链接" + bnBean.getBn_link());

							//秒杀

							Glide.with(getContext())
									.load(bnBean2.getBn_pic())
									.centerCrop()
									.placeholder(R.drawable.jcwz)
									.into(ShopImageMiaosha);

							onImageClick(ShopImageMiaosha, bnBean2.getBn_link(), "限时秒杀");

							Log.d("HeHeShoppingFragment", "秒杀的链接  " + bnBean2.getBn_link());

							//推荐

							if (dataBean.getRec_goods() == null) {
								return;
							}

						} else {

							loding.setVisibility(View.GONE);
							mNestedScroll.setVisibility(View.VISIBLE);

						}

					}

					@Override
					public void onFailure(int statusCode, String error_msg) {
						loding.setVisibility(View.GONE);
						mNestedScroll.setVisibility(View.VISIBLE);
					}
				});

	}


	private void getArticleList() {

		String user_id = AppUtils.get().getString("user_id", "");
		Log.d("HeHeShoppingFragment", "商品user_id" + user_id);
		String aesUser = null;
		if (!user_id.equals("")) {
			try {
				aesUser = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		Map<String, String> params = new HashMap<>();
		params.put("action", "GetGoods");
		params.put("user_id", aesUser);

		params.put("limit", "6"); //这是数量
		params.put("num", String.valueOf(mIndex)); //这是页数
		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {
				loadMallRelative.setVisibility(View.GONE);
				Log.d("HeHeShoppingFragment", data);
				Gson gson = new Gson();
				ShoppingArticleBean articleBean = null;
				try {
					articleBean = gson.fromJson(data, ShoppingArticleBean.class);


					if (data == null || articleBean == null) {
						return;
					} else if (!articleBean.getStatus().equals("200") || articleBean.getData() == null) {
						return;
					}

					shopArticleList.addAll(articleBean.getData());


					tuijianAdapter = new ShopTuiJianRecyclerAdapter(shopArticleList, getContext());

					ShopTuijianRecycler.setAdapter(tuijianAdapter);

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


	}

	private void RecyclerRefresh() {
		//是否开启下拉刷新功能
		//        if (mNestedScroll != null) {
		//
		//            mNestedScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
		//                @Override
		//                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
		//
		//                    if (scrollY > oldScrollY) {
		//
		//                        if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
		//                            mIndex++;
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

		mNestedScroll.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(final View v, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
					case MotionEvent.ACTION_MOVE:
						// getScrollY()是滚动条距离页面顶端的距离
						// getMeasuredHeight()是屏幕顶端距离页面顶端的距离
						// getHeight()是屏幕高度
						int y = mNestedScroll.getScrollY();
						if (y <= 0) {
							Log.i("Main", "正在顶部");
						} else if (mNestedScroll.getChildAt(0).getMeasuredHeight() <= y + mNestedScroll.getHeight()) {
							Log.i("Main", "已经滚动到底部");
							Log.i("Main", "MeasuredHeight:" + mNestedScroll.getMeasuredHeight() + ",ScrollY:" + y + ",Height:" + mNestedScroll.getHeight());
							// 追加内容
							//                            textView.append(getString(R.string.text));
							loadMallRelative.setVisibility(View.VISIBLE);

							new Handler().postDelayed(new Runnable() {
								@Override
								public void run() {
									mIndex++;
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

	private void LunboImage(final List<HeHeShoppingBean.DataBean.BannerAdBean> bannerAdBeen) {

		for (int i = 0; i < bannerAdBeen.size(); i++) {
			View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_faxian_carousel_item, null);

			ImageView image = (ImageView) view.findViewById(R.id.Faxian_mImage);

			Glide.with(getContext())
					.load(bannerAdBeen.get(i).getCarousel_pic())
					.placeholder(R.drawable.jfjz24x)
					.error(R.drawable.jfjz24x)
					.into(image);
			views.add(view);

			final int FinaiI = i;


			image.setOnClickListener(new View.OnClickListener() {

				private String carousel_title;
				private String carousel_link;

				@Override
				public void onClick(View v) {
					try {
						carousel_link = bannerAdBeen.get(FinaiI).getCarousel_link();
						carousel_title = bannerAdBeen.get(FinaiI).getCarousel_title();
					} catch (Exception e) {
						e.printStackTrace();
					}
					WebViewUtils.publicWebView(getContext(), carousel_link, carousel_title);
				}
			});


		}
		slideshowAdapter = new SlideshowAdapter(views);

		ShopViewpager.setAdapter(slideshowAdapter);

		//加载底部小圆点

		for (int i = 0; i < bannerAdBeen.size(); i++) {
			radioButton = new RadioButton(getContext());

			radioButton.setButtonDrawable(null);

			RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 20);

			layoutParams.setMargins(10, 0, 10, 0);

			radioButton.setLayoutParams(layoutParams);

			if (i == 0) {
				radioButton.setBackgroundResource(R.drawable.dianxuanzhong_man_4x);
			}

			ShopRadiogroup.addView(radioButton);

		}


		ShopViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

			}

			@Override
			public void onPageSelected(int position) {
				for (int i = 0; i < bannerAdBeen.size(); i++) {

					position = position % bannerAdBeen.size();

					View view = ShopRadiogroup.getChildAt(i);

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

	}


	/**
	 * 排行榜 秒杀的点击事件
	 */

	private void onImageClick(ImageView imageView, final String url, final String Title) {
		imageView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				WebViewUtils.publicWebView(getContext(), url, Title);

				//                Intent intent = new Intent(getContext(), ShareWebView.class);
				//                intent.putExtra("url", url + "&from=1");
				//                intent.putExtra("title", "商品");
				//                startActivity(intent);

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


	/**
	 * 当页面销毁的时候消除定时器
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();

		if (timer != null) {
			timer.cancel();
		}

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO: inflate a fragment view
		View rootView = super.onCreateView(inflater, container, savedInstanceState);
		unbinder1 = ButterKnife.bind(this, rootView);
		return rootView;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		unbinder1.unbind();
	}
}
