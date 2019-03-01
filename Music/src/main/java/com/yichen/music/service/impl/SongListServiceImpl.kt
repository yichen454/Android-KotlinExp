package com.yichen.music.service.impl

import android.text.TextUtils
import com.yichen.music.entity.MusicSongListEntity
import com.yichen.music.net.MusicApi
import com.yichen.music.net.MusicApiClient
import com.yichen.music.net.MusicSubscriber
import com.yichen.music.service.SongListService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Chen on 2019/2/28
 */
class SongListServiceImpl : SongListService {
    override fun getSongList(
        viewContext: Any,
        limit: Int,
        offset: Int,
        order: String,
        callBack: SongListService.GetSongListCallBack
    ) {
        val params: HashMap<String, String> = HashMap()
        params["key"] = MusicApi.KEY
        params["limit"] = limit.toString()
        params["offset"] = offset.toString()
        params["order"] = order

        MusicApiClient.INSTANCE.netService.getSongList(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MusicSubscriber<List<MusicSongListEntity>>() {
                override fun onSuccess(response: List<MusicSongListEntity>) {
                    callBack.onSongList(response)
                }

                override fun onFail(status: Int, msg: String) {
                }

            })
    }

}