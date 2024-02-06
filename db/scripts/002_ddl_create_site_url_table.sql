create table site_url (
    id serial primary key,
    url varchar(2000) not null unique,
    code varchar(7) not null,
    total bigint DEFAULT 1,
    site_user_id int not null references site_user
);