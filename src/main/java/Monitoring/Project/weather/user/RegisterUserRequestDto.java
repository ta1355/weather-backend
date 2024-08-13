package Monitoring.Project.weather.user;

import java.time.LocalDate;

public record RegisterUserRequestDto(

        String email,
        String password,
        String nickname,
        String birthday
)
{

}

