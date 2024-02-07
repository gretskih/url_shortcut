package ru.job4j.shortcut.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Сущность содержит статистику по ссылкам")
public class StatisticsDto {
    @Schema(description = "Ссылка")
    private String url;
    @Schema(description = "Число запросов по ссылке")
    private Long total;
}
