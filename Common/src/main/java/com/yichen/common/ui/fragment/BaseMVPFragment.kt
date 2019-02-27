package com.yichen.common.ui.fragment

import android.app.Activity
import com.yichen.common.base.BaseApplication
import com.yichen.common.injection.component.DaggerActivityComponent
import com.yichen.common.injection.module.ActivityModule
import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.common.utils.LoadingHelper
import com.yichen.common.widgets.ProgressLoading
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/13
 */
abstract class BaseMvpFragment<T : BasePresenter> : BaseFragment(), BaseView {

    @Inject
    lateinit var mPresenter: T
    lateinit var mProgressLoading: ProgressLoading
    lateinit var mActivityComponent: DaggerActivityComponent

    override fun initOperate() {
        initActivityInjection()
        injectComponent()
    }

    /** 注册依赖关系 */
    abstract fun injectComponent()

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
            .appComponent((activity?.application as BaseApplication).appComponent)
            .activityModule(ActivityModule(activity as Activity))
            .build() as DaggerActivityComponent
    }

    override fun showLoading() {
        LoadingHelper.showLoading(activity)
    }

    override fun hideLoading() {
        LoadingHelper.hideLoading(activity)
    }

    override fun error() {
        mStatusLayoutManager.showErrorLayout()
    }

    override fun empty() {
        mStatusLayoutManager.showEmptyLayout()
    }

}