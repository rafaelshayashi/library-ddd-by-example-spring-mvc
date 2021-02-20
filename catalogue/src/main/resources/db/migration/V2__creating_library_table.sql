create table if not exists library
(
    id      bigserial primary key,
    uuid    uuid         not null,
    name    varchar(255) not null,
    street  varchar(255),
    state   varchar(255),
    country varchar(255),
    zip_code varchar
);