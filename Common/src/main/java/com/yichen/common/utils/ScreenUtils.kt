package com.yichen.common.utils

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.yichen.common.base.BaseApplication

object ScreenUtils {
    fun getScreenSize(context: Context): IntArray {

        val size = IntArray(2)

        val w = context
            .getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val d = w.defaultDisplay
        val metrics = DisplayMetrics()
        d.getMetrics(metrics)
        // since SDK_INT = 1;
        var widthPixels = metrics.widthPixels
        var heightPixels = metrics.heightPixels
        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
            try {
                widthPixels = Display::class.java.getMethod("getRawWidth")
                    .invoke(d) as Int
                heightPixels = Display::class.java
                    .getMethod("getRawHeight").invoke(d) as Int
            } catch (ignored: Exception) {
            }

        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17)
            try {
                val realSize = Point()
                Display::class.java.getMethod("getRealSize", Point::class.java).invoke(
                    d,
                    realSize
                )
                widthPixels = realSize.x
                heightPixels = realSize.y
            } catch (ignored: Exception) {
            }

        size[0] = widthPixels
        size[1] = heightPixels
        return size
    }

    fun px2dp(px: Int): Int {
        val scale = BaseApplication.context.resources.displayMetrics.density
        return (px / scale + 0.5f).toInt()
    }

    fun dp2px(dp: Int): Int {
        val scale = BaseApplication.context.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}