package com.example.sty2024zp2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String[] SPECIES = {"Pies", "Kot", "Świnka morska"};
    private static final int[] MAX_AGE = {18, 20, 9};
    private static final int DEFAULT_HOUR = 16;
    private static final int DEFAULT_MINUTE = 0;

    private EditText editOwner;
    private EditText editPurpose;
    private ListView listSpecies;
    private SeekBar seekAge;
    private TextView textAge;
    private TextView textSummary;
    private TimePicker timeVisit;

    private String selectedSpecies = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupSpeciesList();
        setupAgeSlider();
        setupTimePicker();

        Button buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(view -> showSummary());
    }

    private void bindViews() {
        editOwner = findViewById(R.id.editOwner);
        editPurpose = findViewById(R.id.editPurpose);
        listSpecies = findViewById(R.id.listSpecies);
        seekAge = findViewById(R.id.seekAge);
        textAge = findViewById(R.id.textAge);
        textSummary = findViewById(R.id.textSummary);
        timeVisit = findViewById(R.id.timeVisit);
    }

    private void setupSpeciesList() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, SPECIES);
        listSpecies.setAdapter(adapter);

        listSpecies.setOnItemClickListener((parent, view, position, id) -> {
            selectedSpecies = SPECIES[position];
            seekAge.setMax(MAX_AGE[position]);
            textAge.setText(String.valueOf(seekAge.getProgress()));
        });
    }

    private void setupAgeSlider() {
        seekAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textAge.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setupTimePicker() {
        timeVisit.setIs24HourView(true);
        timeVisit.setHour(DEFAULT_HOUR);
        timeVisit.setMinute(DEFAULT_MINUTE);
    }

    private void showSummary() {
        String time = String.format("%02d:%02d", timeVisit.getHour(), timeVisit.getMinute());
        String summary = editOwner.getText() + ", "
                + selectedSpecies + ", "
                + seekAge.getProgress() + ", "
                + editPurpose.getText() + ", "
                + time;
        textSummary.setText(summary);
    }
}