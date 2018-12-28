package com.example.heyikun.heheshenghuo.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.heyikun.heheshenghuo.R;
import com.example.heyikun.heheshenghuo.modle.dao.AddressBean;

import java.util.List;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/8 18:34
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<AddressBean> beanList;

    public AddressAdapter(Context context, List<AddressBean> beanList) {
        this.context = context;
        this.beanList = beanList;
    }

    @Override
    public int getCount() {
        return beanList.size();
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
        Mhodler h = null;
        if (convertView == null) {
            h = new Mhodler();
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_user_address_item, null);

            h.mTextName = (TextView) convertView.findViewById(R.id.AddressGuanLi_Name);
            h.mTextPhone = (TextView) convertView.findViewById(R.id.AddressGuanLi_Phone);
            h.mTextAddres = (TextView) convertView.findViewById(R.id.AddressGuanli_Detail);
            h.mTextDelete = (TextView) convertView.findViewById(R.id.mText_deleteAddress);
            h.mTextedit = (TextView) convertView.findViewById(R.id.mText_editAddress);
            h.mImage = (ImageView) convertView.findViewById(R.id.mImage_Address_default);

            convertView.setTag(h);

        } else
            h = (Mhodler) convertView.getTag();

        AddressBean bean = beanList.get(position);

        h.mTextName.setText(bean.getName() + "");

        h.mTextPhone.setText(bean.getPhoneNumber() + "");

        h.mTextAddres.setText(bean.getAddress() + "");



        h.mTextDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemDeleteListener.onDeleteClick(position);
            }
        });

        return convertView;
    }

    class Mhodler {
        private TextView mTextName, mTextAddres, mTextPhone, mTextedit, mTextDelete;
        private EditText mEdit;
        private ImageView mImage;

    }

    /**
     * 删除按钮的监听接口
     */
    public interface onItemDeleteListener {
        void onDeleteClick(int i);
    }

    private onItemDeleteListener mOnItemDeleteListener;

    public void setOnItemDeleteClickListener(onItemDeleteListener mOnItemDeleteListener) {
        this.mOnItemDeleteListener = mOnItemDeleteListener;
    }
}
