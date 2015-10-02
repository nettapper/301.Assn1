package com.nettapper.comp301assn1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class OnePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);
        runTutorial();
    }

    private void runTutorial(){
        // Oct 1 2015, http://www.tutorialspoint.com/android/android_alert_dialoges.htm
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("After clicking OK the game will start. Try to click the button on screen as quickly as possible when it changes color!");

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                playGame();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        // End of Tutorialspoint
    }

    private void playGame(){
        final Button onePlayerButton = (Button) findViewById(R.id.onePlayer_clickMe);
        // Oct 1 2015, Hamzeh Soboh, http://stackoverflow.com/questions/12615720/setbackgroundcolor-in-android
        onePlayerButton.setBackgroundColor(Color.BLUE);
        final Game game = new Game();
        int delay = game.start();
        changeColor(onePlayerButton, delay);

        onePlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // after user input stop game
                try {
                    int yourTime = game.stop(new Player(""));
                    String message = String.format("Your time was %d ms.", yourTime);
                    Toast.makeText(OnePlayerActivity.this, message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(OnePlayerActivity.this, "Too Early!", Toast.LENGTH_LONG).show();
                    onePlayerButton.setBackgroundColor(Color.RED);
                    runTutorial();
                }
            }
        });
    }
    private void changeColor(final Button b, int delay){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                b.setBackgroundColor(Color.GREEN);
            }
        }, delay);
    }
}


