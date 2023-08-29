package com.springboot.project.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.project.model.Movie;
import com.springboot.project.model.User;
import com.springboot.project.service.MovieService;
import com.springboot.project.service.UserService;

@Controller
public class RecommendationController {
	
	private UserService userService;
	private MovieService movieService;

	public RecommendationController(UserService userService, MovieService movieService) {
		this.userService = userService;
		this.movieService = movieService;
	}
	
	@GetMapping("/recommendation_friends")
	public String showFriendsList(Model model, Principal principal) {
	    // Get the logged-in user based on the principal's email
	    User loggedInUser = userService.findByEmail(principal.getName());

	    // Get the friends of the logged-in user
	    List<User> friends = loggedInUser.getFriends();

	    model.addAttribute("friends", friends);
	    return "recommendation_friends";
	}
	
	@GetMapping("/recommendation_movies")
	public String recommendMovies(Model model) {
	    List<Movie> movies = movieService.getAllMovies();

	    for (Movie movie : movies) {
	        String imageBase64 = Base64.getEncoder().encodeToString(movie.getImage());
	        movie.setImageBase64(imageBase64);
	    }

	    // List of movie objects with only movie_name and imageBase64
	    List<Movie> simplifiedMovies = new ArrayList<>();
	    for (Movie movie : movies) {
	        Movie simplifiedMovie = new Movie();
	        simplifiedMovie.setMovie_name(movie.getMovie_name());
	        simplifiedMovie.setImageBase64(movie.getImageBase64());
	        simplifiedMovies.add(simplifiedMovie);
	    }

	    model.addAttribute("recommendedMovies", simplifiedMovies);
	    return "recommendation_movies";
	}

}
