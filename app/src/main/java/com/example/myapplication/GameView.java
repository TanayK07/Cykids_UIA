package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {

    private Thread thread;
    private boolean isPlaying, isGameOver = false;
    private int screenX, screenY;
    public static int score=0;
    public static float screenRatioX, screenRatioY;
    private Paint paint;
    private Paint rectPaint;
    private Malware[] malwares;
    private SharedPreferences prefs;
    private Random random;
    private SoundPool soundPool;
    private List<Bullet> bullets;
    private int sound;
    private Flight flight;
    private GameActivity activity;
    private Background background1, background2;
    public static float speedRation;
    MediaPlayer mediaPlayer;
    private File files;

    public GameView(GameActivity activity, int screenX, int screenY) {
        super(activity);

        this.activity = activity;
        speedRation = GameActivity.point.x / 40;
        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .build();

        } else
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        sound = soundPool.load(activity, R.raw.shoot, 1);

        this.screenX = screenX;
        this.screenY = screenY;
        screenRatioX = 1920f / screenX;
        screenRatioY = 1080f / screenY;

        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());

        flight = new Flight(this, screenY, getResources());

        bullets = new ArrayList<>();

        background2.x = screenX;

        paint = new Paint();
        paint.setTextSize(128);
        paint.setColor(Color.WHITE);

        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setColor(Color.parseColor("#ffffff"));

        malwares = new Malware[3];

        Worm worm = new Worm(getResources());
        Trojan trojan = new Trojan(getResources());
        Virus virus = new Virus(getResources());
        malwares[0] = worm;
        malwares[1] = trojan;
        malwares[2] = virus;
        random = new Random();

        files = new File(getResources());
        System.out.println(files.file1.getHeight());
        mediaPlayer = MediaPlayer.create(activity, R.raw.game1music);
        if(!prefs.getBoolean("soundMuted", false)){
            mediaPlayer.start();
        }
    }

    @Override
    public void run() {
        while (isPlaying) {
            if(!mediaPlayer.isPlaying() && (!prefs.getBoolean("soundMuted", false))){
                mediaPlayer.start();
            }
            update ();
            draw ();
            sleep ();
        }
    }

    private void update () {
        /*background1.x -= 2.5 * screenRatioX;
        background2.x -= 2.5 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 0) {
            background1.x = screenX;
        }

        if (background2.x + background2.background.getWidth() < 0) {
            background2.x = screenX;
        }*/

        if (flight.isGoingUp)
            flight.y -= 30 * screenRatioY;
        else
            flight.y += GameActivity.point.y/45;

        if (flight.y < 0)
            flight.y = 0;

        if (flight.y >= screenY - flight.height)
            flight.y = screenY - flight.height;

        List<Bullet> trash = new ArrayList<>();

        for (Bullet bullet : bullets) {

            if (bullet.x > screenX)
                trash.add(bullet);

            bullet.x += 50 * screenRatioX;

            for (Malware malware : malwares) {

                if (Rect.intersects(malware.getCollisionShape(),
                        bullet.getCollisionShape())) {

                    score++;
                    malware.x = -500;
                    bullet.x = screenX + 500;
                    malware.wasShot = true;
                }
            }
        }

        for (Bullet bullet : trash)
            bullets.remove(bullet);

        for (Malware malware : malwares) {

            malware.x -= malware.speed;

            if (malware.x + malware.width < 0) {

                if (!malware.wasShot) {
                    isGameOver = true;
                    return;
                }
                int bound = (int) (speedRation * 0.5);
                malware.speed = random.nextInt(bound);

                if (malware.speed < speedRation/4)
                    malware.speed = (int) speedRation/4;
                malware.x = screenX;
                malware.y = random.nextInt(screenY - (int)(malware.height * 1.25));
                malware.wasShot = false;
            }

        }

    }

    private void draw () {

        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            canvas.drawText(score + "", screenX / 2f, 164, paint);

            for (Malware malware : malwares){
                if(malware.x >= 0){
                    if(malware.x <= GameActivity.point.x/6){
                        rectPaint.setColor(Color.parseColor("#f05a4d"));
                        break;
                    }
                    else{
                        rectPaint.setColor(Color.parseColor("#ffffff"));
                    }
                }

            }
            canvas.drawRect(new Rect(0, 0, GameActivity.point.x/6, GameActivity.point.y), rectPaint);
            for (Malware malware : malwares)
                canvas.drawBitmap(malware.getMalware(), malware.x, malware.y, paint);

            System.out.println(activity.point.y*3/8 - (File.iconSize/2));
            canvas.drawBitmap(files.file1, GameActivity.point.x/6/2-(File.iconSize/2), GameActivity.point.y/8-(File.iconSize/2), paint);
            canvas.drawBitmap(files.file2, GameActivity.point.x/6/2-(File.iconSize/2), GameActivity.point.y*3/8-(File.iconSize/2), paint);
            canvas.drawBitmap(files.file3, GameActivity.point.x/6/2-(File.iconSize/2), GameActivity.point.y*5/8-(File.iconSize/2), paint);

            if (isGameOver) {
                isPlaying = false;
                canvas.drawBitmap(flight.getDead(), flight.x, flight.y, paint);
                getHolder().unlockCanvasAndPost(canvas);
                saveIfHighScore();
                waitBeforeExiting ();
                return;
            }

            canvas.drawBitmap(flight.getFlight(), flight.x, flight.y, paint);

            for (Bullet bullet : bullets)
                canvas.drawBitmap(bullet.bullet, bullet.x, bullet.y, paint);

            getHolder().unlockCanvasAndPost(canvas);

        }

    }

    private void waitBeforeExiting() {

        try {
            Thread.sleep(3000);
            GameMain.isGameOver = true;
            mediaPlayer.stop();
            activity.startActivity(new Intent(activity, GameMain.class));
            activity.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void saveIfHighScore() {

        if (prefs.getInt("highscore", 0) < score) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("highscore", score);
            editor.apply();
        }

    }

    private void sleep () {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume () {

        isPlaying = true;
        thread = new Thread(this);
        thread.start();

    }

    public void pause () {

        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX() < screenX / 2) {
                    flight.isGoingUp = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                flight.isGoingUp = false;
                if (event.getX() > screenX / 2)
                    flight.toShoot++;
                break;
        }

        return true;
    }

    public void newBullet() {

        if (!prefs.getBoolean("isMute", false))
            soundPool.play(sound, 1, 1, 0, 0, 1);

        Bullet bullet = new Bullet(getResources());
        bullet.x = flight.x + flight.width;
        bullet.y = flight.y + (int) (flight.height * 0.15);
        bullets.add(bullet);

    }
}
