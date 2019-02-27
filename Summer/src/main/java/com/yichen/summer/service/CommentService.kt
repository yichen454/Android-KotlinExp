package com.yichen.summer.service

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.summer.entity.SummerCommentData

/**
 * Created by Chen on 2019/2/20
 */
interface CommentService {

    fun getComments(
        viewContext: RxAppCompatActivity,
        type: String,
        qid: String,
        offset: Int,
        since: String,
        sort: String,
        state: Int,
        callBack: GetCommentsCallBack
    )

    interface GetCommentsCallBack {
        fun onComments(datas: List<SummerCommentData>, state: Int)
    }
}