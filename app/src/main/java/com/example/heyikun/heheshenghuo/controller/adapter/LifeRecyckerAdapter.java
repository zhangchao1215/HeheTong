package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.HeHeYangshenBean;
import com.example.heyikun.heheshenghuo.modle.bean.LifeGvBean;
import com.example.heyikun.heheshenghuo.modle.util.WebViewUtils;

import java.util.List;

import butterknife.OnClick;

/**
 * Created by hyk on 2017/11/21.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/21
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class LifeRecyckerAdapter extends BaseRecyclerAdapter<HeHeYangshenBean.DataBean.BodyBean> {
    private LifeOnclick onclick;

    public void setOnclick(LifeOnclick onclick) {
        this.onclick = onclick;
    }

    public LifeRecyckerAdapter(List<HeHeYangshenBean.DataBean.BodyBean> mList, Context context) {
        super(mList, context, R.layout.activity_gridview_item);
    }

    @Override
    protected void convert(ViewHolder holder, final HeHeYangshenBean.DataBean.BodyBean bodyBean, final int Position) {

        ImageView image = holder.getView(R.id.life_new_image);

        Glide.with(context)
                .load(bodyBean.getVote_man())
                .placeholder(R.drawable.jcwz)
                .into(image);
        holder.setText(R.id.life_new_text, bodyBean.getVote_name());

        holder.setOnclickListener(R.id.Linear, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.lifeOnItemClick(Position);

            }
        });


    }

    public interface LifeOnclick {

        void lifeOnItemClick(int position);

    }
}
