package io.javamani.moviecatalogservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.javamani.moviecatalogservice.model.CatalogItem;
import io.javamani.moviecatalogservice.model.Movie;
import io.javamani.moviecatalogservice.model.Rating;

@Service
public class MovieInfo {

	@Autowired
	public RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = this.restTemplate.getForObject("http://MOVIE-INFO-SERVICE/movie/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), "Desc", rating.getRating());

	}

	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());

	}
}
