package com.backbase.omdb.movie.service;

import com.backbase.omdb.movie.model.Movie;
import com.backbase.omdb.movie.model.MovieComparators;
import com.backbase.omdb.movie.model.paging.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Movie Paging
 *
 * @author NG
 * @version 0.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MoviePagingService {

    private final MovieService movieService;

    private static final Comparator<Movie> EMPTY_COMPARATOR = (e1, e2) -> 0;

    public PageArray<Movie> getMoviesArray(PagingRequest pagingRequest, String username) {
        setRequestPageColumns(pagingRequest);
        var moviePage = getMovies(pagingRequest, username);
        return PageArray
                .<Movie>builder()
                .recordsFiltered(moviePage.getRecordsFiltered())
                .recordsTotal(moviePage.getRecordsTotal())
                .draw(moviePage.getDraw())
                .data(moviePage.getData())
                .build();
    }

    private void setRequestPageColumns(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of(
                        MovieColumn.IMDB_ID.getValue(),
                        MovieColumn.TITLE.getValue(),
                        MovieColumn.YEAR.getValue(),
                        MovieColumn.GENRE.getValue(),
                        MovieColumn.AWARDS.getValue(),
                        MovieColumn.IMDB_RATING.getValue(),
                        MovieColumn.IMDB_VOTES.getValue(),
                        MovieColumn.BOX_OFFICE.getValue(),
                        MovieColumn.RATING.getValue()
                )
                .map(Column::new)
                .collect(Collectors.toList()));
    }

    private Page<Movie> getMovies(PagingRequest pagingRequest , String username) {
        return getPage(movieService.getMovies(username),
                pagingRequest);
    }

    private Page<Movie> getPage(List<Movie> movies, PagingRequest pagingRequest) {
        List<Movie> filtered = movies.stream()
                .sorted(sortMovies(pagingRequest))
                .filter(filterMovies(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .collect(Collectors.toList());

        long count = movies.stream()
                .filter(filterMovies(pagingRequest))
                .count();

        Page<Movie> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal(movies.size());
        page.setDraw(pagingRequest.getDraw());
        return page;
    }

    private Predicate<Movie> filterMovies(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null) return movie -> true;
        return movie -> movie.getTitle()
                .toLowerCase()
                .contains(pagingRequest
                        .getSearch()
                        .getValue().toLowerCase());
    }

    private Comparator<Movie> sortMovies(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) return EMPTY_COMPARATOR;

        try {
            var order = pagingRequest.getOrder()
                    .get(0);
            var column = pagingRequest.getColumns()
                    .get(order.getColumn());
            Comparator<Movie> comparator = MovieComparators.getComparator(column.getData(), order.getDir());
            return comparator == null ? EMPTY_COMPARATOR : comparator;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EMPTY_COMPARATOR;
    }

}
