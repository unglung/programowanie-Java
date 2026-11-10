
package com.example.styczen2025;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    class Photo {
        int id;
        String alt;
        String filename;
        int category;
        int downloads;

        Photo(int id, String alt, String filename, int category, int downloads) {
            this.id = id;
            this.alt = alt;
            this.filename = filename;
            this.category = category;
            this.downloads = downloads;
        }
    }

    ArrayList<Photo> photos = new ArrayList<>();

    GridLayout gallery;

    CheckBox flowers;
    CheckBox animals;
    CheckBox cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gallery = findViewById(R.id.gallery);

        flowers = findViewById(R.id.switchFlowers);
        animals = findViewById(R.id.switchAnimals);
        cars = findViewById(R.id.switchCars);

        photos.add(new Photo(0, "Mak", "obraz1", 1, 35));
        photos.add(new Photo(1, "Bukiet", "obraz2", 1, 43));
        photos.add(new Photo(2, "Dalmatyńczyk", "obraz3", 2, 2));
        photos.add(new Photo(3, "Świnka morska", "obraz4", 2, 53));
        photos.add(new Photo(4, "Rotwailer", "obraz5", 2, 43));
        photos.add(new Photo(5, "Audi", "obraz6", 3, 11));
        photos.add(new Photo(6, "kotki", "obraz7", 2, 22));
        photos.add(new Photo(7, "Róża", "obraz8", 1, 33));
        photos.add(new Photo(8, "Świnka morska", "obraz9", 2, 123));
        photos.add(new Photo(9, "Foksterier", "obraz10", 2, 22));
        photos.add(new Photo(10, "Szczeniak", "obraz11", 2, 12));
        photos.add(new Photo(11, "Garbus", "obraz12", 3, 321));

        flowers.setOnCheckedChangeListener((button, checked) -> showPhotos());
        animals.setOnCheckedChangeListener((button, checked) -> showPhotos());
        cars.setOnCheckedChangeListener((button, checked) -> showPhotos());

        showPhotos();
    }

    private void showPhotos() {
        gallery.removeAllViews();

        for (Photo photo : photos) {

            boolean visible =
                    (photo.category == 1 && flowers.isChecked()) ||
                            (photo.category == 2 && animals.isChecked()) ||
                            (photo.category == 3 && cars.isChecked());

            if (!visible) {
                continue;
            }

            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setGravity(Gravity.CENTER);
            card.setPadding(5, 5, 5, 10);

            GradientDrawable background = new GradientDrawable();
            background.setColor(Color.WHITE);
            background.setCornerRadius(15);
            card.setBackground(background);

            ImageView image = new ImageView(this);

            int imageId = getResources().getIdentifier(
                    photo.filename,
                    "drawable",
                    getPackageName()
            );

            if (imageId != 0) {
                image.setImageResource(imageId);
            }

            image.setContentDescription(photo.alt);
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

            LinearLayout.LayoutParams imageParams =
                    new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            160
                    );

            imageParams.setMargins(5, 5, 5, 5);
            image.setLayoutParams(imageParams);

            TextView downloadsText = new TextView(this);
            downloadsText.setText("Pobrań: " + photo.downloads);
            downloadsText.setTextSize(18);
            downloadsText.setTextColor(Color.BLACK);
            downloadsText.setTypeface(null, Typeface.BOLD);
            downloadsText.setGravity(Gravity.CENTER);

            Button downloadButton = new Button(this);
            downloadButton.setText("Pobierz");

            downloadButton.setOnClickListener(v -> {
                photo.downloads++;
                downloadsText.setText("Pobrań: " + photo.downloads);
            });

            card.addView(image);
            card.addView(downloadsText);
            card.addView(downloadButton);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.setMargins(5, 5, 5, 5);

            card.setLayoutParams(params);

            gallery.addView(card);
        }
    }
}
