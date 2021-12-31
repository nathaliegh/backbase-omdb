package com.backbase.omdb.client.dao;


import com.backbase.omdb.client.dto.Example;

import java.io.IOException;
import java.util.Optional;

/**
 * @author NG
 * @version 0.0.1
 * <p>
 * OMDB DAO
 */
public interface OMDBDao {

    Optional<Example> findByTitleAndType(String title, String type) throws IOException;

    Optional<byte[]> findImageByIMDB(String imdb) throws IOException;

}
