package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/12/11.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/11
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：养生达人个人主页的实体类
 */

public class MasterDetailBean {


    /**
     * status : 200
     * message : 获取达人个人主页成功
     * data : {"master_info":{"master_name":"我没有名字","master_desc":"日常垃圾不知如何处理，其实垃圾也可以巧变成宝，生活其实很美好，只是缺少发现美的眼睛。","master_headimg":"http://hehe.heyishenghuo.com/mobile2/app2/health_talent/images/man.png","big_sum":"0","atten_sum":"0","fans_sum":"0","atten_status":"0","per_state":0,"topic_sum":"0"},"bigcasts":[{"bigcast_name":"","bigcast_pic":""}],"master":[{"atten_master_name":"","atten_master_pic":""}],"fans":[{"fans_name":"","fans_pic":""}],"article":[{"article_title":"去除咖啡渍的小妙招","article_desc":"","article_llink":"http://hehe.heyishenghuo.commobile2/app2/health_talent/article.php?id=4","article_pic":["http://hehe.heyishenghuo.com/data/health_talent/201711/1510101170809988016.jpg"],"article_addtime":"2017-11-08","master_name":"我没有名字","master_headimg":"http://hehe.heyishenghuo.com/mobile2/app2/health_talent/images/man.png"},{"article_title":"香皂竟有那么多妙用","article_desc":null,"article_llink":"http://hehe.heyishenghuo.commobile2/app2/health_talent/article.php?id=4","article_pic":["http://hehe.heyishenghuo.com/data/health_talent/201711/1510101170809988016.jpg","http://hehe.heyishenghuo.com/data/health_talent/201711/15101294733404.jpg"],"article_addtime":"2017-11-08","master_name":"我没有名字","master_headimg":"http://hehe.heyishenghuo.com/mobile2/app2/health_talent/images/man.png"}]}
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
         * master_info : {"master_name":"我没有名字","master_desc":"日常垃圾不知如何处理，其实垃圾也可以巧变成宝，生活其实很美好，只是缺少发现美的眼睛。","master_headimg":"http://hehe.heyishenghuo.com/mobile2/app2/health_talent/images/man.png","big_sum":"0","atten_sum":"0","fans_sum":"0","atten_status":"0","per_state":0,"topic_sum":"0"}
         * bigcasts : [{"bigcast_name":"","bigcast_pic":""}]
         * master : [{"atten_master_name":"","atten_master_pic":""}]
         * fans : [{"fans_name":"","fans_pic":""}]
         * article : [{"article_title":"去除咖啡渍的小妙招","article_desc":"","article_llink":"http://hehe.heyishenghuo.commobile2/app2/health_talent/article.php?id=4","article_pic":["http://hehe.heyishenghuo.com/data/health_talent/201711/1510101170809988016.jpg"],"article_addtime":"2017-11-08","master_name":"我没有名字","master_headimg":"http://hehe.heyishenghuo.com/mobile2/app2/health_talent/images/man.png"},{"article_title":"香皂竟有那么多妙用","article_desc":null,"article_llink":"http://hehe.heyishenghuo.commobile2/app2/health_talent/article.php?id=4","article_pic":["http://hehe.heyishenghuo.com/data/health_talent/201711/1510101170809988016.jpg","http://hehe.heyishenghuo.com/data/health_talent/201711/15101294733404.jpg"],"article_addtime":"2017-11-08","master_name":"我没有名字","master_headimg":"http://hehe.heyishenghuo.com/mobile2/app2/health_talent/images/man.png"}]
         */

        private MasterInfoBean master_info;
        private List<BigcastsBean> bigcasts;
        private List<MasterBean> master;
        private List<FansBean> fans;
        private List<ArticleBean> article;

        public MasterInfoBean getMaster_info() {
            return master_info;
        }

        public void setMaster_info(MasterInfoBean master_info) {
            this.master_info = master_info;
        }

        public List<BigcastsBean> getBigcasts() {
            return bigcasts;
        }

        public void setBigcasts(List<BigcastsBean> bigcasts) {
            this.bigcasts = bigcasts;
        }

        public List<MasterBean> getMaster() {
            return master;
        }

        public void setMaster(List<MasterBean> master) {
            this.master = master;
        }

        public List<FansBean> getFans() {
            return fans;
        }

        public void setFans(List<FansBean> fans) {
            this.fans = fans;
        }

        public List<ArticleBean> getArticle() {
            return article;
        }

        public void setArticle(List<ArticleBean> article) {
            this.article = article;
        }

        public static class MasterInfoBean {
            /**
             * master_name : 我没有名字
             * master_desc : 日常垃圾不知如何处理，其实垃圾也可以巧变成宝，生活其实很美好，只是缺少发现美的眼睛。
             * master_headimg : http://hehe.heyishenghuo.com/mobile2/app2/health_talent/images/man.png
             * big_sum : 0
             * atten_sum : 0
             * fans_sum : 0
             * atten_status : 0
             * per_state : 0
             * topic_sum : 0
             */

            private String master_name;
            private String master_desc;
            private String master_headimg;
            private String big_sum;
            private String atten_sum;
            private String fans_sum;
            private String atten_status;
            private int per_state;
            private String topic_sum;

            public String getMaster_name() {
                return master_name;
            }

            public void setMaster_name(String master_name) {
                this.master_name = master_name;
            }

            public String getMaster_desc() {
                return master_desc;
            }

            public void setMaster_desc(String master_desc) {
                this.master_desc = master_desc;
            }

            public String getMaster_headimg() {
                return master_headimg;
            }

            public void setMaster_headimg(String master_headimg) {
                this.master_headimg = master_headimg;
            }

            public String getBig_sum() {
                return big_sum;
            }

            public void setBig_sum(String big_sum) {
                this.big_sum = big_sum;
            }

            public String getAtten_sum() {
                return atten_sum;
            }

            public void setAtten_sum(String atten_sum) {
                this.atten_sum = atten_sum;
            }

            public String getFans_sum() {
                return fans_sum;
            }

            public void setFans_sum(String fans_sum) {
                this.fans_sum = fans_sum;
            }

            public String getAtten_status() {
                return atten_status;
            }

            public void setAtten_status(String atten_status) {
                this.atten_status = atten_status;
            }

            public int getPer_state() {
                return per_state;
            }

            public void setPer_state(int per_state) {
                this.per_state = per_state;
            }

            public String getTopic_sum() {
                return topic_sum;
            }

            public void setTopic_sum(String topic_sum) {
                this.topic_sum = topic_sum;
            }
        }

        public static class BigcastsBean {
            /**
             * bigcast_name :
             * bigcast_pic :
             */

            private String bigcast_name;
            private String bigcast_pic;

            public String getBigcast_name() {
                return bigcast_name;
            }

            public void setBigcast_name(String bigcast_name) {
                this.bigcast_name = bigcast_name;
            }

            public String getBigcast_pic() {
                return bigcast_pic;
            }

            public void setBigcast_pic(String bigcast_pic) {
                this.bigcast_pic = bigcast_pic;
            }
        }

        public static class MasterBean {
            /**
             * atten_master_name :
             * atten_master_pic :
             */

            private String atten_master_name;
            private String atten_master_pic;

            public String getAtten_master_name() {
                return atten_master_name;
            }

            public void setAtten_master_name(String atten_master_name) {
                this.atten_master_name = atten_master_name;
            }

            public String getAtten_master_pic() {
                return atten_master_pic;
            }

            public void setAtten_master_pic(String atten_master_pic) {
                this.atten_master_pic = atten_master_pic;
            }
        }

        public static class FansBean {
            /**
             * fans_name :
             * fans_pic :
             */

            private String fans_name;
            private String fans_pic;

            public String getFans_name() {
                return fans_name;
            }

            public void setFans_name(String fans_name) {
                this.fans_name = fans_name;
            }

            public String getFans_pic() {
                return fans_pic;
            }

            public void setFans_pic(String fans_pic) {
                this.fans_pic = fans_pic;
            }
        }

        public static class ArticleBean {
            /**
             * article_title : 去除咖啡渍的小妙招
             * article_desc :
             * article_llink : http://hehe.heyishenghuo.commobile2/app2/health_talent/article.php?id=4
             * article_pic : ["http://hehe.heyishenghuo.com/data/health_talent/201711/1510101170809988016.jpg"]
             * article_addtime : 2017-11-08
             * master_name : 我没有名字
             * master_headimg : http://hehe.heyishenghuo.com/mobile2/app2/health_talent/images/man.png
             */

            private String article_title;
            private String article_desc;
            private String article_llink;
            private String article_addtime;
            private String master_name;
            private String master_headimg;
            private String article_id;

            public String getArticle_id() {
                return article_id;
            }

            public void setArticle_id(String article_id) {
                this.article_id = article_id;
            }

            private List<String> article_pic;

            public String getArticle_title() {
                return article_title;
            }

            public void setArticle_title(String article_title) {
                this.article_title = article_title;
            }

            public String getArticle_desc() {
                return article_desc;
            }

            public void setArticle_desc(String article_desc) {
                this.article_desc = article_desc;
            }

            public String getArticle_llink() {
                return article_llink;
            }

            public void setArticle_llink(String article_llink) {
                this.article_llink = article_llink;
            }

            public String getArticle_addtime() {
                return article_addtime;
            }

            public void setArticle_addtime(String article_addtime) {
                this.article_addtime = article_addtime;
            }

            public String getMaster_name() {
                return master_name;
            }

            public void setMaster_name(String master_name) {
                this.master_name = master_name;
            }

            public String getMaster_headimg() {
                return master_headimg;
            }

            public void setMaster_headimg(String master_headimg) {
                this.master_headimg = master_headimg;
            }

            public List<String> getArticle_pic() {
                return article_pic;
            }

            public void setArticle_pic(List<String> article_pic) {
                this.article_pic = article_pic;
            }
        }
    }
}
