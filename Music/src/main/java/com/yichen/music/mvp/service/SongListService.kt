package com.yichen.music.mvp.service

import com.yichen.music.entity.MusicSongListDetailEntity
import com.yichen.music.entity.MusicSongListEntity

/**
 * Created by Chen on 2019/2/28
 */
interface SongListService {
    fun getSongList(
        viewContext: Any,
        limit: Int,
        offset: Int,
        order: String,
        callBack: GetSongListCallBack
    )

    interface GetSongListCallBack {
        fun onSongList(datas: List<MusicSongListEntity>)

        fun onFail()
    }

    fun getSongListDetail(
        viewContext: Any,
        id: Long,
        callBack: GetSongListDetailCallBack
    )


    interface GetSongListDetailCallBack {
        fun onDetail(data: MusicSongListDetailEntity)

        fun onFail()
    }
}