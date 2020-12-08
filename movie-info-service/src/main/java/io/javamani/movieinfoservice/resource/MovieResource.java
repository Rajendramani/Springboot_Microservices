package io.javamani.movieinfoservice.resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javamani.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieResource {

	@RequestMapping("/hai")
	public String test() {
		return "Hello this is Movie Info Service";
	}

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		return new Movie(movieId, "Testing movie");
	}
}
