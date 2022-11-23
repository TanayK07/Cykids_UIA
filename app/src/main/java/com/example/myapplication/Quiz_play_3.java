package com.example.myapplication;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz_play_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_play_basic_3);
        View btn_option1=(View)findViewById(R.id.rectangsfhjkdle_1);
        View btn_option2=(View)findViewById(R.id.rectangsfhjkdle_);
        View btn_option3=(View)findViewById(R.id.rectangsfhjkdle_2);
        View btn_back = (View)findViewById(R.id.ellipse_1);
        int key=-1;
        SoundPool backsoundPool;
        SoundPool wrongsoundPool;
        SoundPool rightsoundPool;
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
            backsoundPool
                    = new SoundPool
                    .Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(
                            audioAttributes)
                    .build();
            rightsoundPool
                    = new SoundPool
                    .Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(
                            audioAttributes)
                    .build();
            wrongsoundPool
                    = new SoundPool
                    .Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(
                            audioAttributes)
                    .build();
        }
        else {
            backsoundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);
            rightsoundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);
            wrongsoundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);

        }
        int button_right = rightsoundPool.load(
                this,
                R.raw.right,
                1);
        int button_wrong = wrongsoundPool.load(this,R.raw.wrong,1);
        int back_button_music = backsoundPool.load(this,R.raw.back_button_click,1);
        //if btn_option1 is clicked call func_correctquiz1()
        btn_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_3.this,Quiz_play_3_display.class));
                wrongsoundPool.play(button_wrong,1,1,0,0,1);
            }
        });
        btn_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_3.this,Quiz_play_3_display.class));
                wrongsoundPool.play(button_wrong,1,1,0,0,1);
            }
        });
        btn_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_3.this,Quiz_play_3_display.class));
                rightsoundPool.play(button_right,1,1,0,0,1);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_3.this,Quiz_page.class));
                backsoundPool.play(back_button_music,1,1,0,0,1);
            }
        });

    }}
