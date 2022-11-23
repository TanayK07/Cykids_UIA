//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Random;

/**This class is used to create the Rings in the game.
 * The class Ring is a subclass of the class Sprite.*/

public class Ring extends Sprite{
    private int speed;
    Random random = new Random();
    public Ring(Resources res, int xCoordinate, int yCoordinate, int playerScore){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        Integer[] images = {R.drawable.p0, R.drawable.p1, R.drawable.p2};
        Integer friend = images[random.nextInt(3)];
        image = new Bitmap[30];
        image[0] = BitmapFactory.decodeResource(res,friend);
        image[1] = BitmapFactory.decodeResource(res, friend);
        image[2] = BitmapFactory.decodeResource(res, friend);
        image[3] = BitmapFactory.decodeResource(res, friend);
        image[4] = BitmapFactory.decodeResource(res, friend);
        image[5] = BitmapFactory.decodeResource(res, friend);
        image[6] = BitmapFactory.decodeResource(res, friend);
        image[7] = BitmapFactory.decodeResource(res, friend);
        image[8] = BitmapFactory.decodeResource(res, friend);
        image[9] = BitmapFactory.decodeResource(res, friend);
        image[10] = BitmapFactory.decodeResource(res, friend);
        image[11] = BitmapFactory.decodeResource(res, friend);
        image[12] = BitmapFactory.decodeResource(res, friend);
        image[13] = BitmapFactory.decodeResource(res, friend);
        image[14] = BitmapFactory.decodeResource(res, friend);
        image[15] = BitmapFactory.decodeResource(res, friend);
        image[16] = BitmapFactory.decodeResource(res, friend);
        image[17] = BitmapFactory.decodeResource(res, friend);
        image[18] = BitmapFactory.decodeResource(res, friend);
        image[19] = BitmapFactory.decodeResource(res, friend);
        image[20] = BitmapFactory.decodeResource(res, friend);
        image[21] = BitmapFactory.decodeResource(res, friend);
        image[22] = BitmapFactory.decodeResource(res, friend);
        image[23] = BitmapFactory.decodeResource(res, friend);
        image[24] = BitmapFactory.decodeResource(res, friend);
        image[25] = BitmapFactory.decodeResource(res, friend);
        image[26] = BitmapFactory.decodeResource(res, friend);
        image[27] = BitmapFactory.decodeResource(res, friend);
        image[28] = BitmapFactory.decodeResource(res, friend);
        image[29] = BitmapFactory.decodeResource(res, friend);

        frameCounter = 0;
        width = image[0].getWidth();
        height = image[0].getHeight();

        this.speed = 15 + (random.nextInt(10) * (playerScore/10));
        if(this.speed > 30) {this.speed = 30;}
    }/**The constructor accepts an instance of the class Resources,
     two integers that are the x- and y-coordinate of the Gem
     and an integer which is the player's score.

     In the constructor, multiple Bitmaps are created using .png files located
     in the res/drawable folder and put in an array which will be used to create
     the Ring animation.

     Also, the speed of the Ring is set using the score of the player.
     The speed of the Gem is never lower than 15 and never higher than 30.
     Between those values a random value is created using the player's score,
     increasing the probability of the speed being higher when the player's score increases.*/

    public void updateFrameCounter(){
        if (frameCounter == 29){
            frameCounter = 0;
        } else{
            frameCounter ++;
        }
    }/** The class Ring has a variable named frameCounter (an integer),
     that it inherits from the class Sprite,
     which is used to keep track which Bitmap in the class' array is displayed.
     This variable needs to be updated to create the animation and the method
     updateFrameCounter is used for this purpose.*/

    public void updateMoving(){
        xCoordinate -= speed;
    }/**The method updateMoving() is used to move the Ring across the screen,
     by subtracting the int speed from the Gem's x-coordinate each time the method is called.*/

}

