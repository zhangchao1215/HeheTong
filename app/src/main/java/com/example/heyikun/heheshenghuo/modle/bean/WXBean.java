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

public class WXBean {


    /**
     * return_code : SUCCESS
     * return_msg : OK
     * appid : wxf88051a3634663a9
     * mch_id : 1488231382
     * device_info : WEB
     * nonce_str : ozqmpHDryZ5nLiSX
     * sign : E6753B761E58C4E53492EB3751311DEA
     * result_code : SUCCESS
     * prepay_id : wx2017102510294702a08d21980706245058
     * trade_type : APP
     */

    private String return_code;
    private String return_msg;
    private String appid;
    private String mch_id;
    private String device_info;
    private String nonce_str;
    private String sign;
    private String result_code;
    private String prepay_id;
    private String trade_type;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}
