package com.yichen.gank.component

import com.yichen.common.ui.fragment.BaseFragment
import com.yichen.gank.ui.fragment.GankFragment
import com.yichen.provider.router.service.GankService

/**
 * Created by Chen on 2019/2/15
 */
class GankServiceImpl : GankService {
    override fun getGankFragment(): BaseFragment {
        return GankFragment()
    }
}