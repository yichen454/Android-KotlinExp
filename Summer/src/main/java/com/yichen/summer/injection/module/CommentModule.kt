package com.yichen.summer.injection.module

import com.yichen.summer.mvp.CommentContract
import com.yichen.summer.service.CommentService
import com.yichen.summer.service.impl.CommentServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Chen on 2019/2/20
 */
@Module
class CommentModule(val view: CommentContract.View) {
    @Provides
    fun provideService(serviceImpl: CommentServiceImpl): CommentService {
        return serviceImpl
    }

    @Provides
    fun provideView(): CommentContract.View {
        return view
    }
}