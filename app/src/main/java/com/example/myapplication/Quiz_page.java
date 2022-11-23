package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_page);
        View btn_quizplay=(View)findViewById(R.id.rectangle_9 );
        View btn_back = (View)findViewById(R.id.ellipse_1);


           btn_quizplay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Quiz_page.this,Quiz_play.class));
                }
            });
            btn_back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Quiz_page.this,SplashScreenActivity.class));
                }
            });
    }

}


