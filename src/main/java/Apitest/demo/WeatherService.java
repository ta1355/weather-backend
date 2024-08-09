package Apitest.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeatherService {


    public List<WeatherResponseDto.WeatherData> info() {
        RestClient restClient = RestClient.create();
        WeatherResponseDto body = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/forecast?q=Seoul&appid=23f9b7b6fba13a4a6cb9a7561f5629a8&units=metric")
                .retrieve()
                .body(WeatherResponseDto.class);
        return body.list().stream().filter(
                weatherData -> weatherData.dt_txt().endsWith("12:00:00")).toList();

    }

    public List<WeatherResponseDto2.WeatherData> info2() {

        LocalDateTime Today_Date = LocalDateTime.now();

        LocalDate todayDate = Today_Date.toLocalDate();
        String new111 = String.valueOf(todayDate);


//        String DateNew2 = String.format("%s-%s-%s", year, month, day);


        RestClient restClient = RestClient.create();
        WeatherResponseDto2 body = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/forecast?q=Seoul&appid=23f9b7b6fba13a4a6cb9a7561f5629a8&units=metric")
                .retrieve()
                .body(WeatherResponseDto2.class);
        return body.list().stream()
//                .filter(weatherData -> weatherData.dt_txt().startsWith("2024-08-09"))
                .filter(weatherData -> weatherData.dt_txt().startsWith(new111))
                .toList();

    }
}
