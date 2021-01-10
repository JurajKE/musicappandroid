package com.pacholsky.juraj.music.dto;

import com.google.gson.annotations.SerializedName;
import com.pacholsky.juraj.music.dto.inner.SongInAlbumView;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PerformerTrendsView {

    @SerializedName("songs")
    private List<SongInAlbumView> songs = new ArrayList<>();
    @SerializedName("id")
    private int id;


}
