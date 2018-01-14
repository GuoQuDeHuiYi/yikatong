package com.example.eason.yikatong.DataModel;

import java.util.List;

/**
 * Created by Eason on 2017/12/28.
 */

public class BASIC {

    /**
     * acceptSecure : null
     * curSchoolId : 0
     * msg : null
     * msgContent : null
     * msgState : 1
     * openId : null
     * secure : 0
     * time : 0
     * userBaseInfo : {"admitNumber":null,"brithday":891648000,"campus":null,"childName":null,"cityId":0,"classId":"","classStr":"农业信息学院","college":"0","collegeName":"农业信息学院","deleted":0,"depart":null,"divisionId":0,"domainId":0,"domainName":"","dqztm":1,"endValue":"1000","exp":0,"fullImage":"","grade":null,"headImage":"","inviteCode":"201513218","jg":null,"majorId":"0801","mobile":"15950568556","mobileList":[],"mobileSecret":1,"mzmc":"汉族","nickName":"","officeId":0,"officeName":"","parentName":null,"publishDomain":1,"publishOffice":1,"qq":"","realName":"林城城","rfid":null,"role":8,"roleLevel":0,"schoolId":194,"sex":1,"startValue":"501","summary":"","sznj":"","tel":"","toNextLevel":220,"userId":1946976,"vipLevel":780,"vipLevelName":"3","xqah":null,"zymc":""}
     * userLoginInfo : {"hasBind":false,"loginMobile":"","mail":"201613317@jsahvc.edu.cn","parentUserName":"201613317","phone":null,"pid":null,"regFrom":"信息中心","schoolCard":null,"securityPassword":null,"sex":1,"tokenValue":null,"userId":1946976,"userName":"201613317","wxUserId":null}
     */

    public String acceptSecure;
    public int curSchoolId;
    public String msg;
    public String msgContent;
    public int msgState;
    public String openId;
    public int secure;
    public int time;
    public UserBaseInfoBean userBaseInfo;
    public UserLoginInfoBean userLoginInfo;

    public static class UserBaseInfoBean {
        /**
         * admitNumber : null
         * brithday : 891648000
         * campus : null
         * childName : null
         * cityId : 0
         * classId :
         * classStr : 农业信息学院
         * college : 0
         * collegeName : 农业信息学院
         * deleted : 0
         * depart : null
         * divisionId : 0
         * domainId : 0
         * domainName :
         * dqztm : 1
         * endValue : 1000
         * exp : 0
         * fullImage :
         * grade : null
         * headImage :
         * inviteCode : 201513218
         * jg : null
         * majorId : 0801
         * mobile : 15950568556
         * mobileList : []
         * mobileSecret : 1
         * mzmc : 汉族
         * nickName :
         * officeId : 0
         * officeName :
         * parentName : null
         * publishDomain : 1
         * publishOffice : 1
         * qq :
         * realName : 林城城
         * rfid : null
         * role : 8
         * roleLevel : 0
         * schoolId : 194
         * sex : 1
         * startValue : 501
         * summary :
         * sznj :
         * tel :
         * toNextLevel : 220
         * userId : 1946976
         * vipLevel : 780
         * vipLevelName : 3
         * xqah : null
         * zymc :
         */

        public String admitNumber;
        public int brithday;
        public String campus;
        public String childName;
        public int cityId;
        public String classId;
        public String classStr;
        public String college;
        public String collegeName;
        public int deleted;
        public String depart;
        public int divisionId;
        public int domainId;
        public String domainName;
        public int dqztm;
        public String endValue;
        public int exp;
        public String fullImage;
        public String grade;
        public String headImage;
        public String inviteCode;
        public String jg;
        public String majorId;
        public String mobile;
        public int mobileSecret;
        public String mzmc;
        public String nickName;
        public int officeId;
        public String officeName;
        public String parentName;
        public int publishDomain;
        public int publishOffice;
        public String qq;
        public String realName;
        public String rfid;
        public int role;
        public int roleLevel;
        public int schoolId;
        public int sex;
        public String startValue;
        public String summary;
        public String sznj;
        public String tel;
        public int toNextLevel;
        public int userId;
        public int vipLevel;
        public String vipLevelName;
        public String xqah;
        public String zymc;
        public List<String> mobileList;
    }

    public static class UserLoginInfoBean {
        /**
         * hasBind : false
         * loginMobile :
         * mail : 201613317@jsahvc.edu.cn
         * parentUserName : 201613317
         * phone : null
         * pid : null
         * regFrom : 信息中心
         * schoolCard : null
         * securityPassword : null
         * sex : 1
         * tokenValue : null
         * userId : 1946976
         * userName : 201613317
         * wxUserId : null
         */

        public boolean hasBind;
        public String loginMobile;
        public String mail;
        public String parentUserName;
        public String phone;
        public String pid;
        public String regFrom;
        public String schoolCard;
        public String securityPassword;
        public int sex;
        public String tokenValue;
        public int userId;
        public String userName;
        public String wxUserId;
    }
}
