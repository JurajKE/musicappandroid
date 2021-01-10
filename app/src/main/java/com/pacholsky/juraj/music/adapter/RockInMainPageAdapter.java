package com.pacholsky.juraj.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacholsky.juraj.music.R;
import com.pacholsky.juraj.music.dto.inner.AlbumInPerformerView;
import com.pacholsky.juraj.music.dto.inner.RockAlbumInMainView;
import com.pacholsky.juraj.music.dto.inner.SongInGenreView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RockInMainPageAdapter extends RecyclerView.Adapter<RockInMainPageAdapter.ViewHolder>{

    private List<SongInGenreView> songInGenreViewslist;

    public RockInMainPageAdapter(List<SongInGenreView> songInGenreViews) {
        super();
        this.songInGenreViewslist = songInGenreViews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View albumAndPerformerView = inflater.inflate(R.layout.item_rock_category_in_mainpage, parent, false);
        return new RockInMainPageAdapter.ViewHolder(albumAndPerformerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SongInGenreView songInGenreView = songInGenreViewslist.get(position);

        holder.name.setText(songInGenreView.getName());
        holder.performerName.setText(songInGenreView.getPerformerName());
        Picasso.with(holder.name.getContext()).load(songInGenreView.getSongPictureFolder()).into(holder.pictureFolder);


    }


    public SongInGenreView getOnPosition(int position){
        return songInGenreViewslist.get(position);
    }

    @Override
    public int getItemCount() {
        return songInGenreViewslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //tu budu vsetky elementy, s ktorymi budes dynamicky pracovat
        private TextView name;
        private TextView performerName;
        private ImageView pictureFolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // a tu sa budu nastavovat cez findViewById
            name = itemView.findViewById(R.id.textView11);
            performerName = itemView.findViewById(R.id.textView12);
            pictureFolder = itemView.findViewById(R.id.imageView10);
        }
    }

}
