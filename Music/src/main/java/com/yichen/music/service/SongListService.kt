package com.yichen.music.service

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
}