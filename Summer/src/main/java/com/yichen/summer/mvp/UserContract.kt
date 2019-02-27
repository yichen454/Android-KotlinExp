package com.yichen.summer.mvp

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.summer.entity.SummerUserData

/**
 * Created by Chen on 2019/2/19
 */
interface UserContract {
    interface View : BaseView {
        fun showUserInfo(data: SummerUserData)

        fun getViewContext(): RxAppCompatActivity
    }

    interface Presenter : BasePresenter {
        fun getUserInfo(uid: String, isLoading: Boolean)
    }
}