//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**This class is used to create the Helicopter in the game.
 * The class Helicopter is a subclass of the class Sprite.*/

public class Helicopter extends Sprite {
    private boolean isGoingUp;
    private int directionY;
    private boolean isDead = false;
    private boolean isHit = false;
    private boolean canMove = true;
    private int score = 0;  //score of the player

    public Helicopter(Resources res){
        xCoordinate = 50;
        yCoordinate = GameView.resolutionY * (1/4);
        directionY = 0;
        image = new Bitmap[4];
        image[0] = BitmapFactory.decodeResource(res, R.drawable.sp0);
        image[1] = BitmapFactory.decodeResource(res, R.drawable.sp1);
        image[2] = BitmapFactory.decodeResource(res, R.drawable.sp2);
        image[3] = BitmapFactory.decodeResource(res, R.drawable.sp3);

        frameCounter = 0;

        width = image[0].getWidth() - 80;
        height = image[0].getHeight();
    } /**The constructor accepts an instance of the class Resources,
     which is used for creating the Bitmaps that will be put in the class' Bitmap-array.
     In the constructor, multiple Bitmaps are created using .png files located
     in the res/drawable folder and put in an array which will be used to create
     the Helicopter animation.

     The variable xCoordinate is set to 50, which is never changed.
     The variable yCoordinate is set to 25 percent (or 1/4) of the screen's height initially,
     but this value is changed as the helicopter moves up and down.

     Initially the the variable directionY (which indicates the movement of the helicopter vertically),
     is set to 0.
     */

    public void updateMoving(){
        if(canMove){

            if(isGoingUp){
                directionY -= 1;
            } /**If the player is pressing the screen, isGoingUp is set to true, and directionY is subtracted by 1.*/
            else {
                directionY += 1;
            }/**If the player is not pressing the screen, isGoingUp is set to false, and directionY is added by 1.*/
            if(directionY>10){directionY=10;} /**directionY is capped to 10 at the upper boundary, so that the movement downwards is not increased too much.*/
            if(directionY<-10){directionY = -10;} /**directionY is capped to -10 at the lower boundary, so that the movement upwards is not increased too much*/
            yCoordinate+=directionY*3; /**directionY is then multiplied by 3 and added to yCoordinate, so that the actual Helicopter moves up or down
             depending on the value of directionY.*/

            if(yCoordinate <= 0){
                yCoordinate = 0;
            } /**If the yCoordinate is equal to or lower than 0, meaning if the Helicopter is moving off the screen
             above it, the Helicopter's yCoordinate is set to 0, hindering the Helicopter to move above the screen.*/

            if((yCoordinate + image[0].getHeight()) > GameView.resolutionY){
                isHit = true;
                canMove = false;
            }/**If the bottom of the Helicopter touches the bottom of the screen, in other words the "ground"
             the booleans isHit and canMove is set to false.*/

        } /**The yCoordinate can only change is the boolean canMove is equal to true.*/

    } /**The method updateMoving is used to change the yCoordinate of the Helicopter,
     as the Helicopter moves up when the user presses (and possibly holds on the screen)
     and the Helicopter moves down when the user is not pressing on the screen.
     Please see comments inside the method for further explanation.*/

    public void updateFrameCounter(){
        if (frameCounter == 3){
            frameCounter = 0;
        } else{
            frameCounter ++;
        }
    } /** The class Helicopter has a variable named frameCounter (an integer),
     that it inherits from the class Sprite,
     which is used to keep track which Bitmap in the class' array is displayed.
     This variable needs to be updated to create the animation and the method
     updateFrameCounter is used for this purpose.*/

    public void setGoingUp(boolean goingUp) {
        isGoingUp = goingUp;
    }/** The method setGoingUp is used to change the value of the boolean isGoingUp.*/

    public boolean isDead() {
        return isDead;
    }/**The method isDead is used to retrieve the boolean isDead.*/

    public void setDead(boolean dead) {
        isDead = dead;
    } /**The method setDead is used to change the value of the boolean isDead.*/

    public int getScore() {
        return score;
    }/**The method getScore is used to retrieve the int score.*/

    public void addToScore(int toAdd){
        score += toAdd;
    } /**The method addToScore is used to increase the variable score by the value of the int passed to the method.*/

    public boolean isHit() {
        return isHit;
    } /**The method isHit is used to retrieve the boolean isHit.*/

    public void setHit(boolean hit) {
        isHit = hit;
    }/**The method setHit is used to change the value of the boolean isHit.*/

    public void setCanMove(boolean move){
        canMove = move;
    } /**The method setCanMove is used to change the value of the boolean canMove.*/

    public void setBlank(Resources res){
        image[0] = BitmapFactory.decodeResource(res, R.drawable.ex42);
        image[1] = BitmapFactory.decodeResource(res, R.drawable.ex42);
        image[2] = BitmapFactory.decodeResource(res, R.drawable.ex42);
        image[3] = BitmapFactory.decodeResource(res, R.drawable.ex42);
    } /**When an instance of the class Explosion is created, it is drawn upon the Helicopter.
     It is then necessary for the Helicopter to vanish. This is done by setting the Bitmaps
     in the Bitmap-array to blank .png images, which is done in the method setBlank.*/

}

