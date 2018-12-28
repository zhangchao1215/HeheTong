package com.example.heyikun.heheshenghuo.modle.bean;

import java.util.List;

/**
 * Created by hyk on 2017/12/14.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/12/14
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：帮助的实体类
 */
public class HelpBean {


    /**
     * status : 200
     * message : 获取帮助信息成功
     * data : [{"help_name":"账户问题","use_help":[{"help_title":"怎样获得易券？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4584"},{"help_title":"易券的有效期有多长","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4602"},{"help_title":"为什么注册不了和合生活网账号？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4603"},{"help_title":"购买商品显示\u201d到货通知\u201c如何购买","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4604"}]},{"help_name":"发票问题","use_help":[{"help_title":"怎样获得发票？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4605"},{"help_title":"下单时，未选择开发票可以补开发票？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4606"},{"help_title":"发票开错了怎么办","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4607"},{"help_title":"为什么需要提供纳税人识别号？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4608"},{"help_title":"发票的金额包含运费或活动金额吗？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4609"}]},{"help_name":"支付问题","use_help":[{"help_title":"和合生活网支持哪些付款方式","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4610"},{"help_title":"为什么交易付款不成功？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4611"}]},{"help_name":"订单问题","use_help":[{"help_title":"可以合并订单一起发货吗","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4612"},{"help_title":"如何修改订单信息","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4613"}]},{"help_name":"配送问题","use_help":[{"help_title":"什么时候发货？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4614"},{"help_title":"收到包裹漏发/错发/破损怎么办？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4615"}]},{"help_name":"售后问题","use_help":[{"help_title":"我什么时候才能申请退货？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4616"},{"help_title":"购买的商品出现质量问题如何处理？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4617"},{"help_title":"办理退货周期多久？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4618"},{"help_title":"为什么售后申请没有审核通过？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4619"},{"help_title":"当我发起退货申请时，只要我把包裹退还回来就可以获得退款吗？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4621"}]}]
     */

    private String status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * help_name : 账户问题
         * use_help : [{"help_title":"怎样获得易券？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4584"},{"help_title":"易券的有效期有多长","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4602"},{"help_title":"为什么注册不了和合生活网账号？","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4603"},{"help_title":"购买商品显示\u201d到货通知\u201c如何购买","help_link":"http://hehe.heyishenghuo.com/mobile2/article.php?id=4604"}]
         */

        private String help_name;
        private List<UseHelpBean> use_help;

        public String getHelp_name() {
            return help_name;
        }

        public void setHelp_name(String help_name) {
            this.help_name = help_name;
        }

        public List<UseHelpBean> getUse_help() {
            return use_help;
        }

        public void setUse_help(List<UseHelpBean> use_help) {
            this.use_help = use_help;
        }

        public static class UseHelpBean {
            /**
             * help_title : 怎样获得易券？
             * help_link : http://hehe.heyishenghuo.com/mobile2/article.php?id=4584
             */

            private String help_title;
            private String help_link;

            public String getHelp_title() {
                return help_title;
            }

            public void setHelp_title(String help_title) {
                this.help_title = help_title;
            }

            public String getHelp_link() {
                return help_link;
            }

            public void setHelp_link(String help_link) {
                this.help_link = help_link;
            }
        }
    }
}
