package com.samsungschool.programs.flatgame.gameobjects;

import android.graphics.Canvas;

import com.samsungschool.programs.flatgame.Assets;
import com.samsungschool.programs.flatgame.GameObject;
import com.samsungschool.programs.flatgame.Mathf;
import com.samsungschool.programs.flatgame.Vector2D;
import com.samsungschool.programs.flatgame.drawing.GUI;

public class EnergyBallSpawner extends GameObject {

    public float energyBallsSpeed = 100;

    @Override
    public void start() {
        this.setScale(new Vector2D(100, 100));
    }

    public void spawnEnergyBall()
    {
        EnergyBall obj = GameObject.instantiate(EnergyBall.class);

        Vector2D direction = Mathf.directionToVector(getRotation(), 1);
        obj.setPosition(this.getPosition().sub(direction.mult(100)));
        obj.setVelocity(direction.mult(energyBallsSpeed));
    }

    @Override
    public void draw(Canvas canvas) {
        if (Assets.image_spawnerDirect == null) return;

        GUI.draw(Assets.image_spawnerDirect, this.getPosition(), getScale(), getRotation());
    }
}
