package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz_play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_play);
        View btn_option1=(View)findViewById(R.id.rectangsfhjkdle_1);
    View btn_option2=(View)findViewById(R.id.rectangsfhjkdle_);
        View btn_option3=(View)findViewById(R.id.rectangsfhjkdle_2);

        int key=-1;
        String q1="Clicking on links sent by people you do not know is:";
        String a1="Dangerous";
        String a2="Safe";
        String a3="Fun";
        TextView question=(TextView)findViewById(R.id.what_asjnak);
        TextView option1=(TextView)findViewById(R.id.option1);
        TextView option2=(TextView)findViewById(R.id.optiion2);
        TextView option3=(TextView)findViewById(R.id.optidon3);



        question.setText(q1);
        option1.setText(a1);
option2.setText(a2);
option3.setText(a3);

btn_option1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        func_correctquiz1();
        String q1="Clicking on links sent by people you do not know is:";
        String a1="Dangerous";
        String a2="Safe";
        String a3="Fun";
        TextView question=(TextView)findViewById(R.id.what_asjnak);
        TextView option1=(TextView)findViewById(R.id.option1);
        TextView option2=(TextView)findViewById(R.id.optiion2);
        TextView option3=(TextView)findViewById(R.id.optidon3);



        question.setText(q1);
        option1.setText(a1);
        option2.setText(a2);
        option3.setText(a3);



    }
});



//logic for quiz if correct make btn_option1 green else make it red

    }

    public void func_correctquiz1() {


        View btn_option1 = (View) findViewById(R.id.rectangsfhjkdle_1);
        btn_option1.setBackground(getResources().getDrawable(R.drawable.correct_optn));
        View btn_option2 = (View) findViewById(R.id.rectangsfhjkdle_);
        btn_option1.setBackground(getResources().getDrawable(R.drawable.wrong_option));
        View btn_option3 = (View) findViewById(R.id.rectangsfhjkdle_2);
        btn_option1.setBackground(getResources().getDrawable(R.drawable.wrong_option));

    }
    public void correctquiz2(){


            View btn_option1=(View)findViewById(R.id.rectangsfhjkdle_1);
            btn_option1.setBackground(getResources().getDrawable(R.drawable.wrong_option));
            View btn_option2=(View)findViewById(R.id.rectangsfhjkdle_);
            btn_option1.setBackground(getResources().getDrawable(R.drawable.correct_optn));
            View btn_option3=(View)findViewById(R.id.rectangsfhjkdle_2);
            btn_option1.setBackground(getResources().getDrawable(R.drawable.wrong_option));


        }
    public void correctquiz3(){

            View btn_option1=(View)findViewById(R.id.rectangsfhjkdle_1);
            btn_option1.setBackground(getResources().getDrawable(R.drawable.wrong_option));
            View btn_option2=(View)findViewById(R.id.rectangsfhjkdle_);
            btn_option1.setBackground(getResources().getDrawable(R.drawable.wrong_option));
            View btn_option3=(View)findViewById(R.id.rectangsfhjkdle_2);
            btn_option1.setBackground(getResources().getDrawable(R.drawable.correct_optn));


        }


    }




