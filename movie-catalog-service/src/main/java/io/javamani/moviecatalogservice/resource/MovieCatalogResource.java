package io.javamani.moviecatalogservice.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.javamani.moviecatalogservice.model.CatalogItem;
import io.javamani.moviecatalogservice.model.Movie;
import io.javamani.moviecatalogservice.model.Rating;
import io.javamani.moviecatalogservice.model.UserRating;
import io.javamani.moviecatalogservice.service.MovieInfo;
import io.javamani.moviecatalogservice.service.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	public RestTemplate restTemplate;

	@Autowired
	public MovieInfo movieInfo;

	@Autowired
	public UserRatingInfo userRatingInfo;

	/*
	 * @Autowired public WebClient.Builder webClient;
	 */

	@RequestMapping("/hai")
	public String getTestAPI() {
		return "Hello this is Movie catlog service";
	}

	//@HystrixCommand(fallbackMethod = "getCatalogItemFallback")
	@RequestMapping("/old/{userid}")
	private List<CatalogItem> getCatalogItem(@PathVariable("userid") String userid) {

		/* RestTemplate restTemplate = new RestTemplate(); */

		// Using RestTemplate to call Rating service
		UserRating ratings = this.restTemplate.getForObject("http://RATING-DATA-SERVICE/ratingdata/user/" + userid,
				UserRating.class);

		return ratings.getUserRating().stream().map(rating -> {

			// Using RestTemplate to call Movie info service
			Movie movie = this.restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/" + rating.getMovieId(),
					Movie.class);

			/*
			 * //Using WebClient Movie movie =
			 * webClient.build().get().uri("http://localhost:8082/movie/" +
			 * rating.getMovieId()).retrieve() .bodyToMono(Movie.class).block();
			 */

			return new CatalogItem(movie.getMovieId(), "DESC", rating.getRating());
		}).collect(Collectors.toList());

	}

	@RequestMapping("/{userid}")
	private List<CatalogItem> getCatalogItemNew(@PathVariable("userid") String userid) {
		UserRating userRating = this.userRatingInfo.getUserRating(userid);
		return userRating.getUserRating().stream().map(rating -> movieInfo.getCatalogItem(rating))
				.collect(Collectors.toList());
	}

}
