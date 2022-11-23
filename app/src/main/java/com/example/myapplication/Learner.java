package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Learner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learnmore);
        View btn_learn=(View)findViewById(R.id.rectangle_8);
        btn_learn.setOnClickListener(v -> startActivity(new Intent(Learner.this,Learn_basic.class)));


    }
}


