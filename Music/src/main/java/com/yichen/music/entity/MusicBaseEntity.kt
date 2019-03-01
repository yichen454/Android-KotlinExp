package com.yichen.music.entity

/**
 * Created by Chen on 2019/2/28
 */
data class MusicBaseEntity<T>(var code: Int, var result: String, var data: T)