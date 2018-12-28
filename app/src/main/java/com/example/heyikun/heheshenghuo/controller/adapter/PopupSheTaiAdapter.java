package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.controller.activity.SheTaiDetailActivity;
import com.example.heyikun.heheshenghuo.modle.bean.PopuoBean;
import com.example.heyikun.heheshenghuo.modle.eventbus.EBShetaiImageBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by hyk on 2017/9/15.
 */

public class PopupSheTaiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PopuoBean.DataBean> mList;
    private LayoutInflater mInflater;//布局解析器 用来解析布局生成View的
    private int mSelectedPos = -1;//保存当前选中的position 重点！
    private SheTaiItemClickListener clickListener;

    public void setClickListener(SheTaiItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public PopupSheTaiAdapter(Context context, List<PopuoBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;
        mInflater = LayoutInflater.from(context);

    }

    @Override
    public MViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        MViewHodler hodler = new MViewHodler(mInflater.inflate(R.layout.activity_shouye_shetaiitem, null));

        return hodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    //使用这三个重载的函数

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position, List payloads) {

        final MViewHodler mViewHodler = (MViewHodler) holder;


        final PopuoBean.DataBean dataBean = mList.get(position);

        mViewHodler.mText.setText(dataBean.getDiag_name());

        Glide.with(context)
                .load(dataBean.getImg())
                .centerCrop()
                .placeholder(R.drawable.jcwz)
                .into(mViewHodler.imageView);

        mViewHodler.checkBox.setChecked(mSelectedPos == position);

        mViewHodler.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mSelectedPos = position;
                notifyDataSetChanged();

                clickListener.OnItemClick(position);

            }
        });

    }


    //提供给外部Activity来获取当前checkBox选中的item，这样就不用去遍历了 重点！
    public int getSelectedPos() {
        return mSelectedPos;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MViewHodler extends RecyclerView.ViewHolder {

        public CheckBox checkBox;//单选框 重点！

        private TextView mText;

        private ImageView imageView;

        public MViewHodler(View itemView) {
            super(itemView);
            checkBox = (CheckBox) itemView.findViewById(R.id.SheTai_CheckBox);

            mText = (TextView) itemView.findViewById(R.id.SheTai_mText);

            imageView = (ImageView) itemView.findViewById(R.id.SheTai_Image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    EBShetaiImageBean imageBean = new EBShetaiImageBean();
                    imageBean.setDataBeen(mList);
                    imageBean.setPosition(getLayoutPosition());

                    EventBus.getDefault().postSticky(imageBean);

                    Intent intent = new Intent(context, SheTaiDetailActivity.class);
//                    context.startActivity(intent);


                }
            });

        }

    }


    public interface SheTaiItemClickListener {

        void OnItemClick(int Position);

    }


}
