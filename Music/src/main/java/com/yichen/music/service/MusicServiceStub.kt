package com.yichen.music.service

import com.yichen.music.IMusicService
import java.lang.ref.WeakReference

/**
 * Created by Chen on 2019/3/15
 */
class MusicServiceStub(service: MusicPlayService) : IMusicService.Stub() {

    private var mService: WeakReference<MusicPlayService> = WeakReference(service)

    override fun playMusic(path: String) {
        mService.get()?.playMusic(path)
    }

    override fun playMusicById(id: String) {
        mService.get()?.playMusicById(id)
    }

    override fun pause() {
        mService.get()?.pause()
    }

    override fun stop() {
        mService.get()?.stop()
    }

    override fun playNext() {
        mService.get()?.playNext()
    }

    override fun playPrev() {
        mService.get()?.playPrev()
    }

    override fun seekTo(ms: Int) {
        mService.get()?.seekTo(ms)
    }

    override fun getDuration(): Int {
        return mService.get()?.getDuration() ?: 0
    }

    override fun isPlaying(): Boolean {
        return mService.get()?.isPlaying() ?: false
    }

    override fun isPause(): Boolean {
        return !(mService.get()?.isPlaying() ?: false)
    }

    fun getService(): MusicPlayService? {
        return mService.get()
    }
}