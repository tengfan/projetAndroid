package com.example.administrator.demo1;

import android.app.Activity;
import android.content.Context;
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

import java.util.Random;

public class GameThreeActivity extends Activity implements SensorEventListener {
    public float[] rotationMatrix, orientation, rotation;
    public float xPosition, xAcceleration = 0.0f;
    public float yPosition, yAcceleration = 0.0f;
    public float xCercle, yCercle = 0.0f;
    public float xMax,yMax = 0.0f;
    public boolean isInCercle = false;
    float timePrev = 0;
    float timeNow = 0;
    float tourStart = 0;
    /** Called when the activity is first created. */
    CustomDrawableView mCustomDrawableView = null;
    public SensorManager sensorManager = null;
    public Sensor rotationSensor;
    public float ballSize = 200;
    public float ballSizePlus = 20;
    public boolean isStartGame = false;
    public boolean isKeptInCercle = false;
    public boolean isFailed = false;
    public String textCounter;
    public String textScore;
    public String textInterval;
    public String textAnnonce;
    public int score = 0;
    public float interval1 = 15;
    public float interval2 = 2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);
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
        yMax = size.y-ballSize / 2;

        xCercle = rndmGenFloat(xMax-ballSizePlus);
        yCercle = rndmGenFloat(yMax-ballSizePlus);

        timeNow = (float) (System.currentTimeMillis()%100000000)/1000;
    }

    // This method will update the UI on new sensor events
    public void onSensorChanged(SensorEvent sensorEvent) {
        /** Get the orientation */
        if(isStartGame){
            mCustomDrawableView = new CustomDrawableView(this);
            setContentView(mCustomDrawableView);
        }
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

        timeNow = (float) (System.currentTimeMillis()%100000000)/1000;
        textScore = "Score: "+Integer.toString(score);
        Log.d("InCercle","xPosition:yPosition="+xPosition+":"+yPosition+"xCercle:yCercle="+xCercle+":"+yCercle);
        Log.d("Time","tourStart:"+tourStart+";timeNow:"+timeNow+";timePrev:"+timePrev);
        if(interval1+(tourStart-timeNow)>=0 && interval1+(tourStart-timeNow)<=15){
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
        else isFailed = true;
        Log.d("x:y", "updateBall: " + xPosition + " : " + yPosition);
    }

    // I've chosen to not implement this method
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register this class as a listener for the accelerometer sensor
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        // Unregister the listener
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    public void btStartGameThree(View view) {
        isStartGame = true;
        tourStart = (float) (System.currentTimeMillis()%100000000)/1000;
    }

    public class CustomDrawableView extends View {
        RectF pBallOval;
        Paint pBall;
        RectF pCercleOval;
        Paint pCercle;
        Paint pText;
        Paint pText2;
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
            pText2 = new Paint();
            pText.setTextSize((float) 40);
            pText.setColor(Color.WHITE);
            pText2.setColor(Color.WHITE);
            pText2.setTextSize((float) 120);
            textAnnonce = "Raté";
        }

        protected void onDraw(Canvas canvas) {
            updateBall();
            pBallOval.set(xPosition, yPosition, xPosition + ballSize, yPosition + ballSize);
            pCercleOval.set(xCercle, yCercle, xCercle + ballSize + ballSizePlus, yCercle + ballSize + ballSizePlus);

            if(isFailed){
                canvas.drawText(textAnnonce, xMax/2,yMax/2,pText2);
            }
            else{
                canvas.drawOval(pCercleOval, pCercle);
                canvas.drawOval(pBallOval, pBall);
                if(isInCercle){
                    canvas.drawText(textCounter, xMax-300, referenceText+2*interLine, pText);
                }
                canvas.drawText(textScore, xMax - 300, referenceText, pText);
                canvas.drawText(textInterval, xMax-300, referenceText+interLine, pText);
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
}
