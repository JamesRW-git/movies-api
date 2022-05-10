package com.codeup.fortran_movies_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByTitle(String title);

    @Query(nativeQuery = true,
            value = "SELECT * FROM movies m WHERE m.year >= ? AND m.year <= ?;")
    List<Movie> findByYearRange(Integer startYear, Integer endYear);
}
