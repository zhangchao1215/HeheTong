package com.example.heyikun.heheshenghuo.modle.bean;

/**
 * Created by hyk on 2017/10/19.
 * 1：创建人hyk ： 张超
 * <p>
 * 2：创建时间2017/10/19
 * <p>
 * 3：类描述：
 * <p>
 * 4:类功能：  个人信息设置的实体类
 */

public class UserSettingBean {


    /**
     * status : 200
     * message : 获取个人资料成功
     * data : {"headimg":"http://hehe.heyishenghuo.com/data/headimg/201712/1512415673106743367.jpg","user_name":"超哥","email":"","alias":"我这人很懒，","sex":"1","height":"169公分","weight":"56公斤","blood":"O","marriage":"1","birthday":"2017-01-01","address":"昌平区","job":"0"}
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
         * headimg : http://hehe.heyishenghuo.com/data/headimg/201712/1512415673106743367.jpg
         * user_name : 超哥
         * email :
         * alias : 我这人很懒，
         * sex : 1
         * height : 169公分
         * weight : 56公斤
         * blood : O
         * marriage : 1
         * birthday : 2017-01-01
         * address : 昌平区
         * job : 0
         */

        private String headimg;
        private String user_name;
        private String email;
        private String alias;
        private String sex;
        private String height;
        private String weight;
        private String blood;
        private String marriage;
        private String birthday;
        private String address;
        private String job;

        public String getHeadimg() {
            return headimg;
        }

        public void setHeadimg(String headimg) {
            this.headimg = headimg;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getBlood() {
            return blood;
        }

        public void setBlood(String blood) {
            this.blood = blood;
        }

        public String getMarriage() {
            return marriage;
        }

        public void setMarriage(String marriage) {
            this.marriage = marriage;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }
    }
}
