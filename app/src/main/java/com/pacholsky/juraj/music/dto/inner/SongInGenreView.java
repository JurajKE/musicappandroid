package com.pacholsky.juraj.music.dto.inner;

import lombok.Data;

@Data
public class SongInGenreView {

    private String name;
    private int id;
    private String performerName;
    private String songPictureFolder;
}
