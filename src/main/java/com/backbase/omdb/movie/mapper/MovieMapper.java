package com.backbase.omdb.movie.mapper;

import com.backbase.omdb.client.dto.Example;
import com.backbase.omdb.movie.entity.MovieEntity;
import com.backbase.omdb.movie.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

/**
 * MovieMapper
 *
 * @author NG
 * @version 0.0.1
 *
 * */
@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(source = "imdbID", target = "imdbId")
    @Mapping(source = "imdbVotes", target = "imdbVotes", qualifiedByName = "imdbVotes")
    @Mapping(source = "boxOffice", target = "boxOffice", qualifiedByName = "boxOffice")
    MovieEntity exampleToMovieEntity(Example example);

    Movie convertToModel(MovieEntity movieEntity);

    List<Movie> convertToModel(List<MovieEntity> movieEntity);

    @Named("imdbVotes")
    default int getImdbVotes(String imdbVotes) {
        return Optional.ofNullable(imdbVotes)
                .map(votes ->  votes.equals("N/A") ? -1 :
                        Integer.parseInt(votes.replaceAll("[^0-9]", "")))
                .orElse(-1);
    }

    @Named("boxOffice")
    default int getBoxOffice(String boxOffice) {
        return Optional.ofNullable(boxOffice)
                .map(box ->  box.equals("N/A") ? -1 :
                        Integer.parseInt(box.replaceAll("[^0-9]", "")))
                .orElse(-1);
    }

}
