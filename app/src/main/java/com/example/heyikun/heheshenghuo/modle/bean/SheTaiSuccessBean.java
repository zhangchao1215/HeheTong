package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/10/17.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/17
 * <p>
 * 3：类描述：  舌苔的最后诊断结果
 * <p>
 * 4:类功能：
 */

public class SheTaiSuccessBean {


    /**
     * status : 200
     * message : 诊断成功
     * data : {"name":"风寒头痛","description":"","article_id":"0","article":[{"title":"","link":"http://hehe.heyishenghuo.com/mobile2/app2/disease/disease-article.php?id=0","file_url":"http://hehe.heyishenghuo.com/"}]}
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
         * name : 风寒头痛
         * description :
         * article_id : 0
         * article : [{"title":"","link":"http://hehe.heyishenghuo.com/mobile2/app2/disease/disease-article.php?id=0","file_url":"http://hehe.heyishenghuo.com/"}]
         */

        private String name;
        private String description;
        private String article_id;
        private List<ArticleBean> article;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public static class ArticleBean {
            /**
             * title :
             * link : http://hehe.heyishenghuo.com/mobile2/app2/disease/disease-article.php?id=0
             * file_url : http://hehe.heyishenghuo.com/
             */

            private String title;
            private String link;
            private String file_url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getFile_url() {
                return file_url;
            }

            public void setFile_url(String file_url) {
                this.file_url = file_url;
            }
        }
    }
}
