package sg.edu.nus.iss.fullstackweather.respository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class WeatherRepository {
    
    private RestTemplate rTemplate = new RestTemplate();

    @Value("${weather.api.key}")
    private String WEATHER_API_KEY;

    private String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    

    public ResponseEntity<String> getWeatherByCity(String city) {
        String url = UriComponentsBuilder.fromUriString(WEATHER_URL)
        .queryParam("q", city)
        .queryParam("appid", WEATHER_API_KEY)
        .toUriString();

        ResponseEntity<String> response = rTemplate.getForEntity(url, String.class);
        return response;
    }
}
