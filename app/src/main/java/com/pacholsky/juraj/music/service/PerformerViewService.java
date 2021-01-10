package com.pacholsky.juraj.music.service;

import com.pacholsky.juraj.music.dto.PerformerView;
import com.pacholsky.juraj.music.dto.SongView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PerformerViewService {

    @GET("/performer-view/{id}")
    Call<PerformerView> getById(@Path("id") int id);



}
