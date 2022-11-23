package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz_play_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_play);
        View btn_option1=(View)findViewById(R.id.rectangsfhjkdle_1);
        View btn_option2=(View)findViewById(R.id.rectangsfhjkdle_);
        View btn_option3=(View)findViewById(R.id.rectangsfhjkdle_2);
        View btn_back = (View)findViewById(R.id.ellipse_1);
        int key=-1;
        //if btn_option1 is clicked call func_correctquiz1()
        btn_option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_2.this,Quiz_play_2_display.class));
            }
        });
        btn_option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_2.this,Quiz_play_2_display.class));
            }
        });
        btn_option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_2.this,Quiz_play_2_display.class));
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz_play_2.this,Quiz_page.class));
            }
        });

    }}
