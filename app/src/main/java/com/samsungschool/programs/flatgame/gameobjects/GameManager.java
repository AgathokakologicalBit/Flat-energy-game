package com.samsungschool.programs.flatgame.gameobjects;

import com.samsungschool.programs.flatgame.Entity;
import com.samsungschool.programs.flatgame.Random;
import com.samsungschool.programs.flatgame.Scene;
import com.samsungschool.programs.flatgame.Vector2D;

import java.util.ArrayList;

public class GameManager extends Entity {

    private static GameManager instance;

    public float energyBallSpawnTime = 3;
    private float timer;

    private boolean isGameOver = false;

    enum EnergyType {
        Positive,
        Negative
    }

    private ArrayList<Magnet> magnets;
    private ArrayList<EnergyBallSpawner> spawners;

    @Override
    public void start() {
        magnets = Scene.findObjectsWithType(Magnet.class);
        spawners = Scene.findObjectsWithType(EnergyBallSpawner.class);

        instance = this;
    }

    @Override
    public void update(float deltaTime) {
        timer += deltaTime;
        if (timer > energyBallSpawnTime)
        {
            timer = 0;

            int spawnerId = Random.Range(0, spawners.size());
            spawners.get(spawnerId).spawnEnergyBall();
        }
    }

    @Override
    public void postUpdate(float deltaTime) {
        ArrayList<EnergyBall> balls = Scene.findObjectsWithType(EnergyBall.class);

        for (Magnet magnet: magnets){
            Vector2D magnetPosition = magnet.getPosition();

            for (EnergyBall ball: balls) {
                Vector2D relativePosition = ball.getPosition().sub(magnetPosition);
                float rawStrength = magnet.magneticFieldArea - relativePosition.getLength();
                float strength =  rawStrength / magnet.magneticFieldArea * magnet.strength;
                if (strength <= 0) continue;

                // If ball have opposite energy type - reverse strength and velocity
                if (ball.energyType != magnet.energyType) strength *= -1;

                Vector2D acceleration = relativePosition.normalized().mult(strength * deltaTime);
                ball.accelerate(acceleration);
            }
        }
    }

    /**
     * Ends current game
     */
    static void endGame()
    {
        instance.isGameOver = true;

        // Set timescale to zero(0) to freeze the game
        Scene.setTimeScale(0);
    }


    /**
     * Checks if game is over. Returns true if it is, in other case - returns false
     * @return game state is equals to 'Game over'
     */
    static boolean isGameOver() { return instance.isGameOver; }
}
