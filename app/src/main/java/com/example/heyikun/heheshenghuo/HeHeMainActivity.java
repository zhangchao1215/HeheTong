package com.example.heyikun.heheshenghuo;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.controller.adapter.ShouYeFragmentAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.SexBean;
import com.example.heyikun.heheshenghuo.modle.bean.UpdateBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EventIntentUserBean;
import com.example.heyikun.heheshenghuo.modle.eventbus.EventMessageBean;
import com.example.heyikun.heheshenghuo.modle.eventbus.EventSexBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppManager;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.LocationUtils;
import com.example.heyikun.heheshenghuo.modle.util.PermissionsChecker;
import com.example.heyikun.heheshenghuo.modle.util.PermissionsManager;
import com.example.heyikun.heheshenghuo.modle.util.PermissionsResultAction;
import com.example.heyikun.heheshenghuo.modle.util.SPUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.UserUtls;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.example.heyikun.heheshenghuo.newlife.HeHeLifeCircleFragment;
import com.example.heyikun.heheshenghuo.newlife.HeHeNewLifeFragment;
import com.example.heyikun.heheshenghuo.newlife.HeHeNewLifeWomenFragment;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jiguang.api.JCoreInterface;
import cn.jpush.android.api.JPushInterface;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.attr.versionCode;
import static com.alipay.sdk.app.statistic.c.F;

/**
 * Created by hyk on 2017/9/23.
 */

public class HeHeMainActivity extends BaseActivity {

	@BindView(R.id.mRadio_HeheLife)
	RadioButton mRadioHeheLife;
	@BindView(R.id.mRadio_Shangcheng)
	RadioButton mRadioShangcheng;
	@BindView(R.id.mRadio_FaXian)
	RadioButton mRadioFaXian;
	@BindView(R.id.mRadio_User)
	RadioButton mRadioUser;
	@BindView(R.id.bottom_RadioGroup)
	RadioGroup ManradioGroup;
	@BindView(R.id.Man_frameLayout)
	FrameLayout ManFrameLayout;
	@BindView(R.id.mRadio_Serve)
	RadioButton mRadioServe;
	@BindView(R.id.mRadio_HeheLife_women)
	RadioButton mRadioHeheLifeWomen;
	@BindView(R.id.mRadio_FaXian_women)
	RadioButton mRadioFaXianWomen;
	@BindView(R.id.mRadio_Shangcheng_women)
	RadioButton mRadioShangchengWomen;
	@BindView(R.id.mRadio_Serve_women)
	RadioButton mRadioServeWomen;
	@BindView(R.id.mRadio_User_women)
	RadioButton mRadioUserWomen;
	@BindView(R.id.women_RadioGroup)
	RadioGroup womenRadioGroup;
	private FragmentManager fragmentManager;

	private ShouYeFragmentAdapter adapter;

	private HeHeHealthFaXianFragment faXianFragment;
	private HeHeShoppingFragment shoppingFragment;
	private HeHeUserFragment userFragment;
	private HeHeUserWomenFragment userWomenFragment;
	private HeHeServeFragment serveFragment;
	private String sex;
	private HeHeLifeCircleFragment lifeFragment;
	private HeHeNewLifeWomenFragment womenFragment;


	private int sexFlag;

	private View decorView;
	private String encryptUserId;
	private String mTokenTwo;
	private String encryptToken;
	private String token;
	private String user_id;
	private double latitude;
	private double longitude;
	private
	// 所需的全部权限
	static final String[] PERMISSIONS = new String[]{
			Manifest.permission.ACCESS_COARSE_LOCATION,
			Manifest.permission.ACCESS_FINE_LOCATION
			, Manifest.permission.CAMERA
	};
	private int appversionCode;
	private int RESUME_TAG;
	private String userSex;
	private String intent;
	private int replace;
	private String userFlag;
	private boolean booFirst = false;
	private String registrationID;
	private int versionCode;
	private int versionCode1;
	private String uplink;

	@Override
	protected int layoutId() {
		return R.layout.activity_hehemain_fragment;
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	protected void initView() {

		registrationID = JCoreInterface.getRegistrationID(this);

		Log.d("HeHeMainActivity", "极光推送id   " + registrationID);


		Intent intent = getIntent();
		replace = intent.getIntExtra("replace", 0);
		String jpush = intent.getStringExtra("jpush");


		userFlag = AppUtils.get().getString("userFlag", "");


		// 获取版本号
		appversionCode = getVersionCode(this);


		//安卓手机设备号
		String string = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

		Log.d("HeHeMainActivity", "android手机设备号" + string);


		token = AppUtils.get().getString("token", "");
		user_id = AppUtils.get().getString("user_id", "");
		EventBus.getDefault().register(this);

		decorView = getWindow().getDecorView();


//		SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
//		SharedPreferences.Editor editor = pref.edit();
//		editor.putBoolean("status", false);
//		editor.commit();


		Location location = LocationUtils.getInstance(this).showLocation();

		try

		{
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			Log.d("HeHeMainActivity", "latitude:" + latitude);
			Log.d("HeHeMainActivity", "longitude:" + longitude);
		} catch (
				Exception e)

		{
			e.printStackTrace();
		}
		insertUserInfo();


		mIntent();

		// 判断是否更新app

		UploadApp();

		/**
		 *  判断是男是女 显示不同页面
		 */

//		getSex();

		//获取权限
	}


	@TargetApi(24)
	private void getAllpermission() {
		PermissionsManager.getInstance().requestAllManifestPermissionsIfNecessary(this, new PermissionsResultAction() {
			@Override
			public void onGranted() {
				//维度

			}

			@Override
			public void onDenied(String permission) {
				Log.d("HeHeServeFragment", "permission" + permission);
			}
		});


	}


	private void mIntent() {


		if (replace == 11) {

			mInit(new HeHeLifeFragment());


		} else if (replace == 12) {
			mInit(new HeHeHealthFaXianFragment());

			mRadioFaXian.setChecked(true);
		} else if (replace == 13) {
			mInit(new HeHeUserFragment());


			mRadioUser.setChecked(true);
		} else if (replace == 14) {
			mRadioServe.setChecked(true);
			mInit(new HeHeServeFragment());

		} else if (replace == 15) {
			mRadioShangcheng.setChecked(true);

			mInit(new HeHeShoppingFragment());

		} else {
			getSex();


		}

	}

	@Override
	protected void onResume() {
		super.onResume();


		switch (RESUME_TAG) {
			case 1:
				mRadioHeheLife.setChecked(true);

				break;
			case 2:
				mRadioShangcheng.setChecked(true);


				break;
			case 3:
				mRadioFaXian.setChecked(true);


				break;
			case 4:
				mRadioServe.setChecked(true);

				break;
			case 5:

				mRadioUser.setChecked(true);
				break;
			case 6:
				mRadioHeheLifeWomen.setChecked(true);


				break;
			case 7:
				mRadioFaXianWomen.setChecked(true);

				break;
			case 8:
				mRadioShangchengWomen.setChecked(true);

				break;
			case 9:
				mRadioServeWomen.setChecked(true);

				break;

			case 10:

				mRadioUserWomen.setChecked(true);

				break;


		}

	}


	/**
	 * 获取设备信息  极光推送
	 */

	private void insertUserInfo() {
		//安卓手机设备号
		String device = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);


		String user_id = AppUtils.get().getString("user_id", "");
		String token = AppUtils.get().getString("token", "");

		String currentTime_today = TimeUtils.getCurrentTime_Today();

		String timeStame = TimeUtils.dataOne(currentTime_today);

		String AESUserid = null;
		String AEStoken = null;
		String AESdevice = null;

		try {

			String twoToken = user_id + "," + token + "," + timeStame;
			if (!user_id.equals("") || !token.equals("")) {
				AESUserid = AESUtils.Encrypt(user_id, BaseUrl.AESKey);
				AEStoken = AESUtils.Encrypt(twoToken, BaseUrl.AESKey);

			}

			AESdevice = AESUtils.Encrypt(registrationID, BaseUrl.AESKey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, String> params = new HashMap<>();
		params.put("action", "InsertUserInfo");
		params.put("registration_id", AESdevice);
		params.put("user_id", AESUserid);
		params.put("token", AEStoken);
		params.put("longitude", String.valueOf(longitude));//经度
		params.put("latitude", String.valueOf(latitude));//纬度

		App.myOkHttp.post().url(BaseUrl.BaseUrl)
				.params(params)
				.enqueue(new RawResponseHandler() {
					@Override
					public void onSuccess(int statusCode, String response) {
						Log.d("HeHeMainActivity", response);
					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});

	}


	/**
	 * 获取版本号
	 *
	 * @param mContext
	 * @return
	 */
	public static int getVersionCode(Context mContext) {
		int versionCode = 0;
		try {
			//获取软件版本号，对应AndroidManifest.xml下android:versionCode
			versionCode = mContext.getPackageManager().
					getPackageInfo(mContext.getPackageName(), 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return versionCode;
	}



	/**
	 * 更新APP
	 */
	private void UploadApp() {

		String url = "http://hehe.heyishenghuo.com/getversion.php?type=1";

			OkHttpClient okHttpClient = new OkHttpClient();

			Request request = new Request.Builder()
					.get()
					.url(url)
					.build();


			okHttpClient.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {

					final String string = response.body().string();

					Log.d("SplashActivity", string);

					runOnUiThread(new Runnable() {
						@Override
						public void run() {

							Gson gson = new Gson();
							UpdateBean updateBean = gson.fromJson(string, UpdateBean.class);
							if (updateBean == null || updateBean.getData() == null) {
								return;
							} else if (!updateBean.getStatus().equals("200")) {
								return;
							}
							String version = updateBean.getData().getVersion();
							int versionCode = Integer.parseInt(version);

							Log.d("HeHeMainActivity", "versionCode获取的:" + versionCode);
							Log.d("HeHeMainActivity", "appversionCode App的:" + appversionCode);

							if (appversionCode < versionCode) {

								showDialog();

							}
						}
					});


				}
			});


	}


	public void showDialog() {
		//AlertDialog的创建用到AlertDialog.Builder，AlertDialog.Builder构造函数中的Context必须传Activity的实例(先记着)
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//设置对话框标题，该标题会显示在标题区域中
		builder.setTitle("版本升级")
				.setCancelable(false)
				.setMessage("发现新版本！请及时更新")
				//                .setOnKeyListener(keylistener)
				//设置对话框图标，该标题会显示在标题区域中
				//setMessage方法中的内容会显示在内容区域中
				/*以下三个setXXXButton(CharSequence text, final OnClickListener listener)方法
				   都向对话框的按钮区域添加了一个按钮，方法的第一个参数是按钮文本，第二个是按钮点击监听器。
                   注意按钮的顺序和代码的添加顺序无关，按钮的位置是固定的(如图1)，只有调用了对应的setXXXButton()
                   方法该按钮才显示。
                */
				.setNegativeButton("确定", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						Intent intent = new Intent();
						intent.setAction("android.intent.action.VIEW");
						Uri content_url = Uri.parse("http://hehe.heyishenghuo.com/app_down.php");
						intent.setData(content_url);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);


					}
				})
				.setNeutralButton("取消", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.dismiss();

						//timer.schedule(timerTask, 2000, 2500);
						//                        finish();
						//                        System.exit(0);

					}
				})
				//真正实例化AlertDialog对象
				.create()
				//显示对话框
				.show();


	}


	@Override
	protected void initData() {
		getAllpermission();


	}

	@Override
	protected void initLisenter() {
		//        viewpagerListener();
	}


	private void mInit(Fragment fragment) {

		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.Man_frameLayout, fragment).commit();

	}

	//sp  男性首页
	private void SpManFragment() {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		lifeFragment = new HeHeLifeCircleFragment();
		transaction.add(R.id.Man_frameLayout, lifeFragment, "");
		transaction.commit();

	}


	//sp  女性首页

	private void SpWomenFragment() {

		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		womenFragment = new HeHeNewLifeWomenFragment();

		transaction.add(R.id.Man_frameLayout, womenFragment, "");
		transaction.commit();
	}


	private void SpUserMan() {

		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		if (userFragment == null) {
			userFragment = new HeHeUserFragment();

			transaction.add(R.id.Man_frameLayout, userFragment).commit();

		} else {

			transaction.hide(lifeFragment);
			transaction.show(userFragment);
			transaction.commit();
		}


	}


	//首页跳转切换
	private void SpUserWomen() {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();

		if (userWomenFragment == null) {
			userWomenFragment = new HeHeUserWomenFragment();

			transaction.add(R.id.Man_frameLayout, userWomenFragment).commit();

		} else {
			transaction.hide(womenFragment);
			transaction.show(userWomenFragment);

			transaction.commit();
		}


	}


	@OnClick({R.id.mRadio_HeheLife, R.id.mRadio_Shangcheng, R.id.mRadio_FaXian, R.id.mRadio_User, R.id.mRadio_Serve
			, R.id.mRadio_HeheLife_women, R.id.mRadio_FaXian_women, R.id.mRadio_Shangcheng_women, R.id.mRadio_Serve_women, R.id.mRadio_User_women
	})
	public void onViewClicked(View view) {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		HideAllFragment(transaction);
		switch (view.getId()) {
			case R.id.mRadio_HeheLife:
				RESUME_TAG = 1;

				sexFlag = 0;  //这是判断 0 是首页男女的切换


				if (lifeFragment == null) {
					lifeFragment = new HeHeLifeCircleFragment();
					transaction.add(R.id.Man_frameLayout, lifeFragment, "");
				} else {
					//                    if (womenFragment != null) {
					//                        transaction.hide(womenFragment);
					//                    }

					transaction.show(lifeFragment);
				}

				break;
			case R.id.mRadio_Shangcheng:
				RESUME_TAG = 2;
				if (shoppingFragment == null) {
					shoppingFragment = new HeHeShoppingFragment();
					transaction.add(R.id.Man_frameLayout, shoppingFragment, "");
				} else {
					transaction.show(shoppingFragment);
				}
				break;
			case R.id.mRadio_FaXian:
				RESUME_TAG = 3;
				if (faXianFragment == null) {
					faXianFragment = new HeHeHealthFaXianFragment();
					transaction.add(R.id.Man_frameLayout, faXianFragment, "");
				} else {
					transaction.show(faXianFragment);
				}
				break;

			case R.id.mRadio_Serve:
				RESUME_TAG = 4;

				if (serveFragment == null) {
					serveFragment = new HeHeServeFragment();
					transaction.add(R.id.Man_frameLayout, serveFragment, "");
				} else {
					transaction.show(serveFragment);
				}


				break;


			case R.id.mRadio_User:
				RESUME_TAG = 5;

				sexFlag = 1;

				String user_id = AppUtils.get().getString("user_id", "");
				String token = AppUtils.get().getString("token", "");


				if (token.equals("") || user_id.equals("")) {
					Intent intent = new Intent(this, LoginActivity.class);
					startActivity(intent);
				} else if (userFragment == null) {
					userFragment = new HeHeUserFragment();
					transaction.add(R.id.Man_frameLayout, userFragment, "");
				} else {
					//                    if (userWomenFragment != null) {
					//                        transaction.hide(userWomenFragment);
					//                    }
					transaction.show(userFragment);

				}


				break;


            /*
			女性的点击事件
             */

			case R.id.mRadio_HeheLife_women:
				RESUME_TAG = 6;

				if (womenFragment == null) {
					womenFragment = new HeHeNewLifeWomenFragment();
					transaction.add(R.id.Man_frameLayout, womenFragment, "");
				} else {
					if (lifeFragment != null) {
						transaction.hide(lifeFragment);
					}
					transaction.show(womenFragment);
				}

				break;

			case R.id.mRadio_FaXian_women:
				RESUME_TAG = 7;
				if (faXianFragment == null) {
					faXianFragment = new HeHeHealthFaXianFragment();
					transaction.add(R.id.Man_frameLayout, faXianFragment, "");
				} else {

					transaction.show(faXianFragment);
				}
				break;

			case R.id.mRadio_Shangcheng_women:
				RESUME_TAG = 8;
				if (shoppingFragment == null) {
					shoppingFragment = new HeHeShoppingFragment();
					transaction.add(R.id.Man_frameLayout, shoppingFragment, "");
				} else {

					transaction.show(shoppingFragment);
				}

				break;

			case R.id.mRadio_Serve_women:
				RESUME_TAG = 9;


				if (serveFragment == null) {
					serveFragment = new HeHeServeFragment();
					transaction.add(R.id.Man_frameLayout, serveFragment, "");
				} else {
					transaction.show(serveFragment);
				}


				break;

			case R.id.mRadio_User_women:
				RESUME_TAG = 10;

				if (this.token == null || this.token.isEmpty() || this.token.equals("") || this.user_id.equals("")) {
					Intent intent = new Intent(this, LoginActivity.class);
					startActivity(intent);
				} else if (userWomenFragment == null) {
					userWomenFragment = new HeHeUserWomenFragment();
					transaction.add(R.id.Man_frameLayout, userWomenFragment, "");
				} else {
					if (userFragment != null) {
						transaction.hide(userFragment);
					}
					transaction.show(userWomenFragment);

				}

				break;


		}
		transaction.commit();
	}

	private int firstFlag = 0;

	/**
	 * 获取男女值
	 */

	private void getSex() {


		String sex = AppUtils.get().getString("sex", "");

		Log.d("HeHeMainActivity", "首页默认获取的性别》》》》》》  " + sex);

//		if ("2".equals(sex)) {
//			womenRadioGroup.setVisibility(View.VISIBLE);
//			ManradioGroup.setVisibility(View.GONE);
//			SpWomenFragment();
//			mRadioHeheLifeWomen.setChecked(true);
//		} else {
//			womenRadioGroup.setVisibility(View.GONE);
//			ManradioGroup.setVisibility(View.VISIBLE);
//
//			SpManFragment();
//			mRadioHeheLife.setChecked(true);
//		}

		SpManFragment();
	}


	/**
	 * 隐藏fragment
	 *
	 * @param transaction
	 */
	public void HideAllFragment(FragmentTransaction transaction) {
		if (lifeFragment != null) {
			transaction.hide(lifeFragment);
		}
		if (shoppingFragment != null) {
			transaction.hide(shoppingFragment);
		}
		if (faXianFragment != null) {
			transaction.hide(faXianFragment);
		}
		if (userFragment != null) {
			transaction.hide(userFragment);

		}
		if (womenFragment != null) {
			transaction.hide(womenFragment);
		}
		if (userWomenFragment != null) {
			transaction.hide(userWomenFragment);
		}
		if (serveFragment != null) {
			transaction.hide(serveFragment);
		}


	}


	public void HideManFragment() {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		if (lifeFragment != null) {
			transaction.hide(lifeFragment);
		}
		if (userFragment != null) {
			transaction.hide(userFragment);
		}

		transaction.commit();
	}

	public void HideWomenFragment() {
		FragmentManager manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		if (lifeFragment != null) {
			transaction.hide(lifeFragment);
		}
		if (userFragment != null) {
			transaction.hide(userFragment);
		}

		transaction.commit();
	}


/**
 * 设置登录过后切换页面
 *
 * @param bean
 * <p>
 * 切换男女的传值
 * @param bean
 * <p>
 * 切换男女的传值
 * @param bean
 */

//    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
//    public void MessageBean(EventMessageBean bean) {
//
//
//        FragmentManager manager = getSupportFragmentManager();
//
//        FragmentTransaction fragmentTransaction = manager.beginTransaction();
//
//        if (userFragment == null) {
//            userFragment = new HeHeUserFragment();
//            fragmentTransaction.add(R.id.Man_frameLayout, userFragment, "").commitAllowingStateLoss();
//        } else {
//            fragmentTransaction.show(userFragment).commitAllowingStateLoss();
//        }
//
//    }


//	/**
//	 * 切换男女的传值
//	 *
//	 * @param bean
//	 */
//	@Subscribe(threadMode = ThreadMode.POSTING)
//	public void SexBeanEvent(EventSexBean bean) {
//
//		String sex = bean.getSex();
//
//		Log.d("HeHeMainActivity", "EventBus>>>>>>>>>>>   " + sex);
///**
// * 切换男女的传值
// *
// * @param bean
// */
//
//		if ("女".equals(sex)) {
//			SpUserSexWomen();
//			womenRadioGroup.setVisibility(View.VISIBLE);
//			ManradioGroup.setVisibility(View.GONE);
//		} else if ("男".equals(sex)) {
//
//			SpUserSexMan();
//			womenRadioGroup.setVisibility(View.GONE);
//			ManradioGroup.setVisibility(View.VISIBLE);
//		}
//
//
//	}

	/**
	 * 设置首页跳转到个人中心
	 */
	@Subscribe(sticky = true, threadMode = ThreadMode.POSTING)
	public void UserIntent(EventIntentUserBean bean) {

		String userIntent = bean.getUserIntent();


		if (userIntent.equals("跳转")) {
			SpUserMan();
			mRadioUser.setChecked(true);

		} else if (userIntent.equals("女跳转")) {
			mRadioUserWomen.setChecked(true);
			SpUserWomen();
		}

	}


	//
//    // 女性个人中心
	private void WomenUserFragment() {
		fragmentManager = getSupportFragmentManager();

		FragmentTransaction transaction = fragmentManager.beginTransaction();

		//        transaction.replace(R.id.Man_frameLayout,userWomenFragment).commit();
		if (userWomenFragment == null) {

			userWomenFragment = new HeHeUserWomenFragment();

			transaction.add(R.id.Man_frameLayout, userWomenFragment, "").commit();
		} else {
			if (userFragment != null) {

				transaction.hide(userFragment);
			} else
				transaction.show(userWomenFragment);

			transaction.commit();
		}


	}

	//
//    //男性个人中心
	private void ManUserFragment() {
		fragmentManager = getSupportFragmentManager();

		FragmentTransaction transaction = fragmentManager.beginTransaction();


		if (userFragment == null) {

			userFragment = new HeHeUserFragment();

			transaction.add(R.id.Man_frameLayout, userFragment, "").commit();
		} else {
			if (userWomenFragment != null) {

				transaction.hide(userWomenFragment);
			} else
				transaction.show(userFragment);

			transaction.commit();
		}

	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	//    //记录用户首次点击返回键的时间
	private long firstTime = 0;

	/**
	 * 第一种解决办法 通过监听keyUp
	 *
	 * @param keyCode
	 * @param event
	 * @return
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
			long secondTime = System.currentTimeMillis();
			if (secondTime - firstTime > 2000) {
				Toast.makeText(HeHeMainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				firstTime = secondTime;
				return true;
			} else {
				//                System.exit(0);
				finish();
			}
		}
		return super.onKeyUp(keyCode, event);
	}


	public RadioGroup getManradioGroup() {
		return ManradioGroup;
	}

	public RadioGroup getWomenRadioGroup() {
		return womenRadioGroup;
	}

	public RadioButton getmRadioUser() {
		return mRadioUser;
	}

	public RadioButton getmRadioUserWomen() {
		return mRadioUserWomen;
	}


}
