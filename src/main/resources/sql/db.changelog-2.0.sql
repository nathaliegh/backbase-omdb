create table user
(
    id         int AUTO_INCREMENT PRIMARY KEY,
    username       varchar(100)    NOT NULL,
    password       varchar(100)    NOT NULL,
    created_at timestamp not null default now(),
    UNIQUE Key (username)
);
