package com.example.myapplication;
import static com.example.myapplication.GameView.screenRatioX;
import static com.example.myapplication.GameView.screenRatioY;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Trojan extends Malware{
    Trojan (Resources res) {

        malware1 = BitmapFactory.decodeResource(res, R.drawable.trojan1);
        malware2 = BitmapFactory.decodeResource(res, R.drawable.trojan1);

        width = malware1.getWidth();
        height = malware1.getHeight();

        width /= 6;
        height /= 6;

        width = (int) (width * screenRatioX *2);
        height = (int) (height * screenRatioY *2);
        int w1 = (int) (width * 0.99);
        int h1 = (int) (height * 0.99);
        malware1 = Bitmap.createScaledBitmap(malware1, width, height, false);
        malware2 = Bitmap.createScaledBitmap(malware2, w1, h1, false);

        y = -height;
    }
}
