package com.example.administrator.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class GameOneActivity extends Activity {
    // Variables Declaration
    TextView textView;
    int numberPlayers;
    int numberTurns;
    String[] noPlayerPhrases;
    String[] onePlayerPhrases;
    String[] twoPlayersPhrases;
    String[] threePlayersPhrases;
    String endPhrase;
    int type;
    int nmbr;
    int player1; // PlayerX variable are used to generate random phrases.
    int player2;
    int player3;
    boolean gameOver;
    String [] playerNames = new String[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_one);
        Log.d("GameOneActivity", "onCreate");
        //Hide navigation bar and status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        cbeer classCbeer = (cbeer)getApplication();
        numberPlayers = classCbeer.getNumberPlayers();
        numberTurns = 0;
        gameOver = false;
        endPhrase = getResources().getString(R.string.endPhrase);
        textView = (TextView) findViewById(R.id.textGameOne);
        classCbeer = (cbeer)getApplication();
        for(int i=0;i<classCbeer.getNumberPlayers();i++){
            playerNames[i] = classCbeer.getPlayerByNumber(i);
        }
    }

    @Override
    protected void onResume() {
        Log.d("GameOneActivity", "onResume");
        super.onResume();
    }

    public void btNext(View view) throws InterruptedException{
        //Hide navigation bar and status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        noPlayerPhrases = getResources().getStringArray(R.array.noPlayerArray);
        onePlayerPhrases = getResources().getStringArray(R.array.onePlayerArray);
        twoPlayersPhrases = getResources().getStringArray(R.array.twoPlayersArray);
        threePlayersPhrases = getResources().getStringArray(R.array.threePlayersArray);

        type = rndmType(1000);

        if (type == 0) {
            nmbr = rndmGen(noPlayerPhrases.length);
        } else if (type == 1) {
            nmbr = rndmGen(onePlayerPhrases.length);
            player1 = rndmGen(numberPlayers);
        } else if (type == 2) {
            nmbr = rndmGen(twoPlayersPhrases.length);
            player1 = rndmGen(numberPlayers);
            player2 = rndmGen(numberPlayers);
            while (player2 == player1) {
                player2 = rndmGen(numberPlayers);
            }
        } else {
            nmbr = rndmGen(threePlayersPhrases.length);
            player1 = rndmGen(numberPlayers);
            player2 = rndmGen(numberPlayers);
            while (player2 == player1) {
                player2 = rndmGen(numberPlayers);
            }
            player3 = rndmGen(numberPlayers);
            while (player3 == player1 || player3 == player2) {
                player3 = rndmGen(numberPlayers);
            }
        }

        if (gameOver){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (type == 0) {
                    textView.setText(noPlayerPhrases[nmbr]);
                } else if (type == 1) {
                    textView.setText(String.format(onePlayerPhrases[nmbr], playerNames[player1]));
                } else if (type == 2) {
                    textView.setText(String.format(twoPlayersPhrases[nmbr], playerNames[player1], playerNames[player2]));
                } else {
                    textView.setText(String.format(threePlayersPhrases[nmbr], playerNames[player1], playerNames[player2], playerNames[player3]));
                }

                if (numberTurns <= 40) {
                    numberTurns++;
                } else {
                    textView.setText(endPhrase);
                    gameOver = true;
                }

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("GameOneActivity", "onDestroy");
        finish();
    }

    //Generate random type of phrases with C Group algorithm
    public int rndmType (int max){
        int rndmInt = rndmGen(max);
        int type =0;
        double proba0 = 0, proba1 =0, proba2 =0, proba3 = 0, proba4 = 0;

        if(numberPlayers == 2){
            proba0 = 0.40;
            proba1 = 0.70;
            proba2 = 1;
            proba3 = 0;
            proba4 = 0;
        }
        if(numberPlayers == 3){
            proba0 = 0.34;
            proba1 = 0.56;
            proba2 = 0.78;
            proba3 = 1;
            proba4 = 0;
        }
        if(numberPlayers >= 4){
            proba0 = 0.32;
            proba1 = 0.49;
            proba2 = 0.66;
            proba3 = 0.83;
            proba4 = 1;
        }
        if(rndmInt >= 0 && rndmInt < max*proba0)
            type = 0;
        else if(rndmInt < max*proba1)
            type = 1;
        else if(rndmInt < max*proba2)
            type = 2;
        else if(rndmInt < max*proba3)
            type = 3;
        else if(rndmInt < max*proba4)
            type =4;


        return type;
    }

    //Generate random integer between 0 to max
    public int rndmGen (int max){
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(max);
    }

    /** Back Button Pressed, by pressing the back button
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
