# 1. go get the json file from glitch
# 2. copypasta into a new json file under /resources

# --> You may need to establish a connection to your localhost db here

# 3. create the movies_db
create database if not exists movies_db;

# 4. use the movies_db
use movies_db;

# 5. drop the table(s) to which no other tables are dependent (none at first)
drop table if exists movies;
drop table if exists directors;
drop table if exists actors;
drop table if exists genres;

# 6. map the json movie properties to movies table columns
# --> start with just a movies table with all the columns found in the movie json properties
create table if not exists directors
(
    id   int unsigned not null auto_increment primary key,
    name varchar(120)
);

create table if not exists genres
(
    id   int unsigned not null auto_increment primary key,
    name varchar(255)
);

create table if not exists actors
(
    id   int unsigned not null auto_increment primary key,
    name varchar(255)
);

create table if not exists movies
(
    id          int unsigned not null auto_increment primary key,
    title       varchar(120),
    rating      char(1),
    year        char(4),
    poster      text,
    plot        text,
    director_id int unsigned not null,
    foreign key (director_id) references directors (id)
);

create table if not exists movie_genre
(
    movie_id int unsigned not null,
    genre_id int unsigned not null,
    foreign key (movie_id) references movies (id),
    foreign key (genre_id) references genres (id)
);

describe movie_genre;

create table if not exists movie_actor
(
    movie_id int unsigned not null,
    actor_id int unsigned not null,
    foreign key (movie_id) references movies (id),
    foreign key (actor_id) references actors (id)
);

describe movie_actor;

# 6a. Run the script to make sure it works
describe movies;
describe directors;
describe actors;
describe genres;

# 7. refactor to extract the directors to a new table with just an id and name
# --> change the movies table to reference the directors table via Foreign Key
# --> now that movies is dependent on directors, you need to move directors above movies in the script

# 8. Go add DROP IF EXIST statements for movies and directors

# 9. RUN IT!

INSERT INTO genres (name)
VALUES ('comedy'),
       ('drama'),
       ('action'),
       ('fantasy'),
       ('horror'),
       ('romance'),
       ('hallmark romance'),
       ('thriller'),
       ('war');

INSERT INTO directors (name)
VALUES ('Director Guy'),
       ('Director Lady'),
       ('Francis Coppola');

INSERT INTO actors (name)
VALUES ('Mr. Actor Man'),
       ('Ms. Actor Lady'),
       ('Pac-Man');

INSERT INTO movies (title, rating, year, poster, plot, director_id)
VALUES ('Apocalypse Now', '5', '1979', 'https://m.media-amazon.com/images/I/41TUHqMRtML.jpg',
        'It is the height of the war in Vietnam, and U.S. Army Captain Willard is sent by Colonel Lucas and a General to carry out a mission that, officially, \'does not exist - nor will it ever exist\'. The mission: To seek out a mysterious Green Beret Colonel, Walter Kurtz, whose army has crossed the border into Cambodia and is conducting hit-and-run missions against the Viet Cong and NVA. The army believes Kurtz has gone completely insane and Willard\'s job is to eliminate him. Willard, sent up the Nung River on a U.S. Navy patrol boat, discovers that his target is one of the most decorated officers in the U.S. Army. His crew meets up with surfer-type Lt-Colonel Kilgore, head of a U.S Army helicopter cavalry group which eliminates a Viet Cong outpost to provide an entry point into the Nung River. After some hair-raising encounters, in which some of his crew are killed, Willard, Lance and Chef reach Colonel Kurtz\'s outpost, beyond the Do Lung Bridge. Now, after becoming prisoners of Kurtz, will Willard & the others be able to fulfill their mission?'
        , 3);

INSERT INTO movie_actor
VALUES (1,1);

INSERT INTO movie_genre
VALUES (1,9);


# create a genres table with two columns: id and name

# create a many-to-many relationship b/t movies and genres by creating a movie_genre table.
# --> It needs to only contain a movie_id and genre_id (be sure to foreign key those to their respective tables)

# create create an actors table with two columns: id and name

# create a many-to-many relationship b/t movies and actors by creating movie_actor table.
# --> It needs to only contain a movie_id and actor_id (be sure to foreign key those to their respective tables)






