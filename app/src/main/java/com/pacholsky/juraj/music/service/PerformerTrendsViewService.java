package com.pacholsky.juraj.music.service;

import com.pacholsky.juraj.music.dto.PerformerTrendsView;
import com.pacholsky.juraj.music.dto.PerformerView;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PerformerTrendsViewService {

    @GET("/performertrendsview/{id}")
    Call<PerformerTrendsView> getById(@Path("id") int id);


}
