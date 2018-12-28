package com.example.heyikun.heheshenghuo;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.controller.adapter.RecyclerItemAdapter;
import com.example.heyikun.heheshenghuo.modle.base.BaseActivity;
import com.example.heyikun.heheshenghuo.modle.base.BaseUrl;
import com.example.heyikun.heheshenghuo.modle.bean.Book;
import com.example.heyikun.heheshenghuo.modle.bean.TypeIdBean;
import com.example.heyikun.heheshenghuo.modle.callBack.MyCallBack;
import com.example.heyikun.heheshenghuo.modle.htttp.OkHttpUtils;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by hyk on 2017/9/16.
 */

public class RecyclerBiaoQian extends BaseActivity {
    String[] tags = {"婚姻育", "散文", "设计", "上班这", "影视", "大学", "美人说", "运动", "工具癖", "生活家", "程序员", "想法", "短小说", "美食", "教育", "心理", "奇思", "美食", "摄影"};
    @BindView(R.id.FlexBox)
    FlexboxLayout FlexBox;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    private Book book;
    private RecyclerItemAdapter adapter;
    private List<Book> mList = new ArrayList<>();
    private List<TypeIdBean.DataBean> beanList = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.recycler_biaoqian;
    }

    @Override
    protected void initView() {


        // TODO: 2017/9/16 适配器添加   //这是用适配器添加的，
        for (int i = 0; i < tags.length; i++) {
            book = new Book();

            book.setName(tags[i]);

//            FlexBox.addView(createNewFlexItemTextView(book));

            mList.add(book);

        }
        GridLayoutManager manager = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(manager);

        adapter = new RecyclerItemAdapter(beanList, this);

        mRecyclerView.setAdapter(adapter);
        //------------------------------------------------------------------------------------
        // TODO: 2017/9/16 代码中动态添加
//        for (int i = 0; i < 16; i++) {
//            RadioButton radioButton = new RadioButton(this);
//            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
//            //设置RadioButton边距 (int left, int top, int right, int bottom)
//            lp.setMargins(15, 0, 0, 0);
//            //设置RadioButton背景
//            //radioButton.setBackgroundResource(R.drawable.xx);
//            //设置RadioButton的样式
//            radioButton.setButtonDrawable(null);
//
//            radioButton.setBackgroundResource(R.drawable.self_zhengzhuang_selector);
//            //设置文字距离四周的距离
//            radioButton.setPadding(80, 0, 0, 0);
//            //设置文字
//            radioButton.setText("this is radioButton  " + i);
//            final int finalI = i;
//            //设置radioButton的点击事件
//            radioButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(RecyclerBiaoQian.this, "this is radioButton  " + finalI, Toast.LENGTH_SHORT).show();
//                }
//            });
//            //将radioButton添加到radioGroup中
//            radioGroup.addView(radioButton);
//        }

    }


    /**
     * 动态创建TextView * @param book * @return
     */
    private TextView createNewFlexItemTextView(final Book book) {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(book.getName());
        textView.setTextSize(12);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        textView.setBackgroundResource(R.drawable.self_zhengzhuang_shape_false);
        textView.setTag(book.getId());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("颜色", book.getName());
            }
        });
        int padding = Util.dpToPixel(this, 4);
        int paddingLeftAndRight = Util.dpToPixel(this, 8);
        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = Util.dpToPixel(this, 6);
        int marginTop = Util.dpToPixel(this, 16);
        layoutParams.setMargins(margin, marginTop, margin, 0);
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    @Override
    protected void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("action", "Diagnosis");
        params.put("typeid", String.valueOf(2));


        OkHttpUtils.getInstands().OkhttpPost(BaseUrl.BaseUrl, params, "", new MyCallBack() {
            @Override
            public void onSuccess(String data) {
                Gson gson = new Gson();
                TypeIdBean typeIdBean = gson.fromJson(data, TypeIdBean.class);

                List<TypeIdBean.DataBean> data1 = typeIdBean.getData();

                beanList.addAll(data1);
            }

            @Override
            public void onError(String error) {

            }
        });


    }

    @Override
    protected void initLisenter() {

    }

    public static class Util {
        public int pixelToDp(Context context, int pixel) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return pixel < 0 ? pixel : Math.round(pixel / displayMetrics.density);
        }

        public static int dpToPixel(Context context, int dp) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            return dp < 0 ? dp : Math.round(dp * displayMetrics.density);
        }

    }
}