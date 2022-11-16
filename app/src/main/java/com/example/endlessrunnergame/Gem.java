//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**This class is used to create the Gems in the game.
 * The class Gem is a subclass of the class Sprite.*/

public class Gem extends Sprite{
    private int speed;
    Random random = new Random();

    public Gem(Resources res, int xCoordinate, int yCoordinate, int playerScore){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        image = new Bitmap[8];
        image[0] = BitmapFactory.decodeResource(res, R.drawable.gem0);
        image[1] = BitmapFactory.decodeResource(res, R.drawable.gem1);
        image[2] = BitmapFactory.decodeResource(res, R.drawable.gem2);
        image[3] = BitmapFactory.decodeResource(res, R.drawable.gem3);
        image[4] = BitmapFactory.decodeResource(res, R.drawable.gem4);
        image[5] = BitmapFactory.decodeResource(res, R.drawable.gem5);
        image[6] = BitmapFactory.decodeResource(res, R.drawable.gem6);
        image[7] = BitmapFactory.decodeResource(res, R.drawable.gem7);

        frameCounter = 0;
        width = image[0].getWidth();
        height = image[0].getHeight();

        this.speed = 15 + (random.nextInt(10) * (playerScore /10));
        if(this.speed > 30) {this.speed = 30;}
    } /**The constructor accepts an instance of the class Resources,
     two integers that are the x- and y-coordinate of the Gem
     and an integer which is the player's score.

     In the constructor, multiple Bitmaps are created using .png files located
     in the res/drawable folder and put in an array which will be used to create
     the Gem animation.

     Also, the speed of the Gem is set using the score of the player.
     The speed of the Gem is never lower than 15 and never higher than 30.
     Between those values a random value is created using the player's score,
     increasing the probability of the speed being higher when the player's score increases.
     */

    public void updateFrameCounter(){
        if (frameCounter == 7){
            frameCounter = 0;
        } else{
            frameCounter ++;
        }
    } /** The class Gem has a variable named frameCounter (an integer),
     that it inherits from the class Sprite,
     which is used to keep track which Bitmap in the class' array is displayed.
     This variable needs to be updated to create the animation and the method
     updateFrameCounter is used for this purpose.*/

    public void updateMoving(){
        xCoordinate -= speed;
    } /**The method updateMoving() is used to move the Gem across the screen,
     by subtracting the int speed from the Gem's x-coordinate each time the method is called.*/

}

