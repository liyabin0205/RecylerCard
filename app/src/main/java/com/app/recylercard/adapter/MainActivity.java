package com.app.recylercard.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.recylercard.R;
import com.app.recylercard.adapter.adapter.DayFoodAdapter;
import com.app.recylercard.adapter.adapter.DayReadAdapter;
import com.app.recylercard.adapter.bean.Root;
import com.app.recylercard.adapter.utils.DownAsyncTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DownAsyncTask.WeiXinCallBack {

    private final static String URLWEIXIN = "http://v.juhe.cn/weixin/query?key=11cafa19fd017db18448b5cb4eac0bf2";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Map<String, String>> readlist = new ArrayList<>();
    private List<Root.ListObject> list = new ArrayList<>();
    private DayFoodAdapter foodAdapter;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

         initAdapter(flag);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this, Main3Activity.class));
                            }
                        }).show();
            }
        });
    }

    private void initAdapter(boolean flag){
        if (flag) {
            initData1();
            mRecyclerView.setAdapter(new DayReadAdapter(MainActivity.this, readlist));
        } else {
            initData2();
            foodAdapter = new DayFoodAdapter(MainActivity.this, list);
            mRecyclerView.setAdapter(foodAdapter);
        }
    }

    private void initData1() {
        Map<String, String> map;
        for (int i = 0; i < 10; i++){
            map = new HashMap<>();
            map.put("title", "这是第"+i+"个标题");
            map.put("date", "2016.9."+(i+13));
            map.put("postContent", "  想必每个人都有喜欢的历史人物，他们或是精神偶像，或是才华横溢，或是趣味相投；想必每个人都有偏爱的时代，大气强盛的汉唐，文化璀璨的两宋，或者名士风流的魏晋，大师辈出的民国……如今，名人古迹俱往矣，留下的，是一页页文字和器物遗迹。饶是如此，当我们面对所喜所爱所赋深情的古人古迹时，也难免心动心摇心向往之。");
            readlist.add(map);
        }

    }

    private void initData2() {
        new DownAsyncTask(MainActivity.this, this).execute(URLWEIXIN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_card1:
                flag = true;
                initAdapter(flag);
                Toast.makeText(MainActivity.this, "精品阅读...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_card2:
                flag = false;
                initAdapter(flag);
                Toast.makeText(MainActivity.this, "舌尖美食...", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getNews(Root result) {
        list.addAll(result.getResult().getList());
        Log.i("DDDD---->",""+list.size());
        foodAdapter.notifyDataSetChanged();
    }
}
