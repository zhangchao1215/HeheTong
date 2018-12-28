package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/11/24.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/24
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 新首页的养身实体类
 */

public class HeHeYangshenBean  {


    /**
     * status : 200
     * message : 获取养身自测结果
     * data : {"body":[{"app_sort":"1","vote_id":"47","vote_name":"亚健康值","vote_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=47","vote_man":"","vote_woman":"","vote_copy":"","vote_res":"","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=47"},{"app_sort":"2","vote_id":"68","vote_name":"五行体质","vote_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=68","vote_man":"","vote_woman":"","vote_copy":"","vote_res":"","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=68"},{"app_sort":"3","vote_id":"36","vote_name":"营养素值","vote_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=36","vote_man":"","vote_woman":"","vote_copy":"","vote_res":"","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=36"},{"app_sort":"4","vote_id":"69","vote_name":"男性性功能","vote_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=69","vote_man":"","vote_woman":"","vote_copy":"","vote_res":"","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=69"},{"app_sort":"5","vote_id":"64","vote_name":"疲劳指数","vote_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=64","vote_man":"","vote_woman":"","vote_copy":"","vote_res":"","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=64"},{"app_sort":"6","vote_id":"13","vote_name":"睡眠质量","vote_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=13","vote_man":"","vote_woman":"","vote_copy":"","vote_res":"","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=13"},{"app_sort":"7","vote_id":"54","vote_name":"中医体质判定","vote_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=54","vote_man":"","vote_woman":"","vote_copy":"","vote_res":"","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=54"}]}
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
        private List<BodyBean> body;

        public List<BodyBean> getBody() {
            return body;
        }

        public void setBody(List<BodyBean> body) {
            this.body = body;
        }

        public static class BodyBean {
            /**
             * app_sort : 1
             * vote_id : 47
             * vote_name : 亚健康值
             * vote_link : http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=47
             * vote_man :
             * vote_woman :
             * vote_copy :
             * vote_res :
             * tests_link : http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=47
             */

            private String app_sort;
            private String vote_id;
            private String vote_name;
            private String vote_link;
            private String vote_man;
            private String vote_woman;
            private String vote_copy;
            private String vote_res;
            private String tests_link;

            public String getApp_sort() {
                return app_sort;
            }

            public void setApp_sort(String app_sort) {
                this.app_sort = app_sort;
            }

            public String getVote_id() {
                return vote_id;
            }

            public void setVote_id(String vote_id) {
                this.vote_id = vote_id;
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

            public String getVote_man() {
                return vote_man;
            }

            public void setVote_man(String vote_man) {
                this.vote_man = vote_man;
            }

            public String getVote_woman() {
                return vote_woman;
            }

            public void setVote_woman(String vote_woman) {
                this.vote_woman = vote_woman;
            }

            public String getVote_copy() {
                return vote_copy;
            }

            public void setVote_copy(String vote_copy) {
                this.vote_copy = vote_copy;
            }

            public String getVote_res() {
                return vote_res;
            }

            public void setVote_res(String vote_res) {
                this.vote_res = vote_res;
            }

            public String getTests_link() {
                return tests_link;
            }

            public void setTests_link(String tests_link) {
                this.tests_link = tests_link;
            }
        }
    }
}
