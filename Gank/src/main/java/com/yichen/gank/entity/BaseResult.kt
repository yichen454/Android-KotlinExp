package com.yichen.gank.entity

/**
 * Created by Chen on 2019/2/14
 */
data class BaseResult<T>(var error: Boolean, var results: T)