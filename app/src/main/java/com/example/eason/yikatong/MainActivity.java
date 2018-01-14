package com.example.eason.yikatong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eason.yikatong.DataModel.LOGIN;
import com.example.eason.yikatong.View.request;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    EditText name, passWord;
    Button login;

    LOGIN login1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        name = findViewById(R.id.Username);
        passWord = findViewById(R.id.passWord);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread() {
                    @Override
                    public void run() {
                        String Username = name.getText().toString();
                        String pass = passWord.getText().toString();

                        String s = request.login(Username, pass);
                        Gson g = new Gson();
                        login1 = g.fromJson(s, LOGIN.class);
                        mSharedContext.login_data = login1;

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                switch (login1.msgState) {
                                    case 0:
                                        Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 1:
                                        Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                                        intent.putExtra("token", login1.token);
                                        startActivity(intent);
                                        finish();
                                        break;
                                    case 2:
                                        Toast.makeText(MainActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();
                                        break;
                                }
                            }
                        });
                    }
                }.start();
            }
        });

    }
}
