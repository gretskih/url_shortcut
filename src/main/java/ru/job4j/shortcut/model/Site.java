package ru.job4j.shortcut.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "site_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    private String site;
    private String login;
    private String password;

    public Site(String site, String login, String password) {
        this.site = site;
        this.login = login;
        this.password = password;
    }
}
