package com.dicoding.catalog.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dicoding.catalog.db.DatabaseContract;
import com.dicoding.catalog.db.DatabaseHelper;

public class ImplementRepo implements Repository {
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;

    private static ImplementRepo INSTANCE;

    private ImplementRepo(Context context){
        databaseHelper = new DatabaseHelper(context);
    }

    public static ImplementRepo getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new ImplementRepo(context);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void open(){
        database = databaseHelper.getWritableDatabase();
    }
    @Override
    public void close(){
        databaseHelper.close();
        if (database.isOpen())
            database.close();
    }

    @Override
    public Cursor queryByType(String table, String type){
        return database.query(
                table,
                null,
                DatabaseContract.DatabaseColumns.TYPE+ " = ?",
                new String[]{type},
                null,
                null,
                null,
                null
        );
    }

    @Override
    public Cursor queryById(String table, String id){
        return database.query(
                table,
                null,
                DatabaseContract.DatabaseColumns.ID + "=?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    @Override
    public long insert(String table, ContentValues values){
        return database.insert(table, null,values);
    }

    @Override
    public int deleteById(String table, String id){
        return database.delete(table, DatabaseContract.DatabaseColumns.ID + "=?", new String[]{id});
    }
}
