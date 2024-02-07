package ru.job4j.shortcut.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.dto.RegistrationReqDTO;
import ru.job4j.shortcut.dto.RegistrationRespDTO;
import ru.job4j.shortcut.service.site.SiteService;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Tag(name = "Контроллер сайтов")
public class SiteController {
    private final SiteService siteService;

    @Operation(
            summary = "Регистрация сайта",
            description = "Позволяет зарегистрировать сайт"
    )
    @SecurityRequirement(name = "JWT")
    @PostMapping("/registration")
    public ResponseEntity<RegistrationRespDTO> registration(@RequestBody @Valid RegistrationReqDTO registrationReq) {
        return siteService.save(registrationReq)
                .map(p -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(p))
                .orElseGet(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)::build);
    }

}
