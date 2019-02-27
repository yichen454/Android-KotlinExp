package com.yichen.common.ui.activity

import com.yichen.common.base.BaseApplication
import com.yichen.common.injection.component.DaggerActivityComponent
import com.yichen.common.injection.module.ActivityModule
import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.common.widgets.ProgressLoading
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/13
 */
abstract class BaseMvpActivity<T : BasePresenter> : BaseActivity(), BaseView {

    @Inject
    lateinit var mPresenter: T

    lateinit var mActivityComponent: DaggerActivityComponent

    lateinit var mProgressLoading: ProgressLoading

    override fun initOperate() {
        initActivityInjection()
        injectComponent()
        mProgressLoading = ProgressLoading.create(this)

    }

    private fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .appComponent((application as BaseApplication).appComponent)
            .build() as DaggerActivityComponent
    }

    override fun showLoading() {
        mProgressLoading.showLoading()
    }

    override fun hideLoading() {
        mProgressLoading.hideLoading()
    }

    /**
     * 注册依赖对象
     */
    abstract fun injectComponent()

}