package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/10/28.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/28
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 健康发现首页数据
 */

public class HeHeFaxianBean {


    /**
     * status : 200
     * message : 获取健康发现首页成功
     * data : {"carousel":[{"carousel_title":"2-自营产品","carousel_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1509572053749915055.png","carousel_link":"http://test2.heyishenghuo.com/mobile/self_goods/"},{"carousel_title":"3-饭与床","carousel_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1509572026428509327.png","carousel_link":"http://hehe.heyishenghuo.com/mobile/rice_bed/index.html"}],"consults":[{"title":"对于孩子 不帮助才是最大的帮助","link":"http://hehe.heyishenghuo.com/mobile2/app2/health_talent/personal-page-comm.php?id=165"}],"plate":[{"plate_id":"188","plate_name":"大咖说","plate_link":"http://hehe.heyishenghuo.com/mobile2/app2/bigcast/index.php","plate_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1509315119740179493.png","article_sum":"242","plate_desc":"专家病理分析调理"},{"plate_id":"189","plate_name":"养生达人","plate_link":"http://hehe.heyishenghuo.com/mobile2/app2/health_talent/index.php","plate_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1509315549141103477.png","article_sum":"251","plate_desc":"民间高手养生秘方"},{"plate_id":"190","plate_name":"和合汇","plate_link":"http://hehe.heyishenghuo.com/mobile2/app2/hehehui/index.php","plate_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1509315542257564372.png","article_sum":"275","plate_desc":"丰富多彩养生活动"},{"plate_id":"191","plate_name":"读健康","plate_link":"http://hehe.heyishenghuo.com/mobile2/app2/read-health/index.php","plate_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1509315108433180937.png","article_sum":"226","plate_desc":"您的线上养生书库"},{"plate_id":"192","plate_name":"问健康","plate_link":"http://hehe.heyishenghuo.com/mobile2/app2/ask-health/index.php","plate_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1509315078698171657.png","article_sum":"74","plate_desc":"解答一切健康问题"},{"plate_id":"193","plate_name":"疾病谱","plate_link":"http://hehe.heyishenghuo.com/mobile2/app2/disease/index.php","plate_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1509315115043267555.png","article_sum":"275","plate_desc":"深入了解病情病理"}]}
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
        private List<CarouselBean> carousel;
        private List<ConsultsBean> consults;
        private List<PlateBean> plate;

        public List<CarouselBean> getCarousel() {
            return carousel;
        }

        public void setCarousel(List<CarouselBean> carousel) {
            this.carousel = carousel;
        }

        public List<ConsultsBean> getConsults() {
            return consults;
        }

        public void setConsults(List<ConsultsBean> consults) {
            this.consults = consults;
        }

        public List<PlateBean> getPlate() {
            return plate;
        }

        public void setPlate(List<PlateBean> plate) {
            this.plate = plate;
        }

        public static class CarouselBean {
            /**
             * carousel_title : 2-自营产品
             * carousel_pic : http://hehe.heyishenghuo.com/data/afficheimg/1509572053749915055.png
             * carousel_link : http://test2.heyishenghuo.com/mobile/self_goods/
             */

            private String carousel_title;
            private String carousel_pic;
            private String carousel_link;

            public String getCarousel_title() {
                return carousel_title;
            }

            public void setCarousel_title(String carousel_title) {
                this.carousel_title = carousel_title;
            }

            public String getCarousel_pic() {
                return carousel_pic;
            }

            public void setCarousel_pic(String carousel_pic) {
                this.carousel_pic = carousel_pic;
            }

            public String getCarousel_link() {
                return carousel_link;
            }

            public void setCarousel_link(String carousel_link) {
                this.carousel_link = carousel_link;
            }
        }

        public static class ConsultsBean {
            /**
             * title : 对于孩子 不帮助才是最大的帮助
             * link : http://hehe.heyishenghuo.com/mobile2/app2/health_talent/personal-page-comm.php?id=165
             */

            private String title;
            private String link;

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
        }

        public static class PlateBean {
            /**
             * plate_id : 188
             * plate_name : 大咖说
             * plate_link : http://hehe.heyishenghuo.com/mobile2/app2/bigcast/index.php
             * plate_pic : http://hehe.heyishenghuo.com/data/afficheimg/1509315119740179493.png
             * article_sum : 242
             * plate_desc : 专家病理分析调理
             */

            private String plate_id;
            private String plate_name;
            private String plate_link;
            private String plate_pic;
            private String article_sum;
            private String plate_desc;

            public String getPlate_id() {
                return plate_id;
            }

            public void setPlate_id(String plate_id) {
                this.plate_id = plate_id;
            }

            public String getPlate_name() {
                return plate_name;
            }

            public void setPlate_name(String plate_name) {
                this.plate_name = plate_name;
            }

            public String getPlate_link() {
                return plate_link;
            }

            public void setPlate_link(String plate_link) {
                this.plate_link = plate_link;
            }

            public String getPlate_pic() {
                return plate_pic;
            }

            public void setPlate_pic(String plate_pic) {
                this.plate_pic = plate_pic;
            }

            public String getArticle_sum() {
                return article_sum;
            }

            public void setArticle_sum(String article_sum) {
                this.article_sum = article_sum;
            }

            public String getPlate_desc() {
                return plate_desc;
            }

            public void setPlate_desc(String plate_desc) {
                this.plate_desc = plate_desc;
            }
        }
    }
}
