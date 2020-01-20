package com.dicoding.catalog.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.catalog.R;
import com.dicoding.catalog.adapter.ListMovieAdapter;
import com.dicoding.catalog.entitas.Movie;
import com.dicoding.catalog.repository.Repository;
import com.dicoding.catalog.repository.ImplementRepo;
import com.dicoding.catalog.service.LoadDataCallback;
import com.dicoding.catalog.viewmodel.MovieView;

import java.util.ArrayList;

public class MovieFavoriteFragment extends Fragment implements LoadDataCallback {

    private ProgressBar progressBar;
    private Repository repository;
    private ListMovieAdapter listMovieAdapter;
    private MovieView MovieView;

    public MovieFavoriteFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);

        progressBar = view.findViewById(R.id.progressbar_favorite_movie);
        repository = ImplementRepo.getInstance(view.getContext());

        MovieView = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieView.class);
        RecyclerView Movie = view.findViewById(R.id.favorite_movie);
        Movie.setHasFixedSize();

    }

    @Override
    public void preExecute() {

    }

    @Override
    public void postExecute(ArrayList<Movie> movies) {

    }
}
