package Apitest.demo;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherRestController {



    @GetMapping("/Today_Time_Weather/{Date}")
    public Weather_Tool2 Data1 (@PathVariable Long Date){

        RestTemplate Data1 = new RestTemplate();
        String url = "https://apihub.kma.go.kr/api/typ01/url/kma_sfctm2.php?tm=202408090900&stn=108&help=1&authKey=JbGQz-C3S5CxkM_gtwuQQw";

        String response1 = Data1.getForObject(url, String.class);

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


//            Double A = ((Double.parseDouble(fields[11])));

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





}
