//Author: Alexander Dolk

package com.example.endlessrunnergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/** This class is used to create Background in the game. */

public class Background {
    int x=0, y=0;
    Bitmap background;

    Background (int resolutionX, int resolutionY, Resources resources){
        background = BitmapFactory.decodeResource(resources, R.drawable.cyberbackground);
        background = Bitmap.createScaledBitmap(background, resolutionX, resolutionY, false);
    } /**The constructor of the class Background takes an instance of the class Resources and two integers, which are the
     screen dimensions of the phone. The Resources instance and the integers are then used to create a Bitmap
     which is scaled to size to fit the phone screen.*/
}
