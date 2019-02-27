package com.yichen.common.net.status

import android.view.View

/**
 * @descriptio：状态布局点击监听接口
 */
interface OnStatusLayoutClickListener {

    /**
     * 空布局点击
     * @param view View
     */
    fun onEmptyViewClick(view: View)

    /**
     * 错误布局点击
     * @param view View
     */
    fun onErrorViewClick(view: View)
}