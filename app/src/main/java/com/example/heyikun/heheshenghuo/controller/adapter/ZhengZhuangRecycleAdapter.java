package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.TypeIdBean;

import java.util.List;

/**
 * Created by hyk on 2017/9/17.
 */

public class ZhengZhuangRecycleAdapter extends RecyclerView.Adapter<ZhengZhuangRecycleAdapter.MViewHodler> {
    private List<TypeIdBean.DataBean> mList;
    private Context context;
    public OnmItemClick OnmItemClick;
    public int mSelectedPos = -1;
    private LayoutInflater layoutInflater;

    public void setOnItemClick(OnmItemClick onItemClick) {
        OnmItemClick = onItemClick;
    }

    public ZhengZhuangRecycleAdapter(List<TypeIdBean.DataBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {


        MViewHodler hodler = new MViewHodler(layoutInflater.inflate(R.layout.activity_checkbox, null));


        return hodler;
    }

    @Override
    public void onBindViewHolder(MViewHodler holder, int position) {

    }

    @Override
    public void onBindViewHolder(final MViewHodler holder, final int position, List list) {

        if (list.isEmpty()) {


            TypeIdBean.DataBean dataBean = mList.get(position);

            holder.mText.setText(dataBean.getDiag_name());

            //            holder.checkBox.setChecked(mSelectedPos == position);

        } else {
            //            holder.checkBox.setChecked(mSelectedPos == position);
        }
        mSelectedPos = position;

        holder.mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                holder.mText.setBackgroundResource(R.drawable.tv_item_selector);

                if (position == mSelectedPos) {
                    holder.mText.setBackgroundResource(R.drawable.user_radiobutton_false);
                } else {
                    holder.mText.setBackgroundResource(R.drawable.user_radiobutton_true);

                }


                OnmItemClick.OnItemCickClistenr(v, position);


                holder.mText.setTag(position);

//                mSelectedPos = position;
            }

        });


        //在这里面做的是点击选择RadioButton事件
        //        if (position == mySelectedItem) {
        //            holder.RB.setChecked(true);
        //
        //        } else {
        //            holder.RB.setChecked(false);
        //        }


    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MViewHodler extends RecyclerView.ViewHolder {
        public TextView mText;

        public MViewHodler(View itemView) {
            super(itemView);

            mText = (TextView) itemView.findViewById(R.id.Item_TextView);

            mText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }


        //提供给外部Activity来获取当前checkBox选中的item，这样就不用去遍历了 重点！
    }

    public interface OnmItemClick {
        void OnItemCickClistenr(View v, int Position);
    }
}