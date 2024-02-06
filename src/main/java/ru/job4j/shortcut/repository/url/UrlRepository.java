package ru.job4j.shortcut.repository.url;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Url;

import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Optional<Url> findByUrl(String url);

    Optional<Url> findByCode(String code);

}
