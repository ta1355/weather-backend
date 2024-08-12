package Monitoring.Project.weather.weathers.now;

//dto
public class NowResponseDto {

    private String name;
    private double temperature;
    private String main;
    private String description;
    private String icon;
    private int humidity;
    private double windSpeed;
    private double rain;
    private double snow;

    // Getters and setters

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    public double getSnow() {
        return snow;
    }

    public void setSnow(double snow) {
        this.snow = snow;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "temperature=" + temperature +
                ", description='" + description + '\'' +
                ", humidity=" + humidity +
                ", windSpeed=" + windSpeed +
                ", rain=" + rain +
                ", snow=" + snow +
                ", main='" + main + '\'' +
                ", icon='" + icon + '\'' +
                ", name='" + name + '\'' +  // 추가된 필드
                '}';
    }
}
