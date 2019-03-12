package com.yichen.music.entity

/**
 * Created by Chen on 2019/3/12
 */
data class MusicMvListEntity(
    var id: String,
    var name: String,
    var singer: String,
    var desc: String,
    var playCount: Int,
    var pic: String,
    var url: String
)