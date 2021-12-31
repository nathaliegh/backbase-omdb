package com.backbase.omdb.client.dao;


import com.backbase.omdb.client.dto.Example;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

 class OMDBDaoTest {


    @Mock
    private OMDBDaoImpl omdbDao;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Find Movie by title and type ")
    void findByTitleAndTypeTest() throws IOException {
        Gson gson = new Gson();
        var file = new ClassPathResource("data/Example.json").getFile();
        var example = gson.fromJson(new FileReader(file), Example.class);
        doReturn(Optional.of(example)).when(omdbDao).findByTitleAndType("Inception", "movie");
        var movieResponse = omdbDao.findByTitleAndType("Inception", "movie");
        assertNotNull(movieResponse);
        assertEquals(example, movieResponse.get());
        assertEquals("Inception", movieResponse.get().getTitle());
    }

    @Test
    @DisplayName("Find Movie not exist by title and type")
    void findMovieNotExistByTitleAndTypeTest() throws IOException {
        doReturn(Optional.empty()).when(omdbDao).findByTitleAndType("Baby Boss", "movie");
        var movieResponse = omdbDao.findByTitleAndType("Baby Boss", "movie");
        assertTrue(movieResponse.isEmpty());
    }

    @Test
    @DisplayName("Find Portal Movie by IMDBID ")
    void findImageByImdbTest() throws IOException {
        var file = new ClassPathResource("data/inception.jpg").getFile();
        doReturn(Optional.of(file)).when(omdbDao).findImageByIMDB("tt1375666");
        var portal = omdbDao.findImageByIMDB("tt1375666");
        assertNotNull(portal);
        assertTrue(Optional.of(portal).isPresent());
    }

    @Test
    @DisplayName("Find Portal Movie not exist by IMDBID ")
    void findImageNotExistByImdbTest() throws IOException {
        doReturn(Optional.empty()).when(omdbDao).findImageByIMDB("tt1375777");
        var portal = omdbDao.findImageByIMDB("tt1375777");
        assertTrue(portal.isEmpty());
    }


}

