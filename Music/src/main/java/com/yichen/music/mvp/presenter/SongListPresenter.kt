package com.yichen.music.mvp.presenter

import com.yichen.music.entity.MusicSongListDetailEntity
import com.yichen.music.entity.MusicSongListEntity
import com.yichen.music.mvp.SongListContract
import com.yichen.music.mvp.service.SongListService
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/28
 */
class SongListPresenter @Inject constructor() : SongListContract.Presenter,
    SongListService.GetSongListCallBack,
    SongListService.GetSongListDetailCallBack {

    @Inject
    lateinit var mView: SongListContract.View

    @Inject
    lateinit var mService: SongListService

    override fun getSongList(limit: Int, offset: Int, order: String) {
        mView.showLoading()
        mService.getSongList(mView.getViewContext(), limit, offset, order, this)
    }

    override fun getSongListDetail(id: Long) {
        mView.showLoading()
        mService.getSongListDetail(mView.getViewContext(), id, this)
    }

    override fun onSongList(datas: List<MusicSongListEntity>) {
        mView.showSongList(datas)
        mView.hideLoading()
    }

    override fun onDetail(data: MusicSongListDetailEntity) {
        mView.showSongListDetail(data)
        mView.hideLoading()
    }

    override fun onFail() {
        mView.hideLoading()
    }


}