package com.yichen.summer.injection.component

import com.yichen.common.injection.component.ActivityComponent
import com.yichen.common.injection.scope.PerComponentScope
import com.yichen.summer.injection.module.UserModule
import com.yichen.summer.ui.activity.ProfileActivity
import dagger.Component

/**
 * Created by Chen on 2019/2/19
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(UserModule::class)])
interface UserComponent {
    fun inject(activity: ProfileActivity)
}