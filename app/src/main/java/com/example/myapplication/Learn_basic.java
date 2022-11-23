package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class Learn_basic extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_basic);
        View btn_back = (View)findViewById(R.id.ellipse_1);
        youTubePlayerView = findViewById(R.id.youTubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Learn_basic.this,Learner.class));
            }
        });
    }
}


