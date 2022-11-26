package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Interpolator;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class memory_game_level1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memory_game);
        View back_btn=findViewById(R.id.back_button);
        Intent svc=new Intent(this, bgmservice3.class);
        startService(svc);
        SoundPool backsoundPool;
        if (Build.VERSION.SDK_INT
                >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes
                    audioAttributes
                    = new AudioAttributes
                    .Builder()
                    .setUsage(
                            AudioAttributes
                                    .USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(
                            AudioAttributes
                                    .CONTENT_TYPE_SONIFICATION)
                    .build();
            backsoundPool
                    = new SoundPool
                    .Builder()
                    .setMaxStreams(3)
                    .setAudioAttributes(
                            audioAttributes)
                    .build();
        }
        else {
            backsoundPool
                    = new SoundPool(
                    3,
                    AudioManager.STREAM_MUSIC,
                    0);
        }
        int back_button_music = backsoundPool.load(memory_game_level1.this,R.raw.back_button_click,1);
        back_btn= (View) findViewById(R.id.back_button);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(memory_game_level1.this,MainActivity.class);
                backsoundPool.play(back_button_music,1,1,0,0,1);
                startActivity(i);
                finish();
            }
        });
        /*AnimatorSet bouncer = new AnimatorSet();
bouncer.play(bounceAnim).before(squashAnim1);
bouncer.play(squashAnim1).with(squashAnim2);
bouncer.play(squashAnim1).with(stretchAnim1);
bouncer.play(squashAnim1).with(stretchAnim2);
bouncer.play(bounceBackAnim).after(stretchAnim2);
ValueAnimator fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f);
fadeAnim.setDuration(250);
AnimatorSet animatorSet = new AnimatorSet();
animatorSet.play(bouncer).before(fadeAnim);
animatorSet.start();*/

        ValueAnimator anim_tiles= misc.animation((View) findViewById(R.id.view_blocks),"alpha",0f,1f,2000);
        ValueAnimator uppertextopacity= misc.animation((View) findViewById(R.id.upper_text),"alpha",0f,1f,1000);
        View uppertext=findViewById(R.id.upper_text);
        View bottom=findViewById(R.id.bottom2);
        final boolean[] state = {false};
        uppertext.setTranslationY(200);
        bottom.post(new Runnable() {
            @Override
            public void run() {
                bottom.setTranslationY(bottom.getHeight());
            }
        });
        ValueAnimator bottompos= misc.animation(bottom,"translationY",-bottom.getHeight(),1000);

        ValueAnimator uppertextpos= misc.animation((View) findViewById(R.id.upper_text),"translationY",0,1000);
        AnimatorSet set1=new AnimatorSet();
        set1.play(uppertextopacity).with(uppertextpos).before(anim_tiles).before(bottompos);
        set1.start();
        ArrayList<Integer> list=picker();
        final ArrayList<Integer>[] choices = new ArrayList[]{new ArrayList<>()};
        for(int i=0;i<16;i++){
            String buttonID="a"+list.get(i);
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            int finalI = i;
            int finalI1 = i;
            findViewById(resID).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(""+list.get(finalI));
                    if(state[0] && choices[0].size()<4){
                        choices[0].add(list.get(finalI1));
                        findViewById(resID).setAlpha(1f);

                    }
                }
            });

            if(i<4){
                findViewById(resID).setVisibility(View.VISIBLE);
            }
            else{
                findViewById(resID).setVisibility(View.INVISIBLE);
            }
        }

        View submit=findViewById(R.id.submit);
        View retry=findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition( 0, 0);
                startActivity(getIntent());
                overridePendingTransition( 0, 0);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choices[0].size()<4){
                    Toast toast = new Toast(getApplicationContext());
                    ImageView view1 = new ImageView(getApplicationContext());
                    view1.setImageResource(R.drawable.four);

                    // setting view to toast
                    toast.setView(view1);

                    // showing toast
                    toast.show();
                }
                else{
                    Collections.sort(choices[0]);
                    ArrayList<Integer> temp=new ArrayList<>();
                    for(int i=0;i<4;i++) temp.add(list.get(i));
                    Collections.sort(temp);
                    Boolean check=true;
                    for(int i=0;i<4;i++){
                        System.out.println(temp.get(i)+" "+ choices[0].get(i));
                        if(temp.get(i)!= choices[0].get(i)){
                            check=false;
                            break;
                        }
                    }
                    if(check){
                        //PASS
                        Toast toast = new Toast(getApplicationContext());
                        ImageView view1 = new ImageView(getApplicationContext());
                        view1.setImageResource(R.drawable.correct);

                        // setting view to toast
                        toast.setView(view1);

                        // showing toast
                        toast.show();


                    }
                    else{
                        Toast toast = new Toast(getApplicationContext());
                        ImageView view1 = new ImageView(getApplicationContext());
                        view1.setImageResource(R.drawable.wrong);

                        // setting view to toast
                        toast.setView(view1);

                        // showing toast
                        toast.show();
                        misc.animation(findViewById(R.id.imageView5),"rotation",0f,-360f,1000).start();
                    }
                }
            }
        });

        TextView timer=findViewById(R.id.timer);

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                final int[] secondsLeft = {0};
                new CountDownTimer(5000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        double sec = (millisUntilFinished / (double) 1000) % 60;
                        int sec1=Math.round((float)sec);
                        timer.setText(""+sec1);
                    }

                    public void onFinish() {
                        stopService(svc);
                        timer.setText(""+0);
                        anim_tiles.setDuration(500).reverse();
                        uppertextopacity.reverse();
                        bottompos.reverse();

                        submit.setVisibility(View.VISIBLE);
                        submit.setAlpha(0f);

                        retry.setVisibility(View.VISIBLE);
                        retry.setAlpha(0f);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms
                                misc.appear(submit).start();
                                misc.appear(retry).start();
                            }
                        }, 1000);
                        misc.disappear(findViewById(R.id.imageView2)).start();
                        AnimatorSet anim2=new AnimatorSet();
                        anim2.play(misc.disappear(findViewById(R.id.timer))).before(misc.appear(findViewById(R.id.timesup)));
                        anim2.start();

                        ArrayList<Integer> clickedItems=new ArrayList<>();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                findViewById(R.id.view_blocks).setAlpha(1f);
                                for(int i=0;i<15;i++){

                                    int resID = getResources().getIdentifier("a"+list.get(i), "id", getPackageName());
                                    View v=findViewById(resID);
                                    v.setVisibility(View.VISIBLE);
                                    v.setAlpha(0f);
                                    state[0] =true;

                                }
                            }
                        }, 1000);
                    }
                }.start();
            }
        }, 1000);
        //anim_tiles.start();


    }
    public ArrayList<Integer> picker(){
        ArrayList<Integer> picked=new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=1; i<=16; i++) {
            list.add(new Integer(i));
        }
        Collections.shuffle(list);
        return list;
    }

}