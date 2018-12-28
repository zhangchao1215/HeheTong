package com.example.heyikun.heheshenghuo.modle.eventbus;

import com.example.heyikun.heheshenghuo.modle.bean.MasterDetailBean;

import java.util.List;

/**
 * Created by hyk on 2017/12/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class MasterEvBean {

    private List<MasterDetailBean.DataBean.BigcastsBean> bigcastsBeanList;
    private List<MasterDetailBean.DataBean.MasterBean> masterBeanList;
    private List<MasterDetailBean.DataBean.FansBean> fansBeanList;

    public List<MasterDetailBean.DataBean.BigcastsBean> getBigcastsBeanList() {
        return bigcastsBeanList;
    }

    public void setBigcastsBeanList(List<MasterDetailBean.DataBean.BigcastsBean> bigcastsBeanList) {
        this.bigcastsBeanList = bigcastsBeanList;
    }

    public List<MasterDetailBean.DataBean.MasterBean> getMasterBeanList() {
        return masterBeanList;
    }

    public void setMasterBeanList(List<MasterDetailBean.DataBean.MasterBean> masterBeanList) {
        this.masterBeanList = masterBeanList;
    }

    public List<MasterDetailBean.DataBean.FansBean> getFansBeanList() {
        return fansBeanList;
    }

    public void setFansBeanList(List<MasterDetailBean.DataBean.FansBean> fansBeanList) {
        this.fansBeanList = fansBeanList;
    }
}
