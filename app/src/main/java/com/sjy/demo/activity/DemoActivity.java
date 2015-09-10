package com.sjy.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.sjy.demo.adapter.MyAdapter;
import com.sjy.demo.adapter.StaggeredGridAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends AppCompatActivity {

    private RecyclerView myRv;
    private List<String> mDatas;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_layout);
        initView();
        initData();
        myRv.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        myRv.setAdapter(myAdapter = new MyAdapter(this, mDatas));
        myRv.addItemDecoration(new DeviderGridItemDecoration(this));
    }

    private void initView() {
        myRv = (RecyclerView) findViewById(R.id.myrecycler);
    }

    private void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.listview:
                myRv.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.gridview:
                myRv.setLayoutManager(new GridLayoutManager(this, 4));

                break;
            case R.id.pubu:
                Intent intent = new Intent(DemoActivity.this, StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.add:
                myAdapter.addItem(1);
                break;
            case R.id.delect:
                myAdapter.deleteItem(1);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
