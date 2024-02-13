package ru.job4j.shortcut.service.site;

import ru.job4j.shortcut.dto.RegistrationReqDto;
import ru.job4j.shortcut.dto.RegistrationRespDto;
import ru.job4j.shortcut.model.Site;

import java.util.Optional;

public interface SiteService {
    Optional<RegistrationRespDto> save(RegistrationReqDto registrationReqDTO);

    Optional<Site> findByLogin(String username);
}
