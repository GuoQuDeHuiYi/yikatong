package com.example.eason.yikatong;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qiuchenly on 2017/12/24.
 */

public class Encrypt {
    public final static String LOGIN_ENC = "appKey=GiITvn&param={\"userName\":\"{0}\",\"password\":\"{1}\",\"uuId\":\"\",\"schoolId\":\"194\"}&secure=0&time={2}";
    public final static String GETMONEY = "appKey=GiITvn&param={}&secure=0&time={0}";
    public final static String DIALOG = "appKey=GiITvn&param={\"userId\":{0},\"parentUserName\":null}&secure=0&time={1}";
    public final static String FOUND = "appKey=GiITvn&param={\"offset\":1}&secure=0&time={0}";
    public final static String NEWS = "appKey=GiITvn&param={\"offset\":1,\"newsType\":{0},\"judgeDz\":false}&secure=0&time={1}";
    public final static String NEWS_more = "appKey=GiITvn&param={\"offset\":1,\"newsType\":{0}&secure=0&time={1}";

    public static String encrypt(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] bytes;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        bytes = md5.digest(str.getBytes("UTF-8"));

        StringBuilder strs = new StringBuilder(str.length() * 2);
        for (byte b : bytes) {
            if ((b & 0xff) < 0x10) {
                strs.append("0");
            }
            strs.append(Integer.toHexString(b & 0xff));
        }
        return strs.toString();
    }
}
