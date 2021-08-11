package com.example.fdai3744.memorygame3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/*
 *  Auf der Kamera Activity wollte ich ursprünglich als Zusatz eine Option einbauen,
 *  in der man eigene Fotos aufnehmen und auswählen kann.
 *  Das gleiche auch, wenn man nur ein Bild aus der Gallery auswählen möchte.
 *
 *  Da die Standart Images Resource IDs verwenden und die aufgenommenen Fotos Bitmaps,
 *  erschwert es diese Idee umzusetzen, wenn man mit SharedPreferences arbeitet.
 *
 *  Resources IDs lassen sich noch als Integer abspeichern und es gibt die Möglichkeit
 *  Bitmaps in Strings umzuwandeln.
 *
 *  Leider konnte die Kamera-Option nicht realisiert werden.
 *
 */

public class CameraActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 1138;

    private Button back;
    private int backgroundButton;
    private int theme;

    private ImageView image_001;
    private ImageButton camera_001;
    private ImageButton gallery_001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences settings = getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        theme = settings.getInt("THEME", R.style.AppTheme);
        setTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        image_001 = (ImageView) findViewById(R.id.image001);
        camera_001 = (ImageButton) findViewById(R.id.camera001);
        gallery_001 = (ImageButton) findViewById(R.id.gallery001);

        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red);

        back = (Button) findViewById(R.id.back);
        back.setBackgroundResource(backgroundButton);

        back.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // change color
                    back.setBackgroundResource(R.drawable.button_highlight);
                    Intent intent = new Intent(CameraActivity.this, Settings.class);
                    finish();
                    startActivity(intent);

                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // set to normal color
                    back.setBackgroundResource(backgroundButton);
                }

                return true;
            }
        });
    }
}
