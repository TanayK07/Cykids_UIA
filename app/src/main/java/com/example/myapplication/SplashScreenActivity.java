package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        View btn_play=(View)findViewById(R.id.rectangle_5);
        View btn_quiz=(View)findViewById(R.id.rectangle_6);
        View btn_about=(View)findViewById(R.id.rectanglaboutus);

        btn_play.setOnClickListener(v -> startActivity(new Intent(SplashScreenActivity.this,Game_page.class)));
        btn_quiz.setOnClickListener(v -> startActivity(new Intent(SplashScreenActivity.this,Quiz_page.class)));
        btn_about.setOnClickListener(v -> startActivity(new Intent(SplashScreenActivity.this,About_us.class)));
    }


}