package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

class PlainTextMovieListWriter implements MovieListWriter {

  private final Writer destination;

  public PlainTextMovieListWriter(Writer destination) {
    this.destination = destination;
  }

  @Override
  public void write(MovieList movies) throws IOException {
    Collection<Movie> collection = movies.getMovies();
    if (collection.isEmpty()) {
      return;
    }

    for (Movie movie : collection) {
      movie.writeMovie(this.destination);
    }
    this.destination.flush();
  }
}
