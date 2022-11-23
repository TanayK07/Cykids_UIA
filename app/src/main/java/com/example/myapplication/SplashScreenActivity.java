package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SplashScreenActivity extends AppCompatActivity {
    public static SharedPreferences prefs;
    public long diff;
    public static boolean isTimeUp = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        SoundPool soundPool;
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

        }
        else {
            soundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);
        }
        int button_music = soundPool.load(
                this,
                R.raw.button_click,
                1);

       /* prefs = getSharedPreferences("game", MODE_PRIVATE);
        long timeStarted = prefs.getLong("timeStarted", 0);

        if(timeStarted == 0){
            timeStarted = new Date().getTime();
            prefs.edit().putLong("timeStarted", timeStarted).apply();
        }
        else{
            diff = new Date().getTime() - new Date(timeStarted).getTime();
            int t = (int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);

            if(t >= 1){
                if(isTimeUp){
                    overPlayedDialog();
                }
                else{
                    if(t>2){
                        timeStarted = new Date().getTime();
                        prefs.edit().putLong("timeStarted", timeStarted).apply();
                    }
                    else{
                        isTimeUp=true;
                        overPlayedDialog();
                    }

                }

            }
        }
        if(isTimeUp == false){
            timer(timeStarted);
        }
        isTimeUp = false;*/

        View btn_play=(View)findViewById(R.id.rectangle_5);
        View btn_quiz=(View)findViewById(R.id.rectangle_6);
        View btn_about=(View)findViewById(R.id.rectanglaboutus);
        View btn_learn=(View)findViewById(R.id.rectangle_7);

        btn_play.setOnClickListener(v ->
        {startActivity(new Intent(SplashScreenActivity.this,Game_page.class));
            soundPool.play(button_music, 1, 1, 0, 0, 1);
            });
        btn_quiz.setOnClickListener(v -> {startActivity(new Intent(SplashScreenActivity.this,Quiz_page.class));
            soundPool.play(button_music, 1, 1, 0, 0, 1);
            });
        btn_about.setOnClickListener(v -> {startActivity(new Intent(SplashScreenActivity.this,About_us.class));
            soundPool.play(button_music, 1, 1, 0, 0, 1);
            });
        btn_learn.setOnClickListener(v -> {startActivity(new Intent(SplashScreenActivity.this, Learner.class));
            soundPool.play(button_music, 1, 1, 0, 0, 1);
            });

    }

    public void overPlayedDialog () {

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.timeup)
                .setTitle("Time up")
                .setMessage("Your time is up, you can try again in the next 2 hours")
                .setPositiveButton(android.R.string.yes,
                        (dialog, id) -> {
                            finish();
                            System.exit(0);
                        })
                .create().show();
    }

    public void timer(long timeStarted){
        new Thread(() -> {
            while(true){
                System.out.println("Started monitoring");
                diff = new Date().getTime() - new Date(timeStarted).getTime();
                int t = (int) TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);

                if(t >= 1){
                    isTimeUp = true;
                    Intent intent = new Intent(this, SplashScreenActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    // on the below line we are finishing
                    // our current activity.
                    finish();
                    break;
                }

                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}