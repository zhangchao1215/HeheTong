package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/11/3.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/3
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：个人中心的网址链接
 */

public class UserLinkBean {


    /**
     * status : 200
     * message : 获取成功
     * data : {"bodylink":"http://hehe.heyishenghuo.com/mobile2/my-health-bak.php","heartlink":"http://hehe.heyishenghuo.com/mobile2/my_health.php?action=ct2","moneylink":"http://hehe.heyishenghuo.com/mobile2/my_health.php?action=ct3","allOrder":"http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list","paylink":"http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=100","shiplink":"http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=101","receiptlinnk":"http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=105","commentlink":"http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=103","customerlink":"http://hehe.heyishenghuo.com/mobile2/my-order-sale.php","balancelink":"http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=1","frozenlink":"http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=2","easylink":"http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=3","kunlink":"http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=4","couponlink":"http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=5","footprint":"http://hehe.heyishenghuo.com/mobile2/my-footprint.php?fangzhao=1","tasklink":"http://hehe.heyishenghuo.com/mobile2/my_renwu.php?fangzhao=1","activlink":"http://hehe.heyishenghuo.com/mobile2/app2/hehehui/index.php?fangzhao=1","consulink":"http://hehe.heyishenghuo.com/mobile2/app2/ask-health/my-consult.php?fangzhao=1","sharelink":"http://hehe.heyishenghuo.com/mobile2/share/index.php?fangzhao=1","extension":"http://hehe.heyishenghuo.com/mobile2/share/index3.php?fangzhao=1","healthplan":"http://hehe.heyishenghuo.com/mobile2/guide/health-testing.php?fangzhao=1","upgrade":"http://hehe.heyishenghuo.com/mobile2/health_lv/health_lv.php?fangzhao=1","notice":"http://hehe.heyishenghuo.com/mobile2/messages.php?fangzhao=1","myfriend":"http://hehe.heyishenghuo.commobile2/recommended.php?fangzhao=1","bigcastlink":"http://hehe.heyishenghuo.com/mobile2/app2/public/personal-page.php?fangzhao=1","orderLinks":["http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list","http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=100","http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=101","http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=105","http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=103","http://hehe.heyishenghuo.com/mobile2/my-order-sale.php"],"PersnalLinks":["http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=1","http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=2","http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=3","http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=4","http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=5"],"BlockLink":["http://hehe.heyishenghuo.com/mobile2/my-footprint.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/my_renwu.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/share/index.php?fangzhao=1","http://hehe.heyishenghuo.commobile2/recommended.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/share/index3.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/app2/hehehui/index.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/app2/ask-health/my-consult.php?fangzhao=1"]}
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
         * bodylink : http://hehe.heyishenghuo.com/mobile2/my-health-bak.php
         * heartlink : http://hehe.heyishenghuo.com/mobile2/my_health.php?action=ct2
         * moneylink : http://hehe.heyishenghuo.com/mobile2/my_health.php?action=ct3
         * allOrder : http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list
         * paylink : http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=100
         * shiplink : http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=101
         * receiptlinnk : http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=105
         * commentlink : http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=103
         * customerlink : http://hehe.heyishenghuo.com/mobile2/my-order-sale.php
         * balancelink : http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=1
         * frozenlink : http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=2
         * easylink : http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=3
         * kunlink : http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=4
         * couponlink : http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=5
         * footprint : http://hehe.heyishenghuo.com/mobile2/my-footprint.php?fangzhao=1
         * tasklink : http://hehe.heyishenghuo.com/mobile2/my_renwu.php?fangzhao=1
         * activlink : http://hehe.heyishenghuo.com/mobile2/app2/hehehui/index.php?fangzhao=1
         * consulink : http://hehe.heyishenghuo.com/mobile2/app2/ask-health/my-consult.php?fangzhao=1
         * sharelink : http://hehe.heyishenghuo.com/mobile2/share/index.php?fangzhao=1
         * extension : http://hehe.heyishenghuo.com/mobile2/share/index3.php?fangzhao=1
         * healthplan : http://hehe.heyishenghuo.com/mobile2/guide/health-testing.php?fangzhao=1
         * upgrade : http://hehe.heyishenghuo.com/mobile2/health_lv/health_lv.php?fangzhao=1
         * notice : http://hehe.heyishenghuo.com/mobile2/messages.php?fangzhao=1
         * myfriend : http://hehe.heyishenghuo.commobile2/recommended.php?fangzhao=1
         * bigcastlink : http://hehe.heyishenghuo.com/mobile2/app2/public/personal-page.php?fangzhao=1
         * orderLinks : ["http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list","http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=100","http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=101","http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=105","http://hehe.heyishenghuo.com/mobile2/user.php?act=order_list&composite_status=103","http://hehe.heyishenghuo.com/mobile2/my-order-sale.php"]
         * PersnalLinks : ["http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=1","http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=2","http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=3","http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=4","http://hehe.heyishenghuo.com/mobile2/my-account.php?zh=5"]
         * BlockLink : ["http://hehe.heyishenghuo.com/mobile2/my-footprint.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/my_renwu.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/share/index.php?fangzhao=1","http://hehe.heyishenghuo.commobile2/recommended.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/share/index3.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/app2/hehehui/index.php?fangzhao=1","http://hehe.heyishenghuo.com/mobile2/app2/ask-health/my-consult.php?fangzhao=1"]
         */

        private String bodylink;
        private String heartlink;
        private String moneylink;
        private String allOrder;
        private String paylink;
        private String shiplink;
        private String receiptlinnk;
        private String commentlink;
        private String customerlink;
        private String balancelink;
        private String frozenlink;
        private String easylink;
        private String kunlink;
        private String couponlink;
        private String footprint;
        private String tasklink;
        private String activlink;
        private String consulink;
        private String sharelink;
        private String extension;
        private String healthplan;
        private String upgrade;
        private String notice;
        private String myfriend;
        private String bigcastlink;
        private List<String> orderLinks;
        private List<String> PersnalLinks;
        private List<String> BlockLink;

        public String getBodylink() {
            return bodylink;
        }

        public void setBodylink(String bodylink) {
            this.bodylink = bodylink;
        }

        public String getHeartlink() {
            return heartlink;
        }

        public void setHeartlink(String heartlink) {
            this.heartlink = heartlink;
        }

        public String getMoneylink() {
            return moneylink;
        }

        public void setMoneylink(String moneylink) {
            this.moneylink = moneylink;
        }

        public String getAllOrder() {
            return allOrder;
        }

        public void setAllOrder(String allOrder) {
            this.allOrder = allOrder;
        }

        public String getPaylink() {
            return paylink;
        }

        public void setPaylink(String paylink) {
            this.paylink = paylink;
        }

        public String getShiplink() {
            return shiplink;
        }

        public void setShiplink(String shiplink) {
            this.shiplink = shiplink;
        }

        public String getReceiptlinnk() {
            return receiptlinnk;
        }

        public void setReceiptlinnk(String receiptlinnk) {
            this.receiptlinnk = receiptlinnk;
        }

        public String getCommentlink() {
            return commentlink;
        }

        public void setCommentlink(String commentlink) {
            this.commentlink = commentlink;
        }

        public String getCustomerlink() {
            return customerlink;
        }

        public void setCustomerlink(String customerlink) {
            this.customerlink = customerlink;
        }

        public String getBalancelink() {
            return balancelink;
        }

        public void setBalancelink(String balancelink) {
            this.balancelink = balancelink;
        }

        public String getFrozenlink() {
            return frozenlink;
        }

        public void setFrozenlink(String frozenlink) {
            this.frozenlink = frozenlink;
        }

        public String getEasylink() {
            return easylink;
        }

        public void setEasylink(String easylink) {
            this.easylink = easylink;
        }

        public String getKunlink() {
            return kunlink;
        }

        public void setKunlink(String kunlink) {
            this.kunlink = kunlink;
        }

        public String getCouponlink() {
            return couponlink;
        }

        public void setCouponlink(String couponlink) {
            this.couponlink = couponlink;
        }

        public String getFootprint() {
            return footprint;
        }

        public void setFootprint(String footprint) {
            this.footprint = footprint;
        }

        public String getTasklink() {
            return tasklink;
        }

        public void setTasklink(String tasklink) {
            this.tasklink = tasklink;
        }

        public String getActivlink() {
            return activlink;
        }

        public void setActivlink(String activlink) {
            this.activlink = activlink;
        }

        public String getConsulink() {
            return consulink;
        }

        public void setConsulink(String consulink) {
            this.consulink = consulink;
        }

        public String getSharelink() {
            return sharelink;
        }

        public void setSharelink(String sharelink) {
            this.sharelink = sharelink;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }

        public String getHealthplan() {
            return healthplan;
        }

        public void setHealthplan(String healthplan) {
            this.healthplan = healthplan;
        }

        public String getUpgrade() {
            return upgrade;
        }

        public void setUpgrade(String upgrade) {
            this.upgrade = upgrade;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getMyfriend() {
            return myfriend;
        }

        public void setMyfriend(String myfriend) {
            this.myfriend = myfriend;
        }

        public String getBigcastlink() {
            return bigcastlink;
        }

        public void setBigcastlink(String bigcastlink) {
            this.bigcastlink = bigcastlink;
        }

        public List<String> getOrderLinks() {
            return orderLinks;
        }

        public void setOrderLinks(List<String> orderLinks) {
            this.orderLinks = orderLinks;
        }

        public List<String> getPersnalLinks() {
            return PersnalLinks;
        }

        public void setPersnalLinks(List<String> PersnalLinks) {
            this.PersnalLinks = PersnalLinks;
        }

        public List<String> getBlockLink() {
            return BlockLink;
        }

        public void setBlockLink(List<String> BlockLink) {
            this.BlockLink = BlockLink;
        }
    }
}
