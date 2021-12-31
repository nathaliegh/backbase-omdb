create table movie_rate
(
    id         int AUTO_INCREMENT PRIMARY KEY,
    movie_id    int,
    user_id     int,
    rate     varchar(20),
    created_at timestamp not null default now(),
    updated_at timestamp not null default now(),
    CONSTRAINT fk_movie
    FOREIGN KEY (movie_id)
        REFERENCES movie(id),
    CONSTRAINT fk_user
    FOREIGN KEY (user_id)
        REFERENCES user(id)
);


