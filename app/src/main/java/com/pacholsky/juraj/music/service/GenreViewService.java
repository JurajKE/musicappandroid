package com.pacholsky.juraj.music.service;

import com.pacholsky.juraj.music.dto.GenreView;
import com.pacholsky.juraj.music.dto.SongView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GenreViewService {

    @GET("/genre-view/{id}")
    Call<GenreView> getById(@Path("id") int id);

    @GET("/genre-view/all")
    Call<List<GenreView>> getAll();

}
