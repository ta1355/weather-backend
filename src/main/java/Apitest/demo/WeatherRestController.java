package Apitest.demo;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class WeatherRestController {



    WeatherService weatherService;
    public WeatherRestController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/info")
    public List<WeatherResponseDto.WeatherData> info() {
        return weatherService.info();
    }


    @GetMapping("/infoForToday")
    public List<WeatherResponseDto2.WeatherData> info2() {
        return weatherService.info2();
    }


    @GetMapping("/Today_Time_Weather/{Date}")
    public Weather_Tool2 Data1 (@PathVariable Long Date){

        RestTemplate Data1 = new RestTemplate();
        String url1 = "https://apihub.kma.go.kr/api/typ01/url/kma_sfctm2.php?tm=";
        String url2 = "&stn=108&help=1&authKey=JbGQz-C3S5CxkM_gtwuQQw";
        String url3 = String.valueOf(Date);
        String full = url1 + url3 + url2;

        String response1 = Data1.getForObject(full, String.class);

        System.out.println(Date);

        Weather new1 = DataEdit1(response1);

        return new Weather_Tool2(new1.getTemperature());

    }


    private Weather DataEdit1(String response1){


        Weather DataWeather1 = new Weather();

        String[] lines = response1.split("\n");
        String dataLine = null;

        for (String line: lines){
            if(line.startsWith("2024")){
                dataLine = line.trim();
                break;
            }
        }
//        System.out.println( dataLine);


        if(dataLine!=null){
            String[] fields = dataLine.split("\\s+");


//            Double A = ((Double.parseDouble(fields[11 ])));

            DataWeather1.setTemperature(String.valueOf(fields[11]));

//            System.out.println(B);

//            DataWeather1.setTemperature(String.valueOf(Double.parseDouble(fields[11])));
//
//            DataWeather1.setWeather_status(Integer.parseInt(fields[24]));
        }
//      System.out.println( DataWeather1.getTemperature());
//      System.out.println( DataWeather1.getWeather_status());

        System.out.println(DataWeather1.getTemperature());

        return DataWeather1;
  }


  @GetMapping("/123")
    public WeatherInfo abc() {
        return WeatherFetcher.fetchWeather("Seoul,KR");
  }






}
