package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/11/20.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/20
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 大咖详情的个人主页
 */

public class BigCastPeopleDetailBean {


    /**
     * status : 200
     * message : 获取大咖信息成功
     * data : {"big_name":"徐文兵","big_desc":"厚朴中医学堂堂主，高级中医讲师，中医教育家，身心医学专家。诊疗科目： 中医全科、针灸科。","big_pic":"http://test2.heyishenghuo.com/data/dr_bigcast_cat/1509989270163210254.png","follow_state":1,"article_sum":"5","atten_count":"639","hot":"429","popularity":0,"article":[{"label_name":["医术高超","妙手仁心"],"praise_count":"854","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509990027128922508.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=218"},{"label_name":["医术高超","妙手仁心"],"praise_count":"187","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509990597533216392.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=219"},{"label_name":["医术高超","妙手仁心"],"praise_count":"975","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509990861236169500.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=220"},{"label_name":["医术高超","妙手仁心"],"praise_count":"618","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509991465931697211.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=221"},{"label_name":["医术高超","妙手仁心"],"praise_count":"968","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509991733044941786.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=222"}]}
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
         * big_name : 徐文兵
         * big_desc : 厚朴中医学堂堂主，高级中医讲师，中医教育家，身心医学专家。诊疗科目： 中医全科、针灸科。
         * big_pic : http://test2.heyishenghuo.com/data/dr_bigcast_cat/1509989270163210254.png
         * follow_state : 1
         * article_sum : 5
         * atten_count : 639
         * hot : 429
         * popularity : 0
         * article : [{"label_name":["医术高超","妙手仁心"],"praise_count":"854","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509990027128922508.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=218"},{"label_name":["医术高超","妙手仁心"],"praise_count":"187","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509990597533216392.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=219"},{"label_name":["医术高超","妙手仁心"],"praise_count":"975","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509990861236169500.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=220"},{"label_name":["医术高超","妙手仁心"],"praise_count":"618","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509991465931697211.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=221"},{"label_name":["医术高超","妙手仁心"],"praise_count":"968","comment_count":"0","article_pic":"http://test2.heyishenghuo.com/data/dr_bigcast/1509991733044941786.jpg","article_link":"http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=222"}]
         */

        private String big_name;
        private String big_desc;
        private String big_pic;
        private int follow_state;
        private String article_sum;
        private String atten_count;
        private String hot;
        private int popularity;
        private List<ArticleBean> article;

        public String getBig_name() {
            return big_name;
        }

        public void setBig_name(String big_name) {
            this.big_name = big_name;
        }

        public String getBig_desc() {
            return big_desc;
        }

        public void setBig_desc(String big_desc) {
            this.big_desc = big_desc;
        }

        public String getBig_pic() {
            return big_pic;
        }

        public void setBig_pic(String big_pic) {
            this.big_pic = big_pic;
        }

        public int getFollow_state() {
            return follow_state;
        }

        public void setFollow_state(int follow_state) {
            this.follow_state = follow_state;
        }

        public String getArticle_sum() {
            return article_sum;
        }

        public void setArticle_sum(String article_sum) {
            this.article_sum = article_sum;
        }

        public String getAtten_count() {
            return atten_count;
        }

        public void setAtten_count(String atten_count) {
            this.atten_count = atten_count;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public int getPopularity() {
            return popularity;
        }

        public void setPopularity(int popularity) {
            this.popularity = popularity;
        }

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public static class ArticleBean {
            /**
             * label_name : ["医术高超","妙手仁心"]
             * praise_count : 854
             * comment_count : 0
             * article_pic : http://test2.heyishenghuo.com/data/dr_bigcast/1509990027128922508.jpg
             * article_link : http://test2.heyishenghuo.com/mobile2/app2/bigcast/ups-article.php?id=218
             */

            private String praise_count;
            private String comment_count;
            private String article_pic;
            private String article_link;
            private List<String> label_name;
            private String article_title;

            public String getArticle_title() {
                return article_title;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
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

            public String getArticle_pic() {
                return article_pic;
            }

            public void setArticle_pic(String article_pic) {
                this.article_pic = article_pic;
            }

            public String getArticle_link() {
                return article_link;
            }

            public void setArticle_link(String article_link) {
                this.article_link = article_link;
            }

            public List<String> getLabel_name() {
                return label_name;
            }

            public void setLabel_name(List<String> label_name) {
                this.label_name = label_name;
            }
        }
    }
}
