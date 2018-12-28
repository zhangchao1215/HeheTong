package com.example.heyikun.heheshenghuo.modle.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

/**
 * Created by hyk on 2018/4/24.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/4/24
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ImageLoader {

	public static final String ANDROID_RESOURCE = "android.resource://";
	public static final String FOREWARD_SLASH = "/";

	private static class ImageLoaderHolder {
		private static final ImageLoader INSTANCE = new ImageLoader();
	}

	private ImageLoader() {
	}

	public static final ImageLoader getInstance() {
		return ImageLoaderHolder.INSTANCE;
	}

	//直接加载网络图片
	public void displayImage(Context context, String url, ImageView imageView) {
		if (context != null) {

			Glide.with(context).load(url).centerCrop().crossFade().into(imageView);
		} else {
			return;
		}
	}

	// 加载SD卡图
	public void displayImage(Context context, File file, ImageView imageView) {
		Glide.with(context).load(file).centerCrop().into(imageView);
	}

	//加载SD卡图片并设置大小
	public void displayImage(Context context, File file, ImageView imageView, int width, int height) {
		Glide.with(context).load(file).override(width, height).centerCrop().into(imageView);
	}

	//加载网络图片并设置大小
	public void displayImage(Context context, String url, ImageView imageView, int width, int height) {
		Glide.with(context).load(url).centerCrop().override(width, height).crossFade().into(imageView);
	}

	// 加载drawable图片
	public void displayGifImage(Context context, int resId, ImageView imageView, int errorImage) {
		Glide.with(context)

				.load(resourceIdToUri(context, resId))
				.asGif()
				.placeholder(errorImage)
				.diskCacheStrategy(DiskCacheStrategy.ALL)//把图片的原型都读取到缓存中
				.into(imageView);
	}

	//加载drawable图片显示为圆形图片
	public void displayCricleImage(Context context, int resId, ImageView imageView) {
		if (context != null) {

			Glide.with(context).
					load(resourceIdToUri(context, resId))
					.crossFade()
					.transform(new GlideCircleTransform(context))
					.into(imageView);
		} else {
			return;
		}
	}

	// 加载网络图片显示为圆形图片
	public void displayCricleImage(Context context, String url, ImageView imageView, int placeholder) {
		if (context != null) {

			Glide.with(context)
					.load(url)
					.centerCrop()
					.placeholder(placeholder)
					.transform(new GlideCircleTransform(context))
					.crossFade()
					.into(imageView);
		} else {
			return;
		}
	}

	// 加载SD卡图片显示为圆形图片
	public void displayCricleImage(Context context, File file, ImageView imageView) {
		Glide.with(context)
				.load(file)
				.centerCrop()
				.transform(new GlideCircleTransform(context))
				.into(imageView);
	}
	// 将资源ID转为Uri

	public Uri resourceIdToUri(Context context, int resourceId) {
		return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
	}


}
