package com.pacholsky.juraj.music.intent;

import android.content.Context;
import android.content.Intent;

import com.pacholsky.juraj.music.AlbumActivity;
import com.pacholsky.juraj.music.PerformerActivity;
import com.pacholsky.juraj.music.SongActivity;
import com.pacholsky.juraj.music.constants.MusicContants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Intents {

    public Intent getAlbumIntent(Context context , int albumId){
        Intent intent = new Intent(context , AlbumActivity.class);
        intent.putExtra(MusicContants.ALBUMID_EXTRA, albumId);
        return intent;
    }

    public Intent getPerformerIntent(Context context , int performerId){
        Intent intent = new Intent(context , PerformerActivity.class);
        intent.putExtra(MusicContants.PERFORMERID_EXTRA, performerId);
        return intent;
    }

    public Intent getSongIntent(Context context , int songId){
        Intent intent = new Intent(context , SongActivity.class);
        intent.putExtra(MusicContants.SONGID_EXTRA, songId);
        return intent;
    }




}
