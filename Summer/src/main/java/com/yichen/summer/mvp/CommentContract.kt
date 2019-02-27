package com.yichen.summer.mvp

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.summer.entity.SummerCommentData

/**
 * Created by Chen on 2019/2/20
 */
interface CommentContract {
    interface View : BaseView {
        fun showComments(datas: List<SummerCommentData>, state: Int)

        fun getViewContext(): RxAppCompatActivity
    }

    interface Presenter : BasePresenter {
        fun getComments(
            type: String,
            qid: String,
            offset: Int,
            since: String,
            sort: String,
            state: Int,
            isLoading: Boolean
        )
    }
}