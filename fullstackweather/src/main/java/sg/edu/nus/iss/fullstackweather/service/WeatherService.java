package sg.edu.nus.iss.fullstackweather.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.fullstackweather.respository.WeatherMongoRepository;
import sg.edu.nus.iss.fullstackweather.respository.WeatherRedisRepository;
import sg.edu.nus.iss.fullstackweather.respository.WeatherRepository;

@Service
public class WeatherService {
    
    @Autowired
    WeatherRepository wRepo;

    @Autowired
    WeatherMongoRepository wMongoRepo;

    @Autowired
    WeatherRedisRepository wRedisRepo;

    public ResponseEntity<String> getWeatherByCity(String city) {
        return wRepo.getWeatherByCity(city);
    }

    // Redis
    public ResponseEntity<String> handleCity(String city) {
        // First get cityArray, check if country exists
        if (wRedisRepo.getCityArray().isEmpty()) {
            // create new arrayList and push into redis and getweather
            ArrayList<String> newArrayList = new ArrayList<>();
            newArrayList.add(city.toLowerCase());
            wRedisRepo.setCityArray(newArrayList);
        }
        else if (wRedisRepo.getCityArray().isPresent()) {
            // if country is not in list
            List<String> cityArrayList = wRedisRepo.getCityArray().get();
            if (!cityArrayList.contains(city.toLowerCase())) {
                // push country into existing array and set into redisKey as new arraylist
                System.out.println(cityArrayList.toString());
                cityArrayList.add(city.toLowerCase());
                wRedisRepo.setCityArray(cityArrayList);
            }
        }
        return wRepo.getWeatherByCity(city.toLowerCase());
    }

    public List<String> getCityArray() {
        if (wRedisRepo.getCityArray().isEmpty()) {
            return null;
        }
        else {
            return wRedisRepo.getCityArray().get();
        }
    }
}
