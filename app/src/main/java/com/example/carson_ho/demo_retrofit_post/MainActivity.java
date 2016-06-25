package com.example.carson_ho.demo_retrofit_post;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                POSTrequest();
            }
        });
    }

    public void POSTrequest() {

        //输入POST请求的BODY
        JSONObject jo = new JSONObject();
        try {
            jo.put("requestType", "Search");
            jo.put("productType", "电子电器零件");
            jo.put("material", "ABS");
            jo.put("crumble", "不添加");
            jo.put("CaCo3", "不添加");
            jo.put("fiberglass", "15%以下");
            jo.put("fireproofing", "一般防火");
            jo.put("color", "色粉");
            jo.put("productWeight", 23.4);
            jo.put("productLength", 12.2);
            jo.put("productWidth", 11.2);
            jo.put("productHeight", 23.2);
            jo.put("wallThickness", 3.2);
            jo.put("moduleLength", 1.1);
            jo.put("moduleWidth", 2.2);
            jo.put("moduleHeight", 3.3);
            jo.put("ejector", "拉伸");
            jo.put("locatingRing", 4.4);
            jo.put("screw", "A");
            jo.put("power", "油压");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //把Body加上头，将请求的json包装成httpPOST请求
        MediaType JSON = MediaType.parse("application/json;charset=utf-8");
        RequestBody Body = RequestBody.create(JSON, jo.toString());


        //创建Retrofit对象

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://218.192.170.132:8000")//设置baseUrl
                .addConverterFactory(GsonConverterFactory.create())//设置使用Gson解析
                .build();

        AccessApi access = retrofit.create(AccessApi.class);
        Call<CommodityImfor> call = access.getCall(Body);


        //发送网络请求
        call.enqueue(new Callback<CommodityImfor>() {
            @Override
            public void onResponse(Call<CommodityImfor> call, Response<CommodityImfor> response) {
                System.out.println("有回应");
                //有数据返回
                if (response.isSuccessful()) {

                    Intent intent = new Intent(MainActivity.this,Item_List.class);
                        startActivity(intent);

                    System.out.println(response.body().getFail());
                    System.out.println(response.body().getTotal());
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        System.out.println(response.body().getData().get(i).getName());
                        System.out.println(response.body().getData().get(i).getAddress());
                        System.out.println(response.body().getData().get(i).getPicture());
                        System.out.println(response.body().getData().get(i).getExpress_cost());
                    }

                } else {
                    dialogMachineNotFound();

                }
            }

            //无连接到服务器
            @Override
            public void onFailure(Call<CommodityImfor> call, Throwable throwable) {
                dialogNetworkWarning();
            }
        });
    }


    //网络异常提示
    protected void dialogNetworkWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("网络异常，请检查你的网络连接");

        builder.setTitle("提示");

        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


            }
        });

        builder.setNegativeButton("重试", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                POSTrequest();
            }
        });

        builder.create().show();
    }


    //无数据返回
    protected void dialogMachineNotFound() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("暂时找不到你需求的注塑机，若需个性化制定请致电：020-88888888");

        builder.setTitle("提示");

        builder.setPositiveButton("返回", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        builder.create().show();
    }
}



