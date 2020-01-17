package com.dicoding.catalog.entitas;

import android.os.Parcel;
import android.os.Parcelable;

public final class Movie implements Parcelable {
    private int id;
    private int type;
    private String photo;
    private String title;
    private String date;
    private String description;
    private int isFavorite;

    public Movie(int id, int type, String photo, String title, String date, String description, int isFavorite){
        this.id = id;
        this.type = type;
        this.photo = photo;
        this.title = title;
        this.date = date;
        this.description = description;
        this.isFavorite = isFavorite;
    }

    public int getId(){return id;}

    public int getType() {
        return type;
    }

    public String getPhoto() {
        return photo;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public int describeContents(){return  0;}

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.id);
        dest.writeInt(this.type);
        dest.writeString(this.photo);
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.description);
        dest.writeInt(this.isFavorite);
    }

    protected Movie (Parcel in){
        this.id = in.readInt();
        this.type = in.readInt();
        this.photo = in.readString();
        this.title = in.readString();
        this.date = in.readString();
        this.description = in.readString();
        this.isFavorite = in.readInt();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>(){
        @Override
        public Movie createFromParcel (Parcel source){return new Movie(source); }

        @Override
        public Movie[] newArray(int size){return new Movie[size];}

    };
}