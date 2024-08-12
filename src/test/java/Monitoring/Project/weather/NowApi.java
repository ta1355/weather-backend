package Monitoring.Project.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class NowApi {


    /**
     * OpenWeatherMap API를 호출하여 도시의 날씨 정보를 가져옵니다.
     *
     * @param nowCityEnum 조회할 도시 열거형 객체
     * @return 날씨 정보 객체
     */
    public static NowResponseDto fetchWeather(KoreanCity nowCityEnum) {
        // 도시 코드와 국가 코드를 도시 이름으로 변경하여 URL 생성
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + nowCityEnum.getName() + ",KR&appid=" + API_KEY + "&units=metric";
        NowResponseDto nowResponseDto = new NowResponseDto();

        try {
            // API 호출
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 응답 읽기
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // JSON 응답 파싱
            JSONObject jsonResponse = new JSONObject(response.toString());

            nowResponseDto.setTemperature(jsonResponse.getJSONObject("main").getDouble("temp"));
            nowResponseDto.setMain(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("main"));
            nowResponseDto.setDescription(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description"));
            nowResponseDto.setIcon(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("icon"));
            nowResponseDto.setHumidity(jsonResponse.getJSONObject("main").getInt("humidity"));
            nowResponseDto.setWindSpeed(jsonResponse.getJSONObject("wind").getDouble("speed"));
            nowResponseDto.setName(jsonResponse.getString("name"));

            // 강수량 정보 추가
            JSONObject rain = jsonResponse.optJSONObject("rain");
            JSONObject snow = jsonResponse.optJSONObject("snow");

            nowResponseDto.setRain(rain != null ? rain.optDouble("1h", 0.0) : 0.0);
            nowResponseDto.setSnow(snow != null ? snow.optDouble("1h", 0.0) : 0.0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nowResponseDto;
    }

    private static final String API_KEY = "01479e91a3a86b02769c9a0fb0b44a6f"; // OpenWeatherMap API 키를 입력하세요
}