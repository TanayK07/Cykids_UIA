//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Random;
import com.example.endlessrunnergame.MainActivity;

public class GameView extends SurfaceView implements Runnable{

    private Game game;
    private Thread thread;
    private boolean gameActive = false;
    private boolean explosionIsCreated = false;
    public static int resolutionX;
    public static int resolutionY;
    private Paint paint;
    static Paint scorePaint;
    private Canvas canvas;
    private Background background, mirroredBackground;
    private Bird bird;
    private ArrayList<anonymous> rockets;
    private ArrayList<Ring> rings;
    private ArrayList<Gem> gems;
    private long rocketStartTime = 0;
    private long ringStartTime = 0;
    private long gemStartTime = 0;
    private Random random;
    private SoundPool inGameSoundPool;
    private int explosionSound;
    private int ringSound;
    private int rocketSound;
    private int gameOverSound;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;
    MediaPlayer mediaPlayer;
    private Explosion explosion;
    private SharedPreferences preferences;

    /**The class GameView is used to run the game-session and an instance of the class is created from the class Game.
     * The class GameView extends the class SurfaceView and implements the Runnable interface.
     * */

    public GameView(Game game, int resolutionX, int resolutionY) {
        super(game);
        this.game = game;
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
        background = new Background(resolutionX, resolutionY, getResources());
        mirroredBackground = new Background(resolutionX, resolutionY, getResources());
        Matrix matrix = new Matrix();
        Bitmap mirroredBackgroundsOriginalBitmap = mirroredBackground.background;
        matrix.setScale(-1, 1);
        mirroredBackground.background = Bitmap.createBitmap(mirroredBackgroundsOriginalBitmap, 0, 0, resolutionX, resolutionY, matrix, true);
        mirroredBackground.x = resolutionX;
        bird = new Bird(getResources());
        rockets = new ArrayList<anonymous>();
        rocketStartTime = System.currentTimeMillis();
        rings = new ArrayList<Ring>();
        ringStartTime = System.currentTimeMillis();
        gems = new ArrayList<Gem>();
        gemStartTime = System.currentTimeMillis();
        paint = new Paint();
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(100);
        scorePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        random = new Random();
        audioManager = (AudioManager) game.getSystemService(Context.AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;
        game.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        inGameSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        ringSound = inGameSoundPool.load(game, R.raw.coin_sound, 1);
        explosionSound = inGameSoundPool.load(game, R.raw.explosion_sound, 1);
        rocketSound = inGameSoundPool.load(game, R.raw.rocket_sound, 1);
        gameOverSound = inGameSoundPool.load(game, R.raw.gameoversoundtwo, 1);
        preferences = game.getSharedPreferences("game", Context.MODE_PRIVATE);
        mediaPlayer = MediaPlayer.create(game, R.raw.theme_song);
        if(!preferences.getBoolean("soundMuted", false)){
            mediaPlayer.start();
        }
    } /**The constructor of the class GameView is called when an instance of the class is created.
     * The constructor sets up necessary preparations before the game is started.
     * This includes many things, e.g:
     * - Creation of a background, of a helicopter, of ArrayLists containing rings, rockets and gems.
     * - Creation of Paint-object to draw things on the screen.
     * - Settings for sounds in the game, including creation of a AudioManager and a SoundPool with multiple sounds in it.
     * */

    @Override
    public void run() {
        while(gameActive){
            if(!mediaPlayer.isPlaying() && (!preferences.getBoolean("soundMuted", false))){
                mediaPlayer.start();
            }
            update();
            draw();
            sleep();
        }
    }/**The method run is called when an instance of the class GameView is created.
     * The method run is called continuously as the Thread thread is alive.
     * While the boolean gameActive is true, the three methods update, draw and sleep are called in order to "run" the game.
     * */

    private void update(){
        //Update of Background:
        background.x -= 10 ;
        mirroredBackground.x -= 10 ;
        if(background.x + resolutionX <= 0){
            background.x = resolutionX;
        }
        if(mirroredBackground.x + resolutionX <= 0){
            mirroredBackground.x = resolutionX;
        }

        //Update of Helicopter
        bird.updateFrameCounter();
        bird.updateMoving();

        //If bird has been hit, certain things need to happen
        if(bird.isHit()){
            explosionAndDeath();
        }
        if(bird.isDead()){
//            checkAndSavePotentialHighscore();
            playGameOverSound();
            exitGameSession();
        }

        //Add new rockets on timer, every other second.
        long rocketTimer = (System.currentTimeMillis() - rocketStartTime) /1000;
        if(!bird.isHit()){

            if(bird.getScore() >= 100){
                if(rocketTimer > 1){
                    rockets.add(new anonymous(getResources(), resolutionX, random.nextInt(resolutionY - 30), bird.getScore()));
                    playRocketSound();
                    rocketStartTime = System.currentTimeMillis(); //reset timer each time a rocket is added
                }
            }
            if(bird.getScore() < 100){
                if(rocketTimer > 2){
                    rockets.add(new anonymous(getResources(), resolutionX, random.nextInt(resolutionY - 30), bird.getScore()));
                    playRocketSound();
                    rocketStartTime = System.currentTimeMillis(); //reset timer each time a rocket is added
                }
            }
        }

        //Add new rings on timer, every third second.
        long ringTimer = (System.currentTimeMillis() - ringStartTime) / 1000;
        if(!bird.isDead()){
            if(ringTimer > 3){
                rings.add(new Ring(getResources(), resolutionX, random.nextInt(resolutionY - 30), bird.getScore()));
                ringStartTime = System.currentTimeMillis();
            }
        }

        //Add new gems on timer, every tenth second
        long gemTimer = (System.currentTimeMillis() - gemStartTime) / 1000;
        if(!bird.isDead()){
            if(gemTimer > 10){
                gems.add(new Gem(getResources(), resolutionX, random.nextInt(resolutionY - 30), bird.getScore()));
                gemStartTime = System.currentTimeMillis();
            }
        }

        //Loop through all rockets
        for(int i = 0; i < rockets.size(); i++){
            rockets.get(i).updateFrameCounter(); //Update their animations
            rockets.get(i).updateMoving(); //Update their positions

            //If a rocket hits the bird, the game is over.
            if(spritesIntersects(rockets.get(i), bird)){
                rockets.remove(i);
                bird.setHit(true);
                bird.setCanMove(false);
                break;
            }

            //If a rocket moves of screen, remove it and add score to bird for dodging the rocket.
            if(rockets.get(i).getxCoordinate() < - 150){
                rockets.remove(i);
                bird.addToScore(1);
                System.out.println(bird.getScore());
                break;
            }
        }

        //Loop through all rings
        for(int i = 0; i < rings.size(); i++){
            rings.get(i).updateFrameCounter();
            rings.get(i).updateMoving();

            //If a ring is collected by the bird, remove it and increase score of bird.
            if(spritesIntersects(rings.get(i), bird)){
                playRingSound();
                rings.remove(i);
                bird.addToScore(2);
                break;
            }

            //If a ring moves of screen, remove it.
            if(rings.get(i).getxCoordinate() < - 150){
                rings.remove(i);
                break;
            }
        }

        //Loop through all gems
        for(int i = 0; i < gems.size(); i++){
            gems.get(i).updateFrameCounter();
            gems.get(i).updateMoving();

            //If a gem is collected by the bird, remove it and increase score of bird.
            if(spritesIntersects(gems.get(i), bird)){
                playRingSound();
                gems.remove(i);
                bird.addToScore(10);
                break;
            }

            //If a gem moves of screen, remove it.
            if(gems.get(i).getxCoordinate() < - 150){
                gems.remove(i);
                break;
            }
        }


        if(explosionIsCreated){
            explosion.updateFrameCounter();
            if (explosion.getFrameCounter() == 21){
                bird.setBlank(getResources());
            }
            if(explosion.getFrameCounter() == 42){
                bird.setDead(true);

            }
        }
    } /** The method update is called from the method run.
     * In the method update, a lot of the game-sessions logic is performed.
     * After the logical updates have been done in the method update, these are drawn on the screen by the method draw,
     * which is why the method update is called before the method draw each iteration that the method run is called.
     *
     * I will not write out everything that is done in the method update, but here are a few examples:
     * - The coordinates of the background are updated, in order to make it moving.
     * - For the bird, the coordinates are updated and the current Bitmap that is to be displayed is updated.
     * - On timers, Rings, Rockets and Gems are created.
     * - The ArrayLists containing Rings, Rockets and Gems are iterated through to (among many things):
     *      - update their coordinates;
     *      - update the Bitmap to be displayed for each of them;
     *      - check for collisions with the bird;
     *      - and check if they have moved off the left side of the screen, and then remove them.
     * - It is also checked if an explosion has been created and if so, the Bitmap to displayed for it is updated.
     * */

    private void draw(){
        if(getHolder().getSurface().isValid()){
            canvas = getHolder().lockCanvas();
            canvas.drawBitmap(background.background, background.x, background.y, paint);
            canvas.drawBitmap(mirroredBackground.background, mirroredBackground.x, mirroredBackground.y, paint);
            canvas.drawBitmap(bird.getImage(), bird.getxCoordinate(), bird.getyCoordinate(), paint);
            for(int i = 0; i < rockets.size(); i++){
                canvas.drawBitmap(rockets.get(i).getImage(), rockets.get(i).getxCoordinate(), rockets.get(i).getyCoordinate(), paint);
            }
            for(int i = 0; i < rings.size(); i++){
                canvas.drawBitmap(rings.get(i).getImage(), rings.get(i).getxCoordinate(), rings.get(i).getyCoordinate(), paint);
            }
            for(int i = 0; i < gems.size(); i++){
                canvas.drawBitmap(gems.get(i).getImage(), gems.get(i).getxCoordinate(), gems.get(i).getyCoordinate(), paint);
            }
            if(explosionIsCreated){
                canvas.drawBitmap(explosion.getImage(), explosion.getxCoordinate(), explosion.getyCoordinate(), paint);
            }
            canvas.drawText("Score: " + bird.getScore(), resolutionX * 1/2, 100, scorePaint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    } /**The method draw is called from the method run.
     * It is used to draw all the visual objects in the game-session on a canvas, to make them visible on the screen.
     * */

    private void sleep(){
        try {
            Thread.sleep(17); //equals to updating at 60FPS
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } /**The method sleep is called from the method run.
     * The method makes the Thread running the game sleep for 17 milliseconds every time it is called.
     * This makes the game have a frame rate of 60 FPS.
     */

    private void explosionAndDeath(){
        if( explosionIsCreated == false){
            int birdcenterx = bird.getxCoordinate() + (bird.getWidth() / 2);
            int birdcentery = bird.getyCoordinate() + (bird.getHeight() / 2);
            explosion = new Explosion(getResources(), birdcenterx, birdcentery);
            explosionIsCreated = true;
            mediaPlayer.reset();
            playExplosionSound();
        }
    } /**The method explosionAndDeath is called from the method update if the bird has been hit,
     * either by a rocket or crashing to the bottom of the screen.
     * The functionality in the method explosionAndDeath are supposed to be called only once
     * and are done so only if the boolean explosionIsCreated is equal to false.
     * If explosionIsCreated is false, an explosion is created at the location of the bird
     * and an explosion sound is played using the method playExplosionSound.
     * */

    public boolean spritesIntersects(Sprite first, Sprite second){
        if(Rect.intersects(first.getRectangle(), second.getRectangle())){
            return true;
        } else{
            return false;
        }
    }/** The method spritesIntersects is called multiple times from the method update.
     * It is used to detect if the objects/sprites in the game have collided with each other.
     * E.g. if the bird has caught a gem or the bird has been hit by a rocket.
     * */

//    private void checkAndSavePotentialHighscore(){
//        if(preferences.getInt("highscore", 0) < bird.getScore()){
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putInt("highscore", bird.getScore());
//            editor.apply();
//        }
//    }
    /**The method checkAndSavePotentialHighscore is called from the method update.
     * The method is called if the game-session has ended, or more specifically if the bird is dead.
     * The method saves the score of the game-session as highscore, if it is higher than the previously saved highscore.
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                bird.setGoingUp(true);
                break;
            case MotionEvent.ACTION_UP:
                bird.setGoingUp(false);
                break;
        }
        return true;
    } /**The method onTouchEvent is called every time the user touches the screen.
     * If the user is pressing down on the screen, the bird moves upwards.
     * If the user lets go of the screen, the bird moves downwards.
     * */

    private void playExplosionSound(){
        if(!preferences.getBoolean("soundMuted", false)){
            inGameSoundPool.play(explosionSound, volume, volume, 1, 0, 1f);
        }
    } /**The method playExplosionSound plays the sound of an explosion, if the sound is not muted.*/
    private void playRocketSound(){
        if(!preferences.getBoolean("soundMuted", false)){
            inGameSoundPool.play(rocketSound, volume, volume, 1, 0, 1f);
        }
    } /**The method playRocketSound plays the sound of a rocket being launched, if the sound is not muted.*/
    private void playRingSound(){
        if(!preferences.getBoolean("soundMuted", false)){
            inGameSoundPool.play(ringSound, volume, volume, 1, 0, 1f);
        }
    } /**The method playRingSound plays the sound of a ring/coin being caught, if the sound is not muted.*/
    private void playGameOverSound(){
        if(!preferences.getBoolean("soundMuted", false)){
            inGameSoundPool.play(gameOverSound, volume, volume, 1, 0, 1f);
        }
    } /**The method playGameOverSound plays a short piece of song when the game is over, if the sound is not muted.*/

    public void resume(){
        gameActive = true;
        thread = new Thread(this);
        thread.start();
    } /**The method resume is called from the method onResume in the class Game.
     * It is called when an instance of the class GameView is created, or when the user returns to a game-session after a first one.
     * In the method the boolean gameActive (which is equal to false by default) is set to true, in order to activate the loop in the method run.
     * The variable thread is set to a new instance of the class Thread and then started.
     * */

    public void pause(){
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } /**The method pause is called from the method onPause in the class Game and is called when a game-session is ended and the players returned to the menu.
     * In the method pause, the method join is called on the Thread thread, which terminates it.
     * */

    private void exitGameSession() {
        try {
            gameActive = false;
            Thread.sleep(3000);
            game.startActivity(new Intent(game, MainActivity2.class));
            game.finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } /**The method exitGameSession is called from the method update if the bird is dead (has crashed or been hit by a rocket).
     * In the method exitGameSession, the boolean gameActive is set to false in order to break the loop in the method update.
     * The Thread thread is set to sleep/wait for 3 seconds (in order to let the "game over" sound play).
     * The user is returned to the menu using the class MainActivity, which is displayed using an Intent from the class Game.
     * The method finish is called on the Game game, which closes the game-session.
     * */



}

