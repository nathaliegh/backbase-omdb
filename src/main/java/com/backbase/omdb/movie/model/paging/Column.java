package com.backbase.omdb.movie.model.paging;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Column {
    @ApiModelProperty(notes = "column name")
    private String data;
    private String name;
    @ApiModelProperty(notes = "searchable flag",value = "true")
    private Boolean searchable;
    @ApiModelProperty(notes = "orderable flag",value = "true")
    private Boolean orderable;
    private Search search;

    public Column(String data) {
        this.data = data;
    }
}
