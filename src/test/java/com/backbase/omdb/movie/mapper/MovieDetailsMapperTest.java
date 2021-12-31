package com.backbase.omdb.movie.mapper;

import com.backbase.omdb.data.MovieData;
import com.backbase.omdb.data.MovieEntityData;
import com.backbase.omdb.movie.repository.MovieRateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

class MovieDetailsMapperTest {

    @Mock
    private MovieDetailsMapper movieDetailsMapper;

    @MockBean
    private MovieRateRepository movieRateRepository;

    @Spy
    private File portalImage = Mockito.spy(new ClassPathResource("data/inception.jpg").getFile());

    MovieDetailsMapperTest() throws IOException {
    }

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Convert to Movie Test")
    @Test
    void convertToMovie() throws IOException {
        var inceptionMovie = MovieData.movie;
        inceptionMovie.setImage(Files.readAllBytes(portalImage.toPath()));
        Mockito.doReturn(inceptionMovie).when(movieDetailsMapper)
                .convertToMovie(MovieEntityData.movieEntity, "david");
        var responseMovie = movieDetailsMapper
                .convertToMovie(MovieEntityData.movieEntity, "david");
        Assertions.assertEquals(inceptionMovie, responseMovie);
    }

    @DisplayName("Convert to Movies list")
    @Test
    void convertToMovies() throws IOException {
        var inceptionMovie = MovieData.movie;
        inceptionMovie.setImage(Files.readAllBytes(portalImage.toPath()));
        Mockito.doReturn(List.of(inceptionMovie)).when(movieDetailsMapper)
                .convertToMovies(List.of(MovieEntityData.movieEntity), "david");
        var responseMovie = movieDetailsMapper
                .convertToMovies(List.of(MovieEntityData.movieEntity), "david");
        Assertions.assertEquals(1, responseMovie.size());
        Assertions.assertEquals(List.of(inceptionMovie), responseMovie);
    }

}
