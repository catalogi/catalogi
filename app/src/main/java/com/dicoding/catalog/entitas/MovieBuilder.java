package com.dicoding.catalog.entitas;

public class MovieBuilder {
    private final int id;
    private int type;
    private String photo;
    private final String title;
    private String date;
    private String description;
    private int isFavorite;

    public MovieBuilder(int id, String title){
        if (title == null){
            throw new IllegalArgumentException("Title can not be null");
        }

        this.id =id;
        this.title = title;
    }

    public MovieBuilder withType(int type){
        this.type = type;
        return this;
    }

    public MovieBuilder withPhoto(String photo){
        this.photo = photo;
        return this;
    }

    public MovieBuilder withDate(String date){
        this.date = date;
        return  this;
    }

    public MovieBuilder withDescription(String description){
        this.description = description;
        return this;
    }

    public MovieBuilder withFavorite(int isFavorite){
        this.isFavorite = isFavorite;
        return this;
    }

    public Movie build(){
        return new Movie(id, type, photo, title, date, description, isFavorite);
    }
}
