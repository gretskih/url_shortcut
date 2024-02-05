package ru.job4j.shortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRespDTO {
    private boolean registration;
    private String login;
    private String password;
}
