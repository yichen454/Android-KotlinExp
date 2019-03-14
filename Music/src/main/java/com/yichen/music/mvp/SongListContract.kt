package com.yichen.music.mvp

import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.music.entity.MusicSongListDetailEntity
import com.yichen.music.entity.MusicSongListEntity

/**
 * Created by Chen on 2019/2/28
 */
interface SongListContract {

    interface View : BaseView {

        fun showSongList(datas: List<MusicSongListEntity>)

        fun showSongListDetail(data: MusicSongListDetailEntity)

        fun getViewContext(): Any
    }

    interface Presenter : BasePresenter {
        fun getSongList(limit: Int, offset: Int, order: String)

        fun getSongListDetail(id: Long)
    }
}