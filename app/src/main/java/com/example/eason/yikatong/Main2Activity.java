package com.example.eason.yikatong;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.eason.yikatong.Adapter.iHome_title_item;
import com.example.eason.yikatong.Adapter.iPersonal_RecyclerView_item;
import com.example.eason.yikatong.Adapter.iSetViewEvent;
import com.example.eason.yikatong.Adapter.inews_SetEvent;
import com.example.eason.yikatong.Adapter.mFound_RecycelrView;
import com.example.eason.yikatong.Adapter.mHome_news_ViewPager;
import com.example.eason.yikatong.Adapter.mHome_title_RecyclerView;
import com.example.eason.yikatong.Adapter.mNews_All_RecyclerView;
import com.example.eason.yikatong.Adapter.mPersonal_RecyclerView;
import com.example.eason.yikatong.Adapter.mViewPagerAdapter;
import com.example.eason.yikatong.Base.httpClient.httpClient;
import com.example.eason.yikatong.DataModel.BASIC;
import com.example.eason.yikatong.DataModel.FOUND;
import com.example.eason.yikatong.DataModel.NEWS;
import com.example.eason.yikatong.Utils.mUtilsJ;
import com.example.eason.yikatong.View.request;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity implements iSetViewEvent,
        AdapterView.OnItemClickListener, iPersonal_RecyclerView_item,
        iHome_title_item, inews_SetEvent {

    ViewPager viewPager, viewPager_news;

    GridView gridView;

    BottomNavigationView BN;

    List<Map<String, Object>> list;

    SimpleAdapter sim_adapter;

    RecyclerView recyclerView_personal, recyclerView_found, recyclerView_home,
            recyclerView_news_all, bulletin_recycler, intranet_recycler, work_recycler;

    TextView personal_jifen, personal_name, dailog_id, personal_lv, dailog_name,
            dailog_sex, dailog_mz, dailog_yx, dailog_birthday, dailog_phone,
            express_title_tv, express_tv1, express_time1, express_time2,
            express_tv2, home_more_tv;

    ImageView express_title_img, express_img1, express_img2;

    int[] a = {R.drawable.ic_local_atm_black_24dp, R.drawable.ic_explore_black_24dp, R.drawable.ic_explore_black_24dp,
            R.drawable.ic_explore_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_explore_black_24dp};

    String[] string = {"报名缴费", "课程表", "故障维修", "图书馆", "一卡通", "课堂考勤"};

    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        viewPager = findViewById(R.id.mViewPager);
        BN = findViewById(R.id.BN);

        List<View> list = new ArrayList<>();
        list.add(LayoutInflater.from(this).inflate(R.layout.mviewpager_home, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.mviewpager_notice, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.mviewpager_found, null));
        list.add(LayoutInflater.from(this).inflate(R.layout.mviewpager_personal, null));

        viewPager.setAdapter(new mViewPagerAdapter(list, this));

        BN.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.aaa:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.aaa1:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.aaa2:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.aaa3:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return true;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                BN.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void ViewEvent(View v, int p) {
        switch (p) {
            case 0:
                gridView = v.findViewById(R.id.mgrid);
                list = new ArrayList<Map<String, Object>>();
                getData();
                String[] from = {"image", "text"};
                int[] to = {R.id.gird_img, R.id.gird_tv};
                sim_adapter = new SimpleAdapter(this, list, R.layout.mgird_item, from, to);
                gridView.setAdapter(sim_adapter);
                gridView.setOnItemClickListener(this);

                String[] title = {"学院快讯", "媒体牧院", "学院公告", "内网通知", "工作安排"};
                recyclerView_home = v.findViewById(R.id.home_title_recyclerview);
                recyclerView_home.setLayoutManager(new LinearLayoutManager(Main2Activity.this, 0, false));
                recyclerView_home.setAdapter(new mHome_title_RecyclerView(title, this));

                viewPager_news = v.findViewById(R.id.home_ViewPager);
                List<View> viewList = new ArrayList<>();
                viewList.add(LayoutInflater.from(this).inflate(R.layout.news_express, null));
                viewList.add(LayoutInflater.from(this).inflate(R.layout.news_media_shepherd, null));
                viewList.add(LayoutInflater.from(this).inflate(R.layout.news_bulletin, null));
                viewList.add(LayoutInflater.from(this).inflate(R.layout.news_intranet_notice, null));
                viewList.add(LayoutInflater.from(this).inflate(R.layout.news_work, null));

                viewPager_news.setAdapter(new mHome_news_ViewPager(viewList, this));

                home_more_tv = v.findViewById(R.id.home_more_tv);
                home_more_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent getIntent = getIntent();
                        String[] token = getIntent.getStringArrayExtra("token");
                        Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                        intent.putExtra("token", token);
                        startActivity(intent);
                    }
                });
                break;
            case 1:
                break;
            case 2:
                recyclerView_found = v.findViewById(R.id.found_recyclerview);
                new Thread() {
                    @Override
                    public void run() {
                        String[] token = mSharedContext.login_data.token;
                        String s = request.FOUND(token[0] + "_" + token[1]);
                        Gson gson = new Gson();
                        final FOUND found = gson.fromJson(s, FOUND.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                List<FOUND.DiscoveryListBean> listBeans = new ArrayList<>();
                                listBeans.addAll(found.discoveryList);
                                recyclerView_found.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                                recyclerView_found.addItemDecoration(new RecyclerView.ItemDecoration() {
                                    @Override
                                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                                        outRect.bottom = 15;
                                    }
                                });
                                recyclerView_found.setAdapter(new mFound_RecycelrView(listBeans));
                            }
                        });
                    }
                }.start();
                break;
            case 3:
                recyclerView_personal = v.findViewById(R.id.personal_recyclerview);
                String[] strings = {"个人资料", "手机绑定", "解绑设备", "推荐人账号"};
                recyclerView_personal.setLayoutManager(new LinearLayoutManager(this));
                recyclerView_personal.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                String name = mSharedContext.login_data.userBaseInfo.realName;
                int vipLevel = mSharedContext.login_data.userBaseInfo.vipLevel;

                personal_jifen = v.findViewById(R.id.personal_jifen);
                personal_name = v.findViewById(R.id.personal_name);
                personal_lv = v.findViewById(R.id.personal_lv);

                personal_name.setText(name);
                personal_jifen.setText("积分 " + vipLevel);

                recyclerView_personal.setAdapter(new mPersonal_RecyclerView(strings, this));
                break;
        }
    }

    public List<Map<String, Object>> getData() {
        for (int i = 0; i < a.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", a[i]);
            map.put("text", string[i]);
            list.add(map);
        }
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (a[i]) {
            case R.drawable.ic_home_black_24dp:
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void item_Click(int p) {
        switch (p) {
            case 0:
                new Thread() {
                    @Override
                    public void run() {
                        final Intent getintent = getIntent();
                        final String[] token = mSharedContext.login_data.token;
                        int userid = getintent.getIntExtra("userId", 0);
                        String s = request.DIALOG(userid, token[0] + "_" + token[1]);
                        Gson gson = new Gson();
                        final BASIC basic = gson.fromJson(s, BASIC.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                                View view = LayoutInflater.from(Main2Activity.this).inflate(R.layout.personal_dailog, null);
                                dailog_id = view.findViewById(R.id.dailog_id);
                                dailog_name = view.findViewById(R.id.dailog_name);
                                dailog_sex = view.findViewById(R.id.dailog_sex);
                                dailog_mz = view.findViewById(R.id.dailog_mz);
                                dailog_yx = view.findViewById(R.id.dailog_yx);
                                dailog_birthday = view.findViewById(R.id.dailog_birthday);
                                dailog_phone = view.findViewById(R.id.dailog_phone);

                                dailog_id.setText("学号：" + basic.userLoginInfo.userName);
                                dailog_name.setText("姓名：" + basic.userBaseInfo.realName);
                                int sex = basic.userBaseInfo.sex;
                                if (sex == 1)
                                    dailog_sex.setText("性别：男");
                                else
                                    dailog_sex.setText("性别：女");
                                dailog_mz.setText("民族：" + basic.userBaseInfo.mzmc);
                                dailog_yx.setText("院系：" + basic.userBaseInfo.collegeName);
                                dailog_birthday.setText("生日：" + mUtilsJ.getRealTime(basic.userBaseInfo.brithday));
                                dailog_phone.setText("手机号：" + basic.userBaseInfo.mobile);
                                dialog.setView(view);
                                dialog.show();
                            }
                        });
                    }
                }.start();
                break;
        }
    }

    @Override
    public void Home_title(int p) {
        viewPager_news.setCurrentItem(p);
    }

    Bitmap bitmap = null;
    Bitmap bitmap1 = null;
    Bitmap bitmap2 = null;
    Handler handler = new Handler(Looper.getMainLooper());
    int[] id = {R.id.media_shepherd_recyclerview, R.id.bulletin_recycler,
            R.id.intranet_recycler, R.id.work_recycler};

    @Override
    public void newsSetEvent(View v, final int p) {
        switch (p) {
            case 0:
                express_title_img = v.findViewById(R.id.express_title_img);
                express_title_tv = v.findViewById(R.id.express_title_tv);
                express_img1 = v.findViewById(R.id.express_img1);
                express_tv1 = v.findViewById(R.id.express_tv1);
                express_tv2 = v.findViewById(R.id.express_tv2);
                express_img2 = v.findViewById(R.id.express_img2);
                express_time1 = v.findViewById(R.id.express_time1);
                express_time2 = v.findViewById(R.id.express_time2);

                new Thread() {
                    @Override
                    public void run() {
                        final String[] token = mSharedContext.login_data.token;
                        int newType = 341;
                        String s = request.NEWS(newType, token[0] + "_" + token[1]);
                        Gson gson = new Gson();
                        NEWS news = gson.fromJson(s, NEWS.class);
                        final List<NEWS.NewsListBean> newsListBeanList = new ArrayList<>();
                        newsListBeanList.addAll(news.newsList);
                        final NEWS.NewsListBean data0 = newsListBeanList.get(0);
                        final NEWS.NewsListBean data1 = newsListBeanList.get(1);
                        final NEWS.NewsListBean data2 = newsListBeanList.get(2);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            bitmap = httpClient.Request_Image((String) data0.imgNameList.get(0));
                                            if (data1.imgNameList.size() == 0)
                                                bitmap1 = null;
                                            else
                                                bitmap1 = httpClient.Request_Image("http://lantuservice.com/mobilecampus/" + data1.imgNameList.get(0));
                                            if (data2.imgNameList.size() == 0)
                                                bitmap2 = null;
                                            else
                                                bitmap2 = httpClient.Request_Image("http://lantuservice.com/mobilecampus/" + data2.imgNameList.get(0));
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        handler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                express_title_img.setImageBitmap(bitmap);
                                                if (data1.imgNameList.size() == 0)
                                                    express_img1.setVisibility(View.GONE);
                                                else
                                                    express_img1.setImageBitmap(bitmap1);
                                                if (data2.imgNameList.size() == 0)
                                                    express_img2.setVisibility(View.GONE);
                                                else
                                                    express_img2.setImageBitmap(bitmap2);
                                            }
                                        });

                                    }
                                }.start();
                                express_title_tv.setText(data0.title);
                                express_tv1.setText(data1.title);
                                express_tv2.setText(data2.title);
                                express_time1.setText(simpleDateFormat.format(data1.createTime) + "  " + data1.realName);
                                express_time2.setText(simpleDateFormat.format(data2.createTime) + "  " + data2.realName);
                            }
                        });
                    }
                }.start();
                break;
            case 1:
                recyclerView_news_all = v.findViewById(R.id.media_shepherd_recyclerview);
                recyclerView_news_all.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                recyclerView_news_all.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                news_all(342, recyclerView_news_all);
                break;
            case 2:
                bulletin_recycler = v.findViewById(R.id.bulletin_recycler);
                bulletin_recycler.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                bulletin_recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                news_all(343, bulletin_recycler);
                break;
            case 3:
                intranet_recycler = v.findViewById(R.id.intranet_recycler);
                intranet_recycler.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                intranet_recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                news_all(352, intranet_recycler);
                break;
            case 4:
                work_recycler = v.findViewById(R.id.work_recycler);
                work_recycler.setLayoutManager(new LinearLayoutManager(Main2Activity.this));
                work_recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
                    @Override
                    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                        outRect.bottom = 15;
                    }
                });
                news_all(353, work_recycler);
                break;
        }
    }

    public void news_all(final int num, final RecyclerView name) {
        new Thread() {
            @Override
            public void run() {
                final String[] token = mSharedContext.login_data.token;
                int newType = num;
                String s = request.NEWS(newType, token[0] + "_" + token[1]);
                Gson gson = new Gson();
                final NEWS news = gson.fromJson(s, NEWS.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<NEWS.NewsListBean> newsListBeanList = new ArrayList<>();
                        for (int a = 0; a < 3; a++) {
                            newsListBeanList.add(news.newsList.get(a));
                        }
                        name.setAdapter(new mNews_All_RecyclerView(newsListBeanList));
                    }
                });
            }
        }.start();
    }
}