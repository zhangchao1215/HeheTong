package com.example.heyikun.heheshenghuo.newlife;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/11/13 13:52
 * description：
 */

public class CameraImageActivity extends BaseActivity {
	@BindView(R.id.mImage)
	ImageView mImage;
	@BindView(R.id.mTextAgain)
	TextView mTextAgain;
	@BindView(R.id.mTextCommit)
	TextView mTextCommit;
	@BindView(R.id.title)
	RelativeLayout title;
	private int mCount = 0;

	@Override
	protected int layoutId() {
		return R.layout.activity_camera_image;
	}

	@Override
	protected void initView() {

		Intent intent = getIntent();
		String path = intent.getStringExtra("path");

		String count = intent.getStringExtra("type");

		if (count.equals("1")) {

			mCount = 1;
		} else {
			mCount = 2;
		}

		mImage.setImageURI(Uri.parse(path));

	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initLisenter() {

	}


	@OnClick({R.id.mTextAgain, R.id.mTextCommit})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			case R.id.mTextAgain:

				mIntent();
				break;

			case R.id.mTextCommit:

				mIntent();
				break;
		}
	}

	private void mIntent() {
		Intent intent = new Intent();
		intent.putExtra("count", mCount);
		setResult(50, intent);
		finish();
	}

}
