package com.yichen.summer.component

import com.yichen.provider.router.component.IApplicationLoad
import com.yichen.provider.router.component.Router
import com.yichen.provider.router.service.SummerService

/**
 * Created by Chen on 2019/2/25
 */
class SummerApplicationLoad : IApplicationLoad {
    var router = Router.instance

    override fun registered() {
        router.addService(SummerService::class.java.simpleName, SummerServiceImpl())
    }

    override fun unregistered() {
        router.removeService(SummerService::class.java.simpleName)
    }
}