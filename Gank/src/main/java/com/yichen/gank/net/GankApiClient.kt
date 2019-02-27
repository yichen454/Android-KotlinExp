package com.yichen.gank.net

import com.yichen.gank.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Chen on 2019/2/14
 */
class GankApiClient private constructor() {

    companion object {
        val INSTANCE: GankApiClient by lazy { GankApiClient() }
    }

    private val interceptor: Interceptor
    private val retrofit: Retrofit
    var netService: GankNetService

    init {
        //通用拦截器
        interceptor = Interceptor { chain ->
            var request = chain.request()
                .newBuilder()
                .addHeader("charset", "UTF-8")
                .build()
            chain.proceed(request)

        }

        //创建Retrofit的实例
        retrofit = Retrofit.Builder()
            .baseUrl(GankApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()

        netService = retrofit.create(GankNetService::class.java)
    }

    /**
     * 创建请求客户端
     */
    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(initLogInterceptor())
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    /**
     *日志拦截器
     */
    private fun initLogInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        return interceptor
    }
}