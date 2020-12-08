package io.javamani.ratingdataservice.model;

import java.util.List;

public class UserRating {

	private List<Rating> userRating;

	public UserRating(List<Rating> userRating) {
		super();
		this.userRating = userRating;
	}

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
}
