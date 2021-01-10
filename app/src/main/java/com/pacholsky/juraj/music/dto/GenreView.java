package com.pacholsky.juraj.music.dto;

import com.google.gson.annotations.SerializedName;
import com.pacholsky.juraj.music.dto.inner.AlbumInGenreView;
import com.pacholsky.juraj.music.dto.inner.PerformerInGenreView;
import com.pacholsky.juraj.music.dto.inner.RockAlbumInMainView;
import com.pacholsky.juraj.music.dto.inner.SongInGenreView;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class GenreView {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("songsInGenreView")
    private List<SongInGenreView> songs = new ArrayList<>();
    @SerializedName("albumInGenreView")
    private List<AlbumInGenreView> albums = new ArrayList<>();
    @SerializedName("performerInGenreViews")
    private List<PerformerInGenreView> performers = new ArrayList<>();

}
