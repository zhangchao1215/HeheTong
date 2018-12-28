package com.example.heyikun.heheshenghuo.controller.activity.faxian;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.HeHeHealthFaXianFragment;
import com.example.heyikun.heheshenghuo.HeHeLifeFragment;
import com.example.heyikun.heheshenghuo.HeHeMainActivity;
import com.example.heyikun.heheshenghuo.HeHeServeFragment;
import com.example.heyikun.heheshenghuo.HeHeShoppingFragment;
import com.example.heyikun.heheshenghuo.HeHeUserFragment;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.ShareWebView;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ShareBean;
import com.example.heyikun.heheshenghuo.modle.bean.ZanBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.alipay.sdk.app.statistic.c.F;
import static com.alipay.sdk.app.statistic.c.e;
import static com.alipay.sdk.app.statistic.c.f;
import static com.alipay.sdk.app.statistic.c.g;
import static com.alipay.sdk.app.statistic.c.t;
import static com.alipay.sdk.app.statistic.c.u;
import static com.alipay.sdk.app.statistic.c.v;

/**
 * Created by hyk on 2017/12/7.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/7
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：  大咖养生达人Webview
 */

public class BigCastWebview extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.mTextTitle)
    TextView mTextTitle;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.share_diandian)
    ImageView shareDiandian;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.bigcast_webview)
    WebView bigcastWebview;
    @BindView(R.id.mImage_zan)
    ImageView mImageZan;
    @BindView(R.id.mText_Zan)
    TextView mTextZan;
    @BindView(R.id.mImage_pinglun)
    ImageView mImagePinglun;
    @BindView(R.id.mText_Pinglun)
    TextView mTextPinglun;
    @BindView(R.id.mImage_shoucang)
    ImageView mImageShoucang;
    @BindView(R.id.mText_Shuocang)
    TextView mTextShuocang;
    @BindView(R.id.mImage_Share)
    ImageView mImageShare;
    @BindView(R.id.mText_share)
    TextView mTextShare;
    @BindView(R.id.bigcat_webLinear)
    LinearLayout bigcatWebLinear;
    private String userid;
    private String mtoken;
    private String encryptUserId = null;
    private String url;
    private String sharetitle;
    private String shareicon;
    private String comment_link;
    private String praise_count;
    private String fabulous;
    private ChangePwdBean bean;
    private ShareBean.DataBean dataBean;
    private String articleId;
    private String article_desc;
    private PopupWindow popupWindow;
    private String master;
    private String masterId;

    private String articleType;
    private String videoUrl;
    private String collect_state;

    @Override
    protected int layoutId() {
        return R.layout.activity_bigcast_webview;
    }

    @Override
    protected void initView() {


        WebSettings webSettings = bigcastWebview.getSettings();
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
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //        webSettings.setLoadWithOverviewMode(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        bigcastWebview.addJavascriptInterface(this, "test_hehe_app");
        //清除网页访问留下的缓存
        //由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
        //        ShareWebview.clearCache(true);

        //清除当前webview访问的历史记录
        //只会webview访问历史记录里的所有记录除了当前访问记录
        //        ShareWebview.clearHistory();

        //这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
        //        ShareWebview.clearFormData();


        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");

        mTextTitle.setText(title + "");

        bigcastWebview.loadUrl(url);

        Log.d("BigCastWebview", "跳转过来 网址     " + url);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bigcastWebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        }


        master = "health_talent";


        final String column = "health_talent/health-column.php?";
        final String article = "health_talent/article.php?";

        if (url.contains(article)) {
            bigcatWebLinear.setVisibility(View.VISIBLE);
        } else if (url.contains(column) || url.contains("public/search")) {
            bigcatWebLinear.setVisibility(View.GONE);

        }
        bigcastWebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress == 100) {
                    progressBar1.setVisibility(View.GONE);


                } else {
                    progressBar1.setVisibility(View.VISIBLE);
                    progressBar1.setProgress(newProgress);


                }

            }
        });


        userid = AppUtils.get().getString("user_id", "");
        mtoken = AppUtils.get().getString("token", "");

        //
        bigcastWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Log.d("BigCastWebview", "变换的网址" + url);

                videoUrl = url;
                Map<String, String> parameters = getParameters(url);
                masterId = parameters.get("id");
                if (url.contains("health_talent/article")) {
                    bigcatWebLinear.setVisibility(View.VISIBLE);

                } else {
                    bigcatWebLinear.setVisibility(View.GONE);

                }


                return false;
            }
        });

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        //        //养生达人 获取数据

    }


    @JavascriptInterface
    public void article(String article_type, String article_id) {

        Log.d("BigCastWebview", "文章的类型" + article_type);
        Log.d("BigCastWebview", "文章的id   " + article_id);

        articleType = article_type;

        articleId = article_id;

        if ("2".equals(article_type)) {
            ArticleShare(article_id, "master");

        } else if ("1".equals(article_type)) {
            ArticleShare(article_id, "bigcast");
        }
    }

    @JavascriptInterface
    public void add_attention(String cat_id) {

        Log.d("BigCastWebview", "H5 页面的大咖id" + cat_id);
        if (userid.equals("") || mtoken.equals("")) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
        } else {

            bigcastGuanZhu(1, cat_id);
        }

    }

    private void bigcastGuanZhu(int state, String id) {

        final Map<String, String> params = new HashMap<>();


        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);
        String mTokenTwo = null;
        String encryptToken = null;
        try {

            //给userID 还有 生成二次token ，在进行AES加密
            encryptUserId = AESUtils.Encrypt(userid, BaseUrl.AESKey);

            mTokenTwo = userid + "," + mtoken + "," + timestamp;


            encryptToken = AESUtils.Encrypt(mTokenTwo, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("action", "Ateention");
        params.put("people_id", id);
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("state", String.valueOf(state));

        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new RawResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String response) {
                        Log.d("BigCastPeopleDetailActi", response);

                        Gson gson = new Gson();
                        try {
                            ChangePwdBean changePwdBean = gson.fromJson(response, ChangePwdBean.class);
                            Toast.makeText(BigCastWebview.this, changePwdBean.getMessage(), Toast.LENGTH_SHORT).show();

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }

    private void UmengShare(String url, String sharetitle, String article_desc) {



    }


    @OnClick({R.id.Image_Back, R.id.mImage_zan, R.id.mImage_pinglun, R.id.mImage_shoucang, R.id.mImage_Share
            , R.id.share_diandian
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:

                finish();
                break;
            case R.id.mImage_zan:


                //0点赞  1取消点赞
                //     1大咖说，2养生达人，3问健康，4读健康，5疾病谱，6其他
                if (userid.equals("") || mtoken.equals("")) {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                //进行点赞
                if (fabulous.equals("0")) {
                    mImageZan.setImageResource(R.drawable.zan_shixin_man);
                    String praise_count = dataBean.getPraise_count();
                    int i = Integer.parseInt(praise_count) + 1;
                    mTextZan.setText(i + "");

                    if ("1".equals(articleType)) {
                        FabulousZan("1", 0, articleId);
                    } else if ("2".equals(articleType)) {
                        FabulousZan("2", 0, articleId);
                    }

                    fabulous = "1";
                } else if (fabulous.equals("1")) {
                    //在进行取消点赞
                    mImageZan.setImageResource(R.drawable.zan_kongxinman);
                    String praise_count = dataBean.getPraise_count();
                    int i = Integer.parseInt(praise_count);

                    mTextZan.setText(i + "");

                    //在这进行判断是大咖说点赞还是达人点赞

                    if ("1".equals(articleType)) {
                        FabulousZan("1", 1, articleId);
                    } else if ("2".equals(articleType)) {
                        FabulousZan("2", 1, articleId);
                    }

                    fabulous = "0";
                }


                // else {
                //
                //                    if (dataBean.getFabulous().equals("0")) {
                //                        mImageZan.setImageResource(R.drawable.zan_shixin_man);
                //
                //                        String praise_count = dataBean.getPraise_count();
                //
                //                        int i = Integer.parseInt(praise_count) + 1;
                //
                //                        mTextZan.setText(i + "");
                //
                //                        if ("1".equals(articleType)) {
                //
                //                            FabulousZan("1", 0, articleId);
                //                        } else if ("2".equals(articleType)) {
                //
                //                            FabulousZan("2", 0, articleId);
                //                        }
                //
                //
                //                    } else {
                //
                //                        //取消点赞啊
                //                        mImageZan.setImageResource(R.drawable.zan_kongxinman);
                //
                //                        String praise_count = dataBean.getPraise_count();
                //
                //                        int i = Integer.parseInt(praise_count) - 1;
                //
                //                        mTextZan.setText(i + "");
                //
                //
                //                        FabulousZan("1", 1, articleId);
                //                        if (url.contains(master)) {
                //                            FabulousZan("2", 1, articleId);
                //                        }
                //
                //                    }

                //        }


                break;
            case R.id.mImage_pinglun:
                if (mtoken.equals("") || userid.equals("")) {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    //                    bigcastWebview.loadUrl(comment_link);
                    Intent intent = new Intent(this, ShareWebView.class);
                    intent.putExtra("url", comment_link);
                    intent.putExtra("title", "评论");
                    startActivity(intent);

                }
                //                bigcatWebLinear.setVisibility(View.GONE);

                break;
            case R.id.mImage_shoucang:

                //1大咖说，2养生达人，3问健康，4读健康，5疾病谱，6其他
                // 是否收藏(0未收藏，1收藏)
                if (mtoken.equals("") || userid.equals("")) {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                }

                if (collect_state.equals("0")) {

                    mImageShoucang.setImageResource(R.drawable.shoucang_shixin_man);
                    mTextShuocang.setText("已收藏");

                    if ("1".equals(articleType)) {
                        CollectArticle("CollectArt", articleId, "1");
                    } else if ("2".equals(articleType)) {
                        CollectArticle("CollectArt", articleId, "2");
                    }


                    collect_state = "1";
                } else if (collect_state.equals("1")) {

                    mImageShoucang.setImageResource(R.drawable.shoucang_kongxinman);
                    mTextShuocang.setText("收藏");
                    if ("1".equals(articleType)) {
                        CollectArticle("CancelColl", articleId, "1");
                    } else if ("2".equals(articleType)) {
                        CollectArticle("CancelColl", articleId, "2");
                    }
                    collect_state = "0";
                }

                //                else {
                //
                //                    if (dataBean.getCollect_state().equals("0")) {
                //                        CollectArticle(articleId, "1");
                //
                //                        if (url.contains(master)) {
                //                            CollectArticle(articleId, "2");
                //                        }
                //
                //                    } else {
                //                        mImageShoucang.setImageResource(R.drawable.shoucang_shixin_man);
                //
                //
                //                    }


                break;
            case R.id.mImage_Share:
                String title = null;
                String article_desc = null;
                try {
                    title = dataBean.getTitle();
                    article_desc = dataBean.getArticle_desc();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                //判断是大咖说还是 养身达人
                if ("1".equals(articleType)) {
                    //这是截取&user_id之前的字符串
                    String subUrl = url.substring(0, url.indexOf("&user_id"));

                    Log.d("BigCastWebview", "截取的指定字符串" + subUrl);

                    UmengShare(subUrl, title, article_desc);
                } else if ("2".equals(articleType)) {
                    String subUrl = null;
                    if (url.contains("&user_id")) {

                        subUrl = url.substring(0, url.indexOf("&user_id"));
                    }


                    if (!url.contains("health-column.php")) {
                        UmengShare(subUrl, title, "达人文章");

                    } else if (videoUrl.contains("health_talent/article")) {


                        UmengShare(videoUrl, title, "达人文章");
                    }


                }

                //                if (url.contains("health_talent/article") || url.contains("bigcast/ups-article")) {
                //
                //                    UmengShare(url, title, article_desc);
                //                } else if (videoUrl.contains("health_talent/article")) {
                //                    UmengShare(videoUrl, title, article_desc);
                //
                //                }


                break;

            case R.id.share_diandian:

                mPopupwindow(view);

                break;
        }

    }

    private void mPopupwindow(View v) {
        View view = LayoutInflater.from(this)
                .inflate(R.layout.activity_shareweb_ppw, null);

        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView textLife = (TextView) view.findViewById(R.id.share_ppw_life);

        TextView textHearth = (TextView) view.findViewById(R.id.share_ppw_faxian);

        TextView textUser = (TextView) view.findViewById(R.id.Share_ppw_user);
        TextView textFuwu = (TextView) view.findViewById(R.id.share_ppw_fuwu);
        TextView textShop = (TextView) view.findViewById(R.id.share_ppw_shop);

        textFuwu.setOnClickListener(this);
        textShop.setOnClickListener(this);
        textUser.setOnClickListener(this);
        textHearth.setOnClickListener(this);
        textLife.setOnClickListener(this);


        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        popupWindow.setBackgroundDrawable(new ColorDrawable());

        popupWindow.showAsDropDown(v);

    }

    //
    private void FabulousZan(String type, int state, String id) {
        String token = AppUtils.get().getString("token", "");

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        String encryptUserId = null;


        String encryptToken = null;

        try {

            //给userID 还有 生成二次token ，在进行AES加密
            encryptUserId = AESUtils.Encrypt(userid, BaseUrl.AESKey);

            String mTokenTwo = userid + "," + token + "," + timestamp;


            encryptToken = AESUtils.Encrypt(mTokenTwo, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "Fabulous");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("article_id", id);
        params.put("type", type);
        params.put("state", String.valueOf(state));
        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ZanBean>() {
                    @Override
                    public void onSuccess(int statusCode, ZanBean response) {

                        if (response.getStatus().equals("200")) {
                            Toast.makeText(BigCastWebview.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }

    /**
     * 文章收藏
     *
     * @param id
     * @param type
     */

    private void CollectArticle(String action, String id, String type) {


        Map<String, String> params = new HashMap<>();
        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);


        String mTokenTwo = null;

        String encryptToken = null;

        try {

            //给userID 还有 生成二次token ，在进行AES加密
            encryptUserId = AESUtils.Encrypt(userid, BaseUrl.AESKey);

            mTokenTwo = userid + "," + mtoken + "," + timestamp;


            encryptToken = AESUtils.Encrypt(mTokenTwo, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }

        params.put("action", action);
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("article_id", id);
        params.put("type", type);
        App.myOkHttp.post().url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if (response.getStatus().equals("200")) {

                            Toast.makeText(BigCastWebview.this, response.getMessage(), Toast.LENGTH_SHORT).show();

                            //                            mImageShoucang.setImageResource(R.drawable.shoucang_shixin_man);

                        } else {
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });

    }

    /**
     * 文章分享
     *
     * @param id
     * @param type
     */
    private void ArticleShare(String id, String type) {

        String userid = AppUtils.get().getString("user_id", "");

        Map<String, String> params = new HashMap<>();
        params.put("action", "ShareArt");
        params.put("article_id", id);
        params.put("type", type);
        params.put("user_id", userid);
        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Log.d("BigCastWebview", data);
                Gson gson = new Gson();
                ShareBean shareBean = gson.fromJson(data, ShareBean.class);
                if (data == null || shareBean == null) {

                    return;
                } else if (shareBean.getStatus().equals("200")) {
                    //                    bigcatWebLinear.setVisibility(View.VISIBLE);

                    dataBean = shareBean.getData();

                    sharetitle = dataBean.getTitle();

                    shareicon = dataBean.getIcon();

                    //判断文章是否收藏
                    collect_state = dataBean.getCollect_state();

                    if ("0".equals(collect_state)) {
                        mImageShoucang.setImageResource(R.drawable.shoucang_kongxinman);
                        mTextShuocang.setText("收藏");

                    } else if ("1".equals(collect_state)) {
                        mImageShoucang.setImageResource(R.drawable.shoucang_shixin_man);
                        mTextShuocang.setText("已收藏");

                    }

                    //获取分享文章的描述

                    article_desc = dataBean.getArticle_desc();


                    // 获取点赞数和评论数

                    String comment_count = dataBean.getComment_count();


                    mTextPinglun.setText(comment_count + "");


                    comment_link = dataBean.getComment_link();

                    Log.d("ShareWebView", comment_link);

                    //点赞
                    praise_count = dataBean.getPraise_count();

                    // 判断是否点赞  0 是未点赞 ,1是点赞

                    mTextZan.setText(praise_count);

                    fabulous = dataBean.getFabulous();


                    if ("0".equals(fabulous)) {

                        mImageZan.setImageResource(R.drawable.zan_kongxinman);
                        mTextZan.setText(praise_count + "");
                    } else if ("1".equals(fabulous)) {

                        mImageZan.setImageResource(R.drawable.zan_shixin_man);
                        mTextZan.setText(praise_count + "");
                    }


                } else {
                    bigcatWebLinear.setVisibility(View.GONE);

                }

            }


            @Override
            public void onError(String error) {

            }
        });

    }


    private void goBackClear() {
        mImageShoucang.setImageResource(R.drawable.shoucang_kongxinman);
        mTextShuocang.setText("收藏");
        mTextShare.setText("分享");
        mTextZan.setText("点赞");
        mImageZan.setImageResource(R.drawable.zan_kongxinman);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && bigcastWebview.canGoBack()) {
            if (bigcastWebview.canGoBack()) {
                bigcastWebview.goBack();
                //                goBackClear();
                bigcastWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                bigcatWebLinear.setVisibility(View.GONE);

                return true;
            }

        }
        return super.onKeyDown(keyCode, event);


    }


    //截取参数
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


    /**
     * ppw 中的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.share_ppw_life:
                intentMain(11);


                break;
            case R.id.share_ppw_faxian:
                intentMain(12);


                break;
            case R.id.Share_ppw_user:
                if (userid.equals("") || mtoken.equals("")) {
                    Intent intent = new Intent(this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else
                    intentMain(13);
                break;
            case R.id.share_ppw_fuwu:

                intentMain(14);
                break;
            case R.id.share_ppw_shop:
                intentMain(15);
                break;


        }
    }

    private void intentMain(int i) {
        Intent intent = new Intent(this, HeHeMainActivity.class);
        intent.putExtra("replace", i);
        startActivity(intent);
        AppUtils.put().putString("userSex", "跳转").commit();
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (bigcastWebview != null) {
            ViewParent parent = bigcastWebview.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(bigcastWebview);
            }
            bigcastWebview.removeAllViews();
            bigcastWebview.destroy();
            bigcastWebview = null;
        }
    }
}
