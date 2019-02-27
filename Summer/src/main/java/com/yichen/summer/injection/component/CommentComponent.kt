package com.yichen.summer.injection.component

import com.yichen.common.injection.component.ActivityComponent
import com.yichen.common.injection.scope.PerComponentScope
import com.yichen.summer.injection.module.CommentModule
import com.yichen.summer.ui.activity.CommentActivity
import dagger.Component

/**
 * Created by Chen on 2019/2/20
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(CommentModule::class)])
interface CommentComponent {
    fun inject(activity: CommentActivity)
}