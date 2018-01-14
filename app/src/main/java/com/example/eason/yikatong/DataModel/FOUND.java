package com.example.eason.yikatong.DataModel;

import java.util.List;

/**
 * Created by Eason on 2017/12/29.
 */

public class FOUND {

    public Object acceptSecure;
    public int curSchoolId;
    public Object discovery;
    public int limit;
    public Object msg;
    public Object msgContent;
    public int msgState;
    public int offset;
    public Object openId;
    public int secure;
    public int time;
    public int totalPages;
    public List<DiscoveryListBean> discoveryList;

    public static class DiscoveryListBean {
        public int disClickType;
        public long disCreateTime;
        public String disDetail;
        public Object disHrefUrl;
        public int disId;
        public String disImgUrl;
        public int disIsExamined;
        public int disSchoolid;
        public String disSummary;
        public String disTitle;
        public int disUpvoteCount;
        public int isUserUpvoted;
        public int limit;
        public int offset;
        public List<SchoolListBean> schoolList;

        public static class SchoolListBean {
            public Object cityid;
            public long createtime;
            public Object customercode;
            public int isCampusTV;
            public String latitude;
            public String logo;
            public String longitudes;
            public int mdivisionid;
            public String schoolcode;
            public int schoolid;
            public String schoolname;
            public int schooltype;
            public Object schoolurl;
            public String shortname;
            public int shstatus;
            public String summary;
            public Object suspend;
            public long updatetime;
            public int useSecurity;
            public String website;
        }
    }
}
