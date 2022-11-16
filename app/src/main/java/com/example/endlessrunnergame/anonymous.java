//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.Random;

/**This class is used to create the Rockets in the game.
 * The class anonymous is a subclass of the class Sprite.*/

public class anonymous extends Sprite {
    private int speed;
    Random random = new Random();

    public anonymous(Resources res, int xCoordinate, int yCoordinate, int playerScore){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        image = new Bitmap[13];
        image[0] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[1] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[2] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[3] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[4] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[5] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[6] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[7] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[8] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[9] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[10] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[11] = BitmapFactory.decodeResource(res, R.drawable.ano);
        image[12] = BitmapFactory.decodeResource(res, R.drawable.ano);

        frameCounter = 0;
        width = image[0].getWidth();
        height = image[0].getHeight();

        this.speed = 15 + (random.nextInt(10) * (playerScore /10));
        if(this.speed > 100) {this.speed = 100;}
    } /**The constructor accepts an instance of the class Resources,
     two integers which are the x- and y-coordinate of the Unknown
     and an integer which is the player's score.

     In the constructor, multiple Bitmaps are created using .png files located
     in the res/drawable folder and put in an array which will be used to create
     the anonymous animation.

     Also, the speed of the anonymous is set using the score of the player.
     The speed of the anonymous is never lower than 15 and never higher than 100.
     Between those values a random value is created using the player's score,
     increasing the probability of the speed being higher when the player's score increases.*/

    public void updateFrameCounter(){
        if (frameCounter == 12){
            frameCounter = 0;
        } else{
            frameCounter ++;
        }
    }/** The class anonymous has a variable named frameCounter (an integer),
     that it inherits from the class Sprite,
     which is used to keep track which Bitmap in the class' array is displayed.
     This variable needs to be updated to create the animation and the method
     updateFrameCounter is used for this purpose.*/


    public void updateMoving(){
        xCoordinate -= speed;
    }/**The method updateMoving() is used to move the anonymous across the screen,
     by subtracting the int speed from the Gem's x-coordinate each time the method is called.*/
}
