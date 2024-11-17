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
  2. Custom Alert Dialog: https://www.youtube.com/watch?v=sNyZCyisxX0
*/

package vn.edu.rmit.chordion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ChordFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chord_form);
    }

    public void navigateToHomeFromForm(View v) {
        Intent intent = new Intent(ChordFormActivity.this, MainActivity.class);

        Intent i = getIntent();
        if (i.getExtras() != null) {
            String signal = i.getExtras().get("statusCode").toString();
            if (signal.equals("300")){
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    public void navigateToListFromForm(View v) {
        Intent intent = new Intent(ChordFormActivity.this, ChordListActivity.class);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onAddBtnClicked(View v) {
        AlertDialog dialog = createDialog();
        dialog.show();
    }

    AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to create new chord?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(ChordFormActivity.this, "Waiting...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ChordFormActivity.this, "Continue to edit...", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
}