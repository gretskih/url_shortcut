package ru.job4j.shortcut.service.site;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.RegistrationReqDTO;
import ru.job4j.shortcut.dto.RegistrationRespDTO;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.site.SiteRepository;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class SimpleSiteService implements SiteService {
    private final SiteRepository siteRepository;

    @Override
    public Optional<RegistrationRespDTO> save(RegistrationReqDTO registrationReqDTO) {
        String site = registrationReqDTO.getSite();
        String login = RandomStringUtils.randomAlphanumeric(10);
        String password = RandomStringUtils.randomAlphanumeric(10);
        Site siteRow = new Site(0, site, login, password, Set.of());
        try {
            Site siteSave = siteRepository.save(siteRow);
            RegistrationRespDTO respDTO = new RegistrationRespDTO(
                    true, siteSave.getLogin(), siteSave.getPassword()
            );
            return Optional.of(respDTO);
        } catch (DataIntegrityViolationException e) {
            log.info(e.getMessage());
            Optional<Site> siteFind = siteRepository.findBySite(site);
            if (siteFind.isPresent()) {
                RegistrationRespDTO respDTO = new RegistrationRespDTO(
                        false, siteFind.get().getLogin(), siteFind.get().getPassword()
                );
                return Optional.of(respDTO);
            }
        }
        return Optional.empty();
    }
}
