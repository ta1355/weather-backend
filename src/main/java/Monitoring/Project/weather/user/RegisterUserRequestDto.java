package Monitoring.Project.weather.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

public record RegisterUserRequestDto(
        @NotNull
        @Email(message = "{email.email}") //이메일 형식
        String email,

        @NotNull
        @Size(min = 6)
        String password,

        @NotNull
        String nickname,

        @PastOrPresent(message = "{birthday.pastOrPresent}")
        @NotNull
        LocalDate birthday
)
{

}

