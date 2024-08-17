package Monitoring.Project.weather.user;

import Monitoring.Project.weather.weathers.KoreanCity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class LikedList {
    @Enumerated(EnumType.STRING)
    private KoreanCity likedcity;

    // constructors
    public LikedList() {
    }

    public LikedList(KoreanCity likedcity) {
        this.likedcity = likedcity;
    }

    // getter
    public KoreanCity getLikedcity() {
        return likedcity;
    }


}
