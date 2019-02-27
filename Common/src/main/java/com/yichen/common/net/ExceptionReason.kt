package com.yichen.common.net

/**
 * @descriptio：请求错误原因
 */
enum class ExceptionReason {
    /**
     * 解析数据失败
     */
    PARSE_ERROR,
    /**
     * 网络问题
     */
    BAD_NETWORK,
    /**
     * 连接错误
     */
    CONNECT_ERROR,
    /**
     * 连接超时
     */
    CONNECT_TIMEOUT,
    /**
     * 未知错误
     */
    UNKNOWN_ERROR
}