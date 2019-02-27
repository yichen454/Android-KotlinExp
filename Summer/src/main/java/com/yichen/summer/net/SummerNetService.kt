package com.yichen.summer.net

import com.yichen.summer.entity.SummerCommentData
import com.yichen.summer.entity.SummerInfoData
import com.yichen.summer.entity.SummerUserData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

/**
 * Created by Chen on 2019/2/18
 */
interface SummerNetService {

    @GET(SummerApi.GET_SUMMER_INFO)
    fun getSummerInfo(
        @Path("type", encoded = true) type: String,
        @QueryMap params: Map<String, String>
    ): Observable<List<SummerInfoData>>

    @GET(SummerApi.GET_USER_INFO)
    fun getUserInfo(@Path("uid", encoded = true) uid: String): Observable<SummerUserData>

    @GET(SummerApi.GET_BLACK_ANSWERS)
    fun getCommentsBlack(
        @Path(
            "qid",
            encoded = true
        ) qid: String, @QueryMap params: Map<String, String>
    ): Observable<List<SummerCommentData>>

    @GET(SummerApi.GET_SECRETS_COMMENTS)
    fun getCommentsSecret(
        @Path(
            "qid",
            encoded = true
        ) pid: String, @QueryMap params: Map<String, String>
    ): Observable<List<SummerCommentData>>

    @GET(SummerApi.GET_ACTIVITIES_COMMENTS)
    fun getCommentsActivity(
        @Path(
            "qid",
            encoded = true
        ) pid: String, @QueryMap params: Map<String, String>
    ): Observable<List<SummerCommentData>>
}