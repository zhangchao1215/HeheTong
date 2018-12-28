package com.example.heyikun.heheshenghuo.controller.user.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.ShareWebView;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBLoginBean;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBLoginBeanWomen;
import com.example.heyikun.heheshenghuo.modle.eventbus.EventUserLinkBean;
import com.example.heyikun.heheshenghuo.modle.util.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/9/22.
 * <p>
 * 我的资产
 */

public class MyMoneyFragmentWomen extends BaseFragment {
    @BindView(R.id.user_myAccount)
    LinearLayout userMyAccount;
    @BindView(R.id.user_myHe_Ticket)
    LinearLayout userMyHeTicket;
    @BindView(R.id.user_myYi_Ticket)
    LinearLayout userMyYiTicket;
    @BindView(R.id.user_myKun_Ticket)
    LinearLayout userMyKunTicket;
    @BindView(R.id.user_myDiscounts)
    LinearLayout userMyDiscounts;
    @BindView(R.id.user_Text_myAccount)
    TextView userTextMyAccount;
    @BindView(R.id.user_Text_HeTicket)
    TextView userTextHeTicket;
    @BindView(R.id.user_Text_YiTicket)
    TextView userTextYiTicket;
    @BindView(R.id.user_Text_KunTicket)
    TextView userTextKunTicket;
    @BindView(R.id.user_Text_YouHuiTicket)
    TextView userTextYouHuiTicket;
    private String easylink;
    private String frozenlink;
    private String couponlink;
    private String kunlink;
    private String balancelink;
    private String user_id;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_my_zichan;
    }

    @Override
    protected void initData() {
        user_id = AppUtils.get().getString("user_id", "");
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.user_myAccount, R.id.user_myHe_Ticket, R.id.user_myYi_Ticket, R.id.user_myKun_Ticket, R.id.user_myDiscounts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //我的账户
            case R.id.user_myAccount:
                //                mIntent(UserMyAccountActivity.class);

                //                WebViewUtils.ArticleWebView(getContext(), balancelink);

                mIntent(balancelink, "我的账户");
                break;

            //我的和券
            case R.id.user_myHe_Ticket:
                //                mIntent(HeTicketActivity.class);
                //                WebViewUtils.ArticleWebView(getContext(), frozenlink);
                mIntent(frozenlink, "和券");
                break;

            //我的易券
            case R.id.user_myYi_Ticket:
                //                mIntent(YiTicketActivity.class);
                //                WebViewUtils.ArticleWebView(getContext(), easylink);

                mIntent(easylink, "易券");
                break;

            //我的坤券
            case R.id.user_myKun_Ticket:
                //                mIntent(KunTicketActivity.class);

                //                WebViewUtils.ArticleWebView(getContext(), kunlink);
                mIntent(kunlink, "坤券");
                break;

            //我的优惠券
            case R.id.user_myDiscounts:
                //                mIntent(DiscountsTicketActivity.class);


                mIntent(couponlink, "优惠券");
                //                WebViewUtils.ArticleWebView(getContext(), couponlink);
                break;
        }
    }

    private void mIntent(String url, String title) {
        Intent intent = new Intent(getContext(), ShareWebView.class);
        intent.putExtra("url", url + "?id=" + user_id);
        intent.putExtra("title", title);

        startActivity(intent);


    }



    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void MoneyFragment1(EBLoginBeanWomen bean) {

        //和券
        String heTicket = bean.getHeTicket();

        userTextHeTicket.setText(heTicket);
        //易券
        String yiTicket = bean.getYiTicket();

        userTextYiTicket.setText(yiTicket);
        //坤券

        String kunTicket = bean.getKunTicket();

        userTextKunTicket.setText(kunTicket);

        //我的余额

        String userMoney = bean.getUserMoney();

        userTextMyAccount.setText(userMoney);

        userTextYouHuiTicket.setText(bean.getCoupon());

    }

    /**
     * 获取网址链接
     */

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void EventLinkMoney(EventUserLinkBean bean) {

        easylink = bean.getEasylink();

        frozenlink = bean.getFrozenlink();

        couponlink = bean.getCouponlink();

        kunlink = bean.getKunlink();

        balancelink = bean.getBalancelink();


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
