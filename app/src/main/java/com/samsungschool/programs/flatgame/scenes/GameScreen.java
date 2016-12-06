package com.samsungschool.programs.flatgame.scenes;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.samsungschool.programs.flatgame.GameObject;
import com.samsungschool.programs.flatgame.R;
import com.samsungschool.programs.flatgame.Scene;
import com.samsungschool.programs.flatgame.Vector2D;
import com.samsungschool.programs.flatgame.drawing.GUI;
import com.samsungschool.programs.flatgame.gameobjects.EnergyBallSpawner;
import com.samsungschool.programs.flatgame.gameobjects.GameManager;
import com.samsungschool.programs.flatgame.gameobjects.Magnet;
import com.samsungschool.programs.flatgame.gameobjects.Player;

public class GameScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Prepare android window
        // Remove title and set it to fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        // Set content view to game screen
        setContentView(R.layout.activity_game_screen);

        // Get display data, and set GUI's Width and Height
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        GUI.Width = size.x;
        GUI.Height = size.y;
        GUI.Size = new Vector2D(GUI.Width, GUI.Height);

        // Open new scene to work with
        Scene.openEmptyScene();


        // Fill scene with object
        // * Generate first level

        // Create electrons spawners
        EnergyBallSpawner spawner = GameObject.instantiate(EnergyBallSpawner.class);
        spawner.setPosition(new Vector2D(GUI.Width - 50, GUI.Height / 2));
        spawner.setRotation((float) Math.PI);
        spawner.energyBallsSpeed = 100;

        spawner = GameObject.instantiate(EnergyBallSpawner.class);
        spawner.setPosition(new Vector2D(GUI.Width - 30, GUI.Height * 4 / 10));
        spawner.setRotation((float) Math.PI * 10 / 9);
        spawner.energyBallsSpeed = 150;

        spawner = GameObject.instantiate(EnergyBallSpawner.class);
        spawner.setPosition(new Vector2D(GUI.Width - 30, GUI.Height * 6 / 10));
        spawner.setRotation((float) Math.PI * 8 / 9);
        spawner.energyBallsSpeed = 150;


        // Create magnets
        Magnet magnet = GameObject.instantiate(Magnet.class);
        magnet.setPosition(new Vector2D(GUI.Width / 2, GUI.Height * 2 / 3));
        magnet.strength = 25;

        magnet = GameObject.instantiate(Magnet.class);
        magnet.setPosition(new Vector2D(GUI.Width / 2, GUI.Height / 3));
        magnet.strength = 25;

        // Create Player and GameManager
        Player player = GameObject.instantiate(Player.class);
        player.setPosition(new Vector2D(100, GUI.Height / 2));

        GameManager gameManager = GameObject.instantiate(GameManager.class);
        gameManager.energyBallSpawnTime = 5;
    }
}
