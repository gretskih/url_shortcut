package ru.job4j.shortcut.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ShortCutUrl {
    @Schema(description = "Адрес ссылки")
    private String url;
    @Schema(description = "Адрес сайта")
    private String site;
}
