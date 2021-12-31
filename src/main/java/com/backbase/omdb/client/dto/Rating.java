
package com.backbase.omdb.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

/**
 * Rating
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating {
    @SerializedName("Source")
    private String source;
    @SerializedName("Value")
    private String value;
}
