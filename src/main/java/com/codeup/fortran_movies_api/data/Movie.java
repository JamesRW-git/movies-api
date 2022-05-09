package com.codeup.fortran_movies_api.data;

public class Movie {
    private int id;
    private String title;
    private String year;
    private String director;
    private String actors;
    private String imdbID;
    private String genre;
    private String plot;

    public Movie(int id, String title, String year, String director, String actors, String imdbID, String genre, String plot){
        this.id = id;
        this.title = title;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.imdbID = imdbID;
        this.genre = genre;
        this.plot = plot;
    }

    public Movie(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", imdbId='" + imdbID + '\'' +
                ", genre='" + genre + '\'' +
                ", plot='" + plot + '\'' +
                '}';
    }
}