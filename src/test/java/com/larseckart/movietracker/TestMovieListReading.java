package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Reader;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

class TestMovieListReading {

  private String emptyString = "";

  @Test
  void read_empty_string() throws Exception {
    try (Reader reader = new StringReader(emptyString)) {
      MovieList movieList = MovieList.readFrom(reader);
      assertThat(movieList.size()).isEqualTo(0);
    }
  }

  @Test
  void read_one_movie() throws Exception {
    try (Reader reader = new StringReader("Finding Nemo|Kids|5")) {
      MovieList movieList = MovieList.readFrom(reader);
      Movie movie = movieList.getMovie(0);
      assertThat(movieList.size()).isEqualTo(1);
      assertThat(movie.getName()).isEqualTo("Finding Nemo");
      assertThat(movie.getCategory()).isEqualTo(Category.KIDS);
      assertThat(movie.getRating()).isEqualTo(5);
    }
  }
}
