/*
  RMIT University Vietnam
  Course: COSC2657 - Android Development
  Semester: 2024C
  Assessment: Assignment 1
  Author: Nguyen Anh Duy
  ID: s3878141
  Created  date: 16/11/2024
  Last modified: 17/11/2024
  Acknowledgement (Reference):
  1. Navigation with Intents: W2 Tutorial Lab - Code Example
  2. Reading JSON file and display with RecyclerView: https://www.youtube.com/watch?v=29jY8pCTWq8
  3. Custom SearchView with RecyclerView: https://www.youtube.com/watch?v=tQ7V7iBg5zE
*/

package vn.edu.rmit.chordion;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ChordListActivity extends AppCompatActivity {
    private ArrayList<Chord> chords;
    RecyclerView recyclerView;

    ChordsAdapter chordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_list);

        chords = new ArrayList<>();
        getChordsData();

        SearchView searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        chordsAdapter = new ChordsAdapter(chords, ChordListActivity.this);
        recyclerView.setAdapter(chordsAdapter);

    }

    private void filterList(String text) {
        List<Chord> searchList = new ArrayList<>();
        for (Chord chord: chords) {
            if (chord.getFullName().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(chord);
            }
        }

        if (searchList.isEmpty()) {
            Toast.makeText(this, "No search result found!", Toast.LENGTH_SHORT).show();
        } else {
            chordsAdapter.setFilteredList(searchList);
        }
    }

    public void navigateToHomeFromList(View v) {
        Intent intent = new Intent(ChordListActivity.this, MainActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void navigateToFormFromList(View v) {
        Intent intent = new Intent(ChordListActivity.this, ChordFormActivity.class);
        intent.putExtra("statusCode", 300);
        startActivityForResult(intent,300);
    }

    private void getChordsData() {
        String json = loadJSONFromAssets();

        try {
            JSONObject rootJson = new JSONObject(json);
            JSONArray chordsList = rootJson.getJSONArray("chords");

            // Iterate through the array
            for (int i = 0; i < chordsList.length(); i++) {
                Chord chord = new Chord();
                JSONObject chordObject = chordsList.getJSONObject(i);


                chord.setShortName(chordObject.getString("shortName"));
                chord.setFullName(chordObject.getString("fullName"));

                String image = chordObject.getString("image");
                int imageResourceId = getResources().getIdentifier(image,"drawable", getPackageName());
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

                chord.setFavourite(chordObject.getBoolean("isFavourite"));

                // Add chord to the list
                chords.add(chord);
            }
        } catch(JSONException e) {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 300) {
            if (resultCode == RESULT_OK){
                Intent intent = new Intent(ChordListActivity.this, MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}