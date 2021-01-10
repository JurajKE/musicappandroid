package com.pacholsky.juraj.music.service;

import com.pacholsky.juraj.music.dto.AlbumView;
import com.pacholsky.juraj.music.dto.SongView;
import com.pacholsky.juraj.music.dto.inner.AlbumInPerformerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SongViewService {

    @GET("/song-view/{id}")
    Call<SongView> getById(@Path("id") int id);

    @GET("/song-view")
    Call<List<SongView>> getAll();

}
