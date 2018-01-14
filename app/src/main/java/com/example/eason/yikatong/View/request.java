package com.example.eason.yikatong.View;


import com.example.eason.yikatong.Base.httpClient.ResponseData;
import com.example.eason.yikatong.Base.httpClient.httpClient;
import com.example.eason.yikatong.Encrypt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qiuchenly on 2017/12/24.
 */

public class request {
    private static final String TAG = "request";
    private static final String wl = "http://app.jsahvc.edu.cn";

    public static String IMGHEAD = "https://mobilecampus.oss.aliyuncs.com/";

    public static String login(String user, String pass) {
        String u = wl + "/baseCampus/login/login.do";
        long time = System.currentTimeMillis();
        String data = "";
        try {
            String enc = Encrypt.LOGIN_ENC;
            enc = enc.replace("{0}", user);
            enc = enc.replace("{1}", pass);
            enc = enc.replace("{2}", time + "");
            data = "{\"appKey\":\"GiITvn\",\"param\":\"{\\\"userName\\\":\\\"" + user + "\\\",\\\"password\\\":\\\"" + pass + "\\\",\\\"uuId\\\":\\\"\\\",\\\"schoolId\\\":\\\"194\\\"}\",\"time\":" + time + ",\"secure\":0,\"sign\":\"" + Encrypt.encrypt(enc) + "\"}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseData ret = new ResponseData();
        try {
            ret = httpClient.Request(u, data, "Host: app.jsahvc.edu.cn\n" +
                    "Accept: application/json, text/plain, */*\n" +
                    "Accept-Language: cn\n" +
                    "User-Agent: Mozilla/5.0 (Linux; Android 7.0; MI 5s Build/NRD90M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 lantuMobilecampus lantuMC\n" +
                    "token:\n" +
                    "Content-Type: application/json;charset=UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.responseText;
    }

    public static String getMoney(String token) {
        String u = wl + "/infoCampus/playCampus/getAllPurposeCard.do";
        long time = System.currentTimeMillis();
        String data = "";
        try {
            String enc = Encrypt.GETMONEY;
            enc = enc.replace("{0}", time + "");
            data = "{\"appKey\":\"GiITvn\",\"param\":\"{}\",\"time\":" + time + ",\"secure\":0,\"sign\":\"" + Encrypt.encrypt(enc) + "\"}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseData ret = new ResponseData();
        try {
            ret = httpClient.Request(u, data, "Host: app.jsahvc.edu.cn\n" +
                    "Accept: application/json, text/plain, */*\n" +
                    "Accept-Language: cn\n" +
                    "User-Agent: Mozilla/5.0 (Linux; Android 6.0.1; OPPO R9s Build/MMB29M) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 lantuMobilecampus lantuMC\n" +
                    "token:" + token + "\n" +
                    "Content-Type: application/json;charset=UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.responseText;
    }

    public static String DIALOG(int userid, String token) {
        String u = wl + "/baseCampus/user/getUserInfo.do";
        long time = System.currentTimeMillis();
        String data = "";
        try {
            String enc = Encrypt.DIALOG;
            enc = enc.replace("{0}", userid + "");
            enc = enc.replace("{1}", time + "");
            data = "{\"appKey\":\"GiITvn\",\"param\":\"{\\\"userId\\\":" + userid + ",\\\"parentUserName\\\":null}\",\"time\":" + time + ",\"secure\":0,\"sign\":\"" + Encrypt.encrypt(enc) + "\"}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseData ret = new ResponseData();
        try {
            ret = httpClient.Request(u, data, "Host: app.jsahvc.edu.cn\n" +
                    "Accept: application/json, text/plain, */*\n" +
                    "Accept-Language: cn\n" +
                    "User-Agent: Mozilla/5.0 (Linux; Android 7.0; EVA-AL10 Build/HUAWEIEVA-AL10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 lantuMobilecampus lantuMC\n" +
                    "token:" + token + "\n" +
                    "Content-Type: application/json;charset=UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.responseText;
    }

    public static String FOUND(String token) {
        String u = wl + "/baseCampus/discovery/list.do";
        long time = System.currentTimeMillis();
        String data = "";
        try {
            String enc = Encrypt.FOUND;
            enc = enc.replace("{0}", time + "");
            data = "{\"appKey\":\"GiITvn\",\"param\":\"{\\\"offset\\\":1}\",\"time\":" + time + ",\"secure\":0,\"sign\":\"" + Encrypt.encrypt(enc) + "\"}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseData ret = new ResponseData();
        try {
            ret = httpClient.Request(u, data, "Host: app.jsahvc.edu.cn\n" +
                    "Accept: application/json, text/plain, */*\n" +
                    "Accept-Language: cn\n" +
                    "User-Agent: Mozilla/5.0 (Linux; Android 7.0; EVA-AL10 Build/HUAWEIEVA-AL10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 lantuMobilecampus lantuMC\n" +
                    "token:" + token + "\n" +
                    "Content-Type: application/json;charset=UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.responseText;
    }

    public static String NEWS(int newType, String token) {
        String u = wl + "/newsCampus/news/getSchoolNews.do";
        long time = System.currentTimeMillis();
        String data = "";
        try {
            String enc = Encrypt.NEWS;
            enc = enc.replace("{0}", newType + "");
            enc = enc.replace("{1}", time + "");
            data = "{\"appKey\":\"GiITvn\",\"param\":\"{\\\"offset\\\":1,\\\"newsType\\\":" + newType + ",\\\"judgeDz\\\":false}\",\"time\":" + time + ",\"secure\":0,\"sign\":\"" + Encrypt.encrypt(enc) + "\"}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseData ret = new ResponseData();
        try {
            ret = httpClient.Request(u, data, "Host: app.jsahvc.edu.cn\n" +
                    "Connection: keep-alive\n" +
                    "Content-Length: 153\n" +
                    "Accept: application/json, text/plain, */*\n" +
                    "Origin: file://\n" +
                    "Accept-Language: cn\n" +
                    "User-Agent: Mozilla/5.0 (Linux; Android 7.0; EVA-AL10 Build/HUAWEIEVA-AL10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 lantuMobilecampus lantuMC\n" +
                    "token:" + token + "\n" +
                    "Content-Type: application/json;charset=UTF-8\n" +
                    "Accept-Encoding: gzip, deflate");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.responseText;
    }

    public static String NEWS_more(int newType, String token) {
        String u = "http://app.jsahvc.edu.cn/newsCampus/news/getSchoolNews.do";
        long time = System.currentTimeMillis();
        String data = "";
        try {
            String enc = Encrypt.NEWS_more;
            enc = enc.replace("{0}", newType + "");
            enc = enc.replace("{1}", time + "");
            data = "{\"appKey\":\"GiITvn\",\"param\":\"{\\\"offset\\\":1,\\\"newsType\\\":" + newType + "}\",\"time\":" + time + ",\"secure\":0,\"sign\":\"" + Encrypt.encrypt(enc) + "\"}";
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ResponseData ret = new ResponseData();
        try {
            ret = httpClient.Request(u, data, "Host: app.jsahvc.edu.cn\n" +
                    "Connection: keep-alive\n" +
                    "Content-Length: 135\n" +
                    "Accept: application/json, text/plain, */*\n" +
                    "Origin: file://\n" +
                    "Accept-Language: cn\n" +
                    "User-Agent: Mozilla/5.0 (Linux; Android 7.0; EVA-AL10 Build/HUAWEIEVA-AL10) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Crosswalk/18.48.477.13 Mobile Safari/537.36 lantuMobilecampus lantuMC\n" +
                    "token:" + token + "\n" +
                    "Content-Type: application/json;charset=UTF-8\n" +
                    "Accept-Encoding: gzip, deflate");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret.responseText;
    }
}