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
import android.widget.TextView;

import java.util.Calendar;

public class GameThreeActivity extends Activity implements SensorEventListener {
    public float[] rotationMatrix, orientation, rotation;
    public float xPosition, xAcceleration = 0.0f;
    public float yPosition, yAcceleration = 0.0f;
    public float xmax,ymax;
    public float xcenter,ycenter;
    public boolean isCenter = false;
    Calendar c = Calendar.getInstance();
    public TextView textResult;
    public TextView textTime;
    int seconds_prev = 0;
    int seconds_now = 0;
    /** Called when the activity is first created. */
    CustomDrawableView mCustomDrawableView = null;

    public SensorManager sensorManager = null;
    public Sensor rotationSensor;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_three);
        textResult  = (TextView) findViewById(R.id.textResult);
        textTime = (TextView) findViewById(R.id.textTime);
        textResult.setVisibility(View.GONE);
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

        mCustomDrawableView = new CustomDrawableView(this);

        // Calculate Boundry
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        xmax = size.x - 50;
        ymax = size.y - 50;

        xcenter = xmax/2;
        ycenter = ymax/2;
        Log.d("x:y center", "onCreate: Xcenter:Ycenter= "+xcenter+":"+ycenter);
        setContentView(mCustomDrawableView);
    }

    // This method will update the UI on new sensor events
    public void onSensorChanged(SensorEvent sensorEvent) {
        /** Get the orientation */
        System.arraycopy(sensorEvent.values, 0, rotation, 0, 3);
        SensorManager.getRotationMatrixFromVector(rotationMatrix, rotation);
        SensorManager.getOrientation(rotationMatrix, orientation);

        xAcceleration = orientation[1]*200;
        yAcceleration = orientation[2]*200;

        Log.d("x:y", "Acceleration: " + xAcceleration + " : " + yAcceleration);
        updateBall();
        if(isCenter) {
            textResult.setVisibility(View.VISIBLE);
        }
    }

    private void updateBall() {
        //Add to position negative due to sensor
        //readings being opposite to what we want!
        xPosition -= xAcceleration;
        yPosition -= yAcceleration;

        if (xPosition > xmax) {
            xPosition = xmax;
        } else if (xPosition < 0) {
            xPosition = 0;
        }
        if (yPosition > ymax) {
            yPosition = ymax;
        } else if (yPosition < 0) {
            yPosition = 0;
        }

        seconds_now = c.get(Calendar.SECOND);
        if (xPosition<=xcenter+55 && xPosition >= xcenter-55 && yPosition <= ycenter+55 && yPosition >= ycenter-55) {
            if(seconds_now - seconds_prev > 20) isCenter = true;
        }
        else{
            isCenter = false;
            seconds_prev = c.get(Calendar.SECOND);
        }

        this.runOnUiThread(new Runnable() {

            public void run() {
                textTime.setText(Integer.toString(seconds_now));
            }
        });
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

    public class CustomDrawableView extends View {
        public CustomDrawableView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            RectF oval = new RectF(xPosition, yPosition, xPosition + 50, yPosition + 50);
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            canvas.drawOval(oval, p);
            invalidate();
        }
    }
}
