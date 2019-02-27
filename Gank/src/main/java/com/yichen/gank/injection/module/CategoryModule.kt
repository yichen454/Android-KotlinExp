package com.yichen.gank.injection.module

import com.yichen.gank.mvp.CategoryContract
import com.yichen.gank.service.CategoryService
import com.yichen.gank.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Chen on 2019/2/15
 */
@Module
class CategoryModule(val view: CategoryContract.View) {

    @Provides
    fun provideService(serviceImpl: CategoryServiceImpl): CategoryService {
        return serviceImpl
    }

    @Provides
    fun provideView(): CategoryContract.View {
        return view
    }
}