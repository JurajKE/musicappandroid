package com.pacholsky.juraj.music.dto;

import com.google.gson.annotations.SerializedName;
import com.pacholsky.juraj.music.dto.inner.PerformersInAlbumView;
import com.pacholsky.juraj.music.dto.inner.SongInAlbumView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AlbumView {


    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("performerName")
    private String performerName ;
    @SerializedName("performerId")
    private int performerId;
    @SerializedName("albumId")
    private int albumId;
    @SerializedName("releaseDate")
    private Date releaseDate;
    @SerializedName("songs")
    private List<SongInAlbumView> songs = new ArrayList<>();
    @SerializedName("performersInAlbumViews")
    private List<PerformersInAlbumView> performersInAlbumViews = new ArrayList<>();
    @SerializedName("picture")
    private String picture;
    @SerializedName("performerPicture")
    private String performerPicture;


}