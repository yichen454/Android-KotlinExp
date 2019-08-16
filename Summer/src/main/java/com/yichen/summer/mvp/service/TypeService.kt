package com.yichen.summer.mvp.service

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.summer.entity.SummerInfoData

/**
 * Created by Chen on 2019/2/18
 */
interface TypeService {
    fun getSummerInfo(
        viewContext: RxFragment,
        type: String,
        offset: Int,
        since: String,
        sort: String,
        state: Int,
        callBack: GetSummerInfoCallBack
    )

    fun searchSummerInfo(
        viewContext: RxAppCompatActivity,
        type: String,
        tag: String,
        offset: Int,
        state: Int,
        callBack: GetSummerInfoCallBack
    )

    interface GetSummerInfoCallBack {
        fun onSummerInfo(datas: List<SummerInfoData>, state: Int)
    }
}