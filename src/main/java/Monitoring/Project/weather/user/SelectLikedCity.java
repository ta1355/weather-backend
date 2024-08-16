package Monitoring.Project.weather.user;



import Monitoring.Project.weather.weathers.KoreanCity;
import Monitoring.Project.weather.weathers.now.NowApi;
import Monitoring.Project.weather.weathers.now.NowResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SelectLikedCity {

    // 메서드
    public List<NowResponseDto> addResponseCities(User user) {

        List<KoreanCity> likedList = user.getLikedList()
                .stream().map(l -> l.getLikedcity()).toList();
        List<KoreanCity> responseCities = new ArrayList<>();

        for (KoreanCity city : likedList) {
            if(likedList.contains(city)) {
                responseCities.add(city);
            }
        }

        return responseCities.stream().map(
                city -> NowApi.fetchWeather(city)
        ).toList();
    }

}
