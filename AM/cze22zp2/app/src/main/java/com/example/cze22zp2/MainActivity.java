package com.example.cze22zp2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<String> courses = Arrays.asList(
            "Programowanie w C#",
            "Angular dla początkujących",
            "Kurs Django");

    private EditText editFullName;
    private EditText editCourseNumber;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editFullName = findViewById(R.id.editFullName);
        editCourseNumber = findViewById(R.id.editCourseNumber);
        textResult = findViewById(R.id.textResult);

        showCourseCount();
        showCourseList();

        Button buttonEnroll = findViewById(R.id.buttonEnroll);
        buttonEnroll.setOnClickListener(view -> enroll());
    }

    private void showCourseCount() {
        TextView textCourseCount = findViewById(R.id.textCourseCount);
        textCourseCount.setText("Liczba kursów: " + courses.size());
    }

    private void showCourseList() {
        LinearLayout layoutCourseList = findViewById(R.id.layoutCourseList);
        for (int i = 0; i < courses.size(); i++) {
            TextView item = new TextView(this);
            item.setText((i + 1) + ". " + courses.get(i));
            layoutCourseList.addView(item);
        }
    }

    private void enroll() {
        String fullName = editFullName.getText().toString();
        String courseName = findCourseName(editCourseNumber.getText().toString());
        textResult.setText(fullName + "\n" + courseName);
    }

    private String findCourseName(String courseNumberText) {
        try {
            int courseNumber = Integer.parseInt(courseNumberText.trim());
            if (courseNumber >= 1 && courseNumber <= courses.size()) {
                return courses.get(courseNumber - 1);
            }
        } catch (NumberFormatException e) {
            // puste lub zbyt duze pole - traktujemy jak nieprawidlowy numer
        }
        return "Nieprawidłowy numer kursu";
    }
}