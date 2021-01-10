package com.pacholsky.juraj.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacholsky.juraj.music.AlbumActivity;
import com.pacholsky.juraj.music.PerformerActivity;
import com.pacholsky.juraj.music.R;
import com.pacholsky.juraj.music.dto.inner.AlbumInPerformerView;
import com.pacholsky.juraj.music.dto.inner.PerformersInAlbumView;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumsInPerformerAdapter extends RecyclerView.Adapter<AlbumsInPerformerAdapter.ViewHolder>{

    private List<AlbumInPerformerView> albums;



    public AlbumsInPerformerAdapter(List<AlbumInPerformerView> albums) {
        super();
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumsInPerformerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        final View albumView = inflater.inflate(R.layout.activity_performerslistview, parent, false);
        return new AlbumsInPerformerAdapter.ViewHolder(albumView);

    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsInPerformerAdapter.ViewHolder holder, int position) {

        AlbumInPerformerView album = albums.get(position);
        holder.albumName.setText(album.getName());
        Picasso.with(holder.albumName.getContext()).load(album.getPicture()).into(holder.picture);

        holder.picture.setOnClickListener(v -> {
        });

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }


    public AlbumInPerformerView getOnPosition(int position){
        return albums.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //tu budu vsetky elementy, s ktorymi budes dynamicky pracovat
        private TextView albumName;
        private ImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // a tu sa budu nastavovat cez findViewById
            albumName = itemView.findViewById(R.id.nameAlbumView);
            picture = itemView.findViewById(R.id.imageAlbum);




            }
    }


}
