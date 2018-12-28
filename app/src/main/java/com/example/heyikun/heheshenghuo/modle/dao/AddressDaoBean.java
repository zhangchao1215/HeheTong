package com.example.heyikun.heheshenghuo.modle.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/8 11:04
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */
@Entity
public class AddressDaoBean {
    @Id(autoincrement = true)
    private Long id;

    private String address;

    private String name;

    private String phoneNumber;

    @Generated(hash = 860783652)
    public AddressDaoBean(Long id, String address, String name,
            String phoneNumber) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Generated(hash = 827135242)
    public AddressDaoBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
