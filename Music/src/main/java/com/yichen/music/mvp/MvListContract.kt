package com.yichen.music.mvp

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.music.entity.MusicMvListEntity

/**
 * Created by Chen on 2019/3/12
 */
interface MvListContract {

    interface View : BaseView {
        fun showMvList(datas: List<MusicMvListEntity>, state: Int)

        fun getViewContext(): RxFragment
    }

    interface Presenter : BasePresenter {
        fun getMvList(limit: Int, offset: Int, state: Int, isLoading: Boolean)
    }
}