package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Antivirus {
    int x = 1080, y = 400, width, length;
    Bitmap antivirus;

    Antivirus (int size, Resources res) {
        antivirus = BitmapFactory.decodeResource(res, R.drawable.antivirus);
        antivirus = Bitmap.createScaledBitmap(antivirus, size, size, false);
        width = size;
        length = size;
        x = GameActivity.point.x/2;
        y = GameActivity.point.y/2;
    }
}
