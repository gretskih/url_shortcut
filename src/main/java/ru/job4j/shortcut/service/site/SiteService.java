package ru.job4j.shortcut.service.site;

import ru.job4j.shortcut.dto.RegistrationReqDTO;
import ru.job4j.shortcut.dto.RegistrationRespDTO;
import ru.job4j.shortcut.model.Site;

import java.security.Principal;
import java.util.Optional;

public interface SiteService {
    Optional<RegistrationRespDTO> save(RegistrationReqDTO registrationReqDTO);

    Optional<Site> findByLogin(String username);
}
