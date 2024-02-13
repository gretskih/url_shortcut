package ru.job4j.shortcut.service.site;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.RegistrationReqDto;
import ru.job4j.shortcut.dto.RegistrationRespDto;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.site.SiteRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SimpleSiteService implements SiteService {
    private final SiteRepository siteRepository;
    @Value("${login.length}")
    private Integer loginLength;
    @Value("${password.length}")
    private Integer passwordLength;

    @Override
    public Optional<RegistrationRespDto> save(RegistrationReqDto registrationReqDTO) {
        String site = registrationReqDTO.getSite();
        String login = RandomStringUtils.randomAlphanumeric(loginLength);
        String password = RandomStringUtils.randomAlphanumeric(passwordLength);
        Site siteRow = new Site(site, login, password);
        try {
            Site siteSave = siteRepository.save(siteRow);
            RegistrationRespDto respDTO = new RegistrationRespDto(
                    true, siteSave.getLogin(), siteSave.getPassword()
            );
            return Optional.of(respDTO);
        } catch (DataIntegrityViolationException e) {
            var siteFind = siteRepository.findBySite(site);
            if (siteFind.isPresent()) {
                RegistrationRespDto respDTO = new RegistrationRespDto(
                        false, siteFind.get().getLogin(), siteFind.get().getPassword()
                );
                return Optional.of(respDTO);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Site> findByLogin(String username) {
        return siteRepository.findByLogin(username);
    }
}
