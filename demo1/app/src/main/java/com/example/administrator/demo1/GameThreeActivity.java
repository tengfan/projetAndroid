package com.example.administrator.demo1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
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
    protected float xCercle, yCercle = 0.0f;
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
    protected RadioGroup radioGroup;
    protected RadioButton radioButton;
    protected Button btValid;

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

        xCercle = rndmGenFloat(xMax-ballSizePlus);
        yCercle = rndmGenFloat(yMax-ballSizePlus);

        textStart = (TextView)findViewById(R.id.textStart);
        radioGroup = (RadioGroup)findViewById(R.id.radioDifficulty);
        btValid = (Button)findViewById(R.id.btValid);
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
        Log.d("InCercle","xPosition:yPosition="+xPosition+":"+yPosition+"xCercle:yCercle="+xCercle+":"+yCercle);
        Log.d("Time","tourStart:"+tourStart+";timeNow:"+timeNow+";timePrev:"+timePrev);
        if(isGameOn){
            if(interval1+(tourStart-timeNow)>=0 && interval1+(tourStart-timeNow)<=interval1){
                textInterval = "Il te reste : "+Float.toString(interval1+(tourStart-timeNow))+" sec";
                if (xPosition<=xCercle+ballSizePlus && xPosition >= xCercle && yPosition <= yCercle+ballSizePlus && yPosition >= yCercle) {
                    isInCercle = true;
                    textCounter = "Counter : "+Float.toString(timeNow-timePrev)+" sec";
                    if(timeNow - timePrev > interval2) {
                        isKeptInCercle = true;
                        xCercle = rndmGenFloat(xMax-ballSizePlus);
                        yCercle = rndmGenFloat(yMax-ballSizePlus);
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
            mCustomDrawableView = new CustomDrawableView(this);
            setContentView(mCustomDrawableView);
        }
        else{
            isFailed = false;
            String text;
            if(isFinished){
                if(!isBlocked) {
                    isBlocked = true;
                    int index = 0;
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

    public class CustomDrawableView extends View {
        RectF pBallOval;
        Paint pBall;
        RectF pCercleOval;
        Paint pCercle;
        Paint pText;
        float referenceText = 40;
        float interLine = 50;

        public CustomDrawableView(Context context) {
            super(context);
            pBallOval = new RectF(xPosition, yPosition, xPosition + ballSize, yPosition + ballSize);
            pBall = new Paint();
            pBall.setColor(Color.RED);
            pCercleOval = new RectF(xCercle, yCercle, xCercle+ballSize+ballSizePlus, yCercle+ballSize+ballSizePlus);
            pCercle = new Paint();
            pCercle.setColor(Color.BLUE);
            pText = new Paint();
            pText.setTextSize((float) 40);
            pText.setColor(Color.WHITE);
        }

        protected void onDraw(Canvas canvas) {
            updateBall();
            pBallOval.set(xPosition, yPosition, xPosition + ballSize, yPosition + ballSize);
            pCercleOval.set(xCercle, yCercle, xCercle + ballSize + ballSizePlus, yCercle + ballSize + ballSizePlus);

            if(isFailed) {
                Log.d("onDraw", "activity_game_three");
                if(playerNames.isEmpty()) isFinished = true;
                setContentView(R.layout.activity_game_three);
                textView = (TextView)findViewById(R.id.textView);
                scoreList.add(score);
            }
            else{
                canvas.drawOval(pCercleOval, pCercle);
                canvas.drawOval(pBallOval, pBall);
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
