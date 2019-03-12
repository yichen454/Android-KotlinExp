package com.yichen.music.injection.component

import com.yichen.common.injection.component.ActivityComponent
import com.yichen.common.injection.scope.PerComponentScope
import com.yichen.music.injection.module.MvListModule
import com.yichen.music.ui.fragment.MvFragment
import dagger.Component

/**
 * Created by Chen on 2019/3/12
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(MvListModule::class)])
interface MvListComponent {

    fun inject(fragment: MvFragment)

}