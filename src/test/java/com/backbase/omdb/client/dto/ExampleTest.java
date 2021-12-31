package com.backbase.omdb.client.dto;

import com.backbase.omdb.data.ExampleData;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExampleTest {

    @Mock
    private Example example;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test Example DTO ")
    void exampleDTOTest() throws IOException {

        Gson gson = new Gson();
        var file = new ClassPathResource("data/Example.json").getFile();
        var example = gson.fromJson(new FileReader(file), Example.class);
        var responseMovie = ExampleData.example;
        assertEquals(example, responseMovie);
        assertEquals(example.getTitle(), responseMovie.getTitle());
        assertEquals(example.getActors(), responseMovie.getActors());
        assertEquals(example.getAwards(), responseMovie.getAwards());
        assertEquals(example.getBoxOffice(), responseMovie.getBoxOffice());
        assertEquals(example.getCountry(), responseMovie.getCountry());
        assertEquals(example.getDirector(), responseMovie.getDirector());
        assertEquals(example.getDvd(), responseMovie.getDvd());

    }

    @Test
    @DisplayName("Test Example DTO empty")
    void exampleDTOEmptyTest() {
        var example = Example.builder().build();
        assertTrue(Optional.ofNullable(example.getTitle()).isEmpty());
    }


}
