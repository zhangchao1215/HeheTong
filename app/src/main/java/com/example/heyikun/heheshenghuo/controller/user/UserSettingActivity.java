package com.example.heyikun.heheshenghuo.controller.user;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.GlideImageLoader;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.UserSettingBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.citysector.CityBaseActivity;
import com.example.heyikun.heheshenghuo.modle.eventbus.EventSexBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.picker.wheelview.ChangeDatePopwindow;
import com.example.heyikun.heheshenghuo.modle.picker.wheelview.OnWheelChangedListener;
import com.example.heyikun.heheshenghuo.modle.picker.wheelview.WheelView;
import com.example.heyikun.heheshenghuo.modle.picker.wheelview.adapter.ArrayWheelAdapter;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.SelectDialog;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.tsy.sdk.myokhttp.builder.UpLoadImagesBuilder;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.R.id.UserSetting_userName;

/**
 * Created by hyk on 2017/9/23.
 */

public class UserSettingActivity extends CityBaseActivity implements View.OnClickListener, OnWheelChangedListener {

	private static final String BOUNDARY = "请求数据";
	@BindView(R.id.Image_Back)
	ImageView ImageBack;
	@BindView(R.id.UserSetting_userImage)
	ImageView UserSettingUserImage;
	@BindView(UserSetting_userName)
	TextView UserSettingUserName;
	@BindView(R.id.mText_JianJie)
	TextView mTextJianJie;
	@BindView(R.id.UserSetting_SettingBirthDay)
	TextView UserSettingSettingBirthDay;
	@BindView(R.id.UserSetting_SettingSex)
	TextView UserSettingSettingSex;
	@BindView(R.id.UserSetting_SettingShengao)
	TextView UserSettingSettingShengao;
	@BindView(R.id.UserSetting_SettingWeight)
	TextView UserSettingSettingWeight;
	@BindView(R.id.UserSetting_blood)
	TextView UserSettingBlood;
	@BindView(R.id.UserSetting_SettingCity)
	TextView UserSettingSettingCity;
	@BindView(R.id.UserSetting_SettingMarriage)
	TextView UserSettingSettingMarriage;
	@BindView(R.id.UserSetting_SettingJop)
	TextView UserSettingSettingJop;
	@BindView(R.id.ZhangHao_safety)
	RelativeLayout ZhangHaoSafety;
	@BindView(R.id.Address_administration)
	RelativeLayout AddressAdministration;
	@BindView(R.id.main_Linear)
	LinearLayout mainLinear;
	private String userid;
	private String AESToken;
	private String encryptAppSign;
	private List<String> sexList;     //性别选择的list
	private List<String> marriedList; //结婚选择的list
	private List<String> bloodList; //设置血型
	private List<String> heightList;
	private List<String> weightList;
	private List<String> jopList;
	private String timestamp;
	private String appSign;
	private PopupWindow popupWindow;
	private EditText meditName, meditJianJie;
	private final String ANSWER_ONE = "1";
	private final String ANSWER_ZERO = "0";

	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	private TextView mBtnConfirm, mTextcancle;
	private PopupWindow mCityPop;
	public static final int REQUEST_CODE = 1000;
	private ArrayList<String> path;
	private UserSettingBean.DataBean dataBean;
	private View decorView;
	private int maxImgCount = 1;
	private ArrayList<ImageItem> selImageList; //当前选择的所有图片
	private ArrayList<ImageItem> images = null;

	public static final int IMAGE_ITEM_ADD = -1;
	public static final int REQUEST_CODE_SELECT = 100;
	public static final int REQUEST_CODE_PREVIEW = 101;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_user_usersetting);
		ButterKnife.bind(this);
		initFlag();

		init();

//		onWindow();

		fitsSystemWindows(this);
		PersonalData();

		//        App.activity.getWindow().getDecorView().setSystemUiVisibility(
		//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
		//                        //                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
		//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
		//        App.activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


	}

	private void init() {
		initImagePicker();
		path = new ArrayList<>();
		heightList = new ArrayList<>();
		weightList = new ArrayList<>();
		sexList = new ArrayList<>();
		marriedList = new ArrayList<>();
		weightList = new ArrayList<>();
		jopList = new ArrayList<>();
		heightList = new ArrayList<>();

		bloodList = new ArrayList<>();

		sexList.add("男");
		sexList.add("女");
		sexList.add("保密");


		marriedList.add("已婚");
		marriedList.add("未婚");
		marriedList.add("保密");


		bloodList.add("A");
		bloodList.add("B");
		bloodList.add("O");
		bloodList.add("AB");
		bloodList.add("RH阴性");


		jopList.add("销售/客服/技术支持");
		jopList.add("会计/金融");
		jopList.add("银行/保险");
		jopList.add("生产/营运");
		jopList.add("采购/物流");
		jopList.add("生物/制药");
		jopList.add("医疗/护理");
		jopList.add("广告/市场");
		jopList.add("建筑/房地产");
		jopList.add("计算机软件/IT-管理");
		jopList.add("人事/行政/高级管理");
		jopList.add("其它");

		//for循环添加数据
		for (int i = 100; i < 220; i++) {
			heightList.add(i + "公分");
		}

		for (int i = 30; i < 120; i++) {

			weightList.add(i + "公斤");
		}


		UserSettingUserImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				// GlideLoader 可用自己用的缓存库
				//                ImageConfig imageConfig
				//                        = new ImageConfig.Builder(
				//                        // GlideLoader 可用自己用的缓存库
				//                        new GlideLoader())
				//                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
				//                        .steepToolBarColor(getResources().getColor(R.color.blue))
				//                        // 标题的背景颜色 （默认黑色）
				//                        .titleBgColor(getResources().getColor(R.color.blue))
				//                        // 提交按钮字体的颜色  （默认白色）
				//                        .titleSubmitTextColor(getResources().getColor(R.color.white))
				//                        // 标题颜色 （默认白色）
				//                        .titleTextColor(getResources().getColor(R.color.white))
				//                        // 开启多选   （默认为多选）  (单选 为 singleSelect)
				//                        //                        .singleSelect()
				//                        //                        .crop()
				//                        // 多选时的最大数量   （默认 9 张）
				//                        .mutiSelectMaxSize(1)
				//                        // 已选择的图片路径
				//                        .pathList(path)
				//                        // 拍照后存放的图片路径（默认 /temp/picture）
				//                        .filePath("/ImageSelector/Pictures")
				//                        // 开启拍照功能 （默认开启）
				//                        .showCamera()
				//                        .requestCode(REQUEST_CODE)
				//                        .build();
				//
				//
				//                ImageSelector.open(UserSettingActivity.this, imageConfig);   // 开启图片选择器

				List<String> names = new ArrayList<>();
				names.add("拍照");
				names.add("相册");
				showDialog(new SelectDialog.SelectDialogListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						switch (position) {
							case 0: // 直接调起相机
								/**
								 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
								 *
								 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
								 *
								 * 如果实在有所需要，请直接下载源码引用。
								 */
								//打开选择,本次允许选择的数量
								//                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
								Intent intent = new Intent(UserSettingActivity.this, ImageGridActivity.class);
								intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
								startActivityForResult(intent, REQUEST_CODE_SELECT);
								break;
							case 1:
								//打开选择,本次允许选择的数量
								//                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
								Intent intent1 = new Intent(UserSettingActivity.this, ImageGridActivity.class);
								/* 如果需要进入选择的时候显示已经选中的图片，
								 * 详情请查看ImagePickerActivity
                                 * */
								//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
								startActivityForResult(intent1, REQUEST_CODE_SELECT);
								break;
							default:
								break;
						}

					}
				}, names);


			}
		});

	}

	private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
		SelectDialog dialog = new SelectDialog(this, R.style
				.transparentFrameWindowStyle,
				listener, names);
		if (!this.isFinishing()) {
			dialog.show();
		}
		return dialog;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
			//添加图片返回
			if (data != null && requestCode == REQUEST_CODE_SELECT) {
				images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
				if (images != null) {
					//                    selImageList.addAll(images);


					Glide.with(this).load(images.get(0).path).asBitmap().centerCrop().into(new BitmapImageViewTarget(UserSettingUserImage) {
						@Override
						protected void setResource(Bitmap resource) {
							RoundedBitmapDrawable circularBitmapDrawable =
									RoundedBitmapDrawableFactory.create(getResources(), resource);
							circularBitmapDrawable.setCircular(true);
							UserSettingUserImage.setImageDrawable(circularBitmapDrawable);
						}
					});

					File file = new File(images.get(0).path);
					uploadingImage(file);
				}
			}
		} else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
			//预览图片返回
			if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
				images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
				if (images != null) {
					selImageList.clear();
					//                    selImageList.addAll(images);
					Glide.with(this).load(images.get(0).path).asBitmap().centerCrop().into(new BitmapImageViewTarget(UserSettingUserImage) {
						@Override
						protected void setResource(Bitmap resource) {
							RoundedBitmapDrawable circularBitmapDrawable =
									RoundedBitmapDrawableFactory.create(getResources(), resource);
							circularBitmapDrawable.setCircular(true);
							UserSettingUserImage.setImageDrawable(circularBitmapDrawable);
						}
					});

					File file = new File(images.get(0).path);
					uploadingImage(file);

				}
			}
		}

	}


	/**
	 * 上传图片
	 */

	private void uploadingImage(final File file) {
		final Map<String, String> params = new HashMap<>();

		String user_id = AppUtils.get().getString("user_id", "");
		String token = AppUtils.get().getString("token", "");

		String currentTime_today = TimeUtils.getCurrentTime_Today();

		String timestrap = TimeUtils.dataOne(currentTime_today);


		String AESId = null;
		String AESToken = null;
		String Twotoken = user_id + "," + token + "," + timestrap;

		try {
			AESId = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
			AESToken = AESUtils.Encrypt(Twotoken, BaseUrl.AESKey);

		} catch (Exception e) {
			e.printStackTrace();
		}

		params.put("action", "UpHeardimg");
		params.put("user_id", AESId);
		params.put("token", AESToken);
		params.put("file", file + "");

		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {
				Log.d("UserSettingActivity", "上传头像++" + data);


			}

			@Override
			public void onError(String error) {

			}
		});

		UpLoadImagesBuilder upLoadImagesBuilder = new UpLoadImagesBuilder(App.myOkHttp);


	}


	/**
	 * 获取个人信息
	 */
	private void PersonalData() {

		//获取用户id 进行AES加密
		String user_id = AppUtils.get().getString("user_id", "");

		String Token = AppUtils.get().getString("token", "");
		try {

			//获取时间戳
			String currentTime_today = TimeUtils.getCurrentTime_Today();
			timestamp = TimeUtils.dataOne(currentTime_today);


			//userID进行AES加密
			userid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

			//生成二次token 并进行加密
			String TwoToken = user_id + "," + Token + "," + timestamp;

			AESToken = AESUtils.Encrypt(TwoToken, BaseUrl.AESKey);
			//生成签名并进行加密 先MD5 在进行AES
			String app_sign = "GetInfo" + timestamp + BaseUrl.AESKey;

			String encryptMd5 = MD5Utils.encrypt(app_sign);

			encryptAppSign = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);


		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> params = new HashMap<>();
		params.put("action", "GetInfo");
		params.put("user_id", userid);
		params.put("token", AESToken);
		params.put("app_sign", encryptAppSign);

		App.myOkHttp.post().url(BaseUrl.BaseUrl)
				.params(params)
				.enqueue(new RawResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String response) {
						Log.d("UserSettingActivity", response);
					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});


		App.myOkHttp.post().url(BaseUrl.BaseUrl)
				.params(params)
				.enqueue(new GsonResponseHandler<UserSettingBean>() {
					@Override
					public void onSuccess(int statusCode, UserSettingBean response) {

						dataBean = response.getData();

						if (response.getStatus().equals("200")) {


							String user_name = dataBean.getUser_name();


							String headimg = dataBean.getHeadimg();

							Glide.with(UserSettingActivity.this)
									.load(headimg)
									.asBitmap()
									.placeholder(R.drawable.touxiang_nan_man_4x)
									.centerCrop()
									.into(new BitmapImageViewTarget(UserSettingUserImage) {
										@Override
										protected void setResource(Bitmap resource) {
											RoundedBitmapDrawable circularBitmapDrawable =
													RoundedBitmapDrawableFactory.create(getResources(), resource);
											circularBitmapDrawable.setCircular(true);
											UserSettingUserImage.setImageDrawable(circularBitmapDrawable);

										}

									});

							/**
							 * 把从后台获取的单女信息进行传递
							 */
							//                EventSexBean sexBean = new EventSexBean();
							//
							//                sexBean.setSex(bean.getSex());
							//
							//                EventBus.getDefault().postSticky(sexBean);


							//得到个人简介
							mTextJianJie.setText(dataBean.getAlias());


							//用户姓名
							if (user_name.equals("")) {
								UserSettingUserName.setText("点击设置");
								UserSettingUserName.setBackgroundResource(R.color.colorUserSetting);
							} else {

								UserSettingUserName.setText(user_name + "");
							}


							String sex = dataBean.getSex();

							if (sex.equals("")) {
								UserSettingSettingSex.setText("去设置");
								UserSettingSettingSex.setBackgroundResource(R.color.colorUserSetting);
							} else {

								//设置性别
								if (sex.equals("1")) {
									UserSettingSettingSex.setText("男");
								} else if ("2".equals(sex)) {
									UserSettingSettingSex.setText("女");
								} else if ("0".equals(sex)) {
									UserSettingSettingSex.setText("保密");

								}
							}


							//设置结婚状态

							String marriage = dataBean.getMarriage();

							if (marriage.equals("")) {
								UserSettingSettingMarriage.setText("点击设置");

								UserSettingSettingMarriage.setBackgroundResource(R.color.colorUserSetting);
							} else {

								if (marriage.equals("1")) {
									UserSettingSettingMarriage.setText("未婚");
								} else if (dataBean.getMarriage().equals("2")) {
									UserSettingSettingMarriage.setText("已婚");
								} else if (dataBean.getMarriage().equals("0")) {
									UserSettingSettingMarriage.setText("保密");
								}
							}


							String weight = dataBean.getWeight();
							if (!weight.equals("")) {
								UserSettingSettingWeight.setText(weight + "");


							} else {
								UserSettingSettingWeight.setText("点击设置");

								UserSettingSettingWeight.setBackground(getResources().getDrawable(R.color.colorUserSetting));
							}


							String height = dataBean.getHeight();

							if (!height.equals("")) {

								UserSettingSettingShengao.setText(height + "");
							} else {
								UserSettingSettingShengao.setBackgroundResource(R.color.colorUserSetting);
								UserSettingSettingShengao.setText("点击设置");
							}
							String birthday = dataBean.getBirthday();

							if (!birthday.equals("")) {
								UserSettingSettingBirthDay.setText(birthday + "");
							} else {
								UserSettingSettingBirthDay.setText("点击设置");
								UserSettingSettingBirthDay.setBackgroundResource(R.color.colorUserSetting);
							}
							String address_id = dataBean.getAddress();
							if (!birthday.equals("")) {
								UserSettingSettingCity.setText(address_id + "");
							} else {
								UserSettingSettingCity.setText("点击设置");
								UserSettingSettingBirthDay.setBackgroundResource(R.color.colorUserSetting);
							}

							String blood = dataBean.getBlood();
							if (!birthday.equals("")) {
								UserSettingBlood.setText(blood + "");
							} else {
								UserSettingBlood.setText("点击设置");
								UserSettingSettingBirthDay.setBackgroundResource(R.color.colorUserSetting);
							}

							String job = dataBean.getJob();
							/**
							 * jopList.add("销售/客服/技术支持");
							 jopList.add("会计/金融");
							 jopList.add("银行/保险");
							 jopList.add("生产/营运");
							 jopList.add("采购/物流");
							 jopList.add("生物/制药");
							 jopList.add("医疗/护理");
							 jopList.add("广告/市场");
							 jopList.add("建筑/房地产");
							 jopList.add("计算机软件/IT-管理");
							 jopList.add("人事/行政/高级管理");
							 jopList.add("其它");
							 */
							if ("0".equals(job)) {
								UserSettingSettingJop.setText("销售/客服/技术支持");
							} else if ("1".equals(job)) {
								UserSettingSettingJop.setText("会计/金融");
							} else if ("2".equals(job)) {
								UserSettingSettingJop.setText("银行/保险");
							} else if ("3".equals(job)) {
								UserSettingSettingJop.setText("生产/营运");
							} else if ("4".equals(job)) {
								UserSettingSettingJop.setText("生物/制药");
							} else if ("5".equals(job)) {
								UserSettingSettingJop.setText("医疗/护理");
							} else if ("6".equals(job)) {
								UserSettingSettingJop.setText("广告/市场");
							} else if ("7".equals(job)) {
								UserSettingSettingJop.setText("建筑/房地产");
							} else if ("8".equals(job)) {
								UserSettingSettingJop.setText("计算机软件/IT-管理");
							} else if ("9".equals(job)) {
								UserSettingSettingJop.setText("人事/行政/高级管理");
							} else if ("10".equals(job)) {
								UserSettingSettingJop.setText("其它");
							} else if ("".equals(job)) {
								UserSettingSettingJop.setText("设置");
								UserSettingSettingBirthDay.setBackgroundResource(R.color.colorUserSetting);
							} else {
								UserSettingSettingJop.setText("其他");
							}


						}


					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});


	}


	@OnClick({R.id.UserSetting_userImage, R.id.UserSetting_SettingBirthDay, R.id.UserSetting_SettingSex,
			R.id.Image_Back, R.id.UserSetting_userName,
			R.id.UserSetting_SettingShengao, R.id.UserSetting_SettingWeight, R.id.UserSetting_blood,
			R.id.UserSetting_SettingCity, R.id.UserSetting_SettingMarriage, R.id.UserSetting_SettingJop,
			R.id.Address_administration, R.id.ZhangHao_safety, R.id.mText_JianJie})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.Image_Back:
				finish();
				break;

			//设置个人简介，弹出ppw进行修改
			case R.id.mText_JianJie:

				break;

			case R.id.UserSetting_userName:
				mAliasPopWindow();


				break;
			//设置用户头像
			case R.id.UserSetting_userImage:

				break;


			//设置生日
			case R.id.UserSetting_SettingBirthDay:
				getDateDialog("birthday", UserSettingSettingBirthDay);


				break;

			//设置性别
			case R.id.UserSetting_SettingSex:
				getBlood(sexList, UserSettingSettingSex, "sex");


				break;

			//设置身高
			case R.id.UserSetting_SettingShengao:
				getHeightDialog(heightList, UserSettingSettingShengao, "height");

				break;

			//设置体重
			case R.id.UserSetting_SettingWeight:

				getHeightDialog(weightList, UserSettingSettingWeight, "weight");
				break;

			//设置血型
			case R.id.UserSetting_blood:
				getBlood(bloodList, UserSettingBlood, "blood");


				break;

			//设置城市
			case R.id.UserSetting_SettingCity:

				CityPpw();
				break;

			//设置婚姻状况
			case R.id.UserSetting_SettingMarriage:

				getBlood(marriedList, UserSettingSettingMarriage, "marriage");

				break;

			//设置职业
			case R.id.UserSetting_SettingJop:
				getHeightDialog(jopList, UserSettingSettingJop, "jop");
				//                mRequest("jop",);

				break;


			//设置地址管理
			case R.id.Address_administration:
				Intent in = new Intent(this, UserAddressGuanLiActivity.class);
				startActivity(in);

				break;

			//设置账号安全
			case R.id.ZhangHao_safety:
				Intent intent = new Intent(this, AccountSecurityActivity.class);
				startActivity(intent);


				break;
		}
	}


	/**
	 * 设置血型 ,性别，婚姻状况
	 */
	private void getBlood(final List<String> list, final TextView textView, final String parameter) {
		OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int option2, int options3, View v) {

				//返回的分别是三个级别的选中位置
				String s = list.get(options1);
				textView.setText(s);
				String text = (String) textView.getText();


				AppUtils.put().putString("userFlag", "user");
				AppUtils.put().commit();


				if (text.equals("男") || text.equals("保密")) {
					AppUtils.put().putString("sex", "1").commit();

				} else if (text.equals("女")) {
					AppUtils.put().putString("sex", "2").commit();

				}

				Log.d("UserSettingActivity", "Event传递的数据" + text);

				//在这里做网络请求，把更改的数据上传到后台

				//设置性别
				if (text.equals("女"))

				{
					mRequest(parameter, "2");

				} else if (text.equals("男"))

				{

					mRequest(parameter, "1");

				} else if (text.equals("保密"))

				{

					mRequest(parameter, "0");

				} else if (text.equals("未婚"))

				{

					mRequest(parameter, "1");

				} else if (text.equals("已婚"))

				{

					mRequest(parameter, "2");
				} else

				{
					mRequest(parameter, text);
				}

			}
		})
				.

						setSubCalSize(20)//确定和取消文字大小
				.

						setSubmitColor(Color.BLUE)//确定按钮文字颜色
				.

						setCancelColor(Color.BLUE)//取消按钮文字颜色
				.

						setTextColorCenter(Color.BLACK)//设置选中项的颜色
				.

						build();
		pvOptions.setPicker(list);
		pvOptions.show();


	}


	private void getHeightDialog(final List<String> list, final TextView textView, final String parameter) {
		OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
			@Override
			public void onOptionsSelect(int options1, int option2, int options3, View v) {
				//返回的分别是三个级别的选中位置
				String s = list.get(options1);
				textView.setText(s);
				String text = (String) textView.getText();
				//在这里做网络请求，把更改的数据上传到后台
				/**
				 *    jopList.add("销售/客服/技术支持");
				 jopList.add("会计/金融");
				 jopList.add("银行/保险");
				 jopList.add("生产/营运");
				 jopList.add("采购/物流");
				 jopList.add("生物/制药");
				 jopList.add("医疗/护理");
				 jopList.add("广告/市场");
				 jopList.add("建筑/房地产");
				 jopList.add("计算机软件/IT-管理");
				 jopList.add("其它");
				 */
				if ("销售/客服/技术支持".equals(text)) {
					mRequest(parameter, "0");
				} else if ("银行/保险".equals(text)) {
					mRequest(parameter, "1");
				} else if ("生产/营运".equals(text)) {
					mRequest(parameter, "2");
				} else if ("采购/物流".equals(text)) {
					mRequest(parameter, "3");
				} else if ("生物/制药".equals(text)) {
					mRequest(parameter, "4");
				} else if ("医疗/护理".equals(text)) {
					mRequest(parameter, "5");
				} else if ("广告/市场".equals(text)) {
					mRequest(parameter, "6");
				} else if ("建筑/房地产".equals(text)) {
					mRequest(parameter, "7");

				} else if ("计算机软件/IT-管理".equals(text)) {
					mRequest(parameter, "8");

				} else if ("人事/行政/高级管理".equals(text)) {
					mRequest(parameter, "9");

				} else if ("其他".equals(text)) {
					mRequest(parameter, "10");

				} else {
					mRequest(parameter, text);

				}

			}
		})
				//                                        .setSubmitText("确定")//确定按钮文字
				//                                        .setCancelText("取消")//取消按钮文字
				//                                        .setTitleText("城市选择")//标题
				.setSubCalSize(20)//确定和取消文字大小
				//                        .setTitleSize(20)//标题文字大小
				//                        .setTitleColor(Color.BLACK)//标题文字颜色
				.setSubmitColor(Color.BLUE)//确定按钮文字颜色
				.setCancelColor(Color.BLUE)//取消按钮文字颜色
				//                        .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
				//                                        .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
				//                                        .setContentTextSize(18)//滚轮文字大小
				//                                        .setTextColorCenter(Color.BLUE)//设置选中项的颜色
				.setTextColorCenter(Color.BLACK)//设置选中项的颜色
				//                                        .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
				//                                        .setLinkage(false)//设置是否联动，默认true
				//                                        .setLabels("省", "市", "区")//设置选择的三级单位
				//                                        .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
				//                                        .setCyclic(true, true, true)//循环与否
				//                                        .setSelectOptions(1, 1, 1)  //设置默认选中项
				//                                        .setOutSideCancelable(false)//点击外部dismiss default true
				//                        .isDialog(true)//是否显示为对话框样式
				.build();
		pvOptions.setPicker(list);
		pvOptions.show();


	}

	//设置生日
	private void getDateDialog(final String parameter, final TextView tv) {
		final String[] str = new String[10];
		ChangeDatePopwindow mChangeBirthDialog = new ChangeDatePopwindow(this);
		mChangeBirthDialog.setDate("2017", "1", "1");
		mChangeBirthDialog.showAtLocation(mainLinear, Gravity.BOTTOM, 0, 0);
		mChangeBirthDialog.setBirthdayListener(new ChangeDatePopwindow.OnBirthListener() {

			@Override
			public void onClick(String year, String month, String day) {
				// TODO Auto-generated method stub
				Toast.makeText(UserSettingActivity.this, year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
				StringBuilder sb = new StringBuilder();
				sb.append(year.substring(0, year.length() - 1)).append("-").append(month.substring(0, month.length() - 1)).append("-").append(day.substring(0, day.length() - 1));
				str[0] = year + "-" + month + "-" + day;
				str[1] = sb.toString();

				Log.d("UserSettingActivity", "获取的生日 " + str[1]);

				tv.setText(str[1]);

				String text = (String) tv.getText();

				mRequest(parameter, text);

			}
		});
	}


	/**
	 * 修改个人数据,做网络请求的
	 */
	private void mRequest(String parameter, String text) {
		//生成签名并进行加密 先MD5 在进行AES
		String app_sign = "UpInfo" + timestamp + BaseUrl.AESKey;

		String encryptMd5 = MD5Utils.encrypt(app_sign);

		try {
			appSign = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);
		} catch (Exception e) {
			e.printStackTrace();
		}


		Map<String, String> params = new HashMap<>();
		params.put("action", "UpInfo");
		params.put("user_id", userid);
		params.put("token", AESToken);
		params.put("app_sign", appSign);
		params.put("parameter", parameter);
		params.put("finally", text);


		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {

				Log.d("UserSettingActivity", "修改的最后数据 " + data);

			}

			@Override
			public void onError(String error) {

			}
		});

	}

	/**
	 * 修改个人简介
	 *
	 * @param userName
	 * @param alias
	 */
	private void mChangeAlias(String userName, String alias) {
		//生成签名并进行加密 先MD5 在进行AES
		String app_sign = "UpIntro" + timestamp + BaseUrl.AESKey;

		String encryptMd5 = MD5Utils.encrypt(app_sign);

		try {
			appSign = AESUtils.Encrypt(encryptMd5, BaseUrl.AESKey);
		} catch (Exception e) {
			e.printStackTrace();
		}


		Map<String, String> params = new HashMap<>();
		params.put("action", "UpIntro");
		params.put("user_id", userid);
		params.put("token", AESToken);
		params.put("app_sign", appSign);
		params.put("username", userName);
		params.put("alias", alias);


		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {

				Log.d("UserSettingActivity", "简介修改的最后数据 " + data);

			}

			@Override
			public void onError(String error) {

			}
		});

	}

	/**
	 * 修改个人简介
	 */
	private void mAliasPopWindow() {
		backgroundAlpha(0.4f);
		View view = LayoutInflater.from(this).inflate(R.layout.activity_user_setting_ppw, null);

		TextView mTVdismiss = (TextView) view.findViewById(R.id.User_ppw_IdontText);

		TextView mTVChange = (TextView) view.findViewById(R.id.User_ppw_YesText);

		mTVdismiss.setOnClickListener(this);

		mTVChange.setOnClickListener(this);

		meditName = (EditText) view.findViewById(R.id.User_ppw_Name);

		meditJianJie = (EditText) view.findViewById(R.id.User_ppw_JianJie);


		popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT,
				true);
		//设置背景颜色
		popupWindow.setBackgroundDrawable(new BitmapDrawable());

		//设置外部不可点击
		popupWindow.setOutsideTouchable(false);

		popupWindow.setFocusable(true);
		popupWindow.setClippingEnabled(false);

		//popupwindow的弹出位置nnnnnn
		popupWindow.showAtLocation(findViewById(R.id.main_Linear), Gravity.CENTER, 0, 0);

		popupWindow.setOnDismissListener(new poponDismissListener());

	}


	private void CityPpw() {
		backgroundAlpha(0.4f);
		View view = LayoutInflater.from(this)
				.inflate(R.layout.dialog_myinfo_changecity_sector, null);

		setUpViews(view);

		mCityPop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

		//设置背景颜色
		mCityPop.setBackgroundDrawable((new ColorDrawable(0x00000000)));

		//设置外部可点击
		mCityPop.setFocusable(true);

		mCityPop.setOutsideTouchable(true);


		mCityPop.setClippingEnabled(false);

		setUpData();

		mCityPop.setAnimationStyle(R.style.anim_menu_bottombar);

		mCityPop.showAtLocation(this.findViewById(R.id.main_Linear),

				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
		mCityPop.setOnDismissListener(new poponDismissListener());

		view.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					mCityPop.dismiss();
					mCityPop = null;
					return true;
				}

				return false;
			}
		});

	}

	private void setUpViews(View view) {

		mViewProvince = (WheelView) view.findViewById(R.id.id_province);
		mViewCity = (WheelView) view.findViewById(R.id.id_city);
		mViewDistrict = (WheelView) view.findViewById(R.id.id_district);
		mBtnConfirm = (TextView) view.findViewById(R.id.btn_myinfo_sure);
		mTextcancle = (TextView) view.findViewById(R.id.btn_myinfo_cancel);
		// 添加change事件
		mViewProvince.addChangingListener(this);
		// 添加change事件
		mViewCity.addChangingListener(this);
		// 添加change事件
		mViewDistrict.addChangingListener(this);
		// 添加onclick事件
		mBtnConfirm.setOnClickListener(this);

		mTextcancle.setOnClickListener(this);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.User_ppw_IdontText:
				if (popupWindow.isShowing()) {
					popupWindow.dismiss();
					backgroundAlpha(1.0f);
				}


				break;

			//修改个人简介
			case R.id.User_ppw_YesText:

				String editName = meditName.getText().toString().trim();

				String editJianjie = meditJianJie.getText().toString().trim();

				if (popupWindow.isShowing()) {
					popupWindow.dismiss();
					backgroundAlpha(1.0f);

					if (editJianjie.equals("") || editName.equals("")) {
						UserSettingUserName.setText(dataBean.getUser_name());
						mTextJianJie.setText(dataBean.getAlias());
					} else {

						mChangeAlias(editName, editJianjie);

						UserSettingUserName.setText(editName);

						mTextJianJie.setText(editJianjie);
					}
				}


				break;

			//城市选择弹出的ppw确定
			case R.id.btn_myinfo_sure:
				if (mCityPop.isShowing()) {
					mRequest("address_id", mCurrentDistrictName);
					UserSettingSettingCity.setText(mCurrentDistrictName);
					Toast.makeText(this, mCurrentDistrictName, Toast.LENGTH_SHORT).show();
					mCityPop.dismiss();
					backgroundAlpha(1.0f);
				}


				break;

			//ppw的取消
			case R.id.btn_myinfo_cancel:

				if (mCityPop.isShowing()) {
					mCityPop.dismiss();
					backgroundAlpha(1.0f);
				}

				break;

		}
	}

	private void showSelectedResult() {
		Toast.makeText(this, "当前选中:" + mCurrentProviceName + "," + mCurrentCityName + ","
				+ mCurrentDistrictName + "。。区", Toast.LENGTH_SHORT).show();
	}

	/**
	 * popupwindow的内部类
	 */
	// TODO: 2017/9/15 这是设置 背景为半透明
	public void backgroundAlpha(float bgAlpha) {
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.alpha = bgAlpha; //0.0-1.0
		getWindow().setAttributes(lp);


	}

	private void setUpData() {
		initProvinceDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(this, mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateAreas();
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[]{""};
		}
		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mViewDistrict.setCurrentItem(0);
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[]{""};
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			Log.d("UserSettingActivity", "更新的县区的地址" + mCurrentDistrictName);

			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		}
	}

	class poponDismissListener implements PopupWindow.OnDismissListener {

		@Override
		public void onDismiss() {
			// TODO Auto-generated method stub
			//Log.v("List_noteTypeActivity:", "我是关闭事件");
			backgroundAlpha(1f);
		}

	}


	/**
	 * 事件分发，使ppw点击外部不消失，也不响应点击事件
	 */

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (mCityPop != null && mCityPop.isShowing()) {
			return false;
		}
		return super.dispatchTouchEvent(event);
	}


	private void onWindow() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(getResources().getColor(R.color.ZhenDuanText));

			//            window.setNavigationBarColor(Color.TRANSPARENT);
		}
	}

	/**
	 * 设置页面最外层布局 FitsSystemWindows 属性
	 *
	 * @param activity
	 */
	private void fitsSystemWindows(Activity activity) {
		ViewGroup contentFrameLayout = (ViewGroup) activity.findViewById(android.R.id.content);
		View parentView = contentFrameLayout.getChildAt(0);
		if (parentView != null && Build.VERSION.SDK_INT >= 14) {
			//布局预留状态栏高度的 padding
			parentView.setFitsSystemWindows(true);
			if (parentView instanceof DrawerLayout) {
				DrawerLayout drawer = (DrawerLayout) parentView;
				//将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
				drawer.setClipToPadding(false);
			}
		}
	}


	private void initFlag() {
		decorView = getWindow().getDecorView();
		int flag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide
				| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
		//判断当前版本在4.0以上并且存在虚拟按键，否则不做操作
		if (Build.VERSION.SDK_INT < 19 || !checkDeviceHasNavigationBar()) {
			//一定要判断是否存在按键，否则在没有按键的手机调用会影响别的功能。如之前没有考虑到，导致图传全屏变成小屏显示。
			return;
		} else {
			// 获取属性
			decorView.setSystemUiVisibility(flag);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();


	}

	/**
	 * 判断是否存在虚拟按键
	 *
	 * @return
	 */
	public boolean checkDeviceHasNavigationBar() {
		boolean hasNavigationBar = false;
		Resources rs = getResources();
		int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) {
			hasNavigationBar = rs.getBoolean(id);
		}
		try {
			Class<?> systemPropertiesClass = Class.forName("android.os.SystemProperties");
			Method m = systemPropertiesClass.getMethod("get", String.class);
			String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
			if ("1".equals(navBarOverride)) {
				hasNavigationBar = false;
			} else if ("0".equals(navBarOverride)) {
				hasNavigationBar = true;
			}
		} catch (Exception e) {

		}
		return hasNavigationBar;
	}


	/**
	 * 上传头像
	 */


	private void initImagePicker() {
		ImagePicker imagePicker = ImagePicker.getInstance();
		imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
		imagePicker.setShowCamera(true);                      //显示拍照按钮
		imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
		imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
		imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
		imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
		imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
		imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
		imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
		imagePicker.setOutPutY(1000); //保存文件的高度。单位像素
	}


}
