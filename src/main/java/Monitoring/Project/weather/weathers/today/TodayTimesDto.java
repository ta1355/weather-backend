package Monitoring.Project.weather.weathers.today;

import java.util.List;

public record TodayTimesDto(
        List<WeatherData> list
) {
    public record WeatherData(
            String dt_txt,
            Main main,
            List<Weather> weather
    ) {}

    public record Main(
            double temp_min,
            double temp_max,
            int humidity
    ) {}

    public record Weather(
            String main,
            String icon
    ) {}
}