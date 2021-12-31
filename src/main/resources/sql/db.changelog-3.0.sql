create table movie
(
    id         int AUTO_INCREMENT PRIMARY KEY,
    title       varchar(50) NOT NULL,
    year        varchar(4),
    released    varchar(50), -- not show on front
    genre       varchar(250),
    director    varchar(250), -- not show on front
    actors      varchar(255), -- not show on front
    language    varchar(255),  -- not show on front
    awards      varchar(255),
    imdb_rating  double,
    imdb_votes   int,
    imdb_Id      varchar(15),
    box_office   int,
    image        longblob,
    created_at timestamp not null default now(),
    updated_at timestamp not null default now()
);


