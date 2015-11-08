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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.d("WelcomeActivity", "onCreate");
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
      public void onResume() {
        super.onResume();
        Log.d("WelcomeActivity", "onResume");
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

    @Override
    public void onPause() {
        super.onPause();
        Log.d("WelcomeActivity", "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("WelcomeActivity", "onDestroy");
        finish();
    }

    public void btStart(View view) throws InterruptedException{
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void btAdd3(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd3);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd4);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove3);
        EditText editText = (EditText)findViewById(R.id.editPlayer3);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
    }

    public void btAdd4(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd4);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd5);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove3);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove4);
        EditText editText = (EditText)findViewById(R.id.editPlayer4);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonAddNext.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
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
    }

    public void btAdd8(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd8);
        EditText editText = (EditText)findViewById(R.id.editPlayer8);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove7);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove8);
        editText.setVisibility(View.VISIBLE);
        buttonAddPrev.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.VISIBLE);
    }

    public void btRemove3(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd3);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd4);
        EditText editText = (EditText)findViewById(R.id.editPlayer3);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove3);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemoveNext.setVisibility(View.GONE);
    }

    public void btRemove4(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd4);
        Button buttonAddNext = (Button)findViewById(R.id.buttonAdd5);
        EditText editText = (EditText)findViewById(R.id.editPlayer4);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove3);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove4);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonAddNext.setVisibility(View.GONE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
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
    }

    public void btRemove8(View view) throws InterruptedException{
        Button buttonAddPrev = (Button)findViewById(R.id.buttonAdd8);
        EditText editText = (EditText)findViewById(R.id.editPlayer8);
        Button buttonRemovePrev = (Button)findViewById(R.id.buttonRemove7);
        Button buttonRemoveNext = (Button)findViewById(R.id.buttonRemove8);
        editText.setVisibility(View.GONE);
        buttonAddPrev.setVisibility(View.VISIBLE);
        buttonRemovePrev.setVisibility(View.VISIBLE);
        buttonRemoveNext.setVisibility(View.GONE);
    }
}
