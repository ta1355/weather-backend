package Monitoring.Project.weather.weathers;

public enum KoreanCity {
    SEOUL("Seoul"),
    BUSAN("Busan"),
    DAEGU("Daegu"),
    INCHEON("Incheon"),
    GWANGJU("Gwangju"),
    DAEJEON("Daejeon"),
    ULSAN("Ulsan"),
    SEJONG("Sejong"),
    JEJU("Jeju");

    private final String name;

    KoreanCity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static KoreanCity fromString(String name) {
        for (KoreanCity city : KoreanCity.values()) {
            if (city.name.equalsIgnoreCase(name)) {
                return city;
            }
        }
        throw new IllegalArgumentException("Unknown city: " + name);
    }
}