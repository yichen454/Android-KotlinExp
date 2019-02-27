package com.yichen.summer.mvp.presenter

import com.yichen.summer.entity.SummerUserData
import com.yichen.summer.mvp.UserContract
import com.yichen.summer.service.UserService
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/19
 */
class UserPresenter @Inject constructor() : UserContract.Presenter, UserService.GetUserInfoCallBack {

    @Inject
    lateinit var mView: UserContract.View

    @Inject
    lateinit var mService: UserService

    override fun getUserInfo(uid: String, isLoading: Boolean) {
        mView.showLoading()
        mService.getUserInfo(mView.getViewContext(), uid, this)
    }

    override fun onUserInfo(data: SummerUserData) {
        mView.showUserInfo(data)
        mView.hideLoading()
    }

}