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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.util.ArrayList;

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
            }
        });

        // need to run the game in this case as well
        alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                playGame();  // need to always run the game
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        // End of Tutorialspoint
    }

    private void playGame(){
        final Button onePlayerButton = (Button) findViewById(R.id.onePlayer_clickMe);
        final Game game = new Game();
        int delay = game.start();
        modifyButton(onePlayerButton, 0, "Wait...", Color.LTGRAY);
        final Handler clickMeHandler = modifyButton(onePlayerButton, delay, "CLICK ME", Color.GREEN);

        onePlayerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // is this the second time playing?
                if (onePlayerButton.getText().equals("Play Again?")) {
                    playGame();
                }
                // after user input stop game
                try {
                    ArrayList<Player> players = getPlayers("player.sav");
                    int yourTime = game.stop(players.get(0));
                    if (yourTime < 0) { // additional button presses shouldn't count
                        return;
                    }
                    // Oct 2 2015, http://examples.javacodegeeks.com/core-java/lang/string/java-string-format-example/
                    String message = String.format("Your time was %d ms.", yourTime);
                    Toast.makeText(OnePlayerActivity.this, message, Toast.LENGTH_LONG).show();
                    savePlayer(players, "player.sav");
                    modifyButton(onePlayerButton, 0, "Play Again?", Color.GREEN);
                } catch (InvalidKeyException e) {
                    Toast.makeText(OnePlayerActivity.this, "Too Early!", Toast.LENGTH_LONG).show();
                    // Oct 3 2015, Raghunandan, http://stackoverflow.com/questions/22718951/stop-handler-postdelay
                    clickMeHandler.removeCallbacksAndMessages(null);
                    game.reset(); // dont want this to be recorded
                    modifyButton(onePlayerButton, 0, "Play Again?", Color.RED);
                }
            }
        });
    }

    // Oct 2 2015, Kevin Cruijssen, http://stackoverflow.com/questions/24928589/android-make-button-change-color-after-a-specified-time
    private Handler modifyButton(final Button b, int delay, final String text, final int color){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Oct 1 2015, Hamzeh Soboh, http://stackoverflow.com/questions/12615720/setbackgroundcolor-in-android
                b.setBackgroundColor(color);
                b.setText(text);
            }
        }, delay);
        return handler;
    }
    // end of Kevin Cruijssen

    private ArrayList<Player> getPlayers(String fileName){
        RecordManager recMan = new RecordManager();
        FileInputStream fis = null;
        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < 4; i++){   // I want the default ret val to be 4 players
            players.add(i, new Player());
        }
        try {
            fis = openFileInput(fileName);
            players = recMan.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return players;
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

}


