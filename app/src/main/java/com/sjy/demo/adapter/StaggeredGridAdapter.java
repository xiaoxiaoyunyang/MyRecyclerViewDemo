package com.sjy.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjy.demo.activity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu on 15/7/8.
 */
public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.MyViewHolder> {

    private List<String> list;
    private Context context;
    private List<Integer> mHeights;
    private OnItemListener onItemListener;

    /**
     * 为子布局添加OnItemLisenter事件
     */
    public interface OnItemListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemListener(OnItemListener onItemListener) {
        this.onItemListener = onItemListener;
    }

    public StaggeredGridAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public StaggeredGridAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        StaggeredGridAdapter.MyViewHolder hodler = new StaggeredGridAdapter.MyViewHolder(LayoutInflater.from(this.context).inflate(R.layout.item_layout, parent, false));
        return hodler;
    }

    @Override
    public void onBindViewHolder(final StaggeredGridAdapter.MyViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.textView.getLayoutParams();
        layoutParams.height = mHeights.get(position);
        holder.textView.setLayoutParams(layoutParams);
        holder.setTextView(list.get(position));
        if (onItemListener != null) {
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemListener.onItemClick(holder.itemView, position);
                }
            });
            holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemListener.onItemLongClick(holder.itemView, position);
                    removeItem(position);
                    return  true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(int position) {
        list.add(position, "Insert");
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }

        public void setTextView(String str) {
            textView.setText(str);
        }
    }
}
