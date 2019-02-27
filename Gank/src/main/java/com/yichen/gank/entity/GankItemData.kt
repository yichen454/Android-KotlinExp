package com.yichen.gank.entity

/**
 * Created by Chen on 2019/2/13
 */
data class GankItemData(
    var _id: String,
    var desc: String,
    var createAt: String,
    var publishedAt: String,
    var soure: String,
    var type: String,
    var url: String,
    var used: Boolean,
    var who: String,
    var images: List<String>
)