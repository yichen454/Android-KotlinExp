package com.yichen.summer.net

/**
 * Created by Chen on 2019/2/18
 */
object SummerApi {
    const val BASE_URL = "https://imsummer.cn/api/v4/"

    //获取分类数据
    const val GET_SUMMER_INFO = "{type}?"

    //获取黑板墙评论
    const val GET_BLACK_ANSWERS = "questions/{qid}/answers?"

    //获取黑板墙点赞
    const val GET_BLACK_VOTERS = "questions/{qid}/voters"

    //获取兔子洞评论
    const val GET_SECRETS_COMMENTS = "secrets/{qid}/comments?"

    //获取校内评论
    const val GET_ACTIVITIES_COMMENTS = "activities/{qid}/comments?"

    //获取校内点赞
    const val GET_ACVIVITIES_VOTERS = "activities/{qid}"

    //获取用户信息
    const val GET_USER_INFO = "users/{uid}"
}