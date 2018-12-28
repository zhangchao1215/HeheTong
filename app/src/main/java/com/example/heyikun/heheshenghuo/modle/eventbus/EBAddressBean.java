package com.example.heyikun.heheshenghuo.modle.eventbus;

import com.example.heyikun.heheshenghuo.modle.bean.TakeAddressBean;

import java.util.List;

/**
 * Created by hyk on 2017/10/19.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/19
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 添加收货地址的实体类
 */

public class EBAddressBean {


    private List<TakeAddressBean.DataBean> mList;

    public List<TakeAddressBean.DataBean> getmList() {
        return mList;
    }

    public void setmList(List<TakeAddressBean.DataBean> mList) {
        this.mList = mList;
    }
}
