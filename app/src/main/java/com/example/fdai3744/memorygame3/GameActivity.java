package com.example.fdai3744.memorygame3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    private int MARGIN = 4;
    private int ROWS;
    private int COLUMNS;
    private LinearLayout mainlayout;
    private TextView pairs;
    private String PAIRS = "Pairs: ";
    private List<LinearLayout> layout_rows;
    private List<MemoryCard> cards;

    private ArrayList<Object> images;

    private boolean running = true;
    private final int SECONDS = 200;
    private final int END_SECONDS = 2000;
    private Runnable runnable;
    private Thread thread;
    private int theme;

    private Button back;

    private int cardback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);

        theme = settings.getInt("THEME", R.style.AppTheme);
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        MemoryCard.deletecPairs();

        back = (Button) findViewById(R.id.back);
        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent back = new Intent(GameActivity.this, FirstMenu.class);
                finish();
                startActivity(back);
                return true;
            }
        });

        ROWS = getIntent().getIntExtra("ROWS", 0);
        COLUMNS = getIntent().getIntExtra("COLUMS", 0);
        cardback = getIntent().getIntExtra("BACKSIDE", R.drawable.backside_red);

        images = new ArrayList<Object>();
        images.add(R.drawable.dog001);
        images.add(R.drawable.dog002);
        images.add(R.drawable.dog003);
        images.add(R.drawable.dog004);
        images.add(R.drawable.dog005);
        images.add(R.drawable.dog006);
        images.add(R.drawable.dog007);
        images.add(R.drawable.dog008);
        images.add(R.drawable.dog009);
        images.add(R.drawable.dog010);

        Collections.shuffle(images);

        mainlayout = (LinearLayout) findViewById(R.id.mainLayout);
        pairs = (TextView) findViewById(R.id.pairs);
        pairs.setText(PAIRS + "0");
        layout_rows = new ArrayList<LinearLayout>();
        cards = new ArrayList<MemoryCard>();
        createCards(cards, images, MARGIN, ROWS, COLUMNS, cardback);
        createLayoutRows(layout_rows, ROWS);
        addCardsToLayout(cards, layout_rows, ROWS, COLUMNS, mainlayout);

        runnable = new Runnable() {
            @Override
            public void run() {
                while (running)
                {
                    pairs.post(new Runnable() {
                        @Override
                        public void run() {
                            pairs.setText(PAIRS + MemoryCard.getcPairs());
                        }
                    });

                    try {
                        Thread.sleep(SECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (MemoryCard.getcPairs() == (ROWS * COLUMNS / 2)) {

                        pairs.post(new Runnable() {
                            @Override
                            public void run() {
                                pairs.setText(PAIRS + MemoryCard.getcPairs());
                            }
                        });

                        try {
                            Thread.sleep(END_SECONDS);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // restartGame();
                        winGame();
                    }
                }
            }
        };

        thread = new Thread(runnable);
        thread.start();
    }

    public void winGame()
    {
        running = false;
        thread.interrupt();
        Intent intent = new Intent(GameActivity.this, WinningActivity.class);
        startActivity(intent);
        finish();
    }

    public void restartGame()
    {
        running = false;
        thread.interrupt();
        Intent intent = getIntent();
        startActivity(intent);
        finish();
    }

    public void addCardsToLayout(List<MemoryCard> mCards, List<LinearLayout> l_layout,
                                 int rows, int cols, LinearLayout mLayout)
    {
        int count = 0;

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                l_layout.get(i).addView(mCards.get(count).getImage());
                count++;
            }

            mLayout.addView(l_layout.get(i));
        }
    }

    public void createLayoutRows(List<LinearLayout> l_layout, int rows)
    {
        for (int i = 0; i < rows; i++)
        {
            l_layout.add(new LinearLayout(this));
            l_layout.get(i).setOrientation(LinearLayout.HORIZONTAL);
            l_layout.get(i).setGravity(Gravity.CENTER_HORIZONTAL);
        }
    }

    public void createCards(List<MemoryCard> cards, ArrayList<Object> images, int margin,
                            int rows, int cols, int cardback)
    {
        LinearLayout.LayoutParams lp = new LinearLayout.
                LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(margin, margin, margin, margin);

        ArrayList<Object> storedImages = new ArrayList<Object>();

        for (int i = 0; i < rows * cols / 2; i++)
        {
            storedImages.add(images.get(i));
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < storedImages.size(); j++) {

                if (storedImages.get(j) instanceof Integer) {
                    cards.add(new MemoryCard(this, j + (i * storedImages.size()), j,
                            (Integer) storedImages.get(j), cardback));
                }
                else
                {
                    cards.add(new MemoryCard(this, j + (i * storedImages.size()), j,
                            (Bitmap) storedImages.get(j), cardback));
                }
                cards.get(j + (i * storedImages.size())).getImage().setLayoutParams(lp);
            }
        }

        Collections.shuffle(cards);
    }
}