package com.example.eason.yikatong.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.eason.yikatong.R;

/**
 * Created by Eason on 2017/12/27.
 */

public class mPersonal_RecyclerView extends RecyclerView.Adapter<mPersonal_RecyclerView.VH> implements View.OnClickListener {

    String[] list;

    iPersonal_RecyclerView_item click;

    public mPersonal_RecyclerView(String[] list, iPersonal_RecyclerView_item click) {
        this.list = list;
        this.click = click;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.personal_recyclerview_items, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.p_tv.setText(list[position]);
        holder.view.setTag(position);
        holder.Layout.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    @Override
    public void onClick(View view) {
        int p = (int) view.getTag();
        click.item_Click(p);
    }

    class VH extends RecyclerView.ViewHolder {

        TextView p_tv;
        RelativeLayout Layout;
        View view;

        public VH(View itemView) {
            super(itemView);
            view = itemView;
            p_tv = itemView.findViewById(R.id.personal_recyclerview_tv);
            Layout = itemView.findViewById(R.id.item_personal);
        }
    }
}
