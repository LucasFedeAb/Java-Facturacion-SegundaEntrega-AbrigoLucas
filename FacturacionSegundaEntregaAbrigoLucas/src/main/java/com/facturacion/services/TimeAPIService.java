package com.facturacion.services;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.facturacion.models.entity.TimeApi;

@Service
public class TimeAPIService {
    		
    private final String BASE_URL = "http://worldtimeapi.org/api/timezone/America/Argentina/Mendoza";
    
    private RestTemplate restTemplate;

//    public String getCurrentDateTime() {
//        return restTemplate.getForObject(BASE_URL, String.class);
//    }
    
    @Autowired
    public TimeAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public TimeApi getCurrentDateTime() {
        try {
            ResponseEntity<TimeApi> response = restTemplate.exchange(BASE_URL, HttpMethod.GET, null, TimeApi.class);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener la fecha y hora actual: " + e.getMessage(), e);
        }
    }
    
    public String formatterDateTime (String dateTime) {
    	// Parsear la cadena de fecha y hora a un objeto ZonedDateTime
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime);
        
        // Crear un formateador de fecha y hora con el formato deseado ("DD-MM-YY HH:mm:ss")
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss");
        
        // Formatear la fecha y hora usando el formateador
        String formattedDateTime = zonedDateTime.format(formatter);
        
        
        return formattedDateTime;

    }
    
}