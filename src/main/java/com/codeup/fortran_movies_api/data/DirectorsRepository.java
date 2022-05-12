package com.codeup.fortran_movies_api.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DirectorsRepository extends JpaRepository<Director, Integer> {

    List<Director> findByName(String name);
}
