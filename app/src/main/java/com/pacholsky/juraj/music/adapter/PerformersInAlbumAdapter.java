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
import com.pacholsky.juraj.music.dto.inner.PerformersInAlbumView;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class PerformersInAlbumAdapter extends RecyclerView.Adapter<PerformersInAlbumAdapter.ViewHolder>{

    private List<PerformersInAlbumView> albums;

    public PerformersInAlbumAdapter(List<PerformersInAlbumView> albums) {
        super();
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View performerView = inflater.inflate(R.layout.item_performer_in_album_view, parent, false);
        return new PerformersInAlbumAdapter.ViewHolder(performerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PerformersInAlbumView performersInAlbumView = albums.get(position);

        holder.performerName.setText(performersInAlbumView.getName());
        Picasso.with(holder.performerName.getContext()).load(performersInAlbumView.getPicture()).into(holder.picturePhoto);

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public PerformersInAlbumView getOnPosition(int position){
        return albums.get(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        //tu budu vsetky elementy, s ktorymi budes dynamicky pracovat
        private TextView performerName;
        private ImageView picturePhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // a tu sa budu nastavovat cez findViewById
            performerName = itemView.findViewById(R.id.namePerformerView);
            picturePhoto = itemView.findViewById(R.id.imagePerformer);



        }
    }

}
