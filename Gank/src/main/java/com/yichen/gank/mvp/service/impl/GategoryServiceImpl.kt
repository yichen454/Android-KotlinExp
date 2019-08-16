package com.yichen.gank.mvp.service.impl

import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.gank.entity.GankItemData
import com.yichen.gank.net.GankApiClient
import com.yichen.gank.net.GankSubscriber
import com.yichen.gank.mvp.service.CategoryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/15
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {
    
    override fun getCategoryGanks(
        viewContext: RxFragment,
        type: String,
        page: Int,
        state: Int,
        callBack: CategoryService.GetCategoryGankCallBack
    ) {
        GankApiClient.INSTANCE.netService.getGankInfo(type, 10, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(viewContext.bindUntilEvent(FragmentEvent.DESTROY))
            .subscribe(object : GankSubscriber<List<GankItemData>>() {
                override fun onSuccess(response: List<GankItemData>) {
                    callBack.onCategoryGanks(response, state)
                }

                override fun onFail(status: Int, msg: String) {
                }

            })
    }

}