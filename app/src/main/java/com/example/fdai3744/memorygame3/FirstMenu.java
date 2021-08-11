package com.example.fdai3744.memorygame3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ulf Grösch on 17.07.2017.
 */

public class FirstMenu extends AppCompatActivity {

   // private String path = "daten.txt";
   // private int n;
    private int backgroundButton = R.drawable.button_red;
    private int theme;

    private int arrow_right;
    private int arrow_right_transparency;
    private int arrow_left;
    private int arrow_left_transparency;

    private int four;
    private int six;
    private int eight;
    private int ten;
    private int twelve;
    private int sixteen;
    private int twenty;

    private Button rightArrow;
    private Button leftArrow;
    private Button ok;
    private Button back;
    private int cardBacksideAmount;
    private ImageView cardNumberImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);

        theme = settings.getInt("THEME", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_menu);

        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red);
        arrow_right = settings.getInt("ARROWRIGHT", R.drawable.arrow_right_red);
        arrow_right_transparency = settings.getInt("ARROWRIGHT_TRA", R.drawable.arrow_right_red_transparency);
        arrow_left = settings.getInt("ARROWLEFT", R.drawable.arrow_left_red);
        arrow_left_transparency = settings.getInt("ARROWLEFT_TRA", R.drawable.arrow_left_red_transparency);

        four = settings.getInt("FOUR", R.drawable.four_red);
        six = settings.getInt("SIX", R.drawable.six_red);
        eight = settings.getInt("EIGHT", R.drawable.eight_red);
        ten = settings.getInt("TEN", R.drawable.ten_red);
        twelve = settings.getInt("TWELVE", R.drawable.twelve_red);
        sixteen = settings.getInt("SIXTEEN", R.drawable.sixteen_red);
        twenty = settings.getInt("TWENTY", R.drawable.twenty_red);

        cardBacksideAmount = settings.getInt("CARDAMOUNT", 4);

        rightArrow = (Button) findViewById(R.id.arrowRight);
        leftArrow = (Button) findViewById(R.id.arrowLeft);
        ok = (Button) findViewById(R.id.playGame);
        ok.setBackgroundResource(backgroundButton);
        back = (Button) findViewById(R.id.back);
        back.setBackgroundResource(backgroundButton);
        cardNumberImage =(ImageView)findViewById(R.id.cardAmount);
        initMenu(cardBacksideAmount);
        initCurrentCardAmountColor(cardBacksideAmount);


        leftArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    if (cardBacksideAmount !=4) {
                        leftArrow.setBackgroundResource(R.drawable.arrow_left_highlight);
                        leftArrowOnClick(v);
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    if (cardBacksideAmount != 4) {
                        leftArrow.setBackgroundResource(arrow_left);
                    }
                }

                return true;
            }
        });

        rightArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    if (cardBacksideAmount != 20) {
                        rightArrow.setBackgroundResource(R.drawable.arrow_right_highlight);
                        rightArrowOnClick(v);
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    if (cardBacksideAmount != 20) {
                        rightArrow.setBackgroundResource(arrow_right);
                    }
                }

                return true;
            }
        });

        ok.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    ok.setBackgroundResource(R.drawable.button_highlight);
                    playGame(v);

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    ok.setBackgroundResource(backgroundButton);
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
                    back(v);
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    back.setBackgroundResource(backgroundButton);
                }

                return true;
            }
        });

    }

    /* Original methode by Ulf Grösch and modified by Ramón Wilhelm */

    public void leftArrowOnClick(View v)
    {
        switch (cardBacksideAmount)
        {
            case 6:
                cardNumberImage.setImageResource(four);
                leftArrow.setBackgroundResource(arrow_left_transparency);
                cardBacksideAmount -= 2;
                break;
            case 8:
                cardNumberImage.setImageResource(six);
                cardBacksideAmount -= 2;
                break;
            case 10:
                cardNumberImage.setImageResource(eight);
                cardBacksideAmount -= 2;
                break;
            case 12:
                cardNumberImage.setImageResource(ten);
                cardBacksideAmount -= 2;
                break;
            case 16:
                cardNumberImage.setImageResource(twelve);
                cardBacksideAmount -= 4;
                break;
            case 20:
                cardNumberImage.setImageResource(sixteen);
                rightArrow.setBackgroundResource(arrow_right);
                cardBacksideAmount -= 4;
                break;
            default:
                break;
        }
    }

    /* Original methode by Ulf Grösch and modified by Ramón Wilhelm */

    public void rightArrowOnClick(View v)
    {
       switch (cardBacksideAmount)
       {
           case 4:
               cardNumberImage.setImageResource(six);
               leftArrow.setBackgroundResource(arrow_left);
               cardBacksideAmount += 2;
               break;
           case 6:
               cardNumberImage.setImageResource(eight);
               cardBacksideAmount += 2;
               break;
           case 8:
               cardNumberImage.setImageResource(ten);
               cardBacksideAmount += 2;
               break;
           case 10:
               cardNumberImage.setImageResource(twelve);
               cardBacksideAmount += 2;
               break;
           case 12:
               cardNumberImage.setImageResource(sixteen);
               cardBacksideAmount += 4;
               break;
           case 16:
               cardNumberImage.setImageResource(twenty);
               rightArrow.setBackgroundResource(arrow_right_transparency);
               cardBacksideAmount += 4;
               break;
           default:
               break;
       }
    }

    public void playGame(View v)
    {
        /*
        File f = new File (path);
        if (!f.exists() && !f.isDirectory())
        {
            n = Integer.parseInt(laden());
        }*/


        Intent i = new Intent(FirstMenu.this, GameActivity.class);

        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putInt("CARDAMOUNT", cardBacksideAmount);
        editor.apply();


        int[] amount = getAmounts(cardBacksideAmount);
        i.putExtra("ROWS", amount[0]);
        i.putExtra("COLUMS", amount[1]);
        i.putExtra("BACKSIDE", getResourceBackcard(settings.getInt("BACKCARDVALUE", 0)));
        this.startActivity(i);
        finish();

    }

    public void back(View v)
    {
        Intent in =new Intent(this,MainActivity.class);
        finish();
        this.startActivity(in);
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

    public void initCurrentCardAmountColor(int n)
    {
        switch (n)
        {
            case 4:
                leftArrow.setBackgroundResource(arrow_left_transparency);
                rightArrow.setBackgroundResource(arrow_right);
                cardNumberImage.setImageResource(four);
                break;
            case 6:
                leftArrow.setBackgroundResource(arrow_left);
                rightArrow.setBackgroundResource(arrow_right);
                cardNumberImage.setImageResource(six);
                break;
            case 8:
                leftArrow.setBackgroundResource(arrow_left);
                rightArrow.setBackgroundResource(arrow_right);
                cardNumberImage.setImageResource(eight);
                break;
            case 10:
                leftArrow.setBackgroundResource(arrow_left);
                rightArrow.setBackgroundResource(arrow_right);
                cardNumberImage.setImageResource(ten);
                break;
            case 12:
                leftArrow.setBackgroundResource(arrow_left);
                rightArrow.setBackgroundResource(arrow_right);
                cardNumberImage.setImageResource(twelve);
                break;
            case 16:
                leftArrow.setBackgroundResource(arrow_left);
                rightArrow.setBackgroundResource(arrow_right);
                cardNumberImage.setImageResource(sixteen);
                break;
            case 20:
                leftArrow.setBackgroundResource(arrow_left);
                rightArrow.setBackgroundResource(arrow_right_transparency);
                cardNumberImage.setImageResource(twenty);
                break;
            default:
                break;
        }
    }

    public void initMenu(int n)
    {
        switch (n)
        {
            case 4:
                cardNumberImage.setImageResource(R.drawable.four_red);
                leftArrow.setBackgroundResource(R.drawable.arrow_left_red_transparency);
                rightArrow.setBackgroundResource(R.drawable.arrow_right_red);
                break;
            case 6:
                cardNumberImage.setImageResource(R.drawable.six_red);
                leftArrow.setBackgroundResource(R.drawable.arrow_left_red);
                rightArrow.setBackgroundResource(R.drawable.arrow_right_red);
                break;
            case 8:
                cardNumberImage.setImageResource(R.drawable.eight_red);
                leftArrow.setBackgroundResource(R.drawable.arrow_left_red);
                rightArrow.setBackgroundResource(R.drawable.arrow_right_red);
                break;
            case 10:
                cardNumberImage.setImageResource(R.drawable.ten_red);
                leftArrow.setBackgroundResource(R.drawable.arrow_left_red);
                rightArrow.setBackgroundResource(R.drawable.arrow_right_red);
                break;
            case 12:
                cardNumberImage.setImageResource(R.drawable.twelve_red);
                leftArrow.setBackgroundResource(R.drawable.arrow_left_red);
                rightArrow.setBackgroundResource(R.drawable.arrow_right_red);
                break;
            case 16:
                cardNumberImage.setImageResource(R.drawable.sixteen_red);
                leftArrow.setBackgroundResource(R.drawable.arrow_left_red);
                rightArrow.setBackgroundResource(R.drawable.arrow_right_red);
                break;
            case 20:
                cardNumberImage.setImageResource(R.drawable.twenty_red);
                leftArrow.setBackgroundResource(R.drawable.arrow_left_red);
                rightArrow.setBackgroundResource(R.drawable.arrow_right_red_transparency);
                break;
            default:
                break;
        }
    }

    /*
    public String speichern()
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
    }*/

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

    /* This methodes were created by Ramón Wilhelm*/
}
