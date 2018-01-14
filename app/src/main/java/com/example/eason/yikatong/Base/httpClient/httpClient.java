package com.example.eason.yikatong.Base.httpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Auther: cheny
 * CreateDate 2017/12/24.
 * 2017.12.24 重构网络请求模块部分代码 删除Cookie部分,针对此项目优化
 */

public class httpClient {
    private static final String TAG = "httpClient";

    public static Bitmap Request_Image(String url) throws IOException {
        ResponseData responseData = Request(url, 0, null, null, 10000, 10000, false);
        if (responseData != null) {
            return DecodeByteToBitmap(responseData.responseByte);
        }
        return null;
    }

    public static void Request_Str(String url, HttpResult httpResult) throws IOException {
        ResponseData responseData = Request(url, 0, null, null, 10000, 10000, false);
        if (responseData != null) {
            httpResult.onSuccess(responseData);
            return;
        }
        httpResult.onFailed();
    }

    public static void Request_Str_ASync(final String url, final HttpResult httpResult) {
        new Thread() {
            @Override
            public void run() {
                ResponseData responseData = null;
                try {
                    responseData = Request(url, 0, null, null, 10000, 10000, false);
                } catch (IOException e) {
                    Log.w(TAG, "run: " + e.getMessage());
                }
                if (responseData != null) {
                    httpResult.onSuccess(responseData);
                    return;
                }
                httpResult.onFailed();
            }
        }.start();
    }

    public static String Request_Str(String url, String Data, String Charset) throws
            IOException {
        ResponseData responseData = Request(url, 1, Data, null, 10000, 10000, false);
        if (responseData != null) {
            return DecodeByteToString(responseData.responseByte, Charset);
        }
        return null;
    }

    public static String Request_Str(String url, String Data) throws
            IOException {
        return Request_Str(url, Data, "UTF-8");
    }

    public static ResponseData Request(String url, String Data) throws IOException {
        return Request(url, 1, Data, null, 10000, 10000, false);
    }

    public static ResponseData Request(String url, String Data, String rHead) throws IOException {
        return Request(url, 1, Data, rHead, 10000, 10000, false);
    }

    public static ResponseData Request(String url) throws IOException {
        return Request(url, 0, null, null, 10000, 10000, false);
    }

    public static ResponseData Request(String urls, int RequestCategory, String RequestData, String RequestHeader, int ConnectTimeout, int ReadTimeout
            , boolean isAllowRedirect) throws IOException {
        URL mUrl = new URL(urls);
        HttpURLConnection mHttpUrlConnection = (HttpURLConnection) mUrl.openConnection();
        String Method;
        switch (RequestCategory) {
            case 0:
                Method = "GET";
                break;
            case 1:
                Method = "POST";
                break;
            default:
                return null;
        }
        ResponseData responseData = new ResponseData();
        mHttpUrlConnection.setRequestMethod(Method);
        mHttpUrlConnection.setDoInput(true);
        if (RequestCategory == 1) {
            mHttpUrlConnection.setDoOutput(true);
        }
        mHttpUrlConnection.setConnectTimeout(ConnectTimeout);
        mHttpUrlConnection.setReadTimeout(ReadTimeout);
        mHttpUrlConnection.setInstanceFollowRedirects(isAllowRedirect);
        mHttpUrlConnection.setRequestProperty("Accept", "application/json, text/plain, */*");
        mHttpUrlConnection.setRequestProperty("Accept-Language", "zh-cn");
        mHttpUrlConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        if (RequestHeader != null) {
            String[] Headers = RequestHeader.split("\\n");
            for (String head : Headers) {
                String[] Temp = head.split(":");
                if (Temp.length >= 2) {
                    mHttpUrlConnection.setRequestProperty(Temp[0].trim(), Temp[1].trim());
                }
            }
        }

        if (RequestCategory == 1) {
            mHttpUrlConnection.setRequestProperty("Content-Length",
                    String.valueOf(RequestData.length()));
            OutputStream outputStream = mHttpUrlConnection.getOutputStream();
            outputStream.write(RequestData.getBytes());
        }

        int responseCode = 0;

        try {
            responseCode = mHttpUrlConnection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseData.responseCode = responseCode;
        switch (responseCode) {
            case 0:
                return null;
            case 200:
                responseData.responseByte = DecodeInputStreamToByte(mHttpUrlConnection.getInputStream());
                responseData.responseText = DecodeByteToString(responseData.responseByte);
                break;
            case 302:
                responseData.RedrictUrl = mHttpUrlConnection.getHeaderField("Location");
                break;
            default:
                responseData.responseByte = DecodeInputStreamToByte(mHttpUrlConnection.getInputStream());
                responseData.responseText = DecodeByteToString(responseData.responseByte);
                break;
        }
        return responseData;
    }

    private static byte[] DecodeInputStreamToByte(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len;
        byte[] bytes = new byte[1024];
        while ((len = inputStream.read(bytes)) != -1) {
            byteArrayOutputStream.write(bytes, 0, len);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String DecodeByteToString(byte[] bytes) {
        return new String(bytes);
    }

    public static String DecodeByteToString(byte[] bytes, String chaset) throws UnsupportedEncodingException {
        return new String(bytes, chaset);
    }

    public static Bitmap DecodeByteToBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static String EncodeStr(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8");
    }

    public static String EncodeStr(String str, String chaset) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, chaset);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static Bitmap BlurBitmap(Bitmap bitmap, float blur, Context context) {
        Bitmap TempBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        RenderScript renderScript = RenderScript.create(context);
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        Allocation allocationIn = Allocation.createFromBitmap(renderScript, bitmap);
        Allocation allocationOut = Allocation.createFromBitmap(renderScript, TempBitmap);
        scriptIntrinsicBlur.setRadius(blur);
        scriptIntrinsicBlur.setInput(allocationIn);
        scriptIntrinsicBlur.forEach(allocationOut);
        allocationOut.copyTo(TempBitmap);
        bitmap.recycle();
        renderScript.destroy();
        return TempBitmap;
    }

    public static Bitmap getBingImage() throws IOException {
        ResponseData s = Request("http://guolin.tech/api/bing_pic");
        s = Request(s.responseText);
        return DecodeByteToBitmap(s.responseByte);
    }
}