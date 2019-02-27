package com.yichen.common.ui.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.common.base.AppManager

/**
 * Created by Chen on 2019/2/13
 */
abstract class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val rootView = LayoutInflater.from(this).inflate(getLayoutId(), null)
        setContentView(rootView)
        AppManager.instance.addActivity(this)
        initOperate()
        initView()
        initData()
        setListener()
    }


    abstract fun initView()

    abstract fun initData()

    /** 设置监听 */
    open fun setListener() {

    }

    /**
     * 初始化操作，在onCreate中调用
     */
    open fun initOperate() {

    }

    /**
     * 设置布局id
     */
    abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    /**
     * 设置透明状态栏
     * @param isDarkColor Boolean ：状态栏文字颜色是否为暗色
     */
    fun setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            //根据上面设置是否对状态栏单独设置颜色
            window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            val localLayoutParams = window.attributes
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags)
        }
    }

}
