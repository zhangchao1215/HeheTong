package com.example.heyikun.heheshenghuo.modle.bean.login;

/**
 * Created by hyk on 2017/9/21.
 */

public class LoginMessageBean {
    /**
     * status : 200
     * message : 登陆成功
     * data : {"user_id":"5947","sex":"2","token":"UsYxB6XKPxyO8U0+Z9eIchIHCuUz5HO7MszVZ+wYxz4UR4D/YnMi4xBik6cjrhRS","email":"","vip":1,"level":"1","headimg":"https://hehe.heyishenghuo.com/data/headimg/201712/1512415673106743367.jpg","user_name":"超哥","sur_pass":"1","alias":"我这人很懒，","user_money":"2124.00","frozen_money":"0.00","pay_points":"1406","profit":"0","kun":"0","coupon":"0","bigcast":"5","fans":"1","follow":"3","theme":"2","replay":"0","answer":"0","all":"169","await_receipt":"0","payback":"145","await_comment":"0","await_ship":"24","await_pay":"145"}
     */

    private String status;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * user_id : 5947
         * sex : 2
         * token : UsYxB6XKPxyO8U0+Z9eIchIHCuUz5HO7MszVZ+wYxz4UR4D/YnMi4xBik6cjrhRS
         * email :
         * vip : 1
         * level : 1
         * headimg : https://hehe.heyishenghuo.com/data/headimg/201712/1512415673106743367.jpg
         * user_name : 超哥
         * sur_pass : 1
         * alias : 我这人很懒，
         * user_money : 2124.00
         * frozen_money : 0.00
         * pay_points : 1406
         * profit : 0
         * kun : 0
         * coupon : 0
         * bigcast : 5
         * fans : 1
         * follow : 3
         * theme : 2
         * replay : 0
         * answer : 0
         * all : 169
         * await_receipt : 0
         * payback : 145
         * await_comment : 0
         * await_ship : 24
         * await_pay : 145
         */

        private String user_id;
        private String sex;
        private String token;
        private String email;
        private int vip;
        private String level;
        private String headimg;
        private String user_name;
        private String sur_pass;
        private String alias;
        private String user_money;
        private String frozen_money;
        private String pay_points;
        private String profit;
        private String kun;
        private String coupon;
        private String bigcast;
        private String fans;
        private String follow;
        private String theme;
        private String replay;
        private String answer;
        private String all;
        private String await_receipt;
        private String payback;
        private String await_comment;
        private String await_ship;
        private String await_pay;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getSur_pass() {
            return sur_pass;
        }

        public void setSur_pass(String sur_pass) {
            this.sur_pass = sur_pass;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public String getProfit() {
            return profit;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }

        public String getKun() {
            return kun;
        }

        public void setKun(String kun) {
            this.kun = kun;
        }

        public String getCoupon() {
            return coupon;
        }

        public void setCoupon(String coupon) {
            this.coupon = coupon;
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

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public String getAll() {
            return all;
        }

        public void setAll(String all) {
            this.all = all;
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
    }


    //    /**
//     * status : 200
//     * message : 登陆成功
//     * data : {"user_id":"5456","sex":"0","alias":"","all":"0","await_receipt":"0","payback":"0","await_comment":"0","await_ship":"0","await_pay":"0","user_money":"0.00","frozen_money":"0.00","pay_points":"0","profit":"0","kun":"0","bigcast":"3","fans":"1","follow":"2","theme":"3","replay":"0","token":"1ZnhPM7V62F6sxkcXN0/bNoeUrqDhiJ2uYTdaVsd7WEUR4D/YnMi4xBik6cjrhRS"}
//     */
//
//    private String status;
//    private String message;
//    private DataBean data;
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * user_id : 5456
//         * sex : 0
//         * alias :
//         * all : 0
//         * await_receipt : 0
//         * payback : 0
//         * await_comment : 0
//         * await_ship : 0
//         * await_pay : 0
//         * user_money : 0.00
//         * frozen_money : 0.00
//         * pay_points : 0
//         * profit : 0
//         * kun : 0
//         * bigcast : 3
//         * fans : 1
//         * follow : 2
//         * theme : 3
//         * replay : 0
//         * token : 1ZnhPM7V62F6sxkcXN0/bNoeUrqDhiJ2uYTdaVsd7WEUR4D/YnMi4xBik6cjrhRS
//         */
//
//        private String user_id;
//        private String sex;
//        private String alias;
//        private String all;
//        private String await_receipt;
//        private String payback;
//        private String await_comment;
//        private String await_ship;
//        private String await_pay;
//        private String user_money;
//        private String frozen_money;
//        private String pay_points;
//        private String profit;
//        private String kun;
//        private String bigcast;
//        private String fans;
//        private String follow;
//        private String theme;
//        private String replay;
//        private String token;
//
//        public String getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(String user_id) {
//            this.user_id = user_id;
//        }
//
//        public String getSex() {
//            return sex;
//        }
//
//        public void setSex(String sex) {
//            this.sex = sex;
//        }
//
//        public String getAlias() {
//            return alias;
//        }
//
//        public void setAlias(String alias) {
//            this.alias = alias;
//        }
//
//        public String getAll() {
//            return all;
//        }
//
//        public void setAll(String all) {
//            this.all = all;
//        }
//
//        public String getAwait_receipt() {
//            return await_receipt;
//        }
//
//        public void setAwait_receipt(String await_receipt) {
//            this.await_receipt = await_receipt;
//        }
//
//        public String getPayback() {
//            return payback;
//        }
//
//        public void setPayback(String payback) {
//            this.payback = payback;
//        }
//
//        public String getAwait_comment() {
//            return await_comment;
//        }
//
//        public void setAwait_comment(String await_comment) {
//            this.await_comment = await_comment;
//        }
//
//        public String getAwait_ship() {
//            return await_ship;
//        }
//
//        public void setAwait_ship(String await_ship) {
//            this.await_ship = await_ship;
//        }
//
//        public String getAwait_pay() {
//            return await_pay;
//        }
//
//        public void setAwait_pay(String await_pay) {
//            this.await_pay = await_pay;
//        }
//
//        public String getUser_money() {
//            return user_money;
//        }
//
//        public void setUser_money(String user_money) {
//            this.user_money = user_money;
//        }
//
//        public String getFrozen_money() {
//            return frozen_money;
//        }
//
//        public void setFrozen_money(String frozen_money) {
//            this.frozen_money = frozen_money;
//        }
//
//        public String getPay_points() {
//            return pay_points;
//        }
//
//        public void setPay_points(String pay_points) {
//            this.pay_points = pay_points;
//        }
//
//        public String getProfit() {
//            return profit;
//        }
//
//        public void setProfit(String profit) {
//            this.profit = profit;
//        }
//
//        public String getKun() {
//            return kun;
//        }
//
//        public void setKun(String kun) {
//            this.kun = kun;
//        }
//
//        public String getBigcast() {
//            return bigcast;
//        }
//
//        public void setBigcast(String bigcast) {
//            this.bigcast = bigcast;
//        }
//
//        public String getFans() {
//            return fans;
//        }
//
//        public void setFans(String fans) {
//            this.fans = fans;
//        }
//
//        public String getFollow() {
//            return follow;
//        }
//
//        public void setFollow(String follow) {
//            this.follow = follow;
//        }
//
//        public String getTheme() {
//            return theme;
//        }
//
//        public void setTheme(String theme) {
//            this.theme = theme;
//        }
//
//        public String getReplay() {
//            return replay;
//        }
//
//        public void setReplay(String replay) {
//            this.replay = replay;
//        }
//
//        public String getToken() {
//            return token;
//        }
//
//        public void setToken(String token) {
//            this.token = token;
//        }
//    }
}
