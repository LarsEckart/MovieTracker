package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Iterator;

class XMLMovieListWriter implements MovieListWriter {

  private final Writer destination;
  private final MessageFormat movieFormat = new MessageFormat(
      "  <movie name=\"{0}\" category=\"{1}\">");
  private final MessageFormat ratingFormat = new MessageFormat(
      "\n      <rating value=\"{0,number,integer}\" source=\"{1}\" />");

  public XMLMovieListWriter(Writer destination) {
    this.destination = destination;
  }

  @Override
  public void write(Writer writer, MovieList movies) throws IOException {
    destination.write("<movielist>\n");
    if (movies.size() > 0) {
      writeMovies(movies);
    }
    destination.write("</movielist>");
    destination.flush();
  }

  private void writeMovies(MovieList movies) throws IOException {
    for (var movie : movies.getMovies()) {
      String[] args = {movie.getName(), movie.getCategory().toString()};
      destination.write(movieFormat.format(args));
      writeRatings(movie);
      destination.write("\n  </movie>\n");
    }
  }

  private void writeRatings(Movie movie) throws IOException {
    destination.write("\n    <ratings>");
    Iterator<Rating> ratings = movie.ratings();
    while (ratings.hasNext()) {
      var rating = ratings.next();
      destination.write(ratingFormat.format(new Object[]{rating.value(), rating.source()}));
    }
    destination.write("\n    </ratings>");
  }

}
