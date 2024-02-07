package ru.job4j.shortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlDto {
    @Size(min = 4, max = 2000, message
            = "Длина ссылки должна быть не менее 4-х и не более 2000-ти символов.")
    @NotNull(message = "Ссылка не указана.")
    @NotBlank(message = "Ссылка не должна быть пустой.")
    private String url;
}
