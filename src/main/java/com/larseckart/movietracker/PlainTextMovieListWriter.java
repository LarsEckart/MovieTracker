package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;

class PlainTextMovieListWriter implements MovieListWriter {

  public PlainTextMovieListWriter() {
  }

  @Override
  public void write(Writer destination, MovieList movies) throws IOException {
    Collection<Movie> collection = movies.getMovies();
    if (collection.isEmpty()) {
      return;
    }

    for (Movie movie : collection) {
      movie.writeMovie(destination);
    }
    destination.flush();
  }
}
