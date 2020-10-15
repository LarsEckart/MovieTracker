package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

class XMLMovieListWriter implements MovieListWriter {

  private final Writer destination;

  public XMLMovieListWriter(Writer destination) {
    this.destination = destination;
  }

  @Override
  public void write(MovieList movies) throws IOException {
    destination.write("<movielist>");
    if (movies.size() > 0) {
      writeMovie(movies);
    }
    destination.write("\n</movielist>");
    destination.flush();
  }

  private void writeMovie(MovieList movies) throws IOException {
    for (Movie movie : movies.getMovies()) {
      destination.write("\n  <movie name=\"");
      destination.write(movie.getName());
      destination.write("\" category=\"");
      destination.write(movie.getCategory().toString());
      destination.write("\">");
      destination.write("\n    <ratings>");
      writeRatings(movie);
      destination.write("\n    </ratings>");
      destination.write("\n  </movie>");
    }
  }

  private void writeRatings(Movie movie) throws IOException {
    Iterator<Rating> ratings = movie.ratings();
    while (ratings.hasNext()) {
      destination.write("\n      <rating value=\"");
      Rating next = ratings.next();
      destination.write(Integer.toString(next.value()));
      destination.write("\" source=\"");
      destination.write(next.source());
      destination.write("\" />");
    }
  }

}
