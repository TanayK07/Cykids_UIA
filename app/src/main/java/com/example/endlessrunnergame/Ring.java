//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

/**This class is used to create the Rings in the game.
 * The class Ring is a subclass of the class Sprite.*/

public class Ring extends Sprite{
    private int speed;
    Random random = new Random();

    public Ring(Resources res, int xCoordinate, int yCoordinate, int playerScore){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        image = new Bitmap[30];
        image[0] = BitmapFactory.decodeResource(res, R.drawable.ring0);
        image[1] = BitmapFactory.decodeResource(res, R.drawable.ring1);
        image[2] = BitmapFactory.decodeResource(res, R.drawable.ring2);
        image[3] = BitmapFactory.decodeResource(res, R.drawable.ring3);
        image[4] = BitmapFactory.decodeResource(res, R.drawable.ring4);
        image[5] = BitmapFactory.decodeResource(res, R.drawable.ring5);
        image[6] = BitmapFactory.decodeResource(res, R.drawable.ring6);
        image[7] = BitmapFactory.decodeResource(res, R.drawable.ring7);
        image[8] = BitmapFactory.decodeResource(res, R.drawable.ring8);
        image[9] = BitmapFactory.decodeResource(res, R.drawable.ring9);
        image[10] = BitmapFactory.decodeResource(res, R.drawable.ring10);
        image[11] = BitmapFactory.decodeResource(res, R.drawable.ring11);
        image[12] = BitmapFactory.decodeResource(res, R.drawable.ring12);
        image[13] = BitmapFactory.decodeResource(res, R.drawable.ring13);
        image[14] = BitmapFactory.decodeResource(res, R.drawable.ring14);
        image[15] = BitmapFactory.decodeResource(res, R.drawable.ring15);
        image[16] = BitmapFactory.decodeResource(res, R.drawable.ring16);
        image[17] = BitmapFactory.decodeResource(res, R.drawable.ring17);
        image[18] = BitmapFactory.decodeResource(res, R.drawable.ring18);
        image[19] = BitmapFactory.decodeResource(res, R.drawable.ring19);
        image[20] = BitmapFactory.decodeResource(res, R.drawable.ring20);
        image[21] = BitmapFactory.decodeResource(res, R.drawable.ring21);
        image[22] = BitmapFactory.decodeResource(res, R.drawable.ring22);
        image[23] = BitmapFactory.decodeResource(res, R.drawable.ring23);
        image[24] = BitmapFactory.decodeResource(res, R.drawable.ring24);
        image[25] = BitmapFactory.decodeResource(res, R.drawable.ring25);
        image[26] = BitmapFactory.decodeResource(res, R.drawable.ring26);
        image[27] = BitmapFactory.decodeResource(res, R.drawable.ring27);
        image[28] = BitmapFactory.decodeResource(res, R.drawable.ring28);
        image[29] = BitmapFactory.decodeResource(res, R.drawable.ring29);

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

