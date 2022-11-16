//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**This class is used to create the Explosion,
 *  which occurs when the player crashes the helicopter or gets hit by a rocket.
 *  The class Explosion is subclass of the class Sprite.*/

public class Explosion extends Sprite {

    public Explosion(Resources res, int helicoptercenterx, int helicoptercentery){
        frameCounter = 0;
        image = new Bitmap[43];
        image[0] = BitmapFactory.decodeResource(res, R.drawable.ex0);
        image[1] = BitmapFactory.decodeResource(res, R.drawable.ex1);
        image[2] = BitmapFactory.decodeResource(res, R.drawable.ex2);
        image[3] = BitmapFactory.decodeResource(res, R.drawable.ex3);
        image[4] = BitmapFactory.decodeResource(res, R.drawable.ex4);
        image[5] = BitmapFactory.decodeResource(res, R.drawable.ex5);
        image[6] = BitmapFactory.decodeResource(res, R.drawable.ex6);
        image[7] = BitmapFactory.decodeResource(res, R.drawable.ex7);
        image[8] = BitmapFactory.decodeResource(res, R.drawable.ex8);
        image[9] = BitmapFactory.decodeResource(res, R.drawable.ex9);
        image[10] = BitmapFactory.decodeResource(res, R.drawable.ex10);
        image[11] = BitmapFactory.decodeResource(res, R.drawable.ex11);
        image[12] = BitmapFactory.decodeResource(res, R.drawable.ex12);
        image[13] = BitmapFactory.decodeResource(res, R.drawable.ex13);
        image[14] = BitmapFactory.decodeResource(res, R.drawable.ex14);
        image[15] = BitmapFactory.decodeResource(res, R.drawable.ex15);
        image[16] = BitmapFactory.decodeResource(res, R.drawable.ex16);
        image[17] = BitmapFactory.decodeResource(res, R.drawable.ex17);
        image[18] = BitmapFactory.decodeResource(res, R.drawable.ex18);
        image[19] = BitmapFactory.decodeResource(res, R.drawable.ex19);
        image[20] = BitmapFactory.decodeResource(res, R.drawable.ex20);
        image[21] = BitmapFactory.decodeResource(res, R.drawable.ex21);
        image[22] = BitmapFactory.decodeResource(res, R.drawable.ex22);
        image[23] = BitmapFactory.decodeResource(res, R.drawable.ex23);
        image[24] = BitmapFactory.decodeResource(res, R.drawable.ex24);
        image[25] = BitmapFactory.decodeResource(res, R.drawable.ex25);
        image[26] = BitmapFactory.decodeResource(res, R.drawable.ex26);
        image[27] = BitmapFactory.decodeResource(res, R.drawable.ex27);
        image[28] = BitmapFactory.decodeResource(res, R.drawable.ex28);
        image[29] = BitmapFactory.decodeResource(res, R.drawable.ex29);
        image[30] = BitmapFactory.decodeResource(res, R.drawable.ex30);
        image[31] = BitmapFactory.decodeResource(res, R.drawable.ex31);
        image[32] = BitmapFactory.decodeResource(res, R.drawable.ex32);
        image[33]= BitmapFactory.decodeResource(res, R.drawable.ex33);
        image[34] = BitmapFactory.decodeResource(res, R.drawable.ex34);
        image[35] = BitmapFactory.decodeResource(res, R.drawable.ex35);
        image[36]= BitmapFactory.decodeResource(res, R.drawable.ex36);
        image[37]= BitmapFactory.decodeResource(res, R.drawable.ex37);
        image[38] = BitmapFactory.decodeResource(res, R.drawable.ex38);
        image[39] = BitmapFactory.decodeResource(res, R.drawable.ex39);
        image[40] = BitmapFactory.decodeResource(res, R.drawable.ex40);
        image[41] = BitmapFactory.decodeResource(res, R.drawable.ex41);
        image[42] = BitmapFactory.decodeResource(res, R.drawable.ex42);

        this.xCoordinate = helicoptercenterx - (image[0].getWidth() / 2);
        this.yCoordinate = helicoptercentery - (image[0].getHeight() / 2);
    } /**The constructor accepts an instance of the class Resources
     and two integers which are the x- and y-coordinates of the helicopter
     which upon the Explosion will be created.
     In the constructor, multiple Bitmaps are created using .png files located
     in the res/drawable folder and put in an array which will be used to create
     the Explosion animation.*/

    public void updateFrameCounter(){
        if (frameCounter == 42){
            frameCounter = 42;
        } else{
            frameCounter ++;
        }
    } /** The class Explosion has a variable named frameCounter (an integer),
     that it inherits from the class Sprite,
     which is used to keep track of which Bitmap in the object's array is displayed.
     This variable needs to be updated to create the animation and the method
     updateFrameCounter is used for this purpose.*/

    public int getFrameCounter(){
        return frameCounter;
    } /**The method getFrameCounter is used to retrieve the variable frameCounter.*/

}

