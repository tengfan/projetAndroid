package com.example.administrator.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameTwoActivity extends Activity {
    /* Variables Declaration */
    ArrayList<Integer> cards = new ArrayList<>();
    ImageView imageView;
    TextView textView;
    int imageNumber=0;
    String endOfGame = "Ce tour du jeu est fini. Cliquer sur le bouton Suivant pour recommencer le jeu.";
    boolean restartGame = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_two);

        //Hide navigation bar and status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        initCards();
        imageView = (ImageView) findViewById(R.id.imageCards);
        textView = (TextView) findViewById(R.id.textGameTwo);
    }

    @Override
    protected void onResume() {
        Log.d("GameTwoActivity", "onResume");
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("GameTwoActivity", "onDestroy");
        finish();
    }

    /** Back Button Pressed, by pressing the back button
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void btNext2(View view) throws InterruptedException {
        //Hide navigation bar and status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        if (!cards.isEmpty()) {
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            imageNumber = rndmGen(cards.size());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(cards.get(imageNumber));
                }
            });
            cards.remove(imageNumber);
        }
        else {
            if(!restartGame){
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(endOfGame);
                    }
                });
                restartGame = true;
            }
            else{
                initCards();
                restartGame = false;
            }
        }
        Log.d("btNext2", "btNext2: cards.size = " + cards.size());
    }

    /** Cards initialization, we add the 52 cards without the 2 jokers to begin the game
     * */
    public void initCards(){
        cards.clear();

        cards.add(R.drawable.ace_of_clubs_w720);
        cards.add(R.drawable.ace_of_diamonds_w720);
        cards.add(R.drawable.ace_of_hearts_w720);
        cards.add(R.drawable.ace_of_spades_w720);

        cards.add(R.drawable.eight_of_clubs_w720);
        cards.add(R.drawable.eight_of_diamonds_w720);
        cards.add(R.drawable.eight_of_hearts_w720);
        cards.add(R.drawable.eight_of_spades_w720);

        cards.add(R.drawable.five_of_clubs_w720);
        cards.add(R.drawable.five_of_diamonds_w720);
        cards.add(R.drawable.five_of_hearts_w720);
        cards.add(R.drawable.five_of_spades_w720);

        cards.add(R.drawable.four_of_clubs_w720);
        cards.add(R.drawable.four_of_diamonds_w720);
        cards.add(R.drawable.four_of_hearts_w720);
        cards.add(R.drawable.four_of_spades_w720);

        cards.add(R.drawable.jack_of_clubs_w720);
        cards.add(R.drawable.jack_of_diamonds_w720);
        cards.add(R.drawable.jack_of_hearts_w720);
        cards.add(R.drawable.jack_of_spades_w720);

        cards.add(R.drawable.king_of_clubs_w720);
        cards.add(R.drawable.king_of_diamonds_w720);
        cards.add(R.drawable.king_of_hearts_w720);
        cards.add(R.drawable.king_of_spades_w720);

        cards.add(R.drawable.nine_of_clubs_w720);
        cards.add(R.drawable.nine_of_diamonds_w720);
        cards.add(R.drawable.nine_of_hearts_w720);
        cards.add(R.drawable.nine_of_spades_w720);

        cards.add(R.drawable.queen_of_clubs_w720);
        cards.add(R.drawable.queen_of_diamonds_w720);
        cards.add(R.drawable.queen_of_hearts_w720);
        cards.add(R.drawable.queen_of_spades_w720);

        cards.add(R.drawable.seven_of_clubs_w720);
        cards.add(R.drawable.seven_of_diamonds_w720);
        cards.add(R.drawable.seven_of_hearts_w720);
        cards.add(R.drawable.seven_of_spades_w720);

        cards.add(R.drawable.six_of_clubs_w720);
        cards.add(R.drawable.six_of_diamonds_w720);
        cards.add(R.drawable.six_of_hearts_w720);
        cards.add(R.drawable.six_of_spades_w720);

        cards.add(R.drawable.ten_of_clubs_w720);
        cards.add(R.drawable.ten_of_diamonds_w720);
        cards.add(R.drawable.ten_of_hearts_w720);
        cards.add(R.drawable.ten_of_spades_w720);

        cards.add(R.drawable.three_of_clubs_w720);
        cards.add(R.drawable.three_of_diamonds_w720);
        cards.add(R.drawable.three_of_hearts_w720);
        cards.add(R.drawable.three_of_spades_w720);

        cards.add(R.drawable.two_of_clubs_w720);
        cards.add(R.drawable.two_of_diamonds_w720);
        cards.add(R.drawable.two_of_hearts_w720);
        cards.add(R.drawable.two_of_spades_w720);
    }

    //Generate random integer between 0 to max
    public int rndmGen (int max){
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(max);
    }
}
