package com.yichen.summer.service.impl

import android.text.TextUtils
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.summer.common.Constant
import com.yichen.summer.entity.SummerCommentData
import com.yichen.summer.net.SummerApiClient
import com.yichen.summer.net.SummerSubscriber
import com.yichen.summer.service.CommentService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/20
 */
class CommentServiceImpl @Inject constructor() : CommentService {
    override fun getComments(
        viewContext: RxAppCompatActivity,
        type: String,
        qid: String,
        offset: Int,
        since: String,
        sort: String,
        state: Int,
        callBack: CommentService.GetCommentsCallBack
    ) {
        val subscriber: SummerSubscriber<List<SummerCommentData>> =
            object : SummerSubscriber<List<SummerCommentData>>() {
                override fun onSuccess(response: List<SummerCommentData>) {
                    callBack.onComments(response, state)
                }

                override fun onFail(status: Int, msg: String) {
                }

            }
        val params: HashMap<String, String> = HashMap()
        params["limit"] = "20"
        params["offset"] = offset.toString()
        if (!TextUtils.isEmpty(since)) {
            params["since"] = since
        }
        params["sort"] = sort
        when (type) {
            Constant.TYPE_BLACK -> SummerApiClient.INSTANCE.netService.getCommentsBlack(qid, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(viewContext.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(subscriber)
            Constant.TYPE_SECRET -> SummerApiClient.INSTANCE.netService.getCommentsSecret(qid, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(viewContext.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(subscriber)
            Constant.TYPE_ACTIVITY -> SummerApiClient.INSTANCE.netService.getCommentsActivity(qid, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(viewContext.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(subscriber)
        }
    }

}