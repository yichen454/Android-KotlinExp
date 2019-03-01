package com.yichen.music.net

import android.net.ParseException
import com.google.gson.JsonParseException
import com.yichen.common.net.ExceptionReason
import com.yichen.common.utils.ToastUtils
import com.yichen.music.R
import com.yichen.music.entity.MusicBaseEntity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Created by Chen on 2019/2/28
 */
abstract class MusicSubscriber<T> : Observer<MusicBaseEntity<T>> {
    override fun onComplete() {
    }

    override fun onSubscribe(d: Disposable) {
        onStart()
    }

    override fun onNext(t: MusicBaseEntity<T>) {
        if (t.code == 200) {
            onSuccess(t.data)
        } else {
            onFail(0, "数据异常")
        }
    }

    override fun onError(e: Throwable) {
        onFail(0, "请求错误")
        if (e is HttpException) {
            onException(ExceptionReason.BAD_NETWORK)
        } else if (e is ConnectException
            || e is UnknownHostException
        ) {
            onException(ExceptionReason.CONNECT_ERROR)
        } else if (e is InterruptedIOException) {
            onException(ExceptionReason.CONNECT_TIMEOUT)
        } else if (e is JsonParseException
            || e is JSONException
            || e is ParseException
        ) {
            onException(ExceptionReason.PARSE_ERROR)
        } else {
            onException(ExceptionReason.UNKNOWN_ERROR)
        }
    }

    private fun onException(reason: ExceptionReason) {
        when (reason) {
            ExceptionReason.CONNECT_ERROR -> ToastUtils.instance.showToast(R.string.connect_error)
            ExceptionReason.CONNECT_TIMEOUT -> ToastUtils.instance.showToast(R.string.connect_timeout)
            ExceptionReason.BAD_NETWORK -> ToastUtils.instance.showToast(R.string.bad_network)
            ExceptionReason.PARSE_ERROR -> ToastUtils.instance.showToast(R.string.parse_error)
            ExceptionReason.UNKNOWN_ERROR -> ToastUtils.instance.showToast(R.string.unknown_error)
            else -> ToastUtils.instance.showToast(R.string.unknown_error)
        }
    }

    /**
     * 访问成功
     */
    abstract fun onSuccess(response: T)

    /**
     * 访问失败
     */
    abstract fun onFail(status: Int, msg: String)

    /**
     * 开始访问
     */
    fun onStart() {}
}