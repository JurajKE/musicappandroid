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
import com.pacholsky.juraj.music.dto.inner.PerformerInGenreView;
import com.pacholsky.juraj.music.dto.inner.SongInGenreView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PerformerInGenreViewAdapter extends RecyclerView.Adapter<PerformerInGenreViewAdapter.ViewHolder>{

    private List<PerformerInGenreView> performerInGenreViews;


    public PerformerInGenreViewAdapter(List<PerformerInGenreView> performerInGenreViews) {
        super();
        this.performerInGenreViews = performerInGenreViews;
    }

    @NonNull
    @Override
    public PerformerInGenreViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View performerView = inflater.inflate(R.layout.item_hiphop_in_mainpage, parent, false);
        return new PerformerInGenreViewAdapter.ViewHolder(performerView);


    }

    @Override
    public void onBindViewHolder(@NonNull PerformerInGenreViewAdapter.ViewHolder holder, int position) {

        PerformerInGenreView performerInGenreView = performerInGenreViews.get(position);

        holder.performerName.setText(performerInGenreView.getPerformerName());
        Picasso.with(holder.performerName.getContext()).load(performerInGenreView.getPictureFolder()).into(holder.imageView);


    }


    public PerformerInGenreView getOnPosition(int position){
        return performerInGenreViews.get(position);
    }



    @Override
    public int getItemCount() {
        return performerInGenreViews.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //tu budu vsetky elementy, s ktorymi budes dynamicky pracovat
        private TextView performerName;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // a tu sa budu nastavovat cez findViewById
            performerName = itemView.findViewById(R.id.textView7);
            imageView = itemView.findViewById(R.id.imageView5);
        }
    }

}
