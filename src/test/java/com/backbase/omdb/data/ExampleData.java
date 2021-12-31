package com.backbase.omdb.data;

import com.backbase.omdb.client.dto.Example;
import com.backbase.omdb.client.dto.Rating;

import java.util.List;

public class ExampleData {

    public static Example example = getMovieOMDBInception();


    private static Example getMovieOMDBInception() {
        return Example
                .builder()
                .title("Inception")
                .year("2010")
                .rated("PG-13")
                .released("16 Jul 2010")
                .runtime("148 min")
                .genre("Action, Adventure, Sci-Fi")
                .director("Christopher Nolan")
                .writer("Christopher Nolan")
                .actors("Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page")
                .plot("A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.")
                .language("English, Japanese, French")
                .country("United Kingdom, United States")
                .awards("Won 4 Oscars. 157 wins & 220 nominations total")
                .poster("https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg")
                .ratings(List.of
                        (Rating
                                        .builder()
                                        .source("Internet Movie Database")
                                        .value("8.8/10")
                                        .build(),
                                Rating.builder()
                                        .source("Rotten Tomatoes")
                                        .value("87%")
                                        .build(),
                                Rating.builder()
                                        .source("Metacritic")
                                        .value("74/100")
                                        .build()))
                .metascore("74")
                .imdbRating("8.8")
                .imdbVotes("2,205,517")
                .imdbID("tt1375666")
                .type("movie")
                .dvd("07 Dec 2010")
                .boxOffice("$292,576,195")
                .production("N/A")
                .website("N/A")
                .website("N/A")
                .response("True")
                .build();
    }


}
