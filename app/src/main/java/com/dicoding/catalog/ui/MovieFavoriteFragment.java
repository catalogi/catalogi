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

import com.dicoding.catalog.R;
import com.dicoding.catalog.adapter.ListMovieAdapter;
import com.dicoding.catalog.entity.Movie;
import com.dicoding.catalog.repository.Repository;
import com.dicoding.catalog.repository.RepositoryImpl;
import com.dicoding.catalog.service.LoadDataCallback;

public class MovieFavoriteFragment extends Fragment implements LoadDataCallback {

    private ProgressBar progressBar;
    private Repository repository;
    private ListMovieAdapter listMovieAdapter;

    private MovieFavoriteFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_movie_favorite);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);

        progressBar = view.findViewById(R.id.progressbar_favorite_movie);
        repository = RepositoryImpl.getInstance(view.getContext());

        movieViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(movieViewModel.class);


    }

}
