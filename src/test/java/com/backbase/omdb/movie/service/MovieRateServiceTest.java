package com.backbase.omdb.movie.service;

import com.backbase.omdb.data.MovieEntityData;
import com.backbase.omdb.data.MovieRateEntityData;
import com.backbase.omdb.movie.entity.MovieEntity;
import com.backbase.omdb.movie.entity.MovieRateEntity;
import com.backbase.omdb.movie.repository.MovieRateRepository;
import com.backbase.omdb.movie.repository.MovieRepository;
import com.backbase.omdb.security.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

class MovieRateServiceTest {

    @InjectMocks
    private MovieRateService movieRateService;

    @Mock
    private MovieRateRepository movieRateRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private UserRepository userRepository;

    @Spy
    private MovieEntity ratatouilleMovieEntity = MovieEntityData.ratatouilleEntity;

    @Spy
    private MovieRateEntity movieRate = MovieRateEntityData.movieRate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Rate Movie ")
    void rateMovie() {
        Mockito.doReturn(Optional.of(ratatouilleMovieEntity)).when(movieRepository)
                .findByImdbId("tt0382932");
        movieRateService.rateMovie("tt0382932", "1", "nancy");
    }


}
