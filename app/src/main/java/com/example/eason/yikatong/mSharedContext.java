package com.example.eason.yikatong;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.example.eason.yikatong.DataModel.BASIC;
import com.example.eason.yikatong.DataModel.LOGIN;
import com.example.eason.yikatong.DataModel.Money;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eason on 2017/12/29.
 */

public class mSharedContext extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    public static Bitmap put(String url,Bitmap bitmap){
        return bitmap;
    }

    public static LOGIN login_data = null;
    public static BASIC basic_data = null;
    public static Money money_data = null;

    public static Map<String,Bitmap> bit_Cache=null;
}
