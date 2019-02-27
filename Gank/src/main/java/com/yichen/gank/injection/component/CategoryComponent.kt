package com.yichen.gank.injection.component

import com.yichen.common.injection.component.ActivityComponent
import com.yichen.common.injection.scope.PerComponentScope
import com.yichen.gank.injection.module.CategoryModule
import com.yichen.gank.ui.fragment.CategoryFragment
import dagger.Component

/**
 * Created by Chen on 2019/2/15
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(CategoryModule::class)])
interface CategoryComponent {

    fun inject(fragment: CategoryFragment)

}