package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.dto.CodeDto;
import ru.job4j.shortcut.dto.StatisticsDto;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.service.site.SiteService;
import ru.job4j.shortcut.service.url.UrlService;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
public class UrlController {
    private final SiteService siteService;

    private final UrlService urlService;

    @PostMapping("/convert")
    public ResponseEntity<CodeDto> convert(@RequestBody UrlDto url, Principal siteLogin) {
        var siteOptional = siteService.findByLogin(siteLogin.getName());
        return urlService.save(url, siteOptional.get())
                .map(p -> ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(p))
                .orElseGet(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)::build);
    }

    @GetMapping("/redirect/{code}")
    public ResponseEntity<UrlDto> redirect(@PathVariable String code) {
        return urlService.findByCode(code)
                .map(p -> ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(p))
                .orElseGet(ResponseEntity.status(HttpStatus.NOT_FOUND)::build);
    }

    @GetMapping("/statistic")
    public List<StatisticsDto> statistic() {
        return urlService.findAll();
    }
}
