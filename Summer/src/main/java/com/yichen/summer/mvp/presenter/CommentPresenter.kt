package com.yichen.summer.mvp.presenter

import com.yichen.summer.entity.SummerCommentData
import com.yichen.summer.mvp.CommentContract
import com.yichen.summer.service.CommentService
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/20
 */
class CommentPresenter @Inject constructor() : CommentContract.Presenter, CommentService.GetCommentsCallBack {
    @Inject
    lateinit var mView: CommentContract.View

    private var isLoading = false

    @Inject
    lateinit var mService: CommentService

    override fun getComments(
        type: String,
        qid: String,
        offset: Int,
        since: String,
        sort: String,
        state: Int,
        isLoading: Boolean
    ) {
        this.isLoading = isLoading
        if (isLoading) {
            mView.showLoading()
        }
        mService.getComments(mView.getViewContext(), type, qid, offset, since, sort, state, this)
    }

    override fun onComments(datas: List<SummerCommentData>, state: Int) {
        mView.showComments(datas, state)
        if (isLoading) {
            mView.hideLoading()
        }
    }
}