package com.nettapper.comp301assn1;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class FourPlayerActivity extends AppCompatActivity {
    Buzzer buz;
    ArrayList<Player> players;
    Integer gameid = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_player);
    }

    @Override
    protected void onStart(){
        super.onStart();
        buz = new Buzzer();
        buz.start();
        RecordManager recMan = new RecordManager();
        try {
            players = recMan.load(openFileInput("player.sav"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button one = (Button) findViewById(R.id.fourPlayer_PlayerOne);
        Button two = (Button) findViewById(R.id.fourPlayer_PlayerTwo);
        Button three = (Button) findViewById(R.id.fourPlayer_PlayerThree);
        Button four = (Button) findViewById(R.id.fourPlayer_PlayerFour);


        one.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (buz.stop(players.get(0), gameid)) {
                    displayWinner("Player 1 Won");
                    savePlayer(players, "player.sav");
                }
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (buz.stop(players.get(1), gameid)) {
                    displayWinner("Player 2 Won");
                    savePlayer(players, "player.sav");
                }
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (buz.stop(players.get(2), gameid)) {
                    displayWinner("Player 3 Won");
                    savePlayer(players, "player.sav");
                }
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (buz.stop(players.get(3), gameid)) {
                    displayWinner("Player 4 Won");
                    savePlayer(players, "player.sav");
                }
            }
        });
    }

    private void savePlayer(ArrayList<Player> players, String fileName) {
        RecordManager recMan = new RecordManager();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        recMan.save(players, fos);
    }

    private void displayWinner(String message) {
        // Oct 1 2015, http://www.tutorialspoint.com/android/android_alert_dialoges.htm
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                onStart();
            }
        });

        alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                onStart();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        // End of Tutorialspoint
    }

}
