package com.example.ryanfarrell362.onitama;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
    }

    public void game (View view)
    {
        Intent goToGame = new Intent (this, GameboardActivity.class);
        startActivity (goToGame);
    }

    public void settings (View view)
    {
        Intent goToSettings = new Intent (this, SettingsActivity.class);
        startActivity (goToSettings);
    }

    public void info (View view)
    {
        Intent goToInfo = new Intent (this, InfoActivity.class);
        startActivity (goToInfo);
    }

    public void about (View view)
    {
        Intent goToAbout = new Intent (this, AboutActivity.class);
        startActivity (goToAbout);
    }
}
