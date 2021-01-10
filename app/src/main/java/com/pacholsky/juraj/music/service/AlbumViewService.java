package com.pacholsky.juraj.music.service;

import com.pacholsky.juraj.music.dto.AlbumView;
import com.pacholsky.juraj.music.dto.SongView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlbumViewService {

    @GET("/album-view/{id}")
    Call<AlbumView> getById(@Path("id") int id);

    @GET("/album-view")
    Call<List<AlbumView>> getAll();



}
