package sg.edu.nus.iss.fullstackweather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.edu.nus.iss.fullstackweather.service.WeatherService;

@Controller
public class WeatherController {

    @Autowired
    WeatherService wService;

    @GetMapping("/citylist")
    @ResponseBody
    public ResponseEntity<String> getCityList() {
        if (wService.getCityArray() == null) {
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(wService.getCityArray().toString());
    }

    @GetMapping("/weather")
    @ResponseBody
    public ResponseEntity<String> getWeatherByCity(@RequestParam String city) {
        return wService.getWeatherByCity(city);
    }

    @GetMapping("/weather/{city}")
    @ResponseBody
    public ResponseEntity<String> getCityArray(@PathVariable String city) {
        return wService.handleCity(city);
    }
    
}
