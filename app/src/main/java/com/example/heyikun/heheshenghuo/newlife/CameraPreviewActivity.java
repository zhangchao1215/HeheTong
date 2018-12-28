package com.example.heyikun.heheshenghuo.newlife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.CameraPreViewBean;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.heyikun.heheshenghuo.R.id.mImage;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/14 13:24
 * description： 上传成功后的展示页
 */

public class CameraPreviewActivity extends BaseActivity {
	@BindView(R.id.mText_title)
	TextView mTextTitle;
	@BindView(R.id.mRelative_title)
	RelativeLayout mRelativeTitle;
	@BindView(R.id.mText_time)
	TextView mTextTime;
	@BindView(R.id.mLinear)
	LinearLayout mLinear;
	@BindView(R.id.text)
	TextView text;
	@BindView(R.id.mImage_Back)
	ImageView mImageBack;
	@BindView(R.id.mImage_left)
	ImageView mImageLeft;
	@BindView(R.id.mImage_right)
	ImageView mImageRight;
	@BindView(R.id.mBut_again)
	Button mButAgain;
	@BindView(R.id.mText_left)
	TextView mTextLeft;
	@BindView(R.id.mText_Right)
	TextView mTextRight;
	@BindView(R.id.mText_result)
	TextView mText_result;
	@BindView(R.id.mImage_hand)
	ImageView mImageHand;
	@BindView(R.id.mText_hand)
	TextView mTextHand;
	@BindView(R.id.mLinear_hand)
	LinearLayout mLinearHand;
	@BindView(R.id.mLinear_left)
	LinearLayout mLinearLeft;
	@BindView(R.id.mLinear_right)
	LinearLayout mLinearRight;
	private String user_id;
	private String token;
	private String position;
	private String encryptUserId;
	private String mTokenTwo;
	private String encryptToken;

	@Override
	protected int layoutId() {
		return R.layout.activity_camera_preview;
	}

	@Override
	protected void initView() {

		user_id = AppUtils.get().getString("user_id", "");
		token = AppUtils.get().getString("token", "");

		Intent intent = getIntent();
		position = intent.getStringExtra("position");

		switch (position) {
			case "1":

				mTextTitle.setText("面部特征");
				mTextLeft.setText("面部正面");
				mTextRight.setText("面部侧面");
				break;
			case "2":
				mTextTitle.setText("手部特征");
				mLinearLeft.setVisibility(View.GONE);
				mLinearRight.setVisibility(View.GONE);
				mLinearHand.setVisibility(View.VISIBLE);
				mTextHand.setText("手部正面");
				break;
			case "3":
				mTextTitle.setText("舌苔表象");
				mTextLeft.setText("舌苔上部");
				mTextRight.setText("舌苔下部");

				break;

		}

	}

	@Override
	protected void initData() {
		mLoadData();
	}

	@Override
	protected void initLisenter() {

	}


	private void mLoadData() {

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

		Map<String, String> params = new HashMap<>();
		params.put("action", "get_features");
		params.put("user_id", encryptUserId);
		params.put("token", encryptToken);
		params.put("position", position);

		App.myOkHttp
				.post()
				.url(BaseUrl.BaseUrl)
				.params(params)
				.enqueue(new GsonResponseHandler<CameraPreViewBean>() {
					@Override
					public void onSuccess(int statusCode, CameraPreViewBean response) {

						if (response.getStatus().equals("200")) {
							CameraPreViewBean.DataBean data = response.getData();
							String features_time = data.getFeatures_time();

							mTextTime.setText("上传时间" + features_time);

							if (position.equals("2")) {
								String position1 = data.getPosition1();
								Glide.with(CameraPreviewActivity.this)
										.load(position1)
										.placeholder(R.drawable.jcwz)
										.into(mImageHand);

							} else {
								String position1 = data.getPosition1();
								String position2 = data.getPosition2();

								Glide.with(CameraPreviewActivity.this)
										.load(position1)
										.placeholder(R.drawable.jcwz)
										.into(mImageLeft);

								Glide.with(CameraPreviewActivity.this)
										.load(position2)
										.placeholder(R.drawable.jcwz)
										.into(mImageRight);
							}

							String results = data.getResults();

							if (results.equals("")) {
								mText_result.setText("正在评估中，请等待。。。");
							} else
								mText_result.setText(results);

						}
					}

					@Override
					public void onFailure(int statusCode, String error_msg) {

					}
				});

	}

	@OnClick({R.id.mImage_Back, R.id.mBut_again})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.mImage_Back:
				finish();
				break;
			case R.id.mBut_again:

				Intent intent = new Intent(CameraPreviewActivity.this, CameraViewActivity.class);

				if (position.equals("1")) {

					intent.putExtra("imageType", "head");
				} else if (position.equals("2")) {
					intent.putExtra("imageType", "hand");
				} else {
					intent.putExtra("imageType", "shetai");
				}

				startActivity(intent);

				finish();

				break;
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO: add setContentView(...) invocation
		ButterKnife.bind(this);
	}
}
