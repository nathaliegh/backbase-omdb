package com.backbase.omdb.data;


import com.backbase.omdb.movie.model.Movie;

public class MovieData {

    public static Movie movie = getMovieInception();

    public static Movie ratatouille = getMovieRatatouille();

    private static Movie getMovieInception() {
        return Movie
                .builder()
                .title("Inception")
                .year("2010")
                .released("16 Jul 2010")
                .genre("Action, Adventure, Sci-Fi")
                .director("Christopher Nolan")
                .actors("Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page")
                .language("English, Japanese, French")
                .awards("Won 4 Oscars. 157 wins & 220 nominations total")
                .imdbRating("8.8")
                .imdbVotes(2205517)
                .imdbId("tt1375666")
                .boxOffice(292576195)
                .build();
    }

    private static Movie getMovieRatatouille() {
        return Movie
                .builder()
                .id(5)
                .title("Ratatouille")
                .year("2007")
                .released("29 Jun 2007")
                .genre("Animation, Adventure, Comedy")
                .director("Brad Bird, Jan Pinkava")
                .actors("Brad Garrett, Lou Romano, Patton Oswalt")
                .language("English, French")
                .awards("Won 1 Oscar. 67 wins & 42 nominations total")
                .imdbRating("8.0")
                .imdbVotes(678387)
                .imdbId("tt0382932")
                .boxOffice(206445654)
                .username("Jack")
                .rating("5")
                .build();
    }

}
