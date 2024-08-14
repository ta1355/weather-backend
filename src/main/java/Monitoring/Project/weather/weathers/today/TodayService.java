package Monitoring.Project.weather.weathers.today;


import Monitoring.Project.weather.weathers.KoreanCity;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodayService {

        // 해당 메서드가 'TodayTimesDto'를 반환한다고 가정
        public List<TodayTimesDto.WeatherData> todayAndTomorrow(KoreanCity city) {

            // 현재 날짜 및 내일 날짜를 가져옵니다.
            LocalDateTime now = LocalDateTime.now();
            LocalDate today = now.toLocalDate();
            LocalDate tomorrow = today.plusDays(1);

            // 날짜를 문자열로 변환합니다.
            String todayStr = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
            String tomorrowStr = tomorrow.format(DateTimeFormatter.ISO_LOCAL_DATE);

            // OpenWeatherMap API 호출
            RestClient restClient = RestClient.create();
            TodayTimesDto body = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/forecast?q=" + city.getName() + "&appid=23f9b7b6fba13a4a6cb9a7561f5629a8&units=metric")
                .retrieve()
                .body(TodayTimesDto.class);

            // 오늘과 내일의 데이터를 필터링합니다.
            return body.list().stream()
                .filter(weatherData -> {
                    String date = weatherData.dt_txt().split(" ")[0];
                    return date.equals(todayStr) || date.equals(tomorrowStr);
                })
                .collect(Collectors.toList());
        }

}