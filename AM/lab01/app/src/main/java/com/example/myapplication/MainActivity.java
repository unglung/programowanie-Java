package com.example.myapplication;


import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Button btn = findViewById(R.id.myButton);
        btn.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Oscar Witman", Toast.LENGTH_SHORT).show();
        });
    }
}
