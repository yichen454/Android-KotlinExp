package com.yichen.gank.ui.fragment

import android.os.Bundle
import com.yichen.common.ui.fragment.BaseFragment
import com.yichen.gank.R
import com.yichen.gank.adapter.CategoryPagerAdapter
import com.yichen.gank.common.Constant
import kotlinx.android.synthetic.main.gank_fragment_home.*

/**
 * Created by Chen on 2019/2/15
 */
class GankFragment : BaseFragment() {

    private val mTabTitles = arrayOf("Android", "iOS", "前端", "拓展资源", "瞎推荐")
    private val mFragments = ArrayList<CategoryFragment>()

    override fun getLayoutId(): Int {
        return R.layout.gank_fragment_home
    }

    override fun initView() {
        createFragment(Constant.CATEGORY_ANDROID)
        createFragment(Constant.CATEGORY_IOS)
        createFragment(Constant.CATEGORY_WEB)
        createFragment(Constant.CATEGORY_EXPAND)
        createFragment(Constant.CATEGORY_RECOMMEND)

        mGankViewPager.offscreenPageLimit = mFragments.size
        mGankViewPager.adapter = CategoryPagerAdapter(mFragments, mTabTitles, fragmentManager!!)
        mGankTabLayout.setupWithViewPager(mGankViewPager)
    }

    private fun createFragment(type: String) {
        val fragment = CategoryFragment()
        val bundle = Bundle()
        bundle.putString(Constant.CATEGORY, type)
        fragment.arguments = bundle
        mFragments.add(fragment)
    }

    override fun initData() {

    }

}