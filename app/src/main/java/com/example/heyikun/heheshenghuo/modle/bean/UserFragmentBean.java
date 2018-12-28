package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/10/16.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/16
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class UserFragmentBean  {

    /**
     * status : 200
     * message : 请求成功
     * data : {"sex":"1","vip":0,"email":"","headimg":"http://hehe.heyishenghuo.com/data/headimg/201712/1512415673106743367.jpg","user_name":"超哥","alias":"我这人很懒，","sur_pass":"0","user_money":"0.00","frozen_money":"0.00","pay_points":"674","profit":"0","kun":"0","coupon":"0","bigcast":"6","fans":"0","follow":"0","theme":"0","replay":"0","answer":"0","all":"83","await_receipt":"0","payback":"82","await_comment":"0","await_ship":"1","await_pay":"82","vip_ad":{"vip_title":"和合全域健康计划","vip_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512443365822333212.png","vip_link":"http://hehe.heyishenghuo.com/mobile2/guide/health-testing.php?fangzhao=1"},"test_que":[{"body_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/bodyvip.php?fangzhao=1","is_test":1,"datarr":[{"test_name":"睡眠质量测评","test_res":58},{"test_name":"女性卵巢早衰测试","test_res":42},{"test_name":"营养素缺乏状况综合测评","test_res":1340}]},{"body_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/heartvip.php?fangzhao=1","is_test":1,"datarr":[{"test_name":"焦虑指数测评","test_res":47},{"test_name":"职场心理压力测试","test_res":14},{"test_name":"抑郁症测评","test_res":57}]},{"body_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/moneyvip.php?fangzhao=1","is_test":1,"datarr":[{"test_name":"赚钱指数测评","test_res":10},{"test_name":"财富运用能力测评","test_res":20},{"test_name":"风险承受能力测评","test_res":57}]}]}
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
         * sex : 1
         * vip : 0
         * email :
         * headimg : http://hehe.heyishenghuo.com/data/headimg/201712/1512415673106743367.jpg
         * user_name : 超哥
         * alias : 我这人很懒，
         * sur_pass : 0
         * user_money : 0.00
         * frozen_money : 0.00
         * pay_points : 674
         * profit : 0
         * kun : 0
         * coupon : 0
         * bigcast : 6
         * fans : 0
         * follow : 0
         * theme : 0
         * replay : 0
         * answer : 0
         * all : 83
         * await_receipt : 0
         * payback : 82
         * await_comment : 0
         * await_ship : 1
         * await_pay : 82
         * vip_ad : {"vip_title":"和合全域健康计划","vip_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512443365822333212.png","vip_link":"http://hehe.heyishenghuo.com/mobile2/guide/health-testing.php?fangzhao=1"}
         * test_que : [{"body_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/bodyvip.php?fangzhao=1","is_test":1,"datarr":[{"test_name":"睡眠质量测评","test_res":58},{"test_name":"女性卵巢早衰测试","test_res":42},{"test_name":"营养素缺乏状况综合测评","test_res":1340}]},{"body_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/heartvip.php?fangzhao=1","is_test":1,"datarr":[{"test_name":"焦虑指数测评","test_res":47},{"test_name":"职场心理压力测试","test_res":14},{"test_name":"抑郁症测评","test_res":57}]},{"body_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/moneyvip.php?fangzhao=1","is_test":1,"datarr":[{"test_name":"赚钱指数测评","test_res":10},{"test_name":"财富运用能力测评","test_res":20},{"test_name":"风险承受能力测评","test_res":57}]}]
         */

        private String sex;
        private int vip;
        private String email;
        private String headimg;
        private String user_name;
        private String alias;
        private String sur_pass;
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
        private VipAdBean vip_ad;
        private List<TestQueBean> test_que;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public int getVip() {
            return vip;
        }

        public void setVip(int vip) {
            this.vip = vip;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getSur_pass() {
            return sur_pass;
        }

        public void setSur_pass(String sur_pass) {
            this.sur_pass = sur_pass;
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

        public VipAdBean getVip_ad() {
            return vip_ad;
        }

        public void setVip_ad(VipAdBean vip_ad) {
            this.vip_ad = vip_ad;
        }

        public List<TestQueBean> getTest_que() {
            return test_que;
        }

        public void setTest_que(List<TestQueBean> test_que) {
            this.test_que = test_que;
        }

        public static class VipAdBean {
            /**
             * vip_title : 和合全域健康计划
             * vip_pic : http://hehe.heyishenghuo.com/data/afficheimg/1512443365822333212.png
             * vip_link : http://hehe.heyishenghuo.com/mobile2/guide/health-testing.php?fangzhao=1
             */

            private String vip_title;
            private String vip_pic;
            private String vip_link;

            public String getVip_title() {
                return vip_title;
            }

            public void setVip_title(String vip_title) {
                this.vip_title = vip_title;
            }

            public String getVip_pic() {
                return vip_pic;
            }

            public void setVip_pic(String vip_pic) {
                this.vip_pic = vip_pic;
            }

            public String getVip_link() {
                return vip_link;
            }

            public void setVip_link(String vip_link) {
                this.vip_link = vip_link;
            }
        }

        public static class TestQueBean {
            /**
             * body_link : http://hehe.heyishenghuo.com/mobile2/testquestions/bodyvip.php?fangzhao=1
             * is_test : 1
             * datarr : [{"test_name":"睡眠质量测评","test_res":58},{"test_name":"女性卵巢早衰测试","test_res":42},{"test_name":"营养素缺乏状况综合测评","test_res":1340}]
             */

            private String body_link;
            private int is_test;
            private List<DatarrBean> datarr;

            public String getBody_link() {
                return body_link;
            }

            public void setBody_link(String body_link) {
                this.body_link = body_link;
            }

            public int getIs_test() {
                return is_test;
            }

            public void setIs_test(int is_test) {
                this.is_test = is_test;
            }

            public List<DatarrBean> getDatarr() {
                return datarr;
            }

            public void setDatarr(List<DatarrBean> datarr) {
                this.datarr = datarr;
            }

            public static class DatarrBean {
                /**
                 * test_name : 睡眠质量测评
                 * test_res : 58
                 */

                private String test_name;
                private int test_res;

                public String getTest_name() {
                    return test_name;
                }

                public void setTest_name(String test_name) {
                    this.test_name = test_name;
                }

                public int getTest_res() {
                    return test_res;
                }

                public void setTest_res(int test_res) {
                    this.test_res = test_res;
                }
            }
        }
    }
}
