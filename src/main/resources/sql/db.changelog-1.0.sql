create table config_omdb
(
    id         int AUTO_INCREMENT PRIMARY KEY,
    type       varchar(100) NOT NULL,
    enable     BOOLEAN         default true,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);

