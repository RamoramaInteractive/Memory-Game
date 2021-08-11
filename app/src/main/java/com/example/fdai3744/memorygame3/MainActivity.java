package com.example.fdai3744.memorygame3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.core.app.ActivityCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int backgroundButton;
    private Button toPlayMenu;
    private Button toSettingsMenu;
    private Button quit;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);

        int theme = settings.getInt("THEME", R.style.AppTheme);
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red);

        toPlayMenu = findViewById(R.id.toPlayMenu);
        toPlayMenu.setBackgroundResource(backgroundButton);
        toSettingsMenu = findViewById(R.id.toSettingsMenu);
        toSettingsMenu.setBackgroundResource(backgroundButton);
        quit = findViewById(R.id.quit);
        quit.setBackgroundResource(backgroundButton);

        toPlayMenu.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // change color
                toPlayMenu.setBackgroundResource(R.drawable.button_highlight);
                toPlayMenu(v);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                // set to normal color
                toPlayMenu.setBackgroundResource(backgroundButton);
            }
            return true;
        });

        toSettingsMenu.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // change color
                toSettingsMenu.setBackgroundResource(R.drawable.button_highlight);
                toSettingsMenu(v);

            }
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                // set to normal color
                toSettingsMenu.setBackgroundResource(backgroundButton);
            }

            return true;
        });

        quit.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                // change color
                quit.setBackgroundResource(R.drawable.button_highlight);
            }
            else if (event.getAction() == MotionEvent.ACTION_UP) {
                // set to normal color
                quit.setBackgroundResource(backgroundButton);
                quit(v);
            }
            return true;
        });
    }

    public void toPlayMenu(View v)
    {
        Intent a = new Intent(this, FirstMenu.class);
        this.startActivity(a);
        finish();
    }

    public void toSettingsMenu(View v)
    {
        Intent in = new Intent(this, Settings.class);
        this.startActivity(in);
        finish();
    }

    public void quit(View v)
    {
        finish();
    }
}
