package com.yichen.music.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Created by Chen on 2019/3/15
 */
class MusicPlayService : Service() {

    private lateinit var mPlayer: MusicPlayerEngine

    override fun onCreate() {
        super.onCreate()
        mPlayer = MusicPlayerEngine(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return MusicServiceStub(this).asBinder()
    }

    fun playMusic(path: String) {
        mPlayer.setDataSource(path)
    }

    fun playMusicById(url: String) {
        mPlayer.setDataSource(url)
    }

    fun pause() {
        mPlayer.pause()
    }

    fun stop() {
        mPlayer.stop()
    }

    fun playNext() {

    }

    fun playPrev() {

    }

    fun seekTo(position: Int) {
        mPlayer.seekTo(position)
    }

    fun getDuration(): Int {
        return mPlayer.getDuration()
    }

    fun isPlaying(): Boolean {
        return mPlayer.isPlaying()
    }
}