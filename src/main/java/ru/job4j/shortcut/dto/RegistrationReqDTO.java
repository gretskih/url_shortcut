package ru.job4j.shortcut.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Сущность с данными для регистрации сайта")
public class RegistrationReqDTO {
    @Size(min = 5, max = 70, message
            = "Общая длина адреса сайта должна быть не менее 5-и и не более 70-ти символов.")
    @Pattern(regexp = "^(?!-)[A-Za-z0-9-]{2,63}(?<!-)\\.[A-Za-z]{2,6}$",
            message = "Неверный формат адреса сайта. Поддерживаются только ASCII символы например: site.com")
    @NotNull(message = "Адрес сайта не указан.")
    @NotBlank(message = "Адрес сайта не должен быть пустым.")
    @Schema(description = "Адрес сайта")
    private String site;
}
