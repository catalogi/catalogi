package com.dicoding.catalog;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.catalog.service.DatabaseContract;
import com.dicoding.catalog.service.ImplementRepo;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar tulbar = findViewById(R.id.toolbar_detail);
        setSupportActionBar(tulbar);

        repo = ImplementRepo.getInstance(getApplicationContext());
        judul = findViewById(R.id.tv_title);
        tanggal = findViewById(R.id.tv_date);
        deskrip = findViewById(R.id.tv_description);
        gambar = findViewById(R.id.img_photo);
        movs = getIntent().getParcelableExtra(EXTRA_MOVIE);

        assert movs != null;
        cekFavorite(movs);
        DetailMovs(movs);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(movs.getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void DetailMovs(Movie movs) {
        judul.setText(movs.getTitle());
        tanggal.setText(movs.getDate());
        deskrip.setText(movs.getDescription());
        Glide.with(this)
                .load(movs.getPhoto())
                .apply(new RequestOptions().override(500))
                .into(gambar);

    }

    private void cekFavorite(Movie movs) {
        Cursor cur = repo.queryById(TABLE_MOVIE, String.valueOf(movs.getId()));
        suka = cur.getCount() != 0;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem items) {
        switch (items.getItemId()) {
            case R.id.make_favorite:
                suka = true;
                setFavorite(movs);
                supportInvalidateOptionsMenu();
                break;
            case R.id.make_unfavorite:
                suka = false;
                hapusFavorite(movs);
                supportInvalidateOptionsMenu();
        }
        return super.onOptionsItemSelected(items);
    }

    private void hapusFavorite(Movie movs) {
        movs.setIsFavorite(0);
        long rest = repo.deleteById(TABLE_MOVIE, String.valueOf(movs.getId()));
        if (rest > 0) {
            Toast.makeText(this, "Movie berhasil di hapus!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Movie gagal dihapus!", Toast.LENGTH_SHORT).show();
        }
    }

    private void setFavorite(Movie movs) {
        movs.setIsFavorite(1);

        ContentValues objek = new ContentValues();
        objek.put(DatabaseContract.DatabaseColumns.ID, movs.getId());
        objek.put(DatabaseContract.DatabaseColumns.TYPE, movs.getType());
        objek.put(DatabaseContract.DatabaseColumns.PHOTO, movs.getPhoto());
        objek.put(DatabaseContract.DatabaseColumns.TITLE, movs.getTitle());
        objek.put(DatabaseContract.DatabaseColumns.DATE, movs.getDate());
        objek.put(DatabaseContract.DatabaseColumns.DESCRIPTION, movs.getDescription());
        objek.put(DatabaseContract.DatabaseColumns.FAVORITE, movs.getIsFavorite());

        long rest = repo.insert(TABLE_MOVIE, objek);
        if (rest > 0) {
            Toast.makeText(this, "Berhasil Menambahkan  Movie kedalam Favorite!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Gagal menambahkan movie ke Favorite!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem sukaa = menu.findItem(R.id.make_favorite);
        MenuItem tdkSuka = menu.findItem(R.id.make_unfavorite);

        sukaa.setVisible(!suka);
        tdkSuka.setVisible(suka);
        return true;
    }
}
