package com.yichen.summer.mvp

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.summer.entity.SummerInfoData

/**
 * Created by Chen on 2019/2/18
 */
interface TypeContract {
    interface View : BaseView {
        fun showSummerInfo(datas: List<SummerInfoData>, state: Int)

        fun getTypeContext(): RxFragment?

        fun getSearchContext(): RxAppCompatActivity?
    }

    interface Presenter : BasePresenter {
        fun getSummerInfo(type: String, offset: Int, since: String, sort: String, state: Int, isLoading: Boolean)

        fun searchSummerInfo(type: String, tag: String, offset: Int, state: Int, isLoading: Boolean)
    }
}