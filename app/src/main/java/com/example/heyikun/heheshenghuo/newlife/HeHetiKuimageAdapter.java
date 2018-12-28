package com.example.heyikun.heheshenghuo.newlife;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.adapter.BaseRecyclerAdapter;
import com.example.heyikun.heheshenghuo.modle.bean.TikuImageBean;

import java.util.List;

/**
 * Created by hyk on 2018/1/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class HeHetiKuimageAdapter extends BaseRecyclerAdapter<TikuImageBean.DataBean.TwoClassBean> {
    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public HeHetiKuimageAdapter(List<TikuImageBean.DataBean.TwoClassBean> mList, Context context) {
        super(mList, context, R.layout.activity_shop_haohuo_item);
    }

    @Override
    protected void convert(ViewHolder holder, TikuImageBean.DataBean.TwoClassBean imageBean, final int Position) {

        ImageView imageView = holder.getView(R.id.Shop_Image_haohuo);

        holder.setText(R.id.Shop_Text_Haohuo, imageBean.getClass_name());


        Glide.with(context)
                .load(imageBean.getClass_pic())
                .placeholder(R.drawable.jcwz)
                .into(imageView);


        //        holder.getText();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemclick(Position);
            }
        });

    }

    public interface OnItemClick {

        void onItemclick(int position);

    }
}
