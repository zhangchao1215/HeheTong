package com.example.heyikun.heheshenghuo.modle.util;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目:趣租部落
 *
 * @author：location time：2018/12/3 18:02
 * description：软键盘显示隐藏工具类
 */

public class UtilsHelper {

	/**
	 * 显示软键盘
	 * @param context
	 * @param view
	 */
	public static void showSoftInput(final Context context, final View view) {

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(view, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
			}
		}, 998);
	}

	/**
	 * 隐藏软键盘
	 * @param context
	 * @param view
	 */
	public static void hideSoftInput(Context context, View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);



	}

	/**
	 * 获取软键盘状态
	 * @param context
	 * @return
	 */
	public static boolean isShowSoftInput(Context context,View view) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		//获取状态信息
		return imm.isActive(view);//true 打开
	}

}
