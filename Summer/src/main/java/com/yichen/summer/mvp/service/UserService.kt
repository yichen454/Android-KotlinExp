package com.yichen.summer.mvp.service

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.summer.entity.SummerUserData

/**
 * Created by Chen on 2019/2/19
 */
interface UserService {
    fun getUserInfo(
        viewContext: RxAppCompatActivity,
        uid: String,
        callBack: GetUserInfoCallBack
    )

    interface GetUserInfoCallBack {
        fun onUserInfo(data: SummerUserData)
    }
}