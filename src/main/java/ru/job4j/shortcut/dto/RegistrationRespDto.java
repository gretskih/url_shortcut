package ru.job4j.shortcut.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Сущность с данными зарегистрированного сайта")
public class RegistrationRespDto {
    @Schema(description = "Статус регистрации")
    private boolean registration;
    @Schema(description = "Логин")
    private String login;
    @Schema(description = "Пароль")
    private String password;
}
