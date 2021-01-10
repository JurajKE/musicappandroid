package com.pacholsky.juraj.music.dto;

import com.google.gson.annotations.SerializedName;
import com.pacholsky.juraj.music.dto.inner.AlbumInPerformerView;
import com.pacholsky.juraj.music.dto.inner.SongInAlbumView;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PerformerView {

    @SerializedName("albums")
    private List<AlbumInPerformerView> albums = new ArrayList<>();
    //TODO pridat pesnicky
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("pictureFolder")
    private String pictureFolder;
}
