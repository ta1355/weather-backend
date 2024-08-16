package Monitoring.Project.weather.weathers.today;

import Monitoring.Project.weather.weathers.KoreanCity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodayService {

    public List<TodayTimesDto.WeatherData> todayAndTomorrow(KoreanCity city) {

        ZoneId zoneId = ZoneId.of("Asia/Seoul");

        // 현재 날짜 및 내일 날짜를 가져옵니다. (UTC+9 기준)
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        LocalDate today = now.toLocalDate();
        LocalDate tomorrow = today.plusDays(1);


        String todayStr = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        String tomorrowStr = tomorrow.format(DateTimeFormatter.ISO_LOCAL_DATE);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


        RestClient restClient = RestClient.create();
        TodayTimesDto body = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/forecast?q=" + city.getName() + "&appid=23f9b7b6fba13a4a6cb9a7561f5629a8&units=metric")
                .retrieve()
                .body(TodayTimesDto.class);


        System.out.println("API Response: " + body);


        List<TodayTimesDto.WeatherData> filteredData = body.list().stream()
                .peek(weatherData -> System.out.println("Raw Weather Data: " + weatherData))
                .filter(weatherData -> {

                    ZonedDateTime weatherDateTime;
                    try {
                        LocalDateTime localDateTime = LocalDateTime.parse(weatherData.dt_txt(), formatter);
                        weatherDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("Asia/Seoul"));
                    } catch (Exception e) {
                        System.err.println("Error parsing weather data time: " + weatherData.dt_txt() + ", " + e.getMessage());
                        return false;
                    }
                    LocalDate weatherDate = weatherDateTime.toLocalDate();
                    System.out.println("Weather Date: " + weatherDate);
                    return weatherDate.equals(today) || weatherDate.equals(tomorrow);
                })
                .collect(Collectors.toList());

        // Log the filtered results
        System.out.println("Filtered Data: " + filteredData);

        return filteredData;
    }
}
