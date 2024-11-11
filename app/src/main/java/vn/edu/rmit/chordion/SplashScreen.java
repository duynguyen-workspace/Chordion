/***
 * Reference: https://www.youtube.com/watch?v=Jv9Plup2zZA
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

        // Load animations
        Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        new Handler().postDelayed(() -> {
            // Start the rotation animation on the logo
            noteIcon.startAnimation(rotateAnimation);
        }, 100);

        // After 5 seconds, transition to the next activity
        new Handler().postDelayed(() -> {
            // Make the text visible and start fade-in animation on the text
            logo.setVisibility(View.VISIBLE);
            logo.startAnimation(fadeInAnimation);
        }, 2000); // Total duration of splash screen: 5 seconds

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, WelcomeActivity.class);
            startActivity(intent);
            finish();
        },  ANIMATION_TIME_OUT);
    }
}