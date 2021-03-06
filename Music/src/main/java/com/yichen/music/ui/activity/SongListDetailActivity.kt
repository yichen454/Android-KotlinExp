package com.yichen.music.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.yichen.common.ui.activity.BaseMvpActivity
import com.yichen.common.utils.ImageLoader
import com.yichen.music.R
import com.yichen.music.adapter.SongsAdapter
import com.yichen.music.common.Constant
import com.yichen.music.entity.MusicSongListDetailEntity
import com.yichen.music.entity.MusicSongListEntity
import com.yichen.music.injection.component.DaggerSongListComponent
import com.yichen.music.injection.module.SongListModule
import com.yichen.music.mvp.SongListContract
import com.yichen.music.mvp.presenter.SongListPresenter
import com.yichen.music.service.MusicPlayService
import com.yichen.music.service.MusicServiceStub
import kotlinx.android.synthetic.main.music_activity_songlist_detail.*
import kotlinx.android.synthetic.main.music_head_songlist_detail.*

/**
 * Created by Chen on 2019/2/28
 */
class SongListDetailActivity : BaseMvpActivity<SongListPresenter>(), SongListContract.View {

    private lateinit var songsAdapter: SongsAdapter
    private lateinit var title: String
    private lateinit var onOffsetChangedListener: AppBarLayout.OnOffsetChangedListener
    private var mPlayService: MusicPlayService? = null

    override fun injectComponent() {
        DaggerSongListComponent.builder()
            .activityComponent(mActivityComponent)
            .songListModule(SongListModule(this))
            .build().inject(this)
        setStatusBar()
        bindService(
            Intent(applicationContext, MusicPlayService::class.java),
            connection, Context.BIND_AUTO_CREATE
        )
    }

    override fun initView() {
        mRecyclerView.isPullRefreshEnabled = false
        mRecyclerView.isLoadingMoreEnabled = false
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        songsAdapter = SongsAdapter(this)
        mRecyclerView.adapter = songsAdapter
    }

    override fun initData() {
        val id = intent.getLongExtra(Constant.KEY_ID, 0)
        mPresenter.getSongListDetail(id)
        title = intent.getStringExtra(Constant.KEY_TITLE)
        val pic = intent.getStringExtra(Constant.KEY_PIC)
        ImageLoader.loadUrlBlurImage(this, pic, iv_head_bg, 0.05f, 10f)
        ImageLoader.loadUrlImage(this, pic, iv_cover)
        tv_title.text = title
    }

    override fun getLayoutId(): Int {
        return R.layout.music_activity_songlist_detail
    }

    override fun setListener() {
        onOffsetChangedListener = AppBarLayout.OnOffsetChangedListener { p0, p1 ->
            val progress: Float = Math.abs(p1) / headRoot.totalScrollRange.toFloat()
            rl_head.alpha = (1 - progress) * 1.5f
            if (progress > 0.5) {
                tv_name.text = title
            } else {
                tv_name.text = "歌单"
            }
        }
        headRoot.addOnOffsetChangedListener(onOffsetChangedListener)

        iv_back.setOnClickListener { finish() }

        songsAdapter.setOnItemClickListener { _, item, _ ->
            item as MusicSongListDetailEntity.SongEntity
            Log.e("sss", item.url)
            mPlayService?.playMusicById(item.url)
        }

    }

    override fun showSongList(datas: List<MusicSongListEntity>) {
    }

    override fun showSongListDetail(data: MusicSongListDetailEntity) {
        songsAdapter.setListAll(data.songs)
    }

    override fun getViewContext(): Any {
        return this
    }

    override fun onBackPressed() {
        mPlayService?.stop()
        super.onBackPressed()
    }


    override fun onDestroy() {
        headRoot.removeOnOffsetChangedListener(onOffsetChangedListener)
        unbindService(connection)
        super.onDestroy()
    }

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder) {
            mPlayService = (service as MusicServiceStub).getService()
        }
    }
}