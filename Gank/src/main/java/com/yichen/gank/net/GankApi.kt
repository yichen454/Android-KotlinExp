package com.yichen.gank.net

/**
 * Created by Chen on 16/8/8
 */

object GankApi {

    const val BASE_URL = "http://gank.io/"

    //获取干货分类数据
    const val GET_GANK_INFO = "api/data/{type}/{num}/{page}"

    //获取发过干货的日期
    const val GET_GANK_HISTORY = "api/day/history"

    //获取某日发的干货数据
    const val GET_GANK_DAY = "api/day/{time}"
}
