package com.springboot.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.project.model.Movie;
import com.springboot.project.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService{
	
	private MovieRepository movieRepository;	

	public MovieServiceImpl(MovieRepository movieRepository) {
		super();
		this.movieRepository = movieRepository;
	}

	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Movie saveMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public Movie getMovieById(Long id) {
		return movieRepository.findById(id).get();
	}

	@Override
	public Movie updateMovie(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	public void deleteMovieById(Long id) {
		movieRepository.deleteById(id);
	}
}
