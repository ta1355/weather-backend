package Monitoring.Project.weather.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterUserRequestDto1(

                                      String email,

                                      Long id,

                                      String password,

                                      String nickname,

                                      LocalDate birthday)
{
}
