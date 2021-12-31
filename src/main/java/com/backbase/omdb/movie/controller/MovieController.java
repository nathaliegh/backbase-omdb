package com.backbase.omdb.movie.controller;

import com.backbase.omdb.client.dto.Example;
import com.backbase.omdb.client.service.OMDBService;
import com.backbase.omdb.movie.model.Movie;
import com.backbase.omdb.movie.model.paging.PageArray;
import com.backbase.omdb.movie.model.paging.PagingRequest;
import com.backbase.omdb.movie.service.MoviePagingService;
import com.backbase.omdb.movie.service.MovieRateService;
import com.backbase.omdb.security.model.User;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

/**
 * Movie Controller
 *
 * @author NG
 * @version 0.0.1
 */
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
@Api(tags = "movies")
@Slf4j
public class MovieController {

    private final OMDBService omdbService;

    private final MoviePagingService moviePagingService;

    private final MovieRateService movieRateService;

    /**
     *
     * @param model
     * @return
     */
    @ApiIgnore
    @GetMapping
    public ModelAndView movies(Model model) {
        return new ModelAndView("movies");
    }

    /**
     * Get Movie By title
     *
     * @param title
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "This method to get movie by movie name.")
    @GetMapping(value = "/{title}")
    public ResponseEntity<Example> getMovieByTitle(@ApiParam(name = "title",
                                                            type = "String",
                                                            value = "movie name",
                                                            example = "titanic",
                                                            required = true)
                                                       @PathVariable String title) throws IOException {
        log.info(">>> MovieController >> getMovieByTitle >> title :"+title);
        var example = omdbService.getOMDBByTitleAndType(title, "movie");
        log.info("<<< MovieController << getMovieByTitle");
        return ResponseEntity.ok(example.orElse(null));
    }

    /**
     * Get Movie Image by IMDB
     *
     * @param imdb
     * @return
     * @throws IOException
     */
    @GetMapping(value = "/image/{imdb}")
    public ResponseEntity<byte[]> getMovieImageByIMDB(@PathVariable String imdb) throws IOException {
        log.info(">>> MovieController >> getMovieImageByIMDB >> imdb :"+imdb);
        var imageBytes = omdbService.getImageByIMDB(imdb);
        log.info("<<< MovieController << getMovieImageByIMDB");
        return ResponseEntity.ok(imageBytes.orElse(null));
    }

    /**
     * Fetch all movies
     *
     * @param pagingRequest
     * @param user
     * @return
     */
    @ApiOperation(
            value = "fetch all movies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the movies displayed"),
            @ApiResponse(code = 401, message = "not authorized !", response = AuthenticationException.class),
    })
    @PostMapping("/list")
    public PageArray<Movie> getAllMovies(@RequestBody PagingRequest pagingRequest,
                                         @AuthenticationPrincipal User user) {
        return moviePagingService.getMoviesArray(pagingRequest, user.getUsername());
    }

    /**
     * Update Movie by IMDB
     * @param imdbId
     * @param rate
     * @param user
     */
    @ApiOperation(
            value = "update rating movie by IMDBID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "the rating is done"),
            @ApiResponse(code = 401, message = "not authorized !", response = AuthenticationException.class),
    })
    @PostMapping("/updatebyImdbId")
    @ResponseBody
    public void updateMovieByImdb(@ApiParam(name = "imdbId",
                                            type = "String",
                                            value = "International Movie Database ID",
                                            example = "tt0120338",
                                            required = true)
                                  @RequestParam(name = "imdbId")
                                          String imdbId,
                                  @ApiParam(name = "rate",
                                          type = "String",
                                          value = "rating movie (1,2,3,4,5)",
                                          example = "1",
                                          required = true)
                                  @RequestParam(name = "rate") String rate,
                                  @AuthenticationPrincipal User user) {
        log.info(">>> MovieController >> updateMovieByImdb >> imdbId :"+imdbId+" rate :"+rate);
        movieRateService.rateMovie(imdbId, rate, user.getUsername());
        log.info("<<< MovieController << updateMovieByImdb");
    }


}
