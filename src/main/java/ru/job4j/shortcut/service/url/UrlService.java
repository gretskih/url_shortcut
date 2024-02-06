package ru.job4j.shortcut.service.url;

import ru.job4j.shortcut.dto.CodeDto;
import ru.job4j.shortcut.dto.StatisticsDto;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Site;

import java.util.List;
import java.util.Optional;

public interface UrlService {
    Optional<CodeDto> save(UrlDto url, Site site);

    Optional<UrlDto> findByCode(String code);

    List<StatisticsDto> findAll();
}
