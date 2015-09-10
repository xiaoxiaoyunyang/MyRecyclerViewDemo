package com.sjy.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjy.demo.activity.R;

import java.util.List;

/**
 * Created by zhu on 15/7/7.
 */
public class MyAdapter extends RecyclerView.Adapter {
    private List<String> list;
    private Context context;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHodler hodler = new MyViewHodler(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
        return hodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHodler myHodler = (MyViewHodler) holder;
        myHodler.setTv(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public void addItem(int position){
        list.add(position, "Insert in one");
        notifyItemInserted(position);
    }

    public void deleteItem(int position){
        list.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHodler extends RecyclerView.ViewHolder{

        private TextView tv;
        public MyViewHodler(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }

        public void setTv(String str){
            tv.setText(str);
        }
    }
}
