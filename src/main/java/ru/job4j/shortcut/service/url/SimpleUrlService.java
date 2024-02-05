package ru.job4j.shortcut.service.url;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.repository.url.UrlRepository;

@Service
@AllArgsConstructor
public class SimpleUrlService implements UrlService {
    private final UrlRepository urlRepository;
}
