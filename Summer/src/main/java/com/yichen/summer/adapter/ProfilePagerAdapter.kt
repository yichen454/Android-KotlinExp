package com.yichen.summer.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yichen.common.ui.fragment.BaseFragment

/**
 * Created by Chen on 2019/2/22
 */
class ProfilePagerAdapter(
    val fragments: List<BaseFragment>,
    val titles: Array<String>,
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}