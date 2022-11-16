//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.graphics.Point;
import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.View;

/**This class extends the class Activity and is used to create a "game-session" when the user chooses to do so from the menu in the game. */

public class Game extends Activity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideNavigationBar();
        Display display = getWindowManager().getDefaultDisplay();
        Point resolution = new Point();
        display.getSize(resolution);
        gameView = new GameView(this, resolution.x, resolution.y);
        setContentView(gameView);
    } /**The method onCreate is called when the activity Game is created from the MainActivity class.
     * In the method onCreate, the method hideNavigationBar is called,
     * and the variable gameView is set to a new instance of the class GameView,
     * to which parameters are passed in order for the game to be displayed correctly on the screen.
     * The content of the screen is then set to gameView, which runs the game.
     * */

    @Override
    protected void onPause() {
        super.onPause();
        hideNavigationBar();
        gameView.pause();
    } /**The method onPause is called when the Game activity is paused, in other words when the player dies in a game-session and is returned to the menu.
     * A call to the method hideNavigationBar is made and the method pause is called on the instance gameView in order to pause the game-session.
     * */


    @Override
    protected void onResume() {
        super.onResume();
        hideNavigationBar();
        gameView.resume();
    }/**The method onResume is called automatically after the method onCreate has been called, when the Game activity is first stared.
     * It is also called in the case that the user has played a game-session and died, but then chooses to replay the game from the menu.
     * The method hideNavigationBar is then called and the method resume is called on the instance gameView in order to launch another game-session.
     * */

    private void hideNavigationBar(){
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }/**The method hideNavigationBar is used to used to hide navigation bar of the phone.*/

}

