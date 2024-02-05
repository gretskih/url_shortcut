package ru.job4j.shortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.site.SiteRepository;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class SimpleUserDetailsService implements UserDetailsService {
    private final SiteRepository siteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Site site = siteRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(site.getLogin(), site.getPassword(), emptyList());
    }
}
