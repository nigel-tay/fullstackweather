package sg.edu.nus.iss.fullstackweather.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WeatherMongoRepository {
    
    @Autowired
    MongoTemplate mTemplate;
}
