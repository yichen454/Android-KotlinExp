package com.yichen.summer.injection.module

import com.yichen.summer.mvp.UserContract
import com.yichen.summer.mvp.service.UserService
import com.yichen.summer.mvp.service.impl.UserServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Chen on 2019/2/19
 */
@Module
class UserModule(val view: UserContract.View) {
    @Provides
    fun provideService(serviceImpl: UserServiceImpl): UserService {
        return serviceImpl
    }

    @Provides
    fun provideView(): UserContract.View {
        return view
    }
}