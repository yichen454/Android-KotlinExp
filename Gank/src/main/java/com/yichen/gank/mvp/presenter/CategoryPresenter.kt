package com.yichen.gank.mvp.presenter

import com.yichen.gank.entity.GankItemData
import com.yichen.gank.mvp.CategoryContract
import com.yichen.gank.mvp.service.CategoryService
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/13
 */
class CategoryPresenter @Inject constructor() : CategoryContract.Presenter, CategoryService.GetCategoryGankCallBack {

    @Inject
    lateinit var mView: CategoryContract.View

    private var isLoading = false

    @Inject
    lateinit var mService: CategoryService

    override fun getCategoryGanks(type: String, page: Int, state: Int, isLoading: Boolean) {
        this.isLoading = isLoading
        if (isLoading) {
            mView.showLoading()
        }
        mService.getCategoryGanks(mView.getViewContext(), type, page, state, this)
    }

    override fun onCategoryGanks(datas: List<GankItemData>, state: Int) {
        mView.showGanks(datas, state)
        if (isLoading) {
            mView.hideLoading()
        }
    }


}