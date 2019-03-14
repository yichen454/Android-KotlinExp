package com.yichen.music.entity

/**
 * Created by Chen on 2019/3/13
 */
data class MusicSongListDetailEntity(
    var songListId: String,
    var songListName: String,
    var songListPic: String,
    var songListCount: Int,
    var songListPlayCount: Int,
    var songListDescription: String,
    var songListUserId: Long,
    var songs: List<SongEntity>
) {
     data class SongEntity(
        var id: String,
        var name: String,
        var singer: String,
        var pic: String,
        var lrc: String,
        var url: String,
        var time: Int
    )
}