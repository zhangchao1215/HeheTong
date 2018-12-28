package com.example.heyikun.heheshenghuo.controller.shop;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by hyk on 2018/1/8.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/8
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 商品
 */

public class ShoppingFragment extends BaseFragment {
    @BindView(R.id.Shopping_pay_viewP)
    ViewPager ShoppingPayViewP;
    @BindView(R.id.Shopping_RadioGroup)
    RadioGroup ShoppingRadioGroup;
    @BindView(R.id.Shopping_share_text)
    TextView ShoppingShareText;
    @BindView(R.id.relative)
    RelativeLayout relative;
    @BindView(R.id.shopping_text_Name)
    TextView shoppingTextName;
    @BindView(R.id.shopping_tizhi_linear)
    LinearLayout shoppingTizhiLinear;
    @BindView(R.id.shopping_text_price)
    TextView shoppingTextPrice;
    @BindView(R.id.shopping_text_oldPrice)
    TextView shoppingTextOldPrice;
    @BindView(R.id.youhui_relative)
    RelativeLayout youhuiRelative;
    @BindView(R.id.vip_relative)
    RelativeLayout vipRelative;
    @BindView(R.id.select_relative)
    RelativeLayout selectRelative;
    Unbinder unbinder;
    @BindView(R.id.mScrollview)
    ScrollView mScrollview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shopping_pay;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initListener() {

    }


    @OnClick({R.id.Shopping_share_text, R.id.youhui_relative, R.id.vip_relative, R.id.select_relative})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //分享
            case R.id.Shopping_share_text:


                break;

            //优惠
            case R.id.youhui_relative:
                mPop();

                break;

            //VIP价格
            case R.id.vip_relative:
                mPop();

                break;

            //进行选择
            case R.id.select_relative:
                mPop();

                break;
        }
    }


    private void mPop() {
        backgroundAlpha(0.7f);
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.activity_shopping_ppw, null);

        Button button = (Button) view.findViewById(R.id.but_dismiss);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ppw_recycler);

        final PopupWindow ppw = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);

        ppw.setBackgroundDrawable(new ColorDrawable());


        ppw.setOutsideTouchable(false);

        ppw.setFocusable(true);

        ppw.setAnimationStyle(R.style.anim_menu_bottombar);

        ppw.showAtLocation(getActivity().findViewById(R.id.mScrollview), Gravity.BOTTOM, 0, 0);

        ppw.setOnDismissListener(new poponDismissListener());


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ppw.dismiss();
                backgroundAlpha(1.0f);

            }
        });

    }

    /**
     * popupwindow的内部类
     */
    // TODO: 2017/9/15 这是设置 背景为半透明
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);


    }


    class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }

    }
}
