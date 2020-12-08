package io.javamani.ratingdataservice.resource;

import java.util.Arrays;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.javamani.ratingdataservice.model.Rating;
import io.javamani.ratingdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingdata")
public class RatingResource {

	@RequestMapping("/hai")
	public String test() {
		return "Hello this is Ratinig data service";
	}

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}

	@RequestMapping("/user/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		UserRating userRating = new UserRating(Arrays.asList(new Rating("Master", 4), new Rating("Valimai", 4)));
		return userRating;
	}

}
