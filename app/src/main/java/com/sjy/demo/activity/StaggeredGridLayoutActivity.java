package com.sjy.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sjy.demo.adapter.StaggeredGridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhu on 15/7/8.
 */
public class StaggeredGridLayoutActivity extends ActionBarActivity {

    private RecyclerView recyclerView;
    private List<String> mDatas;
    private StaggeredGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_layout);
        recyclerView = (RecyclerView) findViewById(R.id.myrecycler);
        initValues();
        recyclerView.setAdapter(adapter = new StaggeredGridAdapter(this, mDatas));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        initEvent();
    }

    private void initValues() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    private void initEvent() {
        adapter.setOnItemListener(new StaggeredGridAdapter.OnItemListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,
                        position + " click", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(StaggeredGridLayoutActivity.this,
                        position + " long click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                adapter.addItem(1);
                break;
            case R.id.delect:
                adapter.removeItem(1);
                break;
        }
        return true;
    }

}
