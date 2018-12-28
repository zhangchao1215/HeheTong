package com.example.heyikun.heheshenghuo.modle.base;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.util.AndroidWorkaround;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by hyk on 2017/9/12.
 */

public abstract class BaseFragment extends Fragment {
	private Unbinder unbinder;


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(getLayoutId(), container, false);
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		unbinder = ButterKnife.bind(this, view);


		initView(view);

		//        if (AndroidWorkaround.checkDeviceHasNavigationBar(getContext())) {
		//            AndroidWorkaround.assistActivity(getActivity().findViewById(android.R.id.content));
		//        }
		//        //控制底部虚拟键盘
//        App.activity.getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                        //                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
//        App.activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

//        onWindow();

//        fitsSystemWindows(getActivity());

		initListener();

		initData();

	}


	@Override
	public void onResume() {
		super.onResume();
	}

	protected abstract int getLayoutId();

	//加载数据的时候
	protected abstract void initData();

	//初始化组件
	protected abstract void initView(View view);

	//加载布局的时候


	//初始化监听事件的时候

	protected abstract void initListener();


	@Override
	public void onDestroy() {
		super.onDestroy();
		unbinder.unbind();

	}

	private void onWindow() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			Window window = App.activity.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(getResources().getColor(R.color.ZhenDuanText));
			//            window.setNavigationBarColor(getResources().getColor(R.color.ZhenDuanText));
		}
	}

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
}
