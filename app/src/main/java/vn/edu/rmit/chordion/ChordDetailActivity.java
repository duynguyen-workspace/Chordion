package vn.edu.rmit.chordion;

import android.content.Intent;
import android.graphics.Typeface;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChordDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_detail);

        TextView chordFullNameTxtView = findViewById(R.id.chordFullName);
        TextView chordDifficultyTxtView = findViewById(R.id.chordDifficulty);
        TextView chordDescriptionTxtView = findViewById(R.id.chordDescription);
        TextView chordRatingView = findViewById(R.id.chordRating);
        ImageView chordInstrumentImgView = findViewById(R.id.chordInstrumentImg);
        LinearLayout genresLayout = findViewById(R.id.chordGenres);
        ImageView chordImage = findViewById(R.id.chordImg);


        // Get data extras from intent
        Intent intent = getIntent();

        if (intent.getExtras() != null) {
            String fullName = intent.getStringExtra("fullName");
            String instrument = intent.getStringExtra("instrument");
            int rating = intent.getIntExtra("rating", 0); // Default value 0
            int imageId = intent.getIntExtra("imageId", 0); // Default value 0
            String difficulty = intent.getStringExtra("difficulty");
            String description = intent.getStringExtra("description");

            ArrayList<String> genres = intent.getStringArrayListExtra("genres");

            int instrumentImg = getThumbnailResource(instrument);

            chordFullNameTxtView.setText(fullName);
            chordDifficultyTxtView.setText(difficulty);
            chordDescriptionTxtView.setText(description);
            chordRatingView.setText(String.valueOf(rating));

            genresLayout.removeAllViews(); // Clear any previous views
            for (String genre : genres) {
                TextView genreTextView = new TextView(this);
                genreTextView.setText(genre);
                genreTextView.setBackgroundResource(R.drawable.genre_background);

                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                marginLayoutParams.setMarginEnd(12);
                genreTextView.setLayoutParams(marginLayoutParams);
                genreTextView.setPadding(30,0, 30,5);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Typeface typeface = getResources().getFont(R.font.source_sans_pro);
                    genreTextView.setTypeface(typeface);
                }

                genresLayout.addView(genreTextView);
            }

            chordInstrumentImgView.setImageResource(instrumentImg);
            chordImage.setImageResource(imageId);

        }

    }

    private int getThumbnailResource(String instrument) {
        switch (instrument.toLowerCase()) {
            case "piano":
                return R.drawable.piano;
            case "flute":
                return R.drawable.flute;
            case "guitar":
                return R.drawable.violin;
            case "ukulele":
                return R.drawable.guitar;
            case "keyboard":
                return R.drawable.keyboard;
            default:
                return R.drawable.ic_launcher_background; // Use a default image if the instrument doesn't match
        }
    }

    public void navigateBack(View v) {
        Intent intent = new Intent(ChordDetailActivity.this, ChordListActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void playSound(View v) {
        int mediaFileId = 0;
        Intent intent = getIntent();
        String instrument = intent.getStringExtra("instrument");

        switch (instrument.toLowerCase()) {
            case "piano":
                mediaFileId = R.raw.piano;
                break;
            case "flute":
                mediaFileId = R.raw.flute;
                break;
            case "guitar":
                mediaFileId = R.raw.violin;
                break;
            case "ukulele":
                mediaFileId = R.raw.guitar;
                break;
        }

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, mediaFileId);
        mediaPlayer.start();

    }

}