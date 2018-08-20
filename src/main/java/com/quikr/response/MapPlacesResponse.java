package com.quikr.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapPlacesResponse {
    List<Predictions> predictions;
    String status;
}
