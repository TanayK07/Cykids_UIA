package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz_play_3_display extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_play_basic_3_next);
        View btn_option1=(View)findViewById(R.id.rectangsfhjkdle_1);
        View btn_option2=(View)findViewById(R.id.rectangsfhjkdle_);
        View btn_option3=(View)findViewById(R.id.rectangsfhjkdle_2);

        int key=-1;
        //if btn_option1 is clicked call func_correctquiz1()
        btn_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_3_display.this,SplashScreenActivity.class));
            }
        });
        btn_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_3_display.this,SplashScreenActivity.class));
            }
        });
        btn_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_3_display.this,SplashScreenActivity.class));
            }
        });


    }}