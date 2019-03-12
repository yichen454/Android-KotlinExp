package com.yichen.music.mvp.presenter

import com.yichen.music.entity.MusicMvListEntity
import com.yichen.music.mvp.MvListContract
import com.yichen.music.service.MvListService
import javax.inject.Inject

/**
 * Created by Chen on 2019/3/12
 */
class MvListPresenter @Inject constructor() : MvListContract.Presenter, MvListService.GetMvListCallBack {

    @Inject
    lateinit var mView: MvListContract.View

    private var isLoading = false

    @Inject
    lateinit var mService: MvListService

    override fun getMvList(limit: Int, offset: Int, state: Int, isLoading: Boolean) {
        this.isLoading = isLoading
        if (isLoading) {
            mView.showLoading()
        }
        mService.getMvList(mView.getViewContext(), limit, offset, state, this)
    }

    override fun onMvList(datas: List<MusicMvListEntity>, state: Int) {
        mView.showMvList(datas, state)
        if (isLoading) {
            mView.hideLoading()
            isLoading = false
        }
    }

    override fun onFail() {
        if (isLoading) {
            mView.hideLoading()
        }
    }

}