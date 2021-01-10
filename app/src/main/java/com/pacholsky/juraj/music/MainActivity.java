package com.pacholsky.juraj.music;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pacholsky.juraj.music.adapter.LudovkyInMainPageAdapter;
import com.pacholsky.juraj.music.adapter.PerformerInGenreViewAdapter;
import com.pacholsky.juraj.music.adapter.RockInMainPageAdapter;
import com.pacholsky.juraj.music.client.RetrofitClientInstance;
import com.pacholsky.juraj.music.dto.GenreView;
import com.pacholsky.juraj.music.dto.PerformerTrendsView;
import com.pacholsky.juraj.music.dto.SongView;
import com.pacholsky.juraj.music.dto.inner.AlbumInGenreView;
import com.pacholsky.juraj.music.dto.inner.AlbumInPerformerView;
import com.pacholsky.juraj.music.dto.inner.LudovkyGenreInMainPageView;
import com.pacholsky.juraj.music.dto.inner.PerformerInGenreView;
import com.pacholsky.juraj.music.dto.inner.RockAlbumInMainView;
import com.pacholsky.juraj.music.dto.inner.SongInGenreView;
import com.pacholsky.juraj.music.intent.Intents;
import com.pacholsky.juraj.music.listener.RecyclerItemClickListener;
import com.pacholsky.juraj.music.service.GenreViewService;
import com.pacholsky.juraj.music.service.PerformerTrendsViewService;
import com.pacholsky.juraj.music.service.SongViewService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        GenreViewService genreViewService3 = RetrofitClientInstance.getRetrofitInstance().create(GenreViewService.class);
        Call<GenreView> calls3 = genreViewService3.getById(1);
        calls3.enqueue(new Callback<GenreView>() {
            @Override
            public void onResponse(Call<GenreView> call, Response<GenreView> response) {

                List<PerformerInGenreView> performerInGenreViews = response.body().getPerformers().stream().distinct().collect(Collectors.toList());
                setDataInRecyclerPerformers(performerInGenreViews);

            }

            @Override
            public void onFailure(Call<GenreView> call, Throwable t) {

            }
        });


        GenreViewService genreViewService2 = RetrofitClientInstance.getRetrofitInstance().create(GenreViewService.class);
        Call<GenreView> calls2 = genreViewService2.getById(3);
        calls2.enqueue(new Callback<GenreView>() {
            @Override
            public void onResponse(Call<GenreView> call, Response<GenreView> response) {
                List<AlbumInGenreView> albumInGenreViews = response.body().getAlbums().stream().distinct().collect(Collectors.toList());
                setDataInRecyclerAlbums(albumInGenreViews);
            }

            @Override
            public void onFailure(Call<GenreView> call, Throwable t) {

            }
        });


        GenreViewService genreViewService = RetrofitClientInstance.getRetrofitInstance().create(GenreViewService.class);
        Call<GenreView> calls = genreViewService.getById(2);
        calls.enqueue(new Callback<GenreView>() {
            @Override
            public void onResponse(Call<GenreView> call, Response<GenreView> response) {
                List<SongInGenreView> songInGenreViewList = response.body().getSongs();
                setDataInRecycler(songInGenreViewList);



            }

            @Override
            public void onFailure(Call<GenreView> call, Throwable t) {

            }
        });



//        List<RockGenreInMainPageView> albumsAndPerformers = new ArrayList<>();
//        for (int i = 0; i< 10 ; i++) {
//            RockGenreInMainPageView albums = new RockGenreInMainPageView();
//            albums.setAlbumName(albums.getAlbumName() + i);
//            albums.setPerformerName(albums.getPerformerName() + i);
//            albumsAndPerformers.add(albums);
//        }
//
//        RockInMainPageAdapter rockInMainPageAdapter = new RockInMainPageAdapter(albumsAndPerformers);
//
//        RecyclerView recyclerViewAlbums = findViewById(R.id.rockRecyclerView);
//        recyclerViewAlbums.setAdapter(rockInMainPageAdapter);
//        recyclerViewAlbums.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));




//        List<LudovkyGenreInMainPageView> ludovkyGenreInMainPageViews = new ArrayList<>();
//        for (int i = 0; i< 10 ; i++) {
//            LudovkyGenreInMainPageView ludovky = new LudovkyGenreInMainPageView();
//            ludovky.setAlbumName(ludovky.getAlbumName() + i);
//            ludovky.setPerformerName(ludovky.getPerformerName() + i);
//            ludovkyGenreInMainPageViews.add(ludovky);
//        }
//
//        LudovkyInMainPageAdapter ludovkyInMainPageAdapter = new LudovkyInMainPageAdapter(ludovkyGenreInMainPageViews);
//
//        RecyclerView recyclerViewLudovky = findViewById(R.id.ludovkyRecyclerView);
//        recyclerViewLudovky.setAdapter(ludovkyInMainPageAdapter);
//        recyclerViewLudovky.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//

    }


    private void setDataInRecycler(List<SongInGenreView> songInGenreViews){

        RockInMainPageAdapter rockInMainPageAdapter = new RockInMainPageAdapter(songInGenreViews);

        RecyclerView recyclerView = findViewById(R.id.rockRecyclerView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = Intents.getSongIntent(MainActivity.this , rockInMainPageAdapter.getOnPosition(position).getId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        recyclerView.setAdapter(rockInMainPageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL , false));

    }

    private void setDataInRecyclerAlbums(List<AlbumInGenreView> albumInGenreViews){

        LudovkyInMainPageAdapter ludovkyInMainPageAdapter = new LudovkyInMainPageAdapter(albumInGenreViews);

        RecyclerView recyclerView = findViewById(R.id.ludovkyRecyclerView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = Intents.getAlbumIntent(MainActivity.this , ludovkyInMainPageAdapter.getOnPosition(position).getId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

        recyclerView.setAdapter(ludovkyInMainPageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL , false));

    }

    private void setDataInRecyclerPerformers(List<PerformerInGenreView> performersInGenreView){

        PerformerInGenreViewAdapter performerInGenreViewAdapter = new PerformerInGenreViewAdapter(performersInGenreView);

        RecyclerView recyclerView = findViewById(R.id.recentlyLikeRecyclerView);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = Intents.getPerformerIntent(MainActivity.this , performerInGenreViewAdapter.getOnPosition(position).getId());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        recyclerView.setAdapter(performerInGenreViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL , false));

    }

}
