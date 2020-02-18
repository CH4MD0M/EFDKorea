package com.example.myapplication;

import android.widget.SeekBar;
import android.widget.VideoView;

/**
 * Created by swjtw on 2018-06-17.
 */

class MyThread extends Thread {
    boolean isPlaying = false;
    SeekBar sb;
    VideoView videoView;

    @Override
    public void run() {
        while (isPlaying) {
            sb.setProgress(videoView.getCurrentPosition());


        }
    }
}
