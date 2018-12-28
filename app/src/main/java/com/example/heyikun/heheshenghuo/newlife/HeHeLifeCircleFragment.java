package com.example.heyikun.heheshenghuo.newlife;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
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
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.DoctorChatActivity;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.FragmentViewPageAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.TimeJingluoAdapter;
import com.example.heyikun.heheshenghuo.controller.user.UserSettingActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.CapacityDoctorBean;
import com.example.heyikun.heheshenghuo.modle.bean.TimeJingluoBean;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.CircleView;
import com.example.heyikun.heheshenghuo.modle.util.ImageLoader;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.heyikun.hehe.RecyclerAllCheck;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/7 15:36
 * description：
 */

public class HeHeLifeCircleFragment extends BaseFragment implements CircleView.CircleClickListener {
	@BindView(R.id.life_shengyinImage)
	ImageView lifeShengyinImage;
	@BindView(R.id.Life_UserImage)
	ImageView LifeUserImage;
	@BindView(R.id.Life_UserName)
	TextView LifeUserName;
	@BindView(R.id.mCircleView)
	CircleView mCircleView;
	@BindView(R.id.mImage_gif)
	GifImageView mImageGif;
	@BindView(R.id.mRelative_circle)
	RelativeLayout mRelativeCircle;
	@BindView(R.id.mText_jingluo)
	TextView JingMaiMBut;
	@BindView(R.id.mImage_yuncai)
	ImageView mImageYuncai;
	Unbinder unbinder;
	Unbinder unbinder1;
	@BindView(R.id.mTabLayout)
	TabLayout mTabLayout;
	@BindView(R.id.mViewPager)
	ViewPager mViewPager;
	@BindView(R.id.mLinear)
	LinearLayout mLinear;
	Unbinder unbinder2;
	@BindView(R.id.mRelative_title)
	RelativeLayout mRelativeTitle;
	@BindView(R.id.mLinear_backColor)
	LinearLayout mLinearBackColor;
	Unbinder unbinder3;
	@BindView(R.id.mImage_people)
	ImageView mImagePeople;
	@BindView(R.id.mImage_hand)
	ImageView mImageHand;
	@BindView(R.id.mImage_taiji)
	ImageView mImageTaiji;
	@BindView(R.id.mImage_head)
	ImageView mImageHead;
	@BindView(R.id.mImage_shetai)
	ImageView mImageShetai;
	Unbinder unbinder4;
	@BindView(R.id.mText_xuxing_result)
	TextView mTextXuxingResult;
	Unbinder unbinder5;
	@BindView(R.id.mText_peopleStatus)
	TextView mTextPeopleStatus;
	@BindView(R.id.mText_handStatus)
	TextView mTextHandStatus;
	@BindView(R.id.mText_headStatus)
	TextView mTextHeadStatus;
	@BindView(R.id.mText_shetaiStatus)
	TextView mTextShetaiStatus;
	private String user_id;
	private String sex;
	private View view;
	private RelativeLayout background;
	private ListView listView;
	private TextView textArticle;
	private PopupWindow popupWindow;
	private TimeJingluoBean.DataBean timeBean;
	private List<TimeJingluoBean.DataBean> dataBeanList;
	private TimeJingluoAdapter jingluoAdapter;
	private List<String> strList;
	private List<Fragment> fragmentList;
	private String[] type_id = {"1", "2", "3", "1"};
	private FragmentViewPageAdapter viewPageAdapter;
	private CapacityDoctorBean.DataBean data;
	private int basic_data;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_hehe_life_circle;
	}

	@Override
	protected void initData() {
		loadJingluo();
	}

	@Override
	protected void initView(View view) {
		mInit();
	}

	@Override
	protected void initListener() {
		mCircleView.setOnItemListener(this);
	}

	/**
	 * onresume 重新刷新数据
	 */
	@Override
	public void onResume() {
		super.onResume();
		mOnresumeInit();

		mLoadData();
	}


	private void mLoadData() {
		String user_id = AppUtils.get().getString("user_id", "");
		Map<String, String> params = new HashMap<>();
		params.put("action", "get_index");
		params.put("user_id", user_id);
		App.myOkHttp
				.post()
				.url("https://hehe.heyishenghuo.com/mobile3/api_c/index.php")
				.params(params)
				.enqueue(new GsonResponseHandler<CapacityDoctorBean>() {
					@Override
					public void onSuccess(int statusCode, CapacityDoctorBean response) {

						if (response.getStatus().equals("200")) {
							data = response.getData();

							mTextXuxingResult.setText(data.getWuxing_name());

							//基本数据
							basic_data = data.getBasic_data();

							if (basic_data != 1) {
								mTextPeopleStatus.setText("未填写");
							} else {
								mTextPeopleStatus.setText("已填写");
							}

							//头部
							int features_1 = data.getFeatures_1();

							if (features_1 != 1) {
								mTextHeadStatus.setText("未上传");
							} else {
								mTextHeadStatus.setText("已上传");
							}

							//手部
							int features_2 = data.getFeatures_2();


							if (features_2 != 1) {
								mTextHandStatus.setText("未上传");
							} else {
								mTextHandStatus.setText("已上传");

							}
							//舌苔
							int features_3 = data.getFeatures_3();

							if (features_3 != 1) {
								mTextShetaiStatus.setText("未上传");

							} else {
								mTextShetaiStatus.setText("已上传");
							}

						}

					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});

	}


	/**
	 * 网络请求获取经络文案
	 */

	private void loadJingluo() {
		String user_id = AppUtils.get().getString("user_id", "");

		Map<String, String> params = new HashMap<>();
		params.put("action", "get_shichen_article");
		params.put("user_id", user_id);

		App.myOkHttp
				.post()
				.url(BaseUrl.URL)
				.params(params)
				.enqueue(new GsonResponseHandler<TimeJingluoBean>() {
					@Override
					public void onSuccess(int statusCode, TimeJingluoBean response) {

						if (response.getStatus().equals("200")) {
							timeBean = response.getData();

							dataBeanList.add(timeBean);

							jingluoAdapter = new TimeJingluoAdapter(getContext(), dataBeanList);
							listView.setAdapter(jingluoAdapter);

						}

					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});

	}


	/**
	 * 页面刷新的时候调用
	 */

	private void mOnresumeInit() {
		/**
		 * 根据保存的数据状态更换男女属性,图片
		 */
		sex = AppUtils.get().getString("sex", "");

		/**
		 *  2 为女性 对应着显示女性的所有页面颜色
		 */

		if (sex.equals("2")) {

			mRelativeTitle.setBackgroundColor(getResources().getColor(R.color.colorTextWomen));

			mLinearBackColor.setBackgroundColor(getResources().getColor(R.color.colorTextWomen));

			mCircleView.setBackColor(ContextCompat.getColor(getContext(), R.color.circleColorWomen));

			mCircleView.setTimeColor(ContextCompat.getColor(getContext(), R.color.circleTimeWomen));
			mCircleView.setLineColor(ContextCompat.getColor(getContext(),R.color.circleLine_womanColor));
		} else {

			mRelativeTitle.setBackgroundColor(getResources().getColor(R.color.colorManTitle));

			mLinearBackColor.setBackgroundColor(getResources().getColor(R.color.circleBackColor));

			mCircleView.setBackColor(ContextCompat.getColor(getContext(), R.color.circleColorMan));

			mCircleView.setTimeColor(ContextCompat.getColor(getContext(), R.color.circleTimeMan));

			mCircleView.setLineColor(ContextCompat.getColor(getContext(),R.color.circleLine_manColor));
		}


		//得到当前时间进行更改人形图片
		SimpleDateFormat sdf = new SimpleDateFormat("HH", Locale.getDefault());
		String currentTime = sdf.format(new Date());

		int time = Integer.parseInt(currentTime);


		changeTime(time);


		Log.d("CapacityDoctorFragment", "选择的性别       " + sex);

		user_id = AppUtils.get().getString("user_id", "");


		Log.e("CapacityDoctorFragment", "user_id ======================================" + user_id);
		//获取到当前时间
		initTimePrompt();

		String user_image = AppUtils.get().getString("user_image", "");
		String user_name = AppUtils.get().getString("user_name", "");

		if (user_name.equals("")) {
			LifeUserName.setText("注册/登录");
		} else {

			LifeUserName.setText(user_name + "");
		}

		ImageLoader.getInstance().displayCricleImage(getContext(), user_image, LifeUserImage,
				R.drawable.touxiang_nan_man_4x);

	}

	private void mInit() {

		strList = new ArrayList<>();
		fragmentList = new ArrayList<>();
		strList.add("养身");
		strList.add("养心");
		strList.add("养财");
		strList.add("养智");

		dataBeanList = new ArrayList<>();
		//这个是ppw的布局 应该写在最上方
		view = LayoutInflater.from(getContext()).inflate(R.layout.activity_jingmai_ppw, null);
		//ppw 里面的view listView 以及 背景

		background = (RelativeLayout) view.findViewById(R.id.relative);

		listView = (ListView) view.findViewById(R.id.Jingmai_listview_ppw);

		textArticle = (TextView) view.findViewById(R.id.jingmai_time_ppw);

		/**
		 * 动态设置人形图片的高度
		 */


		mImageGif.postDelayed(new Runnable() {
			@Override
			public void run() {

				ViewGroup.LayoutParams layoutParams = mImageGif.getLayoutParams();
				if (layoutParams instanceof ViewGroup.MarginLayoutParams) {

					//获取到绘制的圆盘高度
					int imageOffset = mCircleView.getImageOffset();
					/**
					 * 背景颜色
					 */

					//得到图片的高度
					int measuredHeight = mImageGif.getMeasuredHeight();
					//圆盘高度减去图片高度除以2 就是中心位置
					((ViewGroup.MarginLayoutParams) layoutParams).topMargin = imageOffset - measuredHeight / 2;

					mImageGif.setLayoutParams(layoutParams);

				}

			}
		}, 32);


		/**
		 *  动态添加Fragment 下面的评测提
		 */
		for (int i = 0; i < strList.size(); i++) {

			ArticleTestFragment fragment = new ArticleTestFragment();
			fragment.setType_id(type_id[i]);

			fragmentList.add(fragment);

		}
		mTabLayout.setupWithViewPager(mViewPager);

		viewPageAdapter = new FragmentViewPageAdapter(getChildFragmentManager(), strList, fragmentList);

		mViewPager.setAdapter(viewPageAdapter);

	}

	// TODO: 2018/5/28 整点报时

	/**
	 * 整点报时
	 */
	private void initTimePrompt() {
		IntentFilter timeFilter = new IntentFilter();
		timeFilter.addAction(Intent.ACTION_TIME_TICK);
		getActivity().registerReceiver(mTimeReceiver, timeFilter);

	}

	private BroadcastReceiver mTimeReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Calendar cal = Calendar.getInstance();
			int hour = cal.get(Calendar.HOUR_OF_DAY);
			int min = cal.get(Calendar.MINUTE);

			Log.d("CapacityDoctorFragment", "hour:得到当前的时辰       " + hour);
			Log.d("CapacityDoctorFragment", "min:得到当前的分钟数       " + min);

			changeTime(hour);


		}
	};


	/**
	 * 弹出框 经络文案
	 */
	private void mAliasPopWindow() {

		backgroundAlpha(0.6f);

		Button but = (Button) view.findViewById(R.id.jingmai_but_ppw);


		popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT,
				true);
		//设置背景颜色
		popupWindow.setBackgroundDrawable(new BitmapDrawable());

		//设置外部不可点击
		popupWindow.setOutsideTouchable(true);

		popupWindow.setFocusable(true);
		//设置ppw进出场动画
		popupWindow.setAnimationStyle(R.style.anim_menu_bottombar);
		//popupwindow的弹出位置nnnnnn
		popupWindow.showAtLocation(getActivity().findViewById(R.id.mLinear), Gravity.CENTER, 0, 0);

		//ppw 的关闭动画
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

// TODO: 2018/11/7  点击事件

	/**
	 * 点击事件
	 *
	 * @param view
	 */
	@OnClick({R.id.mImage_gif, R.id.mText_jingluo, R.id.mImage_hand, R.id.mImage_head,
			R.id.mImage_taiji, R.id.mImage_shetai, R.id.mImage_people})
	public void onViewClicked(View view) {
		if (data == null) {

			return;
		}
		switch (view.getId()) {

			case R.id.mImage_gif:

				mAliasPopWindow();

				break;
			case R.id.mText_jingluo:

				mAliasPopWindow();
				break;

			// 上传 手部数据
			case R.id.mImage_hand:

				mIntentCamera("hand", data.getFeatures_2(), "2");

				break;
			//上传头部数据
			case R.id.mImage_head:
				mIntentCamera("head", data.getFeatures_1(), "1");
				break;
			//上传舌苔数据
			case R.id.mImage_shetai:
				mIntentCamera("shetai", data.getFeatures_3(), "3");
				break;

			// 中间的太极的点击事件，看到是测量结果
			case R.id.mImage_taiji:
//
				WebViewUtils.publicWebView(getContext(),"https://hehe.heyishenghuo.com/mobile3/yscs_jk.php?vote_id=88","");

//				startActivity(new Intent(getContext(), RecyclerAllCheck.class));
				break;


			/**
			 *  人形图片，基本数据
			 */
			case R.id.mImage_people:


				if (user_id.isEmpty() || user_id.equals("")) {
					startActivity(new Intent(getContext(), LoginActivity.class));
					return;
				}
				startActivity(new Intent(getContext(), UserSettingActivity.class));


				break;

		}
	}

	/**
	 * 跳转到上传图片页面
	 *
	 * @param type
	 */
	private void mIntentCamera(String type, int status, String posi) {

		if (user_id.isEmpty() || user_id.equals("")) {
			startActivity(new Intent(getContext(), LoginActivity.class));
			return;
		}

		if (status == 1) {

			Intent data = new Intent(getContext(), CameraPreviewActivity.class);
			data.putExtra("position", posi);
			startActivity(data);


		} else {
			Intent intent = new Intent(getContext(), CameraViewActivity.class);
			intent.putExtra("imageType", type);
			startActivity(intent);
		}
	}


	/**
	 * popupwindow 实现关闭事件监听
	 */
	class poponDismissListener implements PopupWindow.OnDismissListener {

		@Override
		public void onDismiss() {
			// TODO Auto-generated method stub
			//Log.v("List_noteTypeActivity:", "我是关闭事件");
			backgroundAlpha(1.0f);

		}

	}

	/**
	 *
	 */
	// TODO: 2017/9/15 这是设置 背景为半透明
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
		lp.alpha = bgAlpha; //0.0-1.0
		getActivity().getWindow().setAttributes(lp);


	}


	/**
	 * 根据时间动态变更数据
	 *
	 * @param time
	 */
	private void changeTime(int time) {
		switch (time) {
			case 1:

				changeImage(R.drawable.zujueyinganjing_man, R.drawable.zujueyinganjing_woman);

				JingMaiMBut.setText("足厥阴肝经当令");


				background.setBackgroundResource(R.drawable.yinganjing);

				break;

			case 2:
				JingMaiMBut.setText("足厥阴肝经当令");


				background.setBackgroundResource(R.drawable.yinganjing);

				changeImage(R.drawable.zujueyinganjing_man, R.drawable.zujueyinganjing_woman);
				break;

			case 3:
				JingMaiMBut.setText("手太阴肺经当令");

				background.setBackgroundResource(R.drawable.yinfeijing);

				changeImage(R.drawable.shoutaiyangyinfeijing_man, R.drawable.shoutaiyangyinfeijng_woman);
				break;


			case 4:
				JingMaiMBut.setText("手太阴肺经当令");

				background.setBackgroundResource(R.drawable.yinfeijing);
				changeImage(R.drawable.shoutaiyangyinfeijing_man, R.drawable.shoutaiyangyinfeijng_woman);
				break;
			case 5:
				JingMaiMBut.setText("手阳明大肠经当令");


				background.setBackgroundResource(R.drawable.dachangjing);

				changeImage(R.drawable.shouyangmingdachangjing_man, R.drawable.shouyangmingdachangjing_woman);
				break;
			case 6:
				JingMaiMBut.setText("手阳明大肠经当令");

				background.setBackgroundResource(R.drawable.dachangjing);
				changeImage(R.drawable.shouyangmingdachangjing_man, R.drawable.shouyangmingdachangjing_woman);
				break;
			case 7:
				JingMaiMBut.setText("足阳明胃经当令");
				background.setBackgroundResource(R.drawable.zuyangmingweijing_4man);

				changeImage(R.drawable.zuyangmingwei_man, R.drawable.zuyangmingweijing_woman);

				break;
			case 8:
				JingMaiMBut.setText("足阳明胃经当令");


				background.setBackgroundResource(R.drawable.zuyangmingweijing_4man);

				changeImage(R.drawable.zuyangmingwei_man, R.drawable.zuyangmingweijing_woman);
				break;


			case 9:
				JingMaiMBut.setText("足太阴脾经当令");

				background.setBackgroundResource(R.drawable.yinpijing);

				changeImage(R.drawable.zutaiyinpijing_man, R.drawable.zutaiyinpijing_woman);

				break;

			case 10:
				JingMaiMBut.setText("足太阴脾经当令");
				background.setBackgroundResource(R.drawable.yinpijing);
				changeImage(R.drawable.zutaiyinpijing_man, R.drawable.zutaiyinpijing_woman);
				break;
			case 11:
				JingMaiMBut.setText("手少阴心经当令");
				background.setBackgroundResource(R.drawable.yinxinjing);

				changeImage(R.drawable.shoushaoyinxinjing_man, R.drawable.shoushaoyinxinjing_woman);

				Log.d("CapacityDoctorFragment", "现在是11点");
				break;
			case 12:
				JingMaiMBut.setText("手少阴心经当令");
				background.setBackgroundResource(R.drawable.yinxinjing);

				changeImage(R.drawable.shoushaoyinxinjing_man, R.drawable.shoushaoyinxinjing_woman);
				break;
			case 13:

				JingMaiMBut.setText("手太阳小肠经当令");
				background.setBackgroundResource(R.drawable.xiaochangjing);

				changeImage(R.drawable.shoutaiyangxiaochangjing_man, R.drawable.shoutaiyangxiaochangjing_woman);
				break;
			case 14:
				JingMaiMBut.setText("手太阳小肠经当令");
				background.setBackgroundResource(R.drawable.xiaochangjing);

				changeImage(R.drawable.shoutaiyangxiaochangjing_man, R.drawable.shoutaiyangxiaochangjing_woman);

				break;
			case 15:
				JingMaiMBut.setText("足太阳膀胱经当令");
				background.setBackgroundResource(R.drawable.pangguang);

				changeImage(R.drawable.zutaiyangpangguang_man, R.drawable.zutaiyangpangguang_woman);
				break;
			case 16:
				JingMaiMBut.setText("足太阳膀胱经当令");
				background.setBackgroundResource(R.drawable.pangguang);

				changeImage(R.drawable.zutaiyangpangguang_man, R.drawable.zutaiyangpangguang_woman);
				break;
			case 17:
				JingMaiMBut.setText("足少阴肾经当令");
				background.setBackgroundResource(R.drawable.yinshenjing);

				changeImage(R.drawable.zushaoyinshenjing_man, R.drawable.zushaoyinshenjing_woman);

				break;
			case 18:
				JingMaiMBut.setText("足少阴肾经当令");
				background.setBackgroundResource(R.drawable.yinshenjing);
				changeImage(R.drawable.zushaoyinshenjing_man, R.drawable.zushaoyinshenjing_woman);

				break;
			case 19:
				JingMaiMBut.setText("手厥阴心包经当令");
				background.setBackgroundResource(R.drawable.yinxinbao);

				changeImage(R.drawable.shoujueyinxinbao_man, R.drawable.shoujueyinxinbao_woman);

				break;
			case 20:
				JingMaiMBut.setText("手厥阴心包经当令");
				background.setBackgroundResource(R.drawable.yinxinbao);

				changeImage(R.drawable.shoujueyinxinbao_man, R.drawable.shoujueyinxinbao_woman);
				break;
			case 21:
				JingMaiMBut.setText("手少阳三焦经当令");

				background.setBackgroundResource(R.drawable.sanjiaojing);

				changeImage(R.drawable.shoushaoyangshanjiao_man, R.drawable.shoushaoyangsanjiao_woman);
				break;
			case 22:
				JingMaiMBut.setText("手少阳三焦经当令");

				background.setBackgroundResource(R.drawable.sanjiaojing);

				changeImage(R.drawable.shoushaoyangshanjiao_man, R.drawable.shoushaoyangsanjiao_woman);
				break;
			case 23:
				JingMaiMBut.setText("足少阳胆经当令");

				background.setBackgroundResource(R.drawable.yangdanjing);


				changeImage(R.drawable.zushaoyangdanjing_man, R.drawable.zushaoyangdanjing_woman);
				break;
			case 24:

				JingMaiMBut.setText("足少阳胆经当令");

				background.setBackgroundResource(R.drawable.yangdanjing);

				changeImage(R.drawable.zushaoyangdanjing_man, R.drawable.zushaoyangdanjing_woman);
				break;

		}

	}

	/**
	 * 根据时间动态切换图片 同时根据男女属性切换图片
	 *
	 * @param imageMan
	 * @param imageWoman
	 */
	private void changeImage(int imageMan, int imageWoman) {

		try {


			if (sex.equals("2")) {
				//这个方法是初始化图片的
				GifDrawable drawable = new GifDrawable(getResources(), imageWoman);

				mImageGif.setImageDrawable(drawable);
			} else {

				//这个方法是初始化图片的
				GifDrawable drawable = new GifDrawable(getResources(), imageMan);

				mImageGif.setImageDrawable(drawable);
			}


		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		getActivity().unregisterReceiver(mTimeReceiver);

	}

	/**
	 * 24节气的点击事件
	 *
	 * @param message
	 * @param position
	 * @param today
	 */
	@Override
	public void onClick(String message, int position, boolean today) {

		if (data == null) {

			return;
		}
		switch (message) {
			case "秋分":

				mWebViewIntent(data.getQiufen());

				break;
			case "白露":
				mWebViewIntent(data.getBailu());

				break;
			case "处暑":
				mWebViewIntent(data.getChushu());

				break;
			case "立秋":
				mWebViewIntent(data.getLiqiu());

				break;
			case "大暑":
				mWebViewIntent(data.getDashu());

				break;
			case "小暑":
				mWebViewIntent(data.getXiaoshu());

				break;
			case "夏至":
				mWebViewIntent(data.getXiazhi());

				break;
			case "芒种":
				mWebViewIntent(data.getMangzhong());

				break;
			case "小满":
				mWebViewIntent(data.getXiaoman());

				break;
			case "立夏":
				mWebViewIntent(data.getLixia());

				break;
			case "谷雨":
				mWebViewIntent(data.getYushui());

				break;
			case "清明":
				mWebViewIntent(data.getQingming());

				break;
			case "春分":
				mWebViewIntent(data.getChunfen());

				break;
			case "惊蛰":
				mWebViewIntent(data.getJingzhe());

				break;
			case "雨水":
				mWebViewIntent(data.getYushui());

				break;
			case "立春":
				mWebViewIntent(data.getLichun());

				break;
			case "大寒":
				mWebViewIntent(data.getDahan());

				break;
			case "小寒":
				mWebViewIntent(data.getXiaohan());

				break;
			case "冬至":
				mWebViewIntent(data.getDongzhi());

				break;
			case "大雪":
				mWebViewIntent(data.getDaxue());

				break;
			case "小雪":
				mWebViewIntent(data.getXiaoxue());

				break;
			case "立冬":
				mWebViewIntent(data.getLidong());

				break;
			case "霜降":
				mWebViewIntent(data.getShuangjiang());

				break;
			case "寒露":
				mWebViewIntent(data.getHanlu());

				break;


		}


	}

	/**
	 * 时辰的点击事件
	 */
	@Override
	public void onTimeClick() {
		if (data == null) {

			return;
		}

		WebViewUtils.ShareWebView(getContext(), data.getShichen(), "");
	}

	public void mWebViewIntent(String url) {
		WebViewUtils.ShareWebView(getContext(), url, "");

	}


}
