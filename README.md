# Rest Shortcut Service
Cервис выполняет преобразование переданной ссылки в уникальный код, по которому без авторизации сервис выдает ассоциированную ссылку.
Сначала надо зарегистрировать в сервисе адрес сайта, в ответе получить логин и пароль.
По логину и паролю пройти авторизацию, в ответ получить JWT токен.
После этого можно регистрировать в сервисе ваши ссылки и в ответ получать ссылки на сервис в виде уникальных кодов.
Пользователи без авторизации используют уникальные коды для получения ссылок.

## Стек технологий
- ![Java 17](https://img.shields.io/badge/Java-17-blue)
- ![Spring 2.7.12](https://img.shields.io/badge/Spring%20Boot%202.7.12-white?style=flat&logo=Spring)
- ![Liquibase 4.23.1](https://img.shields.io/badge/Liquibase_4.23.1-white?style=flat&logo=Liquibase&logoColor=blue
  )
- ![PostgreSQL 42.3.8](https://img.shields.io/badge/PostgreSQL_42.3.8-white?style=flat&logo=PostgreSQL&logoColor=blue
  )
- ![Maven 4.0.0](https://img.shields.io/badge/Maven%204.0.0-white?style=flat&logo=Apache%20Maven&logoColor=red
  )
- ![Lombok 1.18.26](https://img.shields.io/badge/Lombok%201.18.26-white?style=flat
  )

## Окружение
- Java 17
- PostgreSQL 16
- Apache Maven 4.0.0

## Запуск проекта
1. Скачать архив проекта или создать копию (fork) проекта в своем репозитории и клонировать.
2. Создать локальную базу данных url_shortcut, используя интерфейс PostgreSQL 16 или команду:

   ```create database url_shortcut```

3. В файл конфигурации db/liquibase.properties внести логин и пароль пользователя для доступа к базе данных url_shortcut.
4. Запустить Liquibase для создания таблицы.
5. Для запуска на локальной машине скомпилировать и запустить проект в командной строке

   ```mvn spring-boot:run```

   или после сборки проекта с использованием maven выполнить в командной строке

   ``` java -jar target/url_shortcut-1.0-SNAPSHOT.jar```

## Описание API

### GET /swagger-ui/index.html - Swagger. Доступно без авторизации.

### GET /v3/api-docs - описание API для Swagger. Доступно без авторизации.

### POST /registration - регистрация сайта. Доступно без авторизации.

структура запроса:
```
{
  "site": "string"
}
```
структура ответа:
```
{
  "registration": true,
  "login": "string",
  "password": "string"
}
```

### POST /login - авторизация по логину и паролю.

ответ с заголовком:<br>
Authorization: "*Bearer JWT токен*"

### POST /convert - регистрация ссылки.

структура запроса:
```
{
  "url": "string"
}
```
структура ответа:
```
{
  "code": "string"
}
```

### GET /statistic - запрос статистики.<br>
структура ответа:
```
[
  {
    "url": "string",
    "total": 0
  }
]
```

### GET /redirect/{code} - запрос ссылки по коду code. Доступно без авторизации.
структура ответа:
```
{
  "url": "string"
}
```
## Контакты

email: gretskih@mail.ru <br/>
telegram: @Anatolij_gr