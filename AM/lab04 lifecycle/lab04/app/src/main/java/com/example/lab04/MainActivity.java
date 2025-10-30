package com.example.lab04;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MainActivity extends Activity {

    private Context context;
    private int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        Toast.makeText(context, "onCreate", duration).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(context, "onStart", duration).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, "onResume", duration).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(context, "onPause", duration).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(context, "onStop", duration).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(context, "onRestart", duration).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(context, "onDestroy", duration).show();
    }

    public void exitApp(android.view.View view) {
        Toast.makeText(context, "EXIT button clicked", duration).show();
        finish();
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(context, "onSaveInstanceState", duration).show();
    }
}
