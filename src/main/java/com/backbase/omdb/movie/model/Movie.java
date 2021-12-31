package com.backbase.omdb.movie.model;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Movie {

    private int id;
    private String title;
    private String year;
    private String released;
    private String genre;
    private String director;
    private String actors;
    private String language;
    private String awards;
    private String imdbRating;
    private int imdbVotes;
    private String imdbId;
    private int boxOffice;
    private byte[] image;
    private String username;
    private String rating;

}
