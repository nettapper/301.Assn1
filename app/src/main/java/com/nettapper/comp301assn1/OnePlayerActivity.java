package com.nettapper.comp301assn1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OnePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        runTutorial();
        playGame();
    }

    private void runTutorial(){
        // Oct 1 2015, http://www.tutorialspoint.com/android/android_alert_dialoges.htm
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("After clicking OK the game will start. Try to click the button on screen as quickly as possible when it changes color!");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(OnePlayerActivity.this, "Starting the game!", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        // End of Tutorialspoint
    }

    private void playGame(){
        Button onePlayerButton = (Button) findViewById(R.id.onePlayer_clickMe);
        // Oct 1 2015, Hamzeh Soboh, http://stackoverflow.com/questions/12615720/setbackgroundcolor-in-android
        onePlayerButton.setBackgroundColor(Color.BLUE);
        final Game game = new Game();
        game.start();

        // change button color
        // Oct 1 2015, Hamzeh Soboh, http://stackoverflow.com/questions/12615720/setbackgroundcolor-in-android
        onePlayerButton.setBackgroundColor(Color.GREEN);

        onePlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // after user input stop game
                game.stop(new Player(""));
                Toast.makeText(OnePlayerActivity.this, "Time has been recorded.", Toast.LENGTH_LONG).show();
            }
        });
    }
}


