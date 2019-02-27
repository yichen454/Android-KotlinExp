package com.yichen.summer.injection.component

import com.yichen.common.injection.component.ActivityComponent
import com.yichen.common.injection.scope.PerComponentScope
import com.yichen.summer.injection.module.TypeModule
import com.yichen.summer.ui.activity.SearchActivity
import com.yichen.summer.ui.fragment.TypeFragment
import dagger.Component

/**
 * Created by Chen on 2019/2/18
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(TypeModule::class)])
interface TypeComponent {
    fun inject(fragment: TypeFragment)

    fun inject(activity: SearchActivity)
}