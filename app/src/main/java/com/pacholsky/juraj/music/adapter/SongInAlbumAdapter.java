package com.pacholsky.juraj.music.adapter;

import android.content.Context;
import android.text.Layout;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacholsky.juraj.music.R;
import com.pacholsky.juraj.music.dto.SongView;
import com.pacholsky.juraj.music.dto.inner.SongInAlbumView;

import java.util.List;

public class SongInAlbumAdapter extends RecyclerView.Adapter<SongInAlbumAdapter.ViewHolder>{

    private List<SongInAlbumView> songs;

    public SongInAlbumAdapter(List<SongInAlbumView> songs) {
        super();
        this.songs = songs;
    }

//    public void setAll(List<SongInAlbumView> songs) {
//        //TODO replacce songs with this list
//    }
//
//    public void add(SongInAlbumView song) {
//        //TODO add to current list following song
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View songView = inflater.inflate(R.layout.item_song_in_album, parent, false);
        return new ViewHolder(songView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongInAlbumView song = songs.get(position);

        holder.tvSongName.setText(song.getName());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    /**
     * Trieda obsahujuca vsetky dynamicke elementy v jednom iteme vramci recycler view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        //tu budu vsetky elementy, s ktorymi budes dynamicky pracovat
        private TextView tvSongName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // a tu sa budu nastavovat cez findViewById
            tvSongName = itemView.findViewById(R.id.songNameInSongView);
        }
    }

}
