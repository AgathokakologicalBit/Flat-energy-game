package com.samsungschool.programs.flatgame.drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.samsungschool.programs.flatgame.Vector2D;

public class GUI {

    public static int Width, Height;
    public static Vector2D Size;

    private static Canvas canvas;
    private static Paint paint = new Paint();

    /**
     * Drawing Bitmap at given position, with given scale in units with specified rotation
     * @param bmp Bitmap to draw
     * @param position position in units as Vector2D
     * @param scale scale in units as Vector2D
     * @param angle angle in radians
     */
    public static void draw(Bitmap bmp, Vector2D position, Vector2D scale, float angle)
    {
        // Setting color to WHITE without Alpha channel(Opacity)
        paint.setColor(0xFFFFFFFF);

        Matrix matrix = new Matrix();

        matrix.setTranslate(-bmp.getWidth() / 2, -bmp.getHeight() / 2);
        matrix.postRotate((float) (angle * -180 / Math.PI) + 90);
        matrix.postTranslate(position.x / scale.x * bmp.getWidth(), position.y / scale.y * bmp.getHeight());
        matrix.postScale(scale.x / bmp.getWidth(), scale.y / bmp.getHeight());

        canvas.drawBitmap(bmp, matrix, paint);
    }

    /**
     * Draws specified filled shape on screen with given position in units, scale in units and color as ARGB integer
     * @param shape Target shape
     * @param position position in units
     * @param scale scale in units
     * @param color color as ARGB Integer
     */
    public static void draw(Shape shape, Vector2D position, Vector2D scale, int color)
    {
        paint.setColor(color);

        switch (shape)
        {
            case Circle:
                canvas.drawCircle(position.x, position.y, scale.x / 2, paint);
                break;
            case Rectangle:
                Vector2D offset = scale.div(2);
                canvas.drawRect(position.x - offset.x, position.y - offset.y, position.x + offset.x, position.y + offset.y, paint);
                break;
        }
    }

    /**
     * Sets canvas to draw on.
     * ** USED BY Scene, DO NOT use it in you code **
     * @param canvas target canvas
     */
    public static void setCanvas(Canvas canvas) { GUI.canvas = canvas; }
}
