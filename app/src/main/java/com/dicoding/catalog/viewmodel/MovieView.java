package com.dicoding.catalog.viewmodel;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.catalog.BuildConfig;
import com.dicoding.catalog.service.Movie;
import com.dicoding.catalog.service.MovieBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class MovieView extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> listFavorites = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Movie>> listMovs = new MutableLiveData<>();

    private static final int TYPE_MOVIE = 1;

    public void setMovie(String Language) {
        String url = BuildConfig.MovieURL + Language;
        final ArrayList<Movie> itemList = new ArrayList<>();
        AsyncHttpClient user = new AsyncHttpClient();
        user.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    parMov(itemList, responseBody);
                } catch (Exception e) {
                    Log.d("Exception", (e.getMessage()));
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("OnFailure", (error.getMessage()));
            }
        });
    }

    private void parMov(ArrayList<Movie> itemList, byte[] responseBody) throws JSONException {
        String res = new String(responseBody);
        JSONObject response = new JSONObject(res);
        JSONArray listRes = response.getJSONArray("results");
        for (int j = 0; j < listRes.length(); j++) {
            JSONObject items = listRes.getJSONObject(j);
            Movie mov = getMovie(items);
            itemList.add(mov);
        }
    listMovs.postValue(itemList);
    }

    private Movie getMovie(JSONObject mItems) throws JSONException {
        String tempPoster = "https://image.tmdb.org/t/p/w600" + mItems.getString("poster_path");
        return new MovieBuilder(mItems.getInt("id"), mItems.getString("original_title"))
                .withDate(mItems.getString("release_date"))
                .withDescription(mItems.getString("overview"))
                .withPhoto(tempPoster)
                .withType(TYPE_MOVIE)
                .withFavorite(0)
                .build();

    }

    public LiveData<ArrayList<Movie>> getMov() {
        return listMovs;
    }

    public LiveData<ArrayList<Movie>> getListFavorite() {
        return listFavorites;
    }

    public void setListFavorite(ArrayList<Movie> listFavorite) {
        this.listFavorites.postValue(listFavorite);
    }
}
