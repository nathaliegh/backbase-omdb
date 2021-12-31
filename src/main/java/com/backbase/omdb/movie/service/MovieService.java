package com.backbase.omdb.movie.service;

import com.backbase.omdb.client.dto.Example;
import com.backbase.omdb.client.service.OMDBService;
import com.backbase.omdb.movie.entity.MovieEntity;
import com.backbase.omdb.movie.mapper.MovieDetailsMapper;
import com.backbase.omdb.movie.mapper.MovieMapper;
import com.backbase.omdb.movie.model.Movie;
import com.backbase.omdb.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final OMDBService omdbService;

    private final MovieRepository movieRepository;

    private final MovieMapper movieMapper;

    private final MovieDetailsMapper movieDetailsMapper;

    @Transactional
    public MovieEntity saveMovie(String title) throws IOException {
        return getMovieEntityByTitle(title)
                .map(movieEntity ->
                        movieRepository.findByImdbId(movieEntity.getImdbId())
                                .map(
                                        movieEntity1 -> {
                                            movieEntity.setId(movieEntity1.getId());
                                            movieEntity.setCreatedAt(movieEntity1.getCreatedAt());
                                            return movieRepository.save(movieEntity);
                                        }
                                ).orElse(movieRepository.save(movieEntity))
                ).orElse(null);
    }

    private Optional<MovieEntity> getMovieEntityByTitle(String title) throws IOException {
        return omdbService.getOMDBByTitleAndType(title, "movie")
                .map(example ->
                {
                    try {
                        return getMovieWithPortal(example);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                });
    }


    private MovieEntity getMovieWithPortal(Example example) throws IOException {
        return omdbService.getImageByIMDB(example.getImdbID())
                .map(image -> {
                    var movieEntity = movieMapper.exampleToMovieEntity(example);
                    movieEntity.setImage(image);
                    return movieEntity;
                }).orElse(movieMapper.exampleToMovieEntity(example));
    }

    public List<Movie> getMovies(String username) {
        return getMovieEntities()
                .map(movies -> movieDetailsMapper.convertToMovies(movies, username))
                .orElse(new ArrayList<>());

    }

    public Optional<List<MovieEntity>> getMovieEntities() {
        return Optional.of(movieRepository.findAll());
    }

}
