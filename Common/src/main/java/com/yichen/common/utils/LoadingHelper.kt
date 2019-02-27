package com.yichen.common.utils

import android.app.Activity
import com.yichen.common.widgets.ProgressLoading

object LoadingHelper {

    private var mLoading: ProgressLoading? = null
    fun showLoading(context: Activity?) {
        if (context == null) {
            return
        }
        if (mLoading == null) {
            mLoading = ProgressLoading.create(context)
        }
        mLoading?.showLoading()
    }

    fun hideLoading(context: Activity?) {
        if (context == null) {
            return
        }
        if (mLoading == null) {
            mLoading = ProgressLoading.create(context)
        }
        mLoading?.hideLoading()
        mLoading = null
    }
}