package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/11/9.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/9
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 文章分享实体类
 */

public class ShareBean {


    /**
     * status : 200
     * message : 获取成功
     * data : {"title":"心理访谈节目并不等同心理咨询","icon":"http://hehe.heyishenghuo.com/data/dr_bigcast/1512672332905777638.jpg","article_desc":"有些心理访谈类节目是\u201c伪心理咨询\u201d\u2015\u2015上海社会科学院教授张结海的一席话，无疑像一枚重磅炸弹，给这类节目重重的一击。同时，也让观众心生疑惑，难道心理访谈是在\u201c忽悠\u201d人吗?究竟心理访谈是不是心理咨询，能不能解决困惑呢?","praise_count":"2","comment_count":"0","comment_link":"http://hehe.heyishenghuo.com/mobile2/app2/bigcast/ups-commentaries.php?id=352","fabulous":"1","collect_state":"0"}
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
         * title : 心理访谈节目并不等同心理咨询
         * icon : http://hehe.heyishenghuo.com/data/dr_bigcast/1512672332905777638.jpg
         * article_desc : 有些心理访谈类节目是“伪心理咨询”――上海社会科学院教授张结海的一席话，无疑像一枚重磅炸弹，给这类节目重重的一击。同时，也让观众心生疑惑，难道心理访谈是在“忽悠”人吗?究竟心理访谈是不是心理咨询，能不能解决困惑呢?
         * praise_count : 2
         * comment_count : 0
         * comment_link : http://hehe.heyishenghuo.com/mobile2/app2/bigcast/ups-commentaries.php?id=352
         * fabulous : 1
         * collect_state : 0
         */

        private String title;
        private String icon;
        private String article_desc;
        private String praise_count;
        private String comment_count;
        private String comment_link;
        private String fabulous;
        private String collect_state;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getArticle_desc() {
            return article_desc;
        }

        public void setArticle_desc(String article_desc) {
            this.article_desc = article_desc;
        }

        public String getPraise_count() {
            return praise_count;
        }

        public void setPraise_count(String praise_count) {
            this.praise_count = praise_count;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getComment_link() {
            return comment_link;
        }

        public void setComment_link(String comment_link) {
            this.comment_link = comment_link;
        }

        public String getFabulous() {
            return fabulous;
        }

        public void setFabulous(String fabulous) {
            this.fabulous = fabulous;
        }

        public String getCollect_state() {
            return collect_state;
        }

        public void setCollect_state(String collect_state) {
            this.collect_state = collect_state;
        }
    }
}
