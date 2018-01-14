package com.example.eason.yikatong.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.eason.yikatong.R;

/**
 * Created by Eason on 2017/12/30.
 */

public class mHome_title_RecyclerView extends RecyclerView.Adapter<mHome_title_RecyclerView.VH> {

    String[] strings;

    iHome_title_item click;

    public mHome_title_RecyclerView(String[] strings, iHome_title_item click) {
        this.strings = strings;
        this.click = click;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_title_recyclerview_items, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        holder.title.setText(strings[position]);
        holder.view.setTag(position);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.Home_title(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return strings.length;
    }


    class VH extends RecyclerView.ViewHolder {

        TextView title;
        View view;

        public VH(View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.title_item);
        }
    }
}
