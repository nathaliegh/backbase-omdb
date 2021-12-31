package com.backbase.omdb.data;

import com.backbase.omdb.movie.entity.MovieEntity;
import com.backbase.omdb.movie.entity.MovieRateEntity;
import com.backbase.omdb.security.entity.UserEntity;

public class MovieRateEntityData {

    public static MovieRateEntity movieRate = getMovieRateEntity();

    private static MovieRateEntity getMovieRateEntity(){
        return MovieRateEntity
                .builder()
                .movie(MovieEntity.builder().id(5).build())
                .user(UserEntity
                        .builder()
                        .id(5)
                        .username("Marie")
                        .build())
                .rate("2")
                .build();
    }
}
