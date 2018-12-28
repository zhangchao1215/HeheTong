package com.example.heyikun.heheshenghuo;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.Second_RecyclerBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static com.example.heyikun.heheshenghuo.R.*;

/**
 * Created by hyk on 2017/10/17.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/17
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class FlexBoxLayout extends BaseActivity {
    @BindView(id.FlexBox)
    FlexboxLayout FlexBox;
    private List<Second_RecyclerBean.DataBean> mList;

    @Override
    protected int layoutId() {
        return R.layout.activity_flecboxlayout;
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();

        init();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initLisenter() {

    }


    private void init() {
        Map<String, String> params = new HashMap<>();
        params.put("action", "Diagnosis");
        params.put("typeid", "2");
        params.put("diag_id", "59");
        params.put("disease_id", "2");


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {

            private TextView lastClickView;

            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();


                Second_RecyclerBean bean = gson.fromJson(data, Second_RecyclerBean.class);

                final List<Second_RecyclerBean.DataBean> beanList = bean.getData();

                for (int i = 0; i < beanList.size(); i++) {


                    final TextView textView = new TextView(FlexBoxLayout.this);

                    textView.setText(bean.getData().get(i).getDiag_name());
                    textView.setGravity(Gravity.CENTER);

                    textView.setTextSize(12);

                    textView.setBackground(getResources().getDrawable(drawable.tv_item_selector));


                    textView.setTextColor(getResources().getColor(color.colorTextYangXIn));


                    ViewGroup.LayoutParams params = textView.getLayoutParams();


                    if (params instanceof FlexboxLayout.LayoutParams) {
                        FlexboxLayout.LayoutParams layoutParams = (FlexboxLayout.LayoutParams) params;
                        //                        layoutParams.setFlexBasisPercent(0.5f);
                    }
                    final int pisotion = i;

                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (v == lastClickView) {
                                return;
                            }

                            v.setBackgroundResource(drawable.self_zhengzhuang_shape_true);

                            Second_RecyclerBean.DataBean dataBean = beanList.get(pisotion);

                            if (lastClickView != null) {

                                lastClickView.setBackgroundResource(drawable.self_zhengzhuang_shape_false);

                            }

                            lastClickView = (TextView) v;
                        }
                    });



                    FlexBox.addView(textView); //通过FlexboxLayout.LayoutParams 设置子元素支持的属性
                }

            }

            @Override
            public void onError(String error) {

            }
        });


    }

}
