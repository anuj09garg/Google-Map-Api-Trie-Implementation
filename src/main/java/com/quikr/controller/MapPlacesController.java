package com.quikr.controller;

import com.quikr.request.MapPlacesRequest;
import com.quikr.service.MapPlacesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class MapPlacesController {

    @Autowired
    MapPlacesService mapPlacesService;

    static Logger logger = LoggerFactory.getLogger(MapPlacesController.class);

//    @RequestMapping(value = "placesApi",method = RequestMethod.POST)
    @PostMapping(value = "/placesApi")
    public List<String> getPlaces(@RequestBody MapPlacesRequest request){
//        List<String> placesList = new ArrayList<String>();
        return mapPlacesService.getPlace(request);
//        return placesList;
    }
}
