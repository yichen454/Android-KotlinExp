package com.yichen.gank.component

import android.util.Log
import com.yichen.provider.router.component.IApplicationLoad
import com.yichen.provider.router.component.Router
import com.yichen.provider.router.service.GankService

/**
 * Created by Chen on 2019/2/15
 */
class GankApplicationLoad : IApplicationLoad {

    var router = Router.instance

    override fun registered() {
        router.addService(GankService::class.java.simpleName, GankServiceImpl())
    }

    override fun unregistered() {
        router.removeService(GankService::class.java.simpleName)
    }
}