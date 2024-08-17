package Monitoring.Project.weather.user;



    import Monitoring.Project.weather.weathers.KoreanCity;

    import java.util.List;

    public record LikeRequestDto(
            List<KoreanCity> cities
    ) {
    }
