package com.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
