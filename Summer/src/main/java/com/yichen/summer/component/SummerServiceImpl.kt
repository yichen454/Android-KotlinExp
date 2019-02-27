package com.yichen.summer.component

import com.yichen.common.ui.fragment.BaseFragment
import com.yichen.provider.router.service.SummerService
import com.yichen.summer.ui.fragment.SummerFragment

/**
 * Created by Chen on 2019/2/25
 */
class SummerServiceImpl : SummerService {
    override fun getSummerFragment(): BaseFragment {
        return SummerFragment()
    }
}