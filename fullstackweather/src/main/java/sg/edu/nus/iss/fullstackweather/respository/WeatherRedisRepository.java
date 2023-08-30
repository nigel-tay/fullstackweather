package sg.edu.nus.iss.fullstackweather.respository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherRedisRepository {

    private String redisKey = "CITY_LIST";
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void setCityArray(List<String> countryArrayList) {
        redisTemplate.opsForValue().set(redisKey, countryArrayList, Duration.ofHours(1));
    }

    public Optional<List<String>> getCityArray() {
        ArrayList<String> result = (ArrayList<String>)redisTemplate.opsForValue().get(redisKey);
        if (result == null) {
            return Optional.empty();
        }
        else {
            return Optional.of(result);
        }
    }
}
