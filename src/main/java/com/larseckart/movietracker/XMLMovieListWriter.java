package com.larseckart.movietracker;

import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.Iterator;

class XMLMovieListWriter implements MovieListWriter {

  private final MessageFormat movieFormat = new MessageFormat(
      "  <movie name=\"{0}\" category=\"{1}\">");
  private final String commonReviewFormat = "\n      <rating value=\"{0,number,integer}\" source=\"{1}\"";
  private final MessageFormat ratingFormatWithoutReview = new MessageFormat(
      commonReviewFormat + " />");
  private final MessageFormat ratingFormatWithReview = new MessageFormat(
      commonReviewFormat + ">{2}</rating>");


  public XMLMovieListWriter() {
  }

  @Override
  public void write(Writer destination, MovieList movies) throws IOException {
    destination.write("<movielist>\n");
    if (movies.size() > 0) {
      writeMovies(destination, movies);
    }
    destination.write("</movielist>");
    destination.flush();
  }

  private void writeMovies(Writer destination, MovieList movies) throws IOException {
    for (var movie : movies.getMovies()) {
      String[] args = {movie.getName(), movie.getCategory().toString()};
      destination.write(movieFormat.format(args));
      writeRatings(destination, movie);
      destination.write("\n  </movie>\n");
    }
  }

  private void writeRatings(Writer destination, Movie movie) throws IOException {
    destination.write("\n    <ratings>");
    Iterator<Rating> ratings = movie.ratings();
    while (ratings.hasNext()) {
      var rating = ratings.next();
      if (rating.hasReview()) {
        destination
            .write(ratingFormatWithReview
                .format(new Object[]{rating.value(), rating.source(), rating.review()}));
      } else {
        destination
            .write(ratingFormatWithoutReview.format(new Object[]{rating.value(), rating.source()}));
      }
    }
    destination.write("\n    </ratings>");
  }

}
