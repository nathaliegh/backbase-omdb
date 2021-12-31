package com.backbase.omdb.movie.repository;

import com.backbase.omdb.movie.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * MovieRepository
 *
 * @author NG
 * @version 0.0.1
 */
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {

    Optional<MovieEntity> findByImdbId(String imdbId);

}
