package com.example.heyikun.heheshenghuo.modle.eventbus;

/**
 * Created by hyk on 2017/11/9.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/9
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 把个人中心的用户名和头像传递过去
 */

public class EventLifeBean {


    private String name;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
