package com.yichen.summer.entity

/**
 * Created by Chen on 2019/2/20
 */
data class SummerCommentData(
    var id: String,
    var answer_type: Int,
    var content_type: Int,
    var content: String,
    var content_utl: String,
    var option_index: Int,
    var show: Boolean,
    var images: List<SummerImage>,
    var user: SummerSimpleUser,
    var anonymous: Boolean,
    var created_at: String,
    var votes_count: Int,
    var voted: Boolean,
    var mute: Boolean,
    var to_user: SummerSimpleUser,
    var anonymous_user: SummerSimpleUser,
    var to_anonymous_user: SummerSimpleUser
)