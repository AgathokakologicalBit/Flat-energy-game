package com.samsungschool.programs.flatgame.scenes;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.samsungschool.programs.flatgame.Assets;
import com.samsungschool.programs.flatgame.Mathf;
import com.samsungschool.programs.flatgame.R;

public class SplashScreen extends AppCompatActivity {

    private Runnable uiAnimator;

    private final long DISPLAY_TIME = 4000; // 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        final View splash_circle = findViewById(R.id.splash_circle);
        final GradientDrawable cgd = (GradientDrawable) splash_circle.getBackground();

        cgd.setColor(Assets.color_red);
        final Handler handler = new Handler();

        uiAnimator = new Runnable() {
            private long lastTime = 0;
            private long totalTime = 0;

            private long timer = 0;
            private int state = 0;

            @Override
            public void run() {
                if (lastTime == 0) lastTime = System.nanoTime() / 1000000;

                if (totalTime > DISPLAY_TIME) return;

                long currentTime = System.nanoTime() / 1000000;
                long deltaTime = currentTime - lastTime;
                lastTime = currentTime;

                totalTime += deltaTime;
                timer += deltaTime;

                if (timer >= 1000)
                {
                    timer = 0;
                    state = (state + 1) % 3;
                }

                switch (state)
                {
                    case 0:
                        cgd.setColor(Mathf.lerp(Assets.color_red, Assets.color_blue, timer / 1000f));
                        break;
                    case 1:
                        cgd.setColor(Mathf.lerp(Assets.color_blue, Assets.color_green, timer / 1000f));
                        break;
                    case 2:
                        cgd.setColor(Mathf.lerp(Assets.color_green, Assets.color_red, timer / 1000f));
                        break;
                }

                handler.post(uiAnimator);
            }
        };

        handler.post(uiAnimator);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), GameScreen.class));
                finish();
            }
        }, DISPLAY_TIME);
    }
}
