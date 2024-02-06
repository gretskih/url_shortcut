package ru.job4j.shortcut.repository.site;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Site;

import java.util.Optional;

public interface SiteRepository extends CrudRepository<Site, Integer> {
    Optional<Site> findBySite(String site);

    Optional<Site> findByLogin(String username);
}
