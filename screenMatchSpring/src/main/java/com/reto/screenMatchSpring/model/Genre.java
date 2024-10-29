package com.reto.screenMatchSpring.model;

public enum Genre {
    ACTION("Action"),
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    ANIMATION("Animation"),
    DOCUMENTARY("Documentary");

    private String categoriaOmdb;

    //El constructor
    Genre(String categoriaOmdb){
        this.categoriaOmdb = categoriaOmdb;
    }

    public static Genre fromString(String text){
        for (Genre genre : Genre.values()){
            if (genre.categoriaOmdb.equalsIgnoreCase(text)){
                return genre;
            }
        }
        throw new IllegalArgumentException("Genre not found: " + text);
    }

}
