package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2018/1/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/14
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 和合首页新模块实体类
 */

public class HeHeNewLifeBean {

    /**
     * status : 200
     * message : 获取成功
     * data : {"five":[{"app_sort":"1","vote_name":"肝","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=76","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=76"},{"app_sort":"2","vote_name":"胆","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=91","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=91"},{"app_sort":"3","vote_name":"怒","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=79","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=79"}],"fiveline":{"vote_name":"木行体质","vote_link":"https://hehe.heyishenghuo.com/mobile2/testquestions/fiveline.php?vote_id=68","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=68"},"nine":{"vote_name":"湿热质","vote_link":"https://hehe.heyishenghuo.com/mobile2/constitution.php?vote_id=54","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=54"},"fire":[{"test_name":"心机能","test_sore":"0","test_desc":"点击测试","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=77","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=77"},{"test_name":"小肠机能","test_sore":"0","test_desc":"点击测试","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=83","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=83"},{"test_name":"喜机能","test_sore":"0","test_desc":"点击测试","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=78","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=78"}]}
     * five_result : mu
     */

    private String status;
    private String message;
    private DataBean data;
    private String five_result;

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

    public String getFive_result() {
        return five_result;
    }

    public void setFive_result(String five_result) {
        this.five_result = five_result;
    }

    public static class DataBean {
        /**
         * five : [{"app_sort":"1","vote_name":"肝","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=76","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=76"},{"app_sort":"2","vote_name":"胆","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=91","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=91"},{"app_sort":"3","vote_name":"怒","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=79","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=79"}]
         * fiveline : {"vote_name":"木行体质","vote_link":"https://hehe.heyishenghuo.com/mobile2/testquestions/fiveline.php?vote_id=68","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=68"}
         * nine : {"vote_name":"湿热质","vote_link":"https://hehe.heyishenghuo.com/mobile2/constitution.php?vote_id=54","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=54"}
         * fire : [{"test_name":"心机能","test_sore":"0","test_desc":"点击测试","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=77","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=77"},{"test_name":"小肠机能","test_sore":"0","test_desc":"点击测试","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=83","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=83"},{"test_name":"喜机能","test_sore":"0","test_desc":"点击测试","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=78","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=78"}]
         */

        private FivelineBean fiveline;
        private NineBean nine;
        private List<FiveBean> five;
        private List<FireBean> fire;

        public FivelineBean getFiveline() {
            return fiveline;
        }

        public void setFiveline(FivelineBean fiveline) {
            this.fiveline = fiveline;
        }

        public NineBean getNine() {
            return nine;
        }

        public void setNine(NineBean nine) {
            this.nine = nine;
        }

        public List<FiveBean> getFive() {
            return five;
        }

        public void setFive(List<FiveBean> five) {
            this.five = five;
        }

        public List<FireBean> getFire() {
            return fire;
        }

        public void setFire(List<FireBean> fire) {
            this.fire = fire;
        }

        public static class FivelineBean {
            /**
             * vote_name : 木行体质
             * vote_link : https://hehe.heyishenghuo.com/mobile2/testquestions/fiveline.php?vote_id=68
             * test_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=68
             */

            private String vote_name;
            private String vote_link;
            private String test_link;

            public String getVote_name() {
                return vote_name;
            }

            public void setVote_name(String vote_name) {
                this.vote_name = vote_name;
            }

            public String getVote_link() {
                return vote_link;
            }

            public void setVote_link(String vote_link) {
                this.vote_link = vote_link;
            }

            public String getTest_link() {
                return test_link;
            }

            public void setTest_link(String test_link) {
                this.test_link = test_link;
            }
        }

        public static class NineBean {
            /**
             * vote_name : 湿热质
             * vote_link : https://hehe.heyishenghuo.com/mobile2/constitution.php?vote_id=54
             * test_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=54
             */

            private String vote_name;
            private String vote_link;
            private String test_link;

            public String getVote_name() {
                return vote_name;
            }

            public void setVote_name(String vote_name) {
                this.vote_name = vote_name;
            }

            public String getVote_link() {
                return vote_link;
            }

            public void setVote_link(String vote_link) {
                this.vote_link = vote_link;
            }

            public String getTest_link() {
                return test_link;
            }

            public void setTest_link(String test_link) {
                this.test_link = test_link;
            }
        }

        public static class FiveBean {
            /**
             * app_sort : 1
             * vote_name : 肝
             * vote_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=76
             * test_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=76
             */

            private String app_sort;
            private String vote_name;
            private String vote_link;
            private String test_link;

            public String getApp_sort() {
                return app_sort;
            }

            public void setApp_sort(String app_sort) {
                this.app_sort = app_sort;
            }

            public String getVote_name() {
                return vote_name;
            }

            public void setVote_name(String vote_name) {
                this.vote_name = vote_name;
            }

            public String getVote_link() {
                return vote_link;
            }

            public void setVote_link(String vote_link) {
                this.vote_link = vote_link;
            }

            public String getTest_link() {
                return test_link;
            }

            public void setTest_link(String test_link) {
                this.test_link = test_link;
            }
        }

        public static class FireBean {
            /**
             * test_name : 心机能
             * test_sore : 0
             * test_desc : 点击测试
             * test_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=77
             * vote_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=77
             */

            private String test_name;
            private String test_sore;
            private String test_desc;
            private String test_link;
            private String vote_link;

            public String getTest_name() {
                return test_name;
            }

            public void setTest_name(String test_name) {
                this.test_name = test_name;
            }

            public String getTest_sore() {
                return test_sore;
            }

            public void setTest_sore(String test_sore) {
                this.test_sore = test_sore;
            }

            public String getTest_desc() {
                return test_desc;
            }

            public void setTest_desc(String test_desc) {
                this.test_desc = test_desc;
            }

            public String getTest_link() {
                return test_link;
            }

            public void setTest_link(String test_link) {
                this.test_link = test_link;
            }

            public String getVote_link() {
                return vote_link;
            }

            public void setVote_link(String vote_link) {
                this.vote_link = vote_link;
            }
        }
    }
}
