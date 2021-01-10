package com.pacholsky.juraj.music.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pacholsky.juraj.music.R;
import com.pacholsky.juraj.music.dto.GenreView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenreViewAdapter extends RecyclerView.Adapter<GenreViewAdapter.ViewHolder>{

//      private List<GenreInGenreView> genre;
        private List<GenreView> genre;

    public GenreViewAdapter(List<GenreView> genre) {
        super();
        this.genre = genre;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View genreView = inflater.inflate(R.layout.item_genre_in_search_view, parent, false);
        return new GenreViewAdapter.ViewHolder(genreView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GenreView genres = genre.get(position);
        holder.genreName.setText(genres.getName());

    }

    @Override
    public int getItemCount() {
        return genre.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //tu budu vsetky elementy, s ktorymi budes dynamicky pracovat
        private TextView genreName;
        private ImageView imagecolor;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // a tu sa budu nastavovat cez findViewById
            genreName = itemView.findViewById(R.id.textView4);
            imagecolor = itemView.findViewById(R.id.imageView11);
            imagecolor.setBackgroundColor(getRandomColorCode());




        }
    }

    public int getRandomColorCode(){

        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256),random.nextInt(256));

    }

}
