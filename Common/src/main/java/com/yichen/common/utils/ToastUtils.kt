package com.yichen.common.utils

import android.widget.Toast
import com.yichen.common.base.BaseApplication

/**
 * Created by Chen on 2019/2/13
 */
class ToastUtils {

    private var mToast: Toast

    private constructor() {
        mToast = Toast.makeText(BaseApplication.context, "", Toast.LENGTH_SHORT)
    }

    companion object {
        val instance: ToastUtils by lazy { ToastUtils() }
    }

    /**
     * 显示Toast
     */
    fun showToast(resId: Int) {
        mToast.setText(resId)
        mToast.show()
    }

    /**
     * 显示Toast
     */
    fun showToast(tostMsg: String) {
        mToast.setText(tostMsg)
        mToast.show()
    }

    fun destroy() {
        mToast.cancel()
    }

}