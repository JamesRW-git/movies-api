package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.Movie;
import com.codeup.fortran_movies_api.data.MoviesRepository;
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

    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping("all")
    public List<Movie> getAll() {
        return moviesRepository.findAll();
    }

    @GetMapping("{id}")
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

    @PostMapping // /api/movies POST
    public void create(@RequestBody Movie newMovie) {
        moviesRepository.save(newMovie);
    }

    @PostMapping("all") // /api/movies/many POST
    public void createAll(@RequestBody List<Movie> moviesToAdd) {
        moviesRepository.saveAll(moviesToAdd);
    }

    @DeleteMapping("{id}") // /api/movies/{id}
    public void deleteById(@PathVariable int movieId) throws IOException {
        try {
            moviesRepository.deleteById(movieId);
        } catch(EmptyResultDataAccessException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No matching movie with ID: " + movieId);
        }
    }

}
