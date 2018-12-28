package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.ServeShangquanBean;

import java.util.List;

/**
 * Created by hyk on 2017/12/4.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/4
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 和合服务中的地位商圈适配器
 */

public class ServeShangquanAdaoter extends BaseRecyclerAdapter<ServeShangquanBean.DataBean.DistrictBean> {
    private OnItemLisenter onItemLisenter;
    private int mSelectedPos = -1;//保存当前选中的position 重点！

    public void setOnItemLisenter(OnItemLisenter onItemLisenter) {
        this.onItemLisenter = onItemLisenter;
    }

    public ServeShangquanAdaoter(List<ServeShangquanBean.DataBean.DistrictBean> mList, Context context) {
        super(mList, context, R.layout.activity_serve_shangquan_item);
    }

    @Override
    protected void convert(ViewHolder holder, ServeShangquanBean.DataBean.DistrictBean districtBean, final int Position) {

        CheckBox checkBox = holder.getView(R.id.serve_shangquan_itembut);

        checkBox.setText(districtBean.getDistrict_name());

        checkBox.setChecked(mSelectedPos == Position);


        holder.setOnclickListener(R.id.serve_shangquan_itembut, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedPos = Position;
                notifyDataSetChanged();

                onItemLisenter.itemListenter(Position);

            }
        });

    }

    public interface OnItemLisenter {
        void itemListenter(int position);
    }
}
