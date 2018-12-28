package com.example.heyikun.heheshenghuo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.signature.StringSignature;
import com.example.heyikun.heheshenghuo.controller.adapter.ViewPagerAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.UpdateBean;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hyk on 2017/10/27.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/27
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class SplashActivity extends AppCompatActivity {

	@BindView(R.id.Splash_Image)
	ImageView SplashImage;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	// 底部小点图片
	private ImageView[] dots;

	// 记录当前选中位置
	private int currentIndex;
	Boolean isFirst;

	private final int SPLASH_DISPLAY_LENGHT = 2000;
	private Handler handler;

	private Button mTextTimer;
	private TimeCount timeCount;
	private int versionCode;
	private int versionCode1;
	private String uplink;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_activity);
		ButterKnife.bind(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		timeCount = new TimeCount(5000, 1000);
		// 初始化底部小点

		initViews();


		versionCode = getVersionCode(this);

		Log.d("SplashActivity", "versionCode:" + versionCode);


	}

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
	 * 获取版本号
	 *
	 * @return 当前应用的版本号
	 */
	public String getVersion() {
		try {
			PackageManager manager = this.getPackageManager();
			PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
			String version = info.versionName;
			return this.getString(R.string.app_name) + version;
		} catch (Exception e) {
			e.printStackTrace();
			return this.getString(R.string.app_name);
		}
	}


	public void showDialog() {
		//AlertDialog的创建用到AlertDialog.Builder，AlertDialog.Builder构造函数中的Context必须传Activity的实例(先记着)
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//设置对话框标题，该标题会显示在标题区域中
		builder.setTitle("版本升级")
				.setCancelable(false)
				.setMessage("发现新版本！请及时更新")
				.setOnKeyListener(keylistener)
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

						//                        finish();
						//                        System.exit(0);

					}
				})
				//真正实例化AlertDialog对象
				.create()
				//显示对话框
				.show();


	}

	DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
		public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
				System.exit(0);
				return true;
			} else {
				System.exit(0);
				return false;
			}
		}
	};

	/**
	 * 启动页
	 */


	/**
	 * 引导图
	 */
	private void initViews() {
		String imageUrl = "http://hehe.heyishenghuo.com/mobile2/getimage.php";

		timeCount.start();

//		SharedPreferences pref = getSharedPreferences("data", Activity.MODE_PRIVATE);
//		isFirst = pref.getBoolean("status", true);

		//        if (!isFirst) {
		//            splashImage();
		//
		//            Log.d("SplashActivity", "isFirst:" + isFirst);
		//
		//        } else {


		Glide.with(this)
				.load(imageUrl)
				.placeholder(R.drawable.splash_image)
				.error(R.drawable.splash_image)
				.skipMemoryCache(true)
				.diskCacheStrategy(DiskCacheStrategy.NONE)
				.centerCrop()
				.into(SplashImage);

	}

//
//	private void UpdateData() {
//
//		OkHttpClient okHttpClient = new OkHttpClient();
//
//		Request request = new Request.Builder()
//				.get()
//				.url("http://hehe.heyishenghuo.com/getversion.php?type=1")
//				.build();
//		okHttpClient.newCall(request).enqueue(new Callback() {
//			@Override
//			public void onFailure(Call call, IOException e) {
//
//			}
//
//			@Override
//			public void onResponse(Call call, Response response) throws IOException {
//
//				final String string = response.body().string();
//
//				Log.d("SplashActivity", string);
//
//				runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//
//						Gson gson = new Gson();
//						UpdateBean updateBean = gson.fromJson(string, UpdateBean.class);
//						if (updateBean == null || updateBean.getData() == null) {
//							return;
//						} else if (!updateBean.getStatus().equals("200")) {
//							return;
//						}
//						String version = updateBean.getData().getVersion();
//						versionCode1 = Integer.parseInt(version);
//						Log.d("SplashActivity", "versionCode1:获取后台的" + versionCode1);
//						uplink = updateBean.getData().getUplink();
//						if (versionCode < versionCode1) {
//
//							showDialog();
//
//						}
//
//
//					}
//				});
//
//
//			}
//		});
//
//
//	}


	//倒计时发送验证码
	class TimeCount extends CountDownTimer {

		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {

//			mTextTimer.setText("(" + millisUntilFinished / 1000 + ")秒跳转");
		}

		@Override
		public void onFinish() {
			Intent intent = new Intent(SplashActivity.this, HeHeMainActivity.class);
			startActivity(intent);
			finish();
		}
	}


	@Override
	protected void onStop() {
		super.onStop();

		if (timeCount != null) {
			timeCount.cancel();
			timeCount = null;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (timeCount != null) {
			timeCount.cancel();
			timeCount = null;
		}


	}
}
