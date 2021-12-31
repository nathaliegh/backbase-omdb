package com.backbase.omdb.movie.model.paging;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class PagingRequest {
    @ApiModelProperty(notes = "start index",value="0")
    private int start;
    @ApiModelProperty(notes = "length page",value="10")
    private int length;
    @ApiModelProperty(notes = "request number",value="0")
    private int draw;
    private List<Order> order;
    private List<Column> columns;
    private Search search;
}
