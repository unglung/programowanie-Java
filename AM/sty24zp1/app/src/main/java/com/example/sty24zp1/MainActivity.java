package com.example.sty24zp1;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String DEFAULT_NUMBER = "000";
    private static final String PHOTO_NAME = "zdjecie";
    private static final String FINGERPRINT_NAME = "odcisk";

    private EditText editNumber;
    private EditText editFirstName;
    private EditText editLastName;
    private RadioGroup groupEyeColor;
    private ImageView imagePhoto;
    private ImageView imageFingerprint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        showImages(DEFAULT_NUMBER);

        editNumber.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                showImages(editNumber.getText().toString().trim());
            }
        });

        Button buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(view -> confirmData());
    }

    private void bindViews() {
        editNumber = findViewById(R.id.editNumber);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        groupEyeColor = findViewById(R.id.groupEyeColor);
        imagePhoto = findViewById(R.id.imagePhoto);
        imageFingerprint = findViewById(R.id.imageFingerprint);
    }

    private void showImages(String number) {
        loadImage(imagePhoto, number, PHOTO_NAME);
        loadImage(imageFingerprint, number, FINGERPRINT_NAME);
    }

    private void loadImage(ImageView target, String number, String kind) {
        int resourceId = findDrawableId(number, kind);
        if (resourceId == 0) {
            // brak obrazu dla podanego numeru - nic nie wyswietlamy
            target.setImageDrawable(null);
        } else {
            target.setImageResource(resourceId);
        }
    }

    private int findDrawableId(String number, String kind) {
        if (number.isEmpty()) {
            return 0;
        }
        String[] candidates = {
                "_" + number + "_" + kind,
                "_" + number.substring(1) + "_" + kind
        };
        for (String name : candidates) {
            int id = getResources().getIdentifier(name, "drawable", getPackageName());
            if (id != 0) {
                return id;
            }
        }
        return 0;
    }

    private void confirmData() {
        String firstName = editFirstName.getText().toString().trim();
        String lastName = editLastName.getText().toString().trim();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(this, "Wprowadź dane", Toast.LENGTH_SHORT).show();
            return;
        }

        String message = firstName + " " + lastName + " kolor oczu " + getSelectedEyeColor();
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private String getSelectedEyeColor() {
        RadioButton selected = findViewById(groupEyeColor.getCheckedRadioButtonId());
        return selected.getText().toString();
    }
}