package com.yichen.common.injection.module

import android.app.Activity
import com.yichen.common.injection.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by Chen on 2019/2/13
 * Activity级别的作用域
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun provideActivity(): Activity {
        return this.activity
    }
}
