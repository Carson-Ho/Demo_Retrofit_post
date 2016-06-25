package com.example.carson_ho.demo_retrofit_post;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carson_Ho on 16/6/25.
 */
public class Item_List extends AppCompatActivity implements MyItemClickListener {

    private RecyclerView Rv;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.machine_list);
        initData();
        initView();

    }


    public void initData() {
        listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put(" Machines_Recyclerview_Item_name", "第" + i + "行");
            map.put("Machines_Recyclerview_Item_address", "这是第" + i + "行");
            map.put("Machines_Recyclerview_Item_wholesale", "这是第" + i + "行");
            map.put("Machines_Recyclerview_Item_lowerest_wholesale_number", "这是第" + i + "行");
            map.put("Machines_Recyclerview_Item_price", "这是第" + i + "行");
            map.put("Machines_Recyclerview_Item_picture", R.mipmap.ic_launcher);

            listItem.add(map);
        }
    }

    public void initView() {
        //为ListView绑定适配器
        myAdapter = new MyAdapter(this, listItem);
        myAdapter.setOnItemClickListener(this);

        Rv = (RecyclerView) findViewById(R.id.my_recycler_view);
        Rv.setAdapter(myAdapter);


        //使用线性布局

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);

        Rv.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));//用类设置分割线
//            Rv.addItemDecoration(new DividerItemDecoration(this, R.drawable.line)); //用已有图片设置分割线


    }

    @Override
    public void onItemClick(View view, int postion) {//点击事件的回调函数
        System.out.println("点击了第" + postion + "行");
        Toast.makeText(this, (String) listItem.get(postion).get("ItemText"), Toast.LENGTH_SHORT).show();
    }
}

