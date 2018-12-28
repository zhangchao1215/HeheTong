package com.example.heyikun.heheshenghuo.modle.eventbus;

import com.example.heyikun.heheshenghuo.modle.bean.MainBean;

import java.util.List;

/**
 * Created by hyk on 2017/10/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/14
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class EBDateBean {

    private List<MainBean.DataBean.DayBean> LifeDayList;

    public List<MainBean.DataBean.DayBean> getLifeDayList() {
        return LifeDayList;
    }

    public void setLifeDayList(List<MainBean.DataBean.DayBean> lifeDayList) {
        LifeDayList = lifeDayList;
    }
}
