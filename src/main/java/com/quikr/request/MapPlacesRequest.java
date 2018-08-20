package com.quikr.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MapPlacesRequest {
    String keyword;
    double latitude;
    double longitude;
    int radius;
    String type;
}
