package com.samsungschool.programs.flatgame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public final class Assets {
    // Colors
    public static final int color_red = 0xFFAA4433;
    public static final int color_green = 0xFF11AA66;
    public static final int color_blue = 0xFF1166AA;
    public static final int color_gold = 0xFFFFDC00;
    public static final int color_dark = 0xFF333333;
    public static final int color_gray = 0xFF777777;

    // Textures and icons
    public static Bitmap image_spawnerDirect;
    public static Bitmap image_magnetPositive;
    public static Bitmap image_magnetNegative;

    public static void Init(Resources res)
    {
        image_spawnerDirect = BitmapFactory.decodeResource(res, R.drawable.spawner_direct);
        image_magnetPositive = BitmapFactory.decodeResource(res, R.drawable.magnet_positive);
        image_magnetNegative = BitmapFactory.decodeResource(res, R.drawable.magnet_negative);
    }
}
