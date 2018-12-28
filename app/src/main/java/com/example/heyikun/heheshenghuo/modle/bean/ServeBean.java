package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * 项目名称: 城市通
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2017/12/3 10:18
 * 修改人:  张超
 * 修改内容:
 * 修改时间: 和合服务实体类
 */

public class ServeBean {


    /**
     * status : 200
     * message : 获取和合服务数据成功
     * data : {"menu":[{"menu_name":"中医医馆","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489842520728305.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=1"},{"menu_name":"体检机构","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489859545495039.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=2"},{"menu_name":"美容美体","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489873420283819.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=3"},{"menu_name":"健身俱乐部","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489887535350774.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=4"},{"menu_name":"中医养生馆","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489826706813295.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=9"}],"banner_ad":[{"carousel_title":null,"carousel_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512081197007740074.jpg","carousel_link":"?#"},{"carousel_title":null,"carousel_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512081034114602163.jpg","carousel_link":"?#"},{"carousel_title":null,"carousel_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512085326139638875.png","carousel_link":"?#"}],"shops":[{"shops_name":"中信健康深圳体检中心（福田店）","shops_rank":"3","shops_address":"深南中路6011号NEO大厦A座16楼（近天安数码城）","shops_logo":"http://hehe.heyishenghuo.com/data/street_logo/supplier66/original66_220x220.jpg","shops_tags":[],"shops_link":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/supplier.php?id=66","distance":"未知"},{"shops_name":"中医医馆","shops_rank":"1","shops_address":"北京市丰台区总部基地","shops_logo":"http://hehe.heyishenghuo.com/data/street_logo/supplier73/original73_220x220.jpg","shops_tags":[],"shops_link":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/supplier.php?id=73","distance":"未知"}],"district":"","more_shop_link":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?fangzhao=1"}
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
         * menu : [{"menu_name":"中医医馆","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489842520728305.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=1"},{"menu_name":"体检机构","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489859545495039.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=2"},{"menu_name":"美容美体","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489873420283819.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=3"},{"menu_name":"健身俱乐部","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489887535350774.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=4"},{"menu_name":"中医养生馆","menu_img":"http://hehe.heyishenghuo.com/data/supplier_street/1511489826706813295.png","menu_url":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=9"}]
         * banner_ad : [{"carousel_title":null,"carousel_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512081197007740074.jpg","carousel_link":"?#"},{"carousel_title":null,"carousel_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512081034114602163.jpg","carousel_link":"?#"},{"carousel_title":null,"carousel_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512085326139638875.png","carousel_link":"?#"}]
         * shops : [{"shops_name":"中信健康深圳体检中心（福田店）","shops_rank":"3","shops_address":"深南中路6011号NEO大厦A座16楼（近天安数码城）","shops_logo":"http://hehe.heyishenghuo.com/data/street_logo/supplier66/original66_220x220.jpg","shops_tags":[],"shops_link":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/supplier.php?id=66","distance":"未知"},{"shops_name":"中医医馆","shops_rank":"1","shops_address":"北京市丰台区总部基地","shops_logo":"http://hehe.heyishenghuo.com/data/street_logo/supplier73/original73_220x220.jpg","shops_tags":[],"shops_link":"http://hehe.heyishenghuo.com/mobile2/app2/merchant/supplier.php?id=73","distance":"未知"}]
         * district :
         * more_shop_link : http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?fangzhao=1
         */

        private String district;
        private String more_shop_link;
        private List<MenuBean> menu;
        private List<BannerAdBean> banner_ad;
        private List<ShopsBean> shops;

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getMore_shop_link() {
            return more_shop_link;
        }

        public void setMore_shop_link(String more_shop_link) {
            this.more_shop_link = more_shop_link;
        }

        public List<MenuBean> getMenu() {
            return menu;
        }

        public void setMenu(List<MenuBean> menu) {
            this.menu = menu;
        }

        public List<BannerAdBean> getBanner_ad() {
            return banner_ad;
        }

        public void setBanner_ad(List<BannerAdBean> banner_ad) {
            this.banner_ad = banner_ad;
        }

        public List<ShopsBean> getShops() {
            return shops;
        }

        public void setShops(List<ShopsBean> shops) {
            this.shops = shops;
        }

        public static class MenuBean {
            /**
             * menu_name : 中医医馆
             * menu_img : http://hehe.heyishenghuo.com/data/supplier_street/1511489842520728305.png
             * menu_url : http://hehe.heyishenghuo.com/mobile2/app2/merchant/list.php?cat_id=1
             */

            private String menu_name;
            private String menu_img;
            private String menu_url;

            public String getMenu_name() {
                return menu_name;
            }

            public void setMenu_name(String menu_name) {
                this.menu_name = menu_name;
            }

            public String getMenu_img() {
                return menu_img;
            }

            public void setMenu_img(String menu_img) {
                this.menu_img = menu_img;
            }

            public String getMenu_url() {
                return menu_url;
            }

            public void setMenu_url(String menu_url) {
                this.menu_url = menu_url;
            }
        }

        public static class BannerAdBean {
            /**
             * carousel_title : null
             * carousel_pic : http://hehe.heyishenghuo.com/data/afficheimg/1512081197007740074.jpg
             * carousel_link : ?#
             */

            private Object carousel_title;
            private String carousel_pic;
            private String carousel_link;

            public Object getCarousel_title() {
                return carousel_title;
            }

            public void setCarousel_title(Object carousel_title) {
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

        public static class ShopsBean {
            /**
             * shops_name : 中信健康深圳体检中心（福田店）
             * shops_rank : 3
             * shops_address : 深南中路6011号NEO大厦A座16楼（近天安数码城）
             * shops_logo : http://hehe.heyishenghuo.com/data/street_logo/supplier66/original66_220x220.jpg
             * shops_tags : []
             * shops_link : http://hehe.heyishenghuo.com/mobile2/app2/merchant/supplier.php?id=66
             * distance : 未知
             */

            private String shops_name;
            private String shops_rank;
            private String shops_address;
            private String shops_logo;
            private String shops_link;
            private String distance;
            private List<?> shops_tags;

            public String getShops_name() {
                return shops_name;
            }

            public void setShops_name(String shops_name) {
                this.shops_name = shops_name;
            }

            public String getShops_rank() {
                return shops_rank;
            }

            public void setShops_rank(String shops_rank) {
                this.shops_rank = shops_rank;
            }

            public String getShops_address() {
                return shops_address;
            }

            public void setShops_address(String shops_address) {
                this.shops_address = shops_address;
            }

            public String getShops_logo() {
                return shops_logo;
            }

            public void setShops_logo(String shops_logo) {
                this.shops_logo = shops_logo;
            }

            public String getShops_link() {
                return shops_link;
            }

            public void setShops_link(String shops_link) {
                this.shops_link = shops_link;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public List<?> getShops_tags() {
                return shops_tags;
            }

            public void setShops_tags(List<?> shops_tags) {
                this.shops_tags = shops_tags;
            }
        }
    }
}
