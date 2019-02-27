package com.yichen.common.injection.module

import android.content.Context
import com.yichen.common.base.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Chen on 2019/2/13
 */
@Module
class AppModule(private val context: BaseApplication) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return this.context
    }
}