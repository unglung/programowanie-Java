package com.example.lab03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextName = findViewById(R.id.editTextName);
        RadioGroup radioGroupSize = findViewById(R.id.radioGroupSize);
        CheckBox checkBoxCheese = findViewById(R.id.checkBoxCheese);
        Button buttonSummary = findViewById(R.id.buttonSummary);

        buttonSummary.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();

            if (name.isEmpty()) {
                editTextName.setError("Podaj nazwę klienta");
                return;
            }

            int selectedId = radioGroupSize.getCheckedRadioButtonId();

            if (selectedId == -1) {
                return;
            }

            RadioButton selectedRadio = findViewById(selectedId);
            String size = selectedRadio.getText().toString();
            boolean cheese = checkBoxCheese.isChecked();

            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            intent.putExtra("NAME", name);
            intent.putExtra("SIZE", size);
            intent.putExtra("CHEESE", cheese);

            startActivity(intent);
        });
    }
}