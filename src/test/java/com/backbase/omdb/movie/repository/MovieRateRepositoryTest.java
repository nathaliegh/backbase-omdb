package com.backbase.omdb.movie.repository;

import com.backbase.omdb.data.MovieEntityData;
import com.backbase.omdb.data.MovieRateEntityData;
import com.backbase.omdb.movie.entity.MovieEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

class MovieRateRepositoryTest {

    @Mock
    private MovieRateRepository movieRateRepository;

    @Spy
    private MovieEntity ratatouilleMovieEntity = MovieEntityData.ratatouilleEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Get Movie rate by IMDB ID and username")
    void getMovieRateByMovieImdbIdAndUsername() {
        var movieRate = ratatouilleMovieEntity
                .getRates()
                .stream()
                .filter(movieRateEntity -> movieRateEntity.getUser().getUsername().equals("Jack"))
                .findFirst();
        when(movieRateRepository
                .getMovieRateByMovieImdbIdAndUsername(ratatouilleMovieEntity.getImdbId(), "Jack"))
                .thenReturn(movieRate);
        var responseMovie = movieRateRepository.getMovieRateByMovieImdbIdAndUsername(ratatouilleMovieEntity.getImdbId(), "Jack");
        assertTrue(responseMovie.isPresent());
        assertEquals(movieRate.get(), responseMovie.get());
    }


    @Test
    @DisplayName("Rating movie")
    void rateMovie() {
        var rateMovie = MovieRateEntityData.movieRate;
        var expectedResponse = rateMovie;
        expectedResponse.setId(5);
        doReturn(expectedResponse).when(movieRateRepository).save(rateMovie);
        var actualResponse = movieRateRepository.save(rateMovie);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
    }


}
