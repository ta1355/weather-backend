package Monitoring.Project.weather.post;

import Monitoring.Project.weather.user.User;

public record PostDto(
        String title,
        String content,
        User user
) {

}
