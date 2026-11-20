package com.example.cze22;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int likesCount = 0;
    private TextView textLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLikes = findViewById(R.id.textLikes);

        Button buttonLike = findViewById(R.id.buttonLike);
        Button buttonRemove = findViewById(R.id.buttonRemove);

        buttonLike.setOnClickListener(view -> {
            likesCount++;
            showLikes();
        });

        buttonRemove.setOnClickListener(view -> {
            if (likesCount > 0) {
                likesCount--;
            }
            showLikes();
        });
    }

    private void showLikes() {
        textLikes.setText(likesCount + " polubień");
    }
}