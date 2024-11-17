/*
  RMIT University Vietnam
  Course: COSC2657 - Android Development
  Semester: 2024C
  Assessment: Assignment 1
  Author: Nguyen Anh Duy
  ID: s3878141
  Created  date: 08/11/2024
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
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    /**
     * This function helps navigate from Welcome Screen -> Home Screen
     * @param v
     */
    public void navigateToHome(View v) {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * This function close the application
     */
    public void quit(View v) {
        AlertDialog dialog = createDialog();
        dialog.show();
    }

    AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to quit the application?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(WelcomeActivity.this, "See you again!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(WelcomeActivity.this, "Thanks for staying!", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
}