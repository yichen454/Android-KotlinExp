package com.yichen.common.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.yichen.common.BuildConfig
import com.yichen.common.injection.component.DaggerAppComponent
import com.yichen.common.injection.module.AppModule

/**
 * Created by Chen on 2019/2/13
 */
open class BaseApplication : Application() {
    lateinit var appComponent: DaggerAppComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
        context = this
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build() as DaggerAppComponent
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}