package com.dicoding.catalog.service;

import com.dicoding.catalog.entitas.Movie;

import java.util.ArrayList;

public interface LoadDataCallback {
    void preExecute();
    void postExecute(ArrayList<Movie> mov);
}
