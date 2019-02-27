package com.yichen.summer.common

/**
 * Created by Chen on 2019/2/18
 */
class Constant {
    companion object {
        /** 加载更多的状态 */
        const val STATE_LOADMORE = 1
        /** 刷新的状态*/
        const val STATE_REFRESH = 2

        const val SORT_TOP = "top"
        const val SORT_TIME = "time"

        const val TYPE = "type"
        const val TYPE_BLACK = "questions/board"
        const val TYPE_SECRET = "secrets"
        const val TYPE_ACTIVITY = "activities"

        const val PROFILE_KEY = "profile_uid"
        const val PROFILE_DETRAIL_KEY = "profile_user"

        const val COMMENT_KEY = "comment_qid"
    }

}