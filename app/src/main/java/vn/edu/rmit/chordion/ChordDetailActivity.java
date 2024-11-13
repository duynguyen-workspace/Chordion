package vn.edu.rmit.chordion;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Date;

public class ChordDetailActivity extends AppCompatActivity {
    private Chord chord = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_detail);

        TextView chordFullNameTxtView = findViewById(R.id.cardFullName);

        // Get data extras from intent
        Intent intent = getIntent();

        String shortName = intent.getStringExtra("shortName");
        String fullName = intent.getStringExtra("fullName");
        String type = intent.getStringExtra("type");
        String instrument = intent.getStringExtra("instrument");
        int rating = intent.getIntExtra("rating", 0); // Default value 0
        int imageId = intent.getIntExtra("imageId", 0); // Default value 0
        String difficulty = intent.getStringExtra("difficulty");
        String description = intent.getStringExtra("description");

        ArrayList<String> genres = intent.getStringArrayListExtra("genres");

        long createdDateMillis = intent.getLongExtra("createdDate", -1);
        Date createdDate = createdDateMillis != -1 ? new Date(createdDateMillis) : null;

        boolean isFavourite = intent.getBooleanExtra("isFavourite", false); // Default value false

        if (intent.getExtras() != null) {
            chordFullNameTxtView.setText(intent.getStringExtra("fullName"));
        }

    }
}