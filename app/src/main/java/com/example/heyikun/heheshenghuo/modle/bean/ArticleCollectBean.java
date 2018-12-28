package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/11/1.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/1
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 文章收藏实体类
 */

public class ArticleCollectBean {


    /**
     * status : 200
     * message : 获取成功
     * data : {"article":[{"article_rec_id":"5","article_id":"3","title":"咨询热点","author":"","add_time":"2010-12-06","pic_url":"http://test2.heyishenghuo.com","type":"大咖说","article_link":"http://test2.heyishenghuo.com/mobile/app2/bigcast/ups-article.php?id=3"}]}
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
        private List<ArticleBean> article;

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public static class ArticleBean {
            /**
             * article_rec_id : 5
             * article_id : 3
             * title : 咨询热点
             * author :
             * add_time : 2010-12-06
             * pic_url : http://test2.heyishenghuo.com
             * type : 大咖说
             * article_link : http://test2.heyishenghuo.com/mobile/app2/bigcast/ups-article.php?id=3
             */

            private String article_rec_id;
            private String article_id;
            private String title;
            private String author;
            private String add_time;
            private String pic_url;
            private String type;
            private String article_link;

            public String getArticle_rec_id() {
                return article_rec_id;
            }

            public void setArticle_rec_id(String article_rec_id) {
                this.article_rec_id = article_rec_id;
            }

            public String getArticle_id() {
                return article_id;
            }

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getPic_url() {
                return pic_url;
            }

            public void setPic_url(String pic_url) {
                this.pic_url = pic_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getArticle_link() {
                return article_link;
            }

            public void setArticle_link(String article_link) {
                this.article_link = article_link;
            }
        }
    }
}
