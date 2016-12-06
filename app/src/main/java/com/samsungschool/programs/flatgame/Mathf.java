package com.samsungschool.programs.flatgame;

import android.animation.ArgbEvaluator;

public class Mathf {

    private static final ArgbEvaluator argb = new ArgbEvaluator();

    public static Vector2D directionToVector(float direction, float length)
    {
        if (length == 0) return new Vector2D();
        return new Vector2D((float)Math.cos(direction) * length, (float)Math.sin(direction) * -length);
    }

    public static float getVectorAngle(Vector2D vector) { return (float) Math.atan2(vector.y, vector.x); }

    public static boolean pointInBox(Vector2D point, Vector2D boxPosition, Vector2D boxScale)
    {
        if (point.x < boxPosition.x) return false;
        if (point.y < boxPosition.y) return false;

        if (point.x > boxPosition.x + boxScale.x) return false;
        if (point.y > boxPosition.y + boxScale.y) return false;

        return true;
    }

    public static float clamp(float value, float min, float max) { return value < min ? min : value > max ? max : value; }
    public static int lerp(int color_a, int color_b, float fraction) { return (int)argb.evaluate(fraction, color_a, color_b); }
}
