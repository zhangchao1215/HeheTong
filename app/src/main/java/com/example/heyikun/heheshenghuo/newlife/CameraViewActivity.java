package com.example.heyikun.heheshenghuo.newlife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.BuildConfig;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.BitmapUtils;
import com.example.heyikun.heheshenghuo.modle.util.CameraUtil;
import com.example.heyikun.heheshenghuo.modle.util.LoadingDialog;
import com.example.heyikun.heheshenghuo.modle.util.ResizeAbleSurfaceView;
import com.example.heyikun.heheshenghuo.modle.util.SystemUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.R.id.flash_light;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/13 10:45
 * description：上传图片页面
 */

public class CameraViewActivity extends Activity implements SurfaceHolder.Callback {

	@BindView(R.id.surfaceView)
	ResizeAbleSurfaceView surfaceView;
	@BindView(R.id.img_camera)
	ImageView imgCamera;

	@BindView(R.id.homecamera_bottom_relative)
	RelativeLayout homecameraBottomRelative;
	@BindView(R.id.camera_frontback)
	ImageView cameraFrontback;
	@BindView(R.id.mImage_dismiss)
	ImageView mImageDismiss;
	@BindView(R.id.flash_light)
	ImageView flashLight;
	@BindView(R.id.mImage_left)
	ImageView mImageLeft;
	@BindView(R.id.mImage_right)
	ImageView mImageRight;
	@BindView(R.id.mLinear_left)
	LinearLayout mLinearLeft;
	@BindView(R.id.mLinear_right)
	LinearLayout mLinearRight;
	@BindView(R.id.mImage_commit)
	ImageView mImage_commit;
	@BindView(R.id.mText_left)
	TextView mText_left;
	@BindView(R.id.mText_Right)
	TextView mText_right;
	@BindView(R.id.leftImage)
	ImageView leftImage;
	@BindView(R.id.rightImage)
	ImageView rightImage;
	@BindView(R.id.mImage_handOne)
	ImageView mImageHandOne;


	private SurfaceHolder mHolder;
	private int mCameraId = 0;
	private Context context;
	private Camera mCamera;
	//闪光灯模式 0:关闭 1: 开启 2: 自动
	private int light_num = 0;
	//屏幕宽高
	private int screenWidth;
	private int screenHeight;
	private int picHeight;
	private boolean isview = false;
	private String mImagePathOne = "";
	private String mImagePathTwo = "";
	private int count = 1;
	private String user_id;
	private String token;
	private String encryptUserId;
	private String mTokenTwo;
	private String encryptToken;
	private LoadingDialog dialog;
	private int position1 = -1;
	private int position2 = -1;
	private String imageType;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_view);
		ButterKnife.bind(this);

		dialog = new LoadingDialog(this);

		context = this;

		initView();
	}


	private void initView() {

		Intent intent = getIntent();
		imageType = intent.getStringExtra("imageType");

		switch (imageType) {
			case "hand":

				// 如果是手部照片的话， 把其余的view进行隐藏，展示手部的view图片
				mImageHandOne.setImageResource(R.drawable.hand);
//				rightImage.setImageResource(R.drawable.hand_right);
				mImageHandOne.setVisibility(View.VISIBLE);

				mLinearRight.setVisibility(View.GONE);
				mLinearLeft.setVisibility(View.GONE);
				position1 = 3;
				position2 = 4;

				break;
			case "head":

				leftImage.setImageResource(R.drawable.face_one);
				rightImage.setImageResource(R.drawable.face_two);

				mText_left.setText("面部正面");
				mText_right.setText("面部侧面");
				position1 = 1;
				position2 = 2;

				break;
			case "shetai":

				leftImage.setImageResource(R.drawable.shetai_up);
				rightImage.setImageResource(R.drawable.shetai_down);

				mText_left.setText("舌苔上部");
				mText_right.setText("舌苔下部");

				position1 = 5;
				position2 = 6;

				break;

		}


		user_id = AppUtils.get().getString("user_id", "");
		token = AppUtils.get().getString("token", "");

		mHolder = surfaceView.getHolder();
		mHolder.addCallback(this);
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;


		Log.d("CameraViewActivity","user_id >》》》》》》》》 " + user_id);

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mCamera == null) {
			mCamera = getCamera(mCameraId);
			if (mHolder != null) {
				startPreview(mCamera, mHolder);
			}
		}
	}

	/**
	 * 在页面暂停的时候释放资源
	 */
	@Override
	protected void onPause() {
		super.onPause();
		releaseCamera();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		startPreview(mCamera, holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		mCamera.stopPreview();
		startPreview(mCamera, holder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		releaseCamera();
	}


	@OnClick({R.id.img_camera, R.id.flash_light, R.id.camera_frontback, R.id.mImage_dismiss
			, R.id.mImage_left, R.id.mImage_right, R.id.mImage_commit
	})
	public void onViewClicked(View view) {
		switch (view.getId()) {

			case R.id.mImage_commit:
				upImageData();
				break;

			case R.id.mImage_left:

				mIntent(mImagePathOne, "1");


				break;

			case R.id.mImage_right:
				mIntent(mImagePathTwo, "2");
				break;


			//拍照
			case R.id.img_camera:


				switch (light_num) {
					case 0:
						//关闭
						CameraUtil.getInstance().turnLightOff(mCamera);
						break;
					case 1:
						CameraUtil.getInstance().turnLightOn(mCamera);
						break;
					case 2:
						//自动
						CameraUtil.getInstance().turnLightAuto(mCamera);
						break;
				}
				//开始拍照
				captrue();


				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {

						startPreview(mCamera, mHolder);
					}
				}, 1000);

				break;
			//打开闪光灯
			case flash_light:
				if (mCameraId == 1) {
					//前置
					Toast.makeText(context, "请切换为后置摄像头开启闪光灯", Toast.LENGTH_SHORT);
					return;
				}
				Camera.Parameters parameters = mCamera.getParameters();
				switch (light_num) {
					case 0:
						//打开
						light_num = 1;
						parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);//开启
						mCamera.setParameters(parameters);
						break;
					case 1:
						//自动
						light_num = 2;
						parameters.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
						mCamera.setParameters(parameters);
						break;
					case 2:
						//关闭
						light_num = 0;
						//关闭
						parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
						mCamera.setParameters(parameters);
						break;
				}
				break;
			//相机翻转
			case R.id.camera_frontback:
				switchCamera();

				break;

			case R.id.mImage_dismiss:

				finish();

				break;
		}
	}


	/**
	 * 上传图片
	 */
	private void upImageData() {

		if (imageType.equals("hand")) {

			if (mImagePathOne.equals("")) {
				Toast.makeText(context, "请上传手部照片", Toast.LENGTH_SHORT).show();
				return;
			}
		} else {
			if (mImagePathOne.equals("") || mImagePathTwo.equals("")) {
				Toast.makeText(context, "请上传完整照片", Toast.LENGTH_SHORT).show();

				return;
			}
		}
		dialog.show();

		//获取时间戳
		String currentTime_today = TimeUtils.getCurrentTime_Today();
		String timestamp = TimeUtils.dataOne(currentTime_today);

		try {

			//给userID 还有 生成二次token ，在进行AES加密
			encryptUserId = AESUtils.Encrypt(user_id, BaseUrl.AESKey);

			mTokenTwo = user_id + "," + token + "," + timestamp;


			encryptToken = AESUtils.Encrypt(mTokenTwo, BaseUrl.AESKey);


		} catch (Exception e) {
			e.printStackTrace();
		}

		Log.d("CameraViewActivity", "user_id》》》》" + user_id);
		Log.d("CameraViewActivity", "encryptUserId>>>>> " + encryptUserId);
		Log.d("CameraViewActivity", "token>>>>" + token);
		Log.d("CameraViewActivity", "encryptToken》》》》 " + encryptToken);


		Map<String, String> params = new HashMap<>();
		params.put("action", "Featuresimg");
		params.put("user_id", encryptUserId);
		params.put("token", encryptToken);

		if (imageType.equals("hand")) {
			params.put("file1", mImagePathOne);
			params.put("position1", String.valueOf(position1));

		} else {
			params.put("file1", mImagePathOne);
			params.put("file2", mImagePathTwo);
			params.put("position1", String.valueOf(position1));
			params.put("position2", String.valueOf(position2));

		}

		Log.d("CameraViewActivity", mImagePathOne + "  上传的图片      " + mImagePathTwo);

		Log.d("CameraViewActivity", "position1+position2:" + (position1 + "   上传的索引       " + position2));



		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {
				Log.d("CameraViewActivity", "??????>>>>>>>>上传图片  " + data);

				try {
					JSONObject jsonObject = new JSONObject(data);
					String status = jsonObject.getString("status");
					String message = jsonObject.getString("message");
					if (status.equals("200")) {
						Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

						if (dialog.isShowing()) {
							dialog.dismiss();
						}
						finish();
					} else {
						Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

						if (dialog.isShowing()) {
							dialog.dismiss();
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError(String error) {

			}
		});
	}


	/**
	 * 跳转预览页面
	 *
	 * @param path
	 * @param type
	 */
	private void mIntent(String path, String type) {
		Intent intent = new Intent(this, CameraImageActivity.class);
		intent.putExtra("path", path);
		intent.putExtra("type", type);
		startActivityForResult(intent, 7);

	}


	/**
	 * 开始拍照
	 */

	private void captrue() {

		startPreview(mCamera, mHolder);

		mCamera.takePicture(null, null, new Camera.PictureCallback() {
			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				isview = false;
				//将data 转换为位图 或者你也可以直接保存为文件使用 FileOutputStream
				//这里我相信大部分都有其他用处把 比如加个水印 后续再讲解
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				Bitmap saveBitmap = CameraUtil.getInstance().setTakePicktrueOrientation(mCameraId, bitmap);

				// 保存图片新的宽高
				saveBitmap = Bitmap.createScaledBitmap(saveBitmap, SystemUtils.dp2px(context, 400), SystemUtils.dp2px(context, 500), true);


				String img_path = getExternalFilesDir(Environment.DIRECTORY_DCIM).getPath() +
						File.separator + System.currentTimeMillis() + ".jpg";

				BitmapUtils.saveJPGE_After(context, saveBitmap, img_path, 100);

				if (!bitmap.isRecycled()) {
					bitmap.recycle();
				}

				if (!saveBitmap.isRecycled()) {
					saveBitmap.recycle();
				}
				Log.e("CameraActivity", "img_path       >>> " + img_path);


				if (count == 1) {
					mImagePathOne = img_path;
					/**
					 *  判断是手掌拍照的时候，只展示手掌的一张图片，把其他的都进行隐藏
					 */
					if (imageType.equals("hand")) {
						mImageHandOne.setVisibility(View.VISIBLE);
						mImageHandOne.setImageURI(Uri.parse(img_path));
						mLinearLeft.setVisibility(View.GONE);
						mLinearRight.setVisibility(View.GONE);

					} else

					{
						mLinearLeft.setVisibility(View.GONE);
						mImageLeft.setVisibility(View.VISIBLE);
						mImageLeft.setImageURI(Uri.parse(img_path));
					}
					count = 2;
				} else if (count == 2) {

					if (imageType.equals("hand")) {
						mImageHandOne.setVisibility(View.VISIBLE);
						mImageHandOne.setImageURI(Uri.parse(img_path));
						mLinearLeft.setVisibility(View.GONE);
						mLinearRight.setVisibility(View.GONE);
						mImagePathOne = img_path;
					} else {
						mLinearRight.setVisibility(View.GONE);
						mImageRight.setVisibility(View.VISIBLE);
						mImageRight.setImageURI(Uri.parse(img_path));

						mImagePathTwo = img_path;
					}
					count = 1;
				}
				Log.e("CameraViewActivity", "count:>>>>》》》》》 " + count);

//				Intent intent = new Intent();
//				intent.putExtra("image_path", img_path);
//				setResult(200, intent);
//				finish();

				//这里打印宽高 就能看到 CameraUtil.getInstance().getPropPictureSize(parameters.getSupportedPictureSizes(), 200);
				// 这设置的最小宽度影响返回图片的大小 所以这里一般这是1000左右把我觉得
//                Log.d("bitmapWidth==", bitmap.getWidth() + "");
//                Log.d("bitmapHeight==", bitmap.getHeight() + "");
			}
		});


	}


	/**
	 * 翻转摄像头
	 */
	public void switchCamera() {
		releaseCamera();
		mCameraId = (mCameraId + 1) % mCamera.getNumberOfCameras();
		mCamera = getCamera(mCameraId);
		if (mHolder != null) {
			startPreview(mCamera, mHolder);
		}
	}

	/**
	 * 获取Camera实例
	 *
	 * @return
	 */
	private Camera getCamera(int id) {
		Camera camera = null;
		try {
			camera = Camera.open(id);
		} catch (Exception e) {

		}
		return camera;
	}

	/**
	 * 设置
	 */
	private void setupCamera(Camera camera) {
		Camera.Parameters parameters = camera.getParameters();

		if (parameters.getSupportedFocusModes().contains(
				Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
			parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
		}

		//这里第三个参数为最小尺寸 getPropPreviewSize方法会对从最小尺寸开始升序排列 取出所有支持尺寸的最小尺寸
		Camera.Size previewSize = CameraUtil.getInstance().getPropSizeForHeight(parameters.getSupportedPreviewSizes(), 1080);
		parameters.setPreviewSize(previewSize.width, previewSize.height);

		Camera.Size pictrueSize = CameraUtil.getInstance().getPropSizeForHeight(parameters.getSupportedPictureSizes(), 1920);
		parameters.setPictureSize(pictrueSize.width, pictrueSize.height);

		camera.setParameters(parameters);

		/**
		 * 设置surfaceView的尺寸 因为camera默认是横屏，所以取得支持尺寸也都是横屏的尺寸
		 * 我们在startPreview方法里面把它矫正了过来，但是这里我们设置设置surfaceView的尺寸的时候要注意 previewSize.height<previewSize.width
		 * previewSize.width才是surfaceView的高度
		 * 一般相机都是屏幕的宽度 这里设置为屏幕宽度 高度自适应 你也可以设置自己想要的大小
		 *
		 */

		picHeight = (screenWidth * pictrueSize.width) / pictrueSize.height;

//		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(screenWidth, (screenWidth * pictrueSize.width) / pictrueSize.height);

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(screenWidth, screenHeight);
		//这里当然可以设置拍照位置 比如居中 我这里就置顶了
		params.gravity = Gravity.CENTER;
		surfaceView.resize(screenWidth, screenHeight);


	}

	/**
	 * 释放相机资源
	 */
	private void releaseCamera() {
		if (mCamera != null) {
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview();
			mCamera.release();
			mCamera = null;
		}
	}


	/**
	 * 预览相机
	 */
	private void startPreview(Camera camera, SurfaceHolder holder) {
		try {
			setupCamera(camera);
			camera.setPreviewDisplay(holder);
			//亲测的一个方法 基本覆盖所有手机 将预览矫正
			CameraUtil.getInstance().setCameraDisplayOrientation(this, mCameraId, camera);
//            camera.setDisplayOrientation(90);
			camera.startPreview();
			isview = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/**
		 *   左边图片 50 count >>  1
		 *   右边图片 51 coubt >>  2
		 */
		if (resultCode == 50) {

			count = data.getIntExtra("count", 0);
		}
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
