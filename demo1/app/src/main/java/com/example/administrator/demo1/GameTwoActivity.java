package com.example.administrator.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class GameTwoActivity extends Activity {
    /* Variables Declaration */
    ArrayList<Integer> cards = new ArrayList<Integer>();
    ImageView imageView;
    int numberImage=0;
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

    public void layoutGameTwo(View view) throws InterruptedException{
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
        numberImage++;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(cards.get(numberImage));
            }
        });
    }

    /** Cards initialization, we add the 52 cards without the 2 jokers to begin the game
     * */
    public void initCards(){
        cards.clear();

        cards.add(R.drawable.ace_of_clubs_w960);
        cards.add(R.drawable.ace_of_diamonds_w960);
        cards.add(R.drawable.ace_of_hearts_w960);
        cards.add(R.drawable.ace_of_spades_w960);

        cards.add(R.drawable.eight_of_clubs_w960);
        cards.add(R.drawable.eight_of_diamonds_w960);
        cards.add(R.drawable.eight_of_hearts_w960);
        cards.add(R.drawable.eight_of_spades_w960);

        cards.add(R.drawable.five_of_clubs_w960);
        cards.add(R.drawable.five_of_diamonds_w960);
        cards.add(R.drawable.five_of_hearts_w960);
        cards.add(R.drawable.five_of_spades_w960);

        cards.add(R.drawable.four_of_clubs_w960);
        cards.add(R.drawable.four_of_diamonds_w960);
        cards.add(R.drawable.four_of_hearts_w960);
        cards.add(R.drawable.four_of_spades_w960);

        cards.add(R.drawable.jack_of_clubs_w960);
        cards.add(R.drawable.jack_of_diamonds_w960);
        cards.add(R.drawable.jack_of_hearts_w960);
        cards.add(R.drawable.jack_of_spades_w960);

        cards.add(R.drawable.king_of_clubs_w960);
        cards.add(R.drawable.king_of_diamonds_w960);
        cards.add(R.drawable.king_of_hearts_w960);
        cards.add(R.drawable.king_of_spades_w960);

        cards.add(R.drawable.nine_of_clubs_w960);
        cards.add(R.drawable.nine_of_diamonds_w960);
        cards.add(R.drawable.nine_of_hearts_w960);
        cards.add(R.drawable.nine_of_spades_w960);

        cards.add(R.drawable.queen_of_clubs_w960);
        cards.add(R.drawable.queen_of_diamonds_w960);
        cards.add(R.drawable.queen_of_hearts_w960);
        cards.add(R.drawable.queen_of_spades_w960);

        cards.add(R.drawable.seven_of_clubs_w960);
        cards.add(R.drawable.seven_of_diamonds_w960);
        cards.add(R.drawable.seven_of_hearts_w960);
        cards.add(R.drawable.seven_of_spades_w960);

        cards.add(R.drawable.six_of_clubs_w960);
        cards.add(R.drawable.six_of_diamonds_w960);
        cards.add(R.drawable.six_of_hearts_w960);
        cards.add(R.drawable.six_of_spades_w960);

        cards.add(R.drawable.ten_of_clubs_w960);
        cards.add(R.drawable.ten_of_diamonds_w960);
        cards.add(R.drawable.ten_of_hearts_w960);
        cards.add(R.drawable.ten_of_spades_w960);

        cards.add(R.drawable.three_of_clubs_w960);
        cards.add(R.drawable.three_of_diamonds_w960);
        cards.add(R.drawable.three_of_hearts_w960);
        cards.add(R.drawable.three_of_spades_w960);

        cards.add(R.drawable.two_of_clubs_w960);
        cards.add(R.drawable.two_of_diamonds_w960);
        cards.add(R.drawable.two_of_hearts_w960);
        cards.add(R.drawable.two_of_spades_w960);
    }
}
