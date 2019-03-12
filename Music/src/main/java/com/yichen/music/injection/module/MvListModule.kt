package com.yichen.music.injection.module

import com.yichen.music.mvp.MvListContract
import com.yichen.music.service.MvListService
import com.yichen.music.service.impl.MvListServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Chen on 2019/3/12
 */
@Module
class MvListModule(val view: MvListContract.View) {

    @Provides
    fun provideService(serviceImpl: MvListServiceImpl): MvListService {
        return serviceImpl
    }

    @Provides
    fun provideView(): MvListContract.View {
        return view
    }
}