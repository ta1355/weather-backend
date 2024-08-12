package Monitoring.Project.weather;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime observation_time;

    @Column(nullable = false)
    private Long area_code ;

    @Column(nullable = false)
    private String wind_speed;

    @Column(nullable = false)
    private String temperature;

    @Column(nullable = false)
    private  String humidity;

    @Column(nullable = false)
    private String precipitation;

    @Column(nullable = false)
    private int weather_status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getObservation_time() {
        return observation_time;
    }

    public void setObservation_time(LocalDateTime observation_time) {
        this.observation_time = observation_time;
    }

    public Long getArea_code() {
        return area_code;
    }

    public void setArea_code(Long area_code) {
        this.area_code = area_code;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public int getWeather_status() {
        return weather_status;
    }

    public void setWeather_status(int weather_status) {
        this.weather_status = weather_status;
    }
}