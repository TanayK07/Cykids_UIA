
package com.example.myapplication;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // on below line we are configuring our window to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Intent svc=new Intent(this, BackgroundSoundService.class);
        startService(svc); //OR stopService(svc);
        setContentView(R.layout.activity_main);
        // on below line we are calling handler to run a task
        // for specific time interval
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // on below line we are
                // creating a new intent
                try {
                    startActivity(new Intent(MainActivity.this, SplashScreenActivity.class));

                    // on the below line we are finishing
                    // our current activity.
                    finish();
                } catch (Exception e) {
                    System.out.println("Starting error log");
                    System.out.println(e.getMessage());
                }

            }
        }, 2000);

    }
}