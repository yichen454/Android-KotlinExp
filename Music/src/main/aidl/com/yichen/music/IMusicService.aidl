// IMusicService.aidl
package com.yichen.music;

// Declare any non-default types here with import statements

interface IMusicService {

    void playMusic(String path);
    void playMusicById(String id);
    void pause();
    void stop();
    void playNext();
    void playPrev();
    void seekTo(int ms);
    int getDuration();
    boolean isPlaying();
    boolean isPause();
}
