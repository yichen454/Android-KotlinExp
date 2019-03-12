package com.yichen.music.net

import com.yichen.music.entity.MusicBaseEntity
import com.yichen.music.entity.MusicMvListEntity
import com.yichen.music.entity.MusicSongListEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Chen on 2019/2/28
 */
interface MusicNetService {

    @GET(MusicApi.GET_SONG_LIST)
    fun getSongList(@QueryMap params: Map<String, String>): Observable<MusicBaseEntity<List<MusicSongListEntity>>>
    
    @GET(MusicApi.GET_MV_LIST)
    fun getMvList(@QueryMap params: Map<String, String>): Observable<MusicBaseEntity<List<MusicMvListEntity>>>
}