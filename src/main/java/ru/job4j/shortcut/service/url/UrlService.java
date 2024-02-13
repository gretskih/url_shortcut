package ru.job4j.shortcut.service.url;

import ru.job4j.shortcut.dto.CodeDto;
import ru.job4j.shortcut.dto.ShortCutUrl;
import ru.job4j.shortcut.dto.StatisticsDto;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Site;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public interface UrlService {
    Optional<CodeDto> save(UrlDto url, Site site);

    boolean validUri(ShortCutUrl shortCutUrl);

    boolean validDomain(ShortCutUrl shortCutUrl);

    Optional<UrlDto> findByCode(String code);

    List<StatisticsDto> findAllBySite(Site site);
}
