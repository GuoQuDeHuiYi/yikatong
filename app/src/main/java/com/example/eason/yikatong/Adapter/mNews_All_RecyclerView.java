package com.example.eason.yikatong.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eason.yikatong.DataModel.NEWS;
import com.example.eason.yikatong.R;

import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by Eason on 2017/12/31.
 */

public class mNews_All_RecyclerView extends RecyclerView.Adapter<mNews_All_RecyclerView.VH> {

    List<NEWS.NewsListBean> newsList;

    public mNews_All_RecyclerView(List<NEWS.NewsListBean> newsList) {
        this.newsList = newsList;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recyclerview_items, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
            final NEWS.NewsListBean data = newsList.get(position);
            holder.tv_title.setText(data.title);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
            String t = simpleDateFormat.format(data.createTime);
            holder.tv_time.setText(t + " " + data.realName);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView tv_title, tv_time;

        public VH(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.news_title_item_tv);
            tv_time = itemView.findViewById(R.id.tv_time_news);
        }
    }
}
