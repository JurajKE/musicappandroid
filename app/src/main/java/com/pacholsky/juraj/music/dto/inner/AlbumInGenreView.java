package com.pacholsky.juraj.music.dto.inner;

import lombok.Data;

@Data
public class AlbumInGenreView {

    private int id;
    private String albumName;
    private String performerName;
    private String albumPictureFolder;
}
