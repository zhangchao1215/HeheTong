package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/12/19.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/19
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：经络文章的实体类
 */

public class JingluoArticleBean {


    /**
     * status : 200
     * message : 请求成功
     * channels : <p>足太阴脾经当令</p><p>巳时9:00-11:00</p><p><br/></p><p>十二时辰对应人体的十二经络，当轮到每一脏腑经脉当令时，其余经脉都会全力支持，所以每天每个脏腑经脉都会得到一次良好的保养和修复时间。</p><p><br/></p><p>中医说脾胃是“后天之本”，也是这个道理。因为人生下来活下去靠的就是食物，而脾胃就是负责食物的消化吸收，把食物变现气血及营养，来儒养全身，四肢百骸，身体气血能够正常的运转，人就会健康的生活工作。此时脾经失调主要与运化功能失调有关。</p><p><br/></p><p>对于维持消化功能及将食物化为气血起着重要的作用。若脾经常出现问题，会出现腹胀、 便溏、 下痢、胃脘痛、 嗳气、身重无力等。此外，舌根强痛，下肢内侧肿胀等均显示脾经失调。</p><p><br/></p>
     */

    private String status;
    private String message;
    private String channels;

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

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }
}
