package com.example.heyikun.heheshenghuo.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hyk on 2017/11/13.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/13
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class UpdateWebview extends BaseActivity {
    @BindView(R.id.upda_web)
    WebView webView;

    @Override
    protected int layoutId() {
        return R.layout.activity_updata_webview;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);//设置自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8"); //这是编码格式


        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(url);


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }

}
