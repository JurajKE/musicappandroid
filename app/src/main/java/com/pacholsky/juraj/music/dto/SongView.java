package com.pacholsky.juraj.music.dto;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongView {

    @SerializedName("albumName")
    private String albumName;
    @SerializedName("name")
    private String name;
    @SerializedName("performerNames")
    private List<String> performerNames = new ArrayList<>();
    @SerializedName("id")
    private int id;
    @SerializedName("lenght")
    private Time lenght;
    @SerializedName("albumId")
    private int albumId;
    @SerializedName("performerId")
    private int performerId;
    @SerializedName("albumPicture")
    private String albumPicture;



}
