package com.example.administrator.demo1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends Activity {
    //Variables declaration
    int numberPlayers = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.d("WelcomeActivity", "onCreate");
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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
      public void onResume() {
        super.onResume();
        Log.d("WelcomeActivity", "onResume");
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
    public void onPause() {
        super.onPause();
        Log.d("WelcomeActivity", "onPause");
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
        Log.d("WelcomeActivity", "onDestroy");
        finish();
    }

    //Process behind start button
    public void btStart(View view) throws InterruptedException{
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //Creation of table of players
        EditText [] editPlayers = new EditText[12];
        editPlayers[0] = (EditText) findViewById(R.id.editPlayer1);
        editPlayers[1] = (EditText) findViewById(R.id.editPlayer2);
        editPlayers[2] = (EditText) findViewById(R.id.editPlayer3);
        editPlayers[3] = (EditText) findViewById(R.id.editPlayer4);
        editPlayers[4] = (EditText) findViewById(R.id.editPlayer5);
        editPlayers[5] = (EditText) findViewById(R.id.editPlayer6);
        editPlayers[6] = (EditText) findViewById(R.id.editPlayer7);
        editPlayers[7] = (EditText) findViewById(R.id.editPlayer8);
        editPlayers[8] = (EditText) findViewById(R.id.editPlayer9);
        editPlayers[9] = (EditText) findViewById(R.id.editPlayer10);
        editPlayers[10] = (EditText) findViewById(R.id.editPlayer11);
        editPlayers[11] = (EditText) findViewById(R.id.editPlayer12);


        //Set players' names to application
        cbeer application = (cbeer) getApplication();
        for (int i = 0; i < numberPlayers; i++){
            application.setOnePlayer(i,editPlayers[i].getText().toString());
        }
        application.setNumberPlayers(numberPlayers);
    }

    public void btAdd4(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd4);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd5);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove4);
        EditText editText = (EditText)findViewById(R.id.editPlayer4);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 4;
    }

    public void btAdd5(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd5);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd6);
        EditText editText = (EditText)findViewById(R.id.editPlayer5);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove4);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove5);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 5;
    }

    public void btAdd6(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd6);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd7);
        EditText editText = (EditText)findViewById(R.id.editPlayer6);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove5);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove6);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 6;
    }

    public void btAdd7(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd7);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd8);
        EditText editText = (EditText)findViewById(R.id.editPlayer7);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove6);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove7);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 7;
    }

    public void btAdd8(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd8);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd9);
        EditText editText = (EditText)findViewById(R.id.editPlayer8);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove7);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove8);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 8;
    }

    public void btAdd9(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd9);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd10);
        EditText editText = (EditText)findViewById(R.id.editPlayer9);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove8);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove9);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 9;
    }

    public void btAdd10(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd10);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd11);
        EditText editText = (EditText)findViewById(R.id.editPlayer10);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove9);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove10);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 10;
    }

    public void btAdd11(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd11);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd12);
        EditText editText = (EditText)findViewById(R.id.editPlayer11);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove10);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove11);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 11;
    }

    public void btAdd12(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd12);
        EditText editText = (EditText)findViewById(R.id.editPlayer12);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove11);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove12);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
        numberPlayers = 12;
    }

    public void btRemove4(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd4);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd5);
        EditText editText = (EditText)findViewById(R.id.editPlayer4);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove4);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 3;
    }

    public void btRemove5(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd5);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd6);
        EditText editText = (EditText)findViewById(R.id.editPlayer5);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove4);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove5);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 4;
    }

    public void btRemove6(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd6);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd7);
        EditText editText = (EditText)findViewById(R.id.editPlayer6);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove5);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove6);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 5;
    }

    public void btRemove7(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd7);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd8);
        EditText editText = (EditText)findViewById(R.id.editPlayer7);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove6);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove7);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 6;
    }

    public void btRemove8(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd8);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd9);
        EditText editText = (EditText)findViewById(R.id.editPlayer8);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove7);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove8);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 7;
    }

    public void btRemove9(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd9);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd10);
        EditText editText = (EditText)findViewById(R.id.editPlayer9);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove8);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove9);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 8;
    }

    public void btRemove10(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd10);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd11);
        EditText editText = (EditText)findViewById(R.id.editPlayer10);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove9);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove10);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 9;
    }

    public void btRemove11(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd11);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd12);
        EditText editText = (EditText)findViewById(R.id.editPlayer11);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove10);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove11);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 10;
    }

    public void btRemove12(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd12);
        EditText editText = (EditText)findViewById(R.id.editPlayer12);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove11);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove12);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
        numberPlayers = 11;
    }

    public void layoutWelcome(View view) throws InterruptedException{
        Log.d("layoutWelcome", "onResume");
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
}