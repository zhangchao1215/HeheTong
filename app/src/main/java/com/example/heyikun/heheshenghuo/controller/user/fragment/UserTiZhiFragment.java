package com.example.heyikun.heheshenghuo.controller.user.fragment;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.base.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by hyk on 2017/10/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/11
 * <p>
 * 3：类描述： 体质 里面有些自测题
 * <p>
 * 4:类功能： 测试体质
 */

public class UserTiZhiFragment extends BaseFragment {
    @BindView(R.id.TiZhi_QiyuText)
    RelativeLayout TiZhiQiyuText;
    @BindView(R.id.TiZhi_ShiReText)
    RelativeLayout TiZhiShiReText;
    @BindView(R.id.TiZhi_YangXuText)
    RelativeLayout TiZhiYangXuText;
    @BindView(R.id.TiZhi_XueyuText)
    RelativeLayout TiZhiXueyuText;
    @BindView(R.id.TiZhi_Scrollview)
    ScrollView TiZhiScrollview;
    Unbinder unbinder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_tizhi_fragment;
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

}
