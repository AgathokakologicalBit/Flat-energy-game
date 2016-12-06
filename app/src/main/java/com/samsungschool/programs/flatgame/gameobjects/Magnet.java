package com.samsungschool.programs.flatgame.gameobjects;

import android.graphics.Canvas;

import com.samsungschool.programs.flatgame.Assets;
import com.samsungschool.programs.flatgame.GameObject;
import com.samsungschool.programs.flatgame.Scene;
import com.samsungschool.programs.flatgame.TouchableObject;
import com.samsungschool.programs.flatgame.Vector2D;
import com.samsungschool.programs.flatgame.drawing.GUI;

import java.util.ArrayList;

public class Magnet extends GameObject implements TouchableObject {

    public GameManager.EnergyType energyType = GameManager.EnergyType.Positive;
    public float strength = 50;
    public float magneticFieldArea = 500;

    @Override
    public void start() {
        setScale(new Vector2D(100, 100));
    }

    @Override
    public void update(float deltaTime) {
        Vector2D position = getPosition();
        float radius = getScale().x / 2;

        // Find all balls on scene
        ArrayList<EnergyBall> balls = Scene.findObjectsWithType(EnergyBall.class);

        // Check collisions between Magnet and Balls
        for (EnergyBall ball: balls) {
            float distance = position.sub(ball.getPosition()).getLength();

            if (distance <= ball.getScale().x / 2 + radius) {
                // If ball hits magnet - destroy it
                GameObject.destroy(ball);
            }
        }
    }

    @Override public void onTouch(Vector2D touchPosition) {
        if (GameManager.isGameOver()) return;

        energyType = energyType == GameManager.EnergyType.Positive
                ? GameManager.EnergyType.Negative
                : GameManager.EnergyType.Positive;
    }

    @Override
    public void draw(Canvas canvas) {
        GUI.draw(
                energyType == GameManager.EnergyType.Positive
                        ? Assets.image_magnetPositive
                        : Assets.image_magnetNegative,
                getPosition(),
                getScale(),
                getRotation()
        );
    }
}
