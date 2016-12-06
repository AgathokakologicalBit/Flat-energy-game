package com.samsungschool.programs.flatgame.gameobjects;

import android.graphics.Canvas;

import com.samsungschool.programs.flatgame.Assets;
import com.samsungschool.programs.flatgame.GameObject;
import com.samsungschool.programs.flatgame.Mathf;
import com.samsungschool.programs.flatgame.Random;
import com.samsungschool.programs.flatgame.Vector2D;
import com.samsungschool.programs.flatgame.drawing.GUI;
import com.samsungschool.programs.flatgame.drawing.Shape;

public class EnergyBall extends GameObject {

    public GameManager.EnergyType energyType;
    private static final float MaximalOutDistance = 100;

    @Override
    public void start()
    {
        // Set random energy type for electron
        energyType = Random.Range(0, 2) == 0 ? GameManager.EnergyType.Negative : GameManager.EnergyType.Positive;
        setScale(new Vector2D(50, 50));
    }

    @Override
    public void update(float deltaTime) {
        // If energy ball flew out of scree - destroy it
        if (!Mathf.pointInBox(getPosition(), new Vector2D().sub(MaximalOutDistance), GUI.Size.add(MaximalOutDistance * 2)))
            GameObject.destroy(this);
    }

    @Override
    public void draw(Canvas canvas) {
        // Set object color depending on it's energy type
        int color = energyType == GameManager.EnergyType.Positive
                ? Assets.color_red
                : Assets.color_blue;

        GUI.draw(Shape.Circle, getPosition(), getScale(), color);
    }
}
