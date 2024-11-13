/*
* Reference:
* 1. Reading JSON file and display with RecyclerView: https://www.youtube.com/watch?v=29jY8pCTWq8
*
* */

package vn.edu.rmit.chordion;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChordListActivity extends AppCompatActivity {
    private ArrayList<Chord> chords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_list);

        chords = new ArrayList<>();
        getChordsData();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ChordsAdapter chordsAdapter = new ChordsAdapter(chords, ChordListActivity.this);
        recyclerView.setAdapter(chordsAdapter);

    }

    private void getChordsData() {
        String json = loadJSONFromAssets();

        try {
            JSONObject rootJson = new JSONObject(json);
            JSONArray chordsList = rootJson.getJSONArray("chords");

            // Date formatter
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Iterate through the array
            for (int i = 0; i < chordsList.length(); i++) {
                Chord chord = new Chord();
                JSONObject chordObject = chordsList.getJSONObject(i);


                chord.setShortName(chordObject.getString("shortName"));
                chord.setFullName(chordObject.getString("fullName"));

                String imageID = chordObject.getString("image");
                int imageResourceId = getResources().getIdentifier(imageID,"drawable", getPackageName());
                chord.setImageId(imageResourceId);

                chord.setDescription(chordObject.getString("description"));
                chord.setRating(chordObject.getInt("rating"));
                chord.setDifficulty(chordObject.getString("difficulty"));
                chord.setType(chordObject.getString("type"));
                chord.setInstrument(chordObject.getString("instrument"));

                // Parsing genres array
                JSONArray genresList = chordObject.getJSONArray("genres");
                ArrayList<String> genres = new ArrayList<>();
                for (int j = 0; j < genresList.length(); j++) {
                    genres.add(genresList.getString(j));
                }
                chord.setGenres(genres);

                // Parsing date
                String dateStr = chordObject.getString("createdDate");
                chord.setCreatedDate(dateFormat.parse(dateStr));

                chord.setFavourite(chordObject.getBoolean("isFavourite"));

                // Add chord to the list
                chords.add(chord);
            }
        } catch(JSONException | ParseException e) {
            e.printStackTrace();
            Log.e("ChordListActivity", "Error parsing chords data", e);
        }
    }


    private String loadJSONFromAssets() {
        String json = null;

        try {
            // Load JSON file
            InputStream inputStream = getAssets().open("chords.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ChordListActivity", "Error loading JSON file", e);
        }

        return json;
    }
}