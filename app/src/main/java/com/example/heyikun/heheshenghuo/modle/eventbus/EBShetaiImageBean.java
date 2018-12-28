package com.example.heyikun.heheshenghuo.modle.eventbus;

import com.example.heyikun.heheshenghuo.modle.bean.PopuoBean;

import java.util.List;

/**
 * Created by hyk on 2017/10/30.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/30
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class EBShetaiImageBean {

    private List<PopuoBean.DataBean> dataBeen;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<PopuoBean.DataBean> getDataBeen() {
        return dataBeen;
    }

    public void setDataBeen(List<PopuoBean.DataBean> dataBeen) {
        this.dataBeen = dataBeen;
    }
}
