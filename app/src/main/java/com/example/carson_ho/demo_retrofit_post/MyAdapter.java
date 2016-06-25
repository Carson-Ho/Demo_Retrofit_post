package com.example.carson_ho.demo_retrofit_post;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Carson_Ho on 16/6/24.
 */
public class MyAdapter extends RecyclerView.Adapter{
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyItemClickListener myItemClickListener;

    public MyAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }//构造函数，传入数据


    //定义Viewholder
    class Viewholder extends RecyclerView.ViewHolder  {
        private TextView Machines_Recyclerview_Item_name,Machines_Recyclerview_Item_address,Machines_Recyclerview_Item_wholesale,Machines_Recyclerview_Item_lowerest_wholesale_number,Machines_Recyclerview_Item_price;

        private ImageView Machines_Recyclerview_Item_picture;

        public Viewholder(View root) {
            super(root);

            Machines_Recyclerview_Item_price= (TextView) root.findViewById(R.id.Machines_Recyclerview_Item_price);
            Machines_Recyclerview_Item_name = (TextView) root.findViewById(R.id.Machines_Recyclerview_Item_name);
            Machines_Recyclerview_Item_lowerest_wholesale_number = (TextView) root.findViewById(R.id.Machines_Recyclerview_Item_lowerest_wholesale_number);
            Machines_Recyclerview_Item_wholesale= (TextView) root.findViewById(R.id.Machines_Recyclerview_Item_wholesale);
            Machines_Recyclerview_Item_address = (TextView) root.findViewById(R.id.Machines_Recyclerview_Item_address);
            Machines_Recyclerview_Item_picture = (ImageView) root.findViewById(R.id.Machines_Recyclerview_Item_picture);

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItemClickListener != null)
                        myItemClickListener .onItemClick(v,getPosition());
                }

            }//监听到点击就回调MainActivity的onItemClick函数
            );

        }

        public TextView getMachines_Recyclerview_Item_price() {
            return Machines_Recyclerview_Item_price;
        }


        public TextView getMachines_Recyclerview_Item_name() {
            return Machines_Recyclerview_Item_name;
        }

        public TextView getMachines_Recyclerview_Item_lowerest_wholesale_number() {
            return Machines_Recyclerview_Item_lowerest_wholesale_number;
        }

        public TextView getMachines_Recyclerview_Item_wholesale() {
            return Machines_Recyclerview_Item_wholesale;
        }

        public TextView getMachines_Recyclerview_Item_address() {
            return Machines_Recyclerview_Item_address;
        }


        public ImageView getMachines_Recyclerview_Item_picture() {
            return Machines_Recyclerview_Item_picture;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(inflater.inflate(R.layout.list_cell, null));
    }//在这里把ViewHolder绑定Item的布局

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        Viewholder vh = (Viewholder) holder;
        vh.Machines_Recyclerview_Item_price.setText((String) listItem.get(position).get("Machines_Recyclerview_Item_price"));
        vh.Machines_Recyclerview_Item_name.setText((String) listItem.get(position).get("Machines_Recyclerview_Item_name"));
        vh.Machines_Recyclerview_Item_lowerest_wholesale_number.setText((String) listItem.get(position).get("Machines_Recyclerview_Item_lowerest_wholesale_number"));
        vh.Machines_Recyclerview_Item_wholesale.setText((String) listItem.get(position).get("Machines_Recyclerview_Item_wholesale"));
        vh.Machines_Recyclerview_Item_address.setText((String) listItem.get(position).get("Machines_Recyclerview_Item_address"));
        vh.Machines_Recyclerview_Item_picture.setImageResource((Integer) listItem.get(position).get("Machines_Recyclerview_Item_picture"));
    }//在这里绑定数据到ViewHolder里面

    @Override
    public int getItemCount() {
        return listItem.size();
    }//返回Item数目

    public void setOnItemClickListener(MyItemClickListener listener){
        myItemClickListener = listener;
    }//绑定MainActivity传进来的点击监听器
}
