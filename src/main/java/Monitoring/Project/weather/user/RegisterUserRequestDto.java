package Monitoring.Project.weather.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterUserRequestDto(
        @NotNull
        @Email //이메일 형식
        String email,

        @NotNull
        @Size(min = 6)
        String password,

        @NotNull
        String nickname,

        @NotNull
        String birthday
)
{

}

