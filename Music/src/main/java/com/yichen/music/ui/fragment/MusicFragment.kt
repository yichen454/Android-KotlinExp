package com.yichen.music.ui.fragment

import com.yichen.common.ui.fragment.BaseFragment
import com.yichen.music.R
import com.yichen.music.adapter.MusicPagerAdapter
import kotlinx.android.synthetic.main.music_fragment_home.*

/**
 * Created by Chen on 2019/2/28
 */
class MusicFragment : BaseFragment() {

    private val mTabTitles = arrayOf("歌单", "短片")
    private val mFragments = ArrayList<BaseFragment>()

    override fun initView() {
        mFragments.add(SongListFragment())
        mFragments.add(MvFragment())

        mMusicViewPager.offscreenPageLimit = mFragments.size
        mMusicViewPager.adapter = MusicPagerAdapter(mFragments, mTabTitles, fragmentManager!!)
        mMusicTabLayout.setupWithViewPager(mMusicViewPager)
    }

    override fun initData() {

    }

    override fun getLayoutId(): Int {
        return R.layout.music_fragment_home
    }

}