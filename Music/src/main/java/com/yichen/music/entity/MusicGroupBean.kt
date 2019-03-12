package com.yichen.music.entity

/**
 * Created by Chen on 2019/3/4
 */
data class MusicGroupBean<K, V>(
    var title: K,
    var childern: List<V>
)