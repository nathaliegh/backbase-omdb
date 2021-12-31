package com.backbase.omdb.movie.service;

import com.backbase.omdb.movie.entity.MovieRateEntity;
import com.backbase.omdb.movie.repository.MovieRateRepository;
import com.backbase.omdb.movie.repository.MovieRepository;
import com.backbase.omdb.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieRateService {

    private final MovieRateRepository movieRateRepository;

    private final MovieRepository movieRepository;

    private final UserRepository userRepository;

    @Transactional
    public void rateMovie(String imdbId, String rate, String username) {
        movieRepository.findByImdbId(imdbId)
                .ifPresent(movieEntity ->
                        userRepository.findFirstByUsername(username)
                                .ifPresent(userEntity ->
                                        saveMovieRate(
                                                MovieRateEntity
                                                        .builder()
                                                        .movie(movieEntity)
                                                        .rate(rate)
                                                        .user(userEntity)
                                                        .build())
                                ));
    }

    private void saveMovieRate(MovieRateEntity movieRateEntity) {
        movieRateRepository
                .getMovieRateByMovieImdbIdAndUsername(movieRateEntity.getMovie().getImdbId(),
                        movieRateEntity.getUser().getUsername())
                .ifPresentOrElse(movieRateEntity1 -> {
                    movieRateEntity1.setRate(movieRateEntity.getRate());
                    movieRateRepository.save(movieRateEntity1);
                }, () -> movieRateRepository.save(movieRateEntity));
    }

}
