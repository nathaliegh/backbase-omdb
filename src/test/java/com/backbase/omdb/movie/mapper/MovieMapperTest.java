package com.backbase.omdb.movie.mapper;

import com.backbase.omdb.data.ExampleData;
import com.backbase.omdb.data.MovieData;
import com.backbase.omdb.data.MovieEntityData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

class MovieMapperTest {

    @Mock
    private MovieMapper movieMapper;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @DisplayName("Convert Example to MovieEntity")
    @Test
    void convertExampleToMovieEntity() {
        Mockito.doReturn(MovieEntityData.movieEntity).when(movieMapper)
                .exampleToMovieEntity(ExampleData.example);
        var responseMovie = movieMapper.exampleToMovieEntity(ExampleData.example);
        Assertions.assertEquals(MovieEntityData.movieEntity, responseMovie);
    }

    @DisplayName("Convert MovieEntity to Movie")
    @Test
    void convertToModel() {
        Mockito.doReturn(MovieData.movie).when(movieMapper)
                .convertToModel(MovieEntityData.movieEntity);
        var responseMovie = movieMapper.convertToModel(MovieEntityData.movieEntity);
        Assertions.assertEquals(MovieData.movie, responseMovie);
    }

    @DisplayName("Convert List of MovieEntity to List of Movie")
    @Test
    void convertToModelList() {
        Mockito.doReturn(List.of(MovieData.movie)).when(movieMapper)
                .convertToModel(List.of(MovieEntityData.movieEntity));
        var responseMovie = movieMapper.convertToModel(List.of(MovieEntityData.movieEntity));
        Assertions.assertEquals(1, responseMovie.size());
        Assertions.assertEquals(List.of(MovieData.movie), responseMovie);
    }


}
