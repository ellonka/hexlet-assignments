package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.CityNotFoundException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getForecast(@PathVariable long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException("City not found"));

        return weatherService.getForecastToCity(city.getName());
    }

    @GetMapping(path = "/search")
    public Iterable<Map<String, String>> getSearchedCities(@RequestParam(required = false) String name) {
        List<City> cities = name == null ? cityRepository.findAllByOrderByNameAsc()
                : cityRepository.findByNameStartingWithIgnoreCase(name);


        return cities.stream()
                .map(x -> {
                    Map<String, String> forecast = weatherService.getForecastToCity(x.getName());
                    Map<String, String> tempInCity = new LinkedHashMap<>();
                    tempInCity.put("temperature", forecast.get("temperature"));
                    tempInCity.put("name", forecast.get("name"));
                    return tempInCity;
                })
                .collect(Collectors.toList());
    }
    // END
}

