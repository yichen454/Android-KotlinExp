package com.yichen.summer.net

import com.yichen.summer.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Chen on 2019/2/18
 */
class SummerApiClient private constructor() {

    companion object {
        val INSTANCE: SummerApiClient by lazy { SummerApiClient() }
    }

    private val interceptor: Interceptor
    private val retrofit: Retrofit
    var netService: SummerNetService

    init {
        //通用拦截器
        interceptor = Interceptor { chain ->
            var request = chain.request()
                .newBuilder()
                .addHeader("content-type", "application/json")
                .addHeader("user-agent", "Summer/2.9.8 (iPhone; iOS 12.1.3; Scale/2.00)")
                .addHeader("authorization", "qdQ7vdxyiabbiAKcGxBFo1sK")
                .addHeader("summerplatform", "ios")
                .addHeader("summerversion", "2.9.8")
                .addHeader("devicebrand", "iphone")
                .build()
            chain.proceed(request)
        }

        //创建Retrofit的实例
        retrofit = Retrofit.Builder()
            .baseUrl(SummerApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(initClient())
            .build()

        netService = retrofit.create(SummerNetService::class.java)
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