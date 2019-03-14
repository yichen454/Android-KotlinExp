package com.yichen.music.injection.component

import com.yichen.common.injection.component.ActivityComponent
import com.yichen.common.injection.scope.PerComponentScope
import com.yichen.music.injection.module.SongListModule
import com.yichen.music.ui.activity.SongListDetailActivity
import com.yichen.music.ui.fragment.SongListFragment
import dagger.Component

/**
 * Created by Chen on 2019/3/4
 */
@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(SongListModule::class)])
interface SongListComponent {

    fun inject(fragment: SongListFragment)

    fun inject(activity:SongListDetailActivity)

}