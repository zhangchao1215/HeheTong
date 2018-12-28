package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.BigCastAllDescBean;

import java.util.List;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/1/1 16:17
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */

public class BigCastLookAllAdapterTwo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_TEXT = 0;
    private static final int TYPE_DOCTOR = 1;
    private Context context;
    private List<BigCastAllDescBean> dataBeanList;
    private BigcastOnitemClick onitemClick;

    public void setOnitemClick(BigcastOnitemClick onitemClick) {
        this.onitemClick = onitemClick;
    }

    public BigCastLookAllAdapterTwo(Context context, List<BigCastAllDescBean> dataBeanList) {
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {

        switch (type) {
            case TYPE_DOCTOR:

                View view = LayoutInflater.from(context)
                        .inflate(R.layout.activity_bigcast_people_item, null);
                BigcastHodlerTwo hodlerTwo = new BigcastHodlerTwo(view);

                return hodlerTwo;

            case TYPE_TEXT:

                View view1 = LayoutInflater.from(context)
                        .inflate(R.layout.activity_all_bigcast_item, null);
                BigcastHodlerOne hodlerOne = new BigcastHodlerOne(view1);

                return hodlerOne;

        }


        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof BigcastHodlerOne) {

            ((BigcastHodlerOne) viewHolder).mText.setText(dataBeanList.get(i).getBig_name());

        } else if (viewHolder instanceof BigcastHodlerTwo) {

            ((BigcastHodlerTwo) viewHolder).textName.setText(dataBeanList.get(i).getBig_name());

            ((BigcastHodlerTwo) viewHolder).textDesc.setText(dataBeanList.get(i).getBig_desc());

            Glide.with(context)
                    .load(dataBeanList.get(i).getBig_pic())
                    .asBitmap()
                    .placeholder(R.drawable.jcwz)
                    .into(new BitmapImageViewTarget(((BigcastHodlerTwo) viewHolder).image) {
                        @Override
                        protected void setResource(Bitmap resource) {
                            super.setResource(resource);
                            RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);

                            drawable.setCircular(true);

                            ((BigcastHodlerTwo) viewHolder).image.setImageDrawable(drawable);


                        }
                    });


        }
    }


    @Override
    public int getItemViewType(int position) {
        if (dataBeanList.get(position).getType() == TYPE_TEXT) {
            return TYPE_TEXT;
        }
        return TYPE_DOCTOR;
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    class BigcastHodlerOne extends RecyclerView.ViewHolder {
        private TextView mText;

        public BigcastHodlerOne(View itemView) {
            super(itemView);
            mText = (TextView) itemView.findViewById(R.id.bigcast_yangsheng);
        }
    }

    class BigcastHodlerTwo extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textName, textDesc;

        public BigcastHodlerTwo(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.Bigcast_peopleImage);
            textName = (TextView) itemView.findViewById(R.id.Bigcast_peopleName);
            textDesc = (TextView) itemView.findViewById(R.id.Bigcast_peopleContent);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemClick.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    public interface BigcastOnitemClick {
        void onItemClick(int positon);
    }
}
