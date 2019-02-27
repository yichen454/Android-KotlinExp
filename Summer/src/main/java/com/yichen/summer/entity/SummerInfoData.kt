package com.yichen.summer.entity

/**
 * Created by Chen on 2019/2/18
 */
data class SummerInfoData(
    var id: String,
    var question_type: Int,
    var content_type: Int,
    var content: String,
    var images: List<SummerImage>,
    var created_at: String,
    var published_at: String,
    var school_limit: Boolean,
    var point_name: String,
    var anonymous_user: SummerSimpleUser,
    var answers_count: Int,
    var comments_count: Int,
    var votes_count: Int,
    var voted: Boolean,
    var favorite: Boolean,
    var user: SummerSimpleUser,
    var on_off: Boolean,
    var commented: Boolean,
    var anonymous: Boolean
)