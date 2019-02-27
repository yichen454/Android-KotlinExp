package com.yichen.gank

import android.os.Bundle
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.yichen.gank.ui.fragment.GankFragment

/**
 * 单独测试Activity
 */
class TestActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gank_activity_test)
        supportFragmentManager.beginTransaction().replace(R.id.flay_root,  GankFragment()).commit()
    }
}