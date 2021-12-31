package com.backbase.omdb.movie.repository;

import com.backbase.omdb.data.MovieEntityData;
import com.backbase.omdb.movie.entity.MovieEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

 class MovieRepositoryTest {


    @Mock
    private MovieRepository movieRepository;

    @Spy
    private MovieEntity ratatouilleMovieEntity = MovieEntityData.ratatouilleEntity;

    @Spy
    private MovieEntity inceptionMovieEntity = MovieEntityData.movieEntity;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Find Movie By IMDBID ")
    void findByImdbId() {
        doReturn(Optional.of(ratatouilleMovieEntity)).when(movieRepository)
                .findByImdbId("tt0382932");
        var responseMovie = movieRepository.findByImdbId("tt0382932");
        assertTrue(responseMovie.isPresent());
        assertEquals(ratatouilleMovieEntity, responseMovie.get());
    }

    @Test
    @DisplayName("Save movie")
    void saveMovie() {
        var expectedResponse = inceptionMovieEntity;
        expectedResponse.setId(6);
        doReturn(expectedResponse).when(movieRepository).save(inceptionMovieEntity);
        var actualResponse = movieRepository.save(inceptionMovieEntity);
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        assertEquals(6, actualResponse.getId());
    }

}
