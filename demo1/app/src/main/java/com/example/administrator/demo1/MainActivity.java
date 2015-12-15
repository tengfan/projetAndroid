package com.example.administrator.demo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
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
    protected void onResume(){
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
        finish();
    }

    public void btGameOne(View view) throws InterruptedException{
        Log.d("btGameOne", "btGameOne");
        Intent intent = new Intent(this, GameOneActivity.class);
        startActivity(intent);
    }

    public void btGameTwo(View view) throws InterruptedException{
        Log.d("btGameTwo", "btGameTwo");
        Intent intent = new Intent(this, GameTwoActivity.class);
        startActivity(intent);
    }

    public void btHelp(View view) throws InterruptedException{
        Log.d("btHelp", "btHelp");
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }

    /** Back Button Pressed, by pressing the back button
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
