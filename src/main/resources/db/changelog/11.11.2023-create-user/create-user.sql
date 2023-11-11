create type user_type as enum ('STUDENT', 'ADMIN', 'MENTOR', 'ASSISTANT');


create table "user"
(
    id           uuid primary key,
    first_name   varchar(20)         not null,
    last_name    varchar(20)         not null,
    middle_name  varchar(20),
    user_type    user_type           not null,
    phone_number varchar(20) unique  not null,
    email        varchar(320) unique not null,
    password     varchar             not null
);