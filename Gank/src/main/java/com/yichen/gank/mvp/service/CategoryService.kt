package com.yichen.gank.mvp.service

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.gank.entity.GankItemData

/**
 * Created by Chen on 2019/2/15
 */
interface CategoryService {

    fun getCategoryGanks(
        viewContext: RxFragment,
        type: String,
        page: Int,
        state: Int,
        callBack: GetCategoryGankCallBack
    )

    interface GetCategoryGankCallBack {
        fun onCategoryGanks(datas: List<GankItemData>, state: Int)
    }
}