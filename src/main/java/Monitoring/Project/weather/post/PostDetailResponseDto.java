package Monitoring.Project.weather.post;

import Monitoring.Project.weather.user.User;

import java.time.LocalDateTime;

public record PostDetailResponseDto(
        String title,
        String contents,
        LocalDateTime createdTime,
        int count,
        User user
) {
}
