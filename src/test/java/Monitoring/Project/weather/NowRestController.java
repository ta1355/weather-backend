package Monitoring.Project.weather;

import org.springframework.web.bind.annotation.*;

@RestController
public class NowRestController {

    private final NowService nowService;

    public NowRestController(NowService nowService) {
        this.nowService = nowService;
    }

    // 지금 날씨 조회 (입력:Seoul,Busan,Daegu,Incheon,Gwangju,Daejeon,Ulsan,Sejong,Jeju)

    @GetMapping("/now/{nowCityEnum}")
    public NowResponseDto weather(@PathVariable String nowCityEnum) {
        KoreanCity koreanCity = KoreanCity.fromString(nowCityEnum);
//        System.out.println(koreanCity);
        return nowService.weatherNow(koreanCity);
    }

}