package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.Second_RecyclerBean;

import java.util.List;

/**
 * Created by hyk on 2017/9/17.
 */

public class Second_RecyclerAdapter extends RecyclerView.Adapter<Second_RecyclerAdapter.MHodler> {
    private List<Second_RecyclerBean.DataBean> mList;
    private Context content;
    private SecondOnItemClick onItemClick;

    public void setOnItemClick(SecondOnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public Second_RecyclerAdapter(List<Second_RecyclerBean.DataBean> mList, Context content) {
        this.mList = mList;
        this.content = content;
    }

    @Override
    public MHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(content).inflate(R.layout.activity_recycleritem_radiobutton, null);
        MHodler hodler = new MHodler(v);

        return hodler;
    }

    @Override
    public void onBindViewHolder(MHodler holder, int position) {
        Second_RecyclerBean.DataBean bean = mList.get(position);

        if (bean.getDiag_name().isEmpty()) {
            return;
        }
        holder.radio.setText(bean.getDiag_name());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class MHodler extends RecyclerView.ViewHolder {
//        private RadioButton radio;

        private TextView radio;
        public MHodler(View itemView) {
            super(itemView);

            radio = (TextView) itemView.findViewById(R.id.Recuycler_ItemRB);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.OnitemClick(v, getLayoutPosition());
                }
            });
        }
    }

    public interface SecondOnItemClick {

        void OnitemClick(View v, int position);

    }
}
