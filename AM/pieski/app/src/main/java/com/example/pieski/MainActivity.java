package com.example.pieski;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Button btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);

        loadDogs();

        btnNext.setOnClickListener(v -> {
            int next = viewPager.getCurrentItem() + 1;
            viewPager.setCurrentItem(next, true);
        });

        btnPrev.setOnClickListener(v -> {
            int prev = viewPager.getCurrentItem() - 1;
            if (prev >= 0) {
                viewPager.setCurrentItem(prev, true);
            }
        });
    }

    private void loadDogs() {

        DogApi api = RetrofitClient.getClient().create(DogApi.class);

        api.getDogs().enqueue(new Callback<DogResponse>() {
            @Override
            public void onResponse(Call<DogResponse> call, Response<DogResponse> response) {

                if (response.body() != null) {

                    List<String> images = response.body().message;

                    ImageAdapter adapter = new ImageAdapter(images);
                    viewPager.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<DogResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}