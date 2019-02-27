package com.yichen.kotlin.ui

import com.yichen.common.base.AppManager
import com.yichen.common.ui.activity.BaseActivity
import com.yichen.common.ui.fragment.BaseFragment
import com.yichen.common.utils.ToastUtils
import com.yichen.kotlin.R
import com.yichen.provider.router.component.Router
import com.yichen.provider.router.service.GankService
import com.yichen.provider.router.service.SummerService
import kotlinx.android.synthetic.main.app_activity_main.*
import java.util.*

class MainActivity : BaseActivity() {
    private var clickTime: Long = 0
    private val mStack = Stack<BaseFragment>()
    private var mGankFragment: BaseFragment? = null
    private var mSummerFragment: BaseFragment? = null

    override fun initOperate() {
        setStatusBar()
    }

    override fun initView() {
        initFragment()
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.app_activity_main
    }

    private fun initFragment() {
        val bt = supportFragmentManager.beginTransaction()
        val router = Router.instance
        val gankService = router.getService(GankService::class.java.simpleName)
        val summerService = router.getService(SummerService::class.java.simpleName)
        if (gankService != null) {
            mGankFragment = (gankService as GankService).getGankFragment()
            if (mGankFragment != null) {
                bt.add(R.id.rootLay, mGankFragment!!)
                mStack.add(mGankFragment)
            }
        }

        if (summerService != null) {
            mSummerFragment = (summerService as SummerService).getSummerFragment()
            if (mSummerFragment != null) {
                bt.add(R.id.rootLay, mSummerFragment!!)
                mStack.add(mSummerFragment)
            }
        }


        if (!mStack.isEmpty())
            bt.commit()
    }

    override fun setListener() {
        bottomBar.setOnTabSelectListener {
            when (it) {
                R.id.tab_gank -> {
                    changeFragment(0)
                }
                R.id.tab_summer -> {
                    changeFragment(1)
                }
            }
        }
    }

    private fun changeFragment(position: Int) {
        if (position >= mStack.size) {
            return
        }
        val bt = supportFragmentManager.beginTransaction()
        mStack.forEach {
            bt.hide(it)
        }
        bt.show(mStack[position])
        bt.commit()
    }

    private fun exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
            ToastUtils.instance.showToast("再按一次退出程序")
            clickTime = System.currentTimeMillis()
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    override fun onBackPressed() {
        exit()
    }
}
