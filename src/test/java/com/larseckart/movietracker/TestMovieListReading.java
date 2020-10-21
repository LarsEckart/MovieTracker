package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Reader;
import java.io.StringReader;
import org.junit.jupiter.api.Test;

class TestMovieListReading {

  private String emptyString = "";
  private final MovieListReader movieListReader = new PlainTextMovieListReader();

  @Test
  void read_empty_string() throws Exception {
    try (Reader reader = new StringReader(emptyString)) {
      MovieList movieList = movieListReader.read(reader);
      assertThat(movieList.size()).isEqualTo(0);
    }
  }

  @Test
  void read_one_movie() throws Exception {
    try (Reader reader = new StringReader("Finding Nemo|Kids|8|2")) {
      MovieList movieList = movieListReader.read(reader);

      assertThat(movieList.size()).isEqualTo(1);
      Movie movie = movieList.getMovie(0);
      assertThat(movie.getName()).isEqualTo("Finding Nemo");
      assertThat(movie.getCategory()).isEqualTo(Category.KIDS);
      assertThat(movie.getAverageRating()).isEqualTo(4);
    }
  }

  @Test
  void read_multiple_movies() throws Exception {
    try (Reader reader = new StringReader("Finding Nemo|Kids|5|1\nStar Wars|Science Fiction|9|3")) {
      MovieList movieList = movieListReader.read(reader);

      assertThat(movieList.size()).isEqualTo(2);
      Movie movie = movieList.getMovie(0);
      assertThat(movie.getName()).isEqualTo("Finding Nemo");
      assertThat(movie.getCategory()).isEqualTo(Category.KIDS);
      assertThat(movie.getAverageRating()).isEqualTo(5);
      movie = movieList.getMovie(1);
      assertThat(movie.getName()).isEqualTo("Star Wars");
      assertThat(movie.getCategory()).isEqualTo(Category.SCIFI);
      assertThat(movie.getAverageRating()).isEqualTo(3);
    }
  }
}
