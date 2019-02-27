package com.yichen.summer.service.impl

import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.summer.entity.SummerUserData
import com.yichen.summer.net.SummerApiClient
import com.yichen.summer.net.SummerSubscriber
import com.yichen.summer.service.UserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/19
 */
class UserServiceImpl @Inject constructor() : UserService {
    override fun getUserInfo(viewContext: RxAppCompatActivity, uid: String, callBack: UserService.GetUserInfoCallBack) {
        SummerApiClient.INSTANCE.netService.getUserInfo(uid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(viewContext.bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(object : SummerSubscriber<SummerUserData>() {
                override fun onSuccess(response: SummerUserData) {
                    callBack.onUserInfo(response)
                }

                override fun onFail(status: Int, msg: String) {
                }
            })
    }

}