package com.backbase.omdb.movie.repository;

import com.backbase.omdb.movie.entity.MovieRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * MovieRateRepository
 *
 * @author NG
 * @version 0.0.1
 */
public interface MovieRateRepository extends JpaRepository<MovieRateEntity, Integer> {

    @Query("select m from MovieRateEntity m where m.movie.imdbId =:imdbId and m.user.username =:username")
    Optional<MovieRateEntity> getMovieRateByMovieImdbIdAndUsername(@Param("imdbId") String imdbId , @Param("username") String username);

}
