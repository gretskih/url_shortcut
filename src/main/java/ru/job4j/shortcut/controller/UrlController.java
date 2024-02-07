package ru.job4j.shortcut.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.dto.CodeDto;
import ru.job4j.shortcut.dto.StatisticsDto;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.service.site.SiteService;
import ru.job4j.shortcut.service.url.UrlService;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@Tag(name = "Контроллер ссылок")
public class UrlController {
    private final SiteService siteService;

    private final UrlService urlService;

    @Operation(
            summary = "Регистрация ссылки",
            description = "Позволяет зарегистрировать ссылку и получить ассоциированный код"
    )
    @PostMapping("/convert")
    public ResponseEntity<CodeDto> convert(@RequestBody @Valid UrlDto url, Principal siteLogin) {
        var siteOptional = siteService.findByLogin(siteLogin.getName());
        try {
            if (!urlService.checkUrl(url, siteOptional.get().getSite())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ссылка не относится к: %s.".formatted(siteOptional.get().getSite()));
            }
        } catch (URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Синтаксическая ошибка, в ссылке используются недопустимые символы.");
        }
        return urlService.save(url, siteOptional.get())
                .map(p -> ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(p))
                .orElseGet(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)::build);
    }

    @Operation(
            summary = "Переадресация",
            description = "Позволяет по ассоциированному коду получить ссылку"
    )
    @GetMapping("/redirect/{code}")
    public ResponseEntity<UrlDto> redirect(@PathVariable @NotBlank(message = "Код не должен быть пустым.")
                                               String code) {
        return urlService.findByCode(code)
                .map(p -> ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ссылка для кода: %s не существует".formatted(code)));
    }

    @Operation(
            summary = "Статистика",
            description = "Позволяет получить статистку всех адресов и количество вызовов этого адреса"
    )
    @GetMapping("/statistic")
    public List<StatisticsDto> statistic(Principal siteLogin) {
        var siteOptional = siteService.findByLogin(siteLogin.getName());
        var statistics = urlService.findAllBySite(siteOptional.get());
        if (statistics.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Статистика для %s отсутствует.".formatted(siteOptional.get().getSite()));
        }
        return statistics;
    }
}
