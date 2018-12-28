package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/10/25.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/25
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class AliBean {


    /**
     * orderString : alipay_sdk=alipay-sdk-php-20161101&app_id=2017090608586413&biz_content=%7B%22body%22%3A%22%5Cu6211%5Cu662f%5Cu6d4b%5Cu8bd5%5Cu6570%5Cu636e%22%2C%22subject%22%3A%22App%5Cu652f%5Cu4ed8%5Cu6d4b%5Cu8bd5%22%2C%22out_trade_no%22%3A%22appzfb20173934%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Ftest2.heyishenghuo%2Frespond.php&sign_type=RSA2&timestamp=2017-10-25+17%3A34%3A27&version=1.0&sign=HrnjOfIxVMoTcffVTwBLxOSN7fetyKR1PgHLKKsuH3YO4Ha%2FhLuDA8Npb64cbM8vEkzWM%2B2hejEYOhlpMIRAxRJbBKVImrhskDVLlCre00fnlQFINXIaoRlWFNx%2BPm9YxScRsLZSZSngqBVmF61upbfmxlZTg7rW1B2yiwDwSLPDoKpYCNysdAveSbAjUzI7kbm4rA1latc7TEeZfuy8B%2FPDOcm%2FcAQD2t4Obgl%2Bv13p4LhBlyTdntLgR%2BFWFwuSVdyFoyiOX9YQm1Qy23pYpUbmRWIfr2CxN2uqdb5GNdwAp2ay%2Fuzp%2BzBaKUhUPo7VCFm9PbV7HPE5Cup0y%2Br6eA%3D%3D
     */

    private String orderString;
    private String status;
    private String prepayid;

    public String getStatus() {
        return status;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getOrderString() {
        return orderString;
    }

    public void setOrderString(String orderString) {
        this.orderString = orderString;
    }
}
