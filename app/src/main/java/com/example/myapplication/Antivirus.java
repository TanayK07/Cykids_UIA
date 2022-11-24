package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Antivirus {
    int x = 0, y = 0;
    Bitmap antivirus;

    Antivirus (int size, Resources res) {

        antivirus = BitmapFactory.decodeResource(res, R.drawable.antivirus);
        antivirus = Bitmap.createScaledBitmap(antivirus, size, size, false);

    }
}
