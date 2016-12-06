package com.samsungschool.programs.flatgame.gameobjects;

import android.graphics.Canvas;

import com.samsungschool.programs.flatgame.Assets;
import com.samsungschool.programs.flatgame.GameObject;
import com.samsungschool.programs.flatgame.Mathf;
import com.samsungschool.programs.flatgame.Scene;
import com.samsungschool.programs.flatgame.Vector2D;
import com.samsungschool.programs.flatgame.drawing.GUI;
import com.samsungschool.programs.flatgame.drawing.Shape;

import java.util.ArrayList;

public class Player extends GameObject {

    private float health;
    private float maxHealth = 3;

    @Override
    public void start() {
        this.setScale(new Vector2D(100, 100));
        this.health = maxHealth;
    }

    @Override
    public void update(float deltaTime) {
        Vector2D position = getPosition();
        float radius = getScale().x / 2;

        // Find all balls on scene
        ArrayList<EnergyBall> balls = Scene.findObjectsWithType(EnergyBall.class);

        // Check collisions between player and electrons
        for (EnergyBall ball: balls) {
            float distance = position.sub(ball.getPosition()).getLength();

            if (distance <= ball.getScale().x / 2 + radius) {
                // If ball hit player - reduce player's health
                // And destroy ball, that hit us, to prevent getting extra damage next frame
                this.health = Mathf.clamp(this.health - 1, 0, maxHealth);
                GameObject.destroy(ball);
            }
        }

        // If player is dead(He's health is below(<) zero(0)) - end game
        if (health == 0) GameManager.endGame();
    }

    @Override
    public void draw(Canvas canvas) {
        // Set color of object from gold(full health) to gray(no health)
        // Depending on health percentage
        int color = Mathf.lerp(Assets.color_gray, Assets.color_gold, health / maxHealth);

        // Draw shape using GUI class
        GUI.draw(Shape.Circle, getPosition(), getScale(), color);
    }
}
