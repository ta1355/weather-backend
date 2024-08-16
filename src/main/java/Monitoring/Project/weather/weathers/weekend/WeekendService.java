package Monitoring.Project.weather.weathers.weekend;


import Monitoring.Project.weather.weathers.KoreanCity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class WeekendService {


    @Value("${secretKeyKim}") String secretKey;

    public List<WeekendResponseDto.WeatherData> weekend(KoreanCity city) {
        RestClient restClient = RestClient.create();
        WeekendResponseDto body = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/forecast?q="+ city.getName() +"&appid=" + secretKey + "&units=metric")
                .retrieve()
                .body(WeekendResponseDto.class);
        return body.list().stream().filter(
                weatherData -> weatherData.dt_txt().endsWith("12:00:00")).toList();

    }

}