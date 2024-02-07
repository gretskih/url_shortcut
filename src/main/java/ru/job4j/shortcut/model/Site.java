package ru.job4j.shortcut.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "site_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String site;
    private String login;
    private String password;
}
