package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

import androidx.core.view.animation.PathInterpolatorCompat;

public class misc {
    public static Interpolator interpolator = PathInterpolatorCompat.create(0.420f, 0.000f, 0.045f, 0.960f);
    public static ValueAnimator animation(View view, String property, float valueFrom, float valueTo, int duration, TimeInterpolator interpolator){
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(view, property, valueFrom, valueTo);
        fadeAnim.setDuration(duration);
        fadeAnim.setInterpolator(interpolator);
        return fadeAnim;
    }
    public static ValueAnimator animation(View view, String property, float valueFrom, float valueTo, int duration){
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(view, property, valueFrom, valueTo);
        fadeAnim.setDuration(duration);
        fadeAnim.setInterpolator(misc.interpolator);
        return fadeAnim;
    }
    public static ValueAnimator animation(View view, String property, float valueTo, int duration){
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(view, property, valueTo);
        fadeAnim.setDuration(duration);
        fadeAnim.setInterpolator(misc.interpolator);
        return fadeAnim;
    }
    public static ValueAnimator appear(View view){
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 1f);
        fadeAnim.setDuration(1000);
        fadeAnim.setInterpolator(misc.interpolator);
        return fadeAnim;
    }
    public static ValueAnimator disappear(View view){
        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f);
        fadeAnim.setDuration(1000);
        fadeAnim.setInterpolator(misc.interpolator);
        return fadeAnim;
    }
}
