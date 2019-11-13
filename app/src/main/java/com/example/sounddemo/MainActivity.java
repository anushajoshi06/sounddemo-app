package com.example.sounddemo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button clk1;
    Button clk2;
    MediaPlayer mplayer;
    AudioManager audioamanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         clk1 = (Button) findViewById(R.id.button);
         clk2 = (Button) findViewById(R.id.button2);
        mplayer = MediaPlayer.create(this, R.raw.audio);
        audioamanager = (AudioManager)getSystemService(AUDIO_SERVICE);
        int maxvolume = audioamanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curvolume = audioamanager.getStreamVolume(AudioManager.STREAM_MUSIC);

        SeekBar volumecontrol = (SeekBar) findViewById(R.id.seekBar);
        volumecontrol.setMax(maxvolume);
        volumecontrol.setProgress(curvolume);
        volumecontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("Seekbar value" , Integer.toString(progress));
                audioamanager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar durationcontrol = (SeekBar) findViewById(R.id.seekBar2);
        durationcontrol.setMax(mplayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
durationcontrol.setProgress(mplayer.getCurrentPosition());
            }
        },0,1000);
        durationcontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mplayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

        public void audioplay(View view) {

            mplayer.start();
        }
    public void audiopause(View view) {

        mplayer.pause();
    }
}
