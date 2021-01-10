package com.pacholsky.juraj.music;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pacholsky.juraj.music.client.RetrofitClientInstance;
import com.pacholsky.juraj.music.constants.MusicContants;
import com.pacholsky.juraj.music.dto.AlbumView;
import com.pacholsky.juraj.music.dto.SongView;
import com.pacholsky.juraj.music.service.SongViewService;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongActivity extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        SongViewService service = RetrofitClientInstance.getRetrofitInstance().create(SongViewService.class);

        Bundle bundle = getIntent().getExtras();
        int songId = bundle.getInt(MusicContants.SONGID_EXTRA);

        Call<SongView> call = service.getById(songId);
        call.enqueue(new Callback<SongView>() {
            @Override
            public void onResponse(Call<SongView> call, Response<SongView> response) {

                if (response.body() == null){
                    Toast.makeText(SongActivity.this, "Nejaka chyba", Toast.LENGTH_LONG).show();
                    return;
                }
                setFieldsFromSongView(response.body());
                setImage(response.body().getAlbumPicture());


            }

            @Override
            public void onFailure(Call<SongView> call, Throwable t) {
                Toast.makeText(SongActivity.this, "Nejaka chyba", Toast.LENGTH_LONG).show();
            }
        });


        ImageView down = findViewById(R.id.arrow);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final MediaPlayer mediaPlayer = MediaPlayer.create(this , R.raw.kalisomrad);
        ImageView play = (ImageView) this.findViewById(R.id.playSong);
        ImageView stop = (ImageView) this.findViewById(R.id.pauseSong);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });


    }

    @Override
    public void finish() {
        super.finish();
    }

    private void setFieldsFromSongView(SongView songView){
        TextView nameAlbum = findViewById(R.id.nameAlbum);
        nameAlbum.setText(songView.getAlbumName());

        TextView songName = findViewById(R.id.songName);
        songName.setText(songView.getName());

        ImageView stop = findViewById(R.id.pauseSong);
        ImageView play = findViewById(R.id.playSong);
        ImageView next = findViewById(R.id.nextSong);
        ImageView previous = findViewById(R.id.previousSong);


        TextView nameActor = findViewById(R.id.nameActor);
        String performersNames = String.join(", ", songView.getPerformerNames());
        nameActor.setText(performersNames);
    }

    private void setImage(String obrazok){

        String imageUri = obrazok;
        ImageView ivBasicImage = (ImageView) findViewById(R.id.imageSong);
        Picasso.with(this).load(imageUri).into(ivBasicImage);

    }


}
