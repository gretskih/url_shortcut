package ru.job4j.shortcut.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "site_url")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Url {
    @Id
    @EqualsAndHashCode.Include
    private int id;
    private String url;
    private String code;
    private Long total;
}
