package com.dicoding.catalog.service;

import android.database.Cursor;
import android.os.AsyncTask;

import com.dicoding.catalog.db.DatabaseContract;
import com.dicoding.catalog.db.MappingHelper;
import com.dicoding.catalog.entitas.Movie;
import com.dicoding.catalog.repository.Repository;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class TvShowAsync extends AsyncTask<Void, Void, ArrayList<Movie>> {
    private final WeakReference<Repository> weakRepositoryHelper;
    private final WeakReference<LoadDataCallback> weakCallback;

    public TvShowAsync(Repository repository, LoadDataCallback callback){
        weakRepositoryHelper = new WeakReference<>(repository);
        weakCallback = new WeakReference<>(callback);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        weakCallback.get().preExecute();
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... voids){
        Cursor tvShowCursor = weakRepositoryHelper.get().queryByType(DatabaseContract.MOVIE_TABLE_NAME, "2");
        return MappingHelper.mapCursorToArrayList(tvShowCursor);
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> movies){
        super.onPostExecute(movies);
        weakCallback.get().postExecute(movies);

    }
}
