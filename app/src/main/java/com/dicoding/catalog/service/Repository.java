package com.dicoding.catalog.service;

import android.content.ContentValues;
import android.database.Cursor;

public interface Repository {
    void open();
    void close();
    Cursor queryByType(String table, String type);
    Cursor queryById(String table, String id);
    long insert(String table, ContentValues values);
    int deleteById(String table, String id);
}
