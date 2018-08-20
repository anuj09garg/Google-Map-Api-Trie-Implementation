package com.quikr.service;

import org.magnos.trie.Trie;
import org.magnos.trie.TrieMatch;
import org.magnos.trie.Tries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quikr.request.MapPlacesRequest;
import com.quikr.response.MapPlacesResponse;
import com.quikr.response.Predictions;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapPlacesServiceImpl implements MapPlacesService {

    @Autowired
    MapApiUrl mapApiUrl;

    static Trie<String, Object> trie;

    static {
        trie = Tries.forStrings();
    }
    public List getPlace(MapPlacesRequest mapPlacesRequest) {
        List<String> reply = new ArrayList<>();
        MapPlacesResponse mapPlacesResponse;
        mapApiUrl = new MapApiUrl();

        if(trie.containsKey(mapPlacesRequest.getKeyword())==false){
            mapPlacesResponse = mapApiUrl.getJsonFromAPI(mapPlacesRequest);
            if(mapPlacesResponse.getStatus().equals("OK")){
                for (Predictions place:mapPlacesResponse.getPredictions()) {
                    trie.putIfAbsent(mapPlacesRequest.getKeyword(),place.getDescription().toLowerCase());
                }
            }
            else if(mapPlacesResponse.getStatus().equals("ZERO_RESULTS")){
                return reply;
            }
            else{
                try {
                    throw new Exception("Unable to fetch data from google maps");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        trie.get(mapPlacesRequest.getKeyword(),TrieMatch.STARTS_WITH);
        reply.addAll(trie.keySet(mapPlacesRequest.getKeyword(), TrieMatch.STARTS_WITH));
        return reply;
    }

    /*public static void main(String[] args) {
        MapPlacesServiceImpl mapPlacesService = new MapPlacesServiceImpl();
        MapPlacesRequest request = new MapPlacesRequest();
        request.setKeyword("manyata");
        mapPlacesService.getPlace(request);

        MapPlacesServiceImpl mapPlacesService1 = new MapPlacesServiceImpl();
        request.setKeyword("quikr");
        mapPlacesService1.getPlace(request);
    }*/
}
