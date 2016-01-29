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
    int imageNumber = 0;
    String endOfGame = "Le jeu est fini. Cliquer sur le bouton Suivant pour à l'écran d'accueil.";
    String changeDealer = "Appuyez sur suivant pour tirer le nouveau dealer!";
    boolean inGame = false;
    boolean restartGame = false;
    int count = 0;
    boolean changePlayer = false;
    boolean needValidate = true;
    Button countButton;
    Button btNext2;
    TextView textCounter;
    String[] playerNames = new String[12];
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
        for (int i = 0; i < classCbeer.getNumberPlayers(); i++) {
            playerNames[i] = classCbeer.getPlayerByNumber(i);
        }
        newDealer = rndmGen(numberPlayers);
        textView.setText(String.format("%s est le Dealer!", playerNames[newDealer]));
        btNext2 = (Button) findViewById(R.id.buttonNext2);
        btNext2.setVisibility(View.VISIBLE);
        textCounter = (TextView) findViewById(R.id.textCounter);
        textCounter.setVisibility(View.GONE);
        inGame = false;
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
    protected void onPause() {
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

    /**
     * Back Button Pressed, by pressing the back button
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

        if (inGame) {
            //Changing Player
            //Starting phase
            if (changePlayer) {
                newDealer = rndmGen(numberPlayers);
                actualDealer = newDealer;
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                btNext2.setVisibility(View.VISIBLE);
                textCounter.setVisibility(View.GONE);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(String.format("%s est le Dealer!", playerNames[newDealer]));
                    }
                });
            }

            //Normal phase
            if (changePlayer) {
                while (newDealer == actualDealer) {
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


            // Counting part
            if (count < 2) {
                count++;
            } else if (count == 2) {
                count = 0;
                changePlayer = true;
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                btNext2 = (Button) findViewById(R.id.buttonNext2);
                btNext2.setVisibility(View.VISIBLE);
                textCounter = (TextView) findViewById(R.id.textCounter);
                textCounter.setVisibility(View.GONE);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(changeDealer);
                    }
                });
            }
            textCounter = (TextView) findViewById(R.id.textCounter);
            textCounter.setText(Integer.toString(count));
            //needValidate=true;
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

        if (inGame) {
            count = 0;
            textCounter = (TextView) findViewById(R.id.textCounter);
            textCounter.setText(Integer.toString(count));
            imageNumber = rndmGen(cards.size());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(cards.get(imageNumber));
                }
            });
            cards.remove(imageNumber);
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
        if (!inGame) {
            inGame = true;
            btNext2 = (Button) findViewById(R.id.buttonNext2);
            btNext2.setVisibility(View.GONE);
            textCounter = (TextView) findViewById(R.id.textCounter);
            textCounter.setVisibility(View.VISIBLE);
            textCounter.setText(Integer.toString(0));
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            runOnUiThread(new Runnable() {
                public void run() {
                    imageView.setImageResource(cards.get(imageNumber));
                }
            });
            cards.remove(imageNumber);
        }

        if (changePlayer){
            changePlayer = false;
            textCounter = (TextView) findViewById(R.id.textCounter);
            textCounter.setVisibility(View.GONE);
            btNext2 = (Button) findViewById(R.id.buttonNext2);
            btNext2.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(changeDealer);
                }
            });

        }
     }

    /**
     * Cards initialization, we add the 52 cards without the 2 jokers to begin the game
     */
    public void initCards() {
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
    public int rndmGen(int max) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(max);
    }
}
