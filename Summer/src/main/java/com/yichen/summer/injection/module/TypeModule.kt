package com.yichen.summer.injection.module

import com.yichen.summer.mvp.TypeContract
import com.yichen.summer.mvp.service.impl.TypeServiceImpl
import com.yichen.summer.mvp.service.TypeService
import dagger.Module
import dagger.Provides

/**
 * Created by Chen on 2019/2/18
 */
@Module
class TypeModule(val view: TypeContract.View) {
    @Provides
    fun provideService(serviceImpl: TypeServiceImpl): TypeService {
        return serviceImpl
    }

    @Provides
    fun provideView(): TypeContract.View {
        return view
    }
}