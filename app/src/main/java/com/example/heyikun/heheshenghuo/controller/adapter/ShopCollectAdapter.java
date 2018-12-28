package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.ShopCollectBean;

import java.util.List;

/**
 * Created by hyk on 2017/11/1.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/1
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class ShopCollectAdapter extends BaseAdapter {
    private List<ShopCollectBean.DataBean.GoodsBean> mList;
    private Context context;

    private ClickLisenter clickLisenter;

    public void setClickLisenter(ClickLisenter clickLisenter) {
        this.clickLisenter = clickLisenter;
    }

    public ShopCollectAdapter(List<ShopCollectBean.DataBean.GoodsBean> mList, Context context) {
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MViewHodler hodler = null;


        hodler = new MViewHodler();

        convertView = LayoutInflater.from(context).inflate(R.layout.activity_user_collect_shop, null);

        hodler.mTextTitle = (TextView) convertView.findViewById(R.id.Collect_TextShop_Name);

        hodler.mImage = (ImageView) convertView.findViewById(R.id.Collect_Image);

        hodler.mTextPrice = (TextView) convertView.findViewById(R.id.Collect_TextPrice);

        hodler.mTextOldPrice = (TextView) convertView.findViewById(R.id.Collect_oldPrice);

        hodler.mTextDelete = (TextView) convertView.findViewById(R.id.mText_cancel);
        hodler.checkBox = (CheckBox) convertView.findViewById(R.id.mImage_Duigou);


        ShopCollectBean.DataBean.GoodsBean goodsBean = mList.get(position);

        Glide.with(context)
                .load(goodsBean.getGoods_pic())
                .centerCrop()
                .placeholder(R.drawable.jcwz)
                .into(hodler.mImage);

        hodler.mTextOldPrice.setText(goodsBean.getOld_price());

        hodler.mTextPrice.setText(goodsBean.getGoods_price());

        hodler.mTextTitle.setText(goodsBean.getGoods_name());


        final View finalConvertView = convertView;
        hodler.mTextDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                mSll_main.setStatus(SwipeListLayout.Status.Close, true);
                //
                //                mList.remove(position);
                //
                //                notifyDataSetChanged();

                clickLisenter.deleteLisenter(finalConvertView, position);

            }
        });

        clickLisenter.CheckView(convertView);


        hodler.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLisenter.CheckBoxLisenter(v, position);

            }
        });


        return convertView;
    }

    class MViewHodler {

        private TextView mTextDelete, mTextTitle, mTextPrice, mTextOldPrice;
        private ImageView mImage;
        private CheckBox checkBox;


    }

    public interface ClickLisenter {
        void deleteLisenter(View view, int position);

        void CheckBoxLisenter(View view, int position);

        void CheckView(View view);

    }

}
