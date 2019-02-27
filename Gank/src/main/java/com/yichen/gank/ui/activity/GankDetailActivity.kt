package com.yichen.gank.ui.activity

import android.annotation.SuppressLint
import android.net.http.SslError
import android.view.KeyEvent
import android.webkit.*
import com.yichen.common.ui.activity.BaseActivity
import com.yichen.gank.R
import com.yichen.gank.common.Constant
import kotlinx.android.synthetic.main.gank_activity_detail.*

/**
 * Created by Chen on 2019/2/15
 */
class GankDetailActivity : BaseActivity() {

    private lateinit var webView: WebView

    override fun getLayoutId(): Int {
        return R.layout.gank_activity_detail
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        val adUrl = intent.getStringExtra(Constant.DETAIL_URL)
        webView = wb_gank_detail
        webView.loadUrl(adUrl)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                if (error.primaryError == SslError.SSL_DATE_INVALID
                    || error.primaryError == SslError.SSL_EXPIRED
                    || error.primaryError == SslError.SSL_INVALID
                    || error.primaryError == SslError.SSL_UNTRUSTED
                ) {
                    handler.proceed()
                } else {
                    handler.cancel()
                }
                super.onReceivedSslError(view, handler, error)
            }
        }
        webView.settings.javaScriptEnabled = true
    }

    override fun initData() {
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        webView.clearFocus()
        webView.stopLoading()
        webView.destroy()
        super.onDestroy()
    }

}
