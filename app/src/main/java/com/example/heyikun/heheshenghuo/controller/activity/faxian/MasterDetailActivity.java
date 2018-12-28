package com.example.heyikun.heheshenghuo.controller.activity.faxian;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.App;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.YangshengArticleImageAdapter;
import com.example.heyikun.heheshenghuo.controller.adapter.faxian.YangshengMasterArticleAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.MasterDetailBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.ChangePwdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.eventbus.MasterEvBean;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.DividerItemDecorationRecy;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.RawResponseHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/12/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/11
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：养生达人个人主页的展示
 */

public class MasterDetailActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.master_people_image)
    ImageView masterPeopleImage;
    @BindView(R.id.master_new_reply)
    TextView masterNewReply;
    @BindView(R.id.master_people_name)
    TextView masterPeopleName;
    @BindView(R.id.master_bigcast)
    TextView masterBigcast;
    @BindView(R.id.master_bigcast_linear)
    LinearLayout masterBigcastLinear;
    @BindView(R.id.master_guanzhu_people)
    TextView masterGuanzhuPeople;
    @BindView(R.id.master_guanzhu_people_linear)
    LinearLayout masterGuanzhuPeopleLinear;
    @BindView(R.id.master_fans)
    TextView masterFans;
    @BindView(R.id.master_fans_linear)
    LinearLayout masterFansLinear;
    @BindView(R.id.master_huati)
    TextView masterHuati;
    @BindView(R.id.master_huati_linear)
    LinearLayout masterHuatiLinear;
    @BindView(R.id.master_add_guanzhu)
    TextView masterAddGuanzhu;
    @BindView(R.id.master_people_desc)
    TextView masterPeopleDesc;
    @BindView(R.id.master_recycler)
    RecyclerView masterRecycler;
    @BindView(R.id.mtext_null)
    TextView mtextNull;
    private String mUserId;
    private String mtoken;
    private String encryptUserId;
    private String mTokenTwo;
    private String encryptToken;
    private String master_id;
    private List<MasterDetailBean.DataBean.ArticleBean> articleBeanList;
    private YangshengMasterArticleAdapter articleAdapter;
    private MasterDetailBean.DataBean data;
    private String article_id;
    private PopupWindow popupWindow;
    private int articlePosition;
    private YangshengArticleImageAdapter imageAdapter;
    private String atten_status;
    private String stade;

    @Override
    protected int layoutId() {
        return R.layout.activity_yangsheng_userpeople;
    }

    @Override
    protected void initView() {
        articleBeanList = new ArrayList<>();


        Intent intent = getIntent();

        master_id = intent.getStringExtra("master_id");

        String master_image = intent.getStringExtra("master_image");

        Glide.with(MasterDetailActivity.this)
                .load(master_image)
                .asBitmap()
                .placeholder(R.drawable.jcwz)
                .centerCrop()
                .into(new BitmapImageViewTarget(masterPeopleImage) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);

                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                        drawable.setCircular(true);

                        masterPeopleImage.setImageDrawable(drawable);

                    }
                });


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setSmoothScrollbarEnabled(true);
        manager.setAutoMeasureEnabled(true);
        masterRecycler.setHasFixedSize(true);
        masterRecycler.setNestedScrollingEnabled(false);
        masterRecycler.addItemDecoration(new DividerItemDecorationRecy(this, DividerItemDecorationRecy.VERTICAL_LIST));
        masterRecycler.setLayoutManager(manager);


    }

    @Override
    protected void initData() {

        lodaData(master_id);
    }

    @Override
    protected void initLisenter() {

        masterAddGuanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mUserId.equals("") || mtoken.equals("")) {
                    Toast.makeText(MasterDetailActivity.this, "请先登录", Toast.LENGTH_SHORT).show();

                } else if (atten_status.equals("0")) {
                    MasterAtten(1);//加关注
                    masterAddGuanzhu.setText("已关注");
                    masterAddGuanzhu.setTextColor(getResources().getColor(R.color.color_ccc));
                    Drawable drawable = getResources().getDrawable(R.drawable.guanzhu);

                    masterAddGuanzhu.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                    masterAddGuanzhu.setCompoundDrawablePadding(8);

                    atten_status = "1";
                } else if (atten_status.equals("1")) {
                    MasterAtten(0); //取消关注

                    masterAddGuanzhu.setText("加关注");
                    masterAddGuanzhu.setTextColor(getResources().getColor(R.color.colorTextYangXIn));
                    Drawable drawable = getResources().getDrawable(R.drawable.jiaguanzhu_man3x);

                    masterAddGuanzhu.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                    masterAddGuanzhu.setCompoundDrawablePadding(8);

                    atten_status = "0";
                }

            }
        });

    }


    private void MasterAtten(int state) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "MasterAtten");
        params.put("people_id", master_id);
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("state", String.valueOf(state));
        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)
                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {

                        if(response.getStatus().equals("200")){
                            Toast.makeText(MasterDetailActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MasterDetailActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });


    }

    private void lodaData(String id) {
        mUserId = AppUtils.get().getString("user_id", "");


        mtoken = AppUtils.get().getString("token", "");


        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        try {

            //给userID 还有 生成二次token ，在进行AES加密
            if (!mUserId.equals("") && !mUserId.equals("")) {
                encryptUserId = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);

                mTokenTwo = mUserId + "," + mtoken + "," + timestamp;


                encryptToken = AESUtils.Encrypt(mTokenTwo, BaseUrl.AESKey);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "MasterPersonal");
        params.put("user_id", encryptUserId);
        params.put("token", encryptToken);
        params.put("master_id", id);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private MasterDetailBean masterDetailBean;

            @Override
            public void onSuccess(final String data) {
                Log.d("MasterDetailActivity", data);

                Gson gson = new Gson();
                try {
                    masterDetailBean = gson.fromJson(data, MasterDetailBean.class);

                    if (masterDetailBean.getStatus().equals("200")) {
                        final MasterDetailBean.DataBean dataBean = masterDetailBean.getData();
                        MasterDetailBean.DataBean.MasterInfoBean master_info = dataBean.getMaster_info();

                        masterPeopleName.setText(master_info.getMaster_name());
                        masterPeopleDesc.setText(master_info.getMaster_desc());


                        //大咖
                        masterBigcast.setText(master_info.getBig_sum());
                        masterFans.setText(master_info.getFans_sum());
                        masterGuanzhuPeople.setText(master_info.getAtten_sum());
                        masterHuati.setText(master_info.getTopic_sum());

                        atten_status = master_info.getAtten_status();
                        if (atten_status.equals("0")) {

                            masterAddGuanzhu.setText("加关注");
                            masterAddGuanzhu.setTextColor(getResources().getColor(R.color.ZhenDuanText));

                            Drawable drawable = getResources().getDrawable(R.drawable.jiaguanzhu_man3x);

                            masterAddGuanzhu.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

                            masterAddGuanzhu.setCompoundDrawablePadding(8);

                        } else {
                            Drawable drawable = getResources().getDrawable(R.drawable.guanzhu);

                            masterAddGuanzhu.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);

                            masterAddGuanzhu.setText("已关注");
                            masterAddGuanzhu.setTextColor(getResources().getColor(R.color.color_ccc));

                            masterAddGuanzhu.setCompoundDrawablePadding(8);
                        }

                        //下面文章的加载数据
                        if (dataBean.getArticle().size() < 0 || dataBean.getArticle() == null) {
                            mtextNull.setVisibility(View.VISIBLE);
                            masterRecycler.setVisibility(View.GONE);
                        } else {
                            mtextNull.setVisibility(View.GONE);
                            masterRecycler.setVisibility(View.VISIBLE);
                            final List<MasterDetailBean.DataBean.ArticleBean> article = dataBean.getArticle();
                            articleBeanList.addAll(article);

                            imageAdapter = new YangshengArticleImageAdapter(articleBeanList, MasterDetailActivity.this);

                            imageAdapter.setArticleOnClick(new YangshengArticleImageAdapter.ArticleOnClick() {
                                @Override
                                public void deleteOnitemClick(int position) {
                                    articlePosition = position;
                                    article_id = dataBean.getArticle().get(position).getArticle_id();


                                    Log.d("MasterDetailActivity", article_id);
                                    mPopWindow();
                                }

                                @Override
                                public void editonItemClick(int position) {

                                }
                            });
                            masterRecycler.setAdapter(imageAdapter);

                        }

                        Glide.with(MasterDetailActivity.this)
                                .load(dataBean.getMaster_info().getMaster_headimg())
                                .asBitmap()
                                .placeholder(R.drawable.jcwz)
                                .into(new BitmapImageViewTarget(masterPeopleImage) {
                                    @Override
                                    protected void setResource(Bitmap resource) {
                                        super.setResource(resource);

                                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), resource);
                                        drawable.setCircular(true);
                                        masterPeopleImage.setImageDrawable(drawable);
                                    }
                                });

                    }


                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onError(String error) {

            }
        });

    }


    @OnClick({R.id.Image_Back, R.id.master_bigcast_linear, R.id.master_guanzhu_people_linear, R.id.master_fans_linear, R.id.master_huati_linear})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.Image_Back:
                finish();
                break;
            //关注的大咖
            case R.id.master_bigcast_linear:
                Intent intent = new Intent(MasterDetailActivity.this, MasterPeopleBigcastActivity.class);
                startActivity(intent);

                MasterEvBean bean = new MasterEvBean();
                bean.setBigcastsBeanList(data.getBigcasts());

                break;
            //关注的人
            case R.id.master_guanzhu_people_linear:

                Intent intent1 = new Intent(MasterDetailActivity.this, MasterPeopleMasterActivity.class);
                startActivity(intent1);

                MasterEvBean bean1 = new MasterEvBean();
                bean1.setMasterBeanList(data.getMaster());

                break;
            //我的粉丝
            case R.id.master_fans_linear:
                Intent intent2 = new Intent(MasterDetailActivity.this, MasterPeopleFansActivity.class);
                startActivity(intent2);

                MasterEvBean bean2 = new MasterEvBean();
                bean2.setFansBeanList(data.getFans());


                break;

            //话题
            case R.id.master_huati_linear:


                break;
        }
    }


    private void mIntent() {
        Intent intent = new Intent(this, MasterPeopleBigcastActivity.class);
        startActivity(intent);


    }


    public void deleteArticle() {

        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timeStame = TimeUtils.dataOne(currentTime_today);

        String aesUserid = null;
        String aesToken = null;


        String mToken = mUserId + "," + mtoken + "," + timeStame;
        try {
            if (!mUserId.equals("") || !aesToken.equals("")) {
                aesUserid = AESUtils.Encrypt(mUserId, BaseUrl.AESKey);

                aesToken = AESUtils.Encrypt(mToken, BaseUrl.AESKey);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> params = new HashMap<>();
        params.put("action", "DelMasterArt");
        params.put("user_id", aesUserid);
        params.put("token", aesToken);
        params.put("article_id", article_id);
        App.myOkHttp.post()
                .url(BaseUrl.BaseUrl)
                .params(params)

                .enqueue(new GsonResponseHandler<ChangePwdBean>() {
                    @Override
                    public void onSuccess(int statusCode, ChangePwdBean response) {
                        if (response.getStatus().equals("200")) {

                            articleBeanList.remove(articlePosition);

                            imageAdapter.notifyDataSetChanged();
                            //                            try {
                            //                                articleAdapter.notifyDataSetChanged();
                            //                            } catch (Exception e) {
                            //                                e.printStackTrace();
                            //                            }
                            if (popupWindow.isShowing()) {
                                popupWindow.dismiss();
                                backgroundAlpha(1.0f);
                            }

                        }
                    }

                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                    }
                });
    }


    private void mPopWindow() {
        backgroundAlpha(0.4f);

        View view = LayoutInflater.from(this).inflate(R.layout.activity_user_address_ppw, null);

        TextView mTVdismiss = (TextView) view.findViewById(R.id.mText_dismiss);

        TextView mTVdelete = (TextView) view.findViewById(R.id.mText_Delete);

        TextView text = (TextView) view.findViewById(R.id.pop_text);
        text.setText("确定删除此帖子？");

        mTVdismiss.setOnClickListener(this);
        mTVdelete.setOnClickListener(this);

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                true);
        //设置背景颜色
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        //设置外部不可点击
        popupWindow.setOutsideTouchable(false);

        popupWindow.setFocusable(true);
        popupWindow.setClippingEnabled(false);

        //popupwindow的弹出位置nnnnnn
        popupWindow.showAtLocation(findViewById(R.id.article_linear), Gravity.CENTER, 0, 0);

        //里面button的点击事件
        //        button.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                popupWindow.dismiss();
        //                backgroundAlpha(1f);
        //
        //                //给Intent设置启动模式，让他直接返回到上级界面，不然就重新返回来了
        //
        //
        //            }
        //        });
        popupWindow.setOnDismissListener(new poponDismissListener());
    }

    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);


    }

    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mText_dismiss:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    backgroundAlpha(1f);
                }


                break;
            //点击删除地址做的操作
            case R.id.mText_Delete:

                deleteArticle();


                break;

        }

    }
}
