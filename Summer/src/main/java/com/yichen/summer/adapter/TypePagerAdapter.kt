package com.yichen.summer.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yichen.summer.ui.fragment.TypeFragment

/**
 * Created by Chen on 2019/2/18
 */
class TypePagerAdapter(
    val fragments: List<TypeFragment>,
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