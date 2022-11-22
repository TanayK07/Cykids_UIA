package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Quiz_play extends AppCompatActivity {
    TextView tvTimer;
    // A TextView for showing Result
    TextView tvResult;
    // An ImageView for showing an image in question
    ImageView ivShowImage;
    // Instantiate a HashMap to store technology names and corresponding image resource ids
    HashMap<String, Integer> map = new HashMap<>();
    // An ArrayList for storing technology names only
    ArrayList<String> techList = new ArrayList<>();
    // Declare an index variable. We'll keep incrementing it as the quiz proceeds to
    // the next questions.
    int index;
    // Declare four button object references for displaying four options to choose from
    Button btn_option1, btn_option2, btn_option3, btn4;
    // A TextView for displaying points
    TextView tvPoints;
    // An integer variable to store points
    int points;
    // A CountDownTimer object reference
    CountDownTimer countDownTimer;
    // And a long integer to store the time limit for each question to be used
    // with the CountDownTimer.
    long millisUntilFinished;

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
        index = 0;
        // Populate techList with all the technology names
        techList.add("Bootstrap");
        techList.add("C");
        techList.add("Codeigniter");
        techList.add("Cplusplus");
        techList.add("Csharp");
        techList.add("Css3");
        techList.add("Github");
        techList.add("Html5");
        techList.add("Java");
        techList.add("jQuery");
        techList.add("MySQL");
        techList.add("Nodejs");
        techList.add("Php");
        techList.add("Python");
        techList.add("Wordpress");
        techList.add("Android");


//logic for quiz if correct make btn_option1 green else make it red
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
            // In our case, onTick() callback method is fired on regular intervals of
            // 1000 milliseconds or 1 second and onFinish() callback method is fired
            // when the timer finishes.
            @Override
            public void onTick(long millisUntilFinished) {
                // Update tvTimer every 1 second to show the number of seconds remaining.
                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                // Increment index by 1 so that the next question can be presented
                // automatically when the user is unable to select his/her answer.
                index++;
                // When timer is finished check if all questions are being asked.
                if (index > techList.size() - 1){
                    // If true, hide the ImageView and Buttons.
                    ivShowImage.setVisibility(View.GONE);
                    btn_option1.setVisibility(View.GONE);
                    btn_option2.setVisibility(View.GONE);
                    btn_option3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);
                    // Go to GameOver screen with points using an Intent
                    Intent intent = new Intent(Quiz_play.this, SplashScreenActivity.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    // Finish StartGame Activity
                    finish();
                } else {
                    // In the else part, that is, if all questions are not being asked,
                    // initialize countDownTimer with null and call startGame() again.
                    // And this is the case, when no answer is selected before the
                    // time limit is over. So, the player will be presented with the
                    // next question and no points will be granted. If you want
                    // you can also decrement the points here for skipping a question
                    // and that'll make the quiz a bit harder.
                    countDownTimer = null;
                    new Quiz_play();
                }
            }
        }.start(); // Call start() method to start the timer.
    }

    private void generateQuestions(int index) {
        // Clone techList to a new ArrayList called techListTemp.
        ArrayList<String> techListTemp = (ArrayList<String>) techList.clone();
        // Get the correct answer for the current question from techList using index.
        String correctAnswer = techList.get(index);
        // You need to find three non-repeated incorrect answers randomly.
        // So, delete the correct answer from techListTemp.
        // Shuffle it and get first three elements from it.
        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);
        // Create a temporary ArrayList for storing three non-repeated random answers
        // from techListTemp and one correct answer.
        ArrayList<String> newList = new ArrayList<>();
        // Get first three elements from techListTemp and add into newList.
        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));
        // Also add the correct answer into newList
        newList.add(correctAnswer);
        // Shuffle newList so that the correct answer can be placed in one of the four
        // buttons, randomly.
        Collections.shuffle(newList);
        // Once you shuffle newList, set all four Button's text with the elements
        // from newList.
        btn_option1.setText(newList.get(0));
        btn_option2.setText(newList.get(1));
        btn_option3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        // Also, set the ImageView with current image from map
        ivShowImage.setImageResource(map.get(techList.get(index)));
    }
    public void nextQuestion(View view) {
        // This method is called because the user has tapped the Next button,
        // so, set the background color of all the buttons to the color that we used in start_game.xml.
        btn_option1.setBackgroundColor(Color.parseColor("#2196f3"));
        btn_option2.setBackgroundColor(Color.parseColor("#2196f3"));
        btn_option3.setBackgroundColor(Color.parseColor("#2196f3"));
        btn4.setBackgroundColor(Color.parseColor("#2196f3"));
        // Enable the buttons
        btn_option1.setEnabled(true);
        btn_option2.setEnabled(true);
        btn_option3.setEnabled(true);
        btn4.setEnabled(true);
        // Cancel the countDownTimer
        countDownTimer.cancel();
        index++;
        // Check if all questions have been asked.
        if (index > techList.size() - 1){
            // If true, hide the ImageView and Buttons.
            ivShowImage.setVisibility(View.GONE);
            btn_option1.setVisibility(View.GONE);
            btn_option2.setVisibility(View.GONE);
            btn_option3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            // Go to GameOver screen with points
            Intent intent = new Intent(Quiz_play.this, SplashScreenActivity.class);
            intent.putExtra("points", points);
            startActivity(intent);
            // Finish StartGame Activity
            finish();
        } else {
            // Till there is at least one question left, initialize countDownTimer with null and
            // call startGame()
            countDownTimer = null;
            new Quiz_play();
        }
    }

    public void answerSelected(View view) {
        // Change the clicked Button's background color
        view.setBackgroundColor(Color.parseColor("#17243e"));
        // Disable all four Buttons
        btn_option1.setEnabled(false);
        btn_option2.setEnabled(false);
        btn_option3.setEnabled(false);
        btn4.setEnabled(false);
        // The user has selected an answer, so, cancel the countDownTimer
        countDownTimer.cancel();
        // Get clicked button's text for user answer
        String answer = ((Button) view).getText().toString().trim();
        // And, get the correct answer for the current question from techList using index
        // as position.
        String correctAnswer = techList.get(index);
        // Compare answer and correctAnswer, that is, the answer selected by the user
        // and the correct answer for this question.
        if(answer.equals(correctAnswer)){
            // If true, the user has selected the right answer. So, increment points.
            points++;
            // Here we are incrementing points by 1 here, but, you can increment by any number
            // you want.
            // Update the TextViews for points and result
            tvPoints.setText(points + " / " + techList.size());
            tvResult.setText("Correct");
        } else {
            // In else, that is, when the user answer is incorrect, show "Wrong" in tvResult.
            tvResult.setText("Wrong");
        }
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




