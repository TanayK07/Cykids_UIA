package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class Learn_basic extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_basic);
        youTubePlayerView = findViewById(R.id.youTubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);
    }
}


