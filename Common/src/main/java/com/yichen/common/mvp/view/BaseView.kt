package com.yichen.common.mvp.view

/**
 * Created by Chen on 2019/2/13
 */
open interface BaseView {
    /**
     * 显示Loading
     */
    fun showLoading()

    /**
     * 隐藏Loading
     */
    fun hideLoading()

    /**
     * 访问错误
     */
    fun error() {}

    /**
     * 空数据
     */
    fun empty() {}

}