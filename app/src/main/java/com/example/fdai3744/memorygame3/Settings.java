package com.example.fdai3744.memorygame3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ulf Grösch on 17.07.2017.
 */

public class Settings extends AppCompatActivity {


    private int theme;
    private int backgroundButton;
    private int cardBacksideValue = 0;
    private int arrow_right;
    private int arrow_right_transparency;
    private int arrow_left;
    private int arrow_left_transparency;
    //private String path = "daten.txt";

    private Button arrowLeft;
    private ImageView choosenBackside;
    private Button arrowRight;

    private Button setSettings;
    private Button cameraButton;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);

        theme = settings.getInt("THEME", R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setTheme(theme);
        setContentView(R.layout.activity_settings);

        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red);
        arrow_right = settings.getInt("ARROWRIGHT", R.drawable.arrow_right_red);
        arrow_right_transparency = settings.getInt("ARROWRIGHT_TRA", R.drawable.arrow_right_red_transparency);
        arrow_left = settings.getInt("ARROWLEFT", R.drawable.arrow_left_red);
        arrow_left_transparency = settings.getInt("ARROWLEFT_TRA", R.drawable.arrow_left_red_transparency);

        choosenBackside = (ImageView) findViewById(R.id.backsides);

        arrowLeft = (Button) findViewById(R.id.leftArrow);
        arrowRight = (Button) findViewById(R.id.RightArrow);
        setSettings = (Button) findViewById(R.id.setSettings);
        setSettings.setBackgroundResource(backgroundButton);
        cameraButton = (Button) findViewById(R.id.cameraButton);
        cameraButton.setBackgroundResource(backgroundButton);
        back = (Button) findViewById(R.id.back);
        back.setBackgroundResource(backgroundButton);

        cardBacksideValue = settings.getInt("BACKCARDVALUE", 0);
        initButtonColor(cardBacksideValue);

        setSettings.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    setSettings.setBackgroundResource(R.drawable.button_highlight);
                    setSettings(v);

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    setSettings.setBackgroundResource(backgroundButton);
                }

                return true;
            }
        });

        arrowLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    if (cardBacksideValue != 0) {
                        arrowLeft.setBackgroundResource(R.drawable.arrow_left_highlight);
                        linksButton(v);
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    if (cardBacksideValue != 0) {
                        arrowLeft.setBackgroundResource(arrow_left);
                    }
                }

                return true;
            }
        });

        arrowRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    if (cardBacksideValue != 3) {
                        arrowRight.setBackgroundResource(R.drawable.arrow_right_highlight);
                        rechtsButton(v);
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    if (cardBacksideValue != 3) {
                        arrowRight.setBackgroundResource(arrow_right);
                    }
                }

                return true;
            }
        });

        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    back.setBackgroundResource(R.drawable.button_highlight);
                    goBacktoMenu(v);

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    back.setBackgroundResource(backgroundButton);
                }

                return true;
            }
        });

        cameraButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    cameraButton.setBackgroundResource(R.drawable.button_highlight);
                    toCamera(v);

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    cameraButton.setBackgroundResource(backgroundButton);
                }

                return true;
            }
        });

        /*
        File f = new File (path);

        if (!f.exists() && !f.isDirectory())
        {
            z = Integer.parseInt(laden());
        } */

    }

    public void linksButton(View v)
    {
        switch (cardBacksideValue)
        {
            case 1:
                choosenBackside.setImageResource(R.drawable.backside_red);
                arrowLeft.setBackgroundResource(arrow_left_transparency);
                cardBacksideValue--;
                break;
            case 2:
                choosenBackside.setImageResource(R.drawable.backside_blue);
                cardBacksideValue--;
                break;
            case 3:
                choosenBackside.setImageResource(R.drawable.backside_green);
                arrowRight.setBackgroundResource(arrow_right);
                cardBacksideValue--;
                break;
            default:
                break;
        }
    }

    public void rechtsButton(View v)
    {
        switch (cardBacksideValue)
        {
            case 0:
                choosenBackside.setImageResource(R.drawable.backside_blue);
                arrowLeft.setBackgroundResource(arrow_left);
                cardBacksideValue++;
                break;
            case 1:
                choosenBackside.setImageResource(R.drawable.backside_green);
                cardBacksideValue++;
                break;
            case 2:
                choosenBackside.setImageResource(R.drawable.backside_gray);
                arrowRight.setBackgroundResource(arrow_right_transparency);
                cardBacksideValue++;
                break;
            default:
                break;
        }
    }

    public void setSettings(View v){
        /*
        try {
            FileOutputStream datei = openFileOutput(path, Context.MODE_PRIVATE);

            String meinText=""+z;
            datei.write(meinText.getBytes());
            datei.close();
            Toast.makeText(this, "Einstellung gespeichert!", Toast.LENGTH_LONG).show();
        }
        catch (IOException ex){
            Log.d("MeineApp", ex.getMessage());
        }*/

        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("BACKCARDVALUE", cardBacksideValue);
        editor.putInt("THEME", getTheme(cardBacksideValue));
        editor.putInt("BUTTONCOLOR", getColorButton(cardBacksideValue)[0]);
        editor.putInt("ARROWRIGHT", getColorButton(cardBacksideValue)[1]);
        editor.putInt("ARROWRIGHT_TRA", getColorButton(cardBacksideValue)[2]);
        editor.putInt("ARROWLEFT", getColorButton(cardBacksideValue)[3]);
        editor.putInt("ARROWLEFT_TRA", getColorButton(cardBacksideValue)[4]);
        editor.putInt("FOUR", getColorButton(cardBacksideValue)[5]);
        editor.putInt("SIX", getColorButton(cardBacksideValue)[6]);
        editor.putInt("EIGHT", getColorButton(cardBacksideValue)[7]);
        editor.putInt("TEN", getColorButton(cardBacksideValue)[8]);
        editor.putInt("TWELVE", getColorButton(cardBacksideValue)[9]);
        editor.putInt("SIXTEEN", getColorButton(cardBacksideValue)[10]);
        editor.putInt("TWENTY", getColorButton(cardBacksideValue)[11]);
        editor.apply();
        Toast.makeText(this, "Einstellung gespeichert!", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void toCamera(View view)
    {
        Intent intent = new Intent(Settings.this, CameraActivity.class);
        finish();
        startActivity(intent);
    }

    public void goBacktoMenu(View view)
    {
        Intent in = new Intent(this, MainActivity.class);
        this.startActivity(in);
        finish();

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

    public int getTheme(int n)
    {
        int r = -1;

        switch (n)
        {
            case 0:
                r = R.style.AppTheme;
                break;
            case 1://blue
                r = R.style.BlueAppTheme;
                break;
            case 2://green
                r = R.style.GreenAppTheme;
                break;
            case 3://gray
                r = R.style.GrayAppTheme;
                break;
            default:
                break;
        }

        return r;
    }

    public int[] getColorButton(int n)
    {
        int[] r = new int[12];

        switch (n) {
            case 0:
                r[0] = R.drawable.button_red;
                r[1] = R.drawable.arrow_right_red;
                r[2] = R.drawable.arrow_right_red_transparency;
                r[3] = R.drawable.arrow_left_red;
                r[4] = R.drawable.arrow_left_red_transparency;
                r[5] = R.drawable.four_red;
                r[6] = R.drawable.six_red;
                r[7] = R.drawable.eight_red;
                r[8] = R.drawable.ten_red;
                r[9] = R.drawable.twelve_red;
                r[10] = R.drawable.sixteen_red;
                r[11] = R.drawable.twenty_red;
                break;
            case 1://blue
                r[0] = R.drawable.button_blue;
                r[1] = R.drawable.arrow_right_blue;
                r[2] = R.drawable.arrow_right_blue_transparency;
                r[3] = R.drawable.arrow_left_blue;
                r[4] = R.drawable.arrow_left_blue_transparency;
                r[5] = R.drawable.four_blue;
                r[6] = R.drawable.six_blue;
                r[7] = R.drawable.eight_blue;
                r[8] = R.drawable.ten_blue;
                r[9] = R.drawable.twelve_blue;
                r[10] = R.drawable.sixteen_blue;
                r[11] = R.drawable.twenty_blue;
                break;
            case 2://green
                r[0] = R.drawable.button_green;
                r[1] = R.drawable.arrow_right_green;
                r[2] = R.drawable.arrow_right_green_transparency;
                r[3] = R.drawable.arrow_left_green;
                r[4] = R.drawable.arrow_left_green_transparency;
                r[5] = R.drawable.four_green;
                r[6] = R.drawable.six_green;
                r[7] = R.drawable.eight_green;
                r[8] = R.drawable.ten_green;
                r[9] = R.drawable.twelve_green;
                r[10] = R.drawable.sixteen_green;
                r[11] = R.drawable.twenty_green;
                break;
            case 3://gray
                r[0] = R.drawable.button_gray;
                r[1] = R.drawable.arrow_right_gray;
                r[2] = R.drawable.arrow_right_gray_transparency;
                r[3] = R.drawable.arrow_left_gray;
                r[4] = R.drawable.arrow_left_gray_transparency;
                r[5] = R.drawable.four_gray;
                r[6] = R.drawable.six_gray;
                r[7] = R.drawable.eight_gray;
                r[8] = R.drawable.ten_gray;
                r[9] = R.drawable.twelve_gray;
                r[10] = R.drawable.sixteen_gray;
                r[11] = R.drawable.twenty_gray;
                break;
            default:
                break;
        }
        return r;
    }

    public void initButtonColor(int n)
    {
        switch (n)
        {
            case 0:
                choosenBackside.setImageResource(R.drawable.backside_red);
                arrowLeft.setBackgroundResource(arrow_left_transparency);
                arrowRight.setBackgroundResource(arrow_right);
                break;
            case 1:
                choosenBackside.setImageResource(R.drawable.backside_blue);
                arrowLeft.setBackgroundResource(arrow_left);
                arrowRight.setBackgroundResource(arrow_right);
                break;
            case 2:
                choosenBackside.setImageResource(R.drawable.backside_green);
                arrowLeft.setBackgroundResource(arrow_left);
                arrowRight.setBackgroundResource(arrow_right);
                break;
            case 3:
                choosenBackside.setImageResource(R.drawable.backside_gray);
                arrowLeft.setBackgroundResource(arrow_left);
                arrowRight.setBackgroundResource(arrow_right_transparency);
                break;
            default:
                break;
        }
    }
}

