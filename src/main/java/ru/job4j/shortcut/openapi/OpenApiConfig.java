package ru.job4j.shortcut.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "UrlShortCut Api",
                description = "Cервис выполняет преобразование ссылки в уникальный код, по которому без авторизации сервис выдает ассоциированную ссылку.", version = "1.0.0",
                contact = @Contact(
                        name = "Anatoly",
                        email = "gretskih@mail.ru"
                )
        )
)
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApiConfig {
}
