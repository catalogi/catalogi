package com.dicoding.catalog.service;

import android.database.Cursor;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MovieAsync extends AsyncTask<Void, Void, ArrayList<Movie>> {
    private final WeakReference<Repository> weakRepositoryHelper;
    private final WeakReference<LoadDataCallback> weakCallback;

    public MovieAsync(Repository repository, LoadDataCallback callback){
        this.weakRepositoryHelper = new WeakReference<>(repository);
        this.weakCallback = new WeakReference<>(callback);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        weakCallback.get().preExecute();
    }

    @Override
    protected ArrayList<Movie> doInBackground(Void... voids){
        Cursor movieCursor = weakRepositoryHelper.get().queryByType(DatabaseContract.MOVIE_TABLE_NAME,"1");
        return MappingHelper.mapCursorToArrayList(movieCursor);
    }

    @Override
    protected void onPostExecute(ArrayList<Movie> mov){
        super.onPostExecute(mov);
        weakCallback.get().postExecute(mov);
    }
}
