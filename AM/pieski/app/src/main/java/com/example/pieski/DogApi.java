package com.example.pieski;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogApi {

    @GET("breeds/image/random/10")
    Call<DogResponse> getDogs();

}