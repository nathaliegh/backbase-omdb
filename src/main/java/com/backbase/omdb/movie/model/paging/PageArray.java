package com.backbase.omdb.movie.model.paging;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageArray<T> {
    private List<T> data;
    private int recordsFiltered;
    private int recordsTotal;
    private int draw;
}
