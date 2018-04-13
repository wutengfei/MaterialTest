package com.example.materialtest;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private String urlRoot = "https://raw.githubusercontent.com/wutengfei/Background/master";
    private Beauty[] fruits = {
            new Beauty("1", urlRoot + "/1.jpg"),
            new Beauty("2", urlRoot + "/2.jpg"),
            new Beauty("3", urlRoot + "/3.jpg"),
            new Beauty("4", urlRoot + "/4.jpg"),
            new Beauty("5", urlRoot + "/5.jpg"),
            new Beauty("6", urlRoot + "/6.jpg"),
            new Beauty("7", urlRoot + "/7.jpg"),
            new Beauty("8", urlRoot + "/8.jpg"),
            new Beauty("9", urlRoot + "/9.jpg"),
            new Beauty("10", urlRoot + "/10.jpg"),
            new Beauty("11", urlRoot + "/11.jpg"),
            new Beauty("12", urlRoot + "/12.jpg"),
            new Beauty("13", urlRoot + "/13.jpg"),
            new Beauty("14", urlRoot + "/14.jpg"),
            new Beauty("15", urlRoot + "/15.jpg"),
            new Beauty("16", urlRoot + "/16.jpg"),
            new Beauty("17", urlRoot + "/17.jpg"),
            new Beauty("18", urlRoot + "/18.jpg"),
            new Beauty("19", urlRoot + "/19.jpg"),
            new Beauty("20", urlRoot + "/20.jpg")};

    private List<Beauty> beautyList = new ArrayList<>();

    private BeautyAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                Toast.makeText(MainActivity.this, "点击了" + item, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        //初始化图片到布局中
        initBeauty();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        //  adapter = new BeautyAdapter(beautyList);
        adapter = new BeautyAdapter(beautyList);
        recyclerView.setAdapter(adapter);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initBeauty();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initBeauty() {
        beautyList.clear();
        for (int i = 0; i < 20; i++) {
            beautyList.add(fruits[i]);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}
