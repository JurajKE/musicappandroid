package com.pacholsky.juraj.music;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pacholsky.juraj.music.adapter.AlbumsInPerformerAdapter;
import com.pacholsky.juraj.music.adapter.PerformersInAlbumAdapter;
import com.pacholsky.juraj.music.adapter.SongInAlbumAdapter;
import com.pacholsky.juraj.music.client.RetrofitClientInstance;
import com.pacholsky.juraj.music.constants.MusicContants;
import com.pacholsky.juraj.music.dto.AlbumView;
import com.pacholsky.juraj.music.dto.PerformerView;
import com.pacholsky.juraj.music.dto.SongView;
import com.pacholsky.juraj.music.dto.inner.AlbumInPerformerView;
import com.pacholsky.juraj.music.dto.inner.PerformersInAlbumView;
import com.pacholsky.juraj.music.dto.inner.SongInAlbumView;
import com.pacholsky.juraj.music.intent.Intents;
import com.pacholsky.juraj.music.listener.RecyclerItemClickListener;
import com.pacholsky.juraj.music.service.AlbumViewService;
import com.pacholsky.juraj.music.service.SongViewService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {

    private TextView textView;
    private LinearLayout linearLayout;
    private int performerId;
    private int songId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_view);
        this.linearLayout = findViewById(R.id.songsInAlbumViewLinearLayout);

        AlbumViewService service = RetrofitClientInstance.getRetrofitInstance().create(AlbumViewService.class);

        Bundle bundle = getIntent().getExtras();
        int albumId = bundle.getInt(MusicContants.ALBUMID_EXTRA);

        Call<AlbumView> call = service.getById(albumId);
        call.enqueue(new Callback<AlbumView>() {
            @Override
            public void onResponse(Call<AlbumView> call, Response<AlbumView> response) {
                //TODO doriesit if
                setFieldsFromAlbumView(response.body());
                setImage(response.body().getPicture());
                setImageTwo(response.body().getPerformerPicture());
                setPerformerClick(response.body().getPerformerId());


                List<PerformersInAlbumView> performersInAlbumView = response.body().getSongs().stream().map(s -> s.getPerformerNames()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
                setDataInRecycler(performersInAlbumView);

                for (SongInAlbumView songInAlbumView: response.body().getSongs()
                     ) {
                    View view = getSongInAlbumView(songInAlbumView);

                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = Intents.getSongIntent(v.getContext() , songInAlbumView.getId());
                            startActivity(intent);
                        }
                    });
                    linearLayout.addView(view);
                }
            }

            @Override
            public void onFailure(Call<AlbumView> call, Throwable t) {

            }
        });


        AlbumView albumView = new AlbumView();
        TextView albumName = findViewById(R.id.albumName);
        albumName.setText(albumView.getName());

        TextView actorName = findViewById(R.id.actorName);
        actorName.setText(albumView.getPerformerName());
        actorName.setOnClickListener(v -> {
            Intent intent = Intents.getPerformerIntent(AlbumActivity.this , setPerformerClick(performerId));
            startActivity(intent);
        });


        Button button = findViewById(R.id.shufflePlayInAlbum);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(AlbumActivity.this , SongActivity.class);
            startActivity(intent);
        });

        button.setOnClickListener(v -> {
            Intent intent = new Intent(AlbumActivity.this, PlaylistActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("On resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("On stop");
    }


    private View getSongInAlbumView(SongInAlbumView songInAlbumViews) {


        final View songInAlbumView = getLayoutInflater().inflate(R.layout.item_song_in_album, null);

        TextView songName = songInAlbumView.findViewById(R.id.songNameInSongView);
        TextView songAlbum = songInAlbumView.findViewById(R.id.performerInSongView);

        songName.setText(songInAlbumViews.getName());
        songAlbum.setText(songInAlbumViews.getPerformerNames().stream().map(p -> p.getName()).distinct().collect(Collectors.toList()).toString());

        return songInAlbumView;
    }

    private void setFieldsFromAlbumView(AlbumView albumView){
        TextView nameAlbum = findViewById(R.id.albumName);
        nameAlbum.setText(albumView.getName());
        TextView actorName = findViewById(R.id.actorName);
        actorName.setText(albumView.getPerformerName());
        TextView actor = findViewById(R.id.albumByPerofmerName);
        actor.setText(albumView.getPerformerName());
//        ImageView imageView = findViewById(R.id.imageViewAlbum);
//        imageView.setImageURI(Uri.parse(albumView.getPicture()));
    }

    private void setDataInRecycler(List<PerformersInAlbumView> performersInAlbumView){

          PerformersInAlbumAdapter performersInAlbumAdapter = new PerformersInAlbumAdapter(performersInAlbumView);

        RecyclerView recyclerViewAlbums = findViewById(R.id.otherActors);
        recyclerViewAlbums.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerViewAlbums, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = Intents.getPerformerIntent(AlbumActivity.this , performersInAlbumAdapter.getOnPosition(position).getId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));



        recyclerViewAlbums.setAdapter(performersInAlbumAdapter);
        recyclerViewAlbums.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL , false));

    }

    private void setImage(String obrazok){

        String imageUri = obrazok;
        ImageView ivBasicImage = (ImageView) findViewById(R.id.imageViewAlbum);
        Picasso.with(this).load(imageUri).into(ivBasicImage);

    }

    private void setImageTwo(String obrazok){

        String imageUri = obrazok;
        ImageView ivBasicImage = (ImageView) findViewById(R.id.actorImage);
        Picasso.with(this).load(imageUri).into(ivBasicImage);

    }

    private int setPerformerClick(int performerId){
        this.performerId = performerId;
        return performerId;

    }




}
