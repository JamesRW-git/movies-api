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
import java.util.Spliterator;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MoviesController {

    private final MoviesRepository moviesRepository;
    private final DirectorsRepository directorsRepository;
    private final GenresRepository genresRepository;

    public MoviesController(MoviesRepository moviesRepository, DirectorsRepository directorsRepository, GenresRepository genresRepository) {
        this.moviesRepository = moviesRepository;
        this.directorsRepository = directorsRepository;
        this.genresRepository = genresRepository;
    }

    @GetMapping("all") // /api/movies/all
    public List<MovieDto> getAll() {
        List<Movie> movieEntities = moviesRepository.findAll();
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie movie : movieEntities) {
            movieDtos.add(new MovieDto(movie.getId(),
                    movie.getTitle(),
                    movie.getRating(),
                    movie.getPoster(),
                    movie.getYear(),
                    movie.getGenres().stream().map(Genre::getName).collect(Collectors.joining(",")),
                    movie.getDirector().getName(),
                    movie.getPlot()));
        }

        return movieDtos;
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

    @PostMapping // /api/movies POST
    public void create(@RequestBody MovieDto movieDto) {
        //add to our movies list
        Movie movieToAdd = new Movie (
                movieDto.getTitle(),
                movieDto.getYear(),
                movieDto.getPlot(),
                movieDto.getPoster(),
                movieDto.getRating()
        );

        List<Director> directorInDb = directorsRepository.findByName(movieDto.getDirector());
        System.out.println(directorInDb);
        if (directorInDb.isEmpty()) {
            Director newDirector = new Director(movieDto.getDirector());
            movieToAdd.setDirector(directorsRepository.save(newDirector));
        } else {
            movieToAdd.setDirector(directorInDb.get(0));
        }

        String[] genres = movieDto.getGenre().split(", ");
        List<Genre> moviesGenres = new ArrayList<>();
        for(String genre : genres) {
            Genre genreInDb = genresRepository.findGenreByName(genre);
            System.out.println(genreInDb);
            if (genreInDb == null) {
                Genre newGenre = new Genre(genre);
                moviesGenres.add(genresRepository.save(newGenre));
            } else {
                moviesGenres.add(genreInDb);
            }
        }
        movieToAdd.setGenres(moviesGenres);

        moviesRepository.save(movieToAdd);
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
