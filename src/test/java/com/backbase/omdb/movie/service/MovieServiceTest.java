package com.backbase.omdb.movie.service;

import com.backbase.omdb.client.dto.Example;
import com.backbase.omdb.client.service.OMDBService;
import com.backbase.omdb.data.ExampleData;
import com.backbase.omdb.data.MovieData;
import com.backbase.omdb.data.MovieEntityData;
import com.backbase.omdb.movie.entity.MovieEntity;
import com.backbase.omdb.movie.mapper.MovieDetailsMapper;
import com.backbase.omdb.movie.mapper.MovieMapper;
import com.backbase.omdb.movie.model.Movie;
import com.backbase.omdb.movie.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.backbase.omdb.data.MovieEntityData.ratatouilleEntity;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    @InjectMocks
    private MovieService movieService;

    @Mock
    private OMDBService omdbService;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private MovieDetailsMapper movieDetailsMapper;

    @Spy
    private MovieEntity movieEntity = MovieEntityData.movieEntity;

    @Spy
    private MovieEntity ratatouilleMovieEntity = MovieEntityData.ratatouilleEntity;

    @Spy
    private Example example = ExampleData.example;

    @Spy
    private Movie ratatouille = MovieData.ratatouille;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("save Movie ")
    void saveMovie() throws IOException {
        var expectedSaveMovie = movieEntity;
        expectedSaveMovie.setId(3);
        when(movieMapper.exampleToMovieEntity(example))
                .thenReturn(movieEntity);
        when(omdbService.getImageByIMDB(movieEntity.getImdbId()))
                .thenReturn(Optional.empty());
        when(omdbService.getOMDBByTitleAndType(movieEntity.getTitle(), "movie"))
                .thenReturn(Optional.of(example));
        when(movieRepository.save(movieEntity))
                .thenReturn(expectedSaveMovie);
        when(movieRepository.findByImdbId(movieEntity.getImdbId()))
                .thenReturn(Optional.of(expectedSaveMovie));

        var response = movieService.saveMovie(movieEntity.getTitle());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(3, response.getId());
        Assertions.assertEquals("Inception", response.getTitle());
        Assertions.assertEquals(expectedSaveMovie, response);
    }

    @Test
    @DisplayName("get all movies")
    void getAllMovies() throws IOException {
        movieEntity.setId(3);
        var moviesEntities = List.of(movieEntity, ratatouilleEntity);
        when(movieRepository.findAll())
                .thenReturn(moviesEntities);
        when(movieDetailsMapper.convertToMovies(moviesEntities, "Jack"))
                .thenReturn(List.of(ratatouille));

        var response = movieService.getMovies("Jack");
        Assertions.assertNotNull(response);
        Assertions.assertEquals(1, response.size());
        Assertions.assertEquals("Jack", response.get(0).getUsername());
        Assertions.assertNotEquals("Suzie",response.get(0).getUsername());
        Assertions.assertNotSame(7, response.get(0).getId());
        Assertions.assertFalse("James Bond".equals(response.get(0).getTitle()));
        Assertions.assertEquals("Ratatouille", response.get(0).getTitle());
    }


}
