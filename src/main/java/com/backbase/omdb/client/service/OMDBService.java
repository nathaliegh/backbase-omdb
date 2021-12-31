package com.backbase.omdb.client.service;

import com.backbase.omdb.client.dao.OMDBDaoImpl;
import com.backbase.omdb.client.dto.Example;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

/**
 *
 * @version 0.0.1
 * @author NG
 *
 * OMDB Service
 */
@Service
@RequiredArgsConstructor
public class OMDBService {

    private final OMDBDaoImpl omdbDaoImpl;

    public Optional<Example> getOMDBByTitleAndType(String title, String type) throws IOException {
        return omdbDaoImpl.findByTitleAndType(title, type);
    }

    public Optional<byte[]> getImageByIMDB(String imdb) throws IOException {
        return omdbDaoImpl.findImageByIMDB(imdb);
    }

}
