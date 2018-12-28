package com.example.heyikun.heheshenghuo.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/11/26.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/26
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class NewLifeWebview extends BaseActivity {
    @BindView(R.id.Text_Title)
    TextView TextTitle;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.but_next)
    Button butNext;
    @BindView(R.id.but_GoBack)
    Button butGoBack;
    @BindView(R.id.bottom_relative)
    RelativeLayout bottomRelative;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    private String data;
    private String url;

    private String dataUrl;

    @Override
    protected int layoutId() {
        return R.layout.activity_life_new_webview;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        String title = intent.getStringExtra("title");

        TextTitle.setText(title);

        data = intent.getStringExtra("data");


        webView.loadUrl(url);


        if (url.contains("vote_id")) {

            bottomRelative.setVisibility(View.VISIBLE);
        } else {
            bottomRelative.setVisibility(View.GONE);

        }


        Log.d("NewLifeWebview", "跳转过来的网址" + url);

        //        if (url.contains("wenjuan")) {
        //
        //            bottomRelative.setVisibility(View.GONE);
        //        } else {
        //            bottomRelative.setVisibility(View.VISIBLE);
        //
        //        }


        WebSettings webSettings = webView.getSettings();

        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);

        webSettings.setJavaScriptEnabled(true);
        //        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //关闭w
        // ebview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);//设置自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8"); //这是编码格式
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(true);
        //设置webview自适应屏幕
        //        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小


        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {


                Log.d("NewLifeWebview", "变换的网址" + url);

                return false;
            }
        });

        /**
         * 加载进度条
         */

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失

                } else {

                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条

                    progressBar1.setProgress(newProgress);//设置进度值

                }


            }


        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.but_next, R.id.but_GoBack, R.id.Image_Back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but_next:

                webView.loadUrl(data);
                if (data.contains("wenjuan")) {
                    bottomRelative.setVisibility(View.GONE);
                }

                break;

            case R.id.but_GoBack:
                finish();
                break;
            case R.id.Image_Back:
                finish();
                break;

        }
    }


}
