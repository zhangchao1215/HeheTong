package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.bean.TakeAddressBean;

import java.util.List;

/**
 * Created by hyk on 2017/10/19.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/19
 * <p>
 * 3：类描述： 地址管理的适配器，自定义点击事件，进行删除操作
 * <p>
 * 4:类功能：
 */

public class RecyclerAddressAdapter extends RecyclerView.Adapter<RecyclerAddressAdapter.MyHodler> {

    private List<TakeAddressBean.DataBean> mList;

    private Context context;

    private LayoutInflater inflater;

    private AddressLisenter addressItemLisenter;


    public void setAddressItemLisenter(AddressLisenter addressItemLisenter) {
        this.addressItemLisenter = addressItemLisenter;
    }

    public AddressLisenter getAddressItemLisenter() {
        return addressItemLisenter;
    }

    public RecyclerAddressAdapter(List<TakeAddressBean.DataBean> mList, Context context) {
        this.mList = mList;
        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        MyHodler hodler = new MyHodler(inflater.inflate(R.layout.activity_user_address_item, null));

        return hodler;
    }

    @Override
    public void onBindViewHolder(MyHodler holder, final int position) {

        TakeAddressBean.DataBean dataBean = mList.get(position);

        holder.mTextAddres.setText(dataBean.getAddress());

        holder.mTextName.setText(dataBean.getConsignee());

        holder.mTextPhone.setText(dataBean.getMobile());

        if (dataBean.getState().equals("0")) {

            holder.mImage.setImageResource(R.drawable.weixuanzhong4x);
        } else if (dataBean.getState().equals("1")) {
            holder.mImage.setImageResource(R.drawable.zhifuxuanzhong_4man);
        }
        //删除用户地址
        holder.mTextDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressItemLisenter.DeleteAddress(v, position);

            }
        });

        //修改用户地址

        holder.mTextedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressItemLisenter.ChangeAddress(v, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyHodler extends RecyclerView.ViewHolder {

        private TextView mTextName, mTextPhone, mTextAddres, mTextDelete, mTextedit;
        private ImageView mImage;

        public MyHodler(View convertView) {
            super(convertView);
            mTextName = (TextView) convertView.findViewById(R.id.AddressGuanLi_Name);
            mTextPhone = (TextView) convertView.findViewById(R.id.AddressGuanLi_Phone);
            mTextAddres = (TextView) convertView.findViewById(R.id.AddressGuanli_Detail);
            mTextDelete = (TextView) convertView.findViewById(R.id.mText_deleteAddress);
            mTextedit = (TextView) convertView.findViewById(R.id.mText_editAddress);
            mImage = (ImageView) convertView.findViewById(R.id.mImage_Default_address);


        }
    }

    public interface AddressLisenter {

        void DeleteAddress(View v, int position);

        void ChangeAddress(View view, int position);
    }


}
