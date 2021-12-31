package com.backbase.omdb.data;

import com.backbase.omdb.movie.entity.MovieEntity;
import com.backbase.omdb.movie.entity.MovieRateEntity;
import com.backbase.omdb.security.entity.UserEntity;

import java.util.List;


public class MovieEntityData {

    public static MovieEntity movieEntity = getMovieEntityInception();

    public static MovieEntity ratatouilleEntity = getMovieEntityRatatouille();


    private static MovieEntity getMovieEntityInception() {

        return MovieEntity
                .builder()
                .title("Inception")
                .year("2010")
                .released("16 Jul 2010")
                .genre("Action, Adventure, Sci-Fi")
                .director("Christopher Nolan")
                .actors("Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page")
                .language("English, Japanese, French")
                .awards("Won 4 Oscars. 157 wins & 220 nominations total")
                .imdbRating(8.8)
                .imdbVotes(2205517)
                .imdbId("tt1375666")
                .boxOffice(292576195)
                .build();
    }

    private static MovieEntity getMovieEntityRatatouille() {
        return MovieEntity
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
                .imdbRating(8.0)
                .imdbVotes(678387)
                .imdbId("tt0382932")
                .boxOffice(206445654)
                .rates(List.of(
                        MovieRateEntity
                                .builder()
                                .movie(MovieEntity.builder().id(5).build())
                                .user(UserEntity
                                        .builder()
                                        .id(1)
                                        .username("Jack")
                                        .build())
                                .rate("5")
                                .build()
                        , MovieRateEntity
                                .builder()
                                .movie(MovieEntity.builder().id(5).build())
                                .user(UserEntity
                                        .builder()
                                        .id(2)
                                        .username("David")
                                        .build())
                                .rate("3")
                                .build()))
                .build();
    }


}