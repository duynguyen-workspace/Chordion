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
  2. Change App Icon: https://www.youtube.com/watch?v=m6qBOTjZ4Lw
*/

package vn.edu.rmit.chordion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateToListFromHome(View v) {
        Intent intent = new Intent(MainActivity.this, ChordListActivity.class);
        intent.putExtra("statusCode", 100);
        startActivityForResult(intent, 100);
    }

    public void navigateToFormFromHome(View v) {
        Intent intent = new Intent(MainActivity.this, ChordFormActivity.class);
        startActivity(intent);
    }


}