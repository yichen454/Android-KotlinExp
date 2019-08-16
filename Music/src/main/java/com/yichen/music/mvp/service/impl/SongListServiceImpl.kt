package com.yichen.music.mvp.service.impl

import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import com.yichen.music.entity.MusicSongListDetailEntity
import com.yichen.music.entity.MusicSongListEntity
import com.yichen.music.net.MusicApi
import com.yichen.music.net.MusicApiClient
import com.yichen.music.net.MusicSubscriber
import com.yichen.music.mvp.service.SongListService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Chen on 2019/2/28
 */
class SongListServiceImpl @Inject constructor() : SongListService {

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
            .compose((viewContext as RxFragment).bindUntilEvent(FragmentEvent.DESTROY))
            .subscribe(object : MusicSubscriber<List<MusicSongListEntity>>() {
                override fun onSuccess(response: List<MusicSongListEntity>) {
                    callBack.onSongList(response)
                }

                override fun onFail(status: Int, msg: String) {
                    callBack.onFail()
                }

            })
    }

    override fun getSongListDetail(viewContext: Any, id: Long, callBack: SongListService.GetSongListDetailCallBack) {
        val params: HashMap<String, String> = HashMap()
        params["key"] = MusicApi.KEY
        params["id"] = id.toString()
        MusicApiClient.INSTANCE.netService.getSongListDetail(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose((viewContext as RxAppCompatActivity).bindUntilEvent(ActivityEvent.DESTROY))
            .subscribe(object : MusicSubscriber<MusicSongListDetailEntity>() {
                override fun onSuccess(response: MusicSongListDetailEntity) {
                    callBack.onDetail(response)
                }

                override fun onFail(status: Int, msg: String) {
                    callBack.onFail()
                }

            })
    }

}