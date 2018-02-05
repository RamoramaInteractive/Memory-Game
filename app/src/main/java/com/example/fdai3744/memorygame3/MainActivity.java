package com.example.fdai3744.memorygame3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private int theme;
    private int backgroundButton;
    private Button toPlayMenu;
    private Button toSettingsMenu;
    private Button quit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);

        theme = settings.getInt("THEME", R.style.AppTheme);
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red);

        toPlayMenu = (Button) findViewById(R.id.toPlayMenu);
        toPlayMenu.setBackgroundResource(backgroundButton);
        toSettingsMenu = (Button) findViewById(R.id.toSettingsMenu);
        toSettingsMenu.setBackgroundResource(backgroundButton);
        quit = (Button) findViewById(R.id.quit);
        quit.setBackgroundResource(backgroundButton);

        toPlayMenu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
            }
        });

        toSettingsMenu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
            }
        });

        quit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
            }
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
