package com.example.lab05;

import android.os.Bundle;
import android.widget.*;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        LinearLayout container = findViewById(R.id.containerLayout);

        TextView textView = new TextView(this);
        textView.setText("Lista przycisków");
        textView.setPadding(16,16,16,16);
        textView.setTextSize(20);
        container.addView(textView);

        ScrollView scrollView = new ScrollView(this);
        container.addView(scrollView);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);

        for (int i = 1; i <= 20; i++) {
            Button button = new Button(this);
            button.setText("Przycisk " + i);
            linearLayout.addView(button);
        }
    }
}
