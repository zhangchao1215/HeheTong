package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.CircleProgressView;
import com.example.heyikun.heheshenghuo.modle.bean.NewLifeMuBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hyk on 2018/1/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/14
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：首页最下方的五行测试的展示结果
 */

public class NewLifeMuAdapterTwo extends RecyclerView.Adapter<NewLifeMuAdapterTwo.MyHodler> {
    private List<NewLifeMuBean.FiveTestBean> fiveTestBeanList;
    private Context context;
    private NewOnitemClick newOnitemClick;


    public void setNewOnitemClick(NewOnitemClick newOnitemClick) {
        this.newOnitemClick = newOnitemClick;
    }

    public NewLifeMuAdapterTwo(List<NewLifeMuBean.FiveTestBean> fiveTestBeanList, Context context) {
        this.fiveTestBeanList = fiveTestBeanList;
        this.context = context;
    }

    @Override
    public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_new_life_result_item, null);
        MyHodler hodler = new MyHodler(view);

        return hodler;
    }

    @Override
    public void onBindViewHolder(MyHodler holder, int position) {

        NewLifeMuBean.FiveTestBean fiveTestBean = fiveTestBeanList.get(position);
        String test_sore = fiveTestBean.getTest_sore();
        holder.textFen.setText(test_sore);
        holder.textOne.setText(fiveTestBean.getTest_name());
        int i = Integer.parseInt(test_sore);

        if (i == 0) {
            holder.textFen.setVisibility(View.GONE);
            holder.fenImage.setVisibility(View.VISIBLE);
        } else {
            holder.textFen.setVisibility(View.VISIBLE);
            holder.fenImage.setVisibility(View.GONE);
        }

        holder.Text3.setText(fiveTestBean.getTest_desc());

        //        holder.progressView.setMaxColor(context.getResources().getColor(R.color.quan_xinzang));
        holder.progressView.setMaxProgress(100);
        holder.progressView.setProgress(Integer.parseInt(fiveTestBean.getTest_sore()));

        Log.d("NewLifeMuAdapterTwo", "position:" + position);
        switch (position) {

            case 0:
                getColor(holder, i);

                break;
            case 1:
                getColor(holder, i);
                break;

            case 2:

                getColor(holder, i);
                break;

        }
    }


    private void getColor(MyHodler holder, int testsore) {

        if (testsore >= 0 && testsore <= 20) {

            mColor(holder, context.getResources().getColor(R.color.quan_OneColor));

        } else if (testsore > 20 && testsore <= 40) {
            mColor(holder, context.getResources().getColor(R.color.quan_TwoColor));

        } else if (testsore > 40 && testsore <= 60) {
            mColor(holder, context.getResources().getColor(R.color.quan_ThreeColor));

        } else if (testsore > 60 && testsore <= 80) {
            mColor(holder, context.getResources().getColor(R.color.quan_Fourlor));


        } else {
            mColor(holder, context.getResources().getColor(R.color.quan_FiveColor));

        }


    }


    private void mColor(MyHodler holder, int color) {
        holder.progressView.setMaxColor(color);

        holder.textFen.setTextColor(color);
        holder.Text3.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return fiveTestBeanList.size();
    }

    class MyHodler extends RecyclerView.ViewHolder {

        private TextView textFen, Text3;
        private Button textOne;
        private CircleProgressView progressView;
        private ImageView fenImage;

        public MyHodler(View itemView) {
            super(itemView);
            textOne = (Button) itemView.findViewById(R.id.newLife_item_text);
            textFen = (TextView) itemView.findViewById(R.id.new_life_fen_text);
            Text3 = (TextView) itemView.findViewById(R.id.new_life_result_text);
            progressView = (CircleProgressView) itemView.findViewById(R.id.newLife_progress);
            fenImage = (ImageView) itemView.findViewById(R.id.fenshuImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    newOnitemClick.onItemClick(getLayoutPosition());
                }
            });
        }
    }


    public interface NewOnitemClick {

        void onItemClick(int position);

    }
}
