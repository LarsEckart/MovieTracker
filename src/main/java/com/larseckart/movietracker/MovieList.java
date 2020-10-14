package com.larseckart.movietracker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
    if (category.equals(Category.ALL)) {
      return this;
    }

    MovieList filtered = new MovieList();
    movies.stream()
        .filter(m -> category.equals(m.getCategory()))
        .forEach(filtered::add);
    return filtered;
  }

  @Override
  public String toString() {
    return movies.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MovieList movieList = (MovieList) o;
    return Objects.equals(movies, movieList.movies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(movies);
  }
}
