package io.javamani.moviecatalogservice.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javamani.moviecatalogservice.model.CatalogItem;
import io.javamani.moviecatalogservice.model.Rating;
import io.javamani.moviecatalogservice.model.UserRating;

@Service
public class UserRatingInfo {

	@Autowired
	public RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getUserRatingallback")
	public UserRating getUserRating(@PathVariable("userid") String userid) {
		return this.restTemplate.getForObject("http://RATING-DATA-SERVICE/ratingdata/user/" + userid, UserRating.class);
	}

	public UserRating getUserRatingallback(@PathVariable("userid") String userid) {
		UserRating userRating = new UserRating();
		userRating.setUserRating(Arrays.asList(new Rating("No movie", 0)));
		return userRating;
	}
}
