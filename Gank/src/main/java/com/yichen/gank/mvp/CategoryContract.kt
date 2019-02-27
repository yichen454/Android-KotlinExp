package com.yichen.gank.mvp

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.gank.entity.GankItemData

/**
 * Created by Chen on 2019/2/13
 */
interface CategoryContract {

    interface View : BaseView {

        fun showGanks(datas: List<GankItemData>, state: Int)

        fun getViewContext(): RxFragment
    }

    interface Presenter : BasePresenter {
        fun getCategoryGanks(type: String, page: Int, state: Int, isLoading: Boolean)
    }
}