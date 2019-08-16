package com.yichen.music.mvp.service.impl

import com.yichen.music.entity.MusicMvListEntity
import com.yichen.music.net.MusicApi
import com.yichen.music.net.MusicApiClient
import com.yichen.music.net.MusicSubscriber
import com.yichen.music.mvp.service.MvListService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Chen on 2019/3/12
 */
class MvListServiceImpl @Inject constructor() : MvListService {
    override fun getMvList(
        viewContext: Any,
        limit: Int,
        offset: Int,
        state: Int,
        callBack: MvListService.GetMvListCallBack
    ) {
        val params: HashMap<String, String> = HashMap()
        params["key"] = MusicApi.KEY
        params["limit"] = limit.toString()
        params["offset"] = offset.toString()

        MusicApiClient.INSTANCE.netService.getMvList(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MusicSubscriber<List<MusicMvListEntity>>() {
                override fun onSuccess(response: List<MusicMvListEntity>) {
                    callBack.onMvList(response, state)
                }

                override fun onFail(status: Int, msg: String) {
                    callBack.onFail()
                }

            })
    }

}