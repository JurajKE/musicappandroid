package com.pacholsky.juraj.music;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pacholsky.juraj.music.adapter.GenreViewAdapter;
import com.pacholsky.juraj.music.client.RetrofitClientInstance;
import com.pacholsky.juraj.music.dto.GenreView;
import com.pacholsky.juraj.music.service.GenreViewService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        GenreViewService genreViewService = RetrofitClientInstance.getRetrofitInstance().create(GenreViewService.class);
        Call<List<GenreView>> call = genreViewService.getAll();
        call.enqueue(new Callback<List<GenreView>>() {
            @Override
            public void onResponse(Call<List<GenreView>> call, Response<List<GenreView>> response) {
                //TODO zatial vracia len getAll ale nieje vyriesene este namapovanie z Genre do GenreView v server casti

                //TODO doriesit v server casti, to ze v genreView chcem namapovat GenreInGenreView, a neviem ako mam
                //prechadzat polom Listov vsetkych genre a jednotlive nasetovat

                setDataInRecycler(response.body());


            }

            @Override
            public void onFailure(Call<List<GenreView>> call, Throwable t) {

            }
        });


//        List<GenreInGenreView> genres = new ArrayList<>();
//        for (int i = 0; i< 10 ; i++) {
//            GenreInGenreView genre = new GenreInGenreView();
//            genre.setName(genre.getName() + i);
//            genres.add(genre);
//        }
//
//
//
//
//        GenreInGenreViewAdapter genreInGenreViewAdapter = new GenreInGenreViewAdapter(genres);
//
//        RecyclerView recyclerVieGenres = findViewById(R.id.genreListView);
//        recyclerVieGenres.setAdapter(genreInGenreViewAdapter);
//        recyclerVieGenres.setLayoutManager(new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL ,false));
//


    }

    private void setDataInRecycler(List<GenreView> genreViews){

        GenreViewAdapter genreViewAdapter = new GenreViewAdapter(genreViews);

        RecyclerView recyclerVieGenres = findViewById(R.id.genreListView);
        recyclerVieGenres.setAdapter(genreViewAdapter);
        recyclerVieGenres.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL, false));


    }


}
