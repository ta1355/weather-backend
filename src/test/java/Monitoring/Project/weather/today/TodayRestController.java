package Monitoring.Project.weather.today;


import Monitoring.Project.weather.KoreanCity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodayRestController {

    private final TodayService todayService;

    public TodayRestController(TodayService todayService) {
        this.todayService = todayService;
    }

    @GetMapping("/Today/{city}")
    public List<TodayTimesDto.WeatherData> Seoul(@PathVariable String city) {
        KoreanCity koreanCity = KoreanCity.fromString(city);
        return todayService.today(koreanCity);
    }

}
