package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2018/1/9.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2018/1/9
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：
 */

public class WenxiBean {

    /**
     * code : 1
     * data : {"title":"测试数据看看是否正确","content":"<p>测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确<img src=\"http://dev-admin-api.deixian.net/upload/ueditor/20171207/1512613260896522.png\" title=\"1512613260896522.png\" alt=\"QQ截图20171206143450.png\"/><\/p>","share_url":"http://dev-service-api.deixian.net/h5/join/index.html","date":"2017-12-06 14:16","comment_number":5,"like_number":5,"Collect_number":0,"like_status":1,"collect_status":1}
     * msg :
     */

    private int code;
    private DataBean data;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * title : 测试数据看看是否正确
         * content : <p>测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确测试数据看看是否正确<img src="http://dev-admin-api.deixian.net/upload/ueditor/20171207/1512613260896522.png" title="1512613260896522.png" alt="QQ截图20171206143450.png"/></p>
         * share_url : http://dev-service-api.deixian.net/h5/join/index.html
         * date : 2017-12-06 14:16
         * comment_number : 5
         * like_number : 5
         * Collect_number : 0
         * like_status : 1
         * collect_status : 1
         */

        private String title;
        private String content;
        private String share_url;
        private String date;
        private int comment_number;
        private int like_number;
        private int Collect_number;
        private int like_status;
        private int collect_status;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getComment_number() {
            return comment_number;
        }

        public void setComment_number(int comment_number) {
            this.comment_number = comment_number;
        }

        public int getLike_number() {
            return like_number;
        }

        public void setLike_number(int like_number) {
            this.like_number = like_number;
        }

        public int getCollect_number() {
            return Collect_number;
        }

        public void setCollect_number(int Collect_number) {
            this.Collect_number = Collect_number;
        }

        public int getLike_status() {
            return like_status;
        }

        public void setLike_status(int like_status) {
            this.like_status = like_status;
        }

        public int getCollect_status() {
            return collect_status;
        }

        public void setCollect_status(int collect_status) {
            this.collect_status = collect_status;
        }
    }
}
