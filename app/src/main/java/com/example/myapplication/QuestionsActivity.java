package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String basicQuestions[] = {
            "1.As a kid, What is your responsibility?",
            "2.What would you do if you come across something harmful online?",
            "3.From the items below, select your personal information.",
            "4.John has got a best friend, should he share his social media passwords with him?",
            "5.Which of the following is one of online threats?",
            "6.You are on your mobile phone or laptop online in one of the social media platforms, a stranger send you a message claiming to be a student in a nearby school, what would you do if you receive such message?",
            "7.What would you do if you are being bulled online?",
            "8.A ____________ can be a hardware device or a software program that filters all the packets of data that comes through a network, the internet, etc.?",
            "9.Is it good practice to always connect to public Wi-Fi(s)?",
            "10.Who is responsible for conserving our environment?"};

    String intermediateQuestions[] = {
            "1.What does the three R's of recycling mean?",
            "2.Which of the following items cannot be recycled?",
            "3.Which bin does cardboard go in?",
            "4.Which bin would you put grass clippings or leaves?",
            "5.What is e-waste?",
            "6.Why should we reuse products?",
            "7.What's the largest contributor of waste in Australia?",
            "8.If your bicycle tyre gets punctured, what should you do first?",
            "9.Where does the majority of plastic waste end up?",
            "10.Which of the following items do Australians recycle most?"};

    String advancedQuestions[] = {
            "1.How many years does it take for glass to break down naturally?",
            "2.The biggest problem with recycling is that",
            "3.Which gas is produced at landfills?",
            "4._____ of the average household bin is food waste?",
            "5.If you recycle a tonne of paper, how many trees are you saving?",
            "6.How many years does it take a single aluminium can to decompose?",
            "7.Why should we practice 3R?",
            "8.Which of the following is bad for the environment?",
            "9._____ contributes to Global warming?",
            "10.How would you know if someone wants to hack you?"
    };

    String basicAnswers[] = {
            "Both A&B",
            "All the above",
            "Your address",
            "No, it's not good practice",
            "Both A&B",
            "Do not accept their invitation to chat (it could be someone trying to trick you). ",
            "Stop conversation, block, tell an adult",
            "Firewall",
            "No, public Wi-Fi(s) should not be practically used since hackers sometimes use them to penetrate through.",
            "If that person is a stranger and ask way too confidential information"};


    String basicOpt[] = {
            "Yourself safe","Your stuff safe","Your cookies safe","Both A&B",
            "Tell your parents","Tell your teachers","Tell a trusted adult","All the above",
            "Your shoe","Your address","The pizza restaurant phone number","Your gaming cards",
            "Yes","No, it's not good practice","Maybe","He can if he likes",
            "Identity theft","Phishing","Surfing","Both A&B",
            "Do not accept their invitation to chat (it could be someone trying to trick you). ","Accept the invite and make a new friend!","Do nothing", "Reject",
            "Ask them to please stop","Say you will tell on them","Stop conversation, block, tell an adult","Stop using internet",
            "Firewall","Antivirus","Cookies, tell an adult","Insult them",
            "Yes","Maybe","Just connect and enjoy!","No, public Wi-Fi(s) should not be practically used since hackers sometimes use them to penetrate through.",
            "When reacting on posts","When the person is following you","If that person is a stranger and ask way too confidential information","You never know"
    };


    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        getSupportActionBar().setTitle("Quiz");
        Intent intent = getIntent();
        String value = intent.getStringExtra("VALUE");
        submitbutton = (Button) findViewById(R.id.button3);
        quitbutton = (Button) findViewById(R.id.buttonquit);
        tv = (TextView) findViewById(R.id.tvque);
        radio_g = (RadioGroup) findViewById(R.id.answersgrp);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);
        rb3 = (RadioButton) findViewById(R.id.radioButton3);
        rb4 = (RadioButton) findViewById(R.id.radioButton4);

        if (value.matches("basic")) {
            tv.setText(basicQuestions[flag]);
            tv.startAnimation(AnimationUtils.loadAnimation(this,R.anim.righttoleft));
            rb1.setText(basicOpt[0]);
            rb1.startAnimation(AnimationUtils.loadAnimation(this,R.anim.righttoleft));
            rb2.setText(basicOpt[1]);
            rb2.startAnimation(AnimationUtils.loadAnimation(this,R.anim.righttoleft));
            rb3.setText(basicOpt[2]);
            rb3.startAnimation(AnimationUtils.loadAnimation(this,R.anim.righttoleft));
            rb4.setText(basicOpt[3]);
            rb4.startAnimation(AnimationUtils.loadAnimation(this,R.anim.righttoleft));
            submitbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (radio_g.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                    String ansText = uans.getText().toString();
                    if (ansText.equals(basicAnswers[flag])) {
                        correct++;
                        // Toasty.success(getApplicationContext(), "Yes, that's Correct", Toast.LENGTH_SHORT).show();
                        final Toast toast = Toast.makeText(getApplicationContext(), "Yes, that's Correct", Toast.LENGTH_SHORT);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 500);
                    } else {
                        wrong++;
                        // Toasty.error(getApplicationContext(), "Oops. Wrong answer", Toast.LENGTH_SHORT).show();
                        final Toast toast = Toast.makeText(getApplicationContext(), "Oops. Wrong answer", Toast.LENGTH_SHORT);
                        toast.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toast.cancel();
                            }
                        }, 500);
                    }

                    flag++;

                    if (flag < basicQuestions.length) {
                        tv.setText(basicQuestions[flag]);
                        tv.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce));
                        rb1.setText(basicOpt[flag * 4]);
                        rb1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce));
                        rb2.setText(basicOpt[flag * 4 + 1]);
                        rb2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce));
                        rb3.setText(basicOpt[flag * 4 + 2]);
                        rb3.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce));
                        rb4.setText(basicOpt[flag * 4 + 3]);
                        rb4.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce));
                    } else {
                        marks = correct;
                        Intent in = new Intent(getApplicationContext(), ResultActivity.class);
                        startActivity(in);
                    }
                    radio_g.clearCheck();
                }
            });
        }

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(),"See you soon",
                                Toast.LENGTH_SHORT).show();
                        QuestionsActivity.this.finish();
                        Intent intent = new Intent(QuestionsActivity.this, SelectQuizActivity.class);
                        startActivity(intent);
                    }
                }).create().show();
    }
}
