package com.example.eason.yikatong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eason.yikatong.Adapter.express_viewpager_item;
import com.example.eason.yikatong.Adapter.iexpress_item_SetEvent;
import com.example.eason.yikatong.Adapter.inews_Main4_SetEvent;
import com.example.eason.yikatong.Adapter.m4_express_recycler_view;
import com.example.eason.yikatong.Adapter.mnews_Main4_ViewPager;
import com.example.eason.yikatong.Base.httpClient.httpClient;
import com.example.eason.yikatong.DataModel.NEWS;
import com.example.eason.yikatong.View.request;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity implements inews_Main4_SetEvent, iexpress_item_SetEvent {

    ViewPager viewPager_news, viewPager_express_item;
    RecyclerView express_recycler_m4, bulletin_recycler_m4, media_shepherd_recycler_m4,
            intranet_recycler_m4, work_recycler_m4;
    ImageView return_img, img1_m4_viewpager, img2_m4_viewpager, img3_m4_viewpager;
    Spinner spinner_news;
    TextView title1_m4_viewpager, title2_m4_viewpager, title3_m4_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        viewPager_news = findViewById(R.id.news_ViewPager_main4);
        return_img = findViewById(R.id.return_img);
        spinner_news = findViewById(R.id.spinner_news);

        String[] mItems_title = getResources().getStringArray(R.array.title_news_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mItems_title);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_news.setAdapter(adapter);
        spinner_news.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] title_news_spinner = getResources().getStringArray(R.array.title_news_spinner);
                Toast.makeText(Main4Activity.this, title_news_spinner[i], Toast.LENGTH_SHORT).show();
                viewPager_news.setCurrentItem(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        List<View> viewList = new ArrayList<>();
        viewList.add(LayoutInflater.from(this).inflate(R.layout.m4_news_express, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.m4_news_media_shepherd, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.m4_news_bulletin, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.m4_news_intranet_notice, null));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.m4_news_work, null));

        viewPager_news.setAdapter(new mnews_Main4_ViewPager(viewList, this));
    }

    @Override
    public void main4_setView(View v, int p) {
        switch (p) {
            case 0:
                viewPager_express_item = v.findViewById(R.id.express_viewpager_m4);
                List<View> viewList1 = new ArrayList<>();

                viewList1.add(LayoutInflater.from(this).inflate(R.layout.express_viewpager_item_img1, null));
                viewList1.add(LayoutInflater.from(this).inflate(R.layout.express_viewpager_item_img2, null));
                viewList1.add(LayoutInflater.from(this).inflate(R.layout.express_viewpager_item_img3, null));

                viewPager_express_item.setAdapter(new express_viewpager_item(viewList1, Main4Activity.this));

                express_recycler_m4 = v.findViewById(R.id.express_recycler_m4);
                express_recycler_m4.setLayoutManager(new LinearLayoutManager(Main4Activity.this));
                express_recycler_m4.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                set_recycler_m4(341, express_recycler_m4);
                break;
            case 1:
                media_shepherd_recycler_m4 = v.findViewById(R.id.media_shepherd_recycler_m4);
                media_shepherd_recycler_m4.setLayoutManager(new LinearLayoutManager(Main4Activity.this));
                media_shepherd_recycler_m4.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                set_recycler_m4(342, media_shepherd_recycler_m4);
                break;
            case 2:
                bulletin_recycler_m4 = v.findViewById(R.id.bulletin_recycler_m4);
                bulletin_recycler_m4.setLayoutManager(new LinearLayoutManager(Main4Activity.this));
                bulletin_recycler_m4.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                set_recycler_m4(343, bulletin_recycler_m4);
                break;
            case 3:
                intranet_recycler_m4 = v.findViewById(R.id.intranet_recycler_m4);
                intranet_recycler_m4.setLayoutManager(new LinearLayoutManager(Main4Activity.this));
                intranet_recycler_m4.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                set_recycler_m4(352, intranet_recycler_m4);
                break;
            case 4:
                work_recycler_m4 = v.findViewById(R.id.work_recycler_m4);
                work_recycler_m4.setLayoutManager(new LinearLayoutManager(Main4Activity.this));
                work_recycler_m4.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                set_recycler_m4(353, work_recycler_m4);
                break;
        }

    }

    public void set_recycler_m4(final int num, final RecyclerView recyclerView) {
        new Thread() {
            @Override
            public void run() {
                String[] token = mSharedContext.login_data.token;
                String s = request.NEWS(num, token[0] + "_" + token[1]);
                Gson gson = new Gson();
                final NEWS news = gson.fromJson(s, NEWS.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<NEWS.NewsListBean> newsListBeanList = new ArrayList<>();
                        if (num == 341) {
                            for (int i = 3; i < news.newsList.size(); i++) {
                                newsListBeanList.add(news.newsList.get(i));
                            }
                        } else {
                            for (int i = 0; i < news.newsList.size(); i++) {
                                newsListBeanList.add(news.newsList.get(i));
                            }
                        }
                        recyclerView.setAdapter(new m4_express_recycler_view(newsListBeanList));
                    }
                });
            }
        }.start();
    }

    Bitmap bitmap = null;
    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void express_item_SetEvent(View view, int p) {
        switch (p) {
            case 0:
                img1_m4_viewpager = view.findViewById(R.id.img1_m4_viewpager);
                title1_m4_viewpager = view.findViewById(R.id.title1_m4_viewpager);

                setViewPager_img(img1_m4_viewpager, title1_m4_viewpager, 0);
                break;
            case 1:
                img2_m4_viewpager = view.findViewById(R.id.img2_m4_viewpager);
                title2_m4_viewpager = view.findViewById(R.id.title2_m4_viewpager);

                setViewPager_img(img2_m4_viewpager, title2_m4_viewpager, 1);
                break;
            case 2:
                img3_m4_viewpager = view.findViewById(R.id.img3_m4_viewpager);
                title3_m4_viewpager = view.findViewById(R.id.title3_m4_viewpager);

                setViewPager_img(img3_m4_viewpager, title3_m4_viewpager, 2);
                break;
        }

    }

    public void setViewPager_img(final ImageView img, final TextView Title, final int zhi) {
        new Thread() {
            @Override
            public void run() {
                String[] token = mSharedContext.login_data.token;
                String s = request.NEWS(341, token[0] + "_" + token[1]);
                Gson gson = new Gson();
                final NEWS news = gson.fromJson(s, NEWS.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final List<NEWS.NewsListBean> newsListBeanList = new ArrayList<>();
                        newsListBeanList.addAll(news.newsList);
                        final NEWS.NewsListBean data = newsListBeanList.get(zhi);
                        Title.setText(data.title);

                        new Thread() {
                            @Override
                            public void run() {
                                try {
                                    if (zhi == 0)
                                        bitmap = httpClient.Request_Image((String) data.imgNameList.get(0));
                                    else
                                        bitmap = httpClient.Request_Image("http://lantuservice.com/mobilecampus/" + data.imgNameList.get(0));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        img.setImageBitmap(bitmap);
                                    }
                                });
                            }
                        }.start();
                    }
                });
            }
        }.start();
    }
}
