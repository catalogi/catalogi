package com.dicoding.catalog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.catalog.service.ImplementRepo;
import com.dicoding.catalog.service.Repository;
import com.dicoding.catalog.design.MovFragment;
import com.dicoding.catalog.design.ProfFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;
    private Repository repos;
    private BottomNavigationView.OnNavigationItemSelectedListener navigas =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.nav_movie:
                            fragment = new MovFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.cont_lay,fragment, fragment.getClass().getSimpleName())
                                    .commit();
                            return true;
                        case R.id.nav_prof:
                            fragment = new ProfFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.cont_lay,fragment, fragment.getClass().getSimpleName())
                                    .commit();
                            return true;
                    }
                    return false;
                }
            };

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId()==R.id.change_setting){
            Intent iNtent = new Intent((Settings.ACTION_LOCALE_SETTINGS));
            startActivity(iNtent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.app_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repos = ImplementRepo.getInstance(getApplicationContext());
        repos.open();
        Toolbar tuul = findViewById(R.id.tool);
        setSupportActionBar(tuul);

        BottomNavigationView navigasi = findViewById(R.id.nav);
        navigasi.setOnNavigationItemSelectedListener(navigas);
        if (savedInstanceState == null){
            navigasi.setSelectedItemId(R.id.nav_movie);
        }
    }



    @Override
    protected void onDestroy(){
        super.onDestroy();
        repos.close();
    }
}
