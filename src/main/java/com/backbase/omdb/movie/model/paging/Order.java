package com.backbase.omdb.movie.model.paging;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Order {
    @ApiModelProperty(notes = "table column index")
    private Integer column;
    @ApiModelProperty(notes = "order direction ASC or DESC")
    private Direction dir;
}
