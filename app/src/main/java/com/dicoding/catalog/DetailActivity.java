package com.dicoding.catalog;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.catalog.service.DatabaseContract;
import com.dicoding.catalog.service.Movie;
import com.dicoding.catalog.service.Repository;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "extra_position";
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String TABLE_MOVIE = DatabaseContract.MOVIE_TABLE_NAME;

    private TextView judul;
    private TextView tanggal;
    private TextView deskrip;
    private ImageView gambar;

    private Movie movs;
    private Repository repo;
    private Boolean suka;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar =
    }
}
