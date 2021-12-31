package com.backbase.omdb.movie.model;

import lombok.*;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MovieRating {
    private int id;
    private int movieId;
    private String username;
    private String rating;
}
