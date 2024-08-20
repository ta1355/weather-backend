package Monitoring.Project.weather.post;

import Monitoring.Project.weather.user.User;

public record RequestPostDto(
        String title,
        String contents,
        User user
) {

}
