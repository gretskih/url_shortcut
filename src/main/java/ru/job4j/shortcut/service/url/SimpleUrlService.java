package ru.job4j.shortcut.service.url;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.CodeDto;
import ru.job4j.shortcut.dto.StatisticsDto;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.model.Url;
import ru.job4j.shortcut.repository.url.UrlRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SimpleUrlService implements UrlService {
    private final UrlRepository urlRepository;
    @Value("${code.length}")
    private Integer codeLength;

    @Override
    public Optional<CodeDto> save(UrlDto urlDto, Site site) {
        String code = RandomStringUtils.randomAlphanumeric(codeLength);
        Url url = Url.builder()
                .id(0)
                .url(urlDto.getUrl())
                .code(code)
                .total(0L)
                .site(site)
                .build();
        try {
            Url urlSave = urlRepository.save(url);
            return Optional.of(new CodeDto(urlSave.getCode()));
        } catch (DataIntegrityViolationException e) {
            var urlFind = urlRepository.findByUrl(urlDto.getUrl());
            if (urlFind.isPresent()) {
                return Optional.of(new CodeDto(urlFind.get().getCode()));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<UrlDto> findByCode(String code) {
        var urlOptional = urlRepository.findByCode(code);
        return urlOptional.map(value -> {
            incrementTotal(value);
            urlRepository.save(value);
            return new UrlDto(value.getUrl());
        });
    }

    @Override
    public List<StatisticsDto> findAllBySite(Site site) {
        var urls = urlRepository.findAllBySite(site);
        return urls.stream().map(u -> new StatisticsDto(u.getUrl(), u.getTotal())).collect(Collectors.toList());
    }

    private void incrementTotal(Url url) {
        url.setTotal(Math.incrementExact(url.getTotal()));
    }

    @Override
    public boolean checkUrl(UrlDto urlDto, String siteUrl) throws URISyntaxException {
            URI uri = new URI(urlDto.getUrl());
            String hostName = uri.getHost();
            return hostName.contains(siteUrl);
    }
}
