package com.quikr.service;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import com.quikr.request.MapPlacesRequest;
import com.quikr.response.MapPlacesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.magnos.trie.Trie;
import org.magnos.trie.Tries;

@Service
public class MapApiUrl {

    public MapPlacesResponse getJsonFromAPI(MapPlacesRequest mapPlacesRequest) {
        Trie<String, Integer> t = Tries.forInsensitiveStrings();
        try {
            HttpResponse<String> jsonResponse = Unirest.get("https://maps.googleapis.com/maps/api/place/autocomplete/json")
                    .header("accept", "application/json")
                    .queryString("input", mapPlacesRequest.getKeyword())
                    .queryString("key", "add your api key here")
                    .queryString("session_token",1234567890)

//                    .field("parameter", "value")
//                    .field("foo", "bar")
                    .asString();
            ObjectMapper mapper = new ObjectMapper();
            try {
                Gson gson = new Gson();
                MapPlacesResponse mapPlacesResponse;// = mapper.readValue(jsonResponse.getBody(), MapPlacesResponse.class);
                mapPlacesResponse = gson.fromJson(jsonResponse.getBody(),MapPlacesResponse.class);
                return mapPlacesResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(jsonResponse);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
//        System.out.println(jsonResponse);
//        ClientConfig config = new ClientConfig();
//        Client client = ClientBuilder.newClient(config);
//
//        WebTarget target = client.target(getBaseURI());
//
//        // Get JSON for application
////        json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=YOUR_API_KEY
//        String location;
//        String radius;
//        String type;
//        String query;
//        final String apiKey = "key=AIzaSyDd5ETQMYmw01cjUSxLeWFDRmSDHd8JqC4";
//
////        if(mapPlacesRequest.getLatitude()!=0){
////            location = "location=" + mapPlacesRequest.getLatitude() + "," + mapPlacesRequest.getLongitude()+"&";
////        }
////        if(mapPlacesRequest.getRadius()!=0){
////            radius = "radius=" + mapPlacesRequest.getRadius() +"&";
////        }
////        if(mapPlacesRequest.getType()!=null){
////            type = "type=" + mapPlacesRequest.getType() + "&";
////        }
////        if(mapPlacesRequest.getKeyword()!=null){
//            query = "query="+mapPlacesRequest.getKeyword()+"&";
////        }
//        PlaceDetailsRequest placeDetailsRequestJson = target./*path(query).path(apiKey).*/request()
//                .accept(MediaType.APPLICATION_JSON).get(PlaceDetailsRequest.class);
//
//        System.out.println(placeDetailsRequestJson);
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            return mapper.readValue(jsonResponse,PlaceDetailsRequest.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return null;
    }

//    private static URI getBaseURI() {
//        return UriBuilder.fromUri("https://maps.googleapis.com/maps/api/place/textsearch").build();
//        return UriBuilder.fromUri("https://maps.googleapis.com/maps/api/place/textsearch/json?query=anuj&location=42.3675294,-71.186966&radius=10000&key=AIzaSyDd5ETQMYmw01cjUSxLeWFDRmSDHd8JqC4").build();
//
//    }

    /*public static void main(String[] args) {
        MapApiUrl mapApiUrl = new MapApiUrl();
        MapPlacesRequest request = new MapPlacesRequest();
        request.setKeyword("quikr");
        mapApiUrl.getJsonFromAPI(request);
    }*/

}
