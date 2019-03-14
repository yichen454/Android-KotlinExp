package com.yichen.music.ui.fragment

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.ui.fragment.BaseMvpFragment
import com.yichen.music.R
import com.yichen.music.adapter.SongListAdapter
import com.yichen.music.common.Constant
import com.yichen.music.entity.MusicGroupBean
import com.yichen.music.entity.MusicSongListDetailEntity
import com.yichen.music.entity.MusicSongListEntity
import com.yichen.music.injection.component.DaggerSongListComponent
import com.yichen.music.injection.module.SongListModule
import com.yichen.music.mvp.SongListContract
import com.yichen.music.mvp.presenter.SongListPresenter
import com.yichen.music.ui.activity.SongListDetailActivity
import com.yichen.music.ui.view.SongListHeader
import kotlinx.android.synthetic.main.music_fragment_songlist.*

/**
 * Created by Chen on 2019/3/1
 */
class SongListFragment : BaseMvpFragment<SongListPresenter>(), SongListContract.View {

    private lateinit var mAdapter: SongListAdapter

    override fun injectComponent() {
        DaggerSongListComponent.builder()
            .activityComponent(mActivityComponent)
            .songListModule(SongListModule(this))
            .build().inject(this)
    }

    override fun initView() {
        mRecyclerView.isPullRefreshEnabled = false
        mRecyclerView.isLoadingMoreEnabled = false

        val header = SongListHeader(layoutInflater, mRecyclerView.parent as ViewGroup)
        header.initView()
        mRecyclerView.addHeaderView(header.view)
        mRecyclerView.layoutManager = GridLayoutManager(activity, 3)
        mAdapter = SongListAdapter(activity!!)
        mRecyclerView.adapter = mAdapter
    }

    override fun initData() {
        mPresenter.getSongList(6, 0, "new")
    }

    override fun setListener() {
        mAdapter.setOnChildClickListener { adapter, holder, groupPosition, childPosition, item ->
            item as MusicGroupBean<*, *>
            val clickItem = item.childern[childPosition] as MusicSongListEntity
            val intent = Intent(activity, SongListDetailActivity::class.java)
            intent.putExtra(Constant.KEY_ID, clickItem.id)
            intent.putExtra(Constant.KEY_TITLE, clickItem.title)
            intent.putExtra(Constant.KEY_PIC, clickItem.coverImgUrl)
            startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.music_fragment_songlist
    }

    override fun showSongList(datas: List<MusicSongListEntity>) {
        val groups = ArrayList<MusicGroupBean<String, MusicSongListEntity>>()
        groups.add(MusicGroupBean("推荐歌单", datas))
        mAdapter.groups = groups
    }

    override fun showSongListDetail(data: MusicSongListDetailEntity) {
        //next page
    }

    override fun getViewContext(): RxFragment {
        return this
    }
}