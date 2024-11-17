/*
  RMIT University Vietnam
  Course: COSC2657 - Android Development
  Semester: 2024C
  Assessment: Assignment 1
  Author: Nguyen Anh Duy
  ID: s3878141
  Created  date: 09/11/2024
  Last modified: 17/11/2024
  Acknowledgement (Reference):
  1. Navigation with Intents: W2 Tutorial Lab - Code Example
  2. Create animated splash screen with anim folder: https://www.youtube.com/watch?v=Jv9Plup2zZA
*/

package vn.edu.rmit.chordion;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private static int ANIMATION_TIME_OUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize views
        ImageView noteIcon = findViewById(R.id.noteIcon);
        ImageView logo = findViewById(R.id.logoSmall);

        // Load animations: rotate and fadeIn
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        new Handler().postDelayed(() -> {
            // Start the rotation animation on the logo
            noteIcon.startAnimation(rotateAnimation);
        }, 100);

        // After 2 seconds, make the logo appear
        new Handler().postDelayed(() -> {
            logo.setVisibility(View.VISIBLE);
            logo.startAnimation(fadeInAnimation);
        }, 2000);

        // After a total of 5 seconds -> navigate to welcome screen
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        },  ANIMATION_TIME_OUT); // duration of splash screen: 5 seconds
    }
}