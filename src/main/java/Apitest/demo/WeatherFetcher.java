package Apitest.demo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherFetcher {

    private static final String API_KEY = "01479e91a3a86b02769c9a0fb0b44a6f"; // OpenWeatherMap API 키를 입력하세요

    /**
     * OpenWeatherMap API를 호출하여 도시의 날씨 정보를 가져옵니다.
     *
     * @param city 조회할 도시 및 국가 코드 (예: "Seoul,KR")
     * @return 날씨 정보 객체
     */
    public static WeatherInfo fetchWeather(String city) {
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
        WeatherInfo weatherInfo = new WeatherInfo();

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

            weatherInfo.setTemperature(jsonResponse.getJSONObject("main").getDouble("temp"));
            weatherInfo.setMain(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("main"));
            weatherInfo.setDescription(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description"));
            weatherInfo.setIcon(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("icon"));
            weatherInfo.setHumidity(jsonResponse.getJSONObject("main").getInt("humidity"));
            weatherInfo.setWindSpeed(jsonResponse.getJSONObject("wind").getDouble("speed"));


            // 강수량 정보 추가
            JSONObject rain = jsonResponse.optJSONObject("rain");
            JSONObject snow = jsonResponse.optJSONObject("snow");

            weatherInfo.setRain(rain != null ? rain.optDouble("1h", 0.0) : 0.0);
            weatherInfo.setSnow(snow != null ? snow.optDouble("1h", 0.0) : 0.0);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherInfo;
    }
}