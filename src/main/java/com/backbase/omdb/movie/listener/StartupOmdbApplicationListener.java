package com.backbase.omdb.movie.listener;

import com.backbase.omdb.common.enums.Type;
import com.backbase.omdb.common.service.ConfigOmdbService;
import com.backbase.omdb.movie.service.MovieService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Iterator;
import java.util.Optional;

/**
 * Startup OMDB Application Listener
 *
 * @author NG
 * @version 0.0.1
 */
@Component
@Slf4j
@RequiredArgsConstructor
@PropertySource(ignoreResourceNotFound = true, value = "classpath:config.properties")
public class StartupOmdbApplicationListener implements
        ApplicationListener<ContextRefreshedEvent> {

    @Value("${movies.dataFile.url}")
    private String moviesDataFileURL;

    private final ConfigOmdbService configOmdbService;

    private final MovieService movieService;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var movieConfig = configOmdbService.getConfigByType(Type.MOVIE);
        if (!movieConfig.isEnable()) return;
        CSVReader reader = new CSVReader(new FileReader(ResourceUtils
                .getFile(moviesDataFileURL)));
        try {
            Optional.of(reader.readAll())
                    .ifPresent(list -> Optional.of(list.iterator())
                            .ifPresent(it -> {
                                try {
                                    saveMovieWinBestPicture(it);
                                    configOmdbService.disableConfig(movieConfig);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }));
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
    }

    private void saveMovieWinBestPicture(Iterator<String[]> it) throws IOException {
        while (it.hasNext()) {
            String[] movieInfo = it.next();
            if (movieInfo[1].equals("Best Picture") &&
                    movieInfo[4].equals("YES")) {
                movieService.saveMovie(movieInfo[2]);
            }
        }
    }
}
