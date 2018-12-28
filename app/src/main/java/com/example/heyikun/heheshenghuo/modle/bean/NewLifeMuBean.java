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
 * 4:类功能：
 */

public class NewLifeMuBean  {


    /**
     * status : 200
     * message : 获取成功
     * five_test : [{"vote_id":"76","test_name":"肝机能","test_sore":"72","test_desc":"肝脏机能中度虚损","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=76","vote_link":"https://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=76"},{"vote_id":"91","test_name":"胆机能","test_sore":"81","test_desc":"胆脏机能轻度虚损","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=91","vote_link":"https://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=91"},{"vote_id":"79","test_name":"怒机能","test_sore":"0","test_desc":"点击测试","test_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=79","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=79"}]
     */

    private String status;
    private String message;
    private List<FiveTestBean> five_test;

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

    public List<FiveTestBean> getFive_test() {
        return five_test;
    }

    public void setFive_test(List<FiveTestBean> five_test) {
        this.five_test = five_test;
    }

    public static class FiveTestBean {
        /**
         * vote_id : 76
         * test_name : 肝机能
         * test_sore : 72
         * test_desc : 肝脏机能中度虚损
         * test_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=76
         * vote_link : https://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=76
         */

        private String vote_id;
        private String test_name;
        private String test_sore;
        private String test_desc;
        private String test_link;
        private String vote_link;

        public String getVote_id() {
            return vote_id;
        }

        public void setVote_id(String vote_id) {
            this.vote_id = vote_id;
        }

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
