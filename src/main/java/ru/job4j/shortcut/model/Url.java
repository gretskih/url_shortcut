package ru.job4j.shortcut.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@Table(name = "site_url")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String url;
    private String code;
    private Long total;
    @ManyToOne
    @JoinColumn(name = "site_user_id")
    private Site site;
}
