package com.example.heyikun.heheshenghuo.modle.bean.shop;

/**
 * Created by hyk on 2017/11/29.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/11/29
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能： 获取支付数据的实体类
 */

public class ShopPayBean {


    /**
     * status : 200
     * message : 获取订单支付信息
     * data : {"your_surplus":"0.00","pay_points":"354","order_integral":35,"yb_money":319,"goods_amount":"210.80","cost_total":175.8,"pay_integral":35,"payable":175.8}
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
         * your_surplus : 0.00
         * pay_points : 354
         * order_integral : 35
         * yb_money : 319
         * goods_amount : 210.80
         * cost_total : 175.8
         * pay_integral : 35
         * payable : 175.8
         */

        private String your_surplus;
        private String pay_points;
        private int order_integral;
        private int yb_money;
        private String goods_amount;
        private double cost_total;
        private int pay_integral;
        private double payable;

        public String getYour_surplus() {
            return your_surplus;
        }

        public void setYour_surplus(String your_surplus) {
            this.your_surplus = your_surplus;
        }

        public String getPay_points() {
            return pay_points;
        }

        public void setPay_points(String pay_points) {
            this.pay_points = pay_points;
        }

        public int getOrder_integral() {
            return order_integral;
        }

        public void setOrder_integral(int order_integral) {
            this.order_integral = order_integral;
        }

        public int getYb_money() {
            return yb_money;
        }

        public void setYb_money(int yb_money) {
            this.yb_money = yb_money;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public double getCost_total() {
            return cost_total;
        }

        public void setCost_total(double cost_total) {
            this.cost_total = cost_total;
        }

        public int getPay_integral() {
            return pay_integral;
        }

        public void setPay_integral(int pay_integral) {
            this.pay_integral = pay_integral;
        }

        public double getPayable() {
            return payable;
        }

        public void setPayable(double payable) {
            this.payable = payable;
        }
    }
}
