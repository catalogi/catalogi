package com.dicoding.catalog.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbmovieapps";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s" +
                    "(%s INTEGER PRIMARY KEY," +
                    "%s TEXT NOT NULL," +
                    "%s INTEGER NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NUULL," +
                    "%s TEXT," +
                    "%s INTEGER NOT NULL)",
            DatabaseContract.MOVIE_TABLE_NAME,
            DatabaseContract.DatabaseColumns.ID,
            DatabaseContract.DatabaseColumns.PHOTO,
            DatabaseContract.DatabaseColumns.TYPE,
            DatabaseContract.DatabaseColumns.TITLE,
            DatabaseContract.DatabaseColumns.DATE,
            DatabaseContract.DatabaseColumns.DESCRIPTION,
            DatabaseContract.DatabaseColumns.FAVORITE
    );

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.MOVIE_TABLE_NAME);
    }
}
