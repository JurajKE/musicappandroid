package com.pacholsky.juraj.music.service;

import com.pacholsky.juraj.music.dto.PlaylistView;
import com.pacholsky.juraj.music.dto.SongView;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PlaylistViewService {

    @GET("/playlist-view/{id}")
    Call<PlaylistView> getById(@Path("id") int id);

    @GET("/playlist-view")
    Call<List<PlaylistView>> getAll();

}
