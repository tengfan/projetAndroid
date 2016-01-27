package com.example.administrator.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    String endOfGame = "Le jeu est fini. Cliquer sur le bouton Suivant pour à l'écran d'accueil.";
    String changeDealer = "Appuyez sur suivant pour tirer le nouveau dealer!";
    boolean inGame = false;
    boolean restartGame = false;
    int count=0;
    boolean changePlayer = true;
    boolean needValidate = true;
    Button countButton;
    String [] playerNames = new String[12];
    int numberPlayers;
    int actualDealer, newDealer;

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
        countButton = (Button) findViewById(R.id.buttonCount);
        cbeer classCbeer = (cbeer)getApplication();
        numberPlayers = classCbeer.getNumberPlayers();
        for(int i=0;i<classCbeer.getNumberPlayers();i++){
            playerNames[i] = classCbeer.getPlayerByNumber(i);
        }
    }

    @Override
    protected void onResume() {
        Log.d("GameTwoActivity", "onResume");
        super.onResume();
        //For hiding the navigation buttons and the bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d("GameTwoActivity", "onPause");
        //For hiding the navigation buttons and the bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
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

    public void btCount(View view) throws InterruptedException {
        //Hide navigation bar and status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        if(inGame && !needValidate) {
            if (count < 2) {
                count++;
                switch (count) {
                    case 1:
                        countButton.setText("1/3");
                        break;
                    case 2:
                        countButton.setText("2/3");
                        break;
                }
            } else if (count == 2) {
                count = 0;
                changePlayer = true;
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                countButton.setText("Compteur");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(changeDealer);
                    }
                });
            }
            needValidate=true;
        }
    }

    public void btReset(View view) throws InterruptedException {
        //Hide navigation bar and status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
        if(inGame && !needValidate) {
            count = 0;
            countButton.setText("Compteur");
            needValidate = true;
        }
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

        //Starting phase
        if(!inGame && needValidate && changePlayer) {
            inGame = true;
            changePlayer = false;
            newDealer = rndmGen(numberPlayers);
            actualDealer = newDealer;
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(String.format("%s est le Dealer!", playerNames[newDealer]));
                }
            });
        }
        //normal phase
        else if(needValidate){
            if(changePlayer){
                while(newDealer == actualDealer) {
                    newDealer = rndmGen(numberPlayers);
                }
                actualDealer = newDealer;
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(String.format("%s est le nouveau Dealer!", playerNames[newDealer]));
                    }
                });
                changePlayer = false;
            }
            else {
                needValidate = false;
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
                } else {
                    if (!restartGame) {
                        textView.setVisibility(View.VISIBLE);
                        imageView.setVisibility(View.GONE);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(endOfGame);
                            }
                        });
                        restartGame = true;
                    } else {
                        Intent intent = new Intent(this, WelcomeActivity.class);
                        startActivity(intent);
                    }
                }
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
