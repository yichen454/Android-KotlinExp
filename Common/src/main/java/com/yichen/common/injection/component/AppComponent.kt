package com.yichen.common.injection.component

import android.content.Context
import com.yichen.common.injection.module.AppModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Chen on 2019/2/13
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun context(): Context
}