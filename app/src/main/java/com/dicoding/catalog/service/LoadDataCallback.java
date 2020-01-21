package com.dicoding.catalog.service;

import java.util.ArrayList;

public interface LoadDataCallback {
    void preExecute();
    void postExecute(ArrayList<Movie> mov);
}
