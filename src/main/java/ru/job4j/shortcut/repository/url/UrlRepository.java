package ru.job4j.shortcut.repository.url;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.dto.UrlDto;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.model.Url;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends CrudRepository<Url, Integer> {
    Optional<Url> findByUrl(String url);

    Optional<Url> findByCode(String code);

    List<Url> findAllBySite(Site site);

    @Transactional
    @Modifying
    @Query("UPDATE Url SET total = total + 1 WHERE id = :id")
    void incrementTotalById(Long id);
}
