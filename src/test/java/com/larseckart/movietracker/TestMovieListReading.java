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

      assertThat(movieList.size()).isEqualTo(1);
      Movie movie = movieList.getMovie(0);
      assertThat(movie.getName()).isEqualTo("Finding Nemo");
      assertThat(movie.getCategory()).isEqualTo(Category.KIDS);
      assertThat(movie.getRating()).isEqualTo(5);
    }
  }

  @Test
  void read_multiple_movies() throws Exception {
    try (Reader reader = new StringReader("Finding Nemo|Kids|5\nStar Wars|Science Fiction|3")) {
      MovieList movieList = MovieList.readFrom(reader);

      assertThat(movieList.size()).isEqualTo(2);
      Movie movie = movieList.getMovie(0);
      assertThat(movie.getName()).isEqualTo("Finding Nemo");
      assertThat(movie.getCategory()).isEqualTo(Category.KIDS);
      assertThat(movie.getRating()).isEqualTo(5);
      movie = movieList.getMovie(1);
      assertThat(movie.getName()).isEqualTo("Star Wars");
      assertThat(movie.getCategory()).isEqualTo(Category.SCIFI);
      assertThat(movie.getRating()).isEqualTo(3);
    }
  }
}
