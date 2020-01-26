package com.dicoding.catalog.design;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.dicoding.catalog.R;
import com.dicoding.catalog.service.ListAdapter;
import com.dicoding.catalog.service.Movie;
import com.dicoding.catalog.viewmodel.MovieView;
import com.google.android.material.tabs.TabLayout;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovFragment extends Fragment {
    private ProgressBar progressBar;
    private ListAdapter listAdapter;
    private MovieView movieView;
    public MovFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view, saveInstanceState);

        progressBar = view.findViewById(R.id.progressbar_movie);

        String lang = Locale.getDefault().getLanguage();
        movieView = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MovieView.class);
        if (lang.equals("in")){
            movieView.setMovie("id");
        }else {
            movieView.setMovie(lang);
        }

        loadPage();

        RecyclerView movs = view.findViewById(R.id.movies);
        movs.setHasFixedSize(true);
        movs.setLayoutManager(new LinearLayoutManager(view.getContext()));
        movs.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        listAdapter = new ListAdapter(getActivity());
        listAdapter.notifyDataSetChanged();
        movs.setAdapter(listAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
        movieView.getMov().observe(getViewLifecycleOwner(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                listAdapter.setMovieData(movies);
            }
        });
    }

    private void loadPage() {
        progressBar.setVisibility(View.INVISIBLE);
    }


}
