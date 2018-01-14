package com.example.eason.yikatong.DataModel;

/**
 * Created by qiuchenly on 2017/12/24.
 */

public class LOGIN {
    public  String msg,time;
    public int msgState;
    public String[] token;
    public userBaseInfo userBaseInfo;
    public userLoginInfo userLoginInfo;
    public class userLoginInfo{
        public String userName;
    }
    public class userBaseInfo{
        public String realName;

        //身份
        public int role;
        //性别
        public int sex;
        //积分
        public int vipLevel;
        //民族
        public String mzmc;
        //院系
        public String  collegeName;
        //手机号
        public String mobile;

        public int userId;
    }
//    public int msgState;
//    //1=loginOK 2=用户不存在
//    public schoolInfo schoolInfo;
//    public String[] token;
//    public userBaseInfo userBaseInfo;
//
//    class schoolInfo {
//        public String shortName, schoolName;
//        public int schoolId, schoolType;
//    }
//
//    class userBaseInfo {
//        public String classId, collegeName, mobile, mzmc, realName, sznj;
//        public int userId, vipLevel, toNextLevel;
//    }
}
