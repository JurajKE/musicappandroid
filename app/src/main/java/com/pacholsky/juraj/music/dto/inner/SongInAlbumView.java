package com.pacholsky.juraj.music.dto.inner;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class SongInAlbumView {

    private String name;
    private int id;
    private List<PerformersInAlbumView> performerNames = new ArrayList<>();

}
