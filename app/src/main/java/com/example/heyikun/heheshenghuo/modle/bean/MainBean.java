package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/9/20.
 * 首页数据的请求
 */

public class MainBean  {


    /**
     * status : 200
     * message : 请求成功
     * data : {"body":[{"app_sort":"7","vote_id":"54","vote_name":"中医体质判定","vote_link":"http://hehe.heyishenghuo.com/mobile2/constitution.php?vote_id=54","vote_man":"","vote_woman":"","vote_copy":"气虚质、阳虚质、阴虚质","vote_res":"395","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=54"},{"app_sort":"6","vote_id":"13","vote_name":"睡眠质量","vote_link":"http://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=13","vote_man":"http://hehe.heyishenghuo.com/data/question/1511235702955712088.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511235702006653073.png","vote_copy":"","vote_res":"18","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=13"},{"app_sort":"5","vote_id":"64","vote_name":"疲劳指数","vote_link":"http://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=64","vote_man":"http://hehe.heyishenghuo.com/data/question/1511284439483721328.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511284439625608460.png","vote_copy":"","vote_res":"4","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=64"},{"app_sort":"4","vote_id":"69","vote_name":"男性性功能","vote_link":"http://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=69","vote_man":"http://hehe.heyishenghuo.com/data/question/1511321118871943734.png","vote_woman":"http://hehe.heyishenghuo.com/","vote_copy":"","vote_res":"90","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=69"},{"app_sort":"3","vote_id":"36","vote_name":"营养素值","vote_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/nutrient.php?vote_id=36","vote_man":"http://hehe.heyishenghuo.com/data/question/1511284550337422948.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511284550086662334.png","vote_copy":"","vote_res":"64","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=36"},{"app_sort":"2","vote_id":"68","vote_name":"五行体质","vote_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/fiveline.php?vote_id=68","vote_man":"http://hehe.heyishenghuo.com/data/question/1511321323665684941.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511321323232322808.png","vote_copy":"","vote_res":"68","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=68"},{"app_sort":"1","vote_id":"47","vote_name":"亚健康值","vote_link":"http://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=47","vote_man":"http://hehe.heyishenghuo.com/data/question/1511234538566042913.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511234538491430474.png","vote_copy":"","vote_res":"78","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=47"}],"day":[{"table":"距离<span style='display:inline-block; margin: 0 10px'>情人节<\/span><br />还有<strong style='font-size: 20px;'>56<\/strong>天"},{"table":"距离<span style='display:inline-block; margin: 0 10px'>妇女节<\/span><br />还有<strong style='font-size: 20px;'>78<\/strong>天"}],"dates":{"gregorian":"大雪","year":"2017-12","solar":"20","lunar":"丁酉(鸡)年十一月初三","solarlink":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4693"},"eat":{"eat_copy":"","eat_pic":"http://hehe.heyishenghuo.com/","eat_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id="},"physique":{"body_copy":"健康火锅","body_pic":"http://hehe.heyishenghuo.com/data/article/1513642935974119051.jpg","body_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=6303"},"vip_ad":{"vip_title":"和合家庭全域健康管理","vip_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512513190256035853.png","vip_link":"https://hehe.heyishenghuo.com/mobile2/health_project_info/index.php?fangzhao=1"},"banner_ad":[{"banner_title":"和合家庭全域健康管理","banner_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512524287550890657.jpg","banner_link":"http://hehe.heyishenghuo.com/mobile2/plan/index.php?fangzhao=1"},{"banner_title":"和合家庭全域健康管理","banner_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512513190256035853.png","banner_link":"https://hehe.heyishenghuo.com/mobile2/health_project_info/index.php?fangzhao=1"}],"viplink":"http://hehe.heyishenghuo.com/mobile2/plan/index.php?fangzhao=1","five_result":"jin"}
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
         * body : [{"app_sort":"7","vote_id":"54","vote_name":"中医体质判定","vote_link":"http://hehe.heyishenghuo.com/mobile2/constitution.php?vote_id=54","vote_man":"","vote_woman":"","vote_copy":"气虚质、阳虚质、阴虚质","vote_res":"395","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=54"},{"app_sort":"6","vote_id":"13","vote_name":"睡眠质量","vote_link":"http://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=13","vote_man":"http://hehe.heyishenghuo.com/data/question/1511235702955712088.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511235702006653073.png","vote_copy":"","vote_res":"18","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=13"},{"app_sort":"5","vote_id":"64","vote_name":"疲劳指数","vote_link":"http://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=64","vote_man":"http://hehe.heyishenghuo.com/data/question/1511284439483721328.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511284439625608460.png","vote_copy":"","vote_res":"4","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=64"},{"app_sort":"4","vote_id":"69","vote_name":"男性性功能","vote_link":"http://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=69","vote_man":"http://hehe.heyishenghuo.com/data/question/1511321118871943734.png","vote_woman":"http://hehe.heyishenghuo.com/","vote_copy":"","vote_res":"90","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=69"},{"app_sort":"3","vote_id":"36","vote_name":"营养素值","vote_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/nutrient.php?vote_id=36","vote_man":"http://hehe.heyishenghuo.com/data/question/1511284550337422948.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511284550086662334.png","vote_copy":"","vote_res":"64","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=36"},{"app_sort":"2","vote_id":"68","vote_name":"五行体质","vote_link":"http://hehe.heyishenghuo.com/mobile2/testquestions/fiveline.php?vote_id=68","vote_man":"http://hehe.heyishenghuo.com/data/question/1511321323665684941.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511321323232322808.png","vote_copy":"","vote_res":"68","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=68"},{"app_sort":"1","vote_id":"47","vote_name":"亚健康值","vote_link":"http://hehe.heyishenghuo.com/mobile2/yscs.php?vote_id=47","vote_man":"http://hehe.heyishenghuo.com/data/question/1511234538566042913.png","vote_woman":"http://hehe.heyishenghuo.com/data/question/1511234538491430474.png","vote_copy":"","vote_res":"78","tests_link":"http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=47"}]
         * day : [{"table":"距离<span style='display:inline-block; margin: 0 10px'>情人节<\/span><br />还有<strong style='font-size: 20px;'>56<\/strong>天"},{"table":"距离<span style='display:inline-block; margin: 0 10px'>妇女节<\/span><br />还有<strong style='font-size: 20px;'>78<\/strong>天"}]
         * dates : {"gregorian":"大雪","year":"2017-12","solar":"20","lunar":"丁酉(鸡)年十一月初三","solarlink":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4693"}
         * eat : {"eat_copy":"","eat_pic":"http://hehe.heyishenghuo.com/","eat_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id="}
         * physique : {"body_copy":"健康火锅","body_pic":"http://hehe.heyishenghuo.com/data/article/1513642935974119051.jpg","body_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=6303"}
         * vip_ad : {"vip_title":"和合家庭全域健康管理","vip_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512513190256035853.png","vip_link":"https://hehe.heyishenghuo.com/mobile2/health_project_info/index.php?fangzhao=1"}
         * banner_ad : [{"banner_title":"和合家庭全域健康管理","banner_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512524287550890657.jpg","banner_link":"http://hehe.heyishenghuo.com/mobile2/plan/index.php?fangzhao=1"},{"banner_title":"和合家庭全域健康管理","banner_pic":"http://hehe.heyishenghuo.com/data/afficheimg/1512513190256035853.png","banner_link":"https://hehe.heyishenghuo.com/mobile2/health_project_info/index.php?fangzhao=1"}]
         * viplink : http://hehe.heyishenghuo.com/mobile2/plan/index.php?fangzhao=1
         * five_result : jin
         */

        private DatesBean dates;
        private EatBean eat;
        private PhysiqueBean physique;
        private VipAdBean vip_ad;
        private String viplink;
        private String five_result;
        private List<BodyBean> body;
        private List<DayBean> day;
        private List<BannerAdBean> banner_ad;

        public DatesBean getDates() {
            return dates;
        }

        public void setDates(DatesBean dates) {
            this.dates = dates;
        }

        public EatBean getEat() {
            return eat;
        }

        public void setEat(EatBean eat) {
            this.eat = eat;
        }

        public PhysiqueBean getPhysique() {
            return physique;
        }

        public void setPhysique(PhysiqueBean physique) {
            this.physique = physique;
        }

        public VipAdBean getVip_ad() {
            return vip_ad;
        }

        public void setVip_ad(VipAdBean vip_ad) {
            this.vip_ad = vip_ad;
        }

        public String getViplink() {
            return viplink;
        }

        public void setViplink(String viplink) {
            this.viplink = viplink;
        }

        public String getFive_result() {
            return five_result;
        }

        public void setFive_result(String five_result) {
            this.five_result = five_result;
        }

        public List<BodyBean> getBody() {
            return body;
        }

        public void setBody(List<BodyBean> body) {
            this.body = body;
        }

        public List<DayBean> getDay() {
            return day;
        }

        public void setDay(List<DayBean> day) {
            this.day = day;
        }

        public List<BannerAdBean> getBanner_ad() {
            return banner_ad;
        }

        public void setBanner_ad(List<BannerAdBean> banner_ad) {
            this.banner_ad = banner_ad;
        }

        public static class DatesBean {
            /**
             * gregorian : 大雪
             * year : 2017-12
             * solar : 20
             * lunar : 丁酉(鸡)年十一月初三
             * solarlink : http://hehe.heyishenghuo.com/mobile2/article.php?id=4693
             */

            private String gregorian;
            private String year;
            private String solar;
            private String lunar;
            private String solarlink;

            public String getGregorian() {
                return gregorian;
            }

            public void setGregorian(String gregorian) {
                this.gregorian = gregorian;
            }

            public String getYear() {
                return year;
            }

            public void setYear(String year) {
                this.year = year;
            }

            public String getSolar() {
                return solar;
            }

            public void setSolar(String solar) {
                this.solar = solar;
            }

            public String getLunar() {
                return lunar;
            }

            public void setLunar(String lunar) {
                this.lunar = lunar;
            }

            public String getSolarlink() {
                return solarlink;
            }

            public void setSolarlink(String solarlink) {
                this.solarlink = solarlink;
            }
        }

        public static class EatBean {
            /**
             * eat_copy :
             * eat_pic : http://hehe.heyishenghuo.com/
             * eat_link : http://hehe.heyishenghuo.com/mobile2/article.php?id=
             */

            private String eat_copy;
            private String eat_pic;
            private String eat_link;

            public String getEat_copy() {
                return eat_copy;
            }

            public void setEat_copy(String eat_copy) {
                this.eat_copy = eat_copy;
            }

            public String getEat_pic() {
                return eat_pic;
            }

            public void setEat_pic(String eat_pic) {
                this.eat_pic = eat_pic;
            }

            public String getEat_link() {
                return eat_link;
            }

            public void setEat_link(String eat_link) {
                this.eat_link = eat_link;
            }
        }

        public static class PhysiqueBean {
            /**
             * body_copy : 健康火锅
             * body_pic : http://hehe.heyishenghuo.com/data/article/1513642935974119051.jpg
             * body_link : http://hehe.heyishenghuo.com/mobile2/article.php?id=6303
             */

            private String body_copy;
            private String body_pic;
            private String body_link;

            public String getBody_copy() {
                return body_copy;
            }

            public void setBody_copy(String body_copy) {
                this.body_copy = body_copy;
            }

            public String getBody_pic() {
                return body_pic;
            }

            public void setBody_pic(String body_pic) {
                this.body_pic = body_pic;
            }

            public String getBody_link() {
                return body_link;
            }

            public void setBody_link(String body_link) {
                this.body_link = body_link;
            }
        }

        public static class VipAdBean {
            /**
             * vip_title : 和合家庭全域健康管理
             * vip_pic : http://hehe.heyishenghuo.com/data/afficheimg/1512513190256035853.png
             * vip_link : https://hehe.heyishenghuo.com/mobile2/health_project_info/index.php?fangzhao=1
             */

            private String vip_title;
            private String vip_pic;
            private String vip_link;

            public String getVip_title() {
                return vip_title;
            }

            public void setVip_title(String vip_title) {
                this.vip_title = vip_title;
            }

            public String getVip_pic() {
                return vip_pic;
            }

            public void setVip_pic(String vip_pic) {
                this.vip_pic = vip_pic;
            }

            public String getVip_link() {
                return vip_link;
            }

            public void setVip_link(String vip_link) {
                this.vip_link = vip_link;
            }
        }

        public static class BodyBean {
            /**
             * app_sort : 7
             * vote_id : 54
             * vote_name : 中医体质判定
             * vote_link : http://hehe.heyishenghuo.com/mobile2/constitution.php?vote_id=54
             * vote_man :
             * vote_woman :
             * vote_copy : 气虚质、阳虚质、阴虚质
             * vote_res : 395
             * tests_link : http://hehe.heyishenghuo.com/mobile2/app2/wenjuan/tests.php?id=54
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

        public static class DayBean {
            /**
             * table : 距离<span style='display:inline-block; margin: 0 10px'>情人节</span><br />还有<strong style='font-size: 20px;'>56</strong>天
             */

            private String table;

            public String getTable() {
                return table;
            }

            public void setTable(String table) {
                this.table = table;
            }
        }

        public static class BannerAdBean {
            /**
             * banner_title : 和合家庭全域健康管理
             * banner_pic : http://hehe.heyishenghuo.com/data/afficheimg/1512524287550890657.jpg
             * banner_link : http://hehe.heyishenghuo.com/mobile2/plan/index.php?fangzhao=1
             */

            private String banner_title;
            private String banner_pic;
            private String banner_link;

            public String getBanner_title() {
                return banner_title;
            }

            public void setBanner_title(String banner_title) {
                this.banner_title = banner_title;
            }

            public String getBanner_pic() {
                return banner_pic;
            }

            public void setBanner_pic(String banner_pic) {
                this.banner_pic = banner_pic;
            }

            public String getBanner_link() {
                return banner_link;
            }

            public void setBanner_link(String banner_link) {
                this.banner_link = banner_link;
            }
        }
    }
}
