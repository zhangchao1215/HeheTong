package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2018/1/15.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/15
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class TikuImageBean {

    /**
     * result : 200
     * message : 获取成功
     * data : {"label":[{"label_pic":"https://hehe.heyishenghuo.com/","vote_id":"109","vote_name":"进取精神","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=109"},{"label_pic":"https://hehe.heyishenghuo.com/","vote_id":"114","vote_name":"测试","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=114"},{"label_pic":"https://hehe.heyishenghuo.com/","vote_id":"97","vote_name":"职业倦怠指数","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=97"},{"label_pic":"https://hehe.heyishenghuo.com/","vote_id":"108","vote_name":"躁郁来源","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=108"},{"label_pic":"https://hehe.heyishenghuo.com/","vote_id":"101","vote_name":"判断力指数","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=101"},{"label_pic":"https://hehe.heyishenghuo.com/","vote_id":"110","vote_name":"忧郁来源","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=110"}],"two_class":[{"class_id":"14","class_name":"疾病测试","class_pic":"https://hehe.heyishenghuo.com/data/vote_classify_cat/1516061959600996082.png"},{"class_id":"15","class_name":"健康测试","class_pic":"https://hehe.heyishenghuo.com/data/vote_classify_cat/1516061967623995244.png"}],"tests":[{"vote_id":"114","vote_name":"测试测试","vote_pic":"https://hehe.heyishenghuo.com/data/question/1516070670972559227.jpg","vote_link":"https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=114","total_peo":null,"right_num":0,"is_state":0}]}
     */

    private String result;
    private String message;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
        private List<LabelBean> label;
        private List<TwoClassBean> two_class;
        private List<TestsBean> tests;

        public List<LabelBean> getLabel() {
            return label;
        }

        public void setLabel(List<LabelBean> label) {
            this.label = label;
        }

        public List<TwoClassBean> getTwo_class() {
            return two_class;
        }

        public void setTwo_class(List<TwoClassBean> two_class) {
            this.two_class = two_class;
        }

        public List<TestsBean> getTests() {
            return tests;
        }

        public void setTests(List<TestsBean> tests) {
            this.tests = tests;
        }

        public static class LabelBean {
            /**
             * label_pic : https://hehe.heyishenghuo.com/
             * vote_id : 109
             * vote_name : 进取精神
             * vote_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=109
             */

            private String label_pic;
            private String vote_id;
            private String vote_name;
            private String vote_link;

            public String getLabel_pic() {
                return label_pic;
            }

            public void setLabel_pic(String label_pic) {
                this.label_pic = label_pic;
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
        }

        public static class TwoClassBean {
            /**
             * class_id : 14
             * class_name : 疾病测试
             * class_pic : https://hehe.heyishenghuo.com/data/vote_classify_cat/1516061959600996082.png
             */

            private String class_id;
            private String class_name;
            private String class_pic;

            public String getClass_id() {
                return class_id;
            }

            public void setClass_id(String class_id) {
                this.class_id = class_id;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getClass_pic() {
                return class_pic;
            }

            public void setClass_pic(String class_pic) {
                this.class_pic = class_pic;
            }
        }

        public static class TestsBean {
            /**
             * vote_id : 114
             * vote_name : 测试测试
             * vote_pic : https://hehe.heyishenghuo.com/data/question/1516070670972559227.jpg
             * vote_link : https://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=114
             * total_peo : null
             * right_num : 0
             * is_state : 0
             */

            private String vote_id;
            private String vote_name;
            private String vote_pic;
            private String vote_link;
            private Object total_peo;
            private int right_num;
            private int is_state;

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

            public String getVote_pic() {
                return vote_pic;
            }

            public void setVote_pic(String vote_pic) {
                this.vote_pic = vote_pic;
            }

            public String getVote_link() {
                return vote_link;
            }

            public void setVote_link(String vote_link) {
                this.vote_link = vote_link;
            }

            public Object getTotal_peo() {
                return total_peo;
            }

            public void setTotal_peo(Object total_peo) {
                this.total_peo = total_peo;
            }

            public int getRight_num() {
                return right_num;
            }

            public void setRight_num(int right_num) {
                this.right_num = right_num;
            }

            public int getIs_state() {
                return is_state;
            }

            public void setIs_state(int is_state) {
                this.is_state = is_state;
            }
        }
    }
}
