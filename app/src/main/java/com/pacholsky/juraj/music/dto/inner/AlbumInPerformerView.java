package com.pacholsky.juraj.music.dto.inner;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

import lombok.Data;

@Data
public class AlbumInPerformerView {

    private int albumId;
    private String name;
    private Date albumDate;
    private String picture;



}
