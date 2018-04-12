package com.example.materialtest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
private String urlRoot="https://raw.githubusercontent.com/wutengfei/Background/master";
    private Fruits[] fruits = {
            new Fruits("Apple", urlRoot+"/10.jpg"),
            new Fruits("Banana",urlRoot+ "/11.jpg"),
            new Fruits("Orange",urlRoot+"/12.jpg"),
            new Fruits("Watermelon",urlRoot+ "/13.jpg"),
            new Fruits("Pear",urlRoot+ "/14.jpg"),
            new Fruits("Grape",urlRoot+ "/15.jpg"),
            new Fruits("Pineapple",urlRoot+ "/16.jpg"),
            new Fruits("Strawberry",urlRoot+ "/17.jpg"),
            new Fruits("Cherry",urlRoot+ "/18.jpg"),
            new Fruits("Mango",urlRoot+ "/19.jpg")};
//    new Fruits("Apple", "http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Banana","http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Orange","http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Watermelon","http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Pear","http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Grape","http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Pineapple","http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Strawberry","http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Cherry","http://www.xz7.com/up/2016-6/2016649319.png"),
//            new Fruits("Mango","http://www.xz7.com/up/2016-6/2016649319.png")};

   // private List<Fruit> fruitList = new ArrayList<>();
    private List<Fruits> fruitList = new ArrayList<>();

    private FruitAdapter adapter;

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
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "Data restored", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });

        //下载图片
      //  download();
        //初始化图片到布局中
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
      //  adapter = new FruitAdapter(fruitList);
        adapter = new FruitAdapter(fruitList);
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

    private void download() {

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
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

//    private void initFruits() {
//        fruitList.clear();
//        for (int i = 0; i < 50; i++) {
//            Random random = new Random();
//            int index = random.nextInt(fruits.length);
//            fruitList.add(fruits[index]);
//        }
//    }
    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            fruitList.add(fruits[index]);
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
