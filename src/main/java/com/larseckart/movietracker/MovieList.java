package com.larseckart.movietracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MovieList {

  private final List<Movie> movies = new ArrayList<>();

  public int size() {
    return movies.size();
  }

  public void add(Movie movie) {
    if (movies.contains(movie)) {
      throw new DuplicateMovieException(movie.getName());
    }
    movies.add(movie);
  }

  public boolean contains(Movie movie) {
    return movies.contains(movie);
  }

  public Collection<Movie> getMovies() {
    return this.movies;
  }

  public Movie getMovie(int index) {
    return movies.get(index);
  }

  public void rename(Movie movie, String newName) {
    Movie potentialMovie = new Movie(movie);
    potentialMovie.rename(newName);
    if (this.contains(potentialMovie)) {
      throw new DuplicateMovieException(newName);
    }
    movie.rename(newName);
  }

  public Object categorySublist(Category category) {
    return null;
  }
}
