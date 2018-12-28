package com.example.heyikun.heheshenghuo.modle.util;

import android.text.TextUtils;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/12/3 21:04
 * 修改人:  张超
 * 修改内容:
 * 修改时间:
 */

public class UserUtls {
    public static final String USER_SEX = "userSex";

    public static String getUserSex() {
        String sex = "";
        sex = AppUtils.get().getString(USER_SEX, "");

        return sex;
    }
}
