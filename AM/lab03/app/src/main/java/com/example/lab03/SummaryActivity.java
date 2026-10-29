package com.example.lab03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView textViewName = findViewById(R.id.textViewName);
        TextView textViewSize = findViewById(R.id.textViewSize);
        TextView textViewCheese = findViewById(R.id.textViewCheese);
        Button buttonBack = findViewById(R.id.buttonBack);

        String name = getIntent().getStringExtra("NAME");
        String size = getIntent().getStringExtra("SIZE");
        boolean cheese = getIntent().getBooleanExtra("CHEESE", false);

        textViewName.setText("Klient: " + name);
        textViewSize.setText("Rozmiar pizzy: " + size);
        textViewCheese.setText("Dodatkowy ser: " + (cheese ? "Tak" : "Nie"));

        buttonBack.setOnClickListener(v -> finish());
    }
}