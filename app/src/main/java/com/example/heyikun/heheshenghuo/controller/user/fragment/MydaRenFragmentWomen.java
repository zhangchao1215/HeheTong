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
import com.example.heyikun.heheshenghuo.modle.util.TimeUtils;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by hyk on 2017/9/22.
 * <p>
 * 我的达人
 */

public class MydaRenFragmentWomen extends BaseFragment {
    @BindView(R.id.user_myDaka)
    LinearLayout userMyDaka;
    @BindView(R.id.user_myAttentionPeople)
    LinearLayout userMyAttentionPeople;
    @BindView(R.id.user_myFan)
    LinearLayout userMyFan;
    @BindView(R.id.user_myThemes)
    LinearLayout userMyThemes;
    @BindView(R.id.user_myNewReply)
    LinearLayout userMyNewReply;

    @BindView(R.id.user_Text_myDaka)
    TextView userTextMyDaka;
    @BindView(R.id.user_Text_myAttentionPeople)
    TextView userTextMyAttentionPeople;
    @BindView(R.id.user_Text_myFans)
    TextView userTextMyFans;
    @BindView(R.id.user_Text_myTheme)
    TextView userTextMyTheme;
    @BindView(R.id.user_Text_NewReply)
    TextView userTextNewReply;
    Unbinder unbinder1;
    private String mTokenTwo;
    private String user_id;
    private String bigcastlink;

    private String url;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_my_daren;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView(View view) {
        //获取时间戳
        String currentTime_today = TimeUtils.getCurrentTime_Today();
        String timestamp = TimeUtils.dataOne(currentTime_today);

        user_id = AppUtils.get().getString("user_id", "");

        String token = AppUtils.get().getString("token", "");

        mTokenTwo = user_id + "," + token + "," + timestamp;


        url = "http://hehe.heyishenghuo.com/mobile2/app2/public/personal-page.php?id=" + user_id + "&from=1";

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.user_myDaka, R.id.user_myAttentionPeople, R.id.user_myFan, R.id.user_myThemes, R.id.user_myNewReply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //我的大咖
            case R.id.user_myDaka:
                IntentWeb("大咖");
                break;
            //我关注的人
            case R.id.user_myAttentionPeople:

                IntentWeb("关注");
                break;
            //粉丝
            case R.id.user_myFan:
                IntentWeb("粉丝");
                break;
            //主题
            case R.id.user_myThemes:
                IntentWeb("主题");
                break;
            //回复
            case R.id.user_myNewReply:
                IntentWeb("回复");
                break;
        }
    }

    private void IntentWeb(String title) {
//        Intent intent = new Intent(getContext(), ShareWebView.class);
//        intent.putExtra("url", url);
//        intent.putExtra("title", title);
//        getContext().startActivity(intent);

        WebViewUtils.publicWebView(getContext(),url,title);
    }



    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void PeopleFragment1(EBLoginBeanWomen bean) {

        //关注的大咖数量

        String bigcast = bean.getBigcast();

        userTextMyDaka.setText(bigcast);

        //关注我的粉丝数量
        String fans = bean.getFans();

        userTextMyFans.setText(fans);

        //我关注的数量
        String follow = bean.getFollow();

        userTextMyAttentionPeople.setText(follow);

        //主题

        String theme = bean.getTheme();

        userTextMyTheme.setText(theme);

        //新回复

        String replay = bean.getReplay();

        userTextNewReply.setText(replay);

    }

    /**
     * 获取网址链接
     */

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void EventLinkThreadModle(EventUserLinkBean bean) {
        bigcastlink = bean.getBigcastlink();


    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
