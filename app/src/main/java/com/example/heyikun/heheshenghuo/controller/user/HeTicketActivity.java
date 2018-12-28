package com.example.heyikun.heheshenghuo.controller.user;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hyk on 2017/10/12.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/12
 * <p>
 * 3：类描述：易券账户
 * <p>
 * 4:类功能：
 */

public class HeTicketActivity extends BaseActivity {
    @BindView(R.id.Image_Back)
    ImageView ImageBack;
    @BindView(R.id.mText_include_Title)
    TextView mTextIncludeTitle;
    @BindView(R.id.mText_GetYi_ticket)
    TextView mTextGetYiTicket;
    @BindView(R.id.mText_Detail)
    TextView mTextDetail;
    @BindView(R.id.mText_textOne)
    TextView mTextTextOne;
    @BindView(R.id.mText_Time)
    TextView mTextTime;
    @BindView(R.id.mText_Money)
    TextView mTextMoney;
    @BindView(R.id.Yiticket_ListView)
    ListView YiticketListView;
    @BindView(R.id.mText_ticket_balance)
    TextView mTextTicketBalance;
    @BindView(R.id.mText_record)
    TextView mTextRecord;

    @Override
    protected int layoutId() {
        return R.layout.activity_ticket_yi_account;
    }

    @Override
    protected void initView() {
        mTextIncludeTitle.setText("和券账户");
        mTextIncludeTitle.setTextSize(16);

        mTextGetYiTicket.setText("如何赚取和劵");

        mTextTicketBalance.setText("和券余额");

        mTextRecord.setText("和券记录");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    @OnClick({R.id.Image_Back, R.id.mText_GetYi_ticket, R.id.mText_Detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.Image_Back:
                finish();
                break;
            //获取易券
            case R.id.mText_GetYi_ticket:


                break;

            //查看详情
            case R.id.mText_Detail:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
