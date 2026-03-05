package com.example.smartfoodfinder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ORSService {
    @GET("/search")
    Call<List<OSMPlace>> searchOSM(
            @Query("q") String query,
            @Query("format") String format
    );
}