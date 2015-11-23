package com.example.administrator.demo1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class GameOneActivity extends Activity {
    // Variables Declaration
    TextView textView;
    int numberPlayers;
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
        textView = (TextView) findViewById(R.id.textGameOne);
    }

    @Override
    protected void onResume() {
        Log.d("GameOneActivity", "onResume");
        super.onResume();
        //Hide navigation bar and status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
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

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("Value : " + rndmType(1000));
                Log.d("CONTRUCTION PHRASE", String.format("%s et %s sont des grosses C!!", "Clément", "Teng"));

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("GameOneActivity", "onDestroy");
        finish();
    }

    public void layoutGameOne(View view) throws InterruptedException{
        //Hide navigation bar and status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
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
}
