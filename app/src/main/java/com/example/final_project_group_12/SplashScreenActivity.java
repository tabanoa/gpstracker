package com.example.final_project_group_12;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import gr.net.maroulis.library.EasySplashScreen;

// The splash screen utilizes a dependancy located under Gradle Scripts --> build.gradle (Module: app) line 26
// Remove the action bar when splash screen is loading through res --> values --> styles.xml line 4 to NoActionBar

public class SplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Create instance of splash screen along with its attributes
        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)    // live of splash screen (miliseconds) --> 3 secs
                .withBackgroundColor(Color.parseColor("#ffffff"))       // Green background
                .withAfterLogoText("Position Tracker")
                .withLogo(R.drawable.logo);

        // Change text colour
        config.getAfterLogoTextView().setTextColor(Color.RED);

        // Create splash screen with above settings and set layout
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
