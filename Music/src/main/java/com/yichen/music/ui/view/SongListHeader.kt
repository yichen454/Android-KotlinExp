package com.yichen.music.ui.view

import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.yichen.common.utils.ImageLoader
import com.yichen.common.utils.ScreenUtils
import com.yichen.common.widgets.NiceImageView
import com.yichen.music.R
import com.yichen.music.adapter.SongListHeaderAdapter

/**
 * Created by Chen on 2019/3/6
 */
class SongListHeader(layoutInflater: LayoutInflater, private val viewGroup: ViewGroup) {

    val view: View = layoutInflater.inflate(R.layout.music_header_songlist, viewGroup, false)

    fun initView() {
        val vp: ViewPager = view.findViewById(R.id.mViewPager)
        vp.adapter = SongListHeaderAdapter(getImgList(), vp)
    }

    private fun getImgList(): List<View> {
        val list = ArrayList<View>()
        val ids = intArrayOf(
            R.drawable.header_1,
            R.drawable.header_2,
            R.drawable.header_3,
            R.drawable.header_4,
            R.drawable.header_5,
            R.drawable.header_6,
            R.drawable.header_7,
            R.drawable.header_8
        )

        val ss = ScreenUtils.getScreenSize(viewGroup.context)

        for (i: Int in 0..7) {
            val ll = LinearLayout(viewGroup.context)
            ll.layoutParams = ViewGroup.LayoutParams(ss[0], ss[0] / 3)
            val imageView = NiceImageView(viewGroup.context)
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            lp.setMargins(20, 0, 20, 0)
            imageView.layoutParams = lp
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.setCornerRadius(5)
            ImageLoader.loadUrlImage(viewGroup.context, ids[i], imageView)
            ll.addView(imageView)
            list.add(ll)
        }
        return list
    }
}