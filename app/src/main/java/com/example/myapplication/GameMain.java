package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameMain extends AppCompatActivity {

    private boolean isMute;
    public static boolean isGameOver = false;
    private AlertDialog.Builder gameOverDialog;
    private AlertDialog dialog;
    private ImageView imageView;
    private  TextView scoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game_main);

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameMain.this, GameActivity.class));
                finish();
            }
        });
        if(isGameOver){
            //isGameOver = false;
            createGameOverDialog();
        }
        TextView highScoreTxt = findViewById(R.id.highScoreTxt);

        final SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
        highScoreTxt.setText("HighScore: " + prefs.getInt("highscore", 0));

        isMute = prefs.getBoolean("isMute", false);

        final ImageView volumeCtrl = findViewById(R.id.volumeCtrl);

        if (isMute)
            volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
        else
            volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

        volumeCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMute = !isMute;
                if (isMute)
                    volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
                else
                    volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();

            }
        });

    }

    public void createGameOverDialog(){
        gameOverDialog = new AlertDialog.Builder(this);
        final View gameOverPopupView = getLayoutInflater().inflate(R.layout.activity_gameover,null);
        gameOverDialog.setView(gameOverPopupView);
        dialog = gameOverDialog.create();
        dialog.show();

        imageView = (ImageView) gameOverPopupView.findViewById(R.id.replay);
        scoreView = (TextView) gameOverPopupView.findViewById(R.id.score);
        scoreView.setText("Score: "+ GameView.score);
        GameView.score = 0;

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                dialog.dismiss();
            }
        });

    }
}

