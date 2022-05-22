package com.dell.sg.rainfall.controller;

import com.dell.sg.rainfall.model.Reading;
import com.dell.sg.rainfall.model.Response;
import com.dell.sg.rainfall.model.Station;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownHttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@RestController
public class RainfallController {

    @RequestMapping("/")
    public String retrieveRainfallStats() {

        String resourceUrl = System.getenv().
                getOrDefault("RESOURCE_URL", "https://api.data.gov.sg/v1/environment/rainfall");
        String stationName = System.getenv().
                getOrDefault("STATION_NAME", "Marina Gardens Drive");
        String stationID = null;
        RestTemplate restTemplate = new RestTemplate();

        System.out.println("Rainfall Service started");

        try {
            Response res = restTemplate.getForObject(resourceUrl, Response.class);

            DateFormat dateFormat = new SimpleDateFormat("HH:mm");
            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));
            String formattedTime = dateFormat.format(res.getItems().get(0).getTimestamp());

            for (Station station : res.getMetadata().getStations()) {
                if (stationName.equals(station.getName())) {
                    stationID = station.getId();
                    System.out.println("Station ID of " + stationName + " is " + stationID);
                    break;
                }
            }

            if (null != stationID) {
                for (Reading reading : res.getItems().get(0).getReadings()) {
                    if (stationID.equals(reading.getStation_id())) {
                        if (reading.getValue() > 0) {
                            return stationName + ", " + formattedTime + ", " +
                                    reading.getValue() + res.getMetadata().getReading_unit() + ", Raining";
                        } else {
                            return stationName + ", " + formattedTime + ", " +
                                    reading.getValue() + res.getMetadata().getReading_unit() + ", Not Raining";
                        }
                    }
                }
            } else {
                return "Station ID not found for " + stationName;
            }
        }catch(HttpClientErrorException clientErrorException){
            System.out.println(clientErrorException.toString());
            return "There seems to be 4XX client error";
        }
        catch(HttpServerErrorException serverErrorException){
            System.out.println(serverErrorException.toString());
            return "There seems to be 5XX server error";
        }
        catch (UnknownHttpStatusCodeException httpStatusCodeException){
            System.out.println(httpStatusCodeException.toString());
            return "There seems to be an unknown HTTP error";
        }
        return "Something went wrong !!!";
    }
}
