package com.example.eason.yikatong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.eason.yikatong.DataModel.Money;
import com.example.eason.yikatong.View.request;
import com.google.gson.Gson;

public class Main3Activity extends AppCompatActivity {

    TextView y_tv;
    Money money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        y_tv = findViewById(R.id.y_tv);

        final String[] token = mSharedContext.login_data.token;

        new Thread() {
            @Override
            public void run() {
                String s = request.getMoney(token[0]+"_"+token[1]);
                Gson g = new Gson();
                money = g.fromJson(s,Money.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        y_tv.setText("¥ "+money.allPurposeCardVO.cardGeneralInfo[0].value);
                    }
                });
            }
        }.start();

    }
}
