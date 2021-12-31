package com.backbase.omdb.movie.mapper;

import com.backbase.omdb.movie.entity.MovieEntity;
import com.backbase.omdb.movie.model.Movie;
import com.backbase.omdb.movie.repository.MovieRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Movie Details Mapper
 *
 * @author NG
 * @version 0.0.1
 */
@Component
@RequiredArgsConstructor
public class MovieDetailsMapper {

    private final MovieRateRepository movieRateRepository;

    private final MovieMapper movieMapper;

    /**
     * Convert MovieEntity to Movie include username
     *
     * @param movieEntity
     * @param username
     * @return
     */
    public Movie convertToMovie(MovieEntity movieEntity, String username) {
        var movie = movieMapper.convertToModel(movieEntity);
        movieRateRepository
                .getMovieRateByMovieImdbIdAndUsername(movie.getImdbId(),
                        username)
                .ifPresent(movieRateEntity ->
                        movie.setRating(movieRateEntity.getRate()));
        return movie;
    }

    /**
     * Convert MovieEntityList to Movies List include username
     *
     * @param movieEntities
     * @param username
     * @return
     */
    public List<Movie> convertToMovies(List<MovieEntity> movieEntities, String username) {
        return movieEntities
                .stream().map(movieEntity -> convertToMovie(movieEntity, username))
                .collect(Collectors.toList());
    }


}
