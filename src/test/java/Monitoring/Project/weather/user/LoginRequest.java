package Monitoring.Project.weather.user;

import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        String email,
        @NotNull String password
) {
}