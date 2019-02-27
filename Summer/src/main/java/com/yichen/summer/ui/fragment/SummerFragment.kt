package com.yichen.summer.ui.fragment

import android.content.Intent
import android.os.Bundle
import com.yichen.common.ui.fragment.BaseFragment
import com.yichen.summer.R
import com.yichen.summer.adapter.TypePagerAdapter
import com.yichen.summer.common.Constant
import com.yichen.summer.ui.activity.SearchActivity
import kotlinx.android.synthetic.main.summer_fragment_home.*

/**
 * Created by Chen on 2019/2/18
 */
class SummerFragment : BaseFragment() {

    private val mTabTitles = arrayOf("黑板墙", "兔子洞", "校内")
    private val mFragments = ArrayList<TypeFragment>()

    override fun getLayoutId(): Int {
        return R.layout.summer_fragment_home
    }

    override fun initView() {
        createFragment(Constant.TYPE_BLACK)
        createFragment(Constant.TYPE_SECRET)
        createFragment(Constant.TYPE_ACTIVITY)

        mSummerViewPager.offscreenPageLimit = mFragments.size
        mSummerViewPager.adapter = TypePagerAdapter(mFragments, mTabTitles, fragmentManager!!)
        mSummerTabLayout.setupWithViewPager(mSummerViewPager)
    }

    private fun createFragment(type: String) {
        val fragment = TypeFragment()
        val bundle = Bundle()
        bundle.putString(Constant.TYPE, type)
        fragment.arguments = bundle
        mFragments.add(fragment)
    }

    override fun initData() {
    }

    override fun setListener() {
        super.setListener()
        iv_search.setOnClickListener {
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
        }
    }

}
