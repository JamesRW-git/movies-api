package com.codeup.fortran_movies_api.web;

import com.codeup.fortran_movies_api.data.Movie;
import com.codeup.fortran_movies_api.data.MoviesRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
public class MoviesController {
    //Temporary backing field to give us something to work with, will be removed
    private List<Movie> sampleMovies = setMovies();

    private final MoviesRepository moviesRepository;

    public MoviesController(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @GetMapping
    public Movie one() {
        return moviesRepository.getById(1);
    }

    @GetMapping("all")
    public List<Movie> getAll() {
        return moviesRepository.findAll();
    }

    @GetMapping("{id}")
    public Movie getById(@PathVariable int id) {
        return sampleMovies.stream().filter((movie) -> {
                    return movie.getId() == id;
        })
                .findFirst()
                .orElse(null);
    }

    //@GetMapping("search/{title}")
    //public Movie getByTitle(@PathVariable String title){
    @GetMapping("search")
    public Movie getByTitle(@RequestParam String title){
        Movie movieToReturn = null;

        for (Movie movie : sampleMovies){
            if(movie.getTitle().equals(title)){
                movieToReturn = movie;
            }
        }
        return movieToReturn;
    }

    @PostMapping
    public void create(@RequestBody Movie newMovie) {
        moviesRepository.save(newMovie);
    }

    @PostMapping("all")
    public void createAll(@RequestBody List<Movie> moviesToAdd) {
        moviesRepository.saveAll(moviesToAdd);
    }

    private List<Movie> setMovies() {
        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie(2, "Pulp Fiction", "1994",/* "Quentin Tarantino",
                "Samuel L. Jackson, Uma Thurman, Bruce Willis, John Travolta, Ving Rhames",
                "action, drama, suspense, cult classic, crime",*/
                "Vincent Vega (John Travolta) and Jules Winnfield (Samuel L. Jackson) are hitmen with a penchant " +
                        "for philosophical discussions. In this ultra-hip, multi-strand crime movie, their storyline is " +
                        "interwoven with those of their boss, gangster Marsellus Wallace (Ving Rhames) ; his actress " +
                        "wife, Mia (Uma Thurman) ; struggling boxer Butch Coolidge (Bruce Willis) ; master fixer Winston" +
                        " Wolfe (Harvey Keitel) and a nervous pair of armed robbers, \"Pumpkin\" (Tim Roth) " +
                        "and \"Honey Bunny\" (Amanda Plummer)."
        ));

        movies.add(new Movie(3, "Birdemic", "2010",/* "James Nguyen", "Alan Bagh, " +
                "Whitney Moore, Tippi Hedren", "horror, thriller",*/
                "In the town of Half Moon Bay California, there's an epidemic on the " +
                        "horizon: a Birdemic. All the birds are attacking humanity for their devastating damage they're " +
                        "inflicting on the Earth: they're going after gas stations and people's cars, and the birds have " +
                        "something the humans will never see coming: Bird-Acid."
        ));
        movies.add(new Movie(1, "The Big Lebowski",
                "1995", /*"The Cohen Bros",
                "Jeff Bridges, John Goodman, Steve Buscemi",
                "comedy, drama?",*/
                "the dude just wanted to relax and go bowling"
        ));

        return movies;
    }
}
