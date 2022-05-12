package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.*;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final MoviesRepository moviesRepository;
    private final DirectorsRepository directorsRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public MoviesController(MoviesRepository moviesRepository, DirectorsRepository directorsRepository,
                            ActorRepository actorRepository, GenreRepository genreRepository) {
        this.moviesRepository = moviesRepository;
        this.directorsRepository = directorsRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("all") // /api/movies/all
    public List<Movie> getAll() {
        return moviesRepository.findAll();
    }

    @GetMapping("{id}") // api/movies/{id}
    public Movie getById(@PathVariable int id) {
        return moviesRepository.findById(id).orElse(null);
    }

    //@GetMapping("search/{title}")
    //public Movie getByTitle(@PathVariable String title){
    @GetMapping("search") // /api/movies/search/title
    public List<Movie> getByTitle(@RequestParam("title") String title){
        return moviesRepository.findByTitle(title);
    }

    @GetMapping("search/year") // api/movies/search/year
    public List<Movie> getByYearRange(@RequestParam("startYear") int startYear, @RequestParam("endYear") int endYear){
        return moviesRepository.findByYearRange(startYear, endYear);
    }

    @GetMapping("search/director") // /api/movies/search/director
    public List<Director> getByDirector(@RequestParam("name") String directorName){
        return directorsRepository.findByName(directorName);
    }

    @GetMapping("search/actor") // /api/movies/search/actor
    public List<Actor> getByActor(@RequestParam("name") String actorName) {
        return actorRepository.findByName(actorName);
    }

    @GetMapping("search/genre") // /api/movies/search/genre
    public List<Genre> getByGenre(@RequestParam("name") String genreName) {
        return genreRepository.findByName(genreName);
    }

    @PostMapping // /api/movies POST
    public void create(@RequestBody Movie newMovie) {
        moviesRepository.save(newMovie);
    }

    @PostMapping("all") // /api/movies/many POST
    public void createAll(@RequestBody List<Movie> moviesToAdd) {
        moviesRepository.saveAll(moviesToAdd);
    }

    @PutMapping
    public void updateOne(@RequestBody Movie movie) {
        moviesRepository.save(movie);
    }

    @DeleteMapping("{id}") // /api/movies/{id}
    public void deleteById(@PathVariable int movieId){
        try {
            moviesRepository.deleteById(movieId);
        } catch(EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No matching movie with ID: " + movieId);
        }
    }

}
