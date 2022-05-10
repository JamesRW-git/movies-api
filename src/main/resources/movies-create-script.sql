# CREATE DATABASE IF NOT EXISTS movies_db;
#
# USE movies_db;
# #DROP THE TABLE WITH THE MOST DEPENDENCIES FIRST
# DROP TABLE IF EXISTS movie_genre;
# DROP TABLE IF EXISTS movie_actor;
# DROP TABLE IF EXISTS actors;
# DROP TABLE IF EXISTS genres;
# DROP TABLE IF EXISTS movies;
# DROP TABLE IF EXISTS directors;
#
# CREATE TABLE IF NOT EXISTS directors
# (
#     id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
#     name VARCHAR(255) NOT NULL,
#     PRIMARY KEY (id)
# );
#
# CREATE TABLE IF NOT EXISTS genres
# (
#     id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
#     name VARCHAR(32),
#     PRIMARY KEY (id)
# );
#
# CREATE TABLE IF NOT EXISTS movies
# (
#     id          INT UNSIGNED NOT NULL AUTO_INCREMENT,
#     title       VARCHAR(255) NOT NULL,
#     year        CHAR(4)      NOT NULL,
#     plot        TEXT,
#     rating      CHAR(1)      NOT NULL,
#     poster      TEXT,
#     director_id INT UNSIGNED NOT NULL,
#     PRIMARY KEY (id),
#     FOREIGN KEY (director_id) REFERENCES directors (id)
# );
#
# CREATE TABLE IF NOT EXISTS movie_genre
# (
#     movie_id INT UNSIGNED NOT NULL,
#     genre_id INT UNSIGNED NOT NULL,
#     FOREIGN KEY (movie_id) REFERENCES movies (id),
#     FOREIGN KEY (genre_id) REFERENCES genres (id)
# );
#
# CREATE TABLE IF NOT EXISTS actors
# (
#     id   INT UNSIGNED NOT NULL AUTO_INCREMENT,
#     name VARCHAR(255),
#     PRIMARY KEY (id)
# );
#
# CREATE TABLE IF NOT EXISTS movie_actor
# (
#     movie_id INT UNSIGNED NOT NULL,
#     actor_id INT UNSIGNED NOT NULL,
#     FOREIGN KEY (movie_id) REFERENCES movies (id),
#     FOREIGN KEY (actor_id) REFERENCES actors (id)
# );

CREATE DATABASE IF NOT EXISTS movies_db;

USE movies_db;

CREATE TABLE IF NOT EXISTS movies(
                                     id INT UNSIGNED NOT NULL AUTO_INCREMENT,
                                     title VARCHAR(255) NOT NULL,
                                     year CHAR(4) NOT NULL,
                                     plot TEXT,
                                     PRIMARY KEY (id)
);




