package com.example.eason.yikatong.Adapter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eason.yikatong.Base.httpClient.httpClient;
import com.example.eason.yikatong.DataModel.NEWS;
import com.example.eason.yikatong.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Eason on 2018/1/8.
 */

public class m4_express_recycler_view extends RecyclerView.Adapter<m4_express_recycler_view.VH> {

    List<NEWS.NewsListBean> newsList;

    Handler handler;

    public m4_express_recycler_view(List<NEWS.NewsListBean> newsList) {
        this.newsList = newsList;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.m4_recycler_view_items, parent, false);
        return new VH(view);
    }

    Bitmap bitmap;

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        final NEWS.NewsListBean data = newsList.get(position);
        holder.m4_title_tv.setText(data.title);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        String t = simpleDateFormat.format(data.createTime);
        holder.m4_time_tv.setText(t + " " + data.realName);

        new Thread() {
            @Override
            public void run() {
                try {
//                    if (position == 0)
//                        bitmap = httpClient.Request_Image((String) data.imgNameList.get(0));
//                    else
                    if (data.imgNameList.size() == 0)
                        bitmap = null;
                    else
                        bitmap = httpClient.Request_Image("http://lantuservice.com/mobilecampus/" + data.imgNameList.get(0));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (data.imgNameList.size() == 0)
                            holder.m4_img.setVisibility(View.GONE);
                        else
                            holder.m4_img.setImageBitmap(bitmap);
                    }
                });
            }
        }.start();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView m4_img;
        TextView m4_title_tv, m4_time_tv;

        public VH(View itemView) {
            super(itemView);
            m4_img = itemView.findViewById(R.id.m4_img);
            m4_title_tv = itemView.findViewById(R.id.m4_title_tv);
            m4_time_tv = itemView.findViewById(R.id.m4_time_tv);
        }
    }
}
