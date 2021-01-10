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
import com.pacholsky.juraj.music.dto.inner.AlbumInGenreView;
import com.pacholsky.juraj.music.dto.inner.LudovkyGenreInMainPageView;
import com.pacholsky.juraj.music.dto.inner.SongInGenreView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LudovkyInMainPageAdapter extends RecyclerView.Adapter<LudovkyInMainPageAdapter.ViewHolder>{

    private List<AlbumInGenreView> albumInGenreViews;


    public LudovkyInMainPageAdapter(List<AlbumInGenreView> albumInGenreViews) {
        super();
        this.albumInGenreViews = albumInGenreViews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View ludovkyView = inflater.inflate(R.layout.item_ludovky_in_mainpage, parent, false);
        return new LudovkyInMainPageAdapter.ViewHolder(ludovkyView);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AlbumInGenreView albumInGenreView = albumInGenreViews.get(position);

        holder.albumName.setText(albumInGenreView.getAlbumName());
        holder.performerName.setText(albumInGenreView.getPerformerName());
        Picasso.with(holder.albumName.getContext()).load(albumInGenreView.getAlbumPictureFolder()).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return albumInGenreViews.size();
    }

    public AlbumInGenreView getOnPosition(int position){
        return albumInGenreViews.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //tu budu vsetky elementy, s ktorymi budes dynamicky pracovat
        private TextView albumName;
        private TextView performerName;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // a tu sa budu nastavovat cez findViewById
            albumName = itemView.findViewById(R.id.textView3);
            performerName = itemView.findViewById(R.id.textView5);
            imageView = itemView.findViewById(R.id.imageView3);
        }
    }

}
