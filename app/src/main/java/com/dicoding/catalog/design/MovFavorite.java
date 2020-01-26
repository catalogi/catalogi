package com.dicoding.catalog.design;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.catalog.R;
import com.dicoding.catalog.service.ListAdapter;
import com.dicoding.catalog.service.Movie;
import com.dicoding.catalog.service.Repository;
import com.dicoding.catalog.service.ImplementRepo;
import com.dicoding.catalog.service.LoadDataCallback;
import com.dicoding.catalog.service.MovieAsync;
import com.dicoding.catalog.viewmodel.MovieView;

import java.util.ArrayList;
import java.util.Objects;


public class MovFavorite extends Fragment implements LoadDataCallback {
    private ProgressBar progressBar;
    private ListAdapter listAdapter;
    private MovieView MovieView;

    public MovFavorite(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);

        progressBar = view.findViewById(R.id.progressbar_favorite_movies);
        Repository repository = ImplementRepo.getInstance(view.getContext());

        MovieView = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieView.class);
        RecyclerView Movie = view.findViewById(R.id.favorite_movie);
        Movie.setHasFixedSize(true);
        Movie.setLayoutManager(new LinearLayoutManager((view.getContext())));
        Movie.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));

        listAdapter = new ListAdapter(getActivity());
        listAdapter.notifyDataSetChanged();
        Movie.setAdapter(listAdapter);

        new MovieAsync(repository, this).execute();

    }

    @Override
    public  void onActivityCreated(@Nullable Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        MovieView.getListFavorite().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> mov) {
                if (mov != null)
                    listAdapter.setMovieData(mov);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void preExecute() {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showLoading(true);
            }
        });

    }


    @Override
    public void postExecute(ArrayList<Movie> mov) {
        showLoading(false);

        if (mov.size()>0){
            MovieView.setListFavorite(mov);
        }else{
            MovieView.setListFavorite(new ArrayList<Movie>());
            Toast.makeText(getActivity(), "Data Tidak Ditemukan!",Toast.LENGTH_SHORT).show();
        }
    }

    private void showLoading(Boolean state) {
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
