package ru.job4j.shortcut.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность содержит адрес ссылки")
public class UrlDto {
    @Size(min = 4, max = 2000, message
            = "Длина ссылки должна быть не менее 4-х и не более 2000-ти символов.")
    @NotNull(message = "Ссылка не указана.")
    @NotBlank(message = "Ссылка не должна быть пустой.")
    @Schema(description = "Адрес ссылки")
    private String url;
}
