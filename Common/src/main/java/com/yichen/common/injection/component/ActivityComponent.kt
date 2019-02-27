package com.yichen.common.injection.component

import android.app.Activity
import android.content.Context
import com.yichen.common.injection.module.ActivityModule
import com.yichen.common.injection.scope.ActivityScope
import dagger.Component

/**
 * Created by Chen on 2019/2/13
 */
@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun context(): Context
    fun activity(): Activity

}