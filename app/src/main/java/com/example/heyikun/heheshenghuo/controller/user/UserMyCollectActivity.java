package com.example.heyikun.heheshenghuo.controller.user;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.ArticleCollectBean;
import com.example.heyikun.heheshenghuo.modle.bean.ShopCollectBean;
import com.example.heyikun.heheshenghuo.modle.bean.login.LoginMessageBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.example.heyikun.heheshenghuo.modle.util.AESUtils;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;
import com.example.heyikun.heheshenghuo.modle.util.MD5Utils;
import com.example.heyikun.heheshenghuo.modle.util.SwipeListLayout;
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/26.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/26
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class UserMyCollectActivity extends BaseActivity {
    @BindView(R.id.mText_compile)
    TextView mTextCompile;
    @BindView(R.id.mText_article)
    TextView mTextArticle;
    @BindView(R.id.mText_commodity)
    TextView mTextCommodity;
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.article_lines)
    View articleLines;
    @BindView(R.id.shop_lines)
    View shopLines;
    @BindView(R.id.article_listviwe)
    ListView articleListviwe;
    @BindView(R.id.Shop_listviwe)
    ListView ShopListviwe;
    @BindView(R.id.mText_checkAll)
    TextView mTextCheckAll;
    @BindView(R.id.Article_cancelAll)
    TextView ArticleCancelAll;
    @BindView(R.id.mRelative)
    RelativeLayout mRelative;
    @BindView(R.id.Article_Relative)
    LinearLayout ArticleRelative;
    @BindView(R.id.mText_Shop_checkAll)
    TextView mTextShopCheckAll;
    @BindView(R.id.Article_Shop_cancelAll)
    TextView ArticleShopCancelAll;
    @BindView(R.id.Shop)
    RelativeLayout Shop;
    @BindView(R.id.Shop_Relative)
    LinearLayout ShopRelative;
    private String AESUserid = null;
    private String AEStoken = null;
    private CheckBox shopCheckBox, articleCheckBox;

    private int flag;
    private List<ArticleCollectBean.DataBean.ArticleBean> articleBeen;
    private ArticleCollectAdapter articleAdapter;

    private List<ShopCollectBean.DataBean.GoodsBean> shopList;
    private ShopCollectAdapter shopAdapter;
    private TextView mArticleDelete, mArticleTextTitle, mTextType, mTextContent, mTextTime, mTextauthor;

    private TextView mTextDelete, mTextTitle, mTextPrice, mTextOldPrice;
    private ImageView mImage, mArticleImage;

    private int VisiBleFlag;
    private SwipeListLayout mSll_main;

    @Override
    protected int layoutId() {
        return R.layout.activity_user_my_collcet;
    }

    @Override
    protected void initView() {

        articleBeen = new ArrayList<>();
        shopList = new ArrayList<>();
    }

    @Override
    protected void initData() {

        shopAdapter = new ShopCollectAdapter(shopList, UserMyCollectActivity.this);

        articleAdapter = new ArticleCollectAdapter(articleBeen, UserMyCollectActivity.this);

        articleCollect();

        articleListviwe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Toast.makeText(UserMyCollectActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected void initLisenter() {

        ShopListviwe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });


    }


    @OnClick({R.id.Image_Back, R.id.mText_compile, R.id.mText_article, R.id.mText_commodity})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                onBackPressed();

                break;

            //编辑
            case R.id.mText_compile:
                /**
                 * 判断flag == 0 的时候加载文章收藏页面，1 是商品收藏页面
                 * VisiBleFlag = 这个是判断点击编辑的时候显示的哪个页面的CheckBox
                 * 1 是文章收藏
                 *
                 * 0 是商品收藏
                 *  当点击的时候把最下方的全选进行显示
                 */
                if (flag == 0) {


                    ArticleRelative.setVisibility(View.VISIBLE);

                    ShopRelative.setVisibility(View.GONE);

                    VisiBleFlag = 1;  //文章收藏
                    articleAdapter.notifyDataSetChanged();
                } else if (flag == 1) {

                    ArticleRelative.setVisibility(View.GONE);

                    ShopRelative.setVisibility(View.VISIBLE);

                    VisiBleFlag = 0; //商品收藏
                    shopAdapter.notifyDataSetChanged();

                }


                break;
            //文章
            case R.id.mText_article:

                ShopRelative.setVisibility(View.GONE);

                shopAdapter.notifyDataSetChanged();

                flag = 0;


                articleCollect();


                ShopListviwe.setVisibility(View.GONE);

                articleListviwe.setVisibility(View.VISIBLE);

                articleLines.setVisibility(View.VISIBLE);

                shopLines.setVisibility(View.GONE);

                mTextArticle.setTextColor(getResources().getColor(R.color.Black));


                break;

            //商品
            case R.id.mText_commodity:

                ArticleRelative.setVisibility(View.GONE);

                articleAdapter.notifyDataSetChanged();

                flag = 1;

                ShopCollect();

                ShopListviwe.setVisibility(View.VISIBLE);

                articleListviwe.setVisibility(View.GONE);

                shopLines.setVisibility(View.VISIBLE);

                articleLines.setVisibility(View.GONE);

                mTextCommodity.setTextColor(getResources().getColor(R.color.Black));

                break;
        }
    }


    private void articleCollect() {
        VisiBleFlag = 0;
        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        String userid = AppUtils.get().getString("user_id", "");
        String token = AppUtils.get().getString("token", "");

        String AESUserid = null;
        String AEStoken = null;
        String AESsign = null;

        String twotoken = userid + "," + token + "," + timestamp;

        String sign = "AllCollection" + timestamp + BaseUrl.AESKey;

        String MD5Sign = MD5Utils.encrypt(sign);


        try {
            AESUserid = AESUtils.Encrypt(userid, BaseUrl.AESKey);

            AEStoken = AESUtils.Encrypt(twotoken, BaseUrl.AESKey);

            AESsign = AESUtils.Encrypt(MD5Sign, BaseUrl.AESKey);


        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, String> params = new HashMap<>();
        params.put("action", "AllCollection");
        params.put("user_id", AESUserid);
        params.put("token", AEStoken);
        params.put("app_sign", AESsign);

        if (SuccessFlag == 0) {
            OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
                @Override
                public void onSuccess(String data) {
                    Log.d("UserMyCollectActivity", data);
                    Gson gson = new Gson();
                    ArticleCollectBean bean = null;
                    try {
                        bean = gson.fromJson(data, ArticleCollectBean.class);


                        if (data == null || bean == null) {
                            return;
                        } else if (!bean.getStatus().equals("200")) {
                            return;
                        }

                        List<ArticleCollectBean.DataBean.ArticleBean> article = bean.getData().getArticle();

                        articleBeen.addAll(article);

                        articleListviwe.setAdapter(articleAdapter);

                        articleAdapter.notifyDataSetChanged();
                        SuccessFlag = 1;
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onError(String error) {

                }
            });
        } else {
            SuccessFlag = 1;
        }


    }

    /**
     * 商品的收藏的获取
     */

    int SuccessFlag = 0;

    private void ShopCollect() {
        VisiBleFlag = 1;

        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        String userid = AppUtils.get().getString("user_id", "");
        String token = AppUtils.get().getString("token", "");
        String AESsign = null;

        String twotoken = userid + "," + token + "," + timestamp;

        String sign = "AllGoods" + timestamp + BaseUrl.AESKey;

        String MD5Sign = MD5Utils.encrypt(sign);


        try {
            AESUserid = AESUtils.Encrypt(userid, BaseUrl.AESKey);

            AEStoken = AESUtils.Encrypt(twotoken, BaseUrl.AESKey);

            AESsign = AESUtils.Encrypt(MD5Sign, BaseUrl.AESKey);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, String> params = new HashMap<>();
        params.put("action", "AllGoods");
        params.put("user_id", AESUserid);
        params.put("token", AEStoken);
        params.put("app_sign", AESsign);

        if (SuccessFlag == 0) {
            OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
                @Override
                public void onSuccess(String data) {
                    Log.d("UserMyCollectActivity", data);
                    Gson gson = new Gson();
                    ShopCollectBean collectBean = null;
                    try {
                        collectBean = gson.fromJson(data, ShopCollectBean.class);


                        if (data == null || collectBean == null) {
                            return;
                        }
                        if (!collectBean.getStatus().equals("200")) {
                            return;
                        }

                        shopList.addAll(collectBean.getData().getGoods());


                        ShopListviwe.setAdapter(shopAdapter);

                        shopAdapter.notifyDataSetChanged();

                        SuccessFlag = 1;
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
        } else {
            SuccessFlag = 1;
        }
    }

    /**
     * 取消收藏的请求
     *
     * @param id
     * @param type
     */
    private void cancelArticle(String id, String type) {

        Map<String, String> params = new HashMap<>();
        params.put("action", "CancelAll");
        params.put("user_id", AESUserid);
        params.put("token", AEStoken);
        params.put("rec_id", id);
        params.put("type", type);

        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {

                Log.d("UserMyCollectActivity", "删除文章" + data);

                Gson gson = new Gson();

                LoginMessageBean bean = gson.fromJson(data, LoginMessageBean.class);

                if (bean == null || data == null) {
                    return;
                } else if (!bean.getStatus().equals("200")) {
                    return;
                } else {
                    Toast.makeText(UserMyCollectActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onError(String error) {

            }
        });


    }

    /**
     * 文章内部类适配器
     */


    public class ArticleCollectAdapter extends BaseAdapter {

        private List<ArticleCollectBean.DataBean.ArticleBean> mList;
        private Context context;

        public ArticleCollectAdapter(List<ArticleCollectBean.DataBean.ArticleBean> mList, Context context) {
            this.mList = mList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_user_collect_artice, null);

            mTextauthor = (TextView) convertView.findViewById(R.id.Collect_Name);
            mArticleImage = (ImageView) convertView.findViewById(R.id.Collect_Image);
            mTextType = (TextView) convertView.findViewById(R.id.TextTitle);
            mTextTime = (TextView) convertView.findViewById(R.id.Collect_Time);
            mTextContent = (TextView) convertView.findViewById(R.id.Collect_TextTitle);
            mArticleDelete = (TextView) convertView.findViewById(R.id.mText_cancel);
            articleCheckBox = (CheckBox) convertView.findViewById(R.id.mImage_Duigou);
            LinearLayout mLinear = (LinearLayout) convertView.findViewById(R.id.list_linear);
            final SwipeListLayout mSll_main = (SwipeListLayout) convertView.findViewById(R.id.sll_main);

            final ArticleCollectBean.DataBean.ArticleBean articleBean = mList.get(position);

            mTextTime.setText(articleBean.getAdd_time());
            mTextType.setText(articleBean.getTitle());
            mTextContent.setText(articleBean.getTitle());
            mTextauthor.setText(articleBean.getAuthor());

            Glide.with(UserMyCollectActivity.this)
                    .load(articleBean.getPic_url())
                    .placeholder(R.drawable.jcwz)
                    .into(mArticleImage);

            final ArticleCollectBean.DataBean.ArticleBean bean = mList.get(position);

            //对CheckBox进行显示

            if (VisiBleFlag == 1) {

                articleCheckBox.setVisibility(View.VISIBLE);

                //                shopCheckBox.setVisibility(View.GONE);

            }


            mArticleDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSll_main.setStatus(SwipeListLayout.Status.Close, true);

                    mList.remove(position);

                    articleAdapter.notifyDataSetChanged();

                    cancelArticle(bean.getArticle_id(), "1");


                }
            });

            /**
             * 我用view的点击事件,最外层用linear
             */

            mLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    WebViewUtils.publicWebView(context, articleBean.getArticle_link(), "文章");
                    Log.d("ArticleCollectAdapter", articleBean.getArticle_link());
                }
            });

            return convertView;

        }
    }


    /**
     * 商品收藏的内部类适配器
     */

    public class ShopCollectAdapter extends BaseAdapter {
        private List<ShopCollectBean.DataBean.GoodsBean> mList;
        private Context context;

        public ShopCollectAdapter(List<ShopCollectBean.DataBean.GoodsBean> mList, Context context) {
            this.mList = mList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(context).inflate(R.layout.activity_user_collect_shop, null);

            mTextTitle = (TextView) convertView.findViewById(R.id.Collect_TextShop_Name);

            mImage = (ImageView) convertView.findViewById(R.id.Collect_Image);

            mTextPrice = (TextView) convertView.findViewById(R.id.Collect_TextPrice);

            mTextOldPrice = (TextView) convertView.findViewById(R.id.Collect_oldPrice);

            mTextDelete = (TextView) convertView.findViewById(R.id.mText_cancel);

            shopCheckBox = (CheckBox) convertView.findViewById(R.id.mImage_Duigou);

            try {
                mSll_main = (SwipeListLayout) convertView.findViewById(R.id.sll_main);
            } catch (Exception e) {
                e.printStackTrace();
            }

            final ShopCollectBean.DataBean.GoodsBean goodsBean = mList.get(position);

            Glide.with(context)
                    .load(goodsBean.getGoods_pic())
                    .centerCrop()
                    .placeholder(R.drawable.jcwz)
                    .into(mImage);

            mTextOldPrice.setText(goodsBean.getOld_price());

            mTextPrice.setText(goodsBean.getGoods_price());

            mTextTitle.setText(goodsBean.getGoods_name());


            if (VisiBleFlag == 0) {
                shopCheckBox.setVisibility(View.VISIBLE);

                //                articleCheckBox.setVisibility(View.GONE);

            }

            mTextDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSll_main.setStatus(SwipeListLayout.Status.Close, true);

                    mList.remove(position);

                    shopAdapter.notifyDataSetChanged();

                    cancelArticle(goodsBean.getGoods_id(), "2");

                }
            });


            return convertView;
        }

    }

}
