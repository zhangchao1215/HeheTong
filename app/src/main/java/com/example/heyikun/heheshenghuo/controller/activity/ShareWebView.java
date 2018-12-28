package com.example.heyikun.heheshenghuo.controller.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.Constants;
import com.example.heyikun.heheshenghuo.HeHeMainActivity;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.alipay.PayResult;
import com.example.heyikun.heheshenghuo.controller.activity.login.LoginActivity;
import com.example.heyikun.heheshenghuo.controller.shop.ShopYesPayActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.AliBean;
import com.example.heyikun.heheshenghuo.modle.bean.ShareBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.SelectDialog;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.Util;
import com.example.heyikun.heheshenghuo.modle.util.WXUtil;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneSession;
import static com.tencent.mm.opensdk.modelmsg.SendMessageToWX.Req.WXSceneTimeline;

/**
 * Created by hyk on 2017/11/7.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/7
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ShareWebView extends BaseActivity implements View.OnClickListener {
	@BindView(R.id.Share_webview)
	WebView ShareWebview;
	@BindView(R.id.Image_Back)
	ImageView ImageBack;
	@BindView(R.id.progressBar1)
	ProgressBar progressBar1;
	@BindView(R.id.mTextTitle)
	TextView mTextTitle;
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
	@BindView(R.id.mImage_zan)
	ImageView mImageZan;
	@BindView(R.id.mImage_pinglun)
	ImageView mImagePinglun;
	@BindView(R.id.mImage_shoucang)
	ImageView mImageShoucang;
	@BindView(R.id.mImage_Share)
	ImageView mImageShare;
	@BindView(R.id.share_diandian)
	ImageView shareDiandian;
	@BindView(R.id.sendArticle_image)
	ImageView sendArticleImage;
	@BindView(R.id.ask_health_QQ)
	TextView askHealthQQ;
	@BindView(R.id.ask_health_Phone)
	TextView askHealthPhone;
	@BindView(R.id.ask_health_relative)
	RelativeLayout askHealthRelative;
	@BindView(R.id.text_zaice)
	TextView textZaice;
	@BindView(R.id.text_goBack)
	TextView textGoBack;
	@BindView(R.id.zaice_relative)
	RelativeLayout zaiceRelative;
	private String JsUrl;
	private String mtoken;
	private String id;

	private int ZanFlag;

	private String Shareurl;
	private String sharetitle;
	private String shareicon;
	private String comment_link;
	private String praise_count;
	private String fabulous;
	private String bigcast;
	private String master;
	private String readHealth;

	private String isLike;
	private String userid;
	private IWXAPI wxApi;
	/**
	 * 支付宝支付业务：入参app_id
	 */
	public static final String APPID = "2017090608586413";
	public static final String RSA_PRIVATE = "";

	private static final int SDK_PAY_FLAG = 1;
	private static final int SDK_AUTH_FLAG = 2;
	private String mUserId;
	private boolean isRedirect = false; //是否是重定向
	private boolean isPageOk = true; //目的地地址加载完成
	private PopupWindow popupWindow;
	private String order_id;
	private String aeSordId;
	public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAe";
	private String uId;
	private String url;
	private String zaiCeurl;
	private View viewPop;
	private PopupWindow mPop;
	private String finishURL;

	enum SHARE_TYPE {Type_WXSceneSession, Type_WXSceneTimeline}

	@Override
	protected int layoutId() {
		return R.layout.activity_share_webview;
	}


	private void mInitPpw() {
		viewPop = LayoutInflater.from(this).inflate(R.layout.activity_share_layout, null);

		TextView mTextWechat = (TextView) viewPop.findViewById(R.id.weChat);
		TextView mTextQQ = (TextView) viewPop.findViewById(R.id.QQ);
		TextView mTextCircle = (TextView) viewPop.findViewById(R.id.friendCircle);
		TextView mTextCancle = (TextView) viewPop.findViewById(R.id.mText_cancle);

		mTextCancle.setOnClickListener(this);
		mTextWechat.setOnClickListener(this);
		mTextCircle.setOnClickListener(this);
		mTextQQ.setOnClickListener(this);
	}

	@Override
	protected void initView() {
		mInitPpw();


		wxApi = WXAPIFactory.createWXAPI(this, "wxf88051a3634663a9", false);

		// 将该app注册到微信

		wxApi.registerApp("wxf88051a3634663a9");


		mUserId = AppUtils.get().getString("user_id", "");

		mtoken = AppUtils.get().getString("token", "");

		Log.d("ShareWebView", "我的userId" + mUserId);

		Log.d("ShareWebView", "我的token" + mtoken);


		WebSettings webSettings = ShareWebview.getSettings();
		webSettings.setAppCacheEnabled(true);
		webSettings.setDatabaseEnabled(true);

		webSettings.setJavaScriptEnabled(true);
		//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);  //关闭w
		// ebview中缓存
		webSettings.setAllowFileAccess(true); //设置可以访问文件
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
		webSettings.setLoadsImagesAutomatically(true);//设置自动加载图片
		webSettings.setDefaultTextEncodingName("utf-8"); //这是编码格式
		ShareWebview.addJavascriptInterface(this, "test_hehe_app");

		//        webSettings.setBuiltInZoomControls(true);
		//        webSettings.setDomStorageEnabled(true);
		//        webSettings.setGeolocationEnabled(true);
		//设置webview自适应屏幕
		//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		//        webSettings.setLoadWithOverviewMode(true);
		//设置自适应屏幕，两者合用
		//        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
		//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

		//清除网页访问留下的缓存
		//由于内核缓存是全局的因此这个方法不仅仅针对webview而是针对整个应用程序.
		//        ShareWebview.clearCache(true);

		//清除当前webview访问的历史记录
		//只会webview访问历史记录里的所有记录除了当前访问记录
		//        ShareWebview.clearHistory();

		//这个api仅仅清除自动完成填充的表单数据，并不会清除WebView存储到本地的数据
		//        ShareWebview.clearFormData();

		userid = AppUtils.get().getString("user_id", "");

		final Intent intent = getIntent();

		url = intent.getStringExtra("url");

		ShareWebview.loadUrl(url);

		String title = intent.getStringExtra("title");

		zaiCeurl = intent.getStringExtra("data");


		Log.d("ShareWebView", "再测一次的链接    " + zaiCeurl);
		mTextTitle.setText(title + "");


		//在测一次
		if (url.contains("yscs.php")) {

			zaiceRelative.setVisibility(View.VISIBLE);
		}
		//问健康
		if (url.contains("ask-health/index")) {
			askHealthRelative.setVisibility(View.VISIBLE);
		} else {
			askHealthRelative.setVisibility(View.GONE);
		}

		Log.d("ShareWebView", "第一次进来的网址" + url);


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ShareWebview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

		}


		/**
		 * 加载进度条
		 */

		ShareWebview.setWebChromeClient(new WebChromeClient() {
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


		ShareWebview.setWebViewClient(new WebViewClient() {

			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
				//                handler.proceed();  // 接受所有网站的证书
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				super.onPageStarted(view, url, favicon);


				isRedirect = false;
				isPageOk = true;
				Log.e("测试", "====onPageStarted====");

			}

			/**
			 *  网址加载完成
			 * @param view
			 * @param url
			 */
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);

				Log.d("ShareWebView", "测试加载完成的url  》》》  " + url);

				finishURL = url;

				ArticleUrl(url);

				isPageOk = isRedirect;
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, final String url) {

				Log.d("ShareWebView", "变换的网址    " + url);

				if (url.startsWith("tel:")) {
					Toast.makeText(ShareWebView.this, "暂不可拨打电话，敬请谅解", Toast.LENGTH_SHORT).show();
					return true;

				}

				JsUrl = url;
				view.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
				//hitTestResult==null解决重定向问题
				WebView.HitTestResult hitTestResult = view.getHitTestResult();


				Map<String, String> parameters = getParameters(url);

				uId = parameters.get("u");


				return false;


			}


		});


	}

	/**
	 * 我的分享
	 *
	 * @param msg
	 * @param image
	 */
	@JavascriptInterface
	public void hello_user(String msg, String image) {
		String shareUrl = null;
		if (url.contains("&user_id")) {

			shareUrl = url.substring(0, url.indexOf("&user_id"));

		}

		mSharePpw();


	}

	/**
	 * 进行商品的分享
	 *
	 * @param msg
	 * @param imag
	 */
	@JavascriptInterface
	public void hello(String msg, String imag, String text) {
		//        Log.d("ShareWebView", msg);
		//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		//        UMWeb web = new UMWeb(JsUrl + "&u=" + uId);
		//
		//        web.setTitle("和合分享");
		//
		//        new ShareAction(ShareWebView.this)
		//                .withMedia(web)
		//                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ)
		//                .open();

		Log.d("ShareWebView", msg);

		Log.d("ShareWebView", imag);
		aShare(JsUrl, msg, imag, text);

	}

	/**
	 * 进行支付
	 *
	 * @param order_id
	 */

	@JavascriptInterface
	public void pay(String order_id) {

		Log.d("ShareWebView", "商品 的 id " + order_id);


		Intent orderIntent = new Intent(ShareWebView.this, ShopYesPayActivity.class);
		orderIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		orderIntent.putExtra("order_id", order_id);
		startActivity(orderIntent);
		finish();

	}

	/**
	 * 在商品页面返回首页
	 *
	 * @param str
	 */
	@JavascriptInterface
	public void toindex(String str) {
		Log.d("ShareWebView", "返回首页" + str);
		Intent intent = new Intent(this, HeHeMainActivity.class);
		startActivity(intent);
		finish();

	}

	@JavascriptInterface
	public void login(String login) {

		Log.d("ShareWebView", "login:" + login);

		if (userid.equals("") || mtoken.equals("") || "0".equals(login)) {
			Intent intent = new Intent(this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
		}


	}

	@JavascriptInterface
	public void tel(String title) {
		Log.d("ShareWebView", "得到的手机号" + title);

		if ("".equals(title)) {

		} else {

			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + title));

			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);

		}

	}

	@Override
	protected void initData() {

	}

	@Override
	protected void initLisenter() {

	}
//
//	/**
//	 * 分享微信朋友圈
//	 *
//	 * @param type
//	 */
//	private void share(SHARE_TYPE type,String url) {
//		WXWebpageObject webpageObject = new WXWebpageObject();
//
//
//		webpageObject.webpageUrl = url;
//
//		final WXMediaMessage msg = new WXMediaMessage(webpageObject);
//
//
//
//
//		msg.title = "我的分享";
//
//		msg.description = "您的好友邀请您加入和合生活";
//
//
//		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.new_hehe_logo);
//
//		msg.thumbData = WXUtil.bmpToByteArray(bitmap, true);
//
//		SendMessageToWX.Req req = new SendMessageToWX.Req();
//		req.transaction = buildTransac("webpage");
//		req.message = msg;
//		switch (type) {
//			case Type_WXSceneSession:
//				req.scene = WXSceneSession;
//				break;
//			case Type_WXSceneTimeline:
//				req.scene = WXSceneTimeline;
//				break;
//		}
//		App.api.sendReq(req);
//	}
//
//
//	private String buildTransac(final String type) {
//		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
//	}
//
//	/**
//	 * 分享点击
//	 */
//
//	private void selectorShare() {
//		List<String> names = new ArrayList<>();
//		names.add("微信好友");
//		names.add("朋友圈");
//
//		showDialog(new SelectDialog.SelectDialogListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//				switch (position) {
//					case 0:
//
//						share(SHARE_TYPE.Type_WXSceneSession);
//
//
//						break;
//
//					case 1:
//						share(SHARE_TYPE.Type_WXSceneTimeline);
//						break;
//				}
//
//			}
//		}, names);
//
//
//	}
//
//
//	// 点击头像弹出的dialog
//	private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
//		SelectDialog dialog = new SelectDialog(this, R.style
//				.transparentFrameWindowStyle,
//				listener, names);
//		if (!this.isFinishing()) {
//			dialog.show();
//		}
//		return dialog;
//	}




	private void lifeIntent(String url) {
		Intent intent = new Intent();

		if (url.contains("") || url.contains("")) {

			intent.putExtra("life", "body");
		} else if (url.contains("") || url.contains("")) {

			intent.putExtra("life", "head");
		} else if (url.contains("") || url.contains("")) {

			intent.putExtra("life", "money");
		}

		setResult(201, intent);
	}

	/**
	 * 判断显示文章类型，并获取数据 显示底部分享 点赞的view
	 *
	 * @param url
	 */
	private void ArticleUrl(String url) {
		Map<String, String> parameters = getParameters(url);

		id = parameters.get("id");

		Log.d("ShareWebView", "文章的id  ++++" + id);
		String Readtype = parameters.get("read-health");


		//如果存在这个类型就显示
		if (url.contains("bigcast/ups-article") || url.contains("health_talent/article")) {

			new Thread() {
				@Override
				public void run() {
					super.run();
					try {
						Thread.sleep(1500);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								//框框这张图片，把它延时进行显示
								WebViewRelative.setVisibility(View.VISIBLE);

							}
						});

					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}.start();

			if (url.contains("health_talent")) {

			} else {
				sendArticleImage.setVisibility(View.GONE);
			}


			//大咖说
			bigcast = "bigcast/ups-article";

			//养生达人

			master = "health_talent/article";


			//读健康

			readHealth = "read-health-article";

			if (url.contains(bigcast)) {

				ArticleShare(id, "bigcast");
			} //养生达人
			else if (url.contains(master)) {

				ArticleShare(id, "master");
			}//读健康
			else if (url.contains(readHealth)) {

				ArticleShare(id, "other");
			}


			Shareurl = url;

		} else {
			WebViewRelative.setVisibility(View.GONE);
		}


	}


	/**
	 * 进行分享
	 *
	 * @param url
	 */
	private void aShare(String url, String msg, String image, String text) {


	}


	/**
	 * 进行登录的判断
	 *
	 * @param view
	 * @param url
	 */

	private void aLogin(WebView view, String url) {

		if (url.contains("act=login")) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();

		}

	}


	@OnClick(R.id.Image_Back)
	public void onViewClicked() {
		finish();
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


	@OnClick({R.id.mText_Zan, R.id.mText_Pinglun, R.id.mText_Shuocang, R.id.mText_share,
			R.id.mImage_zan, R.id.mImage_pinglun, R.id.mImage_shoucang, R.id.mImage_Share
			, R.id.share_diandian, R.id.ask_health_QQ, R.id.ask_health_Phone
			, R.id.sendArticle_image, R.id.text_zaice, R.id.text_goBack
	})
	public void onViewClicked(View view) {
		switch (view.getId()) {
			//在测一次
			case R.id.text_zaice:

				ShareWebview.loadUrl(zaiCeurl);

				zaiceRelative.setVisibility(View.GONE);

				break;

			//回到首页
			case R.id.text_goBack:

				finish();

				break;


			case R.id.sendArticle_image:


				UmengShare(Shareurl, sharetitle);


				break;


			case R.id.mImage_zan:

				if (mtoken.equals("") || mUserId.equals("")) {
					Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
					return;
				} else {

					if (Shareurl.contains(bigcast)) {

						ZanType("1");
					} else if (Shareurl.contains(master)) {
						ZanType("2");
					} else if (Shareurl.contains(readHealth)) {
						ZanType("4");
					} else {
						ZanType("6");
					}
				}

				break;

			case R.id.mImage_pinglun:

				if (mtoken.equals("") || mUserId.equals("")) {
					Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
					return;
				} else {

					//                    Intent intent = new Intent(this, ArticleWebView.class);
					//
					//                    intent.putExtra("shop_url", comment_link);
					//                    startActivity(intent);

				}
				break;
			case R.id.mImage_shoucang:

				if (mtoken.equals("") || mUserId.equals("")) {
					Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
					return;
				} else {
					if (Shareurl.contains(bigcast)) {
						CollectArticle(id, "1");
					} else if (Shareurl.contains(master)) {
						CollectArticle(id, "2");
					} else if (Shareurl.contains(readHealth)) {
						CollectArticle(id, "4");
					}

				}
				break;


			case R.id.mImage_Share:
				//                ArticleShare(id, "bigcast");
				mSharePpw();

				break;
			case R.id.share_diandian:


				mPopupwindow(view);
				break;
			//在线咨询
			case R.id.ask_health_QQ:

				String url = "http://hehe.heyishenghuo.com/mobile2/app2/ask-health/my-consult.php?fangzao=1";
				WebViewUtils.bigCastWebView(this, url, "我的咨询");


				break;

			//电话咨询
			case R.id.ask_health_Phone:


				//                Uri uri = Uri.parse("tel:");
				//
				//                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
				//
				//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				//
				//                startActivity(intent);

				Toast.makeText(this, "此功能暂未开放，敬请期待", Toast.LENGTH_SHORT).show();
				break;
		}
	}


	private void mPopupwindow(View v) {
		View view = LayoutInflater.from(this)
				.inflate(R.layout.activity_shareweb_ppw, null);

		popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);

		popupWindow.setBackgroundDrawable(new ColorDrawable());

		popupWindow.showAsDropDown(v);

		TextView life = (TextView) view.findViewById(R.id.share_ppw_life);

		life.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		TextView shop = (TextView) view.findViewById(R.id.share_ppw_shop);

		shop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}


	private void UmengShare(String url, String sharetitle) {


	}


	private void ZanType(String type) {
		if (ZanFlag == 0) {

			isLike = fabulous; //为变量赋值

			Log.d("ShareWebView", "islike  ++  " + isLike);
			//判断第一次是没有点赞，就点赞
			if ("0".equals(fabulous)) {
				FabulousZan(type, 0, id);

				mImageZan.setImageResource(R.drawable.zan_shixin_man);

				int Count = Integer.parseInt(praise_count) + 1;  //没有点赞就+1在原来基础上

				mTextZan.setText(Count + "");


			}


			ZanFlag = 1;


		} else {

			if ("1".equals(fabulous)) {

				FabulousZan(type, 1, id);

				mImageZan.setImageResource(R.drawable.zan_kongxinman);
				int Count = Integer.parseInt(praise_count) - 1;  //点赞就--1在原来基础上

				mTextZan.setText(Count + "");
			}

			ZanFlag = 0;
		}

	}

	/**
	 * 文章点赞
	 *
	 * @param type
	 * @param state
	 * @param id
	 */
	private void FabulousZan(String type, int state, String id) {
		String token = AppUtils.get().getString("token", "");

		//获取时间戳
		String currentTime_today = TimeUtils.getCurrentTime_Today();
		String timestamp = TimeUtils.dataOne(currentTime_today);

		String encryptUserId = null;


		String encryptToken = null;

		try {

			//给userID 还有 生成二次token ，在进行AES加密
			encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);

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
		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {
				Log.d("ShareWebView", data);
				Gson gson = new Gson();
				ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

				if (bean.getStatus().equals("200")) {
					Toast.makeText(ShareWebView.this, bean.getMessage(), Toast.LENGTH_SHORT).show();

					isLike = "1";
				}

			}

			@Override
			public void onError(String error) {

			}
		});

	}

	/**
	 * 文章收藏
	 *
	 * @param id
	 * @param type
	 */

	private void CollectArticle(String id, String type) {


		Map<String, String> params = new HashMap<>();
		//获取时间戳
		String currentTime_today = TimeUtils.getCurrentTime_Today();
		String timestamp = TimeUtils.dataOne(currentTime_today);

		String encryptUserId = null;

		String mTokenTwo = null;

		String encryptToken = null;

		try {

			//给userID 还有 生成二次token ，在进行AES加密
			encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);

			mTokenTwo = mUserId + "," + mtoken + "," + timestamp;


			encryptToken = AESUtils.Encrypt(mTokenTwo, BaseUrl.AESKey);

		} catch (Exception e) {
			e.printStackTrace();
		}

		params.put("action", "CollectArt");
		params.put("user_id", encryptUserId);
		params.put("token", encryptToken);
		params.put("article_id", id);
		params.put("type", type);

		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {
				Gson gson = new Gson();

				ChangePwdBean bean = gson.fromJson(data, ChangePwdBean.class);

				if (bean == null || data == null) {
					return;
				} else if (bean.getStatus().equals("200")) {
					Toast.makeText(ShareWebView.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
					mImageShoucang.setImageResource(R.drawable.shoucang_shixin_man);

				} else {
					mImageShoucang.setImageResource(R.drawable.shoucang_kongxinman);
				}


			}

			@Override
			public void onError(String error) {

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

		Map<String, String> params = new HashMap<>();
		params.put("action", "ShareArt");
		params.put("article_id", id);
		params.put("type", type);
		params.put("user_id", mUserId);


		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {


			@Override
			public void onSuccess(String data) {
				Log.d("ShareWebView", "文章分享" + data);

				Gson gson = new Gson();
				ShareBean shareBean = gson.fromJson(data, ShareBean.class);
				if (data == null || shareBean == null) {
					return;
				} else if (shareBean.getStatus().equals("200")) {

					ShareBean.DataBean dataBean = shareBean.getData();

					sharetitle = dataBean.getTitle();

					shareicon = dataBean.getIcon();

					//判断文章是否收藏
					String collect_state = dataBean.getCollect_state();

					if ("0".equals(collect_state)) {
						mImageShoucang.setImageResource(R.drawable.shoucang_kongxinman);
						mTextShuocang.setText("收藏");

					} else if ("1".equals(collect_state)) {
						mImageShoucang.setImageResource(R.drawable.shoucang_shixin_man);
						mTextShuocang.setText("已收藏");
						Toast.makeText(ShareWebView.this, "你以收藏过这篇文章", Toast.LENGTH_SHORT).show();

					}

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


					if ("0".equals(praise_count)) {

						mImageZan.setImageResource(R.drawable.zan_kongxinman);
						mTextZan.setText(praise_count + "");
					} else if ("1".equals(fabulous)) {

						mImageZan.setImageResource(R.drawable.zan_shixin_man);
						mTextZan.setText(praise_count + "");
					}


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

	private void gotoPay(WebView view, String url) {
		Map<String, String> parameters = getParameters(url);

		if (url.contains("pay_type") && url.contains("prepay_id")) {
			//获取支付方式
			String pay_type = parameters.get("pay_type");
			//获取支付流水
			String prepay_id = parameters.get("prepay_id");
			/**
			 * 进行微信支付
			 */
			toWeChatPay(prepay_id);
		}


		//是否包含
		if (url.contains("pay_type") && url.contains("order_id")) {
			//获取支付宝支付订单
			order_id = parameters.get("order_id");

			//获取支付方式
			String pay_type = parameters.get("pay_type");

			/**
			 * 进行数据的请求 ，获取支付方式以及支付订单，获取完这些信息后在进行第二次请求，进行支付
			 */
			requestData(pay_type, order_id);

		}


	}

	/**
	 * 进行微信的支付
	 *
	 * @param prepayid
	 */
	private void toWeChatPay(String prepayid) {

		if (prepayid.equals("")) {
			return;
		}

		// 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信


		String currentTime_today = TimeUtils.getCurrentTime_Today();
		String timestamp = TimeUtils.dataOne(currentTime_today);

		PayReq request = new PayReq();

		String appid = Constants.APP_ID;
		request.appId = appid;


		//商家id
		String partnerid = Constants.MCH_ID;

		request.partnerId = partnerid;


		//订单号

		request.prepayId = prepayid;


		request.packageValue = "Sign=WXPay";
		String nonce_str = getNonceStr();
		request.nonceStr = nonce_str;


		request.timeStamp = timestamp;


		Log.d("PayActivity", "request:上传上去的参数是什么" + request);
		/**
		 * 生成签名sign
		 */
		String str = "appid=" + Constants.APP_ID + "&noncestr=" + nonce_str + "&package=Sign=WXPay&partnerid=" + partnerid + "&prepayid=" + prepayid + "&timestamp=" + timestamp;
		//appid=wxf88051a3634663a9&noncestr=yso9vHeWTWITXYkT&package=Sign=WXPay&partnerid=1488231382&prepayid=wx20171025172433082690123b0424917222&timestamp1508923473
		Log.d("PayActivity", "第一次拼接的  " + str);
		String stringSignTemp = str + "&key=" + Constants.API_KEY;////注：key为商户平台设置的密钥key

		Log.d("PayActivity", "商户平台设置的密钥key" + stringSignTemp);

		String mSign = MD5Utils.encrypt(stringSignTemp).toUpperCase();

		Log.d("WebViewActivity", "微信支付订单+++" + mSign);
		request.sign = mSign;

		wxApi.sendReq(request);

	}

	private void requestData(String pay_type, String order_id) {

		if (pay_type == null || order_id == null) {
			return;
		}

		Log.d("WebViewActivity", "支付方式 +" + pay_type);

		Log.d("WebViewActivity", "支付流水号+" + order_id);


		//获取时间戳
		mUserId = AppUtils.get().getString("user_id", "");


		String mtoken = AppUtils.get().getString("token", "");


		String currentTime_today = TimeUtils.getCurrentTime_Today();
		String timestamp = TimeUtils.dataOne(currentTime_today);

		//生成二次token
		String TwotoKen = mUserId + "," + mtoken + "," + timestamp;

		String md5 = "Payment" + timestamp + BaseUrl.AESKey;

		String signMd5 = MD5Utils.encrypt(md5);

		String encryptSign = null;


		String payType = null;

		String encryptToken1 = null;

		String encryptUserId = null;
		try {
			String encodeBase64 = Base64.encodeToString(order_id.getBytes(), Base64.DEFAULT);

			Log.d("WebViewActivity", "Base64加密的流水++  " + encodeBase64);

			encryptToken1 = AESUtils.Encrypt(TwotoKen, BaseUrl.AESKey);

			encryptSign = AESUtils.Encrypt(signMd5, BaseUrl.AESKey);

			aeSordId = AESUtils.Encrypt(encodeBase64.toString(), BaseUrl.AESKey);

			Log.d("WebViewActivity", "aes加密的id+  " + aeSordId);

			payType = AESUtils.Encrypt(pay_type, BaseUrl.AESKey);

			encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> params = new HashMap<>();
		params.put("action", "Payment");
		params.put("user_id", encryptUserId);
		params.put("token", encryptToken1);
		params.put("app_sign", encryptSign); //     签名
		params.put("order_id", aeSordId);  //   订单id AES加密
		params.put("pay_type", payType); //支付方式 AES加密
		OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
			@Override
			public void onSuccess(String data) {
				Log.d("WebViewActivity", "支付返回的数据+++" + data);

				Gson gson = new Gson();
				AliBean bean = gson.fromJson(data, AliBean.class);

				if (bean == null || bean.getOrderString().equals("")) {
					return;
				}

				String orderString = bean.getOrderString();

				Log.d("WebViewActivity", "支付订单" + orderString);

				aliPay(orderString);

			}

			@Override
			public void onError(String error) {

			}
		});


	}


	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case SDK_PAY_FLAG: {
					PayResult payResult = new PayResult((String) msg.obj);

					/**
					 * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
					 * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
					 * docType=1) 建议商户依赖异步通知
					 */
					String resultInfo = payResult.getResult();// 同步返回需要验证的信息

					String resultStatus = payResult.getResultStatus();
					// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档

					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							Log.d("ShareWebView", "走这个方法了啊吗");
						}
					});

					if (TextUtils.equals(resultStatus, "9000")) {
						Toast.makeText(ShareWebView.this, "支付成功了", Toast.LENGTH_SHORT).show();
						//                        changeOrderId(order_id); //后台修改订单
					} else {
						// 判断resultStatus 为非"9000"则代表可能支付失败
						// "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
						if (TextUtils.equals(resultStatus, "8000")) {
							Toast.makeText(ShareWebView.this, "支付结果确认中", Toast.LENGTH_SHORT).show();


						} else {
							// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
							Toast.makeText(ShareWebView.this, "支付失败了", Toast.LENGTH_SHORT).show();

						}
					}
					break;
				}
				default:
					break;
			}
		}

	};


	/**
	 * 进行支付
	 */

	private void aliPay(final String ordString) {
		if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
			new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialoginterface, int i) {
							//
							finish();
						}
					}).show();
			return;
		}
		//                Runnable payRunnable = new Runnable() {
		//
		//                    @Override
		//                    public void run() {
		//                        PayTask alipay = new PayTask(ShareWebView.this);
		//                        Map<String, String> result = alipay.payV2(ordString, true);
		//
		//                        Message msg = new Message();
		//                        msg.what = SDK_PAY_FLAG;
		//                        msg.obj = result;
		//                        mHandler.sendMessage(msg);
		//                    }
		//                };

		//                Thread payThread = new Thread(payRunnable);
		//                payThread.start();


		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask(ShareWebView.this);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(ordString, true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (ShareWebview != null) {
			ViewParent parent = ShareWebview.getParent();
			if (parent != null) {
				((ViewGroup) parent).removeView(ShareWebview);
			}
			ShareWebview.removeAllViews();
			ShareWebview.destroy();
			ShareWebview = null;
		}

	}

	/**
	 * 生成的随机字符串
	 *
	 * @return
	 */
	public static String getNonceStr() {
		Random random = new Random();
		long val = random.nextLong();
		String res = MD5Utils.encrypt(val + "yzx").toUpperCase();
		if (32 < res.length()) return res.substring(0, 32);
		else return res;
	}

	@Override
	public void finish() {

		super.finish();

		//        ViewGroup view = (ViewGroup) getWindow().getDecorView();
		//        view.removeAllViews();
	}


	//监听webview返回键
	public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
		if (keyCode == keyEvent.KEYCODE_BACK && ShareWebview.canGoBack()) {
			if (ShareWebview.canGoBack()) {
				ShareWebview.goBack();
				ShareWebview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
				WebViewRelative.setVisibility(View.GONE);

				goBackClear();

				if (url.contains("act=order_list")) {
					Intent intent = new Intent(this, HeHeMainActivity.class);
					startActivity(intent);
				}

				return true;
			}
		}
		return super.onKeyDown(keyCode, keyEvent);
	}

	/**
	 * 分享弹出框
	 */
	private void mSharePpw() {

//		backgroundAlpha(0.6f);

		mPop = new PopupWindow(viewPop, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, false);

		mPop.setBackgroundDrawable(new BitmapDrawable());

		//设置外部可点击消失
		mPop.setOutsideTouchable(true);

		mPop.setFocusable(true);


		mPop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//设置ppw进出场动画
		mPop.setAnimationStyle(R.style.anim_menu_bottombar);

		mPop.showAtLocation(findViewById(R.id.main_Linear), Gravity.BOTTOM, 0, 0);

		//ppw 的关闭动画
		mPop.setOnDismissListener(new poponDismissListener());


//


	}

	/**
	 * popupwindow 实现关闭事件监听
	 */
	class poponDismissListener implements PopupWindow.OnDismissListener {

		@Override
		public void onDismiss() {
			// TODO Auto-generated method stub
			//Log.v("List_noteTypeActivity:", "我是关闭事件");
//			backgroundAlpha(1.0f);

		}

	}

	/**
	 *
	 */
	// TODO: 2017/9/15 这是设置 背景为半透明
//	public void backgroundAlpha(float bgAlpha) {
//		WindowManager.LayoutParams lp = getWindow().getAttributes();
//		lp.alpha = bgAlpha; //0.0-1.0
//		getWindow().setAttributes(lp);
//
//
//	}

	/**
	 *  微信分享
	 */

	/**
	 * 分享微信朋友圈
	 *
	 * @param type
	 */
	private void share(SHARE_TYPE type) {


		WXWebpageObject webpageObject = new WXWebpageObject();

		webpageObject.webpageUrl =finishURL ;

		final WXMediaMessage msg = new WXMediaMessage(webpageObject);


		msg.title = "和合生活";

		msg.description = "您的好友邀请您加入和合生活";


		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.heheicon);

		msg.thumbData = Util.bmpToByteArray(bitmap, true);

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = String.valueOf(System.currentTimeMillis());
		req.message = msg;
		switch (type) {
			case Type_WXSceneSession:
				req.scene = WXSceneSession;
				break;
			case Type_WXSceneTimeline:
				req.scene = WXSceneTimeline;
				break;
		}
		wxApi.sendReq(req);

		if (mPop.isShowing()) {
			mPop.dismiss();
		}
	}

	private String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}

	/**
	 * popupWindow 点击事件
	 *
	 * @param v
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {

			case R.id.weChat:

				share(SHARE_TYPE.Type_WXSceneSession);

				break;

			case R.id.QQ:

				break;

			case R.id.friendCircle:
				share(SHARE_TYPE.Type_WXSceneTimeline);
				break;
			case R.id.mText_cancle:
//				backgroundAlpha(1.0f);

				if (mPop.isShowing()) {
					mPop.dismiss();
				}
				break;

		}

	}
}
