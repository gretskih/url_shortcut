create table site_user (
    id serial primary key,
    site varchar(70) not null unique,
    login varchar(10) not null,
    password varchar(10) not null
);