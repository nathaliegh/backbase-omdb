package com.backbase.omdb.client.service;

import com.backbase.omdb.client.dao.OMDBDaoImpl;
import com.backbase.omdb.client.dto.Example;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

 class OMDBServiceTest {

    @Mock
    private OMDBService omdbService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Find Movie by title and type ")
    void findMovieByTitleAndType() throws IOException {
        Gson gson = new Gson();
        var file = new ClassPathResource("data/Example2.json").getFile();
        var example = gson.fromJson(new FileReader(file), Example.class);
        doReturn(Optional.of(example)).when(omdbService).getOMDBByTitleAndType("Ratatouille", "movie");
        var movieResponse = omdbService.getOMDBByTitleAndType("Ratatouille", "movie");
        assertNotNull(movieResponse);
        assertEquals(example, movieResponse.get());
        assertEquals("Ratatouille", movieResponse.get().getTitle());
    }

    @Test
    @DisplayName("Find Movie not exist by title and type")
    void findMovieNotExistByTitleAndType() throws IOException {
        doReturn(Optional.empty()).when(omdbService).getOMDBByTitleAndType("green", "movie");
        var movieResponse = omdbService.getOMDBByTitleAndType("green", "movie");
        assertTrue(movieResponse.isEmpty());
    }

    @Test
    @DisplayName("Find Portal Movie by IMDBID ")
    void findImageByImdb() throws IOException {
        var file = new ClassPathResource("data/ratatouille.jpg").getFile();
        doReturn(Optional.of(file)).when(omdbService).getImageByIMDB("tt0382932");
        var portal = omdbService.getImageByIMDB("tt0382932");
        assertNotNull(portal);
        assertTrue(Optional.of(portal).isPresent());
    }

    @Test
    @DisplayName("Find Portal Movie not exist by IMDBID ")
    void findImageNotExistByImdb() throws IOException {
        doReturn(Optional.empty()).when(omdbService).getImageByIMDB("tt0382938");
        var portal = omdbService.getImageByIMDB("tt0382938");
        assertTrue(portal.isEmpty());
    }

}
