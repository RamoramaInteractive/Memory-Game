package com.example.fdai3744.memorygame3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WinningActivity extends AppCompatActivity {

    private int theme;
    private int backgroundButton;
    private int amountOfCards;
    private int numberOfBacksideColor;
    private Button again;
    private Button toMenu;

    //private String path = "daten.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);

        theme = settings.getInt("THEME", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning);

        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red);
        again = (Button) findViewById(R.id.againButton);
        again.setBackgroundResource(backgroundButton);
        toMenu = (Button) findViewById(R.id.backToMainMenu);
        toMenu.setBackgroundResource(backgroundButton);

        amountOfCards = settings.getInt("CARDAMOUNT", 4);
        numberOfBacksideColor = settings.getInt("BACKCARDVALUE", 0);

        /*
        File f = new File (path);
        if(!f.exists() && !f.isDirectory()) {
            number = Integer.parseInt(laden());
        }*/

        again.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                        again.setBackgroundResource(R.drawable.button_highlight);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    again.setBackgroundResource(backgroundButton);
                    playAgain(v);
                }
                return true;
            }
        });

        toMenu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    toMenu.setBackgroundResource(R.drawable.button_highlight);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    toMenu.setBackgroundResource(backgroundButton);
                    toMenu(v);
                }
                return true;
            }
        });
    }

    public void playAgain(View view)
    {
        Intent intent = new Intent(WinningActivity.this, GameActivity.class);
        int[] am = getAmounts(amountOfCards);
        intent.putExtra("ROWS", am[0]);
        intent.putExtra("COLUMS", am[1]);
        intent.putExtra("BACKSIDE", getResourceBackcard(numberOfBacksideColor));
        startActivity(intent);
        finish();
    }

    public void toMenu(View view)
    {
        Intent intent = new Intent(WinningActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public int[] getAmounts(int n)
    {
        int[] amountArray = new int[2];

        switch (n)
        {
            case 4:
                amountArray[0] = 2;
                amountArray[1] = 2;
                break;
            case 6:
                amountArray[0] = 2;
                amountArray[1] = 3;
                break;
            case 8:
                amountArray[0] = 2;
                amountArray[1] = 4;
                break;
            case 10:
                amountArray[0] = 5;
                amountArray[1] = 2;
                break;
            case 12:
                amountArray[0] = 4;
                amountArray[1] = 3;
                break;
            case 16:
                amountArray[0] = 4;
                amountArray[1] = 4;
                break;
            case 20:
                amountArray[0] = 5;
                amountArray[1] = 4;
                break;
            default:
                amountArray[0] = 0;
                amountArray[1] = 0;
                break;
        }

        return amountArray;

    }

    public int getResourceBackcard(int n)
    {
        int r;

        switch (n)
        {
            case 0:
                r = R.drawable.backside_red;
                break;
            case 1:
                r = R.drawable.backside_blue;
                break;
            case 2:
                r = R.drawable.backside_green;
                break;
            case 3:
                r = R.drawable.backside_gray;
                break;
            default:
                r = -1;
                break;
        }

        return r;
    }

    /*
    public String laden()
    {
        //lesen
        int ausgabe;
        StringBuffer strInhalt = new StringBuffer("");
        try {
            FileInputStream in = openFileInput(path);

            while( (ausgabe = in.read()) != -1)
                strInhalt.append((char)ausgabe);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strInhalt.toString();
    }
*/
}
