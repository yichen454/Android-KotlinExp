package com.yichen.music.service

import com.yichen.music.entity.MusicMvListEntity

/**
 * Created by Chen on 2019/3/12
 */
interface MvListService {
    fun getMvList(
        viewContext: Any,
        limit: Int,
        offset: Int,
        state:Int,
        callBack: GetMvListCallBack
    )


    interface GetMvListCallBack {
        fun onMvList(datas: List<MusicMvListEntity>,state: Int)

        fun onFail()
    }
}