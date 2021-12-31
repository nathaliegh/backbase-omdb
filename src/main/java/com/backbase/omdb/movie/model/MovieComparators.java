package com.backbase.omdb.movie.model;

import com.backbase.omdb.movie.model.paging.Direction;
import com.backbase.omdb.movie.model.paging.MovieColumn;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * MovieComparators
 */
public final class MovieComparators {

    @EqualsAndHashCode
    @AllArgsConstructor
    @Getter
    static class Key {
        String name;
        Direction dir;
    }

    static Map<Key, Comparator<Movie>> map = new HashMap<>();

    static {
        map.put(new Key(MovieColumn.IMDB_ID.getValue(), Direction.asc), Comparator.comparing(Movie::getImdbId));
        map.put(new Key(MovieColumn.IMDB_ID.getValue(), Direction.desc), Comparator.comparing(Movie::getImdbId)
                .reversed());

        map.put(new Key(MovieColumn.TITLE.getValue(), Direction.asc), Comparator.comparing(Movie::getTitle));
        map.put(new Key(MovieColumn.TITLE.getValue(), Direction.desc), Comparator.comparing(Movie::getTitle)
                .reversed());

        map.put(new Key(MovieColumn.YEAR.getValue(), Direction.asc), Comparator.comparing(Movie::getYear));
        map.put(new Key(MovieColumn.YEAR.getValue(), Direction.desc), Comparator.comparing(Movie::getYear)
                .reversed());

        map.put(new Key(MovieColumn.GENRE.getValue(), Direction.asc), Comparator.comparing(Movie::getGenre));
        map.put(new Key(MovieColumn.GENRE.getValue(), Direction.desc), Comparator.comparing(Movie::getGenre)
                .reversed());

        map.put(new Key(MovieColumn.AWARDS.getValue(), Direction.asc), Comparator.comparing(Movie::getAwards));
        map.put(new Key(MovieColumn.AWARDS.getValue(), Direction.desc), Comparator.comparing(Movie::getAwards)
                .reversed());

        map.put(new Key(MovieColumn.IMDB_RATING.getValue(), Direction.asc), Comparator.comparing(Movie::getImdbRating));
        map.put(new Key(MovieColumn.IMDB_RATING.getValue(), Direction.desc), Comparator.comparing(Movie::getImdbRating)
                .reversed());

        map.put(new Key(MovieColumn.IMDB_VOTES.getValue(), Direction.asc), Comparator.comparing(Movie::getImdbVotes));
        map.put(new Key(MovieColumn.IMDB_VOTES.getValue(), Direction.desc), Comparator.comparing(Movie::getImdbVotes)
                .reversed());

        map.put(new Key(MovieColumn.BOX_OFFICE.getValue(), Direction.asc), Comparator.comparing(Movie::getBoxOffice));
        map.put(new Key(MovieColumn.BOX_OFFICE.getValue(), Direction.desc), Comparator.comparing(Movie::getBoxOffice)
                .reversed());
    }

    public static Comparator<Movie> getComparator(String name, Direction dir) {
        return map.get(new Key(name, dir));
    }

    private MovieComparators() {
    }

}
