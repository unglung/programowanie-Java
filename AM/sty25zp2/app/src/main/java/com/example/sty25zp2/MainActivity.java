package com.example.sty25zp2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int MIN_WASH_NUMBER = 1;
    private static final int MAX_WASH_NUMBER = 12;

    private EditText editWashNumber;
    private TextView textWashNumber;
    private TextView textVacuumState;
    private Button buttonToggleVacuum;

    private boolean vacuumOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editWashNumber = findViewById(R.id.editWashNumber);
        textWashNumber = findViewById(R.id.textWashNumber);
        textVacuumState = findViewById(R.id.textVacuumState);
        buttonToggleVacuum = findViewById(R.id.buttonToggleVacuum);

        Button buttonConfirm = findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(view -> confirmWashNumber());
        buttonToggleVacuum.setOnClickListener(view -> toggleVacuum());
    }

    private void confirmWashNumber() {
        String input = editWashNumber.getText().toString().trim();
        if (input.isEmpty()) {
            return;
        }

        int washNumber = Integer.parseInt(input);
        if (washNumber >= MIN_WASH_NUMBER && washNumber <= MAX_WASH_NUMBER) {
            textWashNumber.setText("Numer prania: " + washNumber);
        }
    }

    private void toggleVacuum() {
        vacuumOn = !vacuumOn;
        if (vacuumOn) {
            buttonToggleVacuum.setText("Wyłącz");
            textVacuumState.setText("Odkurzacz włączony");
        } else {
            buttonToggleVacuum.setText("Włącz");
            textVacuumState.setText("Odkurzacz wyłączony");
        }
    }
}