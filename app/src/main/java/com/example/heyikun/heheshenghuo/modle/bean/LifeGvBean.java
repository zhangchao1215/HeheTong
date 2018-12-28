package com.example.heyikun.heheshenghuo.modle.bean;

import android.widget.TextView;

/**
 * Created by hyk on 2017/11/21.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/21
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 新首页grivdview 实体类
 */

public class LifeGvBean {

    private int image;
    private String text;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
