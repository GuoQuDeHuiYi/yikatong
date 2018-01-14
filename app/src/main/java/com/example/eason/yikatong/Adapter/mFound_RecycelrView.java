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
import com.example.eason.yikatong.DataModel.FOUND;
import com.example.eason.yikatong.R;
import com.example.eason.yikatong.View.request;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * Created by Eason on 2017/12/29.
 */

public class mFound_RecycelrView extends RecyclerView.Adapter<mFound_RecycelrView.VH> {
    List<FOUND.DiscoveryListBean> discoveryList;

    Handler handler;

    public mFound_RecycelrView(List<FOUND.DiscoveryListBean> discoveryList) {
        this.discoveryList = discoveryList;
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.found_recyclerview_items, parent, false);
        return new VH(view);
    }

    Bitmap bitmap;

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        final FOUND.DiscoveryListBean data = discoveryList.get(position);
        holder.root.setTag(position);
        holder.title.setText(data.disTitle);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        String t = simpleDateFormat.format(data.disCreateTime);
        holder.time.setText(t);
        holder.futitle.setText(data.disSummary);
        holder.num.setText(data.disUpvoteCount + "");

        final String url = (data.disImgUrl.contains("mobilecampus.oss.aliyuncs.com") ? "" : request.IMGHEAD) + data.disImgUrl;

//        Bitmap bit_Cache = mSharedContext.bit_Cache.get(url);
//
//        if (bit_Cache != null) {
//            holder.bigimg.setImageBitmap(bit_Cache);
//            return;
//        }

        new Thread() {
            @Override
            public void run() {
                try {
                    bitmap = httpClient.Request_Image(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.post(
                        new Runnable() {
                            @Override
                            public void run() {
                                //mSharedContext.put(url, bitmap);
                                holder.bigimg.setImageBitmap(bitmap);
                            }
                        }
                );
            }
        }.start();
    }

    @Override
    public int getItemCount() {
        return discoveryList.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView title, futitle, time, num;
        ImageView bigimg;
        View root;

        public VH(View itemView) {
            super(itemView);
            root = itemView;
            title = itemView.findViewById(R.id.found_title);
            futitle = itemView.findViewById(R.id.found_futitle);
            time = itemView.findViewById(R.id.found_time);
            num = itemView.findViewById(R.id.found_num);
            bigimg = itemView.findViewById(R.id.found_bigimg);

        }
    }
}
