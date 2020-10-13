package com.larseckart.movietracker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Movie_should {

  private Movie movie;

  @BeforeEach
  void setUp() {
    movie = new Movie("Star Wars");
  }

  @Test
  void have_a_name() {
    assertThat(movie.getName()).isEqualTo("Star Wars");
  }

  @Test
  void display_movie_name_for_toString() {
    assertThat(movie.toString()).isEqualTo("Star Wars");
  }

  @Test
  void bad_rating() {
    movie.addRating(5);
    movie.addRating(3);

    assertThat(movie.getAverageRating()).isEqualTo(4);
  }

  @Test
  void renaming() throws Exception {
    String newMovie = "Star Trek";
    Movie aMovie = new Movie("Star Wars");

    aMovie.rename(newMovie);

    assertThat(aMovie.getName()).isEqualTo(newMovie);
  }

  @Test
  void null_name() throws Exception {
    String nullString = null;
    assertThrows(IllegalArgumentException.class, () -> new Movie(nullString));
  }

  @Test
  void empty_name() throws Exception {
    assertThrows(IllegalArgumentException.class, () -> new Movie(""));
  }

  @Test
  void rename_null_name() throws Exception {
    Movie aMovie = new Movie("Star Wars");

    assertThrows(IllegalArgumentException.class, () -> aMovie.rename(null));
  }

  @Test
  void rename_empty_name() throws Exception {
    Movie aMovie = new Movie("Star Wars");

    assertThrows(IllegalArgumentException.class, () -> aMovie.rename(""));
  }

  @Test
  void copy_constructor() throws Exception {
    Movie starWars = new Movie("Star Wars");
    Movie copyOfStarWars = new Movie(starWars);
    assertThat(copyOfStarWars).isNotSameAs(starWars);
    assertThat(copyOfStarWars).isEqualTo(starWars);
  }

  @Test
  void uncategorized_movie() throws Exception {
    assertThat(movie.getCategory()).isEqualTo("Uncategorized");
  }

  @Test
  void science_fiction_category_movie() throws Exception {
    Movie alien = new Movie("Alien", "Science Fiction");
    assertThat(alien.getCategory()).isEqualTo(Movie.SCIENCE_FICTION);
  }

  @Test
  void bad_category() throws Exception {
    assertThrows(IllegalArgumentException.class, () -> new Movie("Alien", "SciFi"));
  }
}
