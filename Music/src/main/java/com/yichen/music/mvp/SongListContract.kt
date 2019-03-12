package com.yichen.music.mvp

import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.common.mvp.presenter.BasePresenter
import com.yichen.common.mvp.view.BaseView
import com.yichen.music.entity.MusicSongListEntity

/**
 * Created by Chen on 2019/2/28
 */
interface SongListContract {

    interface View : BaseView {

        fun showSongList(datas: List<MusicSongListEntity>)

        fun getViewContext(): RxFragment
    }

    interface Presenter : BasePresenter {
        fun getSongList(limit: Int, offset: Int, order: String)
    }
}