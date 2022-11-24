package com.example.myapplication;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Level_1_waiting extends AppCompatActivity {
    SoundPool soundPool;
    SoundPool backsoundPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1_waitingscreen);
        View btn_back = (View)findViewById(R.id.playbtn);
        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes
                    audioAttributes
                    = new AudioAttributes
                    .Builder()
                    .setUsage(
                            AudioAttributes
                                    .USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(
                            AudioAttributes
                                    .CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool
                    = new SoundPool
                    .Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(
                            audioAttributes)
                    .build();
            backsoundPool
                    = new SoundPool
                    .Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(
                            audioAttributes)
                    .build();
        }
        else {
            soundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);
            backsoundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);
        }
        int button_music = soundPool.load(
                this,
                R.raw.button_click,
                1);
        int back_button_music = backsoundPool.load(this,R.raw.back_button_click,1);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(button_music,1,1,0,0,1);
                Intent intent = new Intent(Level_1_waiting.this, levelTwoGame.class);
                startActivity(intent);
            }
        });
        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc);

     /*   findViewById(R.id.relativeLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Level_1_waiting.this, levelTwoGame.class));
                soundPool.play(button_music, 1, 1, 0, 0, 1);
                finish();
            }
        });*/
        findViewById(R.id.ellipse_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(svc);
                startActivity(new Intent(Level_1_waiting.this, Game_page.class));
                backsoundPool.play(back_button_music,1,1,0,0,1);
            }
        });

    }
}


