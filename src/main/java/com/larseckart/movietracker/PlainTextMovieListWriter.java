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
      destination.write(movie.getName());
      destination.write("|");
      destination.write(movie.getCategory().toString());
      destination.write("|");
      destination.write(Integer.toString(movie.getRatings().stream().mapToInt(Rating::value).sum()));
      destination.write("|");
      destination.write(Integer.toString(movie.getRatings().size()));
      destination.write("\n");
    }
    destination.flush();
  }
}
