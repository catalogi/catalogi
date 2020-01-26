package com.dicoding.catalog.service;

import java.util.ArrayList;
import com.dicoding.catalog.service.Movie;
public interface LoadDataCallback {
    void preExecute();
    void postExecute(ArrayList<Movie> mov);
}
