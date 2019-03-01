package com.yichen.music.entity

/**
 * Created by Chen on 2019/2/28
 */
data class MusicSongListEntity(
    var id: Long,
    var title: String,
    var creator: String,
    var description: String,
    var coverImgUrl: String,
    var songNum: Int,
    var playCount: Int
)