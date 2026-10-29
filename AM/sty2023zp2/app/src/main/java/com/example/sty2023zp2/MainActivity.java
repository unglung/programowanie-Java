package com.example.sty2023zp2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

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

        TextInputEditText input = findViewById(R.id.input);
        Button confirm_button = findViewById(R.id.confirm_button);

        ArrayList<String> items = new ArrayList<>();

        items.add("Zakupy: Chleb, masło, ser");
        items.add("Do zrobienia: obiad, umyć podłogi");
        items.add("weekend: kino, spacer z psem");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.list_element,
                items
        );
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();

                if (!text.isEmpty()) {
                    items.add(text);
                    adapter.notifyDataSetChanged();
                    input.setText("");
                }

            }
        });
    }




}