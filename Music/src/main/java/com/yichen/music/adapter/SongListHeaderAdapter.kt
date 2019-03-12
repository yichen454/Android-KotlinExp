package com.yichen.music.adapter

import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.View
import android.view.ViewGroup

/**
 * Created by Chen on 2019/3/6
 */
class SongListHeaderAdapter(private val imageList: List<View>, private val vp: ViewPager) : PagerAdapter() {

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val iv = imageList[position % imageList.size]
        vp.addView(iv)
        return iv
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        vp.removeView(imageList[position % imageList.size])
    }
}