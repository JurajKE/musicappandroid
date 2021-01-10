package com.pacholsky.juraj.music;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pacholsky.juraj.music.adapter.AlbumsInPerformerAdapter;
import com.pacholsky.juraj.music.adapter.PerformersInAlbumAdapter;
import com.pacholsky.juraj.music.client.RetrofitClientInstance;
import com.pacholsky.juraj.music.constants.MusicContants;
import com.pacholsky.juraj.music.dto.AlbumView;
import com.pacholsky.juraj.music.dto.PerformerTrendsView;
import com.pacholsky.juraj.music.dto.PerformerView;
import com.pacholsky.juraj.music.dto.SongView;
import com.pacholsky.juraj.music.dto.inner.AlbumInPerformerView;
import com.pacholsky.juraj.music.dto.inner.PerformersInAlbumView;
import com.pacholsky.juraj.music.dto.inner.SongInAlbumView;
import com.pacholsky.juraj.music.intent.Intents;
import com.pacholsky.juraj.music.listener.RecyclerItemClickListener;
import com.pacholsky.juraj.music.service.AlbumViewService;
import com.pacholsky.juraj.music.service.PerformerTrendsViewService;
import com.pacholsky.juraj.music.service.PerformerViewService;
import com.pacholsky.juraj.music.service.SongViewService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerformerActivity extends AppCompatActivity {

    private LinearLayout linearLayoutPopularSongs;
    private RecyclerView recyclerViewNew;
    private AlbumsInPerformerAdapter albumsInPerformerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_performer_view);
        this.linearLayoutPopularSongs = findViewById(R.id.linearLayouPopularSongs);


        PerformerTrendsViewService sericetrens = RetrofitClientInstance.getRetrofitInstance().create(PerformerTrendsViewService.class);

        Bundle bundle = getIntent().getExtras();
        int performerId = bundle.getInt(MusicContants.PERFORMERID_EXTRA);

        Call<PerformerTrendsView> calls = sericetrens.getById(performerId);
        calls.enqueue(new Callback<PerformerTrendsView>() {
            @Override
            public void onResponse(Call<PerformerTrendsView> call, Response<PerformerTrendsView> response) {

                for (SongInAlbumView songInAlbumView: response.body().getSongs()
                ) {

                    View view = getSongInPerformerView(songInAlbumView);

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = Intents.getSongIntent(v.getContext() , songInAlbumView.getId());
                            startActivity(intent);
                        }
                    });

                    linearLayoutPopularSongs.addView(view);

                }

            }

            @Override
            public void onFailure(Call<PerformerTrendsView> call, Throwable t) {

            }
        });



        PerformerViewService service = RetrofitClientInstance.getRetrofitInstance().create(PerformerViewService.class);

//        Bundle bundle = getIntent().getExtras();
//        int performerId = bundle.getInt(MusicContants.PERFORMERID_EXTRA);

        Call<PerformerView> call = service.getById(performerId);
        call.enqueue(new Callback<PerformerView>() {
            @Override
            public void onResponse(Call<PerformerView> call, Response<PerformerView> response) {
                setFieldsFromPerformerView(response.body());

                List<AlbumInPerformerView> albumInPerformerViews = response.body().getAlbums().stream().distinct().collect(Collectors.toList());
                setDataInRecycler(albumInPerformerViews);

                setImage(response.body().getPictureFolder());
                setImageTwo(response.body().getPictureFolder());




            }

            @Override
            public void onFailure(Call<PerformerView> call, Throwable t) {

            }
        });


//        List<AlbumInPerformerView> albums = new ArrayList<>();
//        for (int i = 0; i< 10 ; i++) {
//            AlbumInPerformerView album = new AlbumInPerformerView();
//            album.setName(album.getName() + i);
//            albums.add(album);
//        }

        //vstupom do forka by uz mali byt nejake pesnicky
//        for (int i = 0; i < 5; i++) {
//            SongView songView = new SongView();
//            songView.setName("Pesnicka cislo " + (i + 1));
//
//            View v = getSongInPerformerView(songView);
//            linearLayoutPopularSongs.addView(v);
//        }



//        AlbumsInPerformerAdapter albumsInPerformerAdapter = new AlbumsInPerformerAdapter(albums);
//
//        RecyclerView recyclerViewAlbums = findViewById(R.id.albumListView);
//        recyclerViewAlbums.setAdapter(albumsInPerformerAdapter);
//        recyclerViewAlbums.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));




    }

    /**
     * Method to convert one SongView into View with set text fields
     */
    private View getSongInPerformerView(SongInAlbumView songInAlbumView) {
        final View songInPerformerView = getLayoutInflater().inflate(R.layout.item_song_in_performer_view, null);

        TextView textViewSongName = songInPerformerView.findViewById(R.id.songNameInPopularSongs);
        TextView textViewAlbum = songInPerformerView.findViewById(R.id.albumInPopularSongs);

        textViewSongName.setText(songInAlbumView.getName());

        for (PerformersInAlbumView performer: songInAlbumView.getPerformerNames()
             ) {
            textViewAlbum.setText(performer.getName());
        }

        return songInPerformerView;
    }

    private void setFieldsFromPerformerView(PerformerView performerView){
        TextView actorName = findViewById(R.id.namePerformer);
        actorName.setText(performerView.getName());
        TextView interpret = findViewById(R.id.interpret);
        interpret.setText(performerView.getName());
    }

    private void setDataInRecycler(List<AlbumInPerformerView> albumInPerformerViews){

        AlbumsInPerformerAdapter albumsInPerformerAdapter = new AlbumsInPerformerAdapter(albumInPerformerViews);

        RecyclerView recyclerView = findViewById(R.id.albumListView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = Intents.getAlbumIntent(PerformerActivity.this , albumsInPerformerAdapter.getOnPosition(position).getAlbumId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(albumsInPerformerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL , false));

    }

    private void setImage(String obrazok){

        String imageUri = obrazok;
        ImageView ivBasicImage = (ImageView) findViewById(R.id.performerImageHeader);
        Picasso.with(this).load(imageUri).into(ivBasicImage);

    }

    private void setImageTwo(String obrazok){

        String imageUri = obrazok;
        ImageView ivBasicImage = (ImageView) findViewById(R.id.likedSongPicture);
        Picasso.with(this).load(imageUri).into(ivBasicImage);

    }


}