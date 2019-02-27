package com.yichen.summer.service.impl

import android.text.TextUtils
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.summer.common.Constant
import com.yichen.summer.entity.SummerInfoData
import com.yichen.summer.net.SummerApiClient
import com.yichen.summer.net.SummerSubscriber
import com.yichen.summer.service.TypeService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/18
 */
class TypeServiceImpl @Inject constructor() : TypeService {
    override fun getSummerInfo(
        viewContext: RxFragment,
        type: String,
        offset: Int,
        since: String,
        sort: String,
        state: Int,
        callBack: TypeService.GetSummerInfoCallBack
    ) {
        val params: HashMap<String, String> = HashMap()
        params["limit"] = "20"
        params["offset"] = offset.toString()
        if (!TextUtils.isEmpty(since)) {
            params["since"] = since
        }
        params["scope"] = "global"
        params["sort"] = sort

        SummerApiClient.INSTANCE.netService.getSummerInfo(type, params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(viewContext.bindUntilEvent(FragmentEvent.DESTROY))
            .subscribe(object : SummerSubscriber<List<SummerInfoData>>() {
                override fun onSuccess(response: List<SummerInfoData>) {
                    callBack.onSummerInfo(response, state)
                }

                override fun onFail(status: Int, msg: String) {
                }
            })
    }

    override fun searchSummerInfo(
        viewContext: RxAppCompatActivity,
        type: String,
        tag: String,
        offset: Int,
        state: Int,
        callBack: TypeService.GetSummerInfoCallBack
    ) {
        val params: HashMap<String, String> = HashMap()
        params["limit"] = "20"
        params["offset"] = offset.toString()
        params["q_tags"] = tag
        params["scope"] = "global"
        params["sort"] = Constant.SORT_TIME
        SummerApiClient.INSTANCE.netService.getSummerInfo(type, params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(viewContext.bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(object : SummerSubscriber<List<SummerInfoData>>() {
                override fun onSuccess(response: List<SummerInfoData>) {
                    callBack.onSummerInfo(response, state)
                }

                override fun onFail(status: Int, msg: String) {
                }
            })
    }

}