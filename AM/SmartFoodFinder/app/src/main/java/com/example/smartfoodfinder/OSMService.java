package com.example.smartfoodfinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Headers;

public interface OSMService {

    @Headers("User-Agent: SmartFoodFinderApp")
    @GET("/search")
    Call<List<OSMPlace>> searchPlaces(
            @Query("q") String query,
            @Query("format") String format,
            @Query("limit") int limit
    );
}