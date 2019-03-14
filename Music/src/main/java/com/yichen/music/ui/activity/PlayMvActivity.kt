package com.yichen.music.ui.activity

import android.content.pm.ActivityInfo
import android.view.View
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.yichen.common.ui.activity.BaseActivity
import com.yichen.music.R
import com.yichen.music.common.Constant
import kotlinx.android.synthetic.main.music_activity_playmv.*

/**
 * Created by Chen on 2019/3/13
 */
class PlayMvActivity : BaseActivity() {

    private lateinit var orientationUtils: OrientationUtils

    override fun initView() {
        setStatusBar()
        //增加title
        video_player.titleTextView.visibility = View.VISIBLE
        //设置返回键
        video_player.backButton.visibility = View.VISIBLE
        //设置旋转
        orientationUtils = OrientationUtils(this, video_player)
        video_player.fullscreenButton.setOnClickListener { orientationUtils.resolveByClick() }
        video_player.setIsTouchWiget(true)
        video_player.backButton.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initData() {
        val title: String = intent.getStringExtra(Constant.KEY_TITLE)
        val url: String = intent.getStringExtra(Constant.KEY_URL)
        video_player.setUp(url, false, title)
        video_player.startPlayLogic()
    }

    override fun getLayoutId(): Int {
        return R.layout.music_activity_playmv
    }

    override fun onPause() {
        super.onPause()
        video_player.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        video_player.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        orientationUtils.releaseListener()
    }

    override fun onBackPressed() {
        //先返回正常状态
        if (orientationUtils.screenType == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            video_player.fullscreenButton.performClick()
            return
        }
        //释放所有
        video_player.setVideoAllCallBack(null)
        super.onBackPressed()
    }

}