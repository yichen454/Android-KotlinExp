package com.yichen.kotlin.app

import com.alibaba.android.arouter.launcher.ARouter
import com.yichen.common.BuildConfig
import com.yichen.common.base.BaseApplication

/**
 * Created by Chen on 2019/2/25
 */
class MainApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog()
            //开启调试模式
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}