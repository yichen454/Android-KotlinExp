package com.yichen.gank.net

import com.yichen.gank.entity.GankItemData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import com.yichen.gank.entity.BaseResult

/**
 * Created by Chen on 2016/12/13
 */

interface GankNetService {

    @GET(GankApi.GET_GANK_HISTORY)
    fun gankDay(): Observable<BaseResult<String>>

    @GET(GankApi.GET_GANK_DAY)
    fun getDayDetail(@Path("time") time: String): Observable<BaseResult<String>>

    @GET(GankApi.GET_GANK_INFO)
    fun getGankInfo(@Path("type") type: String, @Path("num") num: Int, @Path("page") page: Int): Observable<BaseResult<List<GankItemData>>>
}
