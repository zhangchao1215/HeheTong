package com.example.heyikun.heheshenghuo.modle.eventbus;

/**
 * Created by hyk on 2017/10/12.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/12
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class EBLoginBeanWomen {
    //一句话简介
    private String brief;

    //待收货
    private String await_receipt;
    //退款订单
    private String payback;
    //待评价
    private String await_comment;
    //待发货
    private String await_ship;
    //待付款
    private String await_pay;
    //账户余额
    private String userMoney;


    private String HeTicket;
    private String YiTicket;
    private String KunTicket;

    //收益
    private String profit;
    //大咖数量
    private String bigcast;
    //关注我的数量
    private String fans;
    //我关注的数量
    private String follow;

   //设置主题
    private String theme;

    //新回复
    private String replay;

    private String coupon;

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public EBLoginBeanWomen() {

    }

    public EBLoginBeanWomen(String brief, String await_receipt, String payback, String await_comment, String await_ship, String await_pay, String userMoney, String heTicket, String yiTicket, String kunTicket, String profit, String bigcast, String fans, String follow, String theme, String replay) {
        this.brief = brief;
        this.await_receipt = await_receipt;
        this.payback = payback;
        this.await_comment = await_comment;
        this.await_ship = await_ship;
        this.await_pay = await_pay;
        this.userMoney = userMoney;
        this.HeTicket = heTicket;
        this.YiTicket = yiTicket;
        this.KunTicket = kunTicket;
        this.profit = profit;
        this.bigcast = bigcast;
        this.fans = fans;
        this.follow = follow;
        this.theme = theme;
        this.replay = replay;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAwait_receipt() {
        return await_receipt;
    }

    public void setAwait_receipt(String await_receipt) {
        this.await_receipt = await_receipt;
    }

    public String getPayback() {
        return payback;
    }

    public void setPayback(String payback) {
        this.payback = payback;
    }

    public String getAwait_comment() {
        return await_comment;
    }

    public void setAwait_comment(String await_comment) {
        this.await_comment = await_comment;
    }

    public String getAwait_ship() {
        return await_ship;
    }

    public void setAwait_ship(String await_ship) {
        this.await_ship = await_ship;
    }

    public String getAwait_pay() {
        return await_pay;
    }

    public void setAwait_pay(String await_pay) {
        this.await_pay = await_pay;
    }

    public String getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
    }

    public String getHeTicket() {
        return HeTicket;
    }

    public void setHeTicket(String heTicket) {
        HeTicket = heTicket;
    }

    public String getYiTicket() {
        return YiTicket;
    }

    public void setYiTicket(String yiTicket) {
        YiTicket = yiTicket;
    }

    public String getKunTicket() {
        return KunTicket;
    }

    public void setKunTicket(String kunTicket) {
        KunTicket = kunTicket;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public String getBigcast() {
        return bigcast;
    }

    public void setBigcast(String bigcast) {
        this.bigcast = bigcast;
    }

    public String getFans() {
        return fans;
    }

    public void setFans(String fans) {
        this.fans = fans;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }
}
