package com.example.cze23;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int POSTAL_CODE_LENGTH = 5;

    private RadioGroup groupShipmentType;
    private ImageView imageShipment;
    private TextView textPrice;
    private EditText editPostalCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupShipmentType = findViewById(R.id.groupShipmentType);
        imageShipment = findViewById(R.id.imageShipment);
        textPrice = findViewById(R.id.textPrice);
        editPostalCode = findViewById(R.id.editPostalCode);

        Button buttonCheckPrice = findViewById(R.id.buttonCheckPrice);
        Button buttonConfirm = findViewById(R.id.buttonConfirm);

        buttonCheckPrice.setOnClickListener(view -> showPriceForSelectedType());
        buttonConfirm.setOnClickListener(view -> showMessage(validatePostalCode()));
    }

    private void showPriceForSelectedType() {
        int selectedId = groupShipmentType.getCheckedRadioButtonId();

        if (selectedId == R.id.radioLetter) {
            imageShipment.setImageResource(R.drawable.list);
            textPrice.setText("Cena: 1,5 zł");
        } else if (selectedId == R.id.radioPackage) {
            imageShipment.setImageResource(R.drawable.paczka);
            textPrice.setText("Cena: 10 zł");
        } else {
            imageShipment.setImageResource(R.drawable.pocztowka);
            textPrice.setText("Cena: 1 zł");
        }
    }

    private String validatePostalCode() {
        String postalCode = editPostalCode.getText().toString();

        if (postalCode.length() != POSTAL_CODE_LENGTH) {
            return "Nieprawidłowa liczba cyfr w kodzie pocztowym";
        }
        if (!containsOnlyDigits(postalCode)) {
            return "Kod pocztowy powinien się składać z samych cyfr";
        }
        return "Dane przesyłki zostały wprowadzone";
    }

    private boolean containsOnlyDigits(String text) {
        for (int i = 0; i < text.length(); i++) {
            char sign = text.charAt(i);
            if (sign < '0' || sign > '9') {
                return false;
            }
        }
        return true;
    }

    private void showMessage(String message) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}