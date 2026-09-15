package com.example.bmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText heightInput = findViewById(R.id.height);
        EditText weightInput = findViewById(R.id.weight);
        Button button = findViewById(R.id.confirm_button);
        TextView final_result = findViewById(R.id.final_result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String height = heightInput.getText().toString();
                String weight = weightInput.getText().toString();

                if (height.isEmpty() || weight.isEmpty()) {
                    Toast.makeText(MainActivity.this, "height and weight needed", Toast.LENGTH_SHORT).show();
                    return;
                }

                double bmi = Float.parseFloat(weight) / Math.pow((Float.parseFloat(height) / 100), 2);

                bmi = Math.round(bmi * 100.0) / 100.0;
                String result;
                if (bmi < 17) {
                    result = "Wygłodzenie";
                } else if (bmi < 18.5) {
                    result = "Niedowaga";
                } else if (bmi < 25 ) {
                    result = "Waga prawidłowa";
                } else if (bmi < 30) {
                    result = "Nadwaga";
                } else if ( bmi < 35) {
                    result = "Otyłość";
                } else if ( bmi < 40 ) {
                    result = "Otyłość II stopnia";
                } else  {
                    result = "Skrajna otyłość III stopnia";
                }

                final_result.setText("BMI: " + bmi + "\n" + result);
            }
        });
    }
}