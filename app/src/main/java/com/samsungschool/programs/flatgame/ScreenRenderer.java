package com.samsungschool.programs.flatgame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.samsungschool.programs.flatgame.drawing.GUI;


public class ScreenRenderer extends View implements View.OnTouchListener {
    public ScreenRenderer(Context context) {
        super(context);

        Assets.Init(getResources());
        setOnTouchListener(this);

        invalidate();
    }

    public ScreenRenderer(Context context, AttributeSet attrs) {
        super(context, attrs);

        Assets.Init(getResources());
        setOnTouchListener(this);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (Scene.currentScene != null) {
            Scene.currentScene.setCanvas(canvas);
            GUI.setCanvas(canvas);

            Scene.currentScene.update();
            Scene.currentScene.draw();
        }

        invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Scene.currentScene.onTouch(new Vector2D(event.getX(), event.getY()));
        return false;
    }
}
