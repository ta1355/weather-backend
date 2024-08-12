package Monitoring.Project.weather.weathers.now;

import Monitoring.Project.weather.weathers.KoreanCity;
import org.springframework.stereotype.Service;

@Service
public class NowService {

    public NowResponseDto weatherNow(KoreanCity nowCityEnum) {
        // 도시 열거형 이름을 사용하여 API 호출
        return NowApi.fetchWeather(nowCityEnum);
    }
}
