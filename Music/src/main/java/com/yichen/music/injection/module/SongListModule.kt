package com.yichen.music.injection.module

import com.yichen.music.mvp.SongListContract
import com.yichen.music.service.SongListService
import com.yichen.music.service.impl.SongListServiceImpl
import dagger.Module
import dagger.Provides


/**
 * Created by Chen on 2019/3/4
 */
@Module
class SongListModule(val view: SongListContract.View) {

    @Provides
    fun provideService(serviceImpl: SongListServiceImpl): SongListService {
        return serviceImpl
    }

    @Provides
    fun provideView(): SongListContract.View {
        return view
    }
}