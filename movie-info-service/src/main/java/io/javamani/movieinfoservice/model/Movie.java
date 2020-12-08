package io.javamani.movieinfoservice.model;

public class Movie {
	private String movieId;
	private String name;
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private String desc;

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movie(String movieId, String name) {
		super();
		this.movieId = movieId;
		this.name = name;
	}

}
