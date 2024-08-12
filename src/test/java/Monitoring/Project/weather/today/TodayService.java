package Monitoring.Project.weather.today;


import Monitoring.Project.weather.KoreanCity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodayService {

    public List<TodayTimesDto.WeatherData> today(KoreanCity city) {

        LocalDateTime Today_Date = LocalDateTime.now();

        LocalDate todayDate = Today_Date.toLocalDate();
        String new111 = String.valueOf(todayDate);

        RestClient restClient = RestClient.create();
        TodayTimesDto body = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/forecast?q=" + city.getName() + "&appid=23f9b7b6fba13a4a6cb9a7561f5629a8&units=metric")
                .retrieve()
                .body(TodayTimesDto.class);
        return body.list().stream()
                .filter(weatherData -> weatherData.dt_txt().startsWith(new111))
                .toList();
    }

}