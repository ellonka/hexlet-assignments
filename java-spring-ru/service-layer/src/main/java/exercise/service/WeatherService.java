package exercise.service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import exercise.HttpClient;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public Map<String, String> getForecastToCity(String cityName) {
        String response = client.get("http://weather/api/v2/cities/" + cityName);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> result = new HashMap<>();
        try {
             result = objectMapper.readValue(
                    response,
                    new TypeReference<Map<String, String>>() { }
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
    // END
}
