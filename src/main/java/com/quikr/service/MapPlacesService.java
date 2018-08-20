package com.quikr.service;

import com.quikr.request.MapPlacesRequest;

import java.util.List;

public interface MapPlacesService {
    List getPlace(MapPlacesRequest mapPlacesRequest);
}
