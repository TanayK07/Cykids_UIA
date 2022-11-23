package com.example.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class File {
    Bitmap file1, file2, file3;
    static final int iconSize = 100;
    File (Resources res) {
        file1 = BitmapFactory.decodeResource(res, R.drawable.folder);
        file1 = Bitmap.createScaledBitmap(file1, iconSize, iconSize, false);

        file2 = BitmapFactory.decodeResource(res, R.drawable.pc);
        file2 = Bitmap.createScaledBitmap(file2, iconSize, iconSize, false);

        file3 = BitmapFactory.decodeResource(res, R.drawable.msg);
        file3 = Bitmap.createScaledBitmap(file3, iconSize, iconSize, false);

    }
}
