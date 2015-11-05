package com.example.administrator.demo1;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
public class WelcomeActivity extends Activity {
    //Declaration des variables
    private ImageView [] player = {null, null, null, null, null, null, null, null, null};
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.d("WelcomeActivity", "onCreate() returned: " + "true");

        player[0] = (ImageView)findViewById(R.id.imagePlayer1);
        player[1] = (ImageView)findViewById(R.id.imagePlayer2);
        player[2] = (ImageView)findViewById(R.id.imagePlayer3);
        player[3] = (ImageView)findViewById(R.id.imagePlayer4);
        player[4] = (ImageView)findViewById(R.id.imagePlayer5);
        player[5] = (ImageView)findViewById(R.id.imagePlayer6);
        player[6] = (ImageView)findViewById(R.id.imagePlayer7);
        player[7] = (ImageView)findViewById(R.id.imagePlayer8);
        player[8] = (ImageView)findViewById(R.id.imagePlayer9);

        int[] images = {R.drawable.player1, R.drawable.player2, R.drawable.player3,
                R.drawable.player4, R.drawable.player5, R.drawable.player6,
                R.drawable.player7, R.drawable.player8, R.drawable.player9};

        for(i=0;i<9;i++){
            player[i].setImageResource(images[i]);
        }

        for(i=0;i<9;i++){
            player[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (player[i].getVisibility() == View.VISIBLE) player[i].setVisibility(View.INVISIBLE);
                    else player[i].setVisibility(View.VISIBLE);
                }
            });
         }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
      public void onResume() {
        super.onResume();
        Log.d("WelcomeActivity", "onResume() returned: " + "true");
        //Pour cacher les buttons de navigation et le bar
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
    public void onPause() {
        super.onPause();
        Log.d("WelcomeActivity", "onPause() returned: " + "true");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("WelcomeActivity", "onDestroy() returned: " + "true");
    }
}
