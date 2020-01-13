package com.dicoding.catalog.ui;

import android.view.View;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.dicoding.catalog.adapter.ListMovieAdapter;
import com.dicoding.catalog.repository.Repository;
import com.dicoding.catalog.service.LoadDataCallback;

public class MovieFavoriteFragment extends Fragment implements LoadDataCallback {

    private ProgressBar progressBar;
    private Repository repository;
    private ListMovieAdapter listMovieAdapter;

    private MovieFavoriteFragment(){}

    @Override
    public View onCreateView
}
