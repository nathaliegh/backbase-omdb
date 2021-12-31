package com.backbase.omdb.movie.model.paging;

public enum MovieColumn {

    IMAGE(0,"image"),
    IMDB_ID(1,"imdbID"),
    TITLE(2,"title"),
    YEAR(3,"year"),
    GENRE(4,"genre"),
    AWARDS(5,"awards"),
    IMDB_RATING(6,"imdbRating"),
    IMDB_VOTES(7,"imdbVotes"),
    BOX_OFFICE(8,"boxOffice"),
    RATING(9,"rating");


    private int index;
    private String value;

    MovieColumn(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public int getIndex() {
        return index;
    }
    public String getValue() {
        return value;
    }
}
