package com.yichen.summer.mvp.presenter

import com.yichen.summer.entity.SummerInfoData
import com.yichen.summer.mvp.TypeContract
import com.yichen.summer.service.TypeService
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/18
 */
class TypePresenter @Inject constructor() : TypeContract.Presenter, TypeService.GetSummerInfoCallBack {

    @Inject
    lateinit var mView: TypeContract.View

    private var isLoading = false

    @Inject
    lateinit var mService: TypeService

    override fun getSummerInfo(
        type: String,
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
        mService.getSummerInfo(mView.getTypeContext()!!, type, offset, since, sort, state, this)
    }

    override fun onSummerInfo(datas: List<SummerInfoData>, state: Int) {
        mView.showSummerInfo(datas, state)
        if (isLoading) {
            mView.hideLoading()
        }
    }


    override fun searchSummerInfo(type: String, tag: String, offset: Int, state: Int, isLoading: Boolean) {
        this.isLoading = isLoading
        if (isLoading) {
            mView.showLoading()
        }
        mService.searchSummerInfo(mView.getSearchContext()!!, type, tag, offset, state, this)
    }
}