package com.example.wzh.bigbigshe.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author by wzh,Date on 2019/2/20.
 * PS: 存放请求方法
 */
public class NetManager {

    private static Retrofit instance = null;

    public static final String BASE_URL = "https://api.github.com/";

    private NetManager() {
    }

    public static synchronized Retrofit getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

                //拦截器
                OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //重构request
                        Request request = chain.request().newBuilder()
                                .header("Accept","application/json")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory( GsonConverterFactory.create( gson ) )
                        .client(okHttpClient)
                        .baseUrl(BASE_URL)
                        .build();

                instance = retrofit;
            }
        }
        return instance;
    }
}
