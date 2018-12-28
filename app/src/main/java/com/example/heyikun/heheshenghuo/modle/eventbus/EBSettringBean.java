package com.example.heyikun.heheshenghuo.modle.eventbus;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/10/22 11:12
 * 修改人:  张超
 * 修改内容:
 * 修改时间:  穿值的是个人信息中的Email 和 answer 密保
 */

public class EBSettringBean {

    private String answer;

    private String email;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
