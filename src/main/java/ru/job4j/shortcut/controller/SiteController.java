package ru.job4j.shortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.shortcut.dto.RegistrationReqDTO;
import ru.job4j.shortcut.dto.RegistrationRespDTO;
import ru.job4j.shortcut.service.site.SiteService;

@RestController
@AllArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping("/registration")
    public ResponseEntity<RegistrationRespDTO> registration(@RequestBody RegistrationReqDTO registrationReq) {
        return siteService.save(registrationReq)
                .map(p -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body(p))
                .orElseGet(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)::build);
    }

}
