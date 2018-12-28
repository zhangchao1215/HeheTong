package com.example.heyikun.heheshenghuo.controller.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/30.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/30
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ArticleWebView extends BaseActivity {
    @BindView(R.id.Shop_webView)
    WebView ShopWebView;
    @BindView(R.id.mText_Zan)
    TextView mTextZan;
    @BindView(R.id.mText_Pinglun)
    TextView mTextPinglun;
    @BindView(R.id.mText_Shuocang)
    TextView mTextShuocang;
    @BindView(R.id.mText_share)
    TextView mTextShare;
    @BindView(R.id.WebView_Relative)
    LinearLayout WebViewRelative;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.WebView_title)
    TextView WebViewTitle;
    private String shop_url;
    private String title;

    @Override
    protected int layoutId() {
        return R.layout.artivity_acticle_webview;
    }

    @Override
    protected void initView() {
        WebSettings webSettings = ShopWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);//设置自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8"); //这是编码格式
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDomStorageEnabled(true);

        webSettings.setGeolocationEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //清除网页访问留下的缓存
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        ShopWebView.clearCache(true);

        //清除当前webview访问的历史记录
        //只会webview访问历史记录里的所有记录除了当前访问记录
        ShopWebView.clearHistory();

        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        ShopWebView.clearFormData();


        Intent intent = getIntent();
        shop_url = intent.getStringExtra("shop_url");

        title = intent.getStringExtra("title");

        WebViewTitle.setText(title);

        ShopWebView.loadUrl(shop_url);

        Log.d("ArticleWebView", "跳转加载的网址  " + shop_url);


        /**
         * 加载进度条
         */

        ShopWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失

//                    if (title.equals("商品")) {
//                        WebViewRelative.setVisibility(View.GONE);
//                    } else {
//                        WebViewRelative.setVisibility(View.VISIBLE);
//                    }
//
//                    mShareUmeng(shop_url, "", "");
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值

//                    WebViewRelative.setVisibility(View.GONE);

                }

            }
        });


        /**
         * 加载数据
         */
        ShopWebView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, final String url) {

                Log.d("ArticleWebView", "变换的网址  " + url);
//
//                Map<String, String> parameters = getParameters(url);
//
//                String id = parameters.get("id");
//
//                Log.d("ArticleWebView", "文章id   + " + id);
//
//                if (url.contains("bigcast")) {
//
//                    ShareRequest(url, "bigcast", id);
//
//                }
//                if (url.contains("share")) {
//                    mShareUmeng(url, "", "");
//                } else {
//
//
//                    view.loadUrl(url);
//
//                }

                return false;


            }
        });


    }


    @Override
    protected void initData() {


    }

    @Override
    protected void initLisenter() {
        ImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void ShareRequest(final String url, String type, String id) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "ShareArt");
        params.put("article_id", id);
        params.put("type", type);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("ArticleWebView", data);
                mTextShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mShareUmeng(url, "", "");
                    }
                });
            }

            @Override
            public void onError(String error) {

            }
        });

    }


    private void mShareUmeng(final String url, final String title, final String desc) {



    }


    /**
     * 把得到的网址进行截取
     *
     * @param url 得到的URL
     * @return
     */
    public Map<String, String> getParameters(String url) {
        Map<String, String> params = new HashMap<String, String>();
        if (url == null || "".equals(url.trim())) {
            return params;
        }
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        String[] split = url.split("[?]");
        if (split.length == 2 && !"".equals(split[1].trim())) {
            String[] parameters = split[1].split("&");
            if (parameters != null && parameters.length != 0) {
                for (int i = 0; i < parameters.length; i++) {
                    if (parameters[i] != null
                            && parameters[i].trim().contains("=")) {
                        String[] split2 = parameters[i].split("=");
                        //split2可能为1，可能为2
                        if (split2.length == 1) {
                            //有这个参数但是是空的
                            params.put(split2[0], "");
                        } else if (split2.length == 2) {
                            if (!"".equals(split2[0].trim())) {
                                params.put(split2[0], split2[1]);
                            }
                        }
                    }
                }
            }
        }
        return params;
    }

    //监听webview返回键
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == keyEvent.KEYCODE_BACK) {
            if (ShopWebView.canGoBack()) {
                ShopWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, keyEvent);


    }


    @OnClick({R.id.mText_Zan, R.id.mText_Pinglun, R.id.mText_Shuocang, R.id.mText_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //点赞
            case R.id.mText_Zan:

                break;
            //评论
            case R.id.mText_Pinglun:

                break;
            //收藏
            case R.id.mText_Shuocang:


                break;

            //分享
            case R.id.mText_share:

                //                UMWeb web = new UMWeb(shop_url);
                //                web.setTitle("");//标题
                //                web.setDescription("");//描述
                //
                //                new ShareAction(ArticleWebView.this)
                //                        .withMedia(web)
                //                        .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ)
                //                        .open();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ShopWebView != null) {
            ViewParent parent = ShopWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(ShopWebView);
            }
            ShopWebView.removeAllViews();
            ShopWebView.destroy();
            ShopWebView = null;
        }

    }
}
