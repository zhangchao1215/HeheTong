package com.example.heyikun.heheshenghuo.controller.activity.login;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.HeHeMainActivity;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.user.PhoneVerifyPwdActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.login.LoginMessageBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBLoginBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.Code;
import com.example.heyikun.heheshenghuo.modle.util.LoadingDialog;
import com.example.heyikun.heheshenghuo.modle.util.LocationUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.PermissionsChecker;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jiguang.api.JCoreInterface;

/**
 * Created by hyk on 2017/9/19.
 */

public class LoginActivity extends Activity {


	@BindView(R.id.Login_finish)
	TextView LoginFinish;
	@BindView(R.id.Login_Edit_PhoneNumer)
	EditText LoginEditPhoneNumer;
	@BindView(R.id.Login_Edit_Pwd)
	EditText LoginEditPwd;
	@BindView(R.id.Login_Edit_authcode)
	EditText LoginEditAuthcode;
	@BindView(R.id.Login_mBut)
	Button LoginMBut;
	@BindView(R.id.Register_hehe)
	TextView RegisterHehe;
	@BindView(R.id.Forger_Pwd)
	TextView ForgerPwd;
	@BindView(R.id.Look_Pwd)
	ImageView LookPwd;
	@BindView(R.id.iv_showCode)
	ImageView ivShowCode;
	@BindView(R.id.mview)
	View mview;
	private String user_id;
	private String token;
	private String mEncryptTime;
	private String timestamp;
	private String passWord;
	private LoginMessageBean bean;
	private String realCode;
	private double latitude;
	private double longitude;

	// 所需的全部权限
	static final String[] PERMISSIONS = new String[]{
			Manifest.permission.ACCESS_COARSE_LOCATION,
			Manifest.permission.ACCESS_FINE_LOCATION
	};
	private String userPhone;
	private String registrationID;
	private LoadingDialog dialog;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hehe_login);
		ButterKnife.bind(this);
		dialog = new LoadingDialog(this);

		initView();
		initData();
		initLisenter();


	}


	private void initView() {
		ivShowCode.setImageBitmap(Code.getInstance().createBitmap());
		realCode = Code.getInstance().getCode().toLowerCase();
		registrationID = JCoreInterface.getRegistrationID(this);

		Log.d("HeHeMainActivity", "极光推送id   " + registrationID);
	}

	private void initData() {

		PermissionsChecker checker = new PermissionsChecker(this);

		if (checker.lacksPermissions(PERMISSIONS)) {

			AndPermission.with(this)
					.requestCode(100)
					.permission(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
					.callback(new PermissionListener() {
						@Override
						public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

						}

						@Override
						public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

						}
					});

		}

		Location location = LocationUtils.getInstance(this).showLocation();

		try

		{
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			Log.d("LoginActivity", "latitude:" + latitude);
			Log.d("LoginActivity", "longitude:" + longitude);
		} catch (
				Exception e)

		{
			e.printStackTrace();
		}

	}


	protected void initLisenter() {
	}

	@Override
	protected void onStart() {
		super.onStart();
		//注册EventBus
	}

	private int flag = 0;

	@OnClick({R.id.Login_finish, R.id.Login_mBut, R.id.Register_hehe, R.id.Forger_Pwd, R.id.Look_Pwd, R.id.iv_showCode})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.Login_finish:
				Intent intent = new Intent(this, HeHeMainActivity.class);
				AppUtils.put().putString("userSex", "首页").commit();

				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);

				finish();

				break;
			case R.id.Login_mBut:
				//登录
				ILogin();
				break;
			case R.id.Register_hehe:
				Intent intent1 = new Intent(this, RegisterActivity.class);
				startActivity(intent1);

				break;

			case R.id.Forger_Pwd:
				//忘记密码
				//                Token2();

				Intent in = new Intent(this, PhoneVerifyPwdActivity.class);
				startActivity(in);


				break;

			//查看密文显示的密码
			case R.id.Look_Pwd:
				if (flag == 0) {
					LoginEditPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
					LookPwd.setImageResource(R.drawable.pwd_visible);

					flag = 1;
				} else {
					LoginEditPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
					LookPwd.setImageResource(R.drawable.pwd_gone);

					flag = 0;
				}


				break;

			//随机生成的图片验证码
			case R.id.iv_showCode:

				ivShowCode.setImageBitmap(Code.getInstance().createBitmap());
				realCode = Code.getInstance().getCode().toLowerCase();

				break;

		}
	}


	private void ILogin() {


		userPhone = LoginEditPhoneNumer.getText().toString().trim();

		passWord = LoginEditPwd.getText().toString().trim();

		String authCode = LoginEditAuthcode.getText().toString().trim();

		if (userPhone.isEmpty() || passWord.isEmpty()) {
			Toast.makeText(this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
			return;
		}
		if (userPhone.length() > 11 || userPhone.length() < 11) {
			Toast.makeText(this, "手机号位数错误，请重新输入", Toast.LENGTH_SHORT).show();
			return;
		}
		if (passWord.length() < 6) {
			Toast.makeText(this, "输入密码位数不够，请重新输入", Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			//对账号进行AES加密
			String PhoneEncrypt = AESUtils.Encrypt(userPhone, BaseUrl.AESKey);
			//对密码进行base64加密 在进行AES加密
			String BasePwd = Base64.encodeToString(passWord.getBytes(), Base64.DEFAULT);

			final String Pwdencrypt = AESUtils.Encrypt(BasePwd, BaseUrl.AESKey);

			//获取时间戳
			String currentTime_today = TimeUtils.getCurrentTime_Today();
			timestamp = TimeUtils.dataOne(currentTime_today);
			//时间戳进行AES加密
			mEncryptTime = AESUtils.Encrypt(timestamp, BaseUrl.AESKey);

			//对签名进行md5加密 ，在进行AES加密

			String md5 = userPhone + passWord + timestamp + BaseUrl.AESKey;

			String Md5encrypt = MD5Utils.encrypt(md5);


			AppUtils.put().putString("app_sign", Md5encrypt);

			AppUtils.put().commit();


			dialog.show();

			//对签名AES进行加密
			String sign = AESUtils.Encrypt(Md5encrypt, BaseUrl.AESKey);

			Map<String, String> map = new HashMap<>();
			map.put("action", "aLogin");
			map.put("user_name", PhoneEncrypt);
			map.put("pass", Pwdencrypt);
			map.put("timestamp", mEncryptTime);
			map.put("app_sign", sign);
			OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, map, "", new MyCallBack() {
				@Override
				public void onSuccess(String data) {
					Log.d("LoginActivity", data);
					Gson gson = new Gson();
					bean = gson.fromJson(data, LoginMessageBean.class);
					if (bean == null) {
						return;
					}
					if (bean.getStatus().equals("200")) {


						if (dialog.isShowing()) {
							dialog.dismiss();
						}

						//登录成功后把设备信息发送给极光
						insertUserInfo();

						LoginMessageBean.DataBean beanData = bean.getData();

						//得到userID与token
						Toast.makeText(LoginActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();

						String user_id = beanData.getUser_id();
						token = beanData.getToken();


						//登录成功之后把手机号存入sp中。这是得到正确的手机号

						AppUtils.put().putString("phone", LoginEditPhoneNumer.getText().toString().trim()).commit();


						Intent intent = new Intent(LoginActivity.this, HeHeMainActivity.class);


						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);


						//得到Token进行解密 保存在本地，并用SP传值
						String mtoken = null;
						try {
							mtoken = AESUtils.Decrypt(token, BaseUrl.AESKey);


						} catch (Exception e) {
							e.printStackTrace();
						}
						String sex = beanData.getSex();
						AppUtils.put().putString("PassWord", passWord);
						AppUtils.put().putString("token", mtoken);
						AppUtils.put().putString("user_id", user_id);
						AppUtils.put().putString("user_name", beanData.getUser_name());
						AppUtils.put().putString("user_image", beanData.getHeadimg());
						AppUtils.put().putString("Email", beanData.getEmail());

						AppUtils.put().putString("sex", sex);

						AppUtils.put().commit();

						//使用EventBus进行传值， 传递数据到个人中心页面

						EBLoginBean user = new EBLoginBean();

						user.setBrief(beanData.getAlias());

						user.setAwait_comment(beanData.getAwait_comment());

						user.setAwait_pay(beanData.getAwait_pay());

						user.setAwait_receipt(beanData.getAwait_receipt());

						user.setAwait_ship(beanData.getAwait_ship());

						user.setPayback(beanData.getPayback());

						user.setBigcast(beanData.getBigcast());

						user.setFans(beanData.getFans());

						user.setFollow(beanData.getFollow());

						user.setHeTicket(beanData.getFrozen_money());

						user.setYiTicket(beanData.getPay_points());

						user.setKunTicket(beanData.getKun());

						user.setReplay(beanData.getReplay());

						user.setProfit(beanData.getProfit());

						user.setUserMoney(beanData.getUser_money());

						user.setTheme(beanData.getTheme());

						EventBus.getDefault().postSticky(user);

					} else {
						Toast.makeText(LoginActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();


						if (dialog.isShowing()) {
							dialog.dismiss();
						}

					}

				}

				@Override
				public void onError(String error) {

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	//生成二次Token
	private void Token2() {
		try {

			String Token = user_id + "," + token + "," + timestamp;
			Log.d("LoginActivity拼接的Token", Token);
			String AESToken = AESUtils.Encrypt(Token, BaseUrl.AESKey);

			Log.d("LoginActivity二次token", AESToken);


			/**
			 * QkjqBl+7Jnk+l/ow7LfYN4TFx9T5vPZDub3G2xX4RfAtTJ5hmME/Xb8rQvr/ZNeCFEeA/2JzIuMQ
			 YpOnI64UUg==
			 *
			 */

			Map<String, String> params = new HashMap<>();
			params.put("action", "Proof");
			params.put("token", AESToken);

			OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
				@Override
				public void onSuccess(String data) {
					Log.d("LoginActivity二次Token", data);
				}

				@Override
				public void onError(String error) {

				}
			});


		} catch (Exception e) {
			e.printStackTrace();
		}


	}


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
						Log.d("LoginActivity", response);
					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});

	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();

	}

	//退出时的时间
	private long mExitTime;

//	//对返回键进行监听
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//			Intent intent = new Intent(this, HeHeMainActivity.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//			AppUtils.put().putString("userSex", "首页").commit();
//			startActivity(intent);
//			finish();
//			//            exit();
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
	//


	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			Intent intent = new Intent(this, HeHeMainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			return true;
		}

		return super.onKeyUp(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (dialog != null) {
			dialog.cancel();
			dialog = null;
		}
	}
}