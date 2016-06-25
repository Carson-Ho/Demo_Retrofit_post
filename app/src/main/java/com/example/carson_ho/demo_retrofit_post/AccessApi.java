package com.example.carson_ho.demo_retrofit_post;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Carson_Ho on 16/6/24.
 */
public interface AccessApi {

        @POST("http://218.192.170.132:8000/commodity/search")

        //Body只能用Okhttp的RequestBody
        Call<CommodityImfor> getCall(@Body RequestBody body);
}
