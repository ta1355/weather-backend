package Monitoring.Project.weather.weathers.weekend;


import Monitoring.Project.weather.weathers.KoreanCity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeekendService {


    @Value("${secretKeyKim}")
    String secretKey;

    public List<WeekendResponseDto.WeatherData> weekend(KoreanCity city) {
        RestClient restClient = RestClient.create();
        WeekendResponseDto body = restClient.get()
                .uri("https://api.openweathermap.org/data/2.5/forecast?q=" + city.getName() + "&appid=" + secretKey + "&units=metric")
                .retrieve()
                .body(WeekendResponseDto.class);

        // 데이터를 필터링하고 정렬합니다.
        List<WeekendResponseDto.WeatherData> weatherDataList = body.list().stream()
                .filter(weatherData -> weatherData.dt_txt().endsWith("06:00:00") || weatherData.dt_txt().endsWith("00:00:00"))
                .collect(Collectors.toList());

        // 첫 번째 데이터는 12:00:00, 나머지 데이터는 00:00:00 시각의 데이터를 선택합니다.
        List<WeekendResponseDto.WeatherData> result = new ArrayList<>();

        // 첫 번째 12:00:00 데이터 추가
        weatherDataList.stream()
                .filter(data -> data.dt_txt().endsWith("06:00:00"))
                .findFirst()
                .ifPresent(result::add);

        // 나머지 5개 00:00:00 데이터 추가
        result.addAll(weatherDataList.stream()
                .filter(data -> data.dt_txt().endsWith("00:00:00"))
                .limit(5)
                .collect(Collectors.toList()));

        return result;
    }
}