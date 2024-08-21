package Monitoring.Project.weather.weathers.weekend;


import Monitoring.Project.weather.weathers.KoreanCity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeekendRestController {

    private final WeekendService weekendService;

    public WeekendRestController(WeekendService weekendService) {
        this.weekendService = weekendService;
    }

    @GetMapping("/weekend/{city}")
    public List<WeekendResponseDto.WeatherData> Seoul(@PathVariable String city) {
        KoreanCity koreanCity = KoreanCity.fromString(city);
        return weekendService.weekend(koreanCity);
    }


}
