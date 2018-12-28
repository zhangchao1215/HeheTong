package com.example.heyikun.heheshenghuo.modle.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/8 21:31
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */

@Entity
public class AddressBean  {
  @Id(autoincrement = true)
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    @Generated(hash = 33454195)
    public AddressBean(Long id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    @Generated(hash = 30780671)
    public AddressBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
   
}
