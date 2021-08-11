package com.example.fdai3744.memorygame3;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.sip.SipAudioCall;
import androidx.core.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.os.Handler;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by fdai3744 on 13.07.2017.
 *
 * Anmerkung: Man kann von der Klasse ImageView nicht erben, da sonst die Nachricht angezeigt wird,
 * dass man nur von android.support.v7.widget.AppCompatImageView, anstelle von ImageView,
 * erben kann.
 */

public class MemoryCard implements View.OnClickListener {

    private final LinearLayout.LayoutParams PARAMS =
            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

    private static final int SECONDS = 500;
    private static int cPairs = 0;

    private ImageView image;
    private int id;
    private int nr;
    private boolean clicked = false;
    private Bitmap cardBack;
    private Bitmap cardFront;

    private static ArrayList<MemoryCard> clickedCards = new ArrayList<MemoryCard>();

    /*
     * Die Klasse MemoryCard besitzt vier überladene Konstruktoren, um sowohl ResourceIDs, als auch
     * Bitmaps für die Memory-Karten nutzen zu können.
     */

    public MemoryCard(Context context, int id, int nr, int cardFront, final Bitmap cardBack)
    {
        this.id = id;
        this.nr = nr;
        this.cardBack = cardBack;

        this.cardFront =  BitmapFactory.decodeResource(context.getResources(), cardFront);
        this.image = new ImageView(context);
        this.image.setLayoutParams(PARAMS);
        this.image.setImageBitmap(this.cardBack);
        this.image.setOnClickListener(this);
    }

    public MemoryCard(Context context, int id, int nr, final Bitmap cardFront, int cardBack)
    {
        this.id = id;
        this.nr = nr;
        this.cardBack = BitmapFactory.decodeResource(context.getResources(), cardBack);

        this.cardFront = cardFront;
        this.image = new ImageView(context);
        this.image.setLayoutParams(PARAMS);
        this.image.setImageBitmap(this.cardBack);
        this.image.setOnClickListener(this);
    }

    public MemoryCard(Context context, int id, int nr, final Bitmap cardFront,  final Bitmap cardBack)
    {
        this.id = id;
        this.nr = nr;
        this.cardBack = cardBack;

        this.cardFront = cardFront;
        this.image = new ImageView(context);
        this.image.setLayoutParams(PARAMS);
        this.image.setImageBitmap(this.cardBack);
        this.image.setOnClickListener(this);
    }

    public MemoryCard(Context context, int id, int nr, int cardFront, int cardBack)
    {
        this.id = id;
        this.nr = nr;
        this.cardBack = BitmapFactory.decodeResource(context.getResources(), cardBack);

        this.cardFront = BitmapFactory.decodeResource(context.getResources(), cardFront);
        this.image = new ImageView(context);
        this.image.setLayoutParams(PARAMS);
        this.image.setImageBitmap(this.cardBack);
        this.image.setOnClickListener(this);
    }

    public ImageView getImage()
    {
        return this.image;
    }

    public int getNumber()
    {
        return this.nr;
    }

    public void flipCardtoFront()
    {

        ObjectAnimator flip = ObjectAnimator.ofFloat(this.image, "rotationY", 180f, 0f);
        image.setImageBitmap(cardFront);
        this.clicked = true;
    }

    public void flipCardtoBack()
    {
        image.setImageBitmap(cardBack);
        this.clicked = false;
    }

    public static int getcPairs()
    {
        return cPairs;
    }

    public static void deletecPairs()
    {
        cPairs = 0;
    }

    @Override
    public void onClick(View v) {
        if (!this.clicked && this.clickedCards.size() < 2) {
            flipCardtoFront();
            clickedCards.add(this);

            if (this.clickedCards.size() == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (clickedCards.get(0).getNumber() == clickedCards.get(1).getNumber()) {
                            ++cPairs;
                        } else {
                            clickedCards.get(0).flipCardtoBack();
                            clickedCards.get(1).flipCardtoBack();
                        }

                        clickedCards.clear();
                    }
                }, SECONDS);
            }
        }
    }
}
