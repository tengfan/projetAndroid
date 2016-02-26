package com.example.administrator.demo1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameThreeActivity extends Activity implements SensorEventListener {
    protected float[] rotationMatrix, orientation, rotation;
    protected float xPosition, xAcceleration = 0.0f;
    protected float yPosition, yAcceleration = 0.0f;
    protected float xCible, yCible = 0.0f;
    protected float xMax,yMax = 0.0f;
    protected boolean isInCercle = false;
    protected float timePrev = 0;
    protected float timeNow = 0;
    protected float tourStart = 0;
    /** Called when the activity is first created. */
    protected CustomDrawableView mCustomDrawableView = null;
    protected SensorManager sensorManager = null;
    protected Sensor rotationSensor;
    protected float ballSize = 200;
    protected float ballSizePlus = 20;
    protected boolean isGameOn = false;
    protected boolean isKeptInCercle = false;
    protected boolean isFailed = false;
    protected String textCounter;
    protected String textScore;
    protected String textInterval;
    protected String textPlayer = "";
    protected int score = 0;
    protected float interval1 = 15;
    protected float interval2 = 2;
    protected ArrayList<String> playerNames = new ArrayList<>();
    protected ArrayList<String> newPlayerNames = new ArrayList<>();
    protected ArrayList<Integer> scoreList = new ArrayList<>();
    protected int numberPlayers;
    protected String playerNow = "";
    protected TextView textView;
    protected int touchCounter = 0;
    protected boolean isFinished = false;
    protected boolean isBlocked = false;
    protected TextView textStart;
    protected TextView textStartDifficulty;
    protected RadioGroup radioGroup;
    protected RadioButton radioButton;
    protected Button btValid;
    Resources teng;
    Bitmap teng_bitmap;
    Resources clem;
    Bitmap clem_bitmap;
    Resources flav;
    Bitmap flav_bitmap;
    Resources beer;
    Bitmap beer_bitmap;
    Bitmap cible_bitmap;
    Bitmap ball_bitmap;
    Resources background;
    Bitmap background_bitmap;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three_difficulty);

        //Load player list
        cbeer classCbeer = (cbeer)getApplication();
        numberPlayers = classCbeer.getNumberPlayers();
        for (int i = 0; i < classCbeer.getNumberPlayers(); i++) {
            playerNames.add(classCbeer.getPlayerByNumber(i));
        }
        //For hiding the navigation buttons and the bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        // Get a reference to a SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        rotationMatrix = new float[9];
        orientation = new float[3];
        rotation = new float[3];

        // Calculate Boundry
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        xMax = size.x-ballSize;
        yMax = size.y-ballSize/2;

        xPosition = xMax/2;
        yPosition = yMax/2;

        xCible = rndmGenFloat(xMax-ballSizePlus);
        yCible = rndmGenFloat(yMax-ballSizePlus);

        textStart = (TextView)findViewById(R.id.textStart);
        radioGroup = (RadioGroup)findViewById(R.id.radioDifficulty);
        btValid = (Button)findViewById(R.id.btValid);

        //Load Resources
        teng = getResources();
        teng_bitmap = BitmapFactory.decodeResource(teng, R.drawable.teng);
        clem = getResources();
        clem_bitmap = BitmapFactory.decodeResource(clem, R.drawable.clem);
        flav = getResources();
        flav_bitmap = BitmapFactory.decodeResource(flav, R.drawable.flav);
        beer = getResources();
        beer_bitmap = BitmapFactory.decodeResource(beer, R.drawable.beer_game_3_goal);
        ball_bitmap = Bitmap.createScaledBitmap(beer_bitmap, (int) ballSize, (int) ballSize, false);
        background = getResources();
        background_bitmap = BitmapFactory.decodeResource(beer, R.drawable.background_game_3);
        background_bitmap = Bitmap.createScaledBitmap(background_bitmap, (int) (xMax+ballSize), (int) (yMax+ballSize), false);
    }

    // This method will update the UI on new sensor events
    public void onSensorChanged(SensorEvent sensorEvent) {
        /** Get the orientation */
        System.arraycopy(sensorEvent.values, 0, rotation, 0, 3);
        SensorManager.getRotationMatrixFromVector(rotationMatrix, rotation);
        SensorManager.getOrientation(rotationMatrix, orientation);
        xAcceleration = orientation[1]*10;
        yAcceleration = orientation[2]*10;
        Log.d("x:y", "Acceleration: " + xAcceleration + " : " + yAcceleration);
    }

    private void updateBall() {
        //Add to position negative due to sensor
        //readings being opposite to what we want!
        xPosition -= xAcceleration;
        yPosition -= yAcceleration;

        if (xPosition > xMax) {
            xPosition = xMax;
        } else if (xPosition < 0) {
            xPosition = 0;
        }
        if (yPosition > yMax) {
            yPosition = yMax;
        } else if (yPosition < 0) {
            yPosition = 0;
        }

        timeNow = (float) (System.currentTimeMillis()%1000000000)/1000;
        textScore = "Score: "+Integer.toString(score);
        textPlayer = "Nom du joueur: "+playerNow;
        Log.d("InCercle","xPosition:yPosition="+xPosition+":"+yPosition+"xCible:yCible="+xCible+":"+yCible);
        Log.d("Time","tourStart:"+tourStart+";timeNow:"+timeNow+";timePrev:"+timePrev);
        if(isGameOn){
            if(interval1+(tourStart-timeNow)>=0 && interval1+(tourStart-timeNow)<=interval1){
                textInterval = "Il te reste : "+String.format("%.2f", interval1 + (tourStart - timeNow))+" sec";
                if (xPosition<=xCible+ballSizePlus && xPosition >= xCible && yPosition <= yCible+ballSizePlus && yPosition >= yCible) {
                    isInCercle = true;
                    textCounter = "Counter : "+String.format("%.2f", timeNow - timePrev)+" sec";
                    if(timeNow - timePrev > interval2) {
                        isKeptInCercle = true;
                        int number = rndmGen(3);
                        switch (number){
                            case 0:cible_bitmap = Bitmap.createScaledBitmap(teng_bitmap, (int)(ballSize+ballSizePlus), (int) (ballSize+ballSizePlus), false);break;
                            case 1:cible_bitmap = Bitmap.createScaledBitmap(flav_bitmap, (int)(ballSize+ballSizePlus), (int) (ballSize+ballSizePlus), false);break;
                            case 2:cible_bitmap = Bitmap.createScaledBitmap(clem_bitmap, (int)(ballSize+ballSizePlus), (int) (ballSize+ballSizePlus), false);break;
                            default:cible_bitmap = Bitmap.createScaledBitmap(clem_bitmap, (int)(ballSize+ballSizePlus), (int) (ballSize+ballSizePlus), false);break;
                        }
                        xCible = rndmGenFloat(xMax-ballSizePlus);
                        yCible = rndmGenFloat(yMax-ballSizePlus);
                        score+=1;
                        tourStart = timeNow;
                    }
                }
                else{
                    isInCercle = false;
                    isKeptInCercle = false;
                    timePrev = timeNow;
                }
            }
            else {
                isFailed = true;
                isGameOn = false;
            }
            Log.d("x:y", "updateBall: " + xPosition + " : " + yPosition);
        }
    }

    // I've chosen to not implement this method
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR), SensorManager.SENSOR_DELAY_NORMAL);
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

    @Override
    protected void onStop() {
        // Unregister the listener
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    public void btScreenTouch(View view) {
        if(isGameOn) {
            Log.d("btScreenTouch","mCustomDrawableView");
            tourStart = (float) (System.currentTimeMillis()%1000000000)/1000;
            int number = rndmGen(3);
            switch (number){
                case 0:cible_bitmap = Bitmap.createScaledBitmap(teng_bitmap, (int)(ballSize+ballSizePlus), (int) (ballSize+ballSizePlus), false);break;
                case 1:cible_bitmap = Bitmap.createScaledBitmap(flav_bitmap, (int)(ballSize+ballSizePlus), (int) (ballSize+ballSizePlus), false);break;
                case 2:cible_bitmap = Bitmap.createScaledBitmap(clem_bitmap, (int)(ballSize+ballSizePlus), (int) (ballSize+ballSizePlus), false);break;
                default:cible_bitmap = Bitmap.createScaledBitmap(clem_bitmap, (int)(ballSize+ballSizePlus), (int) (ballSize+ballSizePlus), false);break;
            }
            xCible = rndmGenFloat(xMax-ballSizePlus);
            yCible = rndmGenFloat(yMax-ballSizePlus);
            mCustomDrawableView = new CustomDrawableView(this);
            setContentView(mCustomDrawableView);
        }
        else{
            isFailed = false;
            String text;
            if(isFinished){
                if(!isBlocked) {
                    isBlocked = true;
                    int index;
                    textView = (TextView) findViewById(R.id.textView);
                    textView.setTextSize(20);
                    text = "Ce jeu est fini. Voici les résultat.\n\n";
                    for (int i = 0; i < numberPlayers; i++) {
                        index = getScoreMax();
                        Log.d("New PlayerList Index", "=" + index);
                        Log.d("New PlayerList Size", "=" + newPlayerNames.size());
                        text += newPlayerNames.get(index) + " : " + scoreList.get(index) + "\n";
                        newPlayerNames.remove(index);
                        scoreList.remove(index);
                    }
                    textView.setText(text);
                    isGameOn = false;
                }
            }
            else{
                if(touchCounter==1) {
                    textView = (TextView) findViewById(R.id.textView);
                    text = playerNow + ", ton tour est fini et ton score est " + Integer.toString(score) + "\nAppuie pour le tour suivant.";
                    textView.setText(text);
                    touchCounter = 2;
                }
                else if(touchCounter==2 || touchCounter == 0) {
                    int number = rndmGen(playerNames.size());
                    playerNow = playerNames.get(number);

                    Log.d("btScreenTouch", "textView");
                    textView = (TextView) findViewById(R.id.textView);
                    text = playerNow + " , c'est ton tour à jouer!\nAppuie sur l'écran pour commencer!";
                    textView.setText(text);
                    newPlayerNames.add(playerNow);
                    playerNames.remove(number);
                    isGameOn = true;
                    touchCounter = 1;
                }
            }
            score=0;
        }
    }

    public void btValid(View view) {
        if(radioGroup.getCheckedRadioButtonId()!=-1){
            int selectedId = radioGroup.getCheckedRadioButtonId();
            radioButton = (RadioButton) findViewById(selectedId);
            if(radioButton.getText().equals("Facile")){
                ballSizePlus = 40;
            }
            if(radioButton.getText().equals("Moyen")){
                ballSizePlus = 20;
            }
            if(radioButton.getText().equals("Difficile")){
                ballSizePlus = 10;
            }
            setContentView(R.layout.activity_game_three);
            textView = (TextView)findViewById(R.id.textView);
        }
        else{
            textStartDifficulty = (TextView)findViewById(R.id.textStart);
            textStartDifficulty.setText("Choisir une difficulté STP!");
        }
    }

    public class CustomDrawableView extends View {
        Paint pCible;
        Paint pBall;
        Paint pBackground;
        Paint pText;
        float referenceText = 40;
        float interLine = 50;

        public CustomDrawableView(Context context) {
            super(context);
            pBall = new Paint();
            pCible = new Paint();
            pBackground = new Paint();
            pText = new Paint();
            pText.setTextSize((float) 40);
            pText.setTypeface(Typeface.DEFAULT_BOLD);
            pText.setColor(Color.WHITE);
        }

        protected void onDraw(Canvas canvas) {
            updateBall();
            if(isFailed) {
                Log.d("onDraw", "activity_game_three");
                if(playerNames.isEmpty()) isFinished = true;
                setContentView(R.layout.activity_game_three_failed);
                scoreList.add(score);
            }
            else{
                canvas.drawBitmap(background_bitmap,0,0,pBackground);
                canvas.drawBitmap(cible_bitmap,xCible,yCible,pCible);
                canvas.drawBitmap(ball_bitmap, xPosition, yPosition,pBall);
                if(isInCercle){
                    canvas.drawText(textCounter, xMax-300, referenceText+3*interLine, pText);
                }
                canvas.drawText(textPlayer, xMax - 300, referenceText, pText);
                canvas.drawText(textScore, xMax - 300, referenceText+interLine, pText);
                canvas.drawText(textInterval, xMax - 300, referenceText + 2*interLine, pText);
                invalidate();
            }
        }
    }

    //Generate random float
    public float rndmGenFloat(float max) {
        Random randomGenerator = new Random();
        float result;
        result = randomGenerator.nextFloat();
        result = Math.abs(result*max%max);
        Log.d("rndmGenFloat","result = "+result);
        return result;
    }

    //Generate random integer between 0 to max
    public int rndmGen(int max) {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(max);
    }

    /**
     * get the max score in the rest of players
     * @return index of the player
     */
    public int getScoreMax() {
        int index = 0;
        int i, j, len = scoreList.size();
        for (i = 0; i < len - 1; i++) {
            for (j = 0; j < len - 1 - i; j++) {
                if (scoreList.get(j) > scoreList.get(j + 1)) {
                    index = scoreList.indexOf(scoreList.get(j));
                }
                else{
                    index = scoreList.indexOf(scoreList.get(j+1));
                }
            }
        }
        return index;
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
}
