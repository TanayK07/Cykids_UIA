//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.sax.StartElementListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**The class MainActivity works as the menu of the game, which is displayed when the game is launched.*/

public class MainActivity extends AppCompatActivity {

    private SoundPool menuSoundPool;
    private int playGameSound;
    private int clickSound;
    float actVolume, maxVolume, volume;
    AudioManager audioManager;
    MediaPlayer mediaPlayer;
    private boolean soundMuted;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideNavigationBar(); /**The method hideNavigationBar is called.*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); /**The game is set to fullscreen.*/
        setContentView(R.layout.activity_main); /**The displayed content is set to that written in the file activity_main.xml .*/

        /**Below, audioManager is set to an instance of the class AudioManager and necessary settings adjusted to the volume of sounds in the game.*/
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        actVolume = (float) audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        maxVolume = (float) audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volume = actVolume / maxVolume;
        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);

        /**Below, menuSoundPool is set to an instance of the class SoundPool and to it multiple sounds added that will be played in the menu.*/
        menuSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        playGameSound = menuSoundPool.load(this, R.raw.playgame_sound, 1);
        clickSound = menuSoundPool.load(this, R.raw.click_sound, 1);

        /**Below, mediaPlayer is set to an instance of the class MediaPlayer containing the song that will be played in the menu.*/
        mediaPlayer = MediaPlayer.create(this, R.raw.menu_song);

        /**Below, an instance of the class SharedPreferences is created and used to retrieve the high score from previous game-sessions.
         * Also, soundMuted is set to the value that it was the last time the game was played.*/
        final SharedPreferences preferences = getSharedPreferences("game", MODE_PRIVATE);
//        TextView highScoreText = findViewById(R.id.highScoreText);
//        highScoreText.setText("Highscore: " + preferences.getInt("highscore", 0));
        soundMuted = preferences.getBoolean("soundMuted", false);

        /**Below, the ImageView volume is created and set to the ImageView volumeController defined in the file activity_main.xml .*/
        final ImageView volume = findViewById(R.id.volumeController);

        if(soundMuted){
            volume.setImageResource(R.drawable.ic_volume_off_black_24dp);
        } else {
            volume.setImageResource(R.drawable.ic_volume_up_black_24dp);
        } /**If soundMuted is true, the ImageResource of volume is set to an icon to indicate this,
         otherwise the ImageResource is set to an icon to indicate that volume is not muted.*/

        if(!soundMuted){
            mediaPlayer.start();
        } /**If the sound is not muted, mediaPlayer will start and play the "menu-song".*/

        if(!mediaPlayer.isPlaying() && !soundMuted){
            mediaPlayer.start();
        }/**If the sound is not muted and mediaPlayer is not playing, the mediaPlayer will start.*/

        volume.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                soundMuted = !soundMuted;
                if(soundMuted){
                    playClickSound();
                    volume.setImageResource(R.drawable.ic_volume_off_black_24dp);
                    mediaPlayer.pause();
                } else{
                    playClickSound();
                    volume.setImageResource(R.drawable.ic_volume_up_black_24dp);
                }
                if(!soundMuted){
                    mediaPlayer.start();
                }
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("soundMuted", soundMuted);
                editor.apply();
            }
        }); /**If the ImageView volume is clicked upon,
         all sound in the game is muted if it previously was not muted,
         and the ImageResource is changed to indicate that all sound is now muted.
         If volume is clicked upon and the sound is muted,
         all sounds become non-muted and the ImageResource is changed to indicate this.*/

        findViewById(R.id.playText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.reset();
                playPlayGameSound();
                startActivity(new Intent(MainActivity.this, Game.class));
            }
        }); /**If the text playText (defined in the file activity_main.xml) is clicked upon,
         mediaPlayer is reset, the method playPlayGameSound is called and finally an activity is started using the class Game.*/

    } /**The method onCreate is called when the game is launched, please see comments in the method for further details.*/

    private void playPlayGameSound(){
        if(!soundMuted){
            menuSoundPool.play(playGameSound, volume, volume, 1, 0, 1f);
        }
    }/**The method playPlayGameSound is used to play a sound from menuSoundPool when the game starts,
     if soundMuted is not equal to true.*/

    private void playClickSound(){
        menuSoundPool.play(clickSound, volume, volume, 1, 0, 1f);
    } /**The method playClickSound is used to play a "click" sound from menuSoundPool.*/

    private void hideNavigationBar(){
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    } /**The method hideNavigationBar is used to used to hide navigation bar of the phone.*/

    @Override
    protected void onPause(){
        mediaPlayer.pause();
        super.onPause();
    }

    @Override
    protected void onResume(){
        mediaPlayer.start();
        super.onResume();
    }
}